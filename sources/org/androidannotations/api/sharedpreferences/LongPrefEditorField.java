package org.androidannotations.api.sharedpreferences;

import org.androidannotations.api.sharedpreferences.EditorHelper;

/* loaded from: classes2.dex */
public final class LongPrefEditorField<T extends EditorHelper<T>> extends AbstractPrefEditorField<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public LongPrefEditorField(T t, String str) {
        super(t, str);
    }

    public T put(long j) {
        this.editorHelper.getEditor().putLong(this.key, j);
        return this.editorHelper;
    }
}
