package dev.local.newsoul.feature.chat.voice;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.local.newsoul.feature.chat.ChatBridges;
import dev.local.newsoul.framework.HookIds;
import dev.local.newsoul.framework.Hooks;
import dev.local.newsoul.framework.HostClasses;
import dev.local.newsoul.framework.Logger;
import dev.local.newsoul.ui.ModuleDialog;
import dev.local.newsoul.ui.SurfaceStyle;
import dev.local.newsoul.ui.ViewKit;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

/**
 * 语音库功能域核心（原混淆类 {@code d3}）。
 *
 * <p>职责：</p>
 * <ol>
 *   <li>在会话 Fragment 的 {@code onResume}/{@code onPause} 上安装 hook（原 {@code F.h} 的语音段），
 *       分别在恢复后延迟探测语音入口、在暂停前关闭属于当前 Fragment 的对话框；</li>
 *   <li>为「存入语音库」提供会话入口（{@link #save}，原 {@code d3.h}），供复读菜单通过
 *       {@link ChatBridges.VoiceLibrarySaver} 调用；</li>
 *   <li>提供宿主对象访问、Toast、失败提示与音频下载等公共原语。</li>
 * </ol>
 *
 * <p>宿主对象（Fragment、会话、消息）以 {@code Object} 形式传递，统一通过反射访问；
 * 与原实现一致，找不到成员时抛出反射异常，由调用方决定降级策略。</p>
 *
 * <p>并发：原 {@code d3} 持有主线程 Handler 与单线程执行器，后台流程（读取/保存/发送）
 * 都在 {@link #worker} 上串行执行，结果再投递回 {@link #mainHandler}。</p>
 */
public final class VoiceLibrary implements ChatBridges.VoiceLibrarySaver {

    /** 语音入口视图的资源名（原 {@code d3.d} 中按序探测）。 */
    private static final String[] ENTRY_VIEW_IDS = {
            "menu_tab_voice_inner_outside", "menu_tab_voice_inner", "menu_tab_voice"
    };

    /** 需要额外清除触摸监听的外侧入口资源名。 */
    private static final String ENTRY_OUTSIDE_ID = "menu_tab_voice_inner_outside";

    /** 音频下载最大重定向次数（原 {@code d3.b} 的循环上界）。 */
    private static final int MAX_REDIRECTS = 6;

    /** 音频下载连接超时（毫秒，原 {@code 15000}）。 */
    private static final int CONNECT_TIMEOUT_MILLIS = 15000;

    /** 音频下载读取超时（毫秒，原 {@code 20000}）。 */
    private static final int READ_TIMEOUT_MILLIS = 20000;

    /** 音频文件体积上限：20 MB（原 {@code 20971520}）。 */
    private static final long MAX_AUDIO_BYTES = 0x1400000L;

    /** 动作项字号（px，原 {@code 15}）。 */
    private static final int ACTION_TEXT_SIZE = 15;

    /** 动作项内边距（dp，原 {@code 12}）。 */
    private static final int ACTION_PADDING_DP = 12;

    /** 动作项最小高度（dp，原 {@code 48}）。 */
    private static final int ACTION_MIN_HEIGHT_DP = 48;

    /** 原静态就绪标志 {@code d3.g}：安装成功后才置位。 */
    static volatile boolean ready;

    /** 主线程 Handler（原字段 {@code a}）。 */
    final Handler mainHandler = new Handler(Looper.getMainLooper());

    /** 后台单线程执行器（原字段 {@code b}）。 */
    final ExecutorService worker = Executors.newSingleThreadExecutor();

    /** 已挂接长按监听的入口视图（原字段 {@code c}）。 */
    final WeakHashMap<View, Boolean> attachedViews = new WeakHashMap<>();

    /** 当前账号读取方法 {@code cn.soulapp.imlib.j0.g()}（原字段 {@code d}）。 */
    Method accountGetter;

    /** 原生发送语音方法 {@code y4.q2(String,int,ImMessage)}（原字段 {@code e}）。 */
    Method sendVoiceMethod;

    /** 当前展示中的语音库对话框（原字段 {@code f}）。 */
    VoiceLibraryDialog currentDialog;

    // ------------------------------------------------------------ 访问器

    /** 主线程 Handler（原字段 {@code a}）。 */
    Handler mainHandler() {
        return mainHandler;
    }

    /** 后台执行器（原字段 {@code b}）。 */
    ExecutorService worker() {
        return worker;
    }

    /** 当前账号读取方法（原字段 {@code d}）。 */
    Method accountGetter() {
        return accountGetter;
    }

    /** 原生发送语音方法（原字段 {@code e}）。 */
    Method sendVoiceMethod() {
        return sendVoiceMethod;
    }

    /** 当前展示中的对话框（原字段 {@code f}）。 */
    VoiceLibraryDialog currentDialog() {
        return currentDialog;
    }

    /** 更新当前对话框引用（原 {@code d3.f} 的赋值）。 */
    void setCurrentDialog(VoiceLibraryDialog dialog) {
        this.currentDialog = dialog;
    }

    // ------------------------------------------------------------ 安装

    /**
     * 反射解析宿主成员并安装 hook（原 {@code F.h} 的语音安装段）。
     *
     * <p>任一签名不匹配或 hook 安装失败即抛异常，由 {@link VoiceLibraryFeature} 捕获后放弃整域
     * —— 与原实现「异常则不置就绪标志」一致。</p>
     */
    void install(XposedModule module, ClassLoader classLoader) throws Exception {
        this.accountGetter = classLoader.loadClass(HostClasses.IMLIB_SESSION_PROVIDER)
                .getMethod("g", (Class<?>[]) null);
        this.sendVoiceMethod = classLoader.loadClass(HostClasses.CHAT_UTILS_Y4)
                .getMethod("q2", String.class, Integer.TYPE,
                        classLoader.loadClass(HostClasses.IMLIB_IM_MESSAGE));

        Class<?> fragmentClass = classLoader.loadClass(HostClasses.CHAT_CONVERSATION_FRAGMENT);
        Method onResume = fragmentClass.getDeclaredMethod("onResume", (Class<?>[]) null);
        Method onPause = fragmentClass.getDeclaredMethod("onPause", (Class<?>[]) null);

        installHook(module, HookIds.VOICE_LIBRARY_ENTRY, onResume,
                VoiceLibraryFeature.entryHooker(this));
        installHook(module, HookIds.VOICE_LIBRARY_CLOSE, onPause,
                VoiceLibraryFeature.closeHooker(this));

        ready = true;
        Logger.i("voice library installed; local storage and native audio upload");
    }

    /** 安装单个 hook 并去优化；失败抛出以使整个功能域放弃安装。 */
    private static void installHook(XposedModule module, String id, Method target,
                                    io.github.libxposed.api.XposedInterface.Hooker hooker) {
        if (!Hooks.intercept(module, id, target, hooker)) {
            throw new IllegalStateException("Hook unavailable: " + id);
        }
        Hooks.deoptimize(module, id, target);
    }

    // ------------------------------------------------------------ 入口

    /**
     * 探测并挂接语音库入口的长按监听（原 {@code d3.d}）。
     *
     * <p>只处理处于恢复态、且尚未被挂接长按监听的语音图标；外侧入口额外清除触摸监听。</p>
     */
    void attachEntryPoints(Object host) throws ReflectiveOperationException {
        Activity activity = (Activity) call(host, "getActivity");
        if (activity == null || !((Boolean) call(host, "isResumed"))) {
            return;
        }
        View rootView = (View) call(host, "getView");
        if (rootView == null) {
            return;
        }
        WeakReference<Object> reference = new WeakReference<>(host);
        for (String entryId : ENTRY_VIEW_IDS) {
            int identifier = activity.getResources()
                    .getIdentifier(entryId, "id", activity.getPackageName());
            View entry = identifier == 0 ? null : rootView.findViewById(identifier);
            if (entry == null
                    || attachedViews.containsKey(entry)
                    || LongClickListeners.present(entry)) {
                continue;
            }
            entry.setOnLongClickListener(new VoiceLibraryEntryListener(this, activity, reference));
            if (ENTRY_OUTSIDE_ID.equals(entryId)) {
                entry.setOnTouchListener(null);
            }
            attachedViews.put(entry, Boolean.TRUE);
        }
    }

    /**
     * 把一条语音消息存入本地语音库（原 {@code d3.h}）。
     *
     * @throws IllegalArgumentException 音频地址非法或时长不大于 0（{@code "Invalid voice"}）
     */
    @Override
    public void save(Object host, Object audioMessage) throws Exception {
        VoiceLibraryDialog dialog = new VoiceLibraryDialog(this, host);
        String url = (String) readField(audioMessage, "url");
        int duration = ((Integer) readField(audioMessage, "duration")).intValue();
        if (!isHttpUrl(url) || duration <= 0) {
            throw new IllegalArgumentException("Invalid voice");
        }
        VoiceLibraryDialog previous = currentDialog;
        if (previous != null) {
            previous.close();
        }
        currentDialog = dialog;
        dialog.showRemarkEditor("保存到语音库", "",
                new VoiceSaveSubmit(dialog, url, duration),
                new VoicePostSaveAction(VoicePostSaveAction.CLOSE, dialog));
    }

    // ------------------------------------------------------------ 公共原语

    /** 生成一个可点击的动作项（原静态方法 {@code d3.a}）。 */
    static TextView actionItem(ModuleDialog dialog, String text, RunnableAction action) {
        Context context = dialog.context();
        TextView view = ViewKit.text(context, text, ACTION_TEXT_SIZE, dialog.titleColor());
        view.setGravity(Gravity.CENTER);
        int padding = ViewKit.dp(context, ACTION_PADDING_DP);
        view.setPadding(padding, padding, padding, padding);
        view.setMinHeight(ViewKit.dp(context, ACTION_MIN_HEIGHT_DP));
        view.setSingleLine(false);
        view.setEllipsize(null);
        ViewKit.makeFocusable(view);
        dialog.surfaceStyle().apply(view, 9, SurfaceStyle.radiusFor(9));
        view.setOnClickListener(new VoiceActionClick(action, dialog));
        return view;
    }

    /**
     * 下载音频到本地文件（原静态方法 {@code d3.b}）：不跟随自动重定向，只接受 200，
     * 超过 20 MB 或重定向超过 6 次即失败。
     */
    static void download(File target, String address) throws IOException {
        String current = address;
        for (int attempt = 0; attempt < MAX_REDIRECTS; attempt++) {
            if (!isHttpUrl(current)) {
                throw new IOException("Invalid audio address");
            }
            HttpURLConnection connection = (HttpURLConnection) new URL(current).openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            connection.setReadTimeout(READ_TIMEOUT_MILLIS);
            connection.setInstanceFollowRedirects(false);
            try {
                int responseCode = connection.getResponseCode();
                if (responseCode >= 300 && responseCode < 400) {
                    String location = connection.getHeaderField("Location");
                    if (location == null) {
                        throw new IOException("Invalid redirect");
                    }
                    current = new URL(new URL(current), location).toString();
                    connection.disconnect();
                    continue;
                }
                if (responseCode != 200) {
                    throw new IOException("音频下载失败（" + responseCode + "）");
                }
                if (connection.getContentLengthLong() > MAX_AUDIO_BYTES) {
                    throw new IOException("语音文件过大");
                }
                try (InputStream input = connection.getInputStream();
                     FileOutputStream output = new FileOutputStream(target)) {
                    VoiceLibraryStore.copyBounded(input, output);
                }
                connection.disconnect();
                return;
            } catch (Throwable error) {
                connection.disconnect();
                throw error;
            }
        }
        throw new IOException("Too many redirects");
    }

    /**
     * 沿继承链查找宿主对象中类型为 {@code cn.soulapp.imlib.Conversation} 的实例字段
     * （原静态方法 {@code d3.c}）。
     *
     * @throws IllegalStateException 存在多个候选字段（{@code "Ambiguous conversation field"}）
     */
    static Object conversation(Object host) throws ReflectiveOperationException {
        Field matched = null;
        for (Class<?> type = host.getClass(); type != null; type = type.getSuperclass()) {
            for (Field field : type.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (field.getType().getName().equals(HostClasses.IMLIB_CONVERSATION)) {
                    if (matched != null) {
                        throw new IllegalStateException("Ambiguous conversation field");
                    }
                    matched = field;
                }
            }
        }
        if (matched == null) {
            throw new NoSuchFieldException(HostClasses.IMLIB_CONVERSATION);
        }
        matched.setAccessible(true);
        return matched.get(host);
    }

    /** 调用宿主对象的公有无参方法（原静态方法 {@code d3.e}）。 */
    static Object call(Object target, String name) throws ReflectiveOperationException {
        return target.getClass().getMethod(name, (Class<?>[]) null).invoke(target, (Object[]) null);
    }

    /**
     * 沿继承链读取宿主对象的声明字段（原静态方法 {@code d3.g}）。
     *
     * @throws NoSuchFieldException 整条继承链都找不到该字段
     */
    static Object readField(Object target, String name) throws NoSuchFieldException {
        for (Class<?> type = target.getClass(); type != null; type = type.getSuperclass()) {
            try {
                Field field = type.getDeclaredField(name);
                field.setAccessible(true);
                return field.get(target);
            } catch (NoSuchFieldException ignored) {
                // 继续沿父类查找
            } catch (IllegalAccessException ignored) {
                // 与找不到字段一致地向上抛
            }
        }
        throw new NoSuchFieldException(name);
    }

    /** 语音库操作失败的统一处理：记录日志并提示用户（原静态方法 {@code d3.f}）。 */
    static void failure(Context context, String message, Throwable error) {
        Logger.w("voice library operation failed; no automatic send retry", error);
        toast(context, message);
    }

    /** 短 Toast（原静态方法 {@code d3.i}）。 */
    static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** 判断字符串是否为可下载的 http/https 地址（原 {@code Q0.N}）。 */
    static boolean isHttpUrl(String value) {
        if (value == null) {
            return false;
        }
        try {
            URI uri = new URI(value);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            return ("https".equalsIgnoreCase(scheme) || "http".equalsIgnoreCase(scheme))
                    && host != null && !host.isEmpty() && uri.getUserInfo() == null;
        } catch (URISyntaxException ignored) {
            return false;
        }
    }
}
