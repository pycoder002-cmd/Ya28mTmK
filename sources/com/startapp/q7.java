package com.startapp;

import android.support.v4.app.NotificationCompat;
import com.startapp.r7;
import com.startapp.t7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q7 {
    public static final Map<String, q7> a = new HashMap();
    public static final q7 b;
    public static final q7 c;
    public static final q7 d;
    public static final q7 e;
    public static final q7 f;
    public static final q7 g;
    public static final q7 h;
    public static final q7 i;
    public static final q7 j;
    public static final q7 k;
    public static final q7 l;
    public static final q7 m;
    public static final q7 n;
    public final String o;
    public final r7 p;

    static {
        r7.a aVar = new r7.a();
        aVar.a = 23;
        aVar.b = 50;
        aVar.c = true;
        t7.a aVar2 = new t7.a();
        String[] strArr = {"initialize"};
        List<String> list = aVar2.a;
        if (list == null) {
            list = new ArrayList<>();
            aVar2.a = list;
        }
        t7.a a2 = aVar2.a(strArr, list).a("value");
        a2.d = "8h";
        r7.a a3 = aVar.a(new t7(a2));
        t7.a a4 = new t7.a().a("value", "details");
        a4.d = "30m";
        r7.a a5 = a3.a(new t7(a4));
        t7.a a6 = new t7.a().a("value");
        a6.d = "10s";
        r7.a a7 = a5.a(new t7(a6));
        a7.d = "2h";
        a7.e = "2s";
        b = new q7("general", new r7(a7));
        r7.a aVar3 = new r7.a();
        aVar3.a = 17;
        aVar3.b = 20;
        aVar3.c = true;
        t7.a aVar4 = new t7.a();
        String[] strArr2 = {"fake_click"};
        List<String> list2 = aVar4.b;
        if (list2 == null) {
            list2 = new ArrayList<>();
            aVar4.b = list2;
        }
        t7.a a8 = aVar4.a(strArr2, list2).a("appActivity", "value", "details");
        a8.d = "30m";
        r7.a a9 = aVar3.a(new t7(a8));
        t7.a aVar5 = new t7.a();
        String[] strArr3 = {"fake_click"};
        List<String> list3 = aVar5.b;
        if (list3 == null) {
            list3 = new ArrayList<>();
            aVar5.b = list3;
        }
        t7.a a10 = aVar5.a(strArr3, list3).a("appActivity", "value");
        a10.d = "10s";
        r7.a a11 = a9.a(new t7(a10));
        a11.d = "4h";
        a11.e = "5s";
        c = new q7("error", new r7(a11));
        r7.a aVar6 = new r7.a();
        aVar6.a = 17;
        aVar6.b = 30;
        aVar6.c = true;
        t7.a a12 = new t7.a().a("appActivity", "value", "details");
        a12.d = "12h";
        r7.a a13 = aVar6.a(new t7(a12));
        t7.a a14 = new t7.a().a("appActivity", "value");
        a14.d = "1h";
        r7.a a15 = a13.a(new t7(a14));
        a15.d = "1d";
        a15.e = "5s";
        r7 r7Var = new r7(a15);
        d = new q7("exception", r7Var);
        e = new q7("exception_nt", r7Var);
        r7.a aVar7 = new r7.a();
        aVar7.a = 17;
        aVar7.b = 40;
        aVar7.c = true;
        t7.a a16 = new t7.a().a("value", "details");
        a16.d = "1h";
        r7.a a17 = aVar7.a(new t7(a16));
        a17.d = "2d";
        a17.e = "5s";
        r7 r7Var2 = new r7(a17);
        f = new q7("exception_fatal", r7Var2);
        g = new q7("anr", r7Var2);
        r7.a aVar8 = new r7.a();
        aVar8.a = 17;
        aVar8.b = 10;
        aVar8.c = false;
        aVar8.e = "10s";
        h = new q7("netdiag", new r7(aVar8));
        r7.a aVar9 = new r7.a();
        aVar9.a = 3071;
        aVar9.b = 90;
        aVar9.c = true;
        t7.a a18 = new t7.a().a(NotificationCompat.CATEGORY_SERVICE);
        a18.d = "1m";
        r7.a a19 = aVar9.a(new t7(a18));
        a19.d = "1h";
        i = new q7("periodic", new r7(a19));
        r7.a aVar10 = new r7.a();
        aVar10.a = 17;
        aVar10.b = 60;
        aVar10.c = true;
        aVar10.d = "1d";
        aVar10.e = "5s";
        j = new q7("success_smart_redirect_hop_info", new r7(aVar10));
        r7.a aVar11 = new r7.a();
        aVar11.a = 17;
        aVar11.b = 70;
        aVar11.c = false;
        k = new q7("triggeredLink", new r7(aVar11));
        r7.a aVar12 = new r7.a();
        aVar12.a = 23;
        aVar12.b = 80;
        aVar12.c = true;
        aVar12.d = "1d";
        l = new q7("ct", new r7(aVar12));
        r7.a aVar13 = new r7.a();
        aVar13.a = 23;
        aVar13.b = 80;
        aVar13.c = true;
        aVar13.d = "1d";
        m = new q7("lt", new r7(aVar13));
        r7.a aVar14 = new r7.a();
        aVar14.a = 23;
        aVar14.b = 80;
        aVar14.c = true;
        aVar14.d = "1d";
        n = new q7("nir", new r7(aVar14));
    }

    public q7(String str, r7 r7Var) {
        this.o = str;
        this.p = r7Var;
        a.put(str, this);
    }

    public static q7 a(String str) {
        return a.get(str);
    }

    public String a() {
        return this.o;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q7.class != obj.getClass()) {
            return false;
        }
        return aa.a(this.o, ((q7) obj).o);
    }

    public int hashCode() {
        return this.o.hashCode();
    }

    public String toString() {
        return this.o;
    }
}
