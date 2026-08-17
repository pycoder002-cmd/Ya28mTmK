package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.liulishuo.filedownloader.model.ConnectionModel;

/* loaded from: classes.dex */
public final class ConversationBuilder extends IndexableBuilder<ConversationBuilder> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ConversationBuilder() {
        super("Conversation");
    }

    public ConversationBuilder setId(@NonNull String str) {
        return put(ConnectionModel.ID, str);
    }
}
