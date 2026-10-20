/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 */
package dev.local.newsoul;

import dev.local.newsoul.S0;
import dev.local.newsoul.Y;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public abstract class T0 {
    public static final Pattern a = Pattern.compile("[0-9]{1,9}\\.[0-9]{1,9}\\.[0-9]{1,9}");
    public static final Pattern b = Pattern.compile("new-soul-([0-9]{1,9}\\.[0-9]{1,9}\\.[0-9]{1,9})\\.zip");

    public static String a(String object, String string) {
        object = Pattern.compile(string).matcher((CharSequence)object);
        if (((Matcher)object).find()) {
            return ((Matcher)object).group(1);
        }
        throw new IOException("\u53d1\u5e03\u9875\u683c\u5f0f\u5df2\u53d8\u5316\uff0c\u8bf7\u76f4\u63a5\u6253\u5f00\u53d1\u5e03\u9875");
    }

    public static Y b() {
        block17: {
            String string;
            int n;
            S0 s0 = new S0(System.nanoTime() + 45000000000L);
            Object object = null;
            CharSequence charSequence = s0.a("https://wwbnj.lanzoul.com/b007utki2d", null);
            String string2 = T0.a((String)charSequence, "url\\s*:\\s*['\"](/filemoreajax\\.php\\?file=[0-9]{1,12})['\"]");
            Object object2 = T0.a((String)charSequence, "data\\s*:\\s*\\{([^}]{1,4096})\\}");
            CharSequence charSequence2 = T0.a((String)object2, "['\"]fid['\"]\\s*:\\s*([0-9]{1,12})\\s*,");
            Object object3 = new StringBuilder("=");
            ((StringBuilder)object3).append((String)charSequence2);
            if (!string2.endsWith(((StringBuilder)object3).toString())) break block17;
            object3 = new StringBuilder("lx=2&fid=");
            ((StringBuilder)object3).append((String)charSequence2);
            StringBuilder stringBuilder = new StringBuilder(((StringBuilder)object3).toString());
            for (n = 0; n < 7; ++n) {
                string = (new String[]{"uid", "puid", "rep", "t", "k", "up", "ls"})[n];
                charSequence2 = new StringBuilder("['\"]");
                ((StringBuilder)charSequence2).append(string);
                ((StringBuilder)charSequence2).append("['\"]\\s*:\\s*([^,}]+)");
                object3 = T0.a((String)object2, ((StringBuilder)charSequence2).toString()).trim();
                if (((String)object3).matches("['\"][A-Za-z0-9_=-]{1,256}['\"]")) {
                    charSequence2 = ((String)object3).substring(1, ((String)object3).length() - 1);
                } else {
                    charSequence2 = object3;
                    if (!((String)object3).matches("[0-9]{1,12}")) {
                        if (((String)object3).matches("[A-Za-z_$][A-Za-z0-9_$]{0,63}")) {
                            object3 = Pattern.quote((String)object3);
                            charSequence2 = new StringBuilder("\\bvar\\s+");
                            ((StringBuilder)charSequence2).append((String)object3);
                            ((StringBuilder)charSequence2).append("\\s*=\\s*['\"]([A-Za-z0-9_=-]{1,256})['\"]\\s*;");
                            charSequence2 = T0.a((String)charSequence, ((StringBuilder)charSequence2).toString());
                        } else {
                            throw new IOException("\u53d1\u5e03\u9875\u683c\u5f0f\u5df2\u53d8\u5316");
                        }
                    }
                }
                stringBuilder.append('&');
                stringBuilder.append(string);
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String)charSequence2, "UTF-8"));
            }
            stringBuilder.append("&pwd=3gin");
            object2 = new HashSet();
            charSequence2 = object;
            for (n = 1; n <= 20; ++n) {
                block20: {
                    block21: {
                        block28: {
                            block19: {
                                block18: {
                                    object = "https://wwbnj.lanzoul.com".concat(string2);
                                    object3 = String.valueOf(stringBuilder);
                                    charSequence = new StringBuilder();
                                    ((StringBuilder)charSequence).append((String)object3);
                                    ((StringBuilder)charSequence).append("&pg=");
                                    ((StringBuilder)charSequence).append(n);
                                    object3 = new JSONObject(s0.a((String)object, ((StringBuilder)charSequence).toString()));
                                    object = String.valueOf(object3.get("zt"));
                                    if (!"2".equals(object)) break block18;
                                    if (charSequence2 == null) {
                                        throw new IOException("\u53d1\u5e03\u76ee\u5f55\u4e2d\u6ca1\u6709\u53ef\u8bc6\u522b\u7684\u5b89\u88c5\u5305");
                                    }
                                    break block19;
                                }
                                if (!"1".equals(object)) break block20;
                                charSequence = object3.getJSONArray("text");
                                if (charSequence.length() > 50 || !((HashSet)object2).add(charSequence.toString())) break block21;
                                for (int i = 0; i < charSequence.length(); ++i) {
                                    block24: {
                                        block26: {
                                            block23: {
                                                block27: {
                                                    block25: {
                                                        block22: {
                                                            object = charSequence.getJSONObject(i);
                                                            object3 = String.valueOf(object.get("t"));
                                                            if (!"1".equals(object3)) break block22;
                                                            object = charSequence2;
                                                            break block23;
                                                        }
                                                        if (!"0".equals(object3)) break block24;
                                                        object3 = object.getString("name_all");
                                                        if (((Matcher)(object3 = b.matcher((CharSequence)object3))).matches()) break block25;
                                                        object = charSequence2;
                                                        break block23;
                                                    }
                                                    if (!((String)(object = object.getString("id"))).matches("i[A-Za-z0-9]{5,63}")) break block26;
                                                    string = ((Matcher)object3).group(1);
                                                    object3 = new Y(string, "https://wwbnj.lanzoul.com/".concat((String)object));
                                                    if (charSequence2 == null) break block27;
                                                    object = charSequence2;
                                                    if (T0.c(string, (String)((Y)((Object)charSequence2)).a) <= 0) break block23;
                                                }
                                                object = object3;
                                            }
                                            charSequence2 = object;
                                            continue;
                                        }
                                        throw new IOException("\u5b89\u88c5\u5305\u94fe\u63a5\u683c\u5f0f\u5f02\u5e38");
                                    }
                                    throw new IOException("\u53d1\u5e03\u76ee\u5f55\u683c\u5f0f\u5df2\u53d8\u5316");
                                }
                                if (charSequence.length() >= 50) continue;
                                if (charSequence2 == null) break block28;
                            }
                            return charSequence2;
                        }
                        throw new IOException("\u53d1\u5e03\u76ee\u5f55\u4e2d\u6ca1\u6709\u53ef\u8bc6\u522b\u7684\u5b89\u88c5\u5305");
                    }
                    throw new IOException("\u53d1\u5e03\u76ee\u5f55\u5206\u9875\u5f02\u5e38");
                }
                throw new IOException("\u53d1\u5e03\u76ee\u5f55\u6682\u65f6\u65e0\u6cd5\u8bfb\u53d6\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            }
            throw new IOException("\u53d1\u5e03\u76ee\u5f55\u6587\u4ef6\u8fc7\u591a\uff0c\u8bf7\u76f4\u63a5\u6253\u5f00\u53d1\u5e03\u9875");
        }
        throw new IOException("\u53d1\u5e03\u9875\u683c\u5f0f\u5df2\u53d8\u5316");
    }

    public static int c(String stringArray, String stringArray2) {
        Pattern pattern = a;
        if (pattern.matcher((CharSequence)stringArray).matches() && pattern.matcher((CharSequence)stringArray2).matches()) {
            stringArray = stringArray.split("\\.");
            stringArray2 = stringArray2.split("\\.");
            for (int i = 0; i < 3; ++i) {
                int n = new BigInteger(stringArray[i]).compareTo(new BigInteger(stringArray2[i]));
                if (n == 0) continue;
                return n;
            }
            return 0;
        }
        throw new IllegalArgumentException("\u65e0\u6cd5\u8bc6\u522b\u7248\u672c\u53f7");
    }
}

