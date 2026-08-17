package com.startapp;

import android.app.Activity;
import java.net.URLDecoder;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class a8 implements b8 {
    private static final String LOG_TAG = "a8";
    public a openListener;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        boolean onClickEvent(String str);
    }

    public a8(a aVar) {
        this.openListener = aVar;
    }

    public void applyOrientationProperties(Activity activity, e8 e8Var) {
        try {
            int i = 0;
            int i2 = activity.getResources().getConfiguration().orientation == 1 ? 1 : 0;
            int i3 = e8Var.c;
            if (i3 == 0) {
                i = 1;
            } else if (i3 != 1) {
                i = e8Var.b ? -1 : i2;
            }
            int i4 = ya.a;
            try {
                activity.setRequestedOrientation(i);
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            p7.a(activity, th);
        }
    }

    @Override // com.startapp.b8
    public abstract void close();

    @Override // com.startapp.b8
    public void createCalendarEvent(String str) {
        isFeatureSupported("calendar");
    }

    @Override // com.startapp.b8
    public void expand(String str) {
    }

    public abstract boolean isFeatureSupported(String str);

    @Override // com.startapp.b8
    public boolean open(String str) {
        try {
            String trim = URLDecoder.decode(str, "UTF-8").trim();
            return trim.startsWith("sms") ? openSMS(trim) : trim.startsWith("tel") ? openTel(trim) : this.openListener.onClickEvent(trim);
        } catch (Exception unused) {
            return this.openListener.onClickEvent(str);
        }
    }

    public boolean openSMS(String str) {
        isFeatureSupported("sms");
        return true;
    }

    public boolean openTel(String str) {
        isFeatureSupported("tel");
        return true;
    }

    @Override // com.startapp.b8
    public void playVideo(String str) {
        isFeatureSupported("inlineVideo");
    }

    @Override // com.startapp.b8
    public void resize() {
    }

    @Override // com.startapp.b8
    public void setExpandProperties(Map<String, String> map) {
    }

    @Override // com.startapp.b8
    public abstract void setOrientationProperties(Map<String, String> map);

    @Override // com.startapp.b8
    public void setResizeProperties(Map<String, String> map) {
    }

    @Override // com.startapp.b8
    public void storePicture(String str) {
        isFeatureSupported("storePicture");
    }

    @Override // com.startapp.b8
    public abstract void useCustomClose(String str);
}
