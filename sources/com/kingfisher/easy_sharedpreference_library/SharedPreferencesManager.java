package com.kingfisher.easy_sharedpreference_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SharedPreferencesManager {
    private static final String DEFAULT_SHARED_PREFERENCE = "MySharedPreference";
    private static HashMap<String, SharedPreferencesManager> preferenceManagerHashMap = new HashMap<>();
    private SharedPreferences.Editor editor;
    private Gson gson = new Gson();
    private SharedPreferences sharedPreference;

    private SharedPreferencesManager(Context context, String str) {
        this.sharedPreference = context.getApplicationContext().getSharedPreferences(str, 0);
        this.editor = this.sharedPreference.edit();
    }

    public static synchronized SharedPreferencesManager getInstance() {
        SharedPreferencesManager sharedPreferencesManager;
        synchronized (SharedPreferencesManager.class) {
            sharedPreferencesManager = preferenceManagerHashMap.get(DEFAULT_SHARED_PREFERENCE);
            if (sharedPreferencesManager == null) {
                throw new IllegalStateException("The default share preference is not initialized before. You have to initialize it first by calling init(Context, boolean, String...) function");
            }
        }
        return sharedPreferencesManager;
    }

    public static synchronized SharedPreferencesManager getInstance(String str) {
        SharedPreferencesManager sharedPreferencesManager;
        synchronized (SharedPreferencesManager.class) {
            sharedPreferencesManager = preferenceManagerHashMap.get(str);
            if (sharedPreferencesManager == null) {
                throw new IllegalStateException("The share preference: " + str + " is not initialized before. You have to initialize it first by calling init(Context, boolean, String...) function");
            }
        }
        return sharedPreferencesManager;
    }

    private static SharedPreferencesManager getSharedPreferenceManager(Context context, String str) {
        if (preferenceManagerHashMap == null) {
            preferenceManagerHashMap = new HashMap<>();
        }
        if (TextUtils.isEmpty(str)) {
            str = DEFAULT_SHARED_PREFERENCE;
        }
        SharedPreferencesManager sharedPreferencesManager = preferenceManagerHashMap.get(str);
        if (sharedPreferencesManager != null) {
            return sharedPreferencesManager;
        }
        SharedPreferencesManager sharedPreferencesManager2 = new SharedPreferencesManager(context, str);
        preferenceManagerHashMap.put(str, sharedPreferencesManager2);
        return sharedPreferencesManager2;
    }

    public static synchronized void init(Context context, boolean z, String... strArr) {
        synchronized (SharedPreferencesManager.class) {
            if (z) {
                try {
                    getSharedPreferenceManager(context, DEFAULT_SHARED_PREFERENCE);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (strArr != null && strArr.length != 0) {
                for (String str : strArr) {
                    getSharedPreferenceManager(context, str);
                }
            }
        }
    }

    public void clear() {
        this.editor.clear().commit();
    }

    public SharedPreferences getSharedPreference() {
        return this.sharedPreference;
    }

    public <T> T getValue(String str, Class<T> cls) {
        if (cls == Integer.class) {
            return (T) Integer.valueOf(this.sharedPreference.getInt(str, 0));
        }
        if (cls == Boolean.class) {
            return (T) Boolean.valueOf(this.sharedPreference.getBoolean(str, false));
        }
        if (cls == Float.class) {
            return (T) Float.valueOf(this.sharedPreference.getFloat(str, 0.0f));
        }
        if (cls == String.class) {
            return (T) this.sharedPreference.getString(str, "");
        }
        if (cls == Long.class) {
            return (T) Long.valueOf(this.sharedPreference.getLong(str, 0L));
        }
        String string = this.sharedPreference.getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return (T) this.gson.fromJson(string, (Class) cls);
    }

    public <T> T getValue(String str, Class<T> cls, T t) {
        return this.sharedPreference.contains(str) ? (T) getValue(str, cls) : t;
    }

    public <T> List<T> getValues(String str, Class<T[]> cls) {
        String string = this.sharedPreference.getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        Object[] objArr = (Object[]) this.gson.fromJson(string, (Class) cls);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        return arrayList;
    }

    public void printAllKeyValues() {
        for (Map.Entry<String, ?> entry : this.sharedPreference.getAll().entrySet()) {
            if (entry != null && entry.getValue() != null) {
                Log.e("SharedPreferenceManager", entry.getKey() + ": " + entry.getValue().toString());
            }
        }
    }

    public SharedPreferencesManager putValue(String str, Object obj) {
        if (obj instanceof Integer) {
            this.editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            this.editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            this.editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof String) {
            this.editor.putString(str, (String) obj);
        } else if (obj instanceof Long) {
            this.editor.putLong(str, ((Long) obj).longValue());
        } else {
            this.editor.putString(str, this.gson.toJson(obj));
        }
        this.editor.commit();
        return this;
    }

    public void remove(String str) {
        this.editor.remove(str).commit();
    }
}
