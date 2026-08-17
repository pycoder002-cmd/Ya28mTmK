package com.daimajia.easing.expo;

import com.daimajia.easing.BaseEasingMethod;

/* loaded from: classes.dex */
public class ExpoEaseIn extends BaseEasingMethod {
    public ExpoEaseIn(float f) {
        super(f);
    }

    @Override // com.daimajia.easing.BaseEasingMethod
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f != 0.0f) {
            f2 += f3 * ((float) Math.pow(2.0d, 10.0f * ((f / f4) - 1.0f)));
        }
        return Float.valueOf(f2);
    }
}
