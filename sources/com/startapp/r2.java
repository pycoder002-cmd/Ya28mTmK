package com.startapp;

import android.R;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.a8;
import com.startapp.sdk.adsbase.mraid.bridge.MraidState;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r2 extends q2 {
    public d L;
    public d8 M;
    public e8 N;
    public ImageButton O;
    public TextView P;
    public ImageView Q;
    public MraidState K = MraidState.LOADING;
    public boolean R = false;
    public boolean S = false;
    public Handler T = null;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements a8.a {
        public a() {
        }

        @Override // com.startapp.a8.a
        public boolean onClickEvent(String str) {
            return r2.this.a(str, true);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends WebChromeClient {
        public b() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            try {
                if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR && consoleMessage.message().contains("mraid")) {
                    p7 p7Var = new p7(q7.c);
                    p7Var.d = "MraidMode.ConsoleError";
                    p7Var.e = consoleMessage.message();
                    p7Var.a(r2.this.b);
                }
            } catch (Throwable th) {
                p7.a(r2.this.b, th);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c extends c8 {
        public c(b8 b8Var) {
            super(b8Var);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (r2.this.K == MraidState.LOADING) {
                aa.a(webView, true, "mraid.setPlacementType", "interstitial");
                r2 r2Var = r2.this;
                com.startapp.d.a(r2Var.b, webView, r2Var.M);
                r2.this.w();
                r2 r2Var2 = r2.this;
                r2Var2.getClass();
                try {
                    RelativeLayout relativeLayout = new RelativeLayout(r2Var2.b);
                    ImageButton imageButton = new ImageButton(r2Var2.b);
                    r2Var2.O = imageButton;
                    imageButton.setBackgroundColor(0);
                    r2Var2.O.setOnClickListener(new t2(r2Var2));
                    int a = com.startapp.d.a(r2Var2.b, 50);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
                    layoutParams.addRule(13);
                    relativeLayout.addView(r2Var2.O, layoutParams);
                    if (r2Var2.o() && !r2Var2.t) {
                        int a2 = com.startapp.d.a(r2Var2.b, 32);
                        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
                        layoutParams2.addRule(13);
                        ImageView imageView = new ImageView(r2Var2.b);
                        r2Var2.Q = imageView;
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setShape(1);
                        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
                        gradientDrawable.setStroke(2, -1);
                        int a3 = com.startapp.d.a(r2Var2.b, 32);
                        gradientDrawable.setSize(a3, a3);
                        imageView.setImageDrawable(gradientDrawable);
                        r2Var2.Q.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        relativeLayout.addView(r2Var2.Q, layoutParams2);
                        TextView textView = new TextView(r2Var2.b);
                        r2Var2.P = textView;
                        textView.setTextColor(-1);
                        r2Var2.P.setGravity(17);
                        relativeLayout.addView(r2Var2.P, layoutParams2);
                    }
                    if (!r2Var2.R) {
                        r2Var2.v();
                    }
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(a, a);
                    layoutParams3.addRule(10);
                    layoutParams3.addRule(11);
                    r2Var2.y.addView(relativeLayout, layoutParams3);
                } catch (Throwable th) {
                    p7.a(r2Var2.b, th);
                }
                r2 r2Var3 = r2.this;
                MraidState mraidState = MraidState.DEFAULT;
                r2Var3.K = mraidState;
                com.startapp.d.a(mraidState, webView);
                aa.a(webView, true, "mraid.fireReadyEvent", new Object[0]);
                r2 r2Var4 = r2.this;
                if (r2Var4.S) {
                    r2Var4.L.fireViewableChangeEvent();
                }
                r2 r2Var5 = r2.this;
                Handler handler = r2Var5.T;
                if (handler != null) {
                    handler.post(new s2(r2Var5));
                }
                r2 r2Var6 = r2.this;
                r2Var6.a(r2Var6.O);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d extends a8 {
        public d(a8.a aVar) {
            super(aVar);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void close() {
            r2 r2Var = r2.this;
            MraidState mraidState = MraidState.HIDDEN;
            r2Var.K = mraidState;
            com.startapp.d.a(mraidState, r2Var.w);
            r2.this.I.run();
        }

        public void fireViewableChangeEvent() {
            r2 r2Var = r2.this;
            aa.a(r2Var.w, true, "mraid.fireViewableChangeEvent", Boolean.valueOf(r2Var.S));
        }

        @Override // com.startapp.a8
        public boolean isFeatureSupported(String str) {
            return r2.this.M.b.contains(str);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void setOrientationProperties(Map<String, String> map) {
            boolean parseBoolean = Boolean.parseBoolean(map.get("allowOrientationChange"));
            String str = map.get("forceOrientation");
            e8 e8Var = r2.this.N;
            if (e8Var.b == parseBoolean && e8Var.c == e8.a(str)) {
                return;
            }
            e8 e8Var2 = r2.this.N;
            e8Var2.b = parseBoolean;
            e8Var2.c = e8.a(str);
            r2 r2Var = r2.this;
            applyOrientationProperties(r2Var.b, r2Var.N);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void useCustomClose(String str) {
            boolean parseBoolean = Boolean.parseBoolean(str);
            r2 r2Var = r2.this;
            if (r2Var.R != parseBoolean) {
                r2Var.R = parseBoolean;
                if (!parseBoolean) {
                    r2.this.v();
                    return;
                }
                r2 r2Var2 = r2.this;
                r2Var2.getClass();
                try {
                    ImageButton imageButton = r2Var2.O;
                    if (imageButton != null) {
                        imageButton.setImageResource(R.color.transparent);
                    }
                } catch (Throwable th) {
                    p7.a(r2Var2.b, th);
                }
            }
        }
    }

    @Override // com.startapp.p2
    public void a(Configuration configuration) {
        w();
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void a(Bundle bundle) {
        super.a(bundle);
        if (this.M == null) {
            this.M = new d8(this.b);
        }
        if (this.N == null) {
            this.N = new e8(true, 2);
        }
        if (this.L == null) {
            this.L = new d(new a());
        }
    }

    @Override // com.startapp.q2
    public boolean a(String str, boolean z) {
        MraidState mraidState = MraidState.HIDDEN;
        this.K = mraidState;
        com.startapp.d.a(mraidState, this.w);
        try {
            return super.a(str, z);
        } catch (Throwable th) {
            p7.a(this.b, th);
            return false;
        }
    }

    @Override // com.startapp.q2
    public boolean b(String str) {
        return false;
    }

    @Override // com.startapp.q2, com.startapp.p2
    public boolean c() {
        if (!u()) {
            return true;
        }
        super.c();
        return false;
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void e() {
        this.S = false;
        if (this.K == MraidState.DEFAULT) {
            this.L.fireViewableChangeEvent();
        }
        super.e();
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void f() {
        super.f();
        if (this.T == null && o()) {
            this.T = new Handler();
        }
        this.S = true;
        if (this.K == MraidState.DEFAULT) {
            this.L.fireViewableChangeEvent();
        }
    }

    @Override // com.startapp.q2
    public long j() {
        return (SystemClock.uptimeMillis() - this.B) / 1000;
    }

    @Override // com.startapp.q2
    public boolean o() {
        return this.s > 0;
    }

    @Override // com.startapp.q2
    public void t() {
        this.w.setWebViewClient(new c(this.L));
        this.w.setWebChromeClient(new b());
    }

    public final boolean u() {
        return (SystemClock.uptimeMillis() - this.B) / 1000 >= ((long) this.s);
    }

    public final void v() {
        try {
            if (this.O != null) {
                this.O.setImageDrawable(new BitmapDrawable(this.b.getResources(), c9.a("iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA39pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozODRkZTAxYi00OWRkLWM4NDYtYThkNC0wZWRiMDMwYTZlODAiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QkE0Q0U2MUY2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QkE0Q0U2MUU2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjlkZjAyMGU0LTNlYmUtZTY0ZC04YjRiLWM5ZWY4MTU4ZjFhYyIgc3RSZWY6ZG9jdW1lbnRJRD0iYWRvYmU6ZG9jaWQ6cGhvdG9zaG9wOmU1MzEzNDdlLTZjMDEtMTFlNS1hZGZlLThmMTBjZWYxMGRiZSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PngNsEEAAANeSURBVHjatFfNS1tBEH+pUZOQ0B4i3sTSxHMRFNQoFBEP7dHgvyDiKWgguQra9F+oxqNiwOTQ+oFI1ZM3jSf1YK5FL41ooaKZzu+x+4gv2bx9Rgd+JNn5zO7s7IzH0CQiCvLHZ8YnxkfGe8ZbwS4zSowTxi/GT4/Hc2u8BLHjCOM745b06VboRJpx7GN8ZfyDxUqlQgcHB5RMJmloaIg6Ozupra3NBL5jDTzIQFYQdDOw5db5B8YxLDw+PtLKygr19PQQWDqIRqOUzWZNXUHH2rvBgr2M39C6uLig/v5+bcd2QLdUKskgYLNX57yvIL2zs0OhUOjZziU6Ojro8PBQBnGl3Alm+BknkMI54mybdS4BW3t7ezKIInzVCwDJYm4Zon4p5xLYzfPzcxlEpl7S3SNpmjlznZwQiXn/5CjEnTUzt5GBsbExamlpUfLBg0wjG8vLy3IXlqTzEAoH7m4kElEqTk1Nmfd7bW2tbhBYAw8ykFXZgQ9RJ1CsQghgEr/29/eVStPT09XFhdbX18nr9Vr81tZWyuVyFh+yMzMzSnvwJWjyDS+MYic2NzeV17O7u9vg2m79jsfjBv9bg7PbxOrqqjExMWHxIdvV1aW0V+VrFDtwhFCGh4cbnl0mk6kp+BsbGybsBNlGtkZGRqToEQK4xjfUc6csXlhYcHyFFhcXHe3Al6BrQz427e3tWldpfn5e6Rw83cIkHyvXAUAZb4SdsKZbPe0BaB+Bz+cjTiDlDmxtbZkybo9AKwn9fj9tb2875gBkINvIFnzJJMQ1PMV9GBgYUF6bQCBgFAoFY3x8/Ml6KpUy0un0kzXIQBY6KqrydapViPL5fM0/Rfcj+fhuJw5CqxBpleJYLEY3NzeW8dnZ2RoZrEmCLHQcSvGdWYrFe7CEFTwUqqjR85XLZUokEkoZ8CADWe3HqKoTcnyOdW5KI5m+vj56eHiQz3G0bkNyeXn5ag3J2dmZ/PffVC1Z8bVast3d3eqWLKDVlAaDwaadh8Nhvaa0XluOHg7n9lzn0MWRarfltp0oysEErRqGDTeDCbK9ajApuh7TxGiWERlrjWZzc3M0ODhYM5phDTzbaHb/rNHMFkhUNK13LobTv6K2RJ3se1yO519s4/k7wf5jG89/6I7n/wUYAGo3YtcprD4sAAAAAElFTkSuQmCC")));
                this.O.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        } catch (Throwable th) {
            p7.a(this.b, th);
        }
    }

    public void w() {
        Activity activity = this.b;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            com.startapp.d.b(activity, i, i2, this.w);
            com.startapp.d.a(activity, i, i2, this.w);
            com.startapp.d.a(activity, 0, 0, i, i2, this.w);
            com.startapp.d.b(activity, 0, 0, i, i2, this.w);
        } catch (Throwable th) {
            p7.a(activity, th);
        }
    }
}
