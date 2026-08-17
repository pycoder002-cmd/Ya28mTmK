package com.startapp;

import android.content.SharedPreferences;
import android.os.Build;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p5 implements SharedPreferences {
    public final SharedPreferences a;
    public final b b;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements SharedPreferences.Editor {
        public final SharedPreferences.Editor a;
        public final Map<String, ?> b;
        public final b c;
        public boolean d;

        public a(SharedPreferences.Editor editor, Map<String, ?> map, b bVar) {
            this.a = editor;
            this.b = map;
            this.c = bVar;
        }

        @Override // android.content.SharedPreferences.Editor
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a remove(String str) {
            if (this.b.containsKey(str)) {
                this.d = true;
            }
            this.a.remove(str);
            return this;
        }

        public a a(String str, int i) {
            a(str, (String) Integer.valueOf(i));
            this.a.putInt(str, i);
            return this;
        }

        public a a(String str, long j) {
            a(str, (String) Long.valueOf(j));
            this.a.putLong(str, j);
            return this;
        }

        public a a(String str, String str2) {
            a(str, str2);
            this.a.putString(str, str2);
            return this;
        }

        public final <T> void a(String str, T t) {
            if (this.c == null || aa.a(this.b.get(str), t)) {
                return;
            }
            this.d = true;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            if (Build.VERSION.SDK_INT < 9) {
                this.a.commit();
            } else {
                this.a.apply();
            }
            b bVar = this.c;
            if (bVar == null || !this.d) {
                return;
            }
            this.d = false;
            qb qbVar = (qb) bVar;
            qbVar.getClass();
            x9 x9Var = x9.a;
            x9.a.a(qbVar.a.b, MetaDataRequest.RequestReason.EXTRAS);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            if (!this.b.isEmpty()) {
                this.d = true;
            }
            this.a.clear();
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.a.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            a(str, (String) Boolean.valueOf(z));
            this.a.putBoolean(str, z);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            a(str, (String) Float.valueOf(f));
            this.a.putFloat(str, f);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            a(str, (String) Integer.valueOf(i));
            this.a.putInt(str, i);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            a(str, (String) Long.valueOf(j));
            this.a.putLong(str, j);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            a(str, str2);
            this.a.putString(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set set) {
            a(str, (String) set);
            this.a.putStringSet(str, set);
            return this;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
    }

    public p5(SharedPreferences sharedPreferences) {
        this(sharedPreferences, null);
    }

    public p5(SharedPreferences sharedPreferences, b bVar) {
        this.a = sharedPreferences;
        this.b = bVar;
    }

    @Override // android.content.SharedPreferences
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a edit() {
        return new a(this.a.edit(), this.a.getAll(), this.b);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        try {
            return this.a.contains(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        try {
            return this.a.getAll();
        } catch (Throwable unused) {
            return Collections.emptyMap();
        }
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        try {
            return this.a.getBoolean(str, z);
        } catch (Throwable unused) {
            return z;
        }
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        try {
            return this.a.getFloat(str, f);
        } catch (Throwable unused) {
            return f;
        }
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        try {
            return this.a.getInt(str, i);
        } catch (Throwable unused) {
            return i;
        }
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        try {
            return this.a.getLong(str, j);
        } catch (Throwable unused) {
            return j;
        }
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        try {
            return this.a.getString(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        try {
            return this.a.getStringSet(str, set);
        } catch (Throwable unused) {
            return set;
        }
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
