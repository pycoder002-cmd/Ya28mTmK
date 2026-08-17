package com.startapp.sdk.adsbase.model;

import com.startapp.f;
import com.startapp.sdk.adsbase.BaseResponse;
import com.startapp.sdk.adsbase.adinformation.AdInformationOverrides;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class GetAdResponse extends BaseResponse {
    private static final long serialVersionUID = 1;

    @f(complex = true)
    private AdInformationOverrides adInfoOverrides = AdInformationOverrides.a();

    @f(type = ArrayList.class, value = AdDetails.class)
    private List<AdDetails> adsDetails = new ArrayList();
    private boolean inAppBrowser;

    @f(type = inAppBrowserPreLoad.class)
    private inAppBrowserPreLoad inAppBrowserPreLoad;
    private String productId;
    private String publisherId;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum inAppBrowserPreLoad {
        DISABLED,
        CONTENT,
        FULL
    }

    public AdInformationOverrides c() {
        return this.adInfoOverrides;
    }

    public List<AdDetails> d() {
        return this.adsDetails;
    }
}
