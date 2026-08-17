package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.AnalyticsCategoryFilterConfig;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t7 {
    public final List<String> a;
    public final List<String> b;
    public final List<String> c;
    public final List<String> d;
    public final List<String> e;
    public final long f;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a {
        public List<String> a;
        public List<String> b;
        public List<String> c;
        public String d;

        public a a(String... strArr) {
            List<String> list = this.c;
            if (list == null) {
                list = new ArrayList<>();
                this.c = list;
            }
            return a(strArr, list);
        }

        public final a a(String[] strArr, List<String> list) {
            for (String str : strArr) {
                if (str != null) {
                    list.add(str);
                }
            }
            return this;
        }

        public List<String> a() {
            return this.b;
        }

        public List<String> b() {
            return this.c;
        }

        public List<String> c() {
            return this.a;
        }

        public String d() {
            return this.d;
        }
    }

    public t7(AnalyticsCategoryFilterConfig analyticsCategoryFilterConfig) {
        this.a = aa.b(analyticsCategoryFilterConfig.e());
        this.b = aa.b(analyticsCategoryFilterConfig.b());
        this.c = aa.b(analyticsCategoryFilterConfig.d());
        this.d = aa.b(analyticsCategoryFilterConfig.a());
        this.e = aa.b(analyticsCategoryFilterConfig.c());
        this.f = Math.max(0L, aa.e(analyticsCategoryFilterConfig.f()));
    }

    public t7(a aVar) {
        this.a = aa.b(aVar.c());
        this.b = aa.b(aVar.a());
        this.c = aa.b((List) null);
        this.d = aa.b((List) null);
        this.e = aa.b(aVar.b());
        this.f = Math.max(0L, aa.e(aVar.d()));
    }

    public static List<t7> a(List<AnalyticsCategoryFilterConfig> list) {
        ArrayList arrayList = null;
        if (list == null) {
            return null;
        }
        for (AnalyticsCategoryFilterConfig analyticsCategoryFilterConfig : list) {
            if (analyticsCategoryFilterConfig != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList(list.size());
                }
                arrayList.add(new t7(analyticsCategoryFilterConfig));
            }
        }
        return arrayList != null ? aa.b(arrayList) : arrayList;
    }
}
