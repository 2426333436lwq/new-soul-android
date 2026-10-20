/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;

public abstract class W {
    public static final byte[] a = "NSDBG001".getBytes(StandardCharsets.US_ASCII);
    public static final OAEPParameterSpec b = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);

    public static byte[] a(byte[] byArray, PublicKey object) {
        if (byArray.length <= 0x800000) {
            byte[] byArray2 = new byte[32];
            byte[] byArray3 = new byte[12];
            Object object2 = new SecureRandom();
            ((SecureRandom)object2).nextBytes(byArray2);
            ((SecureRandom)object2).nextBytes(byArray3);
            try {
                object2 = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
                ((Cipher)object2).init(1, (Key)object, b);
                object2 = ((Cipher)object2).doFinal(byArray2);
                object = new ByteArrayOutputStream();
                Object object3 = new DataOutputStream((OutputStream)object);
                ((OutputStream)object3).write(a);
                ((DataOutputStream)object3).writeInt(((Object)object2).length);
                ((OutputStream)object3).write((byte[])object2);
                ((OutputStream)object3).write(byArray3);
                object2 = ((ByteArrayOutputStream)object).toByteArray();
                object3 = Cipher.getInstance("AES/GCM/NoPadding");
                SecretKeySpec secretKeySpec = new SecretKeySpec(byArray2, "AES");
                GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, byArray3);
                ((Cipher)object3).init(1, (Key)secretKeySpec, gCMParameterSpec);
                ((Cipher)object3).updateAAD((byte[])object2);
                ((OutputStream)object).write(((Cipher)object3).doFinal(byArray));
                byArray = ((ByteArrayOutputStream)object).toByteArray();
                return byArray;
            }
            finally {
                Arrays.fill(byArray2, (byte)0);
            }
        }
        throw new IOException("Size limit");
    }
}

