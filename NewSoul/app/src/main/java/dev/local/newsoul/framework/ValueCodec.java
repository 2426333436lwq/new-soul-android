package dev.local.newsoul.framework;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * 值解析与诊断字符串工具。
 *
 * <p>集中放置与宿主对象无关的纯函数：设置界面的整数解析、URL 判定，以及复读诊断日志中
 * 的调用来源描述。这些逻辑在多个功能域中复用，且都不依赖运行中的宿主类型。</p>
 */
public final class ValueCodec {

    /** 模块自身包前缀：诊断来源描述时跳过。 */
    private static final String MODULE_PREFIX = "dev.local.newsoul.";

    /** 需要保留的调用方包前缀。 */
    private static final String HOST_PREFIX = "cn.soulapp.";
    private static final String FEATURE_PREFIX = "uj.";
    private static final String PATCH_MARKER = "patch";

    /** 诊断来源中帧之间的分隔符。 */
    private static final String FRAME_SEPARATOR = " <- ";

    /** 诊断来源最多保留的帧数。 */
    private static final int MAX_FRAMES = 6;

    /** 诊断来源为空时的占位文案。 */
    private static final String UNAVAILABLE = "unavailable";

    private ValueCodec() {
    }

    /**
     * 解析设置界面输入的整数。
     *
     * @param text  待解析文本
     * @param signed 允许负数时传 {@code true}
     * @return 解析结果
     * @throws IllegalArgumentException 文本非法或超出允许范围（含文案，可直接展示给用户）
     */
    public static int parseInt(String text, boolean signed) {
        try {
            int value = Integer.parseInt(text.trim());
            if (!signed && value < 0) {
                throw new NumberFormatException();
            }
            return value;
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException(signed
                    ? "请输入 -2147483648 至 2147483647 的整数"
                    : "请输入 0 至 2147483647 的整数");
        }
    }

    /** 判断字符串是否为「直链 HTTP(S) URL」：scheme 为 http/https、含 host、且不含 userInfo。 */
    public static boolean isDirectHttpUrl(String value) {
        if (value == null) {
            return false;
        }
        try {
            URI uri = new URI(value);
            String scheme = uri.getScheme();
            if (!"https".equalsIgnoreCase(scheme) && !"http".equalsIgnoreCase(scheme)) {
                return false;
            }
            String host = uri.getHost();
            if (host == null || host.isEmpty()) {
                return false;
            }
            return uri.getUserInfo() == null;
        } catch (URISyntaxException ignored) {
            return false;
        }
    }

    /**
     * 描述当前调用来源（复读诊断日志用）。
     *
     * <p>从调用栈中挑出首个属于宿主（{@code cn.soulapp.} / {@code uj.} / 含 {@code patch}）的帧，
     * 最多保留 {@value #MAX_FRAMES} 帧，以 {@code " <- "} 连接；全部被过滤时返回 {@code "unavailable"}。</p>
     */
    public static String describeCallSite() {
        StringBuilder builder = new StringBuilder();
        StackTraceElement[] frames = Thread.currentThread().getStackTrace();
        int kept = 0;
        for (StackTraceElement frame : frames) {
            String className = frame.getClassName();
            if (className.startsWith(MODULE_PREFIX) || Thread.class.getName().equals(className)) {
                continue;
            }
            if (!className.startsWith(HOST_PREFIX)
                    && !className.startsWith(FEATURE_PREFIX)
                    && !className.contains(PATCH_MARKER)) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append(FRAME_SEPARATOR);
            }
            builder.append(className)
                    .append('.')
                    .append(frame.getMethodName())
                    .append(':')
                    .append(frame.getLineNumber());
            if (++kept >= MAX_FRAMES) {
                break;
            }
        }
        return builder.length() == 0 ? UNAVAILABLE : builder.toString();
    }

    /** 以小写十六进制格式输出字节数组（SHA-256 等摘要的展示形式）。 */
    static String toHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (byte value : bytes) {
            builder.append(String.format(Locale.ROOT, "%02x", value & 0xff));
        }
        return builder.toString();
    }
}