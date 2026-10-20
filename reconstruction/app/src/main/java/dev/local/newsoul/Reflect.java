package dev.local.newsoul;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 宿主反射访问层。
 *
 * 对应原始 DEX 的 Q0（HookRegistry 内的原语）、j.f/j.g/j.h（通用安装原语）、
 * p0（ReflectionHelper）。
 *
 * 原始实现的关键设计（二次开发务必保留）：
 *
 * 1. **签名自适应**：不硬编码宿主方法签名。给定候选方法名列表，逐个
 *    getDeclaredMethod，再按「参数个数 / 参数类型 / 返回类型 / static 修饰符」
 *    筛选。命中多个就抛 IllegalStateException("Ambiguous ...")，一个都没有就
 *    抛 NoSuchMethodException，让上层降级而不是 hook 错方法。
 *
 * 2. **版本兼容**：宿主升级导致签名变化时，安装器抛异常，外层 tryInstall 捕获后
 *    只禁用该功能，不影响其他功能。原 module.prop 的 exceptionMode=protective
 *    也是同一目的。
 *
 * 3. **deoptimize**：hook 前调用 XposedModule.deoptimize(Executable)，强制目标
 *    方法去优化，否则已 JIT 编译的方法 hook 不上。
 */
public final class Reflect {

    private Reflect() {}

    // ------------------------------------------------------------------ 类

    public static Class<?> load(ClassLoader cl, String name) throws ClassNotFoundException {
        return cl.loadClass(name);
    }

    public static Class<?> loadOrNull(ClassLoader cl, String name) {
        try {
            return cl.loadClass(name);
        } catch (Throwable t) {
            return null;
        }
    }

    // ------------------------------------------------------------------ 方法查找

    /**
     * 按候选名 + 参数类型精确查找。
     *
     * @param names 候选方法名，按优先级排列
     * @return 命中的 Method
     */
    public static Method findMethod(Class<?> cls, String[] names, Class<?>... params)
            throws NoSuchMethodException {
        for (String n : names) {
            try {
                return cls.getDeclaredMethod(n, params);
            } catch (NoSuchMethodException ignore) {
                // 试下一个候选名
            }
        }
        throw new NoSuchMethodException(cls.getName() + "." + String.join("|", names));
    }

    /**
     * 遍历全部声明方法，按参数类型序列精确匹配。
     * 命中多个 -> IllegalStateException（对应原实现的 "Ambiguous ..." 断言）
     */
    public static Method requireUniqueByParams(Class<?> cls, Class<?>... params) {
        List<Method> hits = new ArrayList<>();
        for (Method m : cls.getDeclaredMethods()) {
            if (java.util.Arrays.equals(m.getParameterTypes(), params)) {
                hits.add(m);
            }
        }
        if (hits.isEmpty()) {
            throw new NoSuchMethodException("no method with params " + java.util.Arrays.toString(params)
                    + " in " + cls.getName());
        }
        if (hits.size() > 1) {
            throw new IllegalStateException("Ambiguous method in " + cls.getName()
                    + " for params " + java.util.Arrays.toString(params));
        }
        return hits.get(0);
    }

    /** 按返回类型 + 参数个数匹配，用于宿主混淆了方法名的场景 */
    public static Method findByReturn(Class<?> cls, Class<?> ret, int paramCount) {
        List<Method> hits = new ArrayList<>();
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getReturnType() == ret && m.getParameterTypes().length == paramCount) {
                hits.add(m);
            }
        }
        if (hits.isEmpty()) {
            throw new NoSuchMethodException("no method returning " + ret.getName()
                    + " with " + paramCount + " params in " + cls.getName());
        }
        if (hits.size() > 1) {
            throw new IllegalStateException("Ambiguous method in " + cls.getName());
        }
        return hits.get(0);
    }

    // ------------------------------------------------------------------ 字段

    public static Field field(Class<?> cls, String name) throws NoSuchFieldException {
        return cls.getDeclaredField(name);
    }

    public static Field fieldOrNull(Class<?> cls, String name) {
        try {
            return cls.getDeclaredField(name);
        } catch (Throwable t) {
            return null;
        }
    }

    public static Object get(Field f, Object target) {
        try {
            f.setAccessible(true);
            return f.get(target);
        } catch (Throwable t) {
            throw new RuntimeException("read " + f.getName(), t);
        }
    }

    public static int getInt(Field f, Object target) {
        try {
            f.setAccessible(true);
            return f.getInt(target);
        } catch (Throwable t) {
            throw new RuntimeException("read " + f.getName(), t);
        }
    }

    public static long getLong(Field f, Object target) {
        try {
            f.setAccessible(true);
            return f.getLong(target);
        } catch (Throwable t) {
            throw new RuntimeException("read " + f.getName(), t);
        }
    }

    public static void set(Field f, Object target, Object value) {
        try {
            f.setAccessible(true);
            f.set(target, value);
        } catch (Throwable t) {
            throw new RuntimeException("write " + f.getName(), t);
        }
    }

    public static void setInt(Field f, Object target, int value) {
        try {
            f.setAccessible(true);
            f.setInt(target, value);
        } catch (Throwable t) {
            throw new RuntimeException("write " + f.getName(), t);
        }
    }

    public static void setLong(Field f, Object target, long value) {
        try {
            f.setAccessible(true);
            f.setLong(target, value);
        } catch (Throwable t) {
            throw new RuntimeException("write " + f.getName(), t);
        }
    }

    // ------------------------------------------------------------------ 调用

    public static Object invoke(Method m, Object target, Object... args) {
        try {
            m.setAccessible(true);
            return m.invoke(target, args);
        } catch (Throwable t) {
            throw new RuntimeException("invoke " + m.getName(), t);
        }
    }

    public static Object newInstance(Constructor<?> c, Object... args) {
        try {
            c.setAccessible(true);
            return c.newInstance(args);
        } catch (Throwable t) {
            throw new RuntimeException("new " + c.getDeclaringClass().getName(), t);
        }
    }

    // ------------------------------------------------------------------ 构造器

    /** 按参数类型序列查找构造器 */
    public static Constructor<?> ctor(Class<?> cls, Class<?>... params) throws NoSuchMethodException {
        return cls.getDeclaredConstructor(params);
    }

    public static Constructor<?> ctorOrNull(Class<?> cls, Class<?>... params) {
        try {
            return cls.getDeclaredConstructor(params);
        } catch (Throwable t) {
            return null;
        }
    }

    // ------------------------------------------------------------------ 工具

    /** 资源 id 查找（原 Z1.e / Q0.X 都走这条路） */
    public static int id(android.content.Context ctx, String name, String type) {
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }

    /** 宿主资源 id 查找（name 在宿主包里） */
    public static int hostId(android.content.Context ctx, String name, String type) {
        return ctx.getResources().getIdentifier(name, type, Host.PACKAGE);
    }

    /** 按宿主资源名找 View。对应 w2.k(Activity, String)。 */
    public static android.view.View hostView(android.app.Activity a, String name) {
        int id = hostId(a, name, "id");
        if (id == 0) return null;
        return a.findViewById(id);
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

    /** 宿主字符串 */
    public static String hostString(android.content.Context ctx, int resId) {
        try {
            return ctx.getPackageManager()
                    .getResourcesForApplication(Host.PACKAGE)
                    .getString(resId);
        } catch (Throwable t) {
            return null;
        }
    }
}
