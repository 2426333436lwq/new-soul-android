package dev.local.newsoul.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射通用原语集合。
 *
 * <p>宿主 {@code cn.soulapp.android} 的所有目标类都以字符串形式引用并运行时反射加载，
 * 本类提供统一的加载、查找、读写、调用能力，并在失败时返回 {@code null} 而不是抛出异常，
 * 以便上层实现「签名不匹配则优雅降级」的策略。</p>
 */
public final class Reflect {

    private Reflect() {
    }

    /**
     * 加载宿主类，失败返回 {@code null}。
     */
    public static Class<?> loadOrNull(ClassLoader classLoader, String className) {
        if (classLoader == null || className == null) {
            return null;
        }
        try {
            return Class.forName(className, false, classLoader);
        } catch (Throwable ignored) {
            return null;
        }
    }

    /** 沿类继承链查找声明方法（含私有），失败返回 {@code null}。 */
    public static Method findDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        if (clazz == null || name == null) {
            return null;
        }
        Class<?> current = clazz;
        while (current != null) {
            // 只允许沿继承链走到宿主自己的类；一旦进入 android.* / androidx.* 立即停止。
            // 否则 hook「宿主某 Activity 的 onCreate」时，若宿主没自己声明该方法，
            // 会一路找到 android.app.Activity.onCreate 并 hook 整个框架基类 ——
            // 那会让宿主任意 Activity 的回调都被改写，表现为大面积白屏。
            if (isFrameworkClass(current)) {
                return null;
            }
            try {
                Method method = current.getDeclaredMethod(name, parameterTypes);
                makeAccessible(method);
                return method;
            } catch (NoSuchMethodException ignored) {
                current = current.getSuperclass();
            } catch (Throwable ignored) {
                return null;
            }
        }
        return null;
    }

    /** 判断是否为框架类（不可 hook）。 */
    private static boolean isFrameworkClass(Class<?> clazz) {
        String name = clazz.getName();
        return name.startsWith("android.")
                || name.startsWith("androidx.")
                || name.startsWith("java.")
                || name.startsWith("javax.")
                || name.startsWith("kotlin.")
                || name.startsWith("com.android.")
                || "java.lang.Object".equals(name);
    }

    /**
     * 尝试解除访问限制。
     *
     * <p>失败时静默忽略：hook 场景下目标方法只需作为 {@link java.lang.reflect.Executable} 传入，
     * 并不一定需要真的可反射调用；若此处抛出会导致整个查找链路退化。</p>
     */
    private static void makeAccessible(java.lang.reflect.AccessibleObject object) {
        try {
            object.setAccessible(true);
        } catch (Throwable ignored) {
            // 隐藏 API 或模块系统限制下无法解除，保持原状即可
        }
    }

    /**
     * 按名称与「参数类型列表」匹配方法，允许参数类型为父类型（宽松匹配）。
     */
    public static Method findCompatibleMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        Method exact = findDeclaredMethod(clazz, name, parameterTypes);
        if (exact != null) {
            return exact;
        }
        for (Method method : allMethods(clazz)) {
            if (!method.getName().equals(name)) {
                continue;
            }
            if (isCompatible(method.getParameterTypes(), parameterTypes)) {
                makeAccessible(method);
                return method;
            }
        }
        return null;
    }

    /** 查找名称匹配的单个方法；若存在重载则返回参数最少的一个，其余交由调用方按签名挑选。 */
    public static Method findAnyMethod(Class<?> clazz, String name) {
        Method best = null;
        for (Method method : allMethods(clazz)) {
            if (method.getName().equals(name)) {
                if (best == null || method.getParameterCount() < best.getParameterCount()) {
                    best = method;
                }
            }
        }
        if (best != null) {
            makeAccessible(best);
        }
        return best;
    }

    /** 查找名称匹配的全部方法。 */
    public static List<Method> findMethods(Class<?> clazz, String name) {
        List<Method> result = new ArrayList<>();
        for (Method method : allMethods(clazz)) {
            if (method.getName().equals(name)) {
                makeAccessible(method);
                result.add(method);
            }
        }
        return result;
    }

    /** 按「返回类型 + 参数个数（可空）」筛选方法，用于宿主签名频繁变化时的候选探测。 */
    public static Method findMethodByShape(Class<?> clazz, String name, Class<?> returnType, Integer parameterCount) {
        for (Method method : allMethods(clazz)) {
            if (!method.getName().equals(name)) {
                continue;
            }
            if (returnType != null && !returnType.isAssignableFrom(method.getReturnType())) {
                continue;
            }
            if (parameterCount != null && method.getParameterCount() != parameterCount) {
                continue;
            }
            makeAccessible(method);
            return method;
        }
        return null;
    }

    /** 收集类及其全部父类的声明方法。 */
    public static List<Method> allMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            if (!isFrameworkClass(current)) {
                // getDeclaredMethods() 会解析每个方法的返回类型/参数类型。
                // 宿主用了 DataBinding / 未打包进本进程的可选依赖时，这一步会抛
                // NoClassDefFoundError（实测：androidx.databinding.ViewDataBinding），
                // 导致整个功能域安装失败。因此逐个类兜底，单个类失败不影响其余。
                try {
                    methods.addAll(Arrays.asList(current.getDeclaredMethods()));
                } catch (Throwable throwable) {
                    Logger.w("declared methods unavailable: " + current.getName(), throwable);
                }
            }
            current = current.getSuperclass();
        }
        return methods;
    }

    /** 收集类及其全部父类的声明字段。 */
    public static List<Field> allFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    /** 查找字段（含父类），失败返回 {@code null}。 */
    public static Field findField(Class<?> clazz, String name) {
        if (clazz == null || name == null) {
            return null;
        }
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            try {
                Field field = current.getDeclaredField(name);
                makeAccessible(field);
                return field;
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            } catch (Throwable ignored) {
                return null;
            }
        }
        return null;
    }

    /** 按类型查找字段（可指定必须为静态 / 实例字段）。 */
    public static Field findFieldByType(Class<?> clazz, Class<?> fieldType, Boolean isStatic) {
        for (Field field : allFields(clazz)) {
            if (!field.getType().equals(fieldType)) {
                continue;
            }
            if (isStatic != null && Modifier.isStatic(field.getModifiers()) != isStatic) {
                continue;
            }
            makeAccessible(field);
            return field;
        }
        return null;
    }

    public static Object getFieldValue(Object target, Field field) {
        if (field == null) {
            return null;
        }
        try {
            return field.get(target);
        } catch (Throwable ignored) {
            return null;
        }
    }

    public static boolean getBoolean(Object target, Field field, boolean fallback) {
        Object value = getFieldValue(target, field);
        return value instanceof Boolean ? (Boolean) value : fallback;
    }

    public static int getInt(Object target, Field field, int fallback) {
        Object value = getFieldValue(target, field);
        return value instanceof Number ? ((Number) value).intValue() : fallback;
    }

    public static void setFieldValue(Object target, Field field, Object value) {
        if (field == null) {
            return;
        }
        try {
            field.set(target, value);
        } catch (Throwable ignored) {
            // 写入失败时静默降级，避免影响宿主
        }
    }

    public static Object invoke(Object target, Method method, Object... args) {
        if (method == null) {
            return null;
        }
        try {
            return method.invoke(target, args);
        } catch (Throwable ignored) {
            return null;
        }
    }

    public static boolean invokeBoolean(Object target, Method method, boolean fallback, Object... args) {
        Object value = invoke(target, method, args);
        return value instanceof Boolean ? (Boolean) value : fallback;
    }

    public static int invokeInt(Object target, Method method, int fallback, Object... args) {
        Object value = invoke(target, method, args);
        return value instanceof Number ? ((Number) value).intValue() : fallback;
    }

    public static Constructor<?> findConstructor(Class<?> clazz, Class<?>... parameterTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
            makeAccessible(constructor);
            return constructor;
        } catch (Throwable ignored) {
            return null;
        }
    }

    /** 判断实参类型列表能否满足形参列表（形参为实参父类型即可）。 */
    private static boolean isCompatible(Class<?>[] formalTypes, Class<?>[] actualTypes) {
        if (formalTypes.length != actualTypes.length) {
            return false;
        }
        for (int i = 0; i < formalTypes.length; i++) {
            Class<?> formal = formalTypes[i];
            Class<?> actual = actualTypes[i];
            if (actual == null) {
                if (formal.isPrimitive()) {
                    return false;
                }
                continue;
            }
            if (!formal.isAssignableFrom(actual)) {
                return false;
            }
        }
        return true;
    }
}