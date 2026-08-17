package com.stepstone.apprating.ratingbar;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.stepstone.apprating.R;
import com.stepstone.apprating.common.Preconditions;
import com.stepstone.apprating.listener.OnRatingBarChangedListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: CustomRatingBar.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001&B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u0013\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\tJ\u0010\u0010$\u001a\u00020\u001b2\b\b\u0001\u0010%\u001a\u00020\u000bR\u000e\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/stepstone/apprating/ratingbar/CustomRatingBar;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "container", "isIndicator", "", "numStars", "", "onRatingBarChangedListener", "Lcom/stepstone/apprating/listener/OnRatingBarChangedListener;", "<set-?>", "", "rating", "getRating", "()F", "setRating", "(F)V", "starColorResId", "starList", "Ljava/util/ArrayList;", "Lcom/stepstone/apprating/ratingbar/StarButton;", "addStar", "addStars", "", "numberOfAll", "numberOfChecked", "getStarColor", "getThemeAccentColor", "setIsIndicator", "setNumStars", "setOnRatingBarChangeListener", "withAnimation", "setStarColor", "colorResId", "OnStarClickedHandler", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class CustomRatingBar extends LinearLayout {
    private final LinearLayout container;
    private boolean isIndicator;
    private int numStars;
    private OnRatingBarChangedListener onRatingBarChangedListener;
    private float rating;
    private int starColorResId;
    private final ArrayList<StarButton> starList;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CustomRatingBar.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/stepstone/apprating/ratingbar/CustomRatingBar$OnStarClickedHandler;", "Landroid/view/View$OnClickListener;", "number", "", "(Lcom/stepstone/apprating/ratingbar/CustomRatingBar;I)V", "onClick", "", "v", "Landroid/view/View;", "app-rating_release"}, k = 1, mv = {1, 1, 9})
    /* loaded from: classes.dex */
    public final class OnStarClickedHandler implements View.OnClickListener {
        private final int number;

        public OnStarClickedHandler(int i) {
            this.number = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@NotNull View v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            CustomRatingBar.this.setRating(this.number, true);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomRatingBar(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
        this.starList = new ArrayList<>();
        LayoutInflater.from(context).inflate(R.layout.component_custom_rating_bar, this);
        View findViewById = findViewById(R.id.rating_bar_container);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.rating_bar_container)");
        this.container = (LinearLayout) findViewById;
    }

    private final StarButton addStar() {
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        StarButton starButton = new StarButton(context);
        starButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.starList.add(starButton);
        this.container.addView(starButton);
        return starButton;
    }

    private final void addStars(int numberOfAll, int numberOfChecked) {
        Preconditions.INSTANCE.checkArgument(numberOfChecked <= numberOfAll, "wrong argument", new Object[0]);
        this.starList.clear();
        this.container.removeAllViews();
        int i = 0;
        while (i < numberOfAll) {
            StarButton checkedWithoutAnimation = addStar().setCheckedWithoutAnimation(i < numberOfChecked);
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            i++;
            checkedWithoutAnimation.setColor(getStarColor(context)).setOnClickListener(new OnStarClickedHandler(i));
        }
    }

    private final int getStarColor(Context context) {
        return this.starColorResId != 0 ? ResourcesCompat.getColor(context.getResources(), this.starColorResId, context.getTheme()) : getThemeAccentColor(context);
    }

    private final int getThemeAccentColor(Context context) {
        int identifier = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue.data;
    }

    private final void setRating(float f) {
        this.rating = f;
    }

    public static /* bridge */ /* synthetic */ void setRating$default(CustomRatingBar customRatingBar, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        customRatingBar.setRating(i, z);
    }

    public final float getRating() {
        return this.rating;
    }

    public final void setIsIndicator(boolean isIndicator) {
        this.isIndicator = isIndicator;
    }

    public final void setNumStars(int numStars) {
        this.numStars = numStars;
        addStars(numStars, 0);
    }

    public final void setOnRatingBarChangeListener(@NotNull OnRatingBarChangedListener onRatingBarChangedListener) {
        Intrinsics.checkParameterIsNotNull(onRatingBarChangedListener, "onRatingBarChangedListener");
        this.onRatingBarChangedListener = onRatingBarChangedListener;
    }

    public final void setRating(int rating, boolean withAnimation) {
        this.rating = rating;
        if (rating <= this.starList.size()) {
            int size = this.starList.size();
            int i = 0;
            while (i < size) {
                if (withAnimation) {
                    this.starList.get(i).setChecked(i < rating);
                } else {
                    this.starList.get(i).setCheckedWithoutAnimation(i < rating);
                }
                i++;
            }
        }
        OnRatingBarChangedListener onRatingBarChangedListener = this.onRatingBarChangedListener;
        if (onRatingBarChangedListener == null) {
            Intrinsics.throwNpe();
        }
        onRatingBarChangedListener.onRatingChanged(rating);
    }

    public final void setStarColor(@ColorRes int colorResId) {
        this.starColorResId = colorResId;
    }
}
