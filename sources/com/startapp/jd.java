package com.startapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Pair;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class jd extends md {
    public BroadcastReceiver c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends BroadcastReceiver {
        public final /* synthetic */ ed a;

        public a(ed edVar) {
            this.a = edVar;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            this.a.a(new Pair(jd.this, intent));
        }
    }

    public jd(String str, Map<String, String> map) {
        super(str, map);
    }

    @Override // com.startapp.nd
    public void a(Context context) throws Exception {
        BroadcastReceiver broadcastReceiver = this.c;
        if (broadcastReceiver == null) {
            throw new IllegalStateException();
        }
        context.unregisterReceiver(broadcastReceiver);
        this.c = null;
    }

    @Override // com.startapp.nd
    public void a(Context context, ed edVar) throws Exception {
        if (this.c != null) {
            throw new IllegalStateException();
        }
        a aVar = new a(edVar);
        this.c = aVar;
        context.registerReceiver(aVar, new IntentFilter(this.a));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || jd.class != obj.getClass()) {
            return false;
        }
        return aa.a(this.c, ((jd) obj).c);
    }

    public int hashCode() {
        Object[] objArr = {this.c};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
