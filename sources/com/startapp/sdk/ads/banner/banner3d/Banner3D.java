package com.startapp.sdk.ads.banner.banner3d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import com.startapp.d;
import com.startapp.g5;
import com.startapp.h2;
import com.startapp.k2;
import com.startapp.l2;
import com.startapp.m2;
import com.startapp.p7;
import com.startapp.r5;
import com.startapp.sdk.ads.banner.BannerBase;
import com.startapp.sdk.ads.banner.BannerInterface;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.banner.BannerOptions;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.JsonAd;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adinformation.AdInformationOverrides;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.x8;
import com.startapp.ya;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class Banner3D extends BannerBase implements AdEventListener, BannerInterface {
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public AdInformationOverrides N;
    public List<m2> O;
    public int P;
    public BannerListener Q;
    public Runnable R;
    public BannerOptions r;
    public Banner3DAd s;
    public List<AdDetails> t;
    public AdPreferences u;
    public Camera v;
    public Matrix w;
    public Paint x;
    public float y;
    public float z;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        public AdRulesResult adRulesResult;
        public boolean bDefaultLoad;
        public boolean bIsVisible;
        private int currentImage;
        private AdDetails[] details;
        public m2[] faces;
        private int firstRotation;
        private int firstRotationFinished;
        public boolean loaded;
        public boolean loading;
        public BannerOptions options;
        public AdInformationOverrides overrides;
        private float rotation;

        private SavedState(Parcel parcel) {
            super(parcel);
            if (parcel.readInt() != 1) {
                this.bIsVisible = false;
                return;
            }
            this.bIsVisible = true;
            this.currentImage = parcel.readInt();
            this.rotation = parcel.readFloat();
            this.firstRotation = parcel.readInt();
            this.firstRotationFinished = parcel.readInt();
            Parcelable[] readParcelableArray = parcel.readParcelableArray(AdDetails.class.getClassLoader());
            if (readParcelableArray != null) {
                AdDetails[] adDetailsArr = new AdDetails[readParcelableArray.length];
                this.details = adDetailsArr;
                System.arraycopy(readParcelableArray, 0, adDetailsArr, 0, readParcelableArray.length);
            }
            int readInt = parcel.readInt();
            this.loaded = false;
            if (readInt == 1) {
                this.loaded = true;
            }
            int readInt2 = parcel.readInt();
            this.loading = false;
            if (readInt2 == 1) {
                this.loading = true;
            }
            int readInt3 = parcel.readInt();
            this.bDefaultLoad = false;
            if (readInt3 == 1) {
                this.bDefaultLoad = true;
            }
            int readInt4 = parcel.readInt();
            if (readInt4 > 0) {
                this.faces = new m2[readInt4];
                for (int i = 0; i < readInt4; i++) {
                    this.faces[i] = (m2) parcel.readParcelable(m2.class.getClassLoader());
                }
            }
            this.overrides = (AdInformationOverrides) parcel.readSerializable();
            this.options = (BannerOptions) parcel.readSerializable();
            this.adRulesResult = (AdRulesResult) parcel.readSerializable();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getCurrentImage() {
            return this.currentImage;
        }

        public List<AdDetails> getDetails() {
            return Arrays.asList(this.details);
        }

        public float getRotation() {
            return this.rotation;
        }

        public boolean isFirstRotation() {
            return this.firstRotation == 1;
        }

        public boolean isFirstRotationFinished() {
            return this.firstRotationFinished == 1;
        }

        public void setCurrentImage(int i) {
            this.currentImage = i;
        }

        public void setDetails(List<AdDetails> list) {
            this.details = new AdDetails[list.size()];
            for (int i = 0; i < list.size(); i++) {
                this.details[i] = list.get(i);
            }
        }

        public void setFirstRotation(boolean z) {
            this.firstRotation = z ? 1 : 0;
        }

        public void setFirstRotationFinished(boolean z) {
            this.firstRotationFinished = z ? 1 : 0;
        }

        public void setRotation(float f) {
            this.rotation = f;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            if (!this.bIsVisible) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeInt(this.currentImage);
            parcel.writeFloat(this.rotation);
            parcel.writeInt(this.firstRotation);
            parcel.writeInt(this.firstRotationFinished);
            parcel.writeParcelableArray(this.details, i);
            parcel.writeInt(this.loaded ? 1 : 0);
            parcel.writeInt(this.loading ? 1 : 0);
            parcel.writeInt(this.bDefaultLoad ? 1 : 0);
            m2[] m2VarArr = this.faces;
            if (m2VarArr == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(m2VarArr.length);
                for (m2 m2Var : this.faces) {
                    parcel.writeParcelable(m2Var, i);
                }
            }
            parcel.writeSerializable(this.overrides);
            parcel.writeSerializable(this.options);
            parcel.writeSerializable(this.adRulesResult);
        }
    }

    public Banner3D(Activity activity) {
        this((Context) activity);
    }

    public Banner3D(Activity activity, AttributeSet attributeSet) {
        this((Context) activity, attributeSet);
    }

    public Banner3D(Activity activity, AttributeSet attributeSet, int i) {
        this((Context) activity, attributeSet, i);
    }

    public Banner3D(Activity activity, BannerListener bannerListener) {
        this((Context) activity, bannerListener);
    }

    public Banner3D(Activity activity, AdPreferences adPreferences) {
        this((Context) activity, adPreferences);
    }

    public Banner3D(Activity activity, AdPreferences adPreferences, BannerListener bannerListener) {
        this((Context) activity, adPreferences, bannerListener);
    }

    public Banner3D(Activity activity, boolean z) {
        this((Context) activity, z);
    }

    public Banner3D(Activity activity, boolean z, AdPreferences adPreferences) {
        this((Context) activity, z, adPreferences);
    }

    @Deprecated
    public Banner3D(Context context) {
        this(context, true, (AdPreferences) null);
    }

    @Deprecated
    public Banner3D(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Banner3D(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = 45.0f;
        this.z = 0.0f;
        this.A = true;
        this.B = false;
        this.C = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = true;
        this.I = true;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = true;
        this.O = new ArrayList();
        this.P = 0;
        this.R = new Runnable() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.1
            /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
            /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 309
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.ads.banner.banner3d.Banner3D.AnonymousClass1.run():void");
            }
        };
        try {
            k();
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    @Deprecated
    public Banner3D(Context context, BannerListener bannerListener) {
        this(context, true, (AdPreferences) null);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public Banner3D(Context context, AdPreferences adPreferences) {
        this(context, true, adPreferences);
    }

    @Deprecated
    public Banner3D(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        this(context, true, adPreferences);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public Banner3D(Context context, boolean z) {
        this(context, z, (AdPreferences) null);
    }

    @Deprecated
    public Banner3D(Context context, boolean z, AdPreferences adPreferences) {
        super(context);
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = 45.0f;
        this.z = 0.0f;
        this.A = true;
        this.B = false;
        this.C = true;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = true;
        this.I = true;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = true;
        this.O = new ArrayList();
        this.P = 0;
        this.R = new Runnable() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.1
            @Override // java.lang.Runnable
            public void run() {
                /*  JADX ERROR: Method code generation error
                    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.IContainer.get(jadx.api.plugins.input.data.attributes.IJadxAttrType)" because "cont" is null
                    	at jadx.core.codegen.RegionGen.declareVars(RegionGen.java:70)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:406)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    */
                /*
                    Method dump skipped, instructions count: 309
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.ads.banner.banner3d.Banner3D.AnonymousClass1.run():void");
            }
        };
        try {
            this.I = z;
            if (adPreferences == null) {
                this.u = new AdPreferences();
            } else {
                this.u = adPreferences;
            }
            k();
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void a(int i) {
        this.h = i;
    }

    public final void a(Canvas canvas, Bitmap bitmap, int i, int i2, int i3, int i4, float f, float f2) {
        if (this.v == null) {
            this.v = new Camera();
        }
        this.v.save();
        this.v.translate(0.0f, 0.0f, i4);
        this.v.rotateX(f2);
        float f3 = -i4;
        this.v.translate(0.0f, 0.0f, f3);
        if (this.w == null) {
            this.w = new Matrix();
        }
        this.v.getMatrix(this.w);
        this.v.restore();
        this.w.preTranslate(-i3, f3);
        this.w.postScale(f, f);
        this.w.postTranslate(i2 + i3, i + i4);
        canvas.drawBitmap(bitmap, this.w, this.x);
    }

    public void a(List<AdDetails> list, boolean z) {
        Banner3DAd banner3DAd;
        this.t = list;
        if (list == null) {
            setErrorMessage("No ads to load");
            if (z) {
                d.a(getContext(), this.Q, this);
                return;
            }
            return;
        }
        l2 l2Var = new l2();
        if (!d.a(getContext(), getParent(), this.r, this, l2Var)) {
            setErrorMessage("Error in banner screen size");
            setVisibility(8);
            if (z) {
                d.a(getContext(), this.Q, this);
                return;
            }
            return;
        }
        setMinimumWidth(d.a(getContext(), this.r.o()));
        setMinimumHeight(d.a(getContext(), this.r.d()));
        if (getLayoutParams() != null && getLayoutParams().width == -1) {
            setMinimumWidth(d.a(getContext(), l2Var.a.x));
        }
        if (getLayoutParams() != null && getLayoutParams().height == -1) {
            setMinimumHeight(d.a(getContext(), l2Var.a.y));
        }
        if (getLayoutParams() != null) {
            if (getLayoutParams().width > 0) {
                setMinimumWidth(getLayoutParams().width);
            }
            if (getLayoutParams().height > 0) {
                setMinimumHeight(getLayoutParams().height);
            }
            if (getLayoutParams().width > 0 && getLayoutParams().height > 0 && (banner3DAd = this.s) != null) {
                banner3DAd.b(true);
            }
        }
        List<m2> list2 = this.O;
        if (list2 == null || list2.size() == 0) {
            q();
            removeAllViews();
            this.O = new ArrayList();
            Iterator<AdDetails> it = list.iterator();
            while (it.hasNext()) {
                this.O.add(new m2(getContext(), this, it.next(), this.r, new TrackingParams(this.j)));
            }
            this.P = 0;
        } else {
            Iterator<m2> it2 = this.O.iterator();
            while (it2.hasNext()) {
                it2.next().a(getContext(), this.r, this);
            }
        }
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(u(), r());
        layoutParams.addRule(13);
        int s = s();
        layoutParams.rightMargin = s;
        layoutParams.leftMargin = s;
        int t = t();
        layoutParams.topMargin = t;
        layoutParams.bottomMargin = t;
        addView(relativeLayout, layoutParams);
        new AdInformationObject(getContext(), AdInformationObject.Size.SMALL, AdPreferences.Placement.INAPP_BANNER, this.N, null).a(relativeLayout);
        if (this.x == null) {
            Paint paint = new Paint();
            this.x = paint;
            paint.setAntiAlias(true);
            this.x.setFilterBitmap(true);
        }
        if (!this.G) {
            this.G = true;
            x();
        }
        if (this.H) {
            w();
        }
        if (z) {
            Context context = getContext();
            BannerListener bannerListener = this.Q;
            g5.a(bannerListener == null ? null : new h2(bannerListener, this, context));
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int d() {
        return this.h;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public String e() {
        return "StartApp Banner3D";
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int f() {
        return 50;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public String getBidToken() {
        Banner3DAd banner3DAd = this.s;
        if (banner3DAd != null) {
            return banner3DAd.getBidToken();
        }
        return null;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int h() {
        return BannerMetaData.b.a().j();
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, com.startapp.sdk.ads.banner.BannerInterface
    public void hideBanner() {
        this.H = false;
        setVisibility(8);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int j() {
        return 300;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void l() {
        if (this.K) {
            return;
        }
        this.r = BannerMetaData.b.b();
        this.t = new ArrayList();
        if (this.u == null) {
            this.u = new AdPreferences();
        }
        this.N = new AdInformationOverrides();
        q();
        this.O = new ArrayList();
        this.K = true;
        setBackgroundColor(0);
        if (getId() == -1) {
            setId(this.h);
        }
        if (this.I) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ViewTreeObserver viewTreeObserver = Banner3D.this.getViewTreeObserver();
                    int i = ya.a;
                    if (Build.VERSION.SDK_INT >= 16) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                    } else {
                        viewTreeObserver.removeGlobalOnLayoutListener(this);
                    }
                    Banner3D banner3D = Banner3D.this;
                    banner3D.a(banner3D.u);
                    Banner3D banner3D2 = Banner3D.this;
                    if (banner3D2.J) {
                        return;
                    }
                    banner3D2.n();
                }
            });
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void o() {
        this.J = false;
        this.K = true;
        this.G = false;
        this.A = true;
        this.C = true;
        this.D = false;
        this.E = false;
        this.g = false;
        this.c = null;
        q();
        this.O = new ArrayList();
        Context context = getContext();
        Banner3DAd banner3DAd = this.s;
        this.s = new Banner3DAd(context, banner3DAd != null ? banner3DAd.h() : 0);
        if (this.u == null) {
            this.u = new AdPreferences();
        }
        this.s.load(this.u, this);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.L = true;
        BannerOptions bannerOptions = this.r;
        if (bannerOptions == null || !bannerOptions.q()) {
            this.C = false;
            this.D = true;
        }
        x();
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.L = false;
        removeCallbacks(this.R);
        List<m2> list = this.O;
        if (list != null) {
            Iterator<m2> it = list.iterator();
            while (it.hasNext()) {
                r5 r5Var = it.next().g;
                if (r5Var != null) {
                    r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
                }
            }
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        Bitmap bitmap2;
        super.onDraw(canvas);
        if (!this.g && !this.K) {
            this.g = true;
            x();
        }
        if (isInEditMode() || !this.H) {
            return;
        }
        List<m2> list = this.O;
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            int u = u();
            int r = r();
            int s = s();
            int t = t();
            float g = this.r.g() + (((float) Math.pow(Math.abs(this.y - 45.0f) / 45.0f, this.r.l())) * (1.0f - this.r.g()));
            if (!this.D) {
                g = this.r.g();
            }
            float f = g;
            Bitmap bitmap3 = this.O.get(((this.P - 1) + this.O.size()) % this.O.size()).d;
            Bitmap bitmap4 = this.O.get(this.P).d;
            if (bitmap4 == null || bitmap3 == null) {
                return;
            }
            float f2 = this.y;
            if (f2 < 45.0f) {
                if (f2 > 3.0f) {
                    bitmap2 = bitmap3;
                    a(canvas, bitmap4, t, s, u / 2, r / 2, f, (f2 - 90.0f) * this.r.c().getRotationMultiplier());
                } else {
                    bitmap2 = bitmap3;
                }
                a(canvas, bitmap2, t, s, u / 2, r / 2, f, this.y * this.r.c().getRotationMultiplier());
                return;
            }
            if (f2 < 87.0f) {
                bitmap = bitmap4;
                a(canvas, bitmap3, t, s, u / 2, r / 2, f, f2 * this.r.c().getRotationMultiplier());
            } else {
                bitmap = bitmap4;
            }
            a(canvas, bitmap, t, s, u / 2, r / 2, f, (this.y - 90.0f) * this.r.c().getRotationMultiplier());
            if (this.C) {
                return;
            }
            this.D = true;
        } catch (Throwable th) {
            p7.a(getContext(), th);
        }
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onFailedToReceiveAd(Ad ad) {
        if (ad != null) {
            setErrorMessage(ad.getErrorMessage());
        }
        d.a(getContext(), this.Q, this);
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onReceiveAd(Ad ad) {
        this.J = true;
        this.K = false;
        this.N = this.s.getAdInfoOverride();
        List<AdDetails> g = ((JsonAd) ad).g();
        this.t = g;
        a(g, this.M);
        this.M = false;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        boolean z = savedState.bIsVisible;
        this.H = z;
        if (z) {
            this.t = savedState.getDetails();
            this.y = savedState.getRotation();
            this.C = savedState.isFirstRotation();
            this.D = savedState.isFirstRotationFinished();
            this.P = savedState.getCurrentImage();
            m2[] m2VarArr = savedState.faces;
            q();
            this.O = new ArrayList();
            if (m2VarArr != null) {
                for (m2 m2Var : m2VarArr) {
                    this.O.add(m2Var);
                }
            }
            this.J = savedState.loaded;
            this.K = savedState.loading;
            this.I = savedState.bDefaultLoad;
            this.N = savedState.overrides;
            this.r = savedState.options;
            if (this.t.size() != 0) {
                post(new Runnable() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.4
                    @Override // java.lang.Runnable
                    public void run() {
                        Banner3D banner3D = Banner3D.this;
                        banner3D.a(banner3D.t, false);
                    }
                });
            } else {
                this.I = true;
                k();
            }
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.bIsVisible = this.H;
        savedState.setDetails(this.t);
        savedState.setRotation(this.y);
        savedState.setFirstRotation(this.C);
        savedState.setFirstRotationFinished(this.D);
        savedState.setCurrentImage(this.P);
        savedState.options = this.r;
        savedState.faces = new m2[this.O.size()];
        savedState.loaded = this.J;
        savedState.loading = this.K;
        savedState.overrides = this.N;
        for (int i = 0; i < this.O.size(); i++) {
            savedState.faces[i] = this.O.get(i);
        }
        return savedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        List<m2> list;
        int u = u();
        int r = r();
        int s = s();
        int t = t();
        if (!(motionEvent.getX() >= ((float) s) && motionEvent.getY() >= ((float) t) && motionEvent.getX() <= ((float) (s + u)) && motionEvent.getY() <= ((float) (t + r))) || (list = this.O) == null || list.size() == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.F = true;
            this.z = motionEvent.getY();
        } else if (action != 1) {
            if (action == 2 && this.z - motionEvent.getY() >= 10.0f) {
                this.F = false;
                this.z = motionEvent.getY();
            }
        } else if (this.F) {
            if (this.y < 45.0f) {
                v();
            }
            this.F = false;
            this.A = false;
            setClicked(true);
            postDelayed(new Runnable() { // from class: com.startapp.sdk.ads.banner.banner3d.Banner3D.3
                @Override // java.lang.Runnable
                public void run() {
                    Banner3D.this.A = true;
                }
            }, AdsCommonMetaData.h.z());
            m2 m2Var = this.O.get(this.P);
            Context context = getContext();
            String l = m2Var.a.l();
            boolean a = g5.a(context, AdPreferences.Placement.INAPP_BANNER);
            r5 r5Var = m2Var.g;
            if (r5Var != null) {
                r5Var.a(null, null);
            }
            if (l != null && !"null".equals(l) && !TextUtils.isEmpty(l)) {
                g5.a(l, m2Var.a.k(), m2Var.a.f(), context, m2Var.f);
            } else if (!m2Var.a.z() || a) {
                g5.a(context, m2Var.a.f(), m2Var.a.t(), m2Var.f, m2Var.a.A() && !a, false);
            } else {
                g5.a(context, m2Var.a.f(), m2Var.a.t(), m2Var.a.o(), m2Var.f, AdsCommonMetaData.h.z(), AdsCommonMetaData.h.y(), m2Var.a.A(), m2Var.a.B(), false, null);
            }
            Context context2 = getContext();
            BannerListener bannerListener = this.Q;
            g5.a(bannerListener != null ? new k2(bannerListener, this, context2) : null);
        }
        return true;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            this.L = false;
            if (this.B) {
                return;
            }
            removeCallbacks(this.R);
            return;
        }
        this.L = true;
        BannerOptions bannerOptions = this.r;
        if (bannerOptions == null || !bannerOptions.q()) {
            this.C = false;
            this.D = true;
        }
        x();
    }

    public final void q() {
        List<m2> list = this.O;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (m2 m2Var : this.O) {
            if (m2Var != null) {
                Bitmap bitmap = m2Var.c;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                Bitmap bitmap2 = m2Var.d;
                if (bitmap2 != null) {
                    bitmap2.recycle();
                }
                m2Var.c = null;
                m2Var.d = null;
                r5 r5Var = m2Var.g;
                if (r5Var != null) {
                    r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
                }
                Banner3DView banner3DView = m2Var.h;
                if (banner3DView != null) {
                    banner3DView.removeAllViews();
                    m2Var.h = null;
                }
            }
        }
    }

    public final int r() {
        return (int) (d.a(getContext(), this.r.d()) * this.r.e());
    }

    public final int s() {
        return (getWidth() - u()) / 2;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void setAdTag(String str) {
        this.j = str;
    }

    @Override // com.startapp.sdk.ads.banner.BannerInterface
    public void setBannerListener(BannerListener bannerListener) {
        this.Q = bannerListener;
    }

    @Override // com.startapp.sdk.ads.banner.BannerInterface
    public void showBanner() {
        this.H = true;
        w();
    }

    public final int t() {
        return (getHeight() - r()) / 2;
    }

    public final int u() {
        return (int) (d.a(getContext(), this.r.o()) * this.r.p());
    }

    public final void v() {
        this.P = ((this.P - 1) + this.O.size()) % this.O.size();
    }

    public final void w() {
        setVisibility(0);
        if (this.s != null) {
            x8 r = ComponentLocator.a(getContext()).r();
            AdPreferences.Placement placement = AdPreferences.Placement.INAPP_BANNER;
            String adId = this.s.getAdId();
            r.getClass();
            if (adId != null) {
                r.a.put(new x8.a(placement, -1), adId);
            }
        }
    }

    public final void x() {
        if (this.L && this.g) {
            removeCallbacks(this.R);
            post(this.R);
        }
    }
}
