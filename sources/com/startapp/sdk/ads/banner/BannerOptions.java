package com.startapp.sdk.ads.banner;

import android.app.Activity;
import com.blankj.utilcode.constant.TimeConstants;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BannerOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private int adsNumber;
    private int delayFaceTime;

    @f(type = Effect.class)
    private Effect effect;
    private int height;
    private float heightRatio;
    private int htmlAdsNumber;
    private float minScale;
    private int minViewabilityPercentage;
    private int refreshRate;
    private int refreshRate3D;
    private boolean rotateThroughOnStart;
    private int rotateThroughSpeedMult;
    private int scalePower;
    private int stepSize;
    private int timeBetweenFrames;
    private int width;
    private float widthRatio;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Effect {
        ROTATE_3D(1),
        EXCHANGE(2),
        FLY_IN(3);

        private int index;

        Effect(int i) {
            this.index = i;
        }

        public static Effect getByIndex(int i) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i2 = 0; i2 < 3; i2++) {
                if (values[i2].getIndex() == i) {
                    effect = values[i2];
                }
            }
            return effect;
        }

        public static Effect getByName(String str) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    effect = values[i];
                }
            }
            return effect;
        }

        public int getIndex() {
            return this.index;
        }

        public int getRotationMultiplier() {
            return (int) Math.pow(2.0d, this.index - 1);
        }
    }

    public BannerOptions() {
        this.width = 300;
        this.height = 50;
        this.minViewabilityPercentage = 50;
        this.timeBetweenFrames = 25;
        this.stepSize = 5;
        this.delayFaceTime = 5000;
        this.adsNumber = 4;
        this.htmlAdsNumber = 10;
        this.refreshRate3D = TimeConstants.MIN;
        this.widthRatio = 1.0f;
        this.heightRatio = 1.0f;
        this.minScale = 0.88f;
        this.scalePower = 4;
        this.effect = Effect.ROTATE_3D;
        this.rotateThroughOnStart = true;
        this.rotateThroughSpeedMult = 2;
        this.refreshRate = TimeConstants.MIN;
    }

    public BannerOptions(BannerOptions bannerOptions) {
        this.width = 300;
        this.height = 50;
        this.minViewabilityPercentage = 50;
        this.timeBetweenFrames = 25;
        this.stepSize = 5;
        this.delayFaceTime = 5000;
        this.adsNumber = 4;
        this.htmlAdsNumber = 10;
        this.refreshRate3D = TimeConstants.MIN;
        this.widthRatio = 1.0f;
        this.heightRatio = 1.0f;
        this.minScale = 0.88f;
        this.scalePower = 4;
        this.effect = Effect.ROTATE_3D;
        this.rotateThroughOnStart = true;
        this.rotateThroughSpeedMult = 2;
        this.refreshRate = TimeConstants.MIN;
        this.width = bannerOptions.width;
        this.height = bannerOptions.height;
        this.timeBetweenFrames = bannerOptions.timeBetweenFrames;
        this.stepSize = bannerOptions.stepSize;
        this.delayFaceTime = bannerOptions.delayFaceTime;
        this.adsNumber = bannerOptions.adsNumber;
        this.htmlAdsNumber = bannerOptions.htmlAdsNumber;
        this.refreshRate3D = bannerOptions.refreshRate3D;
        this.widthRatio = bannerOptions.widthRatio;
        this.heightRatio = bannerOptions.heightRatio;
        this.minScale = bannerOptions.minScale;
        this.scalePower = bannerOptions.scalePower;
        this.effect = bannerOptions.effect;
        this.rotateThroughOnStart = bannerOptions.rotateThroughOnStart;
        this.rotateThroughSpeedMult = bannerOptions.rotateThroughSpeedMult;
        this.refreshRate = bannerOptions.refreshRate;
    }

    public int a() {
        return this.adsNumber;
    }

    public void a(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int b() {
        return this.delayFaceTime;
    }

    public Effect c() {
        return this.effect;
    }

    public int d() {
        return this.height;
    }

    public float e() {
        return this.heightRatio;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BannerOptions.class != obj.getClass()) {
            return false;
        }
        BannerOptions bannerOptions = (BannerOptions) obj;
        return this.width == bannerOptions.width && this.height == bannerOptions.height && this.minViewabilityPercentage == bannerOptions.minViewabilityPercentage && this.timeBetweenFrames == bannerOptions.timeBetweenFrames && this.stepSize == bannerOptions.stepSize && this.delayFaceTime == bannerOptions.delayFaceTime && this.adsNumber == bannerOptions.adsNumber && this.htmlAdsNumber == bannerOptions.htmlAdsNumber && this.refreshRate3D == bannerOptions.refreshRate3D && Float.compare(bannerOptions.widthRatio, this.widthRatio) == 0 && Float.compare(bannerOptions.heightRatio, this.heightRatio) == 0 && Float.compare(bannerOptions.minScale, this.minScale) == 0 && this.scalePower == bannerOptions.scalePower && this.rotateThroughOnStart == bannerOptions.rotateThroughOnStart && this.rotateThroughSpeedMult == bannerOptions.rotateThroughSpeedMult && this.refreshRate == bannerOptions.refreshRate && this.effect == bannerOptions.effect;
    }

    public int f() {
        return this.htmlAdsNumber;
    }

    public float g() {
        return this.minScale;
    }

    public int h() {
        return this.minViewabilityPercentage;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(this.minViewabilityPercentage), Integer.valueOf(this.timeBetweenFrames), Integer.valueOf(this.stepSize), Integer.valueOf(this.delayFaceTime), Integer.valueOf(this.adsNumber), Integer.valueOf(this.htmlAdsNumber), Integer.valueOf(this.refreshRate3D), Float.valueOf(this.widthRatio), Float.valueOf(this.heightRatio), Float.valueOf(this.minScale), Integer.valueOf(this.scalePower), this.effect, Boolean.valueOf(this.rotateThroughOnStart), Integer.valueOf(this.rotateThroughSpeedMult), Integer.valueOf(this.refreshRate)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public int i() {
        return this.refreshRate;
    }

    public int j() {
        return this.refreshRate3D;
    }

    public int k() {
        return this.rotateThroughSpeedMult;
    }

    public int l() {
        return this.scalePower;
    }

    public int m() {
        return this.stepSize;
    }

    public int n() {
        return this.timeBetweenFrames;
    }

    public int o() {
        return this.width;
    }

    public float p() {
        return this.widthRatio;
    }

    public boolean q() {
        return this.rotateThroughOnStart;
    }
}
