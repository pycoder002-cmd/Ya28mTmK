package com.synnapps.carouselview;

import android.support.v4.view.ViewPager;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CarouselViewPagerTransformer implements ViewPager.PageTransformer {
    public static final int DEFAULT = -1;
    public static final int DEPTH = 2;
    public static final int FLOW = 0;
    private static final float MIN_ALPHA_SLIDE = 0.35f;
    private static final float MIN_ALPHA_ZOOM = 0.5f;
    private static final float MIN_SCALE_DEPTH = 0.75f;
    private static final float MIN_SCALE_ZOOM = 0.85f;
    private static final float SCALE_FACTOR_SLIDE = 0.85f;
    public static final int SLIDE_OVER = 1;
    public static final int ZOOM = 3;
    private final int mTransformType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Transformer {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CarouselViewPagerTransformer(int i) {
        this.mTransformType = i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000d. Please report as an issue. */
    @Override // android.support.v4.view.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        float abs;
        float f2 = 0.0f;
        float f3 = 1.0f;
        switch (this.mTransformType) {
            case 0:
                view.setRotationY(f * (-30.0f));
                return;
            case 1:
                if (f < 0.0f && f > -1.0f) {
                    abs = (Math.abs(Math.abs(f) - 1.0f) * 0.14999998f) + 0.85f;
                    f3 = Math.max(MIN_ALPHA_SLIDE, 1.0f - Math.abs(f));
                    float f4 = -view.getWidth();
                    float f5 = f * f4;
                    if (f5 > f4) {
                        f2 = f5;
                    }
                    view.setAlpha(f3);
                    view.setTranslationX(f2);
                    view.setScaleX(abs);
                    view.setScaleY(abs);
                    return;
                }
                abs = 1.0f;
                view.setAlpha(f3);
                view.setTranslationX(f2);
                view.setScaleX(abs);
                view.setScaleY(abs);
                return;
            case 2:
                if (f > 0.0f && f < 1.0f) {
                    f2 = view.getWidth() * (-f);
                    f3 = 1.0f - f;
                    abs = MIN_SCALE_DEPTH + (0.25f * (1.0f - Math.abs(f)));
                    view.setAlpha(f3);
                    view.setTranslationX(f2);
                    view.setScaleX(abs);
                    view.setScaleY(abs);
                    return;
                }
                abs = 1.0f;
                view.setAlpha(f3);
                view.setTranslationX(f2);
                view.setScaleX(abs);
                view.setScaleY(abs);
                return;
            case 3:
                if (f >= -1.0f && f <= 1.0f) {
                    abs = Math.max(0.85f, 1.0f - Math.abs(f));
                    float f6 = MIN_ALPHA_ZOOM + (((abs - 0.85f) / 0.14999998f) * MIN_ALPHA_ZOOM);
                    float f7 = 1.0f - abs;
                    float height = (view.getHeight() * f7) / 2.0f;
                    float width = (view.getWidth() * f7) / 2.0f;
                    f2 = f < 0.0f ? width - (height / 2.0f) : (-width) + (height / 2.0f);
                    f3 = f6;
                    view.setAlpha(f3);
                    view.setTranslationX(f2);
                    view.setScaleX(abs);
                    view.setScaleY(abs);
                    return;
                }
                abs = 1.0f;
                view.setAlpha(f3);
                view.setTranslationX(f2);
                view.setScaleX(abs);
                view.setScaleY(abs);
                return;
            default:
                return;
        }
    }
}
