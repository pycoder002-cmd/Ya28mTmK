package com.startapp.sdk.adsbase.adinformation;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.startapp.k7;
import com.startapp.sdk.adsbase.adinformation.AdInformationConfig;
import com.startapp.sdk.adsbase.adinformation.AdInformationPositions;
import com.startapp.sdk.adsbase.consent.ConsentData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import java.lang.ref.WeakReference;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdInformationObject implements View.OnClickListener {
    public final WeakReference<Context> a;
    public final AdInformationView b;
    public final AdPreferences.Placement c;
    public final ConsentData d;
    public final AdInformationOverrides e;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Size {
        SMALL(AdInformationConfig.ImageResourceType.INFO_S, AdInformationConfig.ImageResourceType.INFO_EX_S),
        LARGE(AdInformationConfig.ImageResourceType.INFO_L, AdInformationConfig.ImageResourceType.INFO_EX_L);

        private final AdInformationConfig.ImageResourceType infoExtendedType;
        private final AdInformationConfig.ImageResourceType infoType;

        Size(AdInformationConfig.ImageResourceType imageResourceType, AdInformationConfig.ImageResourceType imageResourceType2) {
            this.infoType = imageResourceType;
            this.infoExtendedType = imageResourceType2;
        }

        public AdInformationConfig.ImageResourceType a() {
            return this.infoType;
        }
    }

    public AdInformationObject(Context context, Size size, AdPreferences.Placement placement, AdInformationOverrides adInformationOverrides, ConsentData consentData) {
        this.a = new WeakReference<>(context);
        this.c = placement;
        this.e = adInformationOverrides;
        this.d = consentData;
        this.b = new AdInformationView(context, size, placement, adInformationOverrides, this);
    }

    public void a(RelativeLayout relativeLayout) {
        AdInformationConfig a = AdInformationMetaData.a.a();
        AdInformationOverrides adInformationOverrides = this.e;
        if ((adInformationOverrides == null || !adInformationOverrides.d()) ? a.b(this.a.get()) : this.e.c()) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            AdInformationOverrides adInformationOverrides2 = this.e;
            if (adInformationOverrides2 == null || !adInformationOverrides2.e()) {
                AdPreferences.Placement placement = this.c;
                AdInformationPositions.Position position = a.Positions.get(placement);
                if (position == null) {
                    position = AdInformationPositions.Position.BOTTOM_LEFT;
                    a.Positions.put(placement, position);
                }
                position.addRules(layoutParams);
            } else {
                this.e.b().addRules(layoutParams);
            }
            relativeLayout.addView(this.b, layoutParams);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Context context = this.a.get();
        if (context == null) {
            return;
        }
        k7 f = ComponentLocator.a(context).f();
        ConsentData consentData = this.d;
        String c = consentData != null ? consentData.c() : null;
        ConsentData consentData2 = this.d;
        String d = consentData2 != null ? consentData2.d() : null;
        ConsentData consentData3 = this.d;
        f.a(true, c, d, consentData3 != null ? consentData3.b() : null);
    }
}
