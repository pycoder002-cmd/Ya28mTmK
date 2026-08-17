package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

/* loaded from: classes.dex */
public class zzx {
    public static String zza(Map<String, String> map) {
        return zzb(map, "ISO-8859-1");
    }

    public static zzb.zza zzb(zzi zziVar) {
        boolean z;
        boolean z2;
        long j;
        long j2;
        long j3;
        long j4;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zziVar.zzz;
        String str = map.get("Date");
        long zzg = str != null ? zzg(str) : 0L;
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            z = false;
            j = 0;
            j2 = 0;
            for (String str3 : str2.split(",")) {
                String trim = str3.trim();
                if (trim.equals(HeaderConstants.CACHE_CONTROL_NO_CACHE) || trim.equals(HeaderConstants.CACHE_CONTROL_NO_STORE)) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals(HeaderConstants.CACHE_CONTROL_MUST_REVALIDATE) || trim.equals(HeaderConstants.CACHE_CONTROL_PROXY_REVALIDATE)) {
                    z = true;
                }
            }
            z2 = true;
        } else {
            z = false;
            z2 = false;
            j = 0;
            j2 = 0;
        }
        String str4 = map.get("Expires");
        long zzg2 = str4 != null ? zzg(str4) : 0L;
        String str5 = map.get("Last-Modified");
        long zzg3 = str5 != null ? zzg(str5) : 0L;
        String str6 = map.get("ETag");
        if (z2) {
            long j5 = currentTimeMillis + (j * 1000);
            j3 = z ? j5 : j5 + (j2 * 1000);
            j4 = j5;
        } else if (zzg <= 0 || zzg2 < zzg) {
            j3 = 0;
            j4 = 0;
        } else {
            j4 = currentTimeMillis + (zzg2 - zzg);
            j3 = j4;
        }
        zzb.zza zzaVar = new zzb.zza();
        zzaVar.data = zziVar.data;
        zzaVar.zza = str6;
        zzaVar.zze = j4;
        zzaVar.zzd = j3;
        zzaVar.zzb = zzg;
        zzaVar.zzc = zzg3;
        zzaVar.zzf = map;
        return zzaVar;
    }

    public static String zzb(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static long zzg(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0L;
        }
    }
}
