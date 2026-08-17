package com.startapp.sdk.adsbase.adrules;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.i6;
import com.startapp.j6;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdRules implements Serializable {
    private static final long serialVersionUID = 1;

    @f(type = ArrayList.class, value = AdRule.class)
    private List<AdRule> session = new ArrayList();

    @f(innerValue = AdRule.class, key = AdPreferences.Placement.class, type = HashMap.class, value = ArrayList.class)
    private Map<AdPreferences.Placement, List<AdRule>> placements = new HashMap();

    @f(innerValue = AdRule.class, type = HashMap.class, value = ArrayList.class)
    private Map<String, List<AdRule>> tags = new HashMap();
    private boolean applyOnBannerRefresh = true;
    public transient Set<Class<? extends AdRule>> a = new HashSet();

    public synchronized AdRulesResult a(AdPreferences.Placement placement, String str) {
        AdRulesResult a;
        this.a.clear();
        a = a(this.tags.get(str), j6.a.d.get(str), AdRuleLevel.TAG);
        if (a.b()) {
            List<AdRule> list = this.placements.get(placement);
            List<i6> list2 = j6.a.c.get(placement);
            AdRuleLevel adRuleLevel = AdRuleLevel.PLACEMENT;
            placement.toString();
            a = a(list, list2, adRuleLevel);
            if (a.b()) {
                a = a(this.session, j6.a.b, AdRuleLevel.SESSION);
            }
        }
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AdRulesResult a(List list, List list2, AdRuleLevel adRuleLevel) {
        if (list == null) {
            return new AdRulesResult(true, "");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AdRule adRule = (AdRule) it.next();
            if (adRule.a || !this.a.contains(adRule.getClass())) {
                if (!adRule.a(list2)) {
                    return new AdRulesResult(false, adRule.getClass().getSimpleName() + "_" + adRuleLevel + "");
                }
                this.a.add(adRule.getClass());
            }
        }
        return new AdRulesResult(true, "");
    }

    public boolean a() {
        return this.applyOnBannerRefresh;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdRules.class != obj.getClass()) {
            return false;
        }
        AdRules adRules = (AdRules) obj;
        return this.applyOnBannerRefresh == adRules.applyOnBannerRefresh && aa.a(this.session, adRules.session) && aa.a(this.placements, adRules.placements) && aa.a(this.tags, adRules.tags);
    }

    public int hashCode() {
        Object[] objArr = {this.session, this.placements, this.tags, Boolean.valueOf(this.applyOnBannerRefresh)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
