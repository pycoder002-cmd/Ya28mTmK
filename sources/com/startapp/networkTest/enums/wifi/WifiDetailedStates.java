package com.startapp.networkTest.enums.wifi;

import android.net.NetworkInfo;
import android.os.Build;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum WifiDetailedStates {
    Unknown,
    IDLE,
    SCANNING,
    CONNECTING,
    AUTHENTICATING,
    OBTAINING_IPADDR,
    CONNECTED,
    SUSPENDED,
    DISCONNECTING,
    DISCONNECTED,
    FAILED,
    BLOCKED,
    VERIFYING_POOR_LINK,
    CAPTIVE_PORTAL_CHECK;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[NetworkInfo.DetailedState.values().length];
            a = iArr;
            try {
                iArr[NetworkInfo.DetailedState.AUTHENTICATING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NetworkInfo.DetailedState.BLOCKED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[NetworkInfo.DetailedState.CONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[NetworkInfo.DetailedState.CONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[NetworkInfo.DetailedState.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[NetworkInfo.DetailedState.DISCONNECTING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[NetworkInfo.DetailedState.FAILED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[NetworkInfo.DetailedState.IDLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[NetworkInfo.DetailedState.SCANNING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[NetworkInfo.DetailedState.SUSPENDED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public static WifiDetailedStates a(NetworkInfo.DetailedState detailedState) {
        switch (a.a[detailedState.ordinal()]) {
            case 1:
                return AUTHENTICATING;
            case 2:
                return BLOCKED;
            case 3:
                return CONNECTED;
            case 4:
                return CONNECTING;
            case 5:
                return DISCONNECTED;
            case 6:
                return DISCONNECTING;
            case 7:
                return FAILED;
            case 8:
                return IDLE;
            case 9:
                return OBTAINING_IPADDR;
            case 10:
                return SCANNING;
            case 11:
                return SUSPENDED;
            default:
                if (Build.VERSION.SDK_INT >= 17) {
                    WifiDetailedStates wifiDetailedStates = VERIFYING_POOR_LINK;
                    if (detailedState.equals(wifiDetailedStates)) {
                        return wifiDetailedStates;
                    }
                    WifiDetailedStates wifiDetailedStates2 = CAPTIVE_PORTAL_CHECK;
                    if (detailedState.equals(wifiDetailedStates2)) {
                        return wifiDetailedStates2;
                    }
                }
                return Unknown;
        }
    }
}
