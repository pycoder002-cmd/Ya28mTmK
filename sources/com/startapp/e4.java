package com.startapp;

import android.content.Context;
import android.util.Base64;
import com.startapp.c4;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.cache.CachedVideoAd;
import com.startapp.w4;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class e4 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ String b;
    public final /* synthetic */ w4.b c;
    public final /* synthetic */ c4.a d;
    public final /* synthetic */ h4 e;

    public e4(h4 h4Var, Context context, String str, w4.b bVar, c4.a aVar) {
        this.e = h4Var;
        this.a = context;
        this.b = str;
        this.c = bVar;
        this.d = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        h4 h4Var = this.e;
        Context context = this.a;
        String str2 = this.b;
        w4.b bVar = this.c;
        c4.a aVar = this.d;
        if (h4Var.b == null) {
            LinkedList<CachedVideoAd> linkedList = (LinkedList) h9.a(context, "CachedAds", LinkedList.class);
            h4Var.b = linkedList;
            if (linkedList == null) {
                h4Var.b = new LinkedList<>();
            }
            if (h4Var.a(AdsCommonMetaData.h.G().b())) {
                h9.a(context, "CachedAds", h4Var.b);
            }
        }
        try {
            URL url = new URL(str2);
            String str3 = url.getHost() + url.getPath().replace("/", "_");
            try {
                String substring = str3.substring(0, str3.lastIndexOf(46));
                str = new String(Base64.encodeToString(MessageDigest.getInstance("MD5").digest(substring.getBytes()), 0)).replaceAll("[^a-zA-Z0-9]+", "_") + str3.substring(str3.lastIndexOf(46));
            } catch (NoSuchAlgorithmException e) {
                p7.a(context, e);
                str = str3;
            }
            new w4(context, url, str, new f4(h4Var, bVar, new CachedVideoAd(str), context), new g4(h4Var, aVar)).a();
        } catch (MalformedURLException e2) {
            if (bVar != null) {
                bVar.a(null);
            }
            p7.a(context, e2);
        }
    }
}
