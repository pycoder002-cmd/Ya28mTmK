package com.stepstone.apprating;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.stepstone.apprating.C;
import com.stepstone.apprating.common.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AppRatingDialog.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005R\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialog;", "", "activity", "Landroid/support/v4/app/FragmentActivity;", "data", "Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "Lcom/stepstone/apprating/AppRatingDialog$Builder;", "(Landroid/support/v4/app/FragmentActivity;Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;)V", "show", "", "Builder", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class AppRatingDialog {
    private final FragmentActivity activity;
    private final Builder.Data data;

    /* compiled from: AppRatingDialog.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\rJ\u0010\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010\u0016\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0014J\u0010\u0010\u0018\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010\u0019\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0014J\u0010\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0014J\u0010\u0010\u001d\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0014\u0010\u001e\u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\rJ\u0010\u0010#\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0014J\u0010\u0010%\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010&\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\rJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0014J\u0010\u0010(\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010)\u001a\u00020\u00002\b\b\u0001\u0010*\u001a\u00020\rR\u0015\u0010\u0003\u001a\u00060\u0004R\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006,"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialog$Builder;", "Ljava/io/Serializable;", "()V", "data", "Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "getData", "()Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "create", "Lcom/stepstone/apprating/AppRatingDialog;", "activity", "Landroid/support/v4/app/FragmentActivity;", "setCommentBackgroundColor", "colorResId", "", "setCommentTextColor", "setDefaultRating", "defaultRating", "setDescription", "resId", "content", "", "setDescriptionTextColor", "setHint", "hint", "setHintTextColor", "setNegativeButtonText", "negativeButtonText", "setNeutralButtonText", "neutralButtonText", "setNoteDescriptionTextColor", "setNoteDescriptions", "noteDescriptions", "", "setNumberOfStars", "maxRating", "setPositiveButtonText", "positiveButtonText", "setStarColor", "setTitle", "title", "setTitleTextColor", "setWindowAnimation", "animationResId", "Data", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public static final class Builder implements Serializable {

        @NotNull
        private final Data data = new Data();

        /* compiled from: AppRatingDialog.kt */
        @Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001c\u0010$\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014R\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001c\u0010*\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014R\u001a\u0010-\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001a\u00100\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR.\u00103\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u000104j\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001`5X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0006\"\u0004\b<\u0010\bR\u001c\u0010=\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0012\"\u0004\b?\u0010\u0014R\u001a\u0010@\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0006\"\u0004\bB\u0010\bR\u001a\u0010C\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0006\"\u0004\bE\u0010\bR\u001c\u0010F\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0012\"\u0004\bH\u0010\u0014R\u001a\u0010I\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0006\"\u0004\bK\u0010\bR\u001a\u0010L\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0006\"\u0004\bN\u0010\bR\u001a\u0010O\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0006\"\u0004\bQ\u0010\b¨\u0006R"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "Ljava/io/Serializable;", "(Lcom/stepstone/apprating/AppRatingDialog$Builder;)V", "commentBackgroundColorResId", "", "getCommentBackgroundColorResId", "()I", "setCommentBackgroundColorResId", "(I)V", "commentTextColorResId", "getCommentTextColorResId", "setCommentTextColorResId", "defaultRating", "getDefaultRating", "setDefaultRating", "description", "", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "descriptionResId", "getDescriptionResId", "setDescriptionResId", "descriptionTextColorResId", "getDescriptionTextColorResId", "setDescriptionTextColorResId", "hint", "getHint", "setHint", "hintResId", "getHintResId", "setHintResId", "hintTextColorResId", "getHintTextColorResId", "setHintTextColorResId", "negativeButtonText", "getNegativeButtonText", "setNegativeButtonText", "negativeButtonTextResId", "getNegativeButtonTextResId", "setNegativeButtonTextResId", "neutralButtonText", "getNeutralButtonText", "setNeutralButtonText", "neutralButtonTextResId", "getNeutralButtonTextResId", "setNeutralButtonTextResId", "noteDescriptionTextColor", "getNoteDescriptionTextColor", "setNoteDescriptionTextColor", "noteDescriptions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getNoteDescriptions", "()Ljava/util/ArrayList;", "setNoteDescriptions", "(Ljava/util/ArrayList;)V", "numberOfStars", "getNumberOfStars", "setNumberOfStars", "positiveButtonText", "getPositiveButtonText", "setPositiveButtonText", "positiveButtonTextResId", "getPositiveButtonTextResId", "setPositiveButtonTextResId", "starColorResId", "getStarColorResId", "setStarColorResId", "title", "getTitle", "setTitle", "titleResId", "getTitleResId", "setTitleResId", "titleTextColorResId", "getTitleTextColorResId", "setTitleTextColorResId", "windowAnimationResId", "getWindowAnimationResId", "setWindowAnimationResId", "app-rating_release"}, k = 1, mv = {1, 1, 9})
        /* loaded from: classes.dex */
        public final class Data implements Serializable {
            private int commentBackgroundColorResId;
            private int commentTextColorResId;

            @Nullable
            private String description;
            private int descriptionResId;
            private int descriptionTextColorResId;

            @Nullable
            private String hint;
            private int hintResId;
            private int hintTextColorResId;

            @Nullable
            private String negativeButtonText;
            private int negativeButtonTextResId;

            @Nullable
            private String neutralButtonText;
            private int neutralButtonTextResId;
            private int noteDescriptionTextColor;

            @Nullable
            private ArrayList<String> noteDescriptions;

            @Nullable
            private String positiveButtonText;
            private int positiveButtonTextResId;
            private int starColorResId;

            @Nullable
            private String title;
            private int titleResId;
            private int titleTextColorResId;
            private int windowAnimationResId;
            private int numberOfStars = C.InitialValues.INSTANCE.getMAX_RATING();
            private int defaultRating = C.InitialValues.INSTANCE.getDEFAULT_RATING();

            public Data() {
            }

            public final int getCommentBackgroundColorResId() {
                return this.commentBackgroundColorResId;
            }

            public final int getCommentTextColorResId() {
                return this.commentTextColorResId;
            }

            public final int getDefaultRating() {
                return this.defaultRating;
            }

            @Nullable
            public final String getDescription() {
                return this.description;
            }

            public final int getDescriptionResId() {
                return this.descriptionResId;
            }

            public final int getDescriptionTextColorResId() {
                return this.descriptionTextColorResId;
            }

            @Nullable
            public final String getHint() {
                return this.hint;
            }

            public final int getHintResId() {
                return this.hintResId;
            }

            public final int getHintTextColorResId() {
                return this.hintTextColorResId;
            }

            @Nullable
            public final String getNegativeButtonText() {
                return this.negativeButtonText;
            }

            public final int getNegativeButtonTextResId() {
                return this.negativeButtonTextResId;
            }

            @Nullable
            public final String getNeutralButtonText() {
                return this.neutralButtonText;
            }

            public final int getNeutralButtonTextResId() {
                return this.neutralButtonTextResId;
            }

            public final int getNoteDescriptionTextColor() {
                return this.noteDescriptionTextColor;
            }

            @Nullable
            public final ArrayList<String> getNoteDescriptions() {
                return this.noteDescriptions;
            }

            public final int getNumberOfStars() {
                return this.numberOfStars;
            }

            @Nullable
            public final String getPositiveButtonText() {
                return this.positiveButtonText;
            }

            public final int getPositiveButtonTextResId() {
                return this.positiveButtonTextResId;
            }

            public final int getStarColorResId() {
                return this.starColorResId;
            }

            @Nullable
            public final String getTitle() {
                return this.title;
            }

            public final int getTitleResId() {
                return this.titleResId;
            }

            public final int getTitleTextColorResId() {
                return this.titleTextColorResId;
            }

            public final int getWindowAnimationResId() {
                return this.windowAnimationResId;
            }

            public final void setCommentBackgroundColorResId(int i) {
                this.commentBackgroundColorResId = i;
            }

            public final void setCommentTextColorResId(int i) {
                this.commentTextColorResId = i;
            }

            public final void setDefaultRating(int i) {
                this.defaultRating = i;
            }

            public final void setDescription(@Nullable String str) {
                this.description = str;
            }

            public final void setDescriptionResId(int i) {
                this.descriptionResId = i;
            }

            public final void setDescriptionTextColorResId(int i) {
                this.descriptionTextColorResId = i;
            }

            public final void setHint(@Nullable String str) {
                this.hint = str;
            }

            public final void setHintResId(int i) {
                this.hintResId = i;
            }

            public final void setHintTextColorResId(int i) {
                this.hintTextColorResId = i;
            }

            public final void setNegativeButtonText(@Nullable String str) {
                this.negativeButtonText = str;
            }

            public final void setNegativeButtonTextResId(int i) {
                this.negativeButtonTextResId = i;
            }

            public final void setNeutralButtonText(@Nullable String str) {
                this.neutralButtonText = str;
            }

            public final void setNeutralButtonTextResId(int i) {
                this.neutralButtonTextResId = i;
            }

            public final void setNoteDescriptionTextColor(int i) {
                this.noteDescriptionTextColor = i;
            }

            public final void setNoteDescriptions(@Nullable ArrayList<String> arrayList) {
                this.noteDescriptions = arrayList;
            }

            public final void setNumberOfStars(int i) {
                this.numberOfStars = i;
            }

            public final void setPositiveButtonText(@Nullable String str) {
                this.positiveButtonText = str;
            }

            public final void setPositiveButtonTextResId(int i) {
                this.positiveButtonTextResId = i;
            }

            public final void setStarColorResId(int i) {
                this.starColorResId = i;
            }

            public final void setTitle(@Nullable String str) {
                this.title = str;
            }

            public final void setTitleResId(int i) {
                this.titleResId = i;
            }

            public final void setTitleTextColorResId(int i) {
                this.titleTextColorResId = i;
            }

            public final void setWindowAnimationResId(int i) {
                this.windowAnimationResId = i;
            }
        }

        @NotNull
        public final AppRatingDialog create(@NotNull FragmentActivity activity) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            Preconditions.INSTANCE.checkNotNull(activity, "FragmentActivity cannot be null", new Object[0]);
            return new AppRatingDialog(activity, this.data, null);
        }

        @NotNull
        public final Data getData() {
            return this.data;
        }

        @NotNull
        public final Builder setCommentBackgroundColor(@ColorRes int colorResId) {
            this.data.setCommentBackgroundColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setCommentTextColor(@ColorRes int colorResId) {
            this.data.setCommentTextColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setDefaultRating(int defaultRating) {
            Preconditions.INSTANCE.checkArgument(defaultRating >= 0 && defaultRating <= this.data.getNumberOfStars(), "default rating value should be between 0 and " + this.data.getNumberOfStars(), new Object[0]);
            this.data.setDefaultRating(defaultRating);
            return this;
        }

        @NotNull
        public final Builder setDescription(@StringRes int resId) {
            this.data.setDescriptionResId(resId);
            this.data.setDescription((String) null);
            return this;
        }

        @NotNull
        public final Builder setDescription(@NotNull String content) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(content), "description cannot be empty", new Object[0]);
            this.data.setDescription(content);
            this.data.setDescriptionResId(0);
            return this;
        }

        @NotNull
        public final Builder setDescriptionTextColor(@ColorRes int colorResId) {
            this.data.setDescriptionTextColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setHint(@StringRes int resId) {
            this.data.setHintResId(resId);
            this.data.setHint((String) null);
            return this;
        }

        @NotNull
        public final Builder setHint(@NotNull String hint) {
            Intrinsics.checkParameterIsNotNull(hint, "hint");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(hint), "hint cannot be empty", new Object[0]);
            this.data.setHint(hint);
            this.data.setHintResId(0);
            return this;
        }

        @NotNull
        public final Builder setHintTextColor(@ColorRes int colorResId) {
            this.data.setHintTextColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setNegativeButtonText(@StringRes int resId) {
            this.data.setNegativeButtonTextResId(resId);
            this.data.setNegativeButtonText((String) null);
            return this;
        }

        @NotNull
        public final Builder setNegativeButtonText(@NotNull String negativeButtonText) {
            Intrinsics.checkParameterIsNotNull(negativeButtonText, "negativeButtonText");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(negativeButtonText), "text cannot be empty", new Object[0]);
            this.data.setNegativeButtonText(negativeButtonText);
            this.data.setNegativeButtonTextResId(0);
            return this;
        }

        @NotNull
        public final Builder setNeutralButtonText(@StringRes int resId) {
            this.data.setNeutralButtonTextResId(resId);
            this.data.setNeutralButtonText((String) null);
            return this;
        }

        @NotNull
        public final Builder setNeutralButtonText(@NotNull String neutralButtonText) {
            Intrinsics.checkParameterIsNotNull(neutralButtonText, "neutralButtonText");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(neutralButtonText), "text cannot be empty", new Object[0]);
            this.data.setNeutralButtonText(neutralButtonText);
            this.data.setNeutralButtonTextResId(0);
            return this;
        }

        @NotNull
        public final Builder setNoteDescriptionTextColor(@ColorRes int colorResId) {
            this.data.setNoteDescriptionTextColor(colorResId);
            return this;
        }

        @NotNull
        public final Builder setNoteDescriptions(@NotNull List<String> noteDescriptions) {
            Intrinsics.checkParameterIsNotNull(noteDescriptions, "noteDescriptions");
            Preconditions.INSTANCE.checkNotNull(noteDescriptions, "list cannot be null", new Object[0]);
            Preconditions.INSTANCE.checkArgument(!noteDescriptions.isEmpty(), "list cannot be empty", new Object[0]);
            Preconditions.INSTANCE.checkArgument(noteDescriptions.size() <= C.InitialValues.INSTANCE.getMAX_RATING(), "size of the list can be maximally " + C.InitialValues.INSTANCE.getMAX_RATING(), new Object[0]);
            this.data.setNoteDescriptions(new ArrayList<>(noteDescriptions));
            return this;
        }

        @NotNull
        public final Builder setNumberOfStars(int maxRating) {
            Preconditions.INSTANCE.checkArgument(maxRating > 0 && maxRating <= C.InitialValues.INSTANCE.getMAX_RATING(), "max rating value should be between 1 and " + C.InitialValues.INSTANCE.getMAX_RATING(), new Object[0]);
            this.data.setNumberOfStars(maxRating);
            return this;
        }

        @NotNull
        public final Builder setPositiveButtonText(@StringRes int resId) {
            this.data.setPositiveButtonTextResId(resId);
            this.data.setPositiveButtonText((String) null);
            return this;
        }

        @NotNull
        public final Builder setPositiveButtonText(@NotNull String positiveButtonText) {
            Intrinsics.checkParameterIsNotNull(positiveButtonText, "positiveButtonText");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(positiveButtonText), "text cannot be empty", new Object[0]);
            this.data.setPositiveButtonText(positiveButtonText);
            this.data.setPositiveButtonTextResId(0);
            return this;
        }

        @NotNull
        public final Builder setStarColor(@ColorRes int colorResId) {
            this.data.setStarColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setTitle(@StringRes int resId) {
            this.data.setTitleResId(resId);
            this.data.setTitle((String) null);
            return this;
        }

        @NotNull
        public final Builder setTitle(@NotNull String title) {
            Intrinsics.checkParameterIsNotNull(title, "title");
            Preconditions.INSTANCE.checkArgument(!TextUtils.isEmpty(title), "title cannot be empty", new Object[0]);
            this.data.setTitle(title);
            this.data.setTitleResId(0);
            return this;
        }

        @NotNull
        public final Builder setTitleTextColor(@ColorRes int colorResId) {
            this.data.setTitleTextColorResId(colorResId);
            return this;
        }

        @NotNull
        public final Builder setWindowAnimation(@StyleRes int animationResId) {
            this.data.setWindowAnimationResId(animationResId);
            return this;
        }
    }

    private AppRatingDialog(FragmentActivity fragmentActivity, Builder.Data data) {
        this.activity = fragmentActivity;
        this.data = data;
    }

    public /* synthetic */ AppRatingDialog(@NotNull FragmentActivity fragmentActivity, @NotNull Builder.Data data, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, data);
    }

    public final void show() {
        AppRatingDialogFragment.INSTANCE.newInstance(this.data).show(this.activity.getSupportFragmentManager(), "");
    }
}
