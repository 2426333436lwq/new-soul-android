import io, sys

path = r"E:\student\NX\new-soul-1.34.0\NewSoul\app\src\main\java\dev\local\newsoul\feature\chat\game\GameResultFeature.java"
with io.open(path, encoding="utf-8") as f:
    t = f.read()

old = """        List<Field> candidates = new ArrayList<>();
        for (String name : CONTENT_FIELDS) {
            Field field = Reflect.findField(diceMsg, name);
            if (field != null) {
                candidates.add(field);
            }
        }
        if (candidates.isEmpty()) {
            Logger.e("game hook unavailable: no content field; send signature changed");
            return;
        }
        contentFields = candidates;
        Logger.i("game hook ready; fields=" + candidates.size());"""

new = """        // 字段发现：先按已知候选名找；找不到就按「类型 + 可写」兜底扫描。
        // 只依赖字段名在宿主改字段名时会失效（实测 host 6.37.0 的 DiceFingerMsg
        // 没有 content/msgContent/text/result/value 任何一个），形状兜底是必需的。
        List<Field> candidates = new ArrayList<>();
        for (String name : CONTENT_FIELDS) {
            Field field = Reflect.findField(diceMsg, name);
            if (field != null) {
                candidates.add(field);
            }
        }
        if (candidates.isEmpty()) {
            candidates = discoverWritableFields(diceMsg);
        }
        if (candidates.isEmpty()) {
            Logger.e("game hook unavailable: no content field; send signature changed");
            return;
        }
        contentFields = candidates;
        Logger.i("game hook ready; fields=" + candidates.size()
                + " names=" + fieldNames(candidates));"""

helpers = '''
    /**
     * 兜底扫描：找出消息类上所有「可写的 int / Integer / String / long」实例字段。
     *
     * <p>用于宿主改字段名的场景。刻意排除 static / final，只保留可能是
     * 「点数 / 手势结果」的标量字段。</p>
     */
    private static List<Field> discoverWritableFields(Class<?> clazz) {
        List<Field> found = new ArrayList<>();
        try {
            for (Field field : Reflect.allFields(clazz)) {
                int mod = field.getModifiers();
                if (java.lang.reflect.Modifier.isStatic(mod)
                        || java.lang.reflect.Modifier.isFinal(mod)) {
                    continue;
                }
                Class<?> type = field.getType();
                if (type == int.class || type == Integer.class
                        || type == String.class || type == long.class) {
                    found.add(field);
                }
            }
        } catch (Throwable throwable) {
            Logger.w("game field scan failed", throwable);
        }
        return found;
    }

    private static String fieldNames(List<Field> fields) {
        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(field.getName());
        }
        return builder.toString();
    }
'''

if old in t:
    t = t.replace(old, new)
    idx = t.rfind("}")
    t = t[:idx] + helpers + "}\n"
    with io.open(path, "w", encoding="utf-8", newline="\n") as f:
        f.write(t)
    print("PATCHED OK")
else:
    print("PATTERN NOT FOUND")
    # show tail for diagnosis
    print("---- tail ----")
    print(t[-600:])
