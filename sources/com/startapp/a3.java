package com.startapp;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataStyle;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a3 extends ArrayAdapter<e3> {
    public String a;
    public String b;

    public a3(Context context, List list, String str, String str2) {
        super(context, 0, list);
        this.a = str;
        this.b = str2;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        f3 f3Var;
        if (view == null) {
            f3Var = new f3(getContext());
            view2 = f3Var.a;
        } else {
            view2 = view;
            f3Var = (f3) view.getTag();
        }
        e3 item = getItem(i);
        MetaDataStyle a = AdsCommonMetaData.h.a(item.q);
        if (f3Var.g != a) {
            f3Var.g = a;
            f3Var.a.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{a.e().intValue(), a.d().intValue()}));
            f3Var.c.setTextSize(a.h().intValue());
            f3Var.c.setTextColor(a.f().intValue());
            d.a(f3Var.c, a.g());
            f3Var.d.setTextSize(a.c().intValue());
            f3Var.d.setTextColor(a.a().intValue());
            d.a(f3Var.d, a.b());
        }
        f3Var.c.setText(item.g);
        f3Var.d.setText(item.h);
        Bitmap a2 = h3.a.a(this.b).a.a(i, item.a, item.i);
        if (a2 == null) {
            f3Var.b.setImageResource(R.drawable.sym_def_app_icon);
            f3Var.b.setTag("tag_error");
        } else {
            f3Var.b.setImageBitmap(a2);
            f3Var.b.setTag("tag_ok");
        }
        f3Var.f.setRating(item.j);
        f3Var.a(item.n != null);
        g3 a3 = h3.a.a(this.b);
        Context context = getContext();
        String[] strArr = item.c;
        TrackingParams trackingParams = new TrackingParams(this.a);
        Long l = item.o;
        long millis = l != null ? TimeUnit.SECONDS.toMillis(l.longValue()) : TimeUnit.SECONDS.toMillis(MetaData.h.m());
        z2 z2Var = a3.a;
        String a4 = z2Var.a(strArr, a3.c);
        if (!z2Var.a.containsKey(a4)) {
            r5 r5Var = new r5(context, strArr, trackingParams, millis);
            z2Var.a.put(a4, r5Var);
            r5Var.b();
        }
        return view2;
    }
}
