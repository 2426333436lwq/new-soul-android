package dev.local.newsoul;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: S0.class */
public final /* synthetic */ class S0 {
    public final long a;

    public final String a(String str, String str2) throws IOException {
        long jNanoTime = System.nanoTime();
        long j = this.a;
        long C0039j2 = (j - jNanoTime) / 1000000;
        if (C0039j2 <= 0 || Thread.currentThread().isInterrupted()) {
            throw new IOException("检测超时");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        try {
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout((int) Math.min(8000L, C0039j2));
            httpURLConnection.setReadTimeout((int) Math.min(8000L, C0039j2));
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 NewSoul-UpdateCheck");
            httpURLConnection.setRequestProperty("Referer", "https://wwbnj.lanzoul.com/b007utki2d");
            if (str2 != null) {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpURLConnection.setDoOutput(true);
                byte[] bytes = str2.getBytes(StandardCharsets.UTF_8);
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(bytes);
                    outputStream.close();
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (httpURLConnection.getResponseCode() != 200) {
                throw new IOException("发布页连接失败");
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            String str3 = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
                            byteArrayOutputStream.close();
                            inputStream.close();
                            httpURLConnection.disconnect();
                            return str3;
                        }
                        if (byteArrayOutputStream.size() + i > 524288) {
                            throw new IOException("发布页响应过大");
                        }
                        if (System.nanoTime() >= j || Thread.currentThread().isInterrupted()) {
                            throw new IOException("检测超时");
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                } catch (Throwable th3) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                    throw th3;
                }
            } catch (Throwable th5) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th6) {
                        th5.addSuppressed(th6);
                    }
                }
                throw th5;
            }
        } catch (Throwable th7) {
            httpURLConnection.disconnect();
            throw th7;
        }
    }


    public S0(long p0) {
        super();
        this.a = p0;
    }
}
