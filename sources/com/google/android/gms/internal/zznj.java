package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appdatasearch.DocumentContents;
import com.google.android.gms.appdatasearch.DocumentSection;
import com.google.android.gms.appdatasearch.RegisterSectionInfo;
import com.google.android.gms.appdatasearch.UsageInfo;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.internal.zzvw;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zznj {
    public static DocumentSection zza(String str, zzvw.zzc zzcVar) {
        return new DocumentSection(zzasa.zzf(zzcVar), new RegisterSectionInfo.zza(str).zzaz(true).zzfs(str).zzfr("blob").zzahr());
    }

    public static UsageInfo zza(Action action, long j, String str, int i) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putAll(action.zzahu());
        Bundle bundle2 = bundle.getBundle("object");
        Uri parse = bundle2.containsKey(ConnectionModel.ID) ? Uri.parse(bundle2.getString(ConnectionModel.ID)) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("type");
        Intent zza = zznk.zza(str, Uri.parse(bundle2.getString(FileDownloadModel.URL)));
        DocumentContents.zza zza2 = UsageInfo.zza(zza, string, parse, string2, null);
        if (bundle.containsKey(".private:ssbContext")) {
            zza2.zza(DocumentSection.zzl(bundle.getByteArray(".private:ssbContext")));
            bundle.remove(".private:ssbContext");
        }
        if (bundle.containsKey(".private:accountName")) {
            zza2.zzb(new Account(bundle.getString(".private:accountName"), "com.google"));
            bundle.remove(".private:accountName");
        }
        boolean z = false;
        if (bundle.containsKey(".private:isContextOnly") && bundle.getBoolean(".private:isContextOnly")) {
            i2 = 4;
            bundle.remove(".private:isContextOnly");
        } else {
            i2 = 0;
        }
        if (bundle.containsKey(".private:isDeviceOnly")) {
            z = bundle.getBoolean(".private:isDeviceOnly", false);
            bundle.remove(".private:isDeviceOnly");
        }
        zza2.zza(zza(".private:action", zzj(bundle)));
        return new UsageInfo.zza().zza(UsageInfo.zza(str, zza)).zzaa(j).zzcq(i2).zza(zza2.zzaho()).zzbb(z).zzcr(i).zzahs();
    }

    private static zzvw.zzb zzb(String str, Bundle bundle) {
        zzvw.zzb zzbVar = new zzvw.zzb();
        zzbVar.name = str;
        zzbVar.ahG = new zzvw.zzd();
        zzbVar.ahG.ahL = zzj(bundle);
        return zzbVar;
    }

    private static zzvw.zzb zzh(String str, boolean z) {
        zzvw.zzb zzbVar = new zzvw.zzb();
        zzbVar.name = str;
        zzbVar.ahG = new zzvw.zzd();
        zzbVar.ahG.ahI = z;
        return zzbVar;
    }

    public static zzvw.zzc zzj(Bundle bundle) {
        zzvw.zzb zzu;
        ArrayList arrayList = new ArrayList();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                zzu = zzu(str, (String) obj);
            } else if (obj instanceof Bundle) {
                zzu = zzb(str, (Bundle) obj);
            } else {
                int i = 0;
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    int length = strArr.length;
                    while (i < length) {
                        String str2 = strArr[i];
                        if (str2 != null) {
                            arrayList.add(zzu(str, str2));
                        }
                        i++;
                    }
                } else if (obj instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj;
                    int length2 = bundleArr.length;
                    while (i < length2) {
                        Bundle bundle2 = bundleArr[i];
                        if (bundle2 != null) {
                            arrayList.add(zzb(str, bundle2));
                        }
                        i++;
                    }
                } else if (obj instanceof Boolean) {
                    zzu = zzh(str, ((Boolean) obj).booleanValue());
                } else {
                    String valueOf = String.valueOf(obj);
                    StringBuilder sb = new StringBuilder(19 + String.valueOf(valueOf).length());
                    sb.append("Unsupported value: ");
                    sb.append(valueOf);
                    Log.e("SearchIndex", sb.toString());
                }
            }
            arrayList.add(zzu);
        }
        zzvw.zzc zzcVar = new zzvw.zzc();
        if (bundle.containsKey("type")) {
            zzcVar.type = bundle.getString("type");
        }
        zzcVar.ahH = (zzvw.zzb[]) arrayList.toArray(new zzvw.zzb[arrayList.size()]);
        return zzcVar;
    }

    private static zzvw.zzb zzu(String str, String str2) {
        zzvw.zzb zzbVar = new zzvw.zzb();
        zzbVar.name = str;
        zzbVar.ahG = new zzvw.zzd();
        zzbVar.ahG.Fe = str2;
        return zzbVar;
    }
}
