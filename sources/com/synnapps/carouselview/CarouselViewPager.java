package com.synnapps.carouselview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class CarouselViewPager extends ViewPager {
    private ImageClickListener imageClickListener;
    private CarouselViewPagerScroller mScroller;
    private float newX;
    private float oldX;
    private float sens;

    public CarouselViewPager(Context context) {
        super(context);
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.sens = 5.0f;
        this.mScroller = null;
        postInitViewPager();
    }

    public CarouselViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.oldX = 0.0f;
        this.newX = 0.0f;
        this.sens = 5.0f;
        this.mScroller = null;
        postInitViewPager();
    }

    private void postInitViewPager() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            this.mScroller = new CarouselViewPagerScroller(getContext(), (Interpolator) declaredField2.get(null));
            declaredField.set(this, this.mScroller);
        } catch (Exception unused) {
        }
    }

    @Override // android.support.v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.oldX = motionEvent.getX();
                break;
            case 1:
                this.newX = motionEvent.getX();
                if (Math.abs(this.oldX - this.newX) >= this.sens) {
                    this.oldX = 0.0f;
                    this.newX = 0.0f;
                    break;
                } else {
                    if (this.imageClickListener == null) {
                        return true;
                    }
                    this.imageClickListener.onClick(getCurrentItem());
                    return true;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setImageClickListener(ImageClickListener imageClickListener) {
        this.imageClickListener = imageClickListener;
    }

    public void setTransitionVelocity(int i) {
        this.mScroller.setmScrollDuration(i);
    }
}
