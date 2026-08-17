package com.startapp.sdk.inappbrowser;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.d;
import com.startapp.jc;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NavigationBarLayout extends RelativeLayout {
    public static final int a = Color.rgb(78, 86, 101);
    public static final int b = Color.rgb(Opcodes.LCMP, Opcodes.IFLT, Opcodes.IF_ACMPNE);
    public RelativeLayout c;
    public ImageView d;
    public ImageView e;
    public ImageView f;
    public ImageView g;
    public TextView h;
    public TextView i;
    public Boolean j;
    public Map<String, jc> k;

    public NavigationBarLayout(Context context) {
        super(context);
        this.j = Boolean.FALSE;
    }

    public void a() {
        if (Build.VERSION.SDK_INT < 11) {
            ((BitmapDrawable) this.d.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.f.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.g.getDrawable()).getBitmap().recycle();
            ((BitmapDrawable) this.e.getDrawable()).getBitmap().recycle();
        }
        this.k = null;
    }

    public void a(WebView webView) {
        if (this.j.booleanValue()) {
            if (webView.canGoBack()) {
                this.g.setImageBitmap(this.k.get("BACK_DARK").a);
                this.g.setEnabled(true);
            } else {
                this.g.setImageBitmap(this.k.get("BACK").a);
                this.g.setEnabled(false);
            }
            if (webView.canGoForward()) {
                this.e.setImageBitmap(this.k.get("FORWARD_DARK").a);
                this.e.setEnabled(true);
            } else {
                this.e.setImageBitmap(this.k.get("FORWARD").a);
                this.e.setEnabled(false);
            }
            if (webView.getTitle() != null) {
                this.h.setText(webView.getTitle());
                return;
            }
            return;
        }
        if (webView.canGoBack()) {
            this.g.setImageBitmap(this.k.get("BACK_DARK").a);
            addView(this.g, d.a(getContext(), new int[]{6, 0, 0, 0}, new int[]{15, 9}));
            View view = this.e;
            RelativeLayout.LayoutParams a2 = d.a(getContext(), new int[]{9, 0, 0, 0}, new int[]{15});
            a2.addRule(1, 2105);
            addView(view, a2);
            removeView(this.c);
            this.c.removeView(this.i);
            this.c.removeView(this.h);
            this.c.addView(this.h, d.a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14}));
            RelativeLayout relativeLayout = this.c;
            TextView textView = this.i;
            RelativeLayout.LayoutParams a3 = d.a(getContext(), new int[]{0, 0, 0, 0}, new int[]{14});
            a3.addRule(3, 2102);
            relativeLayout.addView(textView, a3);
            RelativeLayout.LayoutParams a4 = d.a(getContext(), new int[]{16, 0, 16, 0}, new int[]{15});
            a4.addRule(1, 2106);
            a4.addRule(0, 2104);
            addView(this.c, a4);
            this.j = Boolean.TRUE;
        }
    }

    public void setButtonsListener(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        this.g.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
        this.f.setOnClickListener(onClickListener);
    }
}
