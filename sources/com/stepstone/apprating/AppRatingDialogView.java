package com.stepstone.apprating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.TextViewCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stepstone.apprating.listener.OnRatingBarChangedListener;
import com.stepstone.apprating.ratingbar.CustomRatingBar;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: AppRatingDialogView.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0012\u0010 \u001a\u00020\u001e2\b\b\u0001\u0010!\u001a\u00020\u001eH\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001eH\u0016J\u000e\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001eJ\u000e\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0007J\u0010\u0010)\u001a\u00020#2\b\b\u0001\u0010*\u001a\u00020\u001eJ\u0010\u0010+\u001a\u00020#2\b\b\u0001\u0010*\u001a\u00020\u001eJ\u0010\u0010,\u001a\u00020#2\b\b\u0001\u0010*\u001a\u00020\u001eJ\u000e\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u0007J\u0010\u0010/\u001a\u00020#2\b\b\u0001\u0010*\u001a\u00020\u001eJ\u0010\u00100\u001a\u00020#2\b\b\u0001\u0010!\u001a\u00020\u001eJ\u0014\u00101\u001a\u00020#2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010J\u000e\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u001eJ\u0010\u00104\u001a\u00020#2\b\b\u0001\u0010!\u001a\u00020\u001eJ\u000e\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020\u0007J\u0010\u00107\u001a\u00020#2\b\b\u0001\u0010*\u001a\u00020\u001eJ\u0010\u00108\u001a\u00020#2\u0006\u0010\u0003\u001a\u00020\u0004H\u0003J\u0010\u00109\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001eH\u0002R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\u00060\u0018R\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/stepstone/apprating/AppRatingDialogView;", "Landroid/widget/LinearLayout;", "Lcom/stepstone/apprating/listener/OnRatingBarChangedListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ClientCookie.COMMENT_ATTR, "", "getComment", "()Ljava/lang/String;", "commentEditText", "Landroid/widget/EditText;", "descriptionText", "Landroid/widget/TextView;", "noteDescriptionText", "noteDescriptions", "", "rateNumber", "", "getRateNumber", "()F", "ratingBar", "Lcom/stepstone/apprating/ratingbar/CustomRatingBar;", "theme", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "getTheme", "()Landroid/content/res/Resources$Theme;", "titleText", "fetchAttributeValue", "", "attr", "getColorFromRes", "colorResId", "onRatingChanged", "", "rating", "setDefaultRating", "defaultRating", "setDescriptionText", "content", "setDescriptionTextColor", "color", "setEditBackgroundColor", "setEditTextColor", "setHint", "hint", "setHintColor", "setNoteDescriptionTextColor", "setNoteDescriptions", "setNumberOfStars", "numberOfStars", "setStarColor", "setTitleText", "title", "setTitleTextColor", "setup", "updateNoteDescriptionText", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class AppRatingDialogView extends LinearLayout implements OnRatingBarChangedListener {
    private EditText commentEditText;
    private TextView descriptionText;
    private TextView noteDescriptionText;
    private List<String> noteDescriptions;
    private CustomRatingBar ratingBar;
    private TextView titleText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppRatingDialogView(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        setup(context);
    }

    private final int fetchAttributeValue(int attr) {
        TypedValue typedValue = new TypedValue();
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    private final int getColorFromRes(@ColorRes int colorResId) {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return ResourcesCompat.getColor(context.getResources(), colorResId, getTheme());
    }

    private final Resources.Theme getTheme() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources.Theme theme = context.getTheme();
        Intrinsics.checkExpressionValueIsNotNull(theme, "context.theme");
        return theme;
    }

    @SuppressLint({"ResourceType"})
    private final void setup(Context context) {
        LayoutInflater.from(context).inflate(R.layout.component_app_rate_dialog, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.app_rate_dialog_rating_bar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.app_rate_dialog_rating_bar)");
        this.ratingBar = (CustomRatingBar) findViewById;
        View findViewById2 = findViewById(R.id.app_rate_dialog_comment_edit_text);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.app_ra…dialog_comment_edit_text)");
        this.commentEditText = (EditText) findViewById2;
        View findViewById3 = findViewById(R.id.app_rate_dialog_title_text);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "findViewById(R.id.app_rate_dialog_title_text)");
        this.titleText = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.app_rate_dialog_description_text);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "findViewById(R.id.app_ra…_dialog_description_text)");
        this.descriptionText = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.app_rate_dialog_note_description);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "findViewById(R.id.app_ra…_dialog_note_description)");
        this.noteDescriptionText = (TextView) findViewById5;
        CustomRatingBar customRatingBar = this.ratingBar;
        if (customRatingBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        customRatingBar.setIsIndicator(false);
        CustomRatingBar customRatingBar2 = this.ratingBar;
        if (customRatingBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        customRatingBar2.setOnRatingBarChangeListener(this);
        TextView textView = this.titleText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
        }
        TextViewCompat.setTextAppearance(textView, fetchAttributeValue(R.attr.appRatingDialogTitleStyle));
        TextView textView2 = this.descriptionText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionText");
        }
        TextViewCompat.setTextAppearance(textView2, fetchAttributeValue(R.attr.appRatingDialogDescriptionStyle));
        TextView textView3 = this.noteDescriptionText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noteDescriptionText");
        }
        TextViewCompat.setTextAppearance(textView3, fetchAttributeValue(R.attr.appRatingDialogNoteDescriptionStyle));
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        TextViewCompat.setTextAppearance(editText, fetchAttributeValue(R.attr.appRatingDialogCommentStyle));
    }

    private final void updateNoteDescriptionText(int rating) {
        if (this.noteDescriptions != null) {
            List<String> list = this.noteDescriptions;
            if (!(list != null ? list.isEmpty() : true)) {
                List<String> list2 = this.noteDescriptions;
                if (list2 == null) {
                    Intrinsics.throwNpe();
                }
                String str = list2.get(rating);
                TextView textView = this.noteDescriptionText;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("noteDescriptionText");
                }
                textView.setText(str);
                TextView textView2 = this.noteDescriptionText;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("noteDescriptionText");
                }
                textView2.setVisibility(0);
                return;
            }
        }
        TextView textView3 = this.noteDescriptionText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noteDescriptionText");
        }
        textView3.setVisibility(8);
    }

    @NotNull
    public final String getComment() {
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        return editText.getText().toString();
    }

    public final float getRateNumber() {
        CustomRatingBar customRatingBar = this.ratingBar;
        if (customRatingBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        return customRatingBar.getRating();
    }

    @Override // com.stepstone.apprating.listener.OnRatingBarChangedListener
    public void onRatingChanged(int rating) {
        updateNoteDescriptionText(rating - 1);
    }

    public final void setDefaultRating(int defaultRating) {
        CustomRatingBar customRatingBar = this.ratingBar;
        if (customRatingBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        CustomRatingBar.setRating$default(customRatingBar, defaultRating, false, 2, null);
    }

    public final void setDescriptionText(@NotNull String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        TextView textView = this.descriptionText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionText");
        }
        textView.setText(content);
        TextView textView2 = this.descriptionText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionText");
        }
        textView2.setVisibility(0);
    }

    public final void setDescriptionTextColor(@ColorRes int color) {
        TextView textView = this.descriptionText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("descriptionText");
        }
        textView.setTextColor(getColorFromRes(color));
    }

    public final void setEditBackgroundColor(@ColorRes int color) {
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        Drawable drawable = editText.getBackground();
        Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
        drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN));
    }

    public final void setEditTextColor(@ColorRes int color) {
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        editText.setTextColor(getColorFromRes(color));
    }

    public final void setHint(@NotNull String hint) {
        Intrinsics.checkParameterIsNotNull(hint, "hint");
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        editText.setHint(hint);
    }

    public final void setHintColor(@ColorRes int color) {
        EditText editText = this.commentEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("commentEditText");
        }
        editText.setHintTextColor(getColorFromRes(color));
    }

    public final void setNoteDescriptionTextColor(@ColorRes int colorResId) {
        TextView textView = this.noteDescriptionText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("noteDescriptionText");
        }
        textView.setTextColor(getColorFromRes(colorResId));
    }

    public final void setNoteDescriptions(@NotNull List<String> noteDescriptions) {
        Intrinsics.checkParameterIsNotNull(noteDescriptions, "noteDescriptions");
        setNumberOfStars(noteDescriptions.size());
        this.noteDescriptions = noteDescriptions;
    }

    public final void setNumberOfStars(int numberOfStars) {
        CustomRatingBar customRatingBar = this.ratingBar;
        if (customRatingBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        customRatingBar.setNumStars(numberOfStars);
    }

    public final void setStarColor(@ColorRes int colorResId) {
        CustomRatingBar customRatingBar = this.ratingBar;
        if (customRatingBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingBar");
        }
        customRatingBar.setStarColor(colorResId);
    }

    public final void setTitleText(@NotNull String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        TextView textView = this.titleText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
        }
        textView.setText(title);
        TextView textView2 = this.titleText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
        }
        textView2.setVisibility(0);
    }

    public final void setTitleTextColor(@ColorRes int color) {
        TextView textView = this.titleText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
        }
        textView.setTextColor(getColorFromRes(color));
    }
}
