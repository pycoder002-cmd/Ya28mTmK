package com.iab.omid.library.startapp.c;

import android.view.View;
import com.iab.omid.library.startapp.c.a;
import com.iab.omid.library.startapp.d.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c implements a {
    private final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public ArrayList<View> a() {
        View rootView;
        ArrayList<View> arrayList = new ArrayList<>();
        com.iab.omid.library.startapp.b.a a = com.iab.omid.library.startapp.b.a.a();
        if (a != null) {
            Collection<com.iab.omid.library.startapp.adsession.a> c = a.c();
            IdentityHashMap identityHashMap = new IdentityHashMap((c.size() * 2) + 3);
            Iterator<com.iab.omid.library.startapp.adsession.a> it = c.iterator();
            while (it.hasNext()) {
                View d = it.next().d();
                if (d != null && f.c(d) && (rootView = d.getRootView()) != null && !identityHashMap.containsKey(rootView)) {
                    identityHashMap.put(rootView, rootView);
                    float a2 = f.a(rootView);
                    int size = arrayList.size();
                    while (size > 0 && f.a(arrayList.get(size - 1)) > a2) {
                        size--;
                    }
                    arrayList.add(size, rootView);
                }
            }
        }
        return arrayList;
    }

    @Override // com.iab.omid.library.startapp.c.a
    public JSONObject a(View view) {
        return com.iab.omid.library.startapp.d.b.a(0, 0, 0, 0);
    }

    @Override // com.iab.omid.library.startapp.c.a
    public void a(View view, JSONObject jSONObject, a.InterfaceC0048a interfaceC0048a, boolean z) {
        Iterator<View> it = a().iterator();
        while (it.hasNext()) {
            interfaceC0048a.a(it.next(), this.a, jSONObject);
        }
    }
}
