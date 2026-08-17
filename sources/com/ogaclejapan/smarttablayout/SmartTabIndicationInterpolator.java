package com.ogaclejapan.smarttablayout;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
public abstract class SmartTabIndicationInterpolator {
    static final int ID_LINEAR = 1;
    static final int ID_SMART = 0;
    public static final SmartTabIndicationInterpolator SMART = new SmartIndicationInterpolator();
    public static final SmartTabIndicationInterpolator LINEAR = new LinearIndicationInterpolator();

    /* loaded from: classes.dex */
    public static class LinearIndicationInterpolator extends SmartTabIndicationInterpolator {
        @Override // com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator
        public float getLeftEdge(float f) {
            return f;
        }

        @Override // com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator
        public float getRightEdge(float f) {
            return f;
        }
    }

    /* loaded from: classes.dex */
    public static class SmartIndicationInterpolator extends SmartTabIndicationInterpolator {
        private static final float DEFAULT_INDICATOR_INTERPOLATION_FACTOR = 3.0f;
        private final Interpolator leftEdgeInterpolator;
        private final Interpolator rightEdgeInterpolator;

        public SmartIndicationInterpolator() {
            this(DEFAULT_INDICATOR_INTERPOLATION_FACTOR);
        }

        public SmartIndicationInterpolator(float f) {
            this.leftEdgeInterpolator = new AccelerateInterpolator(f);
            this.rightEdgeInterpolator = new DecelerateInterpolator(f);
        }

        @Override // com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator
        public float getLeftEdge(float f) {
            return this.leftEdgeInterpolator.getInterpolation(f);
        }

        @Override // com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator
        public float getRightEdge(float f) {
            return this.rightEdgeInterpolator.getInterpolation(f);
        }

        @Override // com.ogaclejapan.smarttablayout.SmartTabIndicationInterpolator
        public float getThickness(float f) {
            return 1.0f / ((1.0f - getLeftEdge(f)) + getRightEdge(f));
        }
    }

    public static SmartTabIndicationInterpolator of(int i) {
        switch (i) {
            case 0:
                return SMART;
            case 1:
                return LINEAR;
            default:
                throw new IllegalArgumentException("Unknown id: " + i);
        }
    }

    public abstract float getLeftEdge(float f);

    public abstract float getRightEdge(float f);

    public float getThickness(float f) {
        return 1.0f;
    }
}
