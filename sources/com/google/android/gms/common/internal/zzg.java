package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.zzsz;

/* loaded from: classes.dex */
public final class zzg {
    private static final SimpleArrayMap<String, String> DO = new SimpleArrayMap<>();

    public static String zzcb(Context context) {
        String str = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return zzsz.zzco(context).zzik(context.getPackageName()).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return context.getPackageName();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b A[RETURN] */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String zzg(android.content.Context r3, int r4) {
        /*
            android.content.res.Resources r0 = r3.getResources()
            r1 = 20
            if (r4 == r1) goto L81
            r1 = 0
            switch(r4) {
                case 1: goto L7a;
                case 2: goto L73;
                case 3: goto L6c;
                case 4: goto L6b;
                case 5: goto L5d;
                case 6: goto L6b;
                case 7: goto L4f;
                case 8: goto L4a;
                case 9: goto L45;
                case 10: goto L40;
                case 11: goto L3b;
                default: goto Lc;
            }
        Lc:
            switch(r4) {
                case 16: goto L36;
                case 17: goto L28;
                case 18: goto L6b;
                default: goto Lf;
            }
        Lf:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r2 = 33
            r0.<init>(r2)
            java.lang.String r2 = "Unexpected error code "
            r0.append(r2)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
        L24:
            android.util.Log.e(r3, r4)
            return r1
        L28:
            java.lang.String r4 = "GoogleApiAvailability"
            java.lang.String r0 = "The specified account could not be signed in."
            android.util.Log.e(r4, r0)
            java.lang.String r4 = "common_google_play_services_sign_in_failed_title"
            java.lang.String r3 = zzu(r3, r4)
            return r3
        L36:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r4 = "One of the API components you attempted to connect to is not available."
            goto L24
        L3b:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r4 = "The application is not licensed to the user."
            goto L24
        L40:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r4 = "Developer error occurred. Please see logs for detailed information"
            goto L24
        L45:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r4 = "Google Play services is invalid. Cannot recover."
            goto L24
        L4a:
            java.lang.String r3 = "GoogleApiAvailability"
            java.lang.String r4 = "Internal error occurred. Please see logs for detailed information"
            goto L24
        L4f:
            java.lang.String r4 = "GoogleApiAvailability"
            java.lang.String r0 = "Network error occurred. Please retry request later."
            android.util.Log.e(r4, r0)
            java.lang.String r4 = "common_google_play_services_network_error_title"
            java.lang.String r3 = zzu(r3, r4)
            return r3
        L5d:
            java.lang.String r4 = "GoogleApiAvailability"
            java.lang.String r0 = "An invalid account was specified when connecting. Please provide a valid account."
            android.util.Log.e(r4, r0)
            java.lang.String r4 = "common_google_play_services_invalid_account_title"
            java.lang.String r3 = zzu(r3, r4)
            return r3
        L6b:
            return r1
        L6c:
            int r3 = com.google.android.gms.R.string.common_google_play_services_enable_title
            java.lang.String r3 = r0.getString(r3)
            return r3
        L73:
            int r3 = com.google.android.gms.R.string.common_google_play_services_update_title
            java.lang.String r3 = r0.getString(r3)
            return r3
        L7a:
            int r3 = com.google.android.gms.R.string.common_google_play_services_install_title
            java.lang.String r3 = r0.getString(r3)
            return r3
        L81:
            java.lang.String r4 = "GoogleApiAvailability"
            java.lang.String r0 = "The current user profile is restricted and could not use authenticated features."
            android.util.Log.e(r4, r0)
            java.lang.String r4 = "common_google_play_services_restricted_profile_title"
            java.lang.String r3 = zzu(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzg.zzg(android.content.Context, int):java.lang.String");
    }

    private static String zzg(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zzu = zzu(context, str);
        if (zzu == null) {
            zzu = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zzu, str2);
    }

    @NonNull
    public static String zzh(Context context, int i) {
        String zzu = i == 6 ? zzu(context, "common_google_play_services_resolution_required_title") : zzg(context, i);
        return zzu == null ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : zzu;
    }

    @NonNull
    public static String zzi(Context context, int i) {
        Resources resources = context.getResources();
        String zzcb = zzcb(context);
        if (i == 5) {
            return zzg(context, "common_google_play_services_invalid_account_text", zzcb);
        }
        if (i == 7) {
            return zzg(context, "common_google_play_services_network_error_text", zzcb);
        }
        if (i == 9) {
            return resources.getString(R.string.common_google_play_services_unsupported_text, zzcb);
        }
        if (i == 20) {
            return zzg(context, "common_google_play_services_restricted_profile_text", zzcb);
        }
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_text, zzcb);
            case 2:
                return com.google.android.gms.common.util.zzi.zzci(context) ? resources.getString(R.string.common_google_play_services_wear_update_text) : resources.getString(R.string.common_google_play_services_update_text, zzcb);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_text, zzcb);
            default:
                switch (i) {
                    case 16:
                        return zzg(context, "common_google_play_services_api_unavailable_text", zzcb);
                    case 17:
                        return zzg(context, "common_google_play_services_sign_in_failed_text", zzcb);
                    case 18:
                        return resources.getString(R.string.common_google_play_services_updating_text, zzcb);
                    default:
                        return resources.getString(R.string.common_google_play_services_unknown_issue, zzcb);
                }
        }
    }

    @NonNull
    public static String zzj(Context context, int i) {
        return i == 6 ? zzg(context, "common_google_play_services_resolution_required_text", zzcb(context)) : zzi(context, i);
    }

    @NonNull
    public static String zzk(Context context, int i) {
        int i2;
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                i2 = R.string.common_google_play_services_install_button;
                break;
            case 2:
                i2 = R.string.common_google_play_services_update_button;
                break;
            case 3:
                i2 = R.string.common_google_play_services_enable_button;
                break;
            default:
                i2 = android.R.string.ok;
                break;
        }
        return resources.getString(i2);
    }

    @Nullable
    private static String zzu(Context context, String str) {
        synchronized (DO) {
            String str2 = DO.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String valueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf.length() != 0 ? "Missing resource: ".concat(valueOf) : new String("Missing resource: "));
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (!TextUtils.isEmpty(string)) {
                DO.put(str, string);
                return string;
            }
            String valueOf2 = String.valueOf(str);
            Log.w("GoogleApiAvailability", valueOf2.length() != 0 ? "Got empty resource: ".concat(valueOf2) : new String("Got empty resource: "));
            return null;
        }
    }
}
