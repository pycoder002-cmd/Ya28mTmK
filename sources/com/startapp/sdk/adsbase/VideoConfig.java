package com.startapp.sdk.adsbase;

import android.app.Activity;
import com.blankj.utilcode.constant.TimeConstants;
import com.google.firebase.appindexing.Indexable;
import com.startapp.aa;
import com.startapp.f;
import cz.msebera.android.httpclient.client.config.CookieSpecs;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoConfig implements Serializable {
    private static final long serialVersionUID = -7167816130382369376L;
    private int maxCachedVideos = 3;
    private long minAvailableStorageRequired = 20;
    private int rewardGrantPercentage = 100;
    private int videoErrorsThreshold = 2;

    @f(type = BackMode.class)
    private BackMode backMode = BackMode.DISABLED;
    private int nativePlayerProbability = 100;
    private int minTimeForCachingIndicator = 1;
    private int maxTimeForCachingIndicator = 10;
    private boolean videoFallback = false;
    private boolean progressive = false;
    private int progressiveBufferInPercentage = 20;
    private int progressiveInitialBufferInPercentage = 5;
    private int progressiveMinBufferToPlayFromCache = 30;
    private String soundMode = CookieSpecs.DEFAULT;
    private int maxVastLevels = 7;
    private int vastTimeout = Indexable.MAX_BYTE_SIZE;
    private int vastRetryTimeout = TimeConstants.MIN;
    private int maxVastCampaignExclude = 10;
    private int vastMediaPicker = 0;
    private int vastPreferredBitrate = 0;
    private long vastDefaultSkipIntervalMilli = 5000;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum BackMode {
        DISABLED,
        SKIP,
        CLOSE,
        BOTH
    }

    public BackMode a() {
        return this.backMode;
    }

    public int b() {
        return this.maxCachedVideos;
    }

    public long c() {
        return TimeUnit.SECONDS.toMillis(this.maxTimeForCachingIndicator);
    }

    public int d() {
        return this.maxVastCampaignExclude;
    }

    public int e() {
        return this.maxVastLevels;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || VideoConfig.class != obj.getClass()) {
            return false;
        }
        VideoConfig videoConfig = (VideoConfig) obj;
        return this.maxCachedVideos == videoConfig.maxCachedVideos && this.minAvailableStorageRequired == videoConfig.minAvailableStorageRequired && this.rewardGrantPercentage == videoConfig.rewardGrantPercentage && this.videoErrorsThreshold == videoConfig.videoErrorsThreshold && this.nativePlayerProbability == videoConfig.nativePlayerProbability && this.minTimeForCachingIndicator == videoConfig.minTimeForCachingIndicator && this.maxTimeForCachingIndicator == videoConfig.maxTimeForCachingIndicator && this.videoFallback == videoConfig.videoFallback && this.progressive == videoConfig.progressive && this.progressiveBufferInPercentage == videoConfig.progressiveBufferInPercentage && this.progressiveInitialBufferInPercentage == videoConfig.progressiveInitialBufferInPercentage && this.progressiveMinBufferToPlayFromCache == videoConfig.progressiveMinBufferToPlayFromCache && this.maxVastLevels == videoConfig.maxVastLevels && this.vastTimeout == videoConfig.vastTimeout && this.vastRetryTimeout == videoConfig.vastRetryTimeout && this.maxVastCampaignExclude == videoConfig.maxVastCampaignExclude && this.vastMediaPicker == videoConfig.vastMediaPicker && this.vastPreferredBitrate == videoConfig.vastPreferredBitrate && this.vastDefaultSkipIntervalMilli == videoConfig.vastDefaultSkipIntervalMilli && this.backMode == videoConfig.backMode && aa.a(this.soundMode, videoConfig.soundMode);
    }

    public long f() {
        return this.minAvailableStorageRequired;
    }

    public long g() {
        return TimeUnit.SECONDS.toMillis(this.minTimeForCachingIndicator);
    }

    public int h() {
        return this.progressiveBufferInPercentage;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.maxCachedVideos), Long.valueOf(this.minAvailableStorageRequired), Integer.valueOf(this.rewardGrantPercentage), Integer.valueOf(this.videoErrorsThreshold), this.backMode, Integer.valueOf(this.nativePlayerProbability), Integer.valueOf(this.minTimeForCachingIndicator), Integer.valueOf(this.maxTimeForCachingIndicator), Boolean.valueOf(this.videoFallback), Boolean.valueOf(this.progressive), Integer.valueOf(this.progressiveBufferInPercentage), Integer.valueOf(this.progressiveInitialBufferInPercentage), Integer.valueOf(this.progressiveMinBufferToPlayFromCache), this.soundMode, Integer.valueOf(this.maxVastLevels), Integer.valueOf(this.vastTimeout), Integer.valueOf(this.vastRetryTimeout), Integer.valueOf(this.maxVastCampaignExclude), Integer.valueOf(this.vastMediaPicker), Integer.valueOf(this.vastPreferredBitrate), Long.valueOf(this.vastDefaultSkipIntervalMilli)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public int i() {
        return this.progressiveInitialBufferInPercentage;
    }

    public int j() {
        return this.progressiveMinBufferToPlayFromCache;
    }

    public int k() {
        return this.rewardGrantPercentage;
    }

    public String l() {
        return this.soundMode;
    }

    public long m() {
        return this.vastDefaultSkipIntervalMilli;
    }

    public int n() {
        return this.vastRetryTimeout;
    }

    public int o() {
        return this.videoErrorsThreshold;
    }

    public boolean p() {
        return this.progressive;
    }

    public boolean q() {
        return this.videoFallback;
    }
}
