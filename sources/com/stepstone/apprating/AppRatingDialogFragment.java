package com.stepstone.apprating;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.C;
import com.stepstone.apprating.listener.RatingDialogListener;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AppRatingDialogFragment.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 .2\u00020\u0001:\u0001.B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\"2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010#\u001a\u00020\u001b2\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010'\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020*H\u0002J\u0018\u0010,\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0016\u0010\u0014\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bR\u0016\u0010\u0016\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0016\u0010\u0018\u001a\u0004\u0018\u00010\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000b¨\u0006/"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialogFragment;", "Landroid/support/v4/app/DialogFragment;", "()V", "alertDialog", "Landroid/support/v7/app/AlertDialog;", "data", "Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "Lcom/stepstone/apprating/AppRatingDialog$Builder;", "description", "", "getDescription", "()Ljava/lang/String;", "dialogView", "Lcom/stepstone/apprating/AppRatingDialogView;", "hint", "getHint", "listener", "Lcom/stepstone/apprating/listener/RatingDialogListener;", "negativeButtonText", "getNegativeButtonText", "neutralButtonText", "getNeutralButtonText", "positiveButtonText", "getPositiveButtonText", "title", "getTitle", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "context", "Landroid/content/Context;", "onCreateDialog", "Landroid/app/Dialog;", "onSaveInstanceState", "outState", "setupAlertDialog", "setupColors", "setupHint", "setupNegativeButton", "builder", "Landroid/support/v7/app/AlertDialog$Builder;", "setupNeutralButton", "setupPositiveButton", "setupTitleAndContentMessages", "Companion", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class AppRatingDialogFragment extends DialogFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AlertDialog alertDialog;
    private AppRatingDialog.Builder.Data data;
    private AppRatingDialogView dialogView;
    private RatingDialogListener listener;

    /* compiled from: AppRatingDialogFragment.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006R\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/stepstone/apprating/AppRatingDialogFragment;", "data", "Lcom/stepstone/apprating/AppRatingDialog$Builder$Data;", "Lcom/stepstone/apprating/AppRatingDialog$Builder;", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AppRatingDialogFragment newInstance(@NotNull AppRatingDialog.Builder.Data data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            AppRatingDialogFragment appRatingDialogFragment = new AppRatingDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(C.ExtraKeys.INSTANCE.getDATA(), data);
            appRatingDialogFragment.setArguments(bundle);
            return appRatingDialogFragment;
        }
    }

    @NotNull
    public static final /* synthetic */ RatingDialogListener access$getListener$p(AppRatingDialogFragment appRatingDialogFragment) {
        RatingDialogListener ratingDialogListener = appRatingDialogFragment.listener;
        if (ratingDialogListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listener");
        }
        return ratingDialogListener;
    }

    private final String getDescription() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getDescription())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getDescription();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getDescriptionResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getDescriptionResId());
    }

    private final String getHint() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getHint())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getHint();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getHintResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getHintResId());
    }

    private final String getNegativeButtonText() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getNegativeButtonText())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getNegativeButtonText();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getNegativeButtonTextResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getNegativeButtonTextResId());
    }

    private final String getNeutralButtonText() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getNeutralButtonText())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getNeutralButtonText();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getNeutralButtonTextResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getNeutralButtonTextResId());
    }

    private final String getPositiveButtonText() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getPositiveButtonText())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getPositiveButtonText();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getPositiveButtonTextResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getPositiveButtonTextResId());
    }

    private final String getTitle() {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (!TextUtils.isEmpty(data.getTitle())) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            return data2.getTitle();
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getTitleResId() == 0) {
            return null;
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        return getString(data4.getTitleResId());
    }

    private final AlertDialog setupAlertDialog(Context context) {
        this.dialogView = new AppRatingDialogView(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Serializable serializable = getArguments().getSerializable(C.ExtraKeys.INSTANCE.getDATA());
        if (serializable == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.stepstone.apprating.AppRatingDialog.Builder.Data");
        }
        this.data = (AppRatingDialog.Builder.Data) serializable;
        AppRatingDialogView appRatingDialogView = this.dialogView;
        if (appRatingDialogView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        setupPositiveButton(appRatingDialogView, builder);
        setupNegativeButton(builder);
        setupNeutralButton(builder);
        AppRatingDialogView appRatingDialogView2 = this.dialogView;
        if (appRatingDialogView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        setupTitleAndContentMessages(appRatingDialogView2);
        AppRatingDialogView appRatingDialogView3 = this.dialogView;
        if (appRatingDialogView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        setupHint(appRatingDialogView3);
        AppRatingDialogView appRatingDialogView4 = this.dialogView;
        if (appRatingDialogView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        setupColors(appRatingDialogView4);
        AppRatingDialogView appRatingDialogView5 = this.dialogView;
        if (appRatingDialogView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        appRatingDialogView5.setNumberOfStars(data.getNumberOfStars());
        AppRatingDialog.Builder.Data data2 = this.data;
        if (data2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        ArrayList<String> noteDescriptions = data2.getNoteDescriptions();
        if (!(noteDescriptions != null ? noteDescriptions.isEmpty() : true)) {
            AppRatingDialogView appRatingDialogView6 = this.dialogView;
            if (appRatingDialogView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogView");
            }
            AppRatingDialog.Builder.Data data3 = this.data;
            if (data3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            ArrayList<String> noteDescriptions2 = data3.getNoteDescriptions();
            if (noteDescriptions2 == null) {
                Intrinsics.throwNpe();
            }
            appRatingDialogView6.setNoteDescriptions(noteDescriptions2);
        }
        AppRatingDialogView appRatingDialogView7 = this.dialogView;
        if (appRatingDialogView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        AppRatingDialog.Builder.Data data4 = this.data;
        if (data4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        appRatingDialogView7.setDefaultRating(data4.getDefaultRating());
        AppRatingDialogView appRatingDialogView8 = this.dialogView;
        if (appRatingDialogView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        builder.setView(appRatingDialogView8);
        AlertDialog create = builder.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "builder.create()");
        this.alertDialog = create;
        AppRatingDialog.Builder.Data data5 = this.data;
        if (data5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data5.getWindowAnimationResId() != 0) {
            AlertDialog alertDialog = this.alertDialog;
            if (alertDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
            }
            Window window = alertDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "alertDialog.window");
            WindowManager.LayoutParams attributes = window.getAttributes();
            AppRatingDialog.Builder.Data data6 = this.data;
            if (data6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            attributes.windowAnimations = data6.getWindowAnimationResId();
        }
        AlertDialog alertDialog2 = this.alertDialog;
        if (alertDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alertDialog");
        }
        return alertDialog2;
    }

    private final void setupColors(AppRatingDialogView dialogView) {
        AppRatingDialog.Builder.Data data = this.data;
        if (data == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data.getTitleTextColorResId() != 0) {
            AppRatingDialog.Builder.Data data2 = this.data;
            if (data2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setTitleTextColor(data2.getTitleTextColorResId());
        }
        AppRatingDialog.Builder.Data data3 = this.data;
        if (data3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data3.getDescriptionTextColorResId() != 0) {
            AppRatingDialog.Builder.Data data4 = this.data;
            if (data4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setDescriptionTextColor(data4.getDescriptionTextColorResId());
        }
        AppRatingDialog.Builder.Data data5 = this.data;
        if (data5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data5.getCommentTextColorResId() != 0) {
            AppRatingDialog.Builder.Data data6 = this.data;
            if (data6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setEditTextColor(data6.getCommentTextColorResId());
        }
        AppRatingDialog.Builder.Data data7 = this.data;
        if (data7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data7.getCommentBackgroundColorResId() != 0) {
            AppRatingDialog.Builder.Data data8 = this.data;
            if (data8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setEditBackgroundColor(data8.getCommentBackgroundColorResId());
        }
        AppRatingDialog.Builder.Data data9 = this.data;
        if (data9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data9.getHintTextColorResId() != 0) {
            AppRatingDialog.Builder.Data data10 = this.data;
            if (data10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setHintColor(data10.getHintTextColorResId());
        }
        AppRatingDialog.Builder.Data data11 = this.data;
        if (data11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data11.getStarColorResId() != 0) {
            AppRatingDialog.Builder.Data data12 = this.data;
            if (data12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setStarColor(data12.getStarColorResId());
        }
        AppRatingDialog.Builder.Data data13 = this.data;
        if (data13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("data");
        }
        if (data13.getNoteDescriptionTextColor() != 0) {
            AppRatingDialog.Builder.Data data14 = this.data;
            if (data14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("data");
            }
            dialogView.setNoteDescriptionTextColor(data14.getNoteDescriptionTextColor());
        }
    }

    private final void setupHint(AppRatingDialogView dialogView) {
        if (TextUtils.isEmpty(getHint())) {
            return;
        }
        String hint = getHint();
        if (hint == null) {
            Intrinsics.throwNpe();
        }
        dialogView.setHint(hint);
    }

    private final void setupNegativeButton(AlertDialog.Builder builder) {
        if (TextUtils.isEmpty(getNegativeButtonText())) {
            return;
        }
        builder.setNegativeButton(getNegativeButtonText(), new DialogInterface.OnClickListener() { // from class: com.stepstone.apprating.AppRatingDialogFragment$setupNegativeButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RatingDialogListener access$getListener$p = AppRatingDialogFragment.access$getListener$p(AppRatingDialogFragment.this);
                if (access$getListener$p != null) {
                    access$getListener$p.onNegativeButtonClicked();
                }
            }
        });
    }

    private final void setupNeutralButton(AlertDialog.Builder builder) {
        if (TextUtils.isEmpty(getNeutralButtonText())) {
            return;
        }
        builder.setNeutralButton(getNeutralButtonText(), new DialogInterface.OnClickListener() { // from class: com.stepstone.apprating.AppRatingDialogFragment$setupNeutralButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RatingDialogListener access$getListener$p = AppRatingDialogFragment.access$getListener$p(AppRatingDialogFragment.this);
                if (access$getListener$p != null) {
                    access$getListener$p.onNeutralButtonClicked();
                }
            }
        });
    }

    private final void setupPositiveButton(final AppRatingDialogView dialogView, AlertDialog.Builder builder) {
        if (TextUtils.isEmpty(getPositiveButtonText())) {
            return;
        }
        builder.setPositiveButton(getPositiveButtonText(), new DialogInterface.OnClickListener() { // from class: com.stepstone.apprating.AppRatingDialogFragment$setupPositiveButton$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                int rateNumber = (int) dialogView.getRateNumber();
                String comment = dialogView.getComment();
                RatingDialogListener access$getListener$p = AppRatingDialogFragment.access$getListener$p(AppRatingDialogFragment.this);
                if (access$getListener$p != null) {
                    access$getListener$p.onPositiveButtonClicked(rateNumber, comment);
                }
            }
        });
    }

    private final void setupTitleAndContentMessages(AppRatingDialogView dialogView) {
        if (!TextUtils.isEmpty(getTitle())) {
            String title = getTitle();
            if (title == null) {
                Intrinsics.throwNpe();
            }
            dialogView.setTitleText(title);
        }
        if (TextUtils.isEmpty(getDescription())) {
            return;
        }
        String description = getDescription();
        if (description == null) {
            Intrinsics.throwNpe();
        }
        dialogView.setDescriptionText(description);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Float valueOf = savedInstanceState != null ? Float.valueOf(savedInstanceState.getFloat(C.ExtraKeys.INSTANCE.getCURRENT_RATE_NUMBER())) : null;
        if (valueOf != null) {
            AppRatingDialogView appRatingDialogView = this.dialogView;
            if (appRatingDialogView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogView");
            }
            if (appRatingDialogView != null) {
                appRatingDialogView.setDefaultRating((int) valueOf.floatValue());
            }
        }
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
        if (!(getHost() instanceof RatingDialogListener)) {
            throw new AssertionError("Host does not implement RatingDialogListener!");
        }
        Object host = getHost();
        if (host == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.stepstone.apprating.listener.RatingDialogListener");
        }
        this.listener = (RatingDialogListener) host;
    }

    @Override // android.support.v4.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return setupAlertDialog(activity);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onSaveInstanceState(@Nullable Bundle outState) {
        AppRatingDialogView appRatingDialogView = this.dialogView;
        if (appRatingDialogView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogView");
        }
        if (appRatingDialogView != null && outState != null) {
            String current_rate_number = C.ExtraKeys.INSTANCE.getCURRENT_RATE_NUMBER();
            AppRatingDialogView appRatingDialogView2 = this.dialogView;
            if (appRatingDialogView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogView");
            }
            outState.putFloat(current_rate_number, appRatingDialogView2.getRateNumber());
        }
        super.onSaveInstanceState(outState);
    }
}
