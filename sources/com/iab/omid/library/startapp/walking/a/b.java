package com.iab.omid.library.startapp.walking.a;

import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class b extends AsyncTask<Object, Void, String> {
    private a a;
    public final InterfaceC0050b d;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void a(b bVar);
    }

    /* compiled from: StartAppSDK */
    /* renamed from: com.iab.omid.library.startapp.walking.a.b$b, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public interface InterfaceC0050b {
        void a(JSONObject jSONObject);

        JSONObject b();
    }

    public b(InterfaceC0050b interfaceC0050b) {
        this.d = interfaceC0050b;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        a aVar = this.a;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public void a(ThreadPoolExecutor threadPoolExecutor) {
        executeOnExecutor(threadPoolExecutor, new Object[0]);
    }
}
