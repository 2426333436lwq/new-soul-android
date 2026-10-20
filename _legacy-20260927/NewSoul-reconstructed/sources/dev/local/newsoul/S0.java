/*
 * Decompiled with CFR 0.152.
 */
package dev.local.newsoul;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public final class S0 {
    public final long a;

    public /* synthetic */ S0(long l) {
        this.a = l;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final String a(String var1_1, String var2_2) {
        block27: {
            block23: {
                var6_6 = System.nanoTime();
                var4_7 = this.a;
                if ((var6_6 = (var4_7 - var6_6) / 1000000L) <= 0L) throw new IOException("\u68c0\u6d4b\u8d85\u65f6");
                if (Thread.currentThread().isInterrupted() != false) throw new IOException("\u68c0\u6d4b\u8d85\u65f6");
                var1_1 = (HttpURLConnection)new URL((String)var1_1).openConnection();
                var1_1.setInstanceFollowRedirects(false);
                var1_1.setConnectTimeout((int)Math.min(8000L, var6_6));
                var1_1.setReadTimeout((int)Math.min(8000L, var6_6));
                var1_1.setUseCaches(false);
                var1_1.setRequestProperty("User-Agent", "Mozilla/5.0 NewSoul-UpdateCheck");
                var1_1.setRequestProperty("Referer", "https://wwbnj.lanzoul.com/b007utki2d");
                if (var2_2 == null) ** GOTO lbl34
                var1_1.setRequestMethod("POST");
                var1_1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                var1_1.setDoOutput(true);
                var8_8 = var2_2.getBytes(StandardCharsets.UTF_8);
                var1_1.setFixedLengthStreamingMode(((Object)var8_8).length);
                var2_2 = var1_1.getOutputStream();
                var2_2.write((byte[])var8_8);
                var2_2.close();
                ** GOTO lbl34
                catch (Throwable var8_9) {
                    if (var2_2 == null) throw var8_9;
                    try {
                        var2_2.close();
                        throw var8_9;
                    }
                    catch (Throwable var2_4) {
                        block26: {
                            block25: {
                                block24: {
                                    var8_9.addSuppressed(var2_4);
                                    throw var8_9;
lbl34:
                                    // 2 sources

                                    if (var1_1.getResponseCode() != 200) break block23;
                                    var2_2 = var1_1.getInputStream();
                                    var8_8 = new ByteArrayOutputStream();
                                    try {
                                        var9_12 /* !! */  = new byte[4096];
                                        while ((var3_14 = var2_2.read(var9_12 /* !! */ )) != -1) {
                                            if (var8_8.size() + var3_14 > 524288) {
                                                var9_12 /* !! */  = (byte[])new IOException;
                                                var9_12 /* !! */ ("\u53d1\u5e03\u9875\u54cd\u5e94\u8fc7\u5927");
                                                throw var9_12 /* !! */ ;
                                            }
                                            if (System.nanoTime() < var4_7 && !Thread.currentThread().isInterrupted()) {
                                                var8_8.write(var9_12 /* !! */ , 0, var3_14);
                                                continue;
                                            }
                                            break block24;
                                        }
                                        break block25;
                                    }
                                    catch (Throwable var9_13) {
                                        break block26;
                                    }
                                }
                                var9_12 /* !! */  = (byte[])new IOException;
                                var9_12 /* !! */ ("\u68c0\u6d4b\u8d85\u65f6");
                                throw var9_12 /* !! */ ;
                            }
                            var9_12 /* !! */  = (byte[])new String(var8_8.toByteArray(), StandardCharsets.UTF_8);
                            var8_8.close();
                            var2_2.close();
                            var1_1.disconnect();
                            return var9_12 /* !! */ ;
                            {
                                catch (Throwable var2_3) {}
                            }
                        }
                        try {
                            var8_8.close();
                            throw var9_13;
                        }
                        catch (Throwable var8_11) {
                            try {
                                var9_13.addSuppressed(var8_11);
                                throw var9_13;
                            }
                            catch (Throwable var8_10) {}
                        }
                        if (var2_2 == null) throw var8_10;
                        try {
                            var2_2.close();
                            throw var8_10;
                        }
                        catch (Throwable var2_5) {
                            var8_10.addSuppressed(var2_5);
                            throw var8_10;
                        }
                    }
                }
                break block27;
            }
            var2_2 = new IOException("\u53d1\u5e03\u9875\u8fde\u63a5\u5931\u8d25");
            throw var2_2;
        }
        var1_1.disconnect();
        throw var2_3;
    }
}

