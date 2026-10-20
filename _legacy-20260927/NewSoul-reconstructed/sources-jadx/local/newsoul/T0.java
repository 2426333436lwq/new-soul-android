package dev.local.newsoul;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class T0 {
    public static final Pattern a = Pattern.compile("[0-9]{1,9}\\.[0-9]{1,9}\\.[0-9]{1,9}");
    public static final Pattern b = Pattern.compile("new-soul-([0-9]{1,9}\\.[0-9]{1,9}\\.[0-9]{1,9})\\.zip");

    public static String a(String str, String str2) throws IOException {
        Matcher matcher = Pattern.compile(str2).matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IOException("发布页格式已变化，请直接打开发布页");
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [dev.local.newsoul.S0] */
    public static Y b() throws JSONException, IOException {
        final long jNanoTime = System.nanoTime() + 45000000000L;
        ?? r2 = new Object() { // from class: dev.local.newsoul.S0
            public final String a(String str, String str2) {
                long jNanoTime2 = System.nanoTime();
                long j = jNanoTime;
                long j2 = (j - jNanoTime2) / 1000000;
                if (j2 <= 0 || Thread.currentThread().isInterrupted()) {
                    throw new IOException("检测超时");
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setInstanceFollowRedirects(false);
                    httpURLConnection.setConnectTimeout((int) Math.min(8000L, j2));
                    httpURLConnection.setReadTimeout((int) Math.min(8000L, j2));
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
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i);
                            }
                            throw new IOException("检测超时");
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
        };
        Y y = null;
        String strA = r2.a("https://wwbnj.lanzoul.com/b007utki2d", null);
        String strA2 = a(strA, "url\\s*:\\s*['\"](/filemoreajax\\.php\\?file=[0-9]{1,12})['\"]");
        String strA3 = a(strA, "data\\s*:\\s*\\{([^}]{1,4096})\\}");
        String strA4 = a(strA3, "['\"]fid['\"]\\s*:\\s*([0-9]{1,12})\\s*,");
        if (!strA2.endsWith("=" + strA4)) {
            throw new IOException("发布页格式已变化");
        }
        StringBuilder sb = new StringBuilder("lx=2&fid=" + strA4);
        String[] strArr = {"uid", "puid", "rep", "t", "k", "up", "ls"};
        for (int i = 0; i < 7; i++) {
            String str = strArr[i];
            String strTrim = a(strA3, "['\"]" + str + "['\"]\\s*:\\s*([^,}]+)").trim();
            if (strTrim.matches("['\"][A-Za-z0-9_=-]{1,256}['\"]")) {
                strTrim = strTrim.substring(1, strTrim.length() - 1);
            } else if (strTrim.matches("[0-9]{1,12}")) {
                continue;
            } else {
                if (!strTrim.matches("[A-Za-z_$][A-Za-z0-9_$]{0,63}")) {
                    throw new IOException("发布页格式已变化");
                }
                strTrim = a(strA, "\\bvar\\s+" + Pattern.quote(strTrim) + "\\s*=\\s*['\"]([A-Za-z0-9_=-]{1,256})['\"]\\s*;");
            }
            sb.append('&');
            sb.append(str);
            sb.append('=');
            sb.append(URLEncoder.encode(strTrim, "UTF-8"));
        }
        sb.append("&pwd=3gin");
        HashSet hashSet = new HashSet();
        for (int i2 = 1; i2 <= 20; i2++) {
            JSONObject jSONObject = new JSONObject(r2.a("https://wwbnj.lanzoul.com".concat(strA2), String.valueOf(sb) + "&pg=" + i2));
            String strValueOf = String.valueOf(jSONObject.get("zt"));
            if (!"2".equals(strValueOf)) {
                if (!"1".equals(strValueOf)) {
                    throw new IOException("发布目录暂时无法读取，请稍后重试");
                }
                JSONArray jSONArray = jSONObject.getJSONArray("text");
                if (jSONArray.length() > 50 || !hashSet.add(jSONArray.toString())) {
                    throw new IOException("发布目录分页异常");
                }
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    String strValueOf2 = String.valueOf(jSONObject2.get("t"));
                    if (!"1".equals(strValueOf2)) {
                        if (!"0".equals(strValueOf2)) {
                            throw new IOException("发布目录格式已变化");
                        }
                        Matcher matcher = b.matcher(jSONObject2.getString("name_all"));
                        if (matcher.matches()) {
                            String string = jSONObject2.getString("id");
                            if (!string.matches("i[A-Za-z0-9]{5,63}")) {
                                throw new IOException("安装包链接格式异常");
                            }
                            String strGroup = matcher.group(1);
                            Y y2 = new Y(strGroup, "https://wwbnj.lanzoul.com/".concat(string));
                            if (y == null || c(strGroup, (String) y.a) > 0) {
                                y = y2;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                if (jSONArray.length() < 50) {
                    if (y == null) {
                        throw new IOException("发布目录中没有可识别的安装包");
                    }
                }
            } else if (y == null) {
                throw new IOException("发布目录中没有可识别的安装包");
            }
            return y;
        }
        throw new IOException("发布目录文件过多，请直接打开发布页");
    }

    public static int c(String str, String str2) {
        Pattern pattern = a;
        if (!pattern.matcher(str).matches() || !pattern.matcher(str2).matches()) {
            throw new IllegalArgumentException("无法识别版本号");
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        for (int i = 0; i < 3; i++) {
            int iCompareTo = new BigInteger(strArrSplit[i]).compareTo(new BigInteger(strArrSplit2[i]));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }
}
