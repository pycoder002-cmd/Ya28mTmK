package com.startapp;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.startapp.b9;
import com.startapp.sdk.ads.banner.BannerOptions;
import com.startapp.sdk.ads.banner.banner3d.Banner3DView;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdDetails;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m2 implements b9.b, Parcelable {
    public static final Parcelable.Creator<m2> CREATOR = new a();
    public AdDetails a;
    public Point b;
    public Bitmap c;
    public Bitmap d;
    public AtomicBoolean e;
    public TrackingParams f;
    public r5 g;
    public Banner3DView h;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Parcelable.Creator<m2> {
        @Override // android.os.Parcelable.Creator
        public m2 createFromParcel(Parcel parcel) {
            return new m2(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public m2[] newArray(int i) {
            return new m2[i];
        }
    }

    public m2(Context context, ViewGroup viewGroup, AdDetails adDetails, BannerOptions bannerOptions, TrackingParams trackingParams) {
        this.c = null;
        this.d = null;
        this.e = new AtomicBoolean(false);
        this.g = null;
        this.h = null;
        this.a = adDetails;
        this.f = trackingParams;
        a(context, bannerOptions, viewGroup);
    }

    public m2(Parcel parcel) {
        this.c = null;
        this.d = null;
        this.e = new AtomicBoolean(false);
        this.g = null;
        this.h = null;
        this.a = (AdDetails) parcel.readParcelable(AdDetails.class.getClassLoader());
        Point point = new Point(1, 1);
        this.b = point;
        point.x = parcel.readInt();
        this.b.y = parcel.readInt();
        this.c = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.e.set(zArr[0]);
        this.f = (TrackingParams) parcel.readSerializable();
    }

    public static Bitmap a(View view) {
        view.measure(view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return createBitmap;
    }

    public final void a() {
        Bitmap a2;
        Point point;
        int i;
        int i2;
        Banner3DView banner3DView = this.h;
        if (banner3DView != null) {
            try {
                a2 = a(banner3DView);
            } catch (OutOfMemoryError unused) {
            } catch (Throwable th) {
                p7.a(banner3DView.getContext(), th);
            }
            this.d = a2;
            if (a2 == null && (i = (point = this.b).x) > 0 && (i2 = point.y) > 0) {
                this.d = Bitmap.createScaledBitmap(a2, i, i2, false);
            }
            return;
        }
        a2 = null;
        this.d = a2;
        if (a2 == null) {
            return;
        }
        this.d = Bitmap.createScaledBitmap(a2, i, i2, false);
    }

    public void a(Context context, BannerOptions bannerOptions, ViewGroup viewGroup) {
        int round = Math.round(TypedValue.applyDimension(1, bannerOptions.d() - 5, context.getResources().getDisplayMetrics()));
        this.b = new Point((int) (Math.round(TypedValue.applyDimension(1, bannerOptions.o(), context.getResources().getDisplayMetrics())) * bannerOptions.p()), (int) (Math.round(TypedValue.applyDimension(1, bannerOptions.d(), context.getResources().getDisplayMetrics())) * bannerOptions.e()));
        Banner3DView banner3DView = new Banner3DView(context, new Point(bannerOptions.o(), bannerOptions.d()));
        this.h = banner3DView;
        banner3DView.setText(this.a.s());
        this.h.setRating(this.a.p());
        this.h.setDescription(this.a.h());
        this.h.setButtonText(this.a.y());
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            this.h.setImage(bitmap, round, round);
        } else {
            this.h.setImage(R.drawable.sym_def_app_icon, round, round);
            new b9(context, this.a.i(), this, 0).a();
        }
        Point point = this.b;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(point.x, point.y);
        layoutParams.addRule(13);
        viewGroup.addView(this.h, layoutParams);
        this.h.setVisibility(8);
        a();
    }

    @Override // com.startapp.b9.b
    public void a(Bitmap bitmap, int i) {
        Banner3DView banner3DView;
        if (bitmap == null || (banner3DView = this.h) == null) {
            return;
        }
        this.c = bitmap;
        banner3DView.setImage(bitmap);
        a();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeInt(this.b.x);
        parcel.writeInt(this.b.y);
        parcel.writeParcelable(this.c, i);
        parcel.writeBooleanArray(new boolean[]{this.e.get()});
        parcel.writeSerializable(this.f);
    }
}
