package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class SharedPreferencesHelper {
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    protected BooleanPrefField booleanField(String str, boolean z) {
        return new BooleanPrefField(this.sharedPreferences, str, z);
    }

    public final void clear() {
        SharedPreferencesCompat.apply(this.sharedPreferences.edit().clear());
    }

    protected FloatPrefField floatField(String str, float f) {
        return new FloatPrefField(this.sharedPreferences, str, f);
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    protected IntPrefField intField(String str, int i) {
        return new IntPrefField(this.sharedPreferences, str, i);
    }

    protected LongPrefField longField(String str, long j) {
        return new LongPrefField(this.sharedPreferences, str, j);
    }

    protected StringPrefField stringField(String str, String str2) {
        return new StringPrefField(this.sharedPreferences, str, str2);
    }

    protected StringSetPrefField stringSetField(String str, Set<String> set) {
        return new StringSetPrefField(this.sharedPreferences, str, set);
    }
}
