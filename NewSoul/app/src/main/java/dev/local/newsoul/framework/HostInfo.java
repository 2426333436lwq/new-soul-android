package dev.local.newsoul.framework;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 宿主应用（Soul）的标识信息。
 *
 * <p>用于在 {@link dev.local.newsoul.host.HostAdapters} 中选择合适的适配器，
 * 以及在诊断面板中展示当前适配到的宿主版本。</p>
 *
 * <p>注：{@code ApplicationInfo} 自 API 34 起不再暴露版本字段，因此版本信息统一通过
 * {@link PackageManager#getPackageInfo(String, int)} 获取。</p>
 */
public final class HostInfo {

    private final String packageName;
    private final String versionName;
    private final long versionCode;

    public HostInfo(String packageName, String versionName, long versionCode) {
        this.packageName = packageName == null ? "" : packageName;
        this.versionName = versionName == null ? "" : versionName;
        this.versionCode = versionCode;
    }

    /**
     * 解析宿主版本信息；任何一步失败都退化为「仅包名」而不抛异常。
     */
    public static HostInfo from(PackageManager packageManager, String packageName) {
        if (packageManager == null) {
            return new HostInfo(packageName, "", 0L);
        }
        try {
            PackageInfo info = packageManager.getPackageInfo(packageName, 0);
            return new HostInfo(packageName, info.versionName, readVersionCode(info));
        } catch (Throwable ignored) {
            return new HostInfo(packageName, "", 0L);
        }
    }

    /**
     * 读取 versionCode。
     *
     * <p>{@link PackageInfo#getLongVersionCode()} 为 API 28 引入，在更低版本上通过
     * {@link PackageInfo#versionCode} 读取。</p>
     */
    private static long readVersionCode(PackageInfo info) {
        try {
            return info.getLongVersionCode();
        } catch (Throwable ignored) {
            // API 26/27 不存在该接口
        }
        try {
            return info.versionCode;
        } catch (Throwable ignored) {
            return 0L;
        }
    }

    public String packageName() {
        return packageName;
    }

    public String versionName() {
        return versionName;
    }

    public long versionCode() {
        return versionCode;
    }

    /** 形如 {@code cn.soulapp.android 6.37.0 (6370)} 的可读描述。 */
    public String describe() {
        return packageName + " " + versionName + " (" + versionCode + ")";
    }

    /** 版本名是否与给定值完全相同。 */
    public boolean versionNameEquals(String expected) {
        return expected != null && expected.equals(versionName);
    }

    /** 版本名是否以给定前缀开头（用于「6.37.x」这类系列适配）。 */
    public boolean versionNameStartsWith(String prefix) {
        return prefix != null && !prefix.isEmpty() && versionName.startsWith(prefix);
    }
}