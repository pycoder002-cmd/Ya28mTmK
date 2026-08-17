package com.startapp.mediation.admob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventNative;
import com.google.android.gms.ads.mediation.customevent.CustomEventNativeListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerBase;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.Mrec;
import com.startapp.sdk.ads.banner.banner3d.Banner3D;
import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.ads.nativead.NativeAdDisplayListener;
import com.startapp.sdk.ads.nativead.NativeAdInterface;
import com.startapp.sdk.ads.nativead.NativeAdPreferences;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class StartappAdapter extends Adapter implements CustomEventInterstitial, CustomEventBanner, MediationRewardedAd, CustomEventNative {
    private static final String LOG_TAG = "StartappAdapter";
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private FrameLayout bannerContainer;
    private StartAppAd interstitial;
    private CustomEventInterstitialListener interstitialListener;
    private StartAppAd rewarded;
    private MediationRewardedAdCallback rewardedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.startapp.mediation.admob.StartappAdapter$8, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$startapp$mediation$admob$StartappAdapter$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$startapp$mediation$admob$StartappAdapter$Mode = iArr;
            try {
                iArr[Mode.OVERLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$startapp$mediation$admob$StartappAdapter$Mode[Mode.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$startapp$mediation$admob$StartappAdapter$Mode[Mode.OFFERWALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static class Extras {
        private static final String AD_TAG = "adTag";
        private static final String APP_ID = "startappAppId";
        private static final String INTERSTITIAL_MODE = "interstitialMode";
        private static final String IS_3D_BANNER = "is3DBanner";
        private static final String MIN_CPM = "minCPM";
        private static final String MUTE_VIDEO = "muteVideo";
        private static final String NATIVE_IMAGE_SIZE = "nativeImageSize";
        private static final String NATIVE_SECONDARY_IMAGE_SIZE = "nativeSecondaryImageSize";
        private StartAppAd.AdMode adMode;
        private final AdPreferences adPreferences;
        private String appId;
        private boolean is3DBanner;

        /* loaded from: classes3.dex */
        public static class Builder {
            final Bundle extras = new Bundle();

            public Builder enable3DBanner() {
                this.extras.putBoolean(Extras.IS_3D_BANNER, true);
                return this;
            }

            public Builder muteVideo() {
                this.extras.putBoolean(Extras.MUTE_VIDEO, true);
                return this;
            }

            public Builder setAdTag(String adTag) {
                this.extras.putString(Extras.AD_TAG, adTag);
                return this;
            }

            public Builder setInterstitialMode(Mode interstitialMode) {
                this.extras.putSerializable(Extras.INTERSTITIAL_MODE, interstitialMode);
                return this;
            }

            public Builder setMinCPM(double cpm) {
                this.extras.putDouble(Extras.MIN_CPM, cpm);
                return this;
            }

            public Builder setNativeImageSize(Size size) {
                this.extras.putSerializable(Extras.NATIVE_IMAGE_SIZE, size);
                return this;
            }

            public Builder setNativeSecondaryImageSize(Size size) {
                this.extras.putSerializable(Extras.NATIVE_SECONDARY_IMAGE_SIZE, size);
                return this;
            }

            public Bundle toBundle() {
                return this.extras;
            }
        }

        Extras(MediationAdRequest mediationAdRequest, Bundle customEventExtras, String serverParameter) {
            AdPreferences makeAdPreferences = makeAdPreferences(customEventExtras, serverParameter, false, null);
            this.adPreferences = makeAdPreferences;
            setKeywords(makeAdPreferences, mediationAdRequest);
            setLocation(makeAdPreferences, mediationAdRequest);
        }

        Extras(MediationRewardedAdConfiguration configuration) {
            AdPreferences makeAdPreferences = makeAdPreferences(configuration.getMediationExtras(), configuration.getServerParameters().getString("parameter"), false, null);
            this.adPreferences = makeAdPreferences;
            if (configuration.getLocation() != null) {
                makeAdPreferences.setLongitude(configuration.getLocation().getLongitude());
                makeAdPreferences.setLatitude(configuration.getLocation().getLatitude());
            }
        }

        Extras(NativeMediationAdRequest mediationAdRequest, Bundle customEventExtras, String serverParameter) {
            AdPreferences makeAdPreferences = makeAdPreferences(customEventExtras, serverParameter, true, mediationAdRequest.getNativeAdOptions());
            this.adPreferences = makeAdPreferences;
            setKeywords(makeAdPreferences, mediationAdRequest);
            setLocation(makeAdPreferences, mediationAdRequest);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(7:(5:(14:67|68|69|70|71|72|(10:112|113|114|115|75|76|77|(3:79|(2:81|(2:83|(1:100))(2:101|(1:103)))(2:104|(1:106))|97)(1:107)|92|(1:94))|74|75|76|77|(0)(0)|92|(0))|77|(0)(0)|92|(0))|71|72|(0)|74|75|76) */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x017e, code lost:
        
            if (r6 == 1) goto L92;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0181, code lost:
        
            if (r6 == 2) goto L91;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0184, code lost:
        
            r20.adMode = com.startapp.sdk.adsbase.StartAppAd.AdMode.OFFERWALL;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0189, code lost:
        
            r20.adMode = com.startapp.sdk.adsbase.StartAppAd.AdMode.VIDEO;
         */
        /* JADX WARN: Removed duplicated region for block: B:107:0x0194  */
        /* JADX WARN: Removed duplicated region for block: B:112:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x01d3  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x01e8  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x01ed  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x01da  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0145 A[Catch: JSONException -> 0x01b2, TryCatch #0 {JSONException -> 0x01b2, blocks: (B:76:0x013f, B:79:0x0145, B:98:0x015e, B:101:0x0168, B:104:0x0172), top: B:75:0x013f }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x019b A[Catch: JSONException -> 0x01b3, TRY_LEAVE, TryCatch #2 {JSONException -> 0x01b3, blocks: (B:91:0x0184, B:92:0x0195, B:94:0x019b, B:96:0x0189, B:97:0x018e), top: B:77:0x0143 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private com.startapp.sdk.adsbase.model.AdPreferences makeAdPreferences(android.os.Bundle r21, java.lang.String r22, boolean r23, com.google.android.gms.ads.formats.NativeAdOptions r24) {
            /*
                Method dump skipped, instructions count: 526
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.mediation.admob.StartappAdapter.Extras.makeAdPreferences(android.os.Bundle, java.lang.String, boolean, com.google.android.gms.ads.formats.NativeAdOptions):com.startapp.sdk.adsbase.model.AdPreferences");
        }

        private static void setKeywords(AdPreferences prefs, MediationAdRequest request) {
            Set keywords = request.getKeywords();
            if (keywords == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = keywords.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                sb.append(",");
            }
            prefs.setKeywords(sb.substring(0, sb.length() - 1));
        }

        private static void setLocation(AdPreferences prefs, MediationAdRequest request) {
            if (request.getLocation() == null) {
                return;
            }
            prefs.setLongitude(request.getLocation().getLongitude());
            prefs.setLatitude(request.getLocation().getLatitude());
        }

        StartAppAd.AdMode getAdMode() {
            return this.adMode;
        }

        AdPreferences getAdPreferences() {
            return this.adPreferences;
        }

        public String getAppId() {
            return this.appId;
        }

        boolean is3DBanner() {
            return this.is3DBanner;
        }
    }

    /* loaded from: classes3.dex */
    private static class MappedImage extends NativeAd.Image {
        private final Bitmap bitmap;
        private final Context context;
        private final Uri uri;

        MappedImage(Context context, Uri uri, Bitmap bitmap) {
            this.context = context;
            this.uri = uri;
            this.bitmap = bitmap;
        }

        public Drawable getDrawable() {
            if (this.bitmap == null) {
                return null;
            }
            return new BitmapDrawable(this.context.getResources(), this.bitmap);
        }

        public double getScale() {
            return 1.0d;
        }

        public Uri getUri() {
            return this.uri;
        }
    }

    /* loaded from: classes3.dex */
    public enum Mode {
        OFFERWALL,
        VIDEO,
        OVERLAY
    }

    /* loaded from: classes3.dex */
    private static class NativeMapper extends UnifiedNativeAdMapper {
        private final NativeAdDetails details;
        private final WeakReference<CustomEventNativeListener> listener;

        NativeMapper(Context context, NativeAdDetails details, boolean isContentAd, CustomEventNativeListener listener) {
            Uri parse;
            Uri parse2;
            this.details = details;
            this.listener = new WeakReference<>(listener);
            setHasVideoContent(false);
            setHeadline(details.getTitle());
            setBody(details.getDescription());
            setCallToAction(details.getCallToAction());
            setStarRating(Double.valueOf(details.getRating()));
            if (!isContentAd) {
                if (!TextUtils.isEmpty(details.getImageUrl()) && (parse2 = Uri.parse(details.getImageUrl())) != null) {
                    setImages(Collections.singletonList(new MappedImage(context, parse2, details.getImageBitmap())));
                }
                if (!TextUtils.isEmpty(details.getSecondaryImageUrl()) && (parse = Uri.parse(details.getSecondaryImageUrl())) != null) {
                    setIcon(new MappedImage(context, parse, details.getSecondaryImageBitmap()));
                }
            }
            setOverrideClickHandling(true);
            setOverrideImpressionRecording(true);
        }

        public void trackViews(View containerView, Map<String, View> clickableAssetViews, Map<String, View> nonclickableAssetViews) {
            this.details.registerViewForInteraction(containerView, new ArrayList(clickableAssetViews.values()), new NativeAdDisplayListener() { // from class: com.startapp.mediation.admob.StartappAdapter.NativeMapper.1
                @Override // com.startapp.sdk.ads.nativead.NativeAdDisplayListener
                public void adClicked(NativeAdInterface nativeAdInterface) {
                    CustomEventNativeListener customEventNativeListener = (CustomEventNativeListener) NativeMapper.this.listener.get();
                    if (customEventNativeListener != null) {
                        customEventNativeListener.onAdClicked();
                        customEventNativeListener.onAdOpened();
                    }
                }

                @Override // com.startapp.sdk.ads.nativead.NativeAdDisplayListener
                public void adDisplayed(NativeAdInterface nativeAdInterface) {
                    CustomEventNativeListener customEventNativeListener = (CustomEventNativeListener) NativeMapper.this.listener.get();
                    if (customEventNativeListener != null) {
                        customEventNativeListener.onAdImpression();
                    }
                }

                @Override // com.startapp.sdk.ads.nativead.NativeAdDisplayListener
                public void adHidden(NativeAdInterface nativeAdInterface) {
                    CustomEventNativeListener customEventNativeListener = (CustomEventNativeListener) NativeMapper.this.listener.get();
                    if (customEventNativeListener != null) {
                        customEventNativeListener.onAdClosed();
                    }
                }

                @Override // com.startapp.sdk.ads.nativead.NativeAdDisplayListener
                public void adNotDisplayed(NativeAdInterface nativeAdInterface) {
                }
            });
        }

        public void untrackView(View view) {
            this.details.unregisterView();
        }
    }

    /* loaded from: classes3.dex */
    public enum Size {
        SIZE72X72,
        SIZE100X100,
        SIZE150X150,
        SIZE340X340,
        SIZE1200X628
    }

    /* loaded from: classes3.dex */
    public static class StartappRewardItem implements RewardItem {
        public int getAmount() {
            return 1;
        }

        public String getType() {
            return "";
        }
    }

    private static void initializeIfNecessary(Context context, String appId, boolean testAds) {
        if (TextUtils.isEmpty(appId)) {
            Log.e("StartAppSDK", "App ID not found\n+-----------------------------------------------------------------------+\n|                S   T   A   R   T   A   P   P                          |\n| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |\n| AdMob Mediation is configured wrong, App ID not found                 |\n| Put your App ID as Parameter for Custom Event:                        |\n|                                                                       |\n|              { startappAppId : 'YOUR_APP_ID' }                        |\n|                                                                       |\n| https://support.start.io/hc/en-us/articles/360005100893-AdMob-Adapter |\n+-----------------------------------------------------------------------+\n");
            return;
        }
        if (isInitialized.getAndSet(true)) {
            return;
        }
        StartAppSDK.setTestAdsEnabled(testAds);
        StartAppAd.disableSplash();
        StartAppAd.enableConsent(context, false);
        StartAppSDK.addWrapper(context, "AdMob", BuildConfig.VERSION_NAME);
        StartAppSDK.init(context, appId, false);
    }

    private static String leadingDigits(String input) {
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char charAt = input.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return input.substring(0, i);
            }
        }
        return input;
    }

    private BannerBase loadBanner(Context context, String serverParameter, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle customEventExtras, BannerListener loadListener) {
        Extras extras = new Extras(mediationAdRequest, customEventExtras, serverParameter);
        initializeIfNecessary(context, extras.getAppId(), mediationAdRequest.isTesting());
        BannerBase mrec = adSize.equals(AdSize.MEDIUM_RECTANGLE) ? new Mrec(context, extras.getAdPreferences(), loadListener) : extras.is3DBanner() ? new Banner3D(context, extras.getAdPreferences(), loadListener) : new Banner(context, extras.getAdPreferences(), loadListener);
        mrec.loadAd(adSize.getWidth(), adSize.getHeight());
        return mrec;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AdError messageToError(String message) {
        boolean z = message != null && (message.contains("204") || message.contains("Empty Response"));
        return new AdError(z ? 9 : 0, z ? "No Fill" : "Internal error", "io.start");
    }

    private static void removeFromParent(View view) {
        if (view == null || view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }

    public VersionInfo getSDKVersionInfo() {
        String str;
        try {
            str = (String) StartAppSDK.class.getDeclaredMethod("getVersion", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable unused) {
            str = null;
        }
        if (str == null) {
            try {
                str = (String) Class.forName("com.startapp.sdk.GeneratedConstants").getDeclaredField("INAPP_VERSION").get(null);
            } catch (Throwable unused2) {
            }
        }
        if (str == null) {
            return new VersionInfo(0, 0, 1);
        }
        String[] split = str.split("\\.");
        if (split.length < 3) {
            return new VersionInfo(0, 0, 1);
        }
        try {
            return new VersionInfo(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(leadingDigits(split[2])));
        } catch (Throwable unused3) {
            return new VersionInfo(0, 0, 1);
        }
    }

    public VersionInfo getVersionInfo() {
        String[] split = BuildConfig.VERSION_NAME.split("\\.");
        if (split.length < 3) {
            return new VersionInfo(0, 0, 1);
        }
        try {
            return new VersionInfo(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        } catch (Exception unused) {
            return new VersionInfo(0, 0, 1);
        }
    }

    public void initialize(Context context, InitializationCompleteCallback completeCallback, List<MediationConfiguration> list) {
        completeCallback.onInitializationSucceeded();
    }

    public void loadRewardedAd(MediationRewardedAdConfiguration adConfiguration, final MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> loadCallback) {
        Context context = adConfiguration.getContext();
        Extras extras = new Extras(adConfiguration);
        initializeIfNecessary(context, extras.getAppId(), adConfiguration.isTestRequest());
        StartAppAd startAppAd = new StartAppAd(context);
        this.rewarded = startAppAd;
        startAppAd.setVideoListener(new VideoListener() { // from class: com.startapp.mediation.admob.StartappAdapter.4
            @Override // com.startapp.sdk.adsbase.adlisteners.VideoListener
            public void onVideoCompleted() {
                if (StartappAdapter.this.rewardedListener != null) {
                    StartappAdapter.this.rewardedListener.onVideoComplete();
                    StartappAdapter.this.rewardedListener.onUserEarnedReward(new StartappRewardItem());
                }
            }
        });
        this.rewarded.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, extras.getAdPreferences(), new AdEventListener() { // from class: com.startapp.mediation.admob.StartappAdapter.5
            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onFailedToReceiveAd(Ad ad) {
                String errorMessage = ad.getErrorMessage();
                Log.v(StartappAdapter.LOG_TAG, "ad loading failed: " + errorMessage);
                loadCallback.onFailure(StartappAdapter.messageToError(errorMessage));
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onReceiveAd(Ad ad) {
                StartappAdapter startappAdapter = StartappAdapter.this;
                startappAdapter.rewardedListener = (MediationRewardedAdCallback) loadCallback.onSuccess(startappAdapter);
            }
        });
    }

    public void onDestroy() {
        removeFromParent(this.bannerContainer);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void requestBannerAd(Context context, final CustomEventBannerListener listener, String serverParameter, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        this.bannerContainer = new FrameLayout(context);
        BannerBase loadBanner = loadBanner(context, serverParameter, adSize, mediationAdRequest, customEventExtras, new BannerListener() { // from class: com.startapp.mediation.admob.StartappAdapter.3
            @Override // com.startapp.sdk.ads.banner.BannerListener
            public void onClick(View view) {
                listener.onAdClicked();
                listener.onAdOpened();
            }

            @Override // com.startapp.sdk.ads.banner.BannerListener
            public void onFailedToReceiveAd(View view) {
                listener.onAdFailedToLoad(3);
            }

            @Override // com.startapp.sdk.ads.banner.BannerListener
            public void onImpression(View view) {
            }

            @Override // com.startapp.sdk.ads.banner.BannerListener
            public void onReceiveAd(View view) {
                listener.onAdLoaded(StartappAdapter.this.bannerContainer);
            }
        });
        this.bannerContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.bannerContainer.addView(loadBanner, new FrameLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context), 17));
    }

    public void requestInterstitialAd(Context context, final CustomEventInterstitialListener listener, String serverParameter, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        Extras extras = new Extras(mediationAdRequest, customEventExtras, serverParameter);
        initializeIfNecessary(context, extras.getAppId(), mediationAdRequest.isTesting());
        this.interstitialListener = listener;
        this.interstitial = new StartAppAd(context);
        AdEventListener adEventListener = new AdEventListener() { // from class: com.startapp.mediation.admob.StartappAdapter.1
            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onFailedToReceiveAd(Ad ad) {
                String errorMessage = ad.getErrorMessage();
                Log.v(StartappAdapter.LOG_TAG, "ad loading failed: " + errorMessage);
                listener.onAdFailedToLoad(StartappAdapter.messageToError(errorMessage));
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onReceiveAd(Ad ad) {
                listener.onAdLoaded();
            }
        };
        if (extras.getAdMode() == null) {
            this.interstitial.loadAd(extras.getAdPreferences(), adEventListener);
        } else {
            this.interstitial.loadAd(extras.getAdMode(), extras.getAdPreferences(), adEventListener);
        }
    }

    public void requestNativeAd(final Context context, final CustomEventNativeListener listener, String serverParameter, NativeMediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        Extras extras = new Extras(mediationAdRequest, customEventExtras, serverParameter);
        initializeIfNecessary(context, extras.getAppId(), mediationAdRequest.isTesting());
        final StartAppNativeAd startAppNativeAd = new StartAppNativeAd(context);
        final NativeAdPreferences nativeAdPreferences = (NativeAdPreferences) extras.getAdPreferences();
        startAppNativeAd.loadAd(nativeAdPreferences, new AdEventListener() { // from class: com.startapp.mediation.admob.StartappAdapter.7
            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onFailedToReceiveAd(Ad ad) {
                String errorMessage = ad.getErrorMessage();
                Log.v(StartappAdapter.LOG_TAG, "ad loading failed: " + errorMessage);
                listener.onAdFailedToLoad(StartappAdapter.messageToError(errorMessage));
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
            public void onReceiveAd(Ad ad) {
                ArrayList<NativeAdDetails> nativeAds = startAppNativeAd.getNativeAds();
                if (nativeAds != null && !nativeAds.isEmpty()) {
                    listener.onAdLoaded(new NativeMapper(context, nativeAds.get(0), nativeAdPreferences.isContentAd(), listener));
                } else {
                    Log.v(StartappAdapter.LOG_TAG, "ad loading failed: no fill");
                    listener.onAdFailedToLoad(StartappAdapter.messageToError("204"));
                }
            }
        });
    }

    public void showAd(Context context) {
        StartAppAd startAppAd = this.rewarded;
        if (startAppAd == null) {
            return;
        }
        startAppAd.showAd(new AdDisplayListener() { // from class: com.startapp.mediation.admob.StartappAdapter.6
            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adClicked(Ad ad) {
                if (StartappAdapter.this.rewardedListener != null) {
                    StartappAdapter.this.rewardedListener.reportAdClicked();
                }
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adDisplayed(Ad ad) {
                if (StartappAdapter.this.rewardedListener != null) {
                    StartappAdapter.this.rewardedListener.onAdOpened();
                    StartappAdapter.this.rewardedListener.onVideoStart();
                    StartappAdapter.this.rewardedListener.reportAdImpression();
                }
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adHidden(Ad ad) {
                if (StartappAdapter.this.rewardedListener != null) {
                    StartappAdapter.this.rewardedListener.onAdClosed();
                }
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adNotDisplayed(Ad ad) {
                if (StartappAdapter.this.rewardedListener != null) {
                    String errorMessage = ad.getErrorMessage();
                    MediationRewardedAdCallback mediationRewardedAdCallback = StartappAdapter.this.rewardedListener;
                    if (errorMessage == null) {
                        errorMessage = "adNotDisplayed";
                    }
                    mediationRewardedAdCallback.onAdFailedToShow(new AdError(0, errorMessage, "io.start"));
                }
            }
        });
    }

    public void showInterstitial() {
        StartAppAd startAppAd = this.interstitial;
        if (startAppAd == null) {
            return;
        }
        startAppAd.showAd(new AdDisplayListener() { // from class: com.startapp.mediation.admob.StartappAdapter.2
            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adClicked(Ad ad) {
                if (StartappAdapter.this.interstitialListener == null) {
                    return;
                }
                StartappAdapter.this.interstitialListener.onAdClicked();
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adDisplayed(Ad ad) {
                if (StartappAdapter.this.interstitialListener == null) {
                    return;
                }
                StartappAdapter.this.interstitialListener.onAdOpened();
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adHidden(Ad ad) {
                if (StartappAdapter.this.interstitialListener == null) {
                    return;
                }
                StartappAdapter.this.interstitialListener.onAdClosed();
            }

            @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
            public void adNotDisplayed(Ad ad) {
            }
        });
    }
}
