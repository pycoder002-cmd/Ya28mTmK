package com.startapp.sdk.adsbase.adinformation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.startapp.d;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adinformation.AdInformationPositions;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdInformationView extends RelativeLayout {
    public ImageView a;
    public RelativeLayout b;
    public View.OnClickListener c;
    public AdInformationConfig d;
    public ImageResourceConfig e;
    public AdPreferences.Placement f;
    public AdInformationPositions.Position g;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ View.OnClickListener a;

        public a(AdInformationView adInformationView, View.OnClickListener onClickListener) {
            this.a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.onClick(view);
        }
    }

    public AdInformationView(Context context, AdInformationObject.Size size, AdPreferences.Placement placement, AdInformationOverrides adInformationOverrides, View.OnClickListener onClickListener) {
        super(context);
        this.c = null;
        this.f = placement;
        this.c = new a(this, onClickListener);
        a(size, adInformationOverrides);
    }

    public void a(AdInformationObject.Size size, AdInformationOverrides adInformationOverrides) {
        AdInformationConfig a2 = AdInformationMetaData.a.a();
        this.d = a2;
        if (a2 == null) {
            AdInformationConfig adInformationConfig = new AdInformationConfig();
            AdInformationConfig.a(adInformationConfig);
            this.d = adInformationConfig;
        }
        this.e = this.d.b.get(size.a());
        if (adInformationOverrides == null || !adInformationOverrides.e()) {
            AdInformationConfig adInformationConfig2 = this.d;
            AdPreferences.Placement placement = this.f;
            AdInformationPositions.Position position = adInformationConfig2.Positions.get(placement);
            if (position == null) {
                position = AdInformationPositions.Position.BOTTOM_LEFT;
                adInformationConfig2.Positions.put(placement, position);
            }
            this.g = position;
        } else {
            this.g = adInformationOverrides.b();
        }
        ImageView imageView = new ImageView(getContext());
        this.a = imageView;
        imageView.setContentDescription("info");
        this.a.setId(1475346433);
        this.a.setImageBitmap(this.e.a(getContext()));
        this.b = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(d.a(getContext(), (int) (this.e.d() * this.d.e())), d.a(getContext(), (int) (this.e.a() * this.d.e())));
        this.b.setBackgroundColor(0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(d.a(getContext(), this.e.d()), d.a(getContext(), this.e.a()));
        layoutParams2.setMargins(0, 0, 0, 0);
        this.a.setPadding(0, 0, 0, 0);
        this.g.addRules(layoutParams2);
        this.b.addView(this.a, layoutParams2);
        this.b.setOnClickListener(this.c);
        addView(this.b, layoutParams);
    }
}
