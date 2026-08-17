package org.androidannotations.api.sharedpreferences;

import android.content.SharedPreferences;
import org.androidannotations.api.sharedpreferences.EditorHelper;

/* loaded from: classes2.dex */
public abstract class EditorHelper<T extends EditorHelper<T>> {
    private final SharedPreferences.Editor editor;

    public EditorHelper(SharedPreferences sharedPreferences) {
        this.editor = sharedPreferences.edit();
    }

    private T cast() {
        return this;
    }

    public final void apply() {
        SharedPreferencesCompat.apply(this.editor);
    }

    protected BooleanPrefEditorField<T> booleanField(String str) {
        return new BooleanPrefEditorField<>(cast(), str);
    }

    public final T clear() {
        this.editor.clear();
        return cast();
    }

    protected FloatPrefEditorField<T> floatField(String str) {
        return new FloatPrefEditorField<>(cast(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    protected IntPrefEditorField<T> intField(String str) {
        return new IntPrefEditorField<>(cast(), str);
    }

    protected LongPrefEditorField<T> longField(String str) {
        return new LongPrefEditorField<>(cast(), str);
    }

    protected StringPrefEditorField<T> stringField(String str) {
        return new StringPrefEditorField<>(cast(), str);
    }

    protected StringSetPrefEditorField<T> stringSetField(String str) {
        return new StringSetPrefEditorField<>(cast(), str);
    }
}
