package com.iab.omid.library.startapp.b;

import android.content.Context;
import android.os.Handler;
import com.iab.omid.library.startapp.b.b;
import com.iab.omid.library.startapp.walking.TreeWalker;
import java.util.Iterator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class f implements com.iab.omid.library.startapp.a.c, b.a {
    private static f a;
    private float b = 0.0f;
    private final com.iab.omid.library.startapp.a.e c;
    private final com.iab.omid.library.startapp.a.b d;
    private com.iab.omid.library.startapp.a.d e;
    private a f;

    public f(com.iab.omid.library.startapp.a.e eVar, com.iab.omid.library.startapp.a.b bVar) {
        this.c = eVar;
        this.d = bVar;
    }

    public static f a() {
        if (a == null) {
            a = new f(new com.iab.omid.library.startapp.a.e(), new com.iab.omid.library.startapp.a.b());
        }
        return a;
    }

    private a e() {
        if (this.f == null) {
            this.f = a.a();
        }
        return this.f;
    }

    @Override // com.iab.omid.library.startapp.a.c
    public void a(float f) {
        this.b = f;
        Iterator<com.iab.omid.library.startapp.adsession.a> it = e().c().iterator();
        while (it.hasNext()) {
            it.next().getAdSessionStatePublisher().a(f);
        }
    }

    public void a(Context context) {
        this.e = this.c.a(new Handler(), context, this.d.a(), this);
    }

    @Override // com.iab.omid.library.startapp.b.b.a
    public void a(boolean z) {
        if (z) {
            TreeWalker.getInstance().a();
        } else {
            TreeWalker.getInstance().c();
        }
    }

    public void b() {
        b.a().a(this);
        b.a().b();
        TreeWalker.getInstance().a();
        this.e.a();
    }

    public void c() {
        TreeWalker.getInstance().b();
        b.a().c();
        this.e.b();
    }

    public float d() {
        return this.b;
    }
}
