package com.blankj.utilcode.util;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class SPUtils {
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    public SPUtils(String str) {
        this.sp = Utils.getContext().getSharedPreferences(str, 0);
        this.editor = this.sp.edit();
        this.editor.apply();
    }

    public void clear() {
        this.editor.clear().apply();
    }

    public boolean contains(String str) {
        return this.sp.contains(str);
    }

    public Map<String, ?> getAll() {
        return this.sp.getAll();
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.sp.getBoolean(str, z);
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(String str, float f) {
        return this.sp.getFloat(str, f);
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public int getInt(String str, int i) {
        return this.sp.getInt(str, i);
    }

    public long getLong(String str) {
        return getLong(str, -1L);
    }

    public long getLong(String str, long j) {
        return this.sp.getLong(str, j);
    }

    public String getString(String str) {
        return getString(str, null);
    }

    public String getString(String str, String str2) {
        return this.sp.getString(str, str2);
    }

    public Set<String> getStringSet(String str) {
        return getStringSet(str, null);
    }

    public Set<String> getStringSet(String str, @Nullable Set<String> set) {
        return this.sp.getStringSet(str, set);
    }

    public void put(String str, float f) {
        this.editor.putFloat(str, f).apply();
    }

    public void put(String str, int i) {
        this.editor.putInt(str, i).apply();
    }

    public void put(String str, long j) {
        this.editor.putLong(str, j).apply();
    }

    public void put(String str, @Nullable String str2) {
        this.editor.putString(str, str2).apply();
    }

    public void put(String str, @Nullable Set<String> set) {
        this.editor.putStringSet(str, set).apply();
    }

    public void put(String str, boolean z) {
        this.editor.putBoolean(str, z).apply();
    }

    public void remove(String str) {
        this.editor.remove(str).apply();
    }
}
