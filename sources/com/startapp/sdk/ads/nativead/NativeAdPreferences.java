package com.startapp.sdk.ads.nativead;

import com.startapp.sdk.adsbase.model.AdPreferences;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NativeAdPreferences extends AdPreferences {
    private static final long serialVersionUID = 1;
    private NativeAdBitmapSize bitmapSize;
    private int adsNumber = 1;
    private boolean autoBitmapDownload = false;
    private int primaryImage = -1;
    private int moreImage = -1;
    private boolean isContentAd = false;
    private boolean useSimpleToken = true;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum NativeAdBitmapSize {
        SIZE72X72(72, 72),
        SIZE100X100(100, 100),
        SIZE150X150(Opcodes.FCMPG, Opcodes.FCMPG),
        SIZE340X340(340, 340);

        public int height;
        public int width;

        NativeAdBitmapSize(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public int getAdsNumber() {
        return this.adsNumber;
    }

    public NativeAdBitmapSize getImageSize() {
        return this.bitmapSize;
    }

    public int getPrimaryImageSize() {
        return this.primaryImage;
    }

    public int getSecondaryImageSize() {
        return this.moreImage;
    }

    public boolean isAutoBitmapDownload() {
        return this.autoBitmapDownload;
    }

    public boolean isContentAd() {
        return this.isContentAd;
    }

    @Override // com.startapp.sdk.adsbase.model.AdPreferences
    public boolean isSimpleToken() {
        return this.useSimpleToken;
    }

    public NativeAdPreferences setAdsNumber(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Ads Number must be >= 1");
        }
        this.adsNumber = i;
        return this;
    }

    public NativeAdPreferences setAutoBitmapDownload(boolean z) {
        this.autoBitmapDownload = z;
        return this;
    }

    public NativeAdPreferences setContentAd(boolean z) {
        this.isContentAd = z;
        return this;
    }

    public NativeAdPreferences setImageSize(NativeAdBitmapSize nativeAdBitmapSize) {
        this.bitmapSize = nativeAdBitmapSize;
        return this;
    }

    public NativeAdPreferences setPrimaryImageSize(int i) {
        this.primaryImage = i;
        return this;
    }

    public NativeAdPreferences setSecondaryImageSize(int i) {
        this.moreImage = i;
        return this;
    }

    @Override // com.startapp.sdk.adsbase.model.AdPreferences
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== NativeAdConfig =====\n");
        stringBuffer.append("    adsNumber: [" + getAdsNumber() + "]\n");
        stringBuffer.append("    autoBitmapDownload: [" + isAutoBitmapDownload() + "]\n");
        stringBuffer.append("    useSimpleToken: [" + isSimpleToken() + "]\n");
        stringBuffer.append("===== End NativeAdConfig =====");
        return stringBuffer.toString();
    }

    public NativeAdPreferences useSimpleToken(boolean z) {
        this.useSimpleToken = z;
        return this;
    }
}
