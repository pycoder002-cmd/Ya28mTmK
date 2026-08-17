package com.stepstone.apprating.ratingbar;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.stepstone.apprating.C;
import com.stepstone.apprating.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: StarButton.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/stepstone/apprating/ratingbar/StarButton;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "emptyStarImage", "Landroid/widget/ImageView;", "fullStarImage", "initialize", "", "setChecked", "checked", "", "setCheckedWithoutAnimation", "setColor", "color", "", "app-rating_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class StarButton extends FrameLayout {
    private ImageView emptyStarImage;
    private ImageView fullStarImage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StarButton(@NotNull Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        initialize();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StarButton(@NotNull Context context, @NotNull AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    private final void initialize() {
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        ((LayoutInflater) systemService).inflate(R.layout.star_button_layout, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.empty_star_image_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.empty_star_image_view)");
        this.emptyStarImage = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.full_star_image_view);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "findViewById(R.id.full_star_image_view)");
        this.fullStarImage = (ImageView) findViewById2;
    }

    @NotNull
    public final StarButton setChecked(boolean checked) {
        ImageView imageView = this.fullStarImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullStarImage");
        }
        imageView.animate().alpha(checked ? C.Animation.INSTANCE.getVISIBLE() : C.Animation.INSTANCE.getINVISIBLE()).setDuration(C.Animation.INSTANCE.getCHECK_STAR_DURATION()).start();
        return this;
    }

    @NotNull
    public final StarButton setCheckedWithoutAnimation(boolean checked) {
        ImageView imageView = this.fullStarImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullStarImage");
        }
        imageView.setAlpha(checked ? C.Animation.INSTANCE.getVISIBLE() : C.Animation.INSTANCE.getINVISIBLE());
        return this;
    }

    @NotNull
    public final StarButton setColor(@ColorInt int color) {
        ImageView imageView = this.emptyStarImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyStarImage");
        }
        imageView.setColorFilter(color);
        ImageView imageView2 = this.fullStarImage;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullStarImage");
        }
        imageView2.setColorFilter(color);
        return this;
    }
}
