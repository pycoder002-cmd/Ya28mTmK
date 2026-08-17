package com.iab.omid.library.startapp.walking;

import android.view.View;
import com.iab.omid.library.startapp.d.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a {
    private final HashMap<View, String> a = new HashMap<>();
    private final HashMap<View, C0049a> b = new HashMap<>();
    private final HashMap<String, View> c = new HashMap<>();
    private final HashSet<View> d = new HashSet<>();
    private final HashSet<String> e = new HashSet<>();
    private final HashSet<String> f = new HashSet<>();
    private final HashMap<String, String> g = new HashMap<>();
    private boolean h;

    /* compiled from: StartAppSDK */
    /* renamed from: com.iab.omid.library.startapp.walking.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public static class C0049a {
        private final com.iab.omid.library.startapp.b.c a;
        private final ArrayList<String> b = new ArrayList<>();

        public C0049a(com.iab.omid.library.startapp.b.c cVar, String str) {
            this.a = cVar;
            a(str);
        }

        public com.iab.omid.library.startapp.b.c a() {
            return this.a;
        }

        public void a(String str) {
            this.b.add(str);
        }

        public ArrayList<String> b() {
            return this.b;
        }
    }

    private void a(com.iab.omid.library.startapp.adsession.a aVar) {
        Iterator<com.iab.omid.library.startapp.b.c> it = aVar.a().iterator();
        while (it.hasNext()) {
            a(it.next(), aVar);
        }
    }

    private void a(com.iab.omid.library.startapp.b.c cVar, com.iab.omid.library.startapp.adsession.a aVar) {
        View view = cVar.a().get();
        if (view == null) {
            return;
        }
        C0049a c0049a = this.b.get(view);
        if (c0049a != null) {
            c0049a.a(aVar.getAdSessionId());
        } else {
            this.b.put(view, new C0049a(cVar, aVar.getAdSessionId()));
        }
    }

    private String d(View view) {
        if (!view.hasWindowFocus()) {
            return "noWindowFocus";
        }
        HashSet hashSet = new HashSet();
        while (view != null) {
            String e = f.e(view);
            if (e != null) {
                return e;
            }
            hashSet.add(view);
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.d.addAll(hashSet);
        return null;
    }

    public String a(View view) {
        if (this.a.size() == 0) {
            return null;
        }
        String str = this.a.get(view);
        if (str != null) {
            this.a.remove(view);
        }
        return str;
    }

    public String a(String str) {
        return this.g.get(str);
    }

    public HashSet<String> a() {
        return this.e;
    }

    public View b(String str) {
        return this.c.get(str);
    }

    public C0049a b(View view) {
        C0049a c0049a = this.b.get(view);
        if (c0049a != null) {
            this.b.remove(view);
        }
        return c0049a;
    }

    public HashSet<String> b() {
        return this.f;
    }

    public c c(View view) {
        return this.d.contains(view) ? c.PARENT_VIEW : this.h ? c.OBSTRUCTION_VIEW : c.UNDERLYING_VIEW;
    }

    public void c() {
        com.iab.omid.library.startapp.b.a a = com.iab.omid.library.startapp.b.a.a();
        if (a != null) {
            for (com.iab.omid.library.startapp.adsession.a aVar : a.c()) {
                View d = aVar.d();
                if (aVar.e()) {
                    String adSessionId = aVar.getAdSessionId();
                    if (d != null) {
                        String d2 = d(d);
                        if (d2 == null) {
                            this.e.add(adSessionId);
                            this.a.put(d, adSessionId);
                            a(aVar);
                        } else {
                            this.f.add(adSessionId);
                            this.c.put(adSessionId, d);
                            this.g.put(adSessionId, d2);
                        }
                    } else {
                        this.f.add(adSessionId);
                        this.g.put(adSessionId, "noAdView");
                    }
                }
            }
        }
    }

    public void d() {
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.h = false;
    }

    public void e() {
        this.h = true;
    }
}
