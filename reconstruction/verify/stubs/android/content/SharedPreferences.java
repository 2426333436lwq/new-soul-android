package android.content;

public interface SharedPreferences {

    interface Editor {
        Editor putBoolean(String key, boolean value);

        Editor putInt(String key, int value);

        Editor putLong(String key, long value);

        Editor putFloat(String key, float value);

        Editor putString(String key, String value);

        Editor remove(String key);

        Editor clear();

        boolean commit();

        void apply();
    }

    boolean getBoolean(String key, boolean defValue);

    int getInt(String key, int defValue);

    long getLong(String key, long defValue);

    float getFloat(String key, float defValue);

    String getString(String key, String defValue);

    boolean contains(String key);

    java.util.Map<String, ?> getAll();

    Editor edit();
}
