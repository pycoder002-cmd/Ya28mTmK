package com.iab.omid.library.startapp.walking.a;

import com.iab.omid.library.startapp.walking.a.b;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class e extends a {
    public e(b.InterfaceC0050b interfaceC0050b, HashSet<String> hashSet, JSONObject jSONObject, long j) {
        super(interfaceC0050b, hashSet, jSONObject, j);
    }

    private void b(String str) {
        com.iab.omid.library.startapp.b.a a = com.iab.omid.library.startapp.b.a.a();
        if (a != null) {
            for (com.iab.omid.library.startapp.adsession.a aVar : a.b()) {
                if (((a) this).a.contains(aVar.getAdSessionId())) {
                    aVar.getAdSessionStatePublisher().b(str, this.c);
                }
            }
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Object... objArr) {
        return this.b.toString();
    }

    @Override // com.iab.omid.library.startapp.walking.a.b, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(String str) {
        b(str);
        super.onPostExecute(str);
    }
}
