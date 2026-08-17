package com.startapp;

import android.R;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.inappbrowser.AnimatingProgressBar;
import com.startapp.sdk.inappbrowser.NavigationBarLayout;
import java.util.HashMap;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ic extends p2 implements View.OnClickListener {
    public static boolean v;
    public FrameLayout A;
    public String B;
    public RelativeLayout w;
    public NavigationBarLayout x;
    public WebView y;
    public AnimatingProgressBar z;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends WebChromeClient {
        public a() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            ic.this.z.setProgress(i);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            if (str == null || str.equals("")) {
                return;
            }
            ic.this.x.h.setText(str);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b extends WebViewClient {
        public Context a;
        public ic b;
        public NavigationBarLayout c;
        public AnimatingProgressBar d;
        public int e = 0;
        public boolean f = false;

        public b(Context context, NavigationBarLayout navigationBarLayout, AnimatingProgressBar animatingProgressBar, ic icVar) {
            this.a = context;
            this.d = animatingProgressBar;
            this.c = navigationBarLayout;
            this.b = icVar;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (ic.v) {
                return;
            }
            this.c.a(webView);
            int i = this.e - 1;
            this.e = i;
            if (i == 0) {
                this.f = false;
                this.d.a();
                if (this.d.isShown()) {
                    this.d.setVisibility(8);
                }
                this.c.a(webView);
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (ic.v) {
                return;
            }
            if (this.f) {
                this.e = 1;
                this.d.a();
                this.c.a(webView);
            } else {
                this.e = Math.max(this.e, 1);
            }
            this.d.setVisibility(0);
            this.c.i.setText(str);
            this.c.a(webView);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.d.a();
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (webView != null && str != null && !aa.b(webView.getContext(), str) && !ic.v) {
                if (!this.f) {
                    this.f = true;
                    this.d.a();
                    this.e = 0;
                }
                this.e++;
                if (g5.c(str) && !g5.b(str)) {
                    return false;
                }
                this.e = 1;
                g5.b(this.a, str, (String) null);
                ic icVar = this.b;
                if (icVar != null) {
                    icVar.i();
                }
            }
            return true;
        }
    }

    public ic(String str) {
        this.B = str;
    }

    @Override // com.startapp.p2
    public void a(Bundle bundle) {
        la.a(this.b).a(this.d, new IntentFilter("com.startapp.android.CloseAdActivity"));
        v = false;
        this.w = new RelativeLayout(this.b);
        String str = this.B;
        if (this.x == null) {
            NavigationBarLayout navigationBarLayout = new NavigationBarLayout(this.b);
            this.x = navigationBarLayout;
            navigationBarLayout.setDescendantFocusability(262144);
            navigationBarLayout.setBackgroundColor(Color.parseColor("#e9e9e9"));
            navigationBarLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, d.a(navigationBarLayout.getContext(), 60)));
            navigationBarLayout.setId(2101);
            HashMap hashMap = new HashMap();
            hashMap.put("BACK", new jc(null, 14, 22, "back_.png"));
            hashMap.put("BACK_DARK", new jc(null, 14, 22, "back_dark.png"));
            hashMap.put("FORWARD", new jc(null, 14, 22, "forward_.png"));
            hashMap.put("FORWARD_DARK", new jc(null, 14, 22, "forward_dark.png"));
            hashMap.put("X", new jc(null, 23, 23, "x_dark.png"));
            hashMap.put("BROWSER", new jc(null, 28, 28, "browser_icon_dark.png"));
            navigationBarLayout.k = hashMap;
            NavigationBarLayout navigationBarLayout2 = this.x;
            navigationBarLayout2.getClass();
            Typeface typeface = Typeface.DEFAULT;
            navigationBarLayout2.h = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.h, typeface, 1, 16.46f, NavigationBarLayout.a, 2102);
            navigationBarLayout2.i = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.h, typeface, 1, 12.12f, NavigationBarLayout.b, 2107);
            navigationBarLayout2.h.setText("Loading...");
            RelativeLayout relativeLayout = new RelativeLayout(navigationBarLayout2.getContext());
            navigationBarLayout2.c = relativeLayout;
            relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            navigationBarLayout2.c.addView(navigationBarLayout2.h, d.a(navigationBarLayout2.getContext(), new int[]{0, 0, 0, 0}, new int[0]));
            RelativeLayout relativeLayout2 = navigationBarLayout2.c;
            TextView textView = navigationBarLayout2.i;
            RelativeLayout.LayoutParams a2 = d.a(navigationBarLayout2.getContext(), new int[]{0, 0, 0, 0}, new int[0]);
            a2.addRule(3, 2102);
            relativeLayout2.addView(textView, a2);
            for (jc jcVar : navigationBarLayout2.k.values()) {
                Bitmap a3 = a9.a(navigationBarLayout2.getContext(), jcVar.d);
                if (a3 != null) {
                    jcVar.a = Bitmap.createScaledBitmap(a3, d.a(navigationBarLayout2.getContext(), jcVar.b), d.a(navigationBarLayout2.getContext(), jcVar.c), true);
                }
            }
            navigationBarLayout2.d = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.d, navigationBarLayout2.k.get("X").a, 2103);
            navigationBarLayout2.f = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.f, navigationBarLayout2.k.get("BROWSER").a, 2104);
            navigationBarLayout2.g = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.g, navigationBarLayout2.k.get("BACK").a, 2105);
            navigationBarLayout2.e = d.a(navigationBarLayout2.getContext(), navigationBarLayout2.e, navigationBarLayout2.k.get("FORWARD").a, 2106);
            int a4 = d.a(navigationBarLayout2.getContext(), 10);
            navigationBarLayout2.e.setPadding(a4, a4, a4, a4);
            navigationBarLayout2.e.setEnabled(false);
            navigationBarLayout2.g.setPadding(a4, a4, a4, a4);
            navigationBarLayout2.addView(navigationBarLayout2.d, d.a(navigationBarLayout2.getContext(), new int[]{0, 0, 16, 0}, new int[]{15, 11}));
            ImageView imageView = navigationBarLayout2.f;
            RelativeLayout.LayoutParams a5 = d.a(navigationBarLayout2.getContext(), new int[]{0, 0, 17, 0}, new int[]{15});
            a5.addRule(0, 2103);
            navigationBarLayout2.addView(imageView, a5);
            RelativeLayout relativeLayout3 = navigationBarLayout2.c;
            RelativeLayout.LayoutParams a6 = d.a(navigationBarLayout2.getContext(), new int[]{16, 6, 16, 0}, new int[]{9});
            a6.addRule(0, 2104);
            navigationBarLayout2.addView(relativeLayout3, a6);
            this.x.setButtonsListener(this);
        }
        this.w.addView(this.x);
        this.z = new AnimatingProgressBar(this.b, null, R.attr.progressBarStyleHorizontal);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.parseColor("#45d200"));
        this.z.setProgressDrawable(new ClipDrawable(shapeDrawable, 3, 1));
        this.z.setBackgroundColor(-1);
        this.z.setId(2108);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, d.a(this.b, 4));
        layoutParams.addRule(3, 2101);
        this.w.addView(this.z, layoutParams);
        this.A = new FrameLayout(this.b);
        if (this.y == null) {
            try {
                j();
                this.y.loadUrl(str);
            } catch (Throwable th) {
                p7.a(this.b, th);
                this.x.a();
                g5.b(this.b, str, (String) null);
                this.b.finish();
            }
        }
        this.A.addView(this.y);
        this.A.setBackgroundColor(-1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(15);
        layoutParams2.addRule(3, 2108);
        this.w.addView(this.A, layoutParams2);
        if (bundle != null) {
            this.y.restoreState(bundle);
        }
        this.b.setContentView(this.w, new RelativeLayout.LayoutParams(-2, -2));
    }

    @Override // com.startapp.p2
    public boolean a(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return false;
        }
        WebView webView = this.y;
        if (webView == null || !webView.canGoBack()) {
            i();
            return true;
        }
        this.z.a();
        this.y.goBack();
        return true;
    }

    @Override // com.startapp.p2
    public void b(Bundle bundle) {
        this.y.saveState(bundle);
    }

    @Override // com.startapp.p2
    public void e() {
    }

    @Override // com.startapp.p2
    public void f() {
    }

    public void i() {
        try {
            v = true;
            this.y.stopLoading();
            this.y.removeAllViews();
            this.y.postInvalidate();
            ya.a(this.y);
            this.y.destroy();
            this.y = null;
        } catch (Exception unused) {
        }
        this.x.a();
        this.b.finish();
    }

    public final void j() {
        WebView a2 = ComponentLocator.a(this.b).t().a();
        this.y = a2;
        a2.getSettings().setJavaScriptEnabled(true);
        this.y.getSettings().setUseWideViewPort(true);
        this.y.getSettings().setLoadWithOverviewMode(true);
        this.y.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.y.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 11) {
            this.y.getSettings().setDisplayZoomControls(false);
        }
        this.y.setWebViewClient(new b(this.b, this.x, this.z, this));
        this.y.setWebChromeClient(new a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case 2103:
                i();
                return;
            case 2104:
                WebView webView = this.y;
                if (webView != null) {
                    g5.b(this.b, webView.getUrl(), (String) null);
                    i();
                    return;
                }
                return;
            case 2105:
                WebView webView2 = this.y;
                if (webView2 == null || !webView2.canGoBack()) {
                    return;
                }
                this.z.a();
                this.y.goBack();
                return;
            case 2106:
                WebView webView3 = this.y;
                if (webView3 == null || !webView3.canGoForward()) {
                    return;
                }
                this.z.a();
                this.y.goForward();
                return;
            default:
                return;
        }
    }
}
