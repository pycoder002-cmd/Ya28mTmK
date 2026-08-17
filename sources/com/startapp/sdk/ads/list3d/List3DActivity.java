package com.startapp.sdk.ads.list3d;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.a3;
import com.startapp.a9;
import com.startapp.aa;
import com.startapp.e3;
import com.startapp.g3;
import com.startapp.g5;
import com.startapp.h3;
import com.startapp.i3;
import com.startapp.j3;
import com.startapp.la;
import com.startapp.p7;
import com.startapp.r5;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adinformation.AdInformationOverrides;
import com.startapp.sdk.adsbase.commontracking.CloseTrackingParams;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.z2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class List3DActivity extends Activity implements i3 {
    public List3DView a;
    public int b;
    public AdInformationObject c;
    public Long d;
    public Long e;
    public String h;
    public String i;
    public List<e3> j;
    public long f = 0;
    public long g = 0;
    public BroadcastReceiver k = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            List3DActivity.this.finish();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements AdapterView.OnItemClickListener {

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                List3DActivity.this.finish();
            }
        }

        public b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            r5 r5Var;
            String str = List3DActivity.this.j.get(i).b;
            String[] strArr = List3DActivity.this.j.get(i).d;
            String str2 = List3DActivity.this.j.get(i).f;
            boolean z = List3DActivity.this.j.get(i).k;
            boolean z2 = List3DActivity.this.j.get(i).l;
            String str3 = List3DActivity.this.j.get(i).n;
            String str4 = List3DActivity.this.j.get(i).m;
            Boolean bool = List3DActivity.this.j.get(i).p;
            g3 a2 = h3.a.a(List3DActivity.this.h);
            String[] strArr2 = List3DActivity.this.j.get(i).c;
            z2 z2Var = a2.a;
            String a3 = z2Var.a(strArr2, a2.c);
            HashMap<String, r5> hashMap = z2Var.a;
            if (hashMap != null && (r5Var = hashMap.get(a3)) != null) {
                r5Var.a(null, null);
            }
            if (str3 != null && !TextUtils.isEmpty(str3)) {
                List3DActivity list3DActivity = List3DActivity.this;
                g5.a(str3, str4, str, list3DActivity, new TrackingParams(list3DActivity.i));
                List3DActivity.this.finish();
            } else {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                boolean a4 = g5.a(List3DActivity.this.getApplicationContext(), AdPreferences.Placement.INAPP_OFFER_WALL);
                if (z && !a4) {
                    List3DActivity list3DActivity2 = List3DActivity.this;
                    g5.a(list3DActivity2, str, strArr, str2, list3DActivity2.a(), AdsCommonMetaData.h.z(), AdsCommonMetaData.h.y(), z2, bool, false, new a());
                } else {
                    List3DActivity list3DActivity3 = List3DActivity.this;
                    g5.a((Context) list3DActivity3, str, strArr, list3DActivity3.a(), z2 && !a4, false);
                    List3DActivity.this.finish();
                }
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            List3DActivity list3DActivity = List3DActivity.this;
            g5.a(list3DActivity, list3DActivity.b(), List3DActivity.this.a());
            List3DActivity.this.finish();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            List3DActivity.this.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
    }

    public TrackingParams a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.f = uptimeMillis;
        return new CloseTrackingParams((uptimeMillis - this.g) / 1000, this.i);
    }

    public String b() {
        List<e3> list = this.j;
        return (list == null || list.isEmpty() || this.j.get(0).e == null) ? "" : this.j.get(0).e;
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            this.f = SystemClock.uptimeMillis();
            g5.a(this, b(), a());
            String str = StartAppSDKInternal.a;
            StartAppSDKInternal.c.a.q = false;
            if (this.b == getResources().getConfiguration().orientation) {
                la.a(this).a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
            }
            synchronized (this) {
                if (this.k != null) {
                    la.a(this).a(this.k);
                    this.k = null;
                }
            }
            String str2 = this.h;
            if (str2 != null) {
                h3.a.a(str2).a();
                if (!AdsConstants.g.booleanValue()) {
                    h3 h3Var = h3.a;
                    h3Var.b.remove(this.h);
                }
            }
        } catch (Throwable th) {
            p7.a(this, th);
        }
        super.finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        h3.a.a(this.h).a();
        super.onBackPressed();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        View view;
        try {
            overridePendingTransition(0, 0);
            super.onCreate(bundle);
            if (getIntent().getBooleanExtra("fullscreen", false)) {
                requestWindowFeature(1);
                getWindow().setFlags(1024, 1024);
            }
            if (bundle == null) {
                la.a(this).a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                this.d = (Long) getIntent().getSerializableExtra("lastLoadTime");
                this.e = (Long) getIntent().getSerializableExtra("adCacheTtl");
            } else {
                if (bundle.containsKey("lastLoadTime")) {
                    this.d = (Long) bundle.getSerializable("lastLoadTime");
                }
                if (bundle.containsKey("adCacheTtl")) {
                    this.e = (Long) bundle.getSerializable("adCacheTtl");
                }
            }
            getIntent().getStringExtra("position");
            this.h = getIntent().getStringExtra("listModelUuid");
            la.a(this).a(this.k, new IntentFilter("com.startapp.android.CloseAdActivity"));
            this.b = getResources().getConfiguration().orientation;
            Map<Activity, Integer> map = aa.a;
            aa.a((Activity) this, getResources().getConfiguration().orientation, true);
            requestWindowFeature(1);
            this.i = getIntent().getStringExtra("adTag");
            int d2 = AdsCommonMetaData.h.d();
            int c2 = AdsCommonMetaData.h.c();
            this.a = new List3DView(this, null, this.i, this.h);
            this.a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{d2, c2}));
            List<e3> list = h3.a.a(this.h).b;
            this.j = list;
            if (list == null) {
                finish();
                return;
            }
            this.a.setStarted();
            this.a.setHint(true);
            this.a.setFade(true);
            a3 a3Var = new a3(this, this.j, this.i, this.h);
            h3.a.a(this.h).a(this, true);
            this.a.setAdapter(a3Var);
            this.a.setDynamics(new j3(0.9f, 0.6f));
            this.a.setOnItemClickListener(new b());
            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setContentDescription("StartApp Ad");
            relativeLayout.setId(1475346432);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            relativeLayout.addView(linearLayout, layoutParams2);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            relativeLayout2.setBackgroundColor(AdsCommonMetaData.h.A().intValue());
            linearLayout.addView(relativeLayout2);
            TextView textView = new TextView(this);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(13);
            textView.setLayoutParams(layoutParams3);
            float f = 2;
            textView.setPadding(0, Math.round(TypedValue.applyDimension(1, f, getResources().getDisplayMetrics())), 0, Math.round(TypedValue.applyDimension(1, 5, getResources().getDisplayMetrics())));
            textView.setTextColor(AdsCommonMetaData.h.D().intValue());
            textView.setTextSize(AdsCommonMetaData.h.F().intValue());
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setText(AdsCommonMetaData.h.B());
            textView.setShadowLayer(2.5f, -2.0f, 2.0f, -11513776);
            com.startapp.d.a(textView, AdsCommonMetaData.h.E());
            relativeLayout2.addView(textView);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(11);
            layoutParams4.addRule(15);
            Bitmap a2 = a9.a(this, "close_button.png");
            if (a2 != null) {
                ImageButton imageButton = new ImageButton(this, null, R.style.Theme.Translucent);
                float f2 = 36;
                imageButton.setImageBitmap(Bitmap.createScaledBitmap(a2, Math.round(TypedValue.applyDimension(1, f2, getResources().getDisplayMetrics())), Math.round(TypedValue.applyDimension(1, f2, getResources().getDisplayMetrics())), true));
                view = imageButton;
            } else {
                TextView textView2 = new TextView(this);
                textView2.setText("   x   ");
                textView2.setTextSize(20.0f);
                view = textView2;
            }
            view.setLayoutParams(layoutParams4);
            view.setOnClickListener(new c());
            view.setContentDescription("x");
            view.setId(1475346435);
            relativeLayout2.addView(view);
            View view2 = new View(this);
            view2.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(TypedValue.applyDimension(1, f, getResources().getDisplayMetrics()))));
            view2.setBackgroundColor(AdsCommonMetaData.h.C().intValue());
            linearLayout.addView(view2);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams5.weight = 1.0f;
            this.a.setLayoutParams(layoutParams5);
            linearLayout.addView(this.a);
            LinearLayout linearLayout2 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams6.gravity = 80;
            linearLayout2.setLayoutParams(layoutParams6);
            linearLayout2.setBackgroundColor(AdsCommonMetaData.h.u().intValue());
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2);
            TextView textView3 = new TextView(this);
            textView3.setTextColor(AdsCommonMetaData.h.v().intValue());
            textView3.setPadding(0, Math.round(TypedValue.applyDimension(1, f, getResources().getDisplayMetrics())), 0, Math.round(TypedValue.applyDimension(1, 3, getResources().getDisplayMetrics())));
            textView3.setText("Powered By ");
            textView3.setTextSize(16.0f);
            linearLayout2.addView(textView3);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(a9.a(this, "logo.png"), Math.round(TypedValue.applyDimension(1, 56, getResources().getDisplayMetrics())), Math.round(TypedValue.applyDimension(1, 12, getResources().getDisplayMetrics())), true));
            linearLayout2.addView(imageView);
            AdInformationObject adInformationObject = new AdInformationObject(this, AdInformationObject.Size.LARGE, AdPreferences.Placement.INAPP_OFFER_WALL, (AdInformationOverrides) getIntent().getSerializableExtra("adInfoOverride"), null);
            this.c = adInformationObject;
            adInformationObject.a(relativeLayout);
            setContentView(relativeLayout, layoutParams);
            new Handler().postDelayed(new d(), 500L);
        } catch (Throwable th) {
            p7.a(this, th);
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Map<Activity, Integer> map = aa.a;
        aa.a((Activity) this, getResources().getConfiguration().orientation, false);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        for (r5 r5Var : h3.a.a(this.h).a.a.values()) {
            if (r5Var != null) {
                r5Var.a();
            }
        }
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if ((this.d == null || this.e == null || System.currentTimeMillis() - this.d.longValue() <= this.e.longValue()) ? false : true) {
            finish();
            return;
        }
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.q = true;
        this.g = SystemClock.uptimeMillis();
        z2 z2Var = h3.a.a(this.h).a;
        for (String str2 : z2Var.a.keySet()) {
            if (z2Var.a.get(str2) != null) {
                z2Var.a.get(str2).b();
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Long l = this.d;
        if (l != null) {
            bundle.putSerializable("lastLoadTime", l);
        }
        Long l2 = this.e;
        if (l2 != null) {
            bundle.putSerializable("adCacheTtl", l2);
        }
    }
}
