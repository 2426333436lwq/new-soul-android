package dev.local.newsoul.feature.privacy;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.Future;

import dev.local.newsoul.framework.Logger;

/**
 * 隐私保存按钮点击监听（原 {@code l1} case 0，label {@code pswitch_225}）。
 *
 * <p>照片分支（{@code savePhoto}）通过反射调用宿主内置的 Glide，把原图地址加载为
 * {@link android.graphics.Bitmap} 后在后台线程写入系统相册（见 {@link PrivacyPhotoStore}）；
 * 视频分支经 {@link DownloadManager} 下载到 {@code Movies/New Soul}。</p>
 *
 * <p>所有对外文案（按钮文本、Toast、日志、DownloadManager 标题/描述、文件名前缀）与原实现逐字一致。
 * 原 {@code l1} 的另一条分支（屏蔽词对话框）与本功能域无关，未复刻。</p>
 */
public final class PrivacySaveClickListener implements android.view.View.OnClickListener {

    private static final String EXTRA_URL = "url";
    private static final String SERVICE_DOWNLOAD = "download";
    private static final String GLIDE_CLASS = "com.bumptech.glide.Glide";
    private static final String METHOD_LOAD = "load";
    private static final String PHOTO_SAVE_THREAD = "NewSoul-privacy-photo-save";
    private static final String VIDEO_TITLE = "New Soul 隐私视频";
    private static final String VIDEO_DESCRIPTION = "正在保存视频";
    private static final String VIDEO_MIME_TYPE = "video/mp4";
    private static final String VIDEO_DIRECTORY = "New Soul/";
    private static final String VIDEO_FILE_PREFIX = "NewSoul_";
    private static final String VIDEO_EXTENSION_PATTERN = "\\.(mp4|mov|m4v|webm)";
    private static final String VIDEO_EXTENSION_FALLBACK = ".mp4";

    private static final int QUERY_INDEX = 0x3F;
    private static final int SLASH_INDEX = 0x2F;
    private static final int DOT_INDEX = 0x2E;
    /** 扩展名长短上限（原 {@code 6}）。 */
    private static final int EXTENSION_MAX_LENGTH = 6;

    private final boolean savePhoto;
    private final Activity activity;
    private final TextView button;
    private final ClassLoader hostClassLoader;

    public PrivacySaveClickListener(boolean savePhoto, Activity activity, TextView button,
            ClassLoader hostClassLoader) {
        this.savePhoto = savePhoto;
        this.activity = activity;
        this.button = button;
        this.hostClassLoader = hostClassLoader;
    }

    @Override
    public void onClick(android.view.View view) {
        String url = activity.getIntent().getStringExtra(EXTRA_URL);
        if (savePhoto) {
            savePhoto(url);
        } else {
            saveVideo(url);
        }
    }

    /** 照片分支：Glide 反射加载原图后交给后台线程落盘。 */
    private void savePhoto(String url) {
        if (url == null || url.isEmpty()) {
            Toast.makeText(activity, "原图地址不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        button.setEnabled(false);
        button.setText("保存中…");
        try {
            Future<?> future = submitGlideRequest(url);
            Thread thread = new Thread(new PhotoSaveTask(future, activity, button),
                    PHOTO_SAVE_THREAD);
            thread.start();
        } catch (Throwable throwable) {
            button.setEnabled(true);
            button.setText("保存原图");
            Logger.e("privacy photo download setup failed", throwable);
            Toast.makeText(activity, "保存功能当前不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 反射构造 Glide 请求并取得 {@link Future}。
     *
     * <p>{@code load(Object)} 在不同 Glide 版本中声明于请求类的父类，因此沿继承链查找首个
     * {@code load(Object)} 声明；找不到时抛出 {@code "<类名>.load"}，
     * {@code submit()} 返回值非 {@link Future} 时抛出 {@code "Glide target is not Future"}。</p>
     */
    private Future<?> submitGlideRequest(String url) throws Exception {
        Class<?> glide = hostClassLoader.loadClass(GLIDE_CLASS);
        Object requestManager = glide.getMethod("with", Context.class).invoke(null, activity);
        Object bitmapRequest = requestManager.getClass().getMethod("asBitmap")
                .invoke(requestManager);
        Class<?> requestClass = bitmapRequest.getClass();

        Method load = null;
        Class<?> current = requestClass;
        while (current != null) {
            try {
                load = current.getDeclaredMethod(METHOD_LOAD, Object.class);
                load.setAccessible(true);
                break;
            } catch (NoSuchMethodException ignored) {
                current = current.getSuperclass();
            }
        }
        if (load == null) {
            throw new NoSuchMethodException(requestClass.getName() + ".load");
        }
        Object loaded = load.invoke(bitmapRequest, url);
        Object target = loaded.getClass().getMethod("submit").invoke(loaded);
        if (!(target instanceof Future)) {
            throw new IllegalStateException("Glide target is not Future");
        }
        return (Future<?>) target;
    }

    /** 视频分支：经 DownloadManager 下载到 Movies/New Soul。 */
    private void saveVideo(String url) {
        if (url == null || url.isEmpty()) {
            Toast.makeText(activity, "视频地址不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String fileName = VIDEO_FILE_PREFIX + System.currentTimeMillis() + resolveExtension(url);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle(VIDEO_TITLE);
            request.setDescription(VIDEO_DESCRIPTION);
            request.setMimeType(VIDEO_MIME_TYPE);
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES,
                    VIDEO_DIRECTORY + fileName);
            DownloadManager downloadManager = (DownloadManager) activity.getSystemService(
                    SERVICE_DOWNLOAD);
            if (downloadManager == null) {
                throw new IllegalStateException("DownloadManager unavailable");
            }
            long id = downloadManager.enqueue(request);
            Toast.makeText(activity, "已开始保存到 Movies/New Soul", Toast.LENGTH_SHORT).show();
            Logger.i("privacy video download enqueued: id=" + id);
        } catch (Throwable throwable) {
            Logger.e("privacy video download failed", throwable);
            Toast.makeText(activity, "视频保存失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    /** 从地址推导视频扩展名；无法识别时回退 {@code .mp4}。 */
    private static String resolveExtension(String url) {
        int query = url.indexOf(QUERY_INDEX);
        String path = query >= 0 ? url.substring(0, query) : url;
        int slash = path.lastIndexOf(SLASH_INDEX);
        int dot = path.lastIndexOf(DOT_INDEX);
        if (dot > slash && path.length() - dot <= EXTENSION_MAX_LENGTH) {
            String suffix = path.substring(dot).toLowerCase(Locale.ROOT);
            if (suffix.matches(VIDEO_EXTENSION_PATTERN)) {
                return suffix;
            }
        }
        return VIDEO_EXTENSION_FALLBACK;
    }

    /** 后台线程任务：等待 Glide 结果并写入相册（原 {@code H} case 4）。 */
    private static final class PhotoSaveTask implements Runnable {

        private final Future<?> future;
        private final Activity activity;
        private final TextView button;

        PhotoSaveTask(Future<?> future, Activity activity, TextView button) {
            this.future = future;
            this.activity = activity;
            this.button = button;
        }

        @Override
        public void run() {
            try {
                Object resource = future.get();
                if (!(resource instanceof android.graphics.Bitmap)) {
                    throw new IllegalStateException("Resource is not bitmap");
                }
                Uri uri = PrivacyPhotoStore.saveBitmapToPictures(activity,
                        (android.graphics.Bitmap) resource);
                activity.runOnUiThread(new PhotoSaveResultTask(activity, button, uri));
                Logger.i("privacy photo saved: " + uri);
            } catch (Throwable throwable) {
                Logger.e("privacy photo download failed", throwable);
                activity.runOnUiThread(new PhotoSaveFailureTask(activity, button));
            }
        }
    }

    /** 照片保存成功/插入失败的回填（原 {@code H} case 5）。 */
    private static final class PhotoSaveResultTask implements Runnable {

        private final Activity activity;
        private final TextView button;
        private final Uri uri;

        PhotoSaveResultTask(Activity activity, TextView button, Uri uri) {
            this.activity = activity;
            this.button = button;
            this.uri = uri;
        }

        @Override
        public void run() {
            button.setEnabled(true);
            button.setText("保存原图");
            String message = uri == null ? "保存失败" : "已保存到 Pictures/New Soul";
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }

    /** 照片保存失败的回填（原 {@code g} case 4）。 */
    private static final class PhotoSaveFailureTask implements Runnable {

        private final Activity activity;
        private final TextView button;

        PhotoSaveFailureTask(Activity activity, TextView button) {
            this.activity = activity;
            this.button = button;
        }

        @Override
        public void run() {
            button.setEnabled(true);
            button.setText("保存原图");
            Toast.makeText(activity, "保存失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }
}
