package dev.local.newsoul;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class W {
    public static final byte[] a = "NSDBG001".getBytes(StandardCharsets.US_ASCII);
    public static final OAEPParameterSpec b = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);

    public static byte[] a(byte[] bArr, PublicKey publicKey) throws IOException {
        if (bArr.length > 8388608) {
            throw new IOException("Size limit");
        }
        byte[] bArr2 = new byte[32];
        byte[] bArr3 = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bArr2);
        secureRandom.nextBytes(bArr3);
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(1, publicKey, b);
            byte[] bArrDoFinal = cipher.doFinal(bArr2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.write(a);
            dataOutputStream.writeInt(bArrDoFinal.length);
            dataOutputStream.write(bArrDoFinal);
            dataOutputStream.write(bArr3);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Cipher cipher2 = Cipher.getInstance("AES/GCM/NoPadding");
            cipher2.init(1, new SecretKeySpec(bArr2, "AES"), new GCMParameterSpec(128, bArr3));
            cipher2.updateAAD(byteArray);
            byteArrayOutputStream.write(cipher2.doFinal(bArr));
            return byteArrayOutputStream.toByteArray();
        } finally {
            Arrays.fill(bArr2, (byte) 0);
        }
    }
}
