package com.iab.omid.library.startapp.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a {
    private static a a = new a();
    private final ArrayList<com.iab.omid.library.startapp.adsession.a> b = new ArrayList<>();
    private final ArrayList<com.iab.omid.library.startapp.adsession.a> c = new ArrayList<>();

    private a() {
    }

    public static a a() {
        return a;
    }

    public void a(com.iab.omid.library.startapp.adsession.a aVar) {
        this.b.add(aVar);
    }

    public Collection<com.iab.omid.library.startapp.adsession.a> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public void b(com.iab.omid.library.startapp.adsession.a aVar) {
        boolean d = d();
        this.c.add(aVar);
        if (d) {
            return;
        }
        f.a().b();
    }

    public Collection<com.iab.omid.library.startapp.adsession.a> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public void c(com.iab.omid.library.startapp.adsession.a aVar) {
        boolean d = d();
        this.b.remove(aVar);
        this.c.remove(aVar);
        if (!d || d()) {
            return;
        }
        f.a().c();
    }

    public boolean d() {
        return this.c.size() > 0;
    }
}
