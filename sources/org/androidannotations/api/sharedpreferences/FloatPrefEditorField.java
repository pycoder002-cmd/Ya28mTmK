package org.androidannotations.api.sharedpreferences;

import org.androidannotations.api.sharedpreferences.EditorHelper;

/* loaded from: classes2.dex */
public final class FloatPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatPrefEditorField(T t, String str) {
        super(t, str);
    }

    public T put(float f) {
        this.editorHelper.getEditor().putFloat(this.key, f);
        return this.editorHelper;
    }
}
