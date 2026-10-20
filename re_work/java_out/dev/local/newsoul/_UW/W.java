package dev/local/newsoul;

// ==== reconstructed from Ldev/local/newsoul/W ====
// super: Ljava/lang/Object
// source: "SourceFile"

public  abstract class W extends Ljava/lang/Object {

    public static final byte[] a;
    public static final OAEPParameterSpec b;

    static void static {}() {
      W.a = "NSDBG001".getBytes(StandardCharsets.US_ASCII);
      new Ljavax/crypto/spec/OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource$PSpecified.DEFAULT);
      W.b = new Ljavax/crypto/spec/OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource$PSpecified.DEFAULT);
      return;
    }

    public static byte[] a(BLjava/security/PublicKey;[] p0) {
      if (p0.length > 0x800000) goto :cond_74;
      new Ljava/security/SecureRandom();
      new Ljava/security/SecureRandom().nextBytes(new byte[][0x20]);
      new Ljava/security/SecureRandom().nextBytes(new byte[][0xc]);
      :try_start_19:
      Ljavax/crypto/Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding").init(0x1, p1, W.b);
      new Ljava/io/ByteArrayOutputStream();
      new Ljava/io/DataOutputStream(new Ljava/io/ByteArrayOutputStream());
      new Ljava/io/DataOutputStream(new Ljava/io/ByteArrayOutputStream()).write(W.a);
      new Ljava/io/DataOutputStream(new Ljava/io/ByteArrayOutputStream()).writeInt(Ljavax/crypto/Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding").doFinal(p0.length).length);
      new Ljava/io/DataOutputStream(new Ljava/io/ByteArrayOutputStream()).write(Ljavax/crypto/Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding").doFinal(p0.length));
      new Ljava/io/DataOutputStream(new Ljava/io/ByteArrayOutputStream()).write(0x800000);
      new Ljavax/crypto/spec/SecretKeySpec(p0.length, "AES");
      new Ljavax/crypto/spec/GCMParameterSpec(0x80, 0x800000);
      Ljavax/crypto/Cipher.getInstance("AES/GCM/NoPadding").init(0x1, new Ljavax/crypto/spec/SecretKeySpec(p0.length, "AES"), new Ljavax/crypto/spec/GCMParameterSpec(0x80, 0x800000));
      Ljavax/crypto/Cipher.getInstance("AES/GCM/NoPadding").updateAAD(new Ljava/io/ByteArrayOutputStream().toByteArray());
      new Ljava/io/ByteArrayOutputStream().write(Ljavax/crypto/Cipher.getInstance("AES/GCM/NoPadding").doFinal(p0));
      :try_end_6b:
      Ljava/util/Arrays.fill(p0.length, v2);
      return p0.length;
      :catchall_6f:
      Ljava/util/Arrays.fill(p0.length, v2);
      throw e;
      :cond_74:
      new Ljava/io/IOException("Size limit");
      throw new Ljava/io/IOException("Size limit");

      // .catchall {:try_start_19 .. :try_end_6b} :catchall_6f
    }

}