package dev.local.newsoul.feature.privacy;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 隐私照片落盘辅助（原 {@code Q0.T}）。
 *
 * <p>把位图写入系统相册：先以 {@code is_pending=1} 插入
 * {@link MediaStore.Images.Media#EXTERNAL_CONTENT_URI}（文件名 {@code NewSoul_<时间戳>.png}，
 * 相对路径 {@code Pictures/New Soul}），压缩写入后再把 {@code is_pending} 置 {@code 0} 并更新条目，
 * 使照片在写入完成后才对相册可见。</p>
 *
 * <p>压缩失败（{@code compress} 返回 {@code false}）或插入失败时抛出
 * {@link IllegalStateException#IllegalStateException(String) "Bitmap compression failed"}；
 * 清理路径中删除已插入的空条目后原样重抛原异常，保持调用方看到的异常类型不变。</p>
 */
public final class PrivacyPhotoStore {

    private static final String FIELD_DISPLAY_NAME = "_display_name";
    private static final String FIELD_MIME_TYPE = "mime_type";
    private static final String FIELD_RELATIVE_PATH = "relative_path";
    private static final String FIELD_IS_PENDING = "is_pending";

    private static final String MIME_TYPE_PNG = "image/png";
    private static final String RELATIVE_PATH = "Pictures/New Soul";
    private static final String FILE_NAME_PREFIX = "NewSoul_";
    private static final String FILE_NAME_SUFFIX = ".png";

    private static final int IS_PENDING_TRUE = 1;
    private static final int IS_PENDING_FALSE = 0;
    private static final int COMPRESS_QUALITY = 0x64;

    private PrivacyPhotoStore() {
    }

    /**
     * 保存位图到相册，返回写入的 {@link Uri}；插入失败返回 {@code null}。
     *
     * @throws IOException 写入过程中发生的 IO 异常（原样向上抛出）
     */
    public static Uri saveBitmapToPictures(Activity activity, Bitmap bitmap) throws IOException {
        ContentResolver resolver = activity.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FIELD_DISPLAY_NAME,
                FILE_NAME_PREFIX + System.currentTimeMillis() + FILE_NAME_SUFFIX);
        values.put(FIELD_MIME_TYPE, MIME_TYPE_PNG);
        values.put(FIELD_RELATIVE_PATH, RELATIVE_PATH);
        values.put(FIELD_IS_PENDING, IS_PENDING_TRUE);

        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (uri == null) {
            return null;
        }
        OutputStream out = null;
        try {
            out = resolver.openOutputStream(uri, "w");
            if (out == null
                    || !bitmap.compress(Bitmap.CompressFormat.PNG, COMPRESS_QUALITY, out)) {
                throw new IllegalStateException("Bitmap compression failed");
            }
            out.close();
            out = null;
            values.clear();
            values.put(FIELD_IS_PENDING, IS_PENDING_FALSE);
            resolver.update(uri, values, null, null);
            return uri;
        } catch (Throwable throwable) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable suppressed) {
                    throwable.addSuppressed(suppressed);
                }
            }
            resolver.delete(uri, null, null);
            throw throwable;
        }
    }
}
