package com.startapp;

import android.content.Context;
import android.util.AttributeSet;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g2 {
    public Context a;
    public String b;

    public g2(Context context, AttributeSet attributeSet) {
        this.a = context;
        this.b = a(attributeSet, "adTag");
    }

    public final String a(AttributeSet attributeSet, String str) {
        try {
            int attributeResourceValue = attributeSet.getAttributeResourceValue(null, str, -1);
            return attributeResourceValue != -1 ? this.a.getResources().getString(attributeResourceValue) : attributeSet.getAttributeValue(null, str);
        } catch (Exception unused) {
            return null;
        }
    }
}
