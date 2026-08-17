package com.startapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class va implements CookieStore {
    public final CookieStore a;
    public final SharedPreferences b;

    public va(Context context) {
        HttpCookie httpCookie;
        p5 p5Var = new p5(context.getSharedPreferences("com.startapp.android.publish.CookiePrefsFile", 0));
        this.b = p5Var;
        this.a = new CookieManager().getCookieStore();
        String string = p5Var.getString("names", null);
        if (string != null) {
            for (String str : TextUtils.split(string, ";")) {
                String string2 = this.b.getString("cookie_" + str, null);
                if (string2 != null && (httpCookie = (HttpCookie) c.a(string2, HttpCookie.class)) != null) {
                    if (httpCookie.hasExpired()) {
                        b(httpCookie);
                        a();
                    } else if (httpCookie.getDomain() != null) {
                        this.a.add(URI.create(httpCookie.getDomain()), httpCookie);
                    }
                }
            }
        }
    }

    public final String a(HttpCookie httpCookie) {
        return httpCookie.getDomain() + "_" + httpCookie.getName();
    }

    public final void a() {
        SharedPreferences.Editor edit = this.b.edit();
        HashSet hashSet = new HashSet();
        Iterator<HttpCookie> it = this.a.getCookies().iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        edit.putString("names", TextUtils.join(";", hashSet));
        edit.apply();
    }

    @Override // java.net.CookieStore
    public void add(URI uri, HttpCookie httpCookie) {
        String a = a(httpCookie);
        this.a.add(uri, httpCookie);
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("cookie_" + a, String.valueOf(c.b(httpCookie)));
        edit.apply();
        a();
    }

    public final void b(HttpCookie httpCookie) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove("cookie_" + a(httpCookie));
        edit.apply();
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> get(URI uri) {
        return this.a.get(uri);
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> getCookies() {
        return this.a.getCookies();
    }

    @Override // java.net.CookieStore
    public List<URI> getURIs() {
        return this.a.getURIs();
    }

    @Override // java.net.CookieStore
    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (!this.a.remove(uri, httpCookie)) {
            return false;
        }
        b(httpCookie);
        a();
        return true;
    }

    @Override // java.net.CookieStore
    public boolean removeAll() {
        if (!this.a.removeAll()) {
            return false;
        }
        SharedPreferences.Editor edit = this.b.edit();
        edit.clear();
        edit.apply();
        a();
        return true;
    }
}
