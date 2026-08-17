package com.startapp.simple.bloomfilter.version;

import net.gotev.uploadservice.BuildConfig;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum BloomVersion {
    ZERO("0", 1, 720),
    THREE(BuildConfig.VERSION_NAME, 1, 720) { // from class: com.startapp.simple.bloomfilter.version.BloomVersion.1
    },
    FOUR("4", 3, 3500),
    FIVE("5", 3, 1000000);

    private final int numberOfHashes;
    private final int sizeOfBucket;
    private final String version;

    BloomVersion(String str, int i, int i2) {
        this.version = str;
        this.numberOfHashes = i;
        this.sizeOfBucket = i2;
    }

    public int a() {
        return this.numberOfHashes;
    }

    public int b() {
        return this.sizeOfBucket;
    }

    public String c() {
        return this.version;
    }
}
