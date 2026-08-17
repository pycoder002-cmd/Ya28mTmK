package com.stepstone.apprating.listener;

import cz.msebera.android.httpclient.cookie.ClientCookie;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RatingDialogListener.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \n2\u00020\u0001:\u0001\nJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Lcom/stepstone/apprating/listener/RatingDialogListener;", "", "onNegativeButtonClicked", "", "onNeutralButtonClicked", "onPositiveButtonClicked", "rate", "", ClientCookie.COMMENT_ATTR, "", "Companion", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public interface RatingDialogListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: RatingDialogListener.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stepstone/apprating/listener/RatingDialogListener$Companion;", "", "()V", "NULL", "Lcom/stepstone/apprating/listener/RatingDialogListener;", "getNULL", "()Lcom/stepstone/apprating/listener/RatingDialogListener;", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final RatingDialogListener NULL = new RatingDialogListener() { // from class: com.stepstone.apprating.listener.RatingDialogListener$Companion$NULL$1
            @Override // com.stepstone.apprating.listener.RatingDialogListener
            public void onNegativeButtonClicked() {
            }

            @Override // com.stepstone.apprating.listener.RatingDialogListener
            public void onNeutralButtonClicked() {
            }

            @Override // com.stepstone.apprating.listener.RatingDialogListener
            public void onPositiveButtonClicked(int rate, @NotNull String comment) {
                Intrinsics.checkParameterIsNotNull(comment, "comment");
            }
        };

        private Companion() {
        }

        @NotNull
        public final RatingDialogListener getNULL() {
            return NULL;
        }
    }

    void onNegativeButtonClicked();

    void onNeutralButtonClicked();

    void onPositiveButtonClicked(int rate, @NotNull String comment);
}
