package org.androidannotations.api.sharedpreferences;

import org.androidannotations.api.sharedpreferences.EditorHelper;

/* loaded from: classes2.dex */
public final class IntPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public IntPrefEditorField(T t, String str) {
        super(t, str);
    }

    public T put(int i) {
        this.editorHelper.getEditor().putInt(this.key, i);
        return this.editorHelper;
    }
}
