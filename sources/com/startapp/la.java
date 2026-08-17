package com.startapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class la {
    public static final Object a;
    public static la b;
    public final Context c;
    public final Map<BroadcastReceiver, ArrayList<IntentFilter>> d = new WeakHashMap();
    public final HashMap<String, ArrayList<c>> e = new HashMap<>();
    public final ArrayList<b> f = new ArrayList<>();
    public final Handler g;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int size;
            b[] bVarArr;
            if (message.what != 1) {
                super.handleMessage(message);
                return;
            }
            la laVar = la.this;
            while (true) {
                synchronized (laVar.d) {
                    size = laVar.f.size();
                    if (size <= 0) {
                        return;
                    }
                    bVarArr = new b[size];
                    laVar.f.toArray(bVarArr);
                    laVar.f.clear();
                }
                for (int i = 0; i < size; i++) {
                    b bVar = bVarArr[i];
                    for (int i2 = 0; i2 < bVar.b.size(); i2++) {
                        BroadcastReceiver broadcastReceiver = bVar.b.get(i2).b.get();
                        if (broadcastReceiver != null) {
                            broadcastReceiver.onReceive(laVar.c, bVar.a);
                        }
                    }
                }
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b {
        public final Intent a;
        public final ArrayList<c> b;

        public b(Intent intent, ArrayList<c> arrayList) {
            this.a = intent;
            this.b = arrayList;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class c {
        public final IntentFilter a;
        public final WeakReference<BroadcastReceiver> b;
        public boolean c;

        public c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.a = intentFilter;
            this.b = new WeakReference<>(broadcastReceiver);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.b);
            sb.append(" filter=");
            sb.append(this.a);
            sb.append("}");
            return sb.toString();
        }
    }

    static {
        ya.a((Class<?>) la.class);
        a = new Object();
    }

    public la(Context context) {
        this.c = context;
        this.g = new a(context.getMainLooper());
    }

    public static la a(Context context) {
        la laVar;
        synchronized (a) {
            if (b == null) {
                Context a2 = y8.a(context);
                if (a2 != null) {
                    context = a2;
                }
                b = new la(context);
            }
            laVar = b;
        }
        return laVar;
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        synchronized (this.d) {
            ArrayList<IntentFilter> remove = this.d.remove(broadcastReceiver);
            if (remove == null) {
                return;
            }
            for (int i = 0; i < remove.size(); i++) {
                IntentFilter intentFilter = remove.get(i);
                for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                    String action = intentFilter.getAction(i2);
                    ArrayList<c> arrayList = this.e.get(action);
                    if (arrayList != null) {
                        int i3 = 0;
                        while (i3 < arrayList.size()) {
                            if (arrayList.get(i3).b.get() == broadcastReceiver) {
                                arrayList.remove(i3);
                                i3--;
                            }
                            i3++;
                        }
                        if (arrayList.size() <= 0) {
                            this.e.remove(action);
                        }
                    }
                }
            }
        }
    }

    public void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.d) {
            c cVar = new c(intentFilter, broadcastReceiver);
            ArrayList<IntentFilter> arrayList = this.d.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.d.put(broadcastReceiver, arrayList);
            }
            arrayList.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<c> arrayList2 = this.e.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.e.put(action, arrayList2);
                }
                arrayList2.add(cVar);
            }
        }
    }

    public boolean a(Intent intent) {
        boolean z;
        String str;
        String str2;
        int i;
        ArrayList arrayList;
        Uri uri;
        synchronized (this.d) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.c.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            ArrayList<c> arrayList2 = this.e.get(intent.getAction());
            if (arrayList2 != null) {
                ArrayList arrayList3 = null;
                int i2 = 0;
                while (i2 < arrayList2.size()) {
                    c cVar = arrayList2.get(i2);
                    if (cVar.c) {
                        i = i2;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        uri = data;
                        arrayList = arrayList3;
                    } else {
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        i = i2;
                        arrayList = arrayList3;
                        uri = data;
                        if (cVar.a.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager") >= 0) {
                            arrayList3 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList3.add(cVar);
                            cVar.c = true;
                            i2 = i + 1;
                            action = str;
                            resolveTypeIfNeeded = str2;
                            data = uri;
                        }
                    }
                    arrayList3 = arrayList;
                    i2 = i + 1;
                    action = str;
                    resolveTypeIfNeeded = str2;
                    data = uri;
                }
                ArrayList arrayList4 = arrayList3;
                z = false;
                if (arrayList4 != null) {
                    for (int i3 = 0; i3 < arrayList4.size(); i3++) {
                        ((c) arrayList4.get(i3)).c = false;
                    }
                    this.f.add(new b(intent, arrayList4));
                    if (!this.g.hasMessages(1)) {
                        this.g.sendEmptyMessage(1);
                    }
                    return true;
                }
            } else {
                z = false;
            }
            return z;
        }
    }
}
