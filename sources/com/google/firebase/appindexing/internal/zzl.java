package com.google.firebase.appindexing.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.URLUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.firebase.appindexing.FirebaseAppIndexingException;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseAppIndexingTooManyArgumentsException;

/* loaded from: classes.dex */
public class zzl {
    public static FirebaseAppIndexingException zzb(@NonNull Status status, String str) {
        zzaa.zzy(status);
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null && !statusMessage.isEmpty()) {
            str = statusMessage;
        }
        switch (status.getStatusCode()) {
            case 17510:
                return new FirebaseAppIndexingInvalidArgumentException(str);
            case 17511:
                return new FirebaseAppIndexingTooManyArgumentsException(str);
            default:
                return new FirebaseAppIndexingException(str);
        }
    }

    public static void zzrs(@NonNull String str) throws FirebaseAppIndexingInvalidArgumentException {
        zzaa.zzy(str);
        Uri parse = Uri.parse(str);
        if (parse == null || !parse.isAbsolute()) {
            StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length());
            sb.append("Invalid String passed as url: '");
            sb.append(str);
            sb.append("'.");
            throw new FirebaseAppIndexingInvalidArgumentException(sb.toString());
        }
    }

    public static void zzrt(@NonNull String str) throws FirebaseAppIndexingInvalidArgumentException {
        zzaa.zzy(str);
        if (URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder(46 + String.valueOf(str).length());
        sb.append("Web url must be with http or https scheme: '");
        sb.append(str);
        sb.append("'.");
        throw new FirebaseAppIndexingInvalidArgumentException(sb.toString());
    }
}
