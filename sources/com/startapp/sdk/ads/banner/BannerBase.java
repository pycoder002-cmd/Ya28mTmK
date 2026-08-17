package com.startapp.sdk.ads.banner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.d;
import com.startapp.da;
import com.startapp.g2;
import com.startapp.i6;
import com.startapp.j6;
import com.startapp.r5;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.adrules.AdaptMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.ya;
import java.util.Random;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class BannerBase extends RelativeLayout {
    public boolean a;
    public AdPreferences b;
    public AdRulesResult c;
    public int d;
    public boolean e;
    public Point f;
    public boolean g;
    public int h;
    public int i;
    public String j;
    public da k;
    public boolean l;
    public boolean m;
    public String n;
    public final Runnable o;
    public final Handler p;
    public final Object q;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AdRulesResult adRulesResult;
            if (BannerBase.this.isShown() || !((adRulesResult = BannerBase.this.c) == null || adRulesResult.b())) {
                BannerBase.this.m();
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Handler.Callback {
        public b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2) {
                BannerBase bannerBase = BannerBase.this;
                bannerBase.p();
                bannerBase.m();
            }
            return true;
        }
    }

    public BannerBase(Context context) {
        super(context);
        this.a = false;
        this.d = 0;
        this.e = true;
        this.g = false;
        int nextInt = new Random().nextInt(100000) + 159868227;
        this.h = nextInt;
        this.i = nextInt + 1;
        this.j = null;
        this.l = false;
        this.m = false;
        this.o = new a();
        this.p = new Handler(Looper.getMainLooper(), new b());
        this.q = new Object();
        try {
            ComponentLocator.a(context).q().a(512);
        } catch (Throwable unused) {
        }
    }

    public BannerBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.d = 0;
        this.e = true;
        this.g = false;
        int nextInt = new Random().nextInt(100000) + 159868227;
        this.h = nextInt;
        this.i = nextInt + 1;
        this.j = null;
        this.l = false;
        this.m = false;
        this.o = new a();
        this.p = new Handler(Looper.getMainLooper(), new b());
        this.q = new Object();
        a(context, attributeSet);
    }

    public void a() {
        if (isFirstLoad() || AdaptMetaData.a.a().a()) {
            setFirstLoad(false);
            j6.a.a(new i6(AdPreferences.Placement.INAPP_BANNER, this.j));
        }
    }

    public abstract void a(int i);

    public final void a(Context context, AttributeSet attributeSet) {
        setAdTag(new g2(context, attributeSet).b);
    }

    public void a(r5 r5Var) {
        if (this.k != null) {
            return;
        }
        da daVar = new da(i(), r5Var, g());
        this.k = daVar;
        if (daVar.b()) {
            daVar.run();
        }
    }

    public void a(AdPreferences adPreferences) {
        boolean z = this.a;
        int i = ya.a;
        adPreferences.setHardwareAccelerated((Build.VERSION.SDK_INT >= 11 && 1 != getLayerType() && z) ? isHardwareAccelerated() : false);
    }

    public void b() {
        if (isInEditMode()) {
            return;
        }
        removeCallbacks(this.o);
        synchronized (this.q) {
            this.p.removeMessages(2);
        }
    }

    public int c() {
        return h();
    }

    public abstract int d();

    public abstract String e();

    public abstract int f();

    public int g() {
        return BannerMetaData.b.a().h();
    }

    public abstract String getBidToken();

    public String getErrorMessage() {
        return this.n;
    }

    public abstract int h();

    public abstract void hideBanner();

    public View i() {
        return this;
    }

    public boolean isClicked() {
        return this.l;
    }

    public boolean isFirstLoad() {
        return this.e;
    }

    public abstract int j();

    public void k() {
        if (!isInEditMode()) {
            l();
            return;
        }
        setMinimumWidth(d.a(getContext(), j()));
        setMinimumHeight(d.a(getContext(), f()));
        setBackgroundColor(Color.rgb(Opcodes.RET, Opcodes.RET, Opcodes.RET));
        TextView textView = new TextView(getContext());
        textView.setText(e());
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(textView, layoutParams);
    }

    public abstract void l();

    public void loadAd() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        loadAd(d.b(getContext(), displayMetrics.widthPixels), d.b(getContext(), displayMetrics.heightPixels));
    }

    public void loadAd(int i, int i2) {
        if (getParent() != null) {
            return;
        }
        try {
            ComponentLocator.a(getContext()).q().a(1024);
        } catch (Throwable unused) {
        }
        this.f = new Point(i, i2);
        n();
    }

    public void m() {
        da daVar = this.k;
        if (daVar != null) {
            daVar.a();
            this.k = null;
        }
        if (this.c != null && !AdaptMetaData.a.a().a()) {
            if (this.c.b()) {
                o();
            }
        } else {
            AdRulesResult a2 = AdaptMetaData.a.a().a(AdPreferences.Placement.INAPP_BANNER, this.j);
            this.c = a2;
            if (a2.b()) {
                o();
            } else {
                hideBanner();
            }
        }
    }

    public void n() {
        synchronized (this.q) {
            if (!this.p.hasMessages(1)) {
                this.p.sendEmptyMessage(1);
            }
        }
    }

    public abstract void o();

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            ComponentLocator.a(getContext()).q().a(4096);
        } catch (Throwable unused) {
        }
        this.a = true;
        p();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a = false;
        b();
        da daVar = this.k;
        if (daVar != null) {
            daVar.a();
            this.k = null;
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        a(bundle.getInt("bannerId"));
        this.c = (AdRulesResult) bundle.getSerializable("adRulesResult");
        this.b = (AdPreferences) bundle.getSerializable("adPreferences");
        this.d = bundle.getInt("offset");
        this.e = bundle.getBoolean("firstLoad");
        this.m = bundle.getBoolean("shouldReloadBanner");
        super.onRestoreInstanceState(bundle.getParcelable("upperState"));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        if (isClicked()) {
            setClicked(false);
            this.m = true;
        }
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("bannerId", d());
        bundle.putParcelable("upperState", onSaveInstanceState);
        bundle.putSerializable("adRulesResult", this.c);
        bundle.putSerializable("adPreferences", this.b);
        bundle.putInt("offset", this.d);
        bundle.putBoolean("firstLoad", this.e);
        bundle.putBoolean("shouldReloadBanner", this.m);
        return bundle;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            this.a = false;
            b();
            return;
        }
        if (this.m) {
            this.m = false;
            m();
        }
        this.a = true;
        p();
    }

    public void p() {
        if (!this.a || isInEditMode()) {
            return;
        }
        removeCallbacks(this.o);
        postDelayed(this.o, c());
        long v = MetaData.h.v() * 1000;
        synchronized (this.q) {
            this.p.removeMessages(2);
            this.p.sendEmptyMessageDelayed(2, v);
        }
    }

    public abstract void setAdTag(String str);

    public void setClicked(boolean z) {
        this.l = z;
    }

    public void setErrorMessage(String str) {
        this.n = str;
    }

    public void setFirstLoad(boolean z) {
        this.e = z;
    }
}
