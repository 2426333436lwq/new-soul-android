package dev.local.newsoul;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 混合加密 + 加密诊断日志导出。
 *
 * 对应原始 DEX 的 W（HybridCrypto）+ Q0.M（rsaPublicKey）+ V（DiagnosticLog）。
 *
 * ⚠️ 原始实现里 Q0.M 内嵌了一个硬编码的 2048-bit RSA 公钥，导出文件只有
 * 作者持有私钥才能解密。重建时如需对接原作者的导出文件，把下面
 * ORIGINAL_PUBLIC_KEY_BASE64 换成原值即可（见 re_work/REPORT.md §6.3）。
 * 如果只是自用，建议用自己的密钥对，别用别人的公钥。
 *
 * 原实现的加密结构（ByteArrayOutputStream + DataOutputStream）：
 *   [4字节 AES 密钥长度][RSA-OAEP 加密的 AES 密钥]
 *   [4字节 IV 长度][IV]
 *   [4字节 AAD 长度][AAD]
 *   [AES-GCM 密文]
 */
public final class HybridCrypto {

    private HybridCrypto() {}

    /** 原作者的公钥（X.509 SubjectPublicKeyInfo，Base64）。 */
    public static final String ORIGINAL_PUBLIC_KEY_BASE64 =
            "MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqrDmgObzyP43bK71v6HgV0OBDB6+HIfkXftRMhNO"
            + "IwfWbfaNZRR0/HCNnI2QSn+bFWyGHUdu7fh67Cy9n0hCnhDQh36aa3nq7+jogQqSEquEqJHqyFIfxi+4/H"
            + "RJXyWAhs39M6sKyS4fJGK2xKP6gjN3oDzsunro5+ldPx9hfy/J38zm5u7qIhx5s37KKn9JoE58czyAoGp6"
            + "a476JcrqUf1z5F+dhRiFa/Q8Cs/Sa1U26OxSZ+EOSauQj19rZBaXPtHh7AAXyakRJfznNPHbaH9OWg5loO"
            + "B0nWHLyb/RYhlmdx/WTb+n2MiYOI2EkY4g2NanTLW4/Hydi6tM4gVTzs1xfqjdhHaI52iOXEvLdEnnOlk0"
            + "6ha0hFbfULxfDS4usDDs3J5G70kte+qrMmeqrcEmygaZnIUqKbVW6Qx6WIP8yVdNY3sBh1PwHy3LnXdsCF"
            + "QbdhFocSSjZqvx5AUVi0Z74E7WAQsvQioFR3oyuGjJdxaXF3PX5bE0gVX80wzXAgMBAAE=";

    private static final String AES_TRANSFORM = "AES/GCM/NoPadding";
    private static final String RSA_TRANSFORM = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final int GCM_TAG_BITS = 128;

    /**
     * 混合加密：AES-GCM 加密载荷，RSA-OAEP 包裹 AES 密钥。
     * 对应 W.a。
     */
    public static byte[] encrypt(byte[] plaintext, PublicKey publicKey, byte[] aad)
            throws Exception {
        SecureRandom rnd = new SecureRandom();

        // 1. 随机 AES 密钥 + IV
        byte[] aesKey = new byte[32];           // AES-256
        rnd.nextBytes(aesKey);
        byte[] iv = new byte[12];               // GCM 推荐 96-bit IV
        rnd.nextBytes(iv);

        // 2. AES-GCM 加密载荷（绑定 AAD）
        Cipher aes = Cipher.getInstance(AES_TRANSFORM);
        aes.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(aesKey, "AES"),
                new GCMParameterSpec(GCM_TAG_BITS, iv));
        if (aad != null && aad.length > 0) {
            aes.updateAAD(aad);
        }
        byte[] cipherText = aes.doFinal(plaintext);

        // 3. RSA-OAEP 包裹 AES 密钥
        Cipher rsa = Cipher.getInstance(RSA_TRANSFORM);
        rsa.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] wrappedKey = rsa.doFinal(aesKey);

        // 4. 归零 AES 密钥（原实现用 Arrays.fill 做同样的事）
        Arrays.fill(aesKey, (byte) 0);

        // 5. 按原结构序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(wrappedKey.length);
        dos.write(wrappedKey);
        dos.writeInt(iv.length);
        dos.write(iv);
        dos.writeInt(aad == null ? 0 : aad.length);
        if (aad != null && aad.length > 0) {
            dos.write(aad);
        }
        dos.writeInt(cipherText.length);
        dos.write(cipherText);
        dos.flush();
        return bos.toByteArray();
    }

    // ------------------------------------------------------------------ 诊断日志

    /**
     * 加密诊断日志导出。
     * 对应 V.e：把诊断记录打成 ZIP，再混合加密，超限分片写 .chunk。
     */
    public static byte[] exportEncrypted(Context ctx, PublicKey key, long sizeLimit)
            throws Exception {
        File dir = new File(ctx.getFilesDir(), "newsoul-debug");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            throw new IOException("No records");
        }
        Arrays.sort(files, (a, b) -> {
            int c = Long.compare(a.length(), b.length());
            return c != 0 ? c : a.getName().compareTo(b.getName());
        });

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(bos)) {
            long total = 0;
            for (File f : files) {
                zos.putNextEntry(new ZipEntry(f.getName()));
                try (FileInputStream fis = new FileInputStream(f)) {
                    byte[] buf = new byte[8192];
                    int n;
                    while ((n = fis.read(buf)) > 0) {
                        zos.write(buf, 0, n);
                    }
                }
                zos.closeEntry();
                total += f.length();
                if (sizeLimit > 0 && total > sizeLimit) {
                    throw new IOException("Size limit");
                }
            }
        }

        byte[] zip = bos.toByteArray();
        byte[] aad = ("newsoul-debug/" + ctx.getPackageName())
                .getBytes(java.nio.charset.StandardCharsets.UTF_8);
        return encrypt(zip, key, aad);
    }

    /** SHA-256 十六进制摘要。对应 Q0.L。 */
    public static String sha256Hex(Object o) {
        try {
            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("SHA-256");
            byte[] d = md.digest(String.valueOf(o)
                    .getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(d.length * 2);
            for (byte b : d) {
                sb.append(String.format(java.util.Locale.ROOT, "%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
