package com.iab.omid.library.startapp.walking.a;

import android.text.TextUtils;
import com.iab.omid.library.startapp.walking.a.b;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class f extends a {
    public f(b.InterfaceC0050b interfaceC0050b, HashSet<String> hashSet, JSONObject jSONObject, long j) {
        super(interfaceC0050b, hashSet, jSONObject, j);
    }

    private void b(String str) {
        com.iab.omid.library.startapp.b.a a = com.iab.omid.library.startapp.b.a.a();
        if (a != null) {
            for (com.iab.omid.library.startapp.adsession.a aVar : a.b()) {
                if (((a) this).a.contains(aVar.getAdSessionId())) {
                    aVar.getAdSessionStatePublisher().a(str, this.c);
                }
            }
        }
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Object... objArr) {
        if (com.iab.omid.library.startapp.d.b.b(this.b, this.d.b())) {
            return null;
        }
        this.d.a(this.b);
        return this.b.toString();
    }

    @Override // com.iab.omid.library.startapp.walking.a.b, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(str);
        }
        super.onPostExecute(str);
    }
}
