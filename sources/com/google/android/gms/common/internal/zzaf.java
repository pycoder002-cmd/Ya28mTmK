package com.google.android.gms.common.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/* loaded from: classes.dex */
public final class zzaf extends Button {
    public zzaf(Context context) {
        this(context, null);
    }

    public zzaf(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
    }

    private void zza(Resources resources) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        int i = (int) ((resources.getDisplayMetrics().density * 48.0f) + 0.5f);
        setMinHeight(i);
        setMinWidth(i);
    }

    private void zzb(Resources resources, int i, int i2) {
        setBackgroundDrawable(resources.getDrawable(zze(i, zzg(i2, com.google.android.gms.R.drawable.common_google_signin_btn_icon_dark, com.google.android.gms.R.drawable.common_google_signin_btn_icon_light, com.google.android.gms.R.drawable.common_google_signin_btn_icon_light), zzg(i2, com.google.android.gms.R.drawable.common_google_signin_btn_text_dark, com.google.android.gms.R.drawable.common_google_signin_btn_text_light, com.google.android.gms.R.drawable.common_google_signin_btn_text_light))));
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0018. Please report as an issue. */
    private void zzc(Resources resources, int i, int i2) {
        int i3;
        setTextColor((ColorStateList) zzaa.zzy(resources.getColorStateList(zzg(i2, com.google.android.gms.R.color.common_google_signin_btn_text_dark, com.google.android.gms.R.color.common_google_signin_btn_text_light, com.google.android.gms.R.color.common_google_signin_btn_text_light))));
        switch (i) {
            case 0:
                i3 = com.google.android.gms.R.string.common_signin_button_text;
                setText(resources.getString(i3));
                setTransformationMethod(null);
                return;
            case 1:
                i3 = com.google.android.gms.R.string.common_signin_button_text_long;
                setText(resources.getString(i3));
                setTransformationMethod(null);
                return;
            case 2:
                setText((CharSequence) null);
                setTransformationMethod(null);
                return;
            default:
                StringBuilder sb = new StringBuilder(32);
                sb.append("Unknown button size: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    private int zze(int i, int i2, int i3) {
        switch (i) {
            case 0:
            case 1:
                return i3;
            case 2:
                return i2;
            default:
                StringBuilder sb = new StringBuilder(32);
                sb.append("Unknown button size: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    private int zzg(int i, int i2, int i3, int i4) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i3;
            case 2:
                return i4;
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unknown color scheme: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    public void zza(Resources resources, int i, int i2) {
        zza(resources);
        zzb(resources, i, i2);
        zzc(resources, i, i2);
    }
}
