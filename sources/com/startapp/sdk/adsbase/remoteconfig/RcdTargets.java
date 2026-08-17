package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import android.util.Pair;
import com.startapp.aa;
import com.startapp.common.parser.TypeParser;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class RcdTargets implements Serializable {
    private static final long serialVersionUID = 6963217195144137950L;

    @f
    private final SortedMap<String, Pair<Integer, String>> nameToScopesIds;

    @f
    private final SortedMap<Integer, SortedMap<String, String>> scopeToNamesIds;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class Parser implements TypeParser<RcdTargets> {
        private static void add(String str, String str2, int i, SortedMap<String, Pair<Integer, String>> sortedMap, SortedMap<Integer, SortedMap<String, String>> sortedMap2) {
            if (str2.length() < 1) {
                return;
            }
            String replaceAll = str2.replaceAll("~", ".");
            sortedMap.put(replaceAll, new Pair<>(Integer.valueOf(i), str));
            for (int i2 = 0; i2 < 16; i2++) {
                int i3 = 1 << i2;
                if ((i & i3) == i3) {
                    SortedMap<String, String> sortedMap3 = sortedMap2.get(Integer.valueOf(i3));
                    if (sortedMap3 == null) {
                        sortedMap3 = new TreeMap<>();
                        sortedMap2.put(Integer.valueOf(i3), sortedMap3);
                    }
                    sortedMap3.put(replaceAll, str);
                }
            }
        }

        public static void parseRec(String str, String str2, JSONObject jSONObject, SortedMap<String, Pair<Integer, String>> sortedMap, SortedMap<Integer, SortedMap<String, String>> sortedMap2) {
            if (jSONObject == null) {
                return;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equals("~")) {
                    add(str, str2, jSONObject.optInt(next), sortedMap, sortedMap2);
                } else if (next.endsWith("~") && (jSONObject.opt(next) instanceof JSONObject)) {
                    parseRec(str, str2 + next, jSONObject.optJSONObject(next), sortedMap, sortedMap2);
                } else if (next.length() > 0) {
                    add(str, str2 + next, jSONObject.optInt(next), sortedMap, sortedMap2);
                }
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.startapp.common.parser.TypeParser
        public RcdTargets parse(Class<RcdTargets> cls, Object obj) {
            if (!(obj instanceof JSONObject)) {
                return null;
            }
            JSONObject jSONObject = (JSONObject) obj;
            TreeMap treeMap = new TreeMap();
            TreeMap treeMap2 = new TreeMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                parseRec(next, "", jSONObject.optJSONObject(next), treeMap, treeMap2);
            }
            return new RcdTargets(treeMap, treeMap2);
        }
    }

    public RcdTargets(SortedMap<String, Pair<Integer, String>> sortedMap, SortedMap<Integer, SortedMap<String, String>> sortedMap2) {
        this.nameToScopesIds = sortedMap;
        this.scopeToNamesIds = sortedMap2;
    }

    public String a(Map<String, Integer> map) {
        Pair<Integer, String> pair;
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (key != null && value != null && (pair = this.nameToScopesIds.get(key)) != null) {
                String str = (String) pair.second;
                Integer num = (Integer) treeMap.get(str);
                if (num == null) {
                    num = 0;
                }
                treeMap.put(str, Integer.valueOf(value.intValue() | num.intValue()));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry2 : treeMap.entrySet()) {
            sb.append(':');
            sb.append((String) entry2.getKey());
            sb.append(':');
            int intValue = ((Integer) entry2.getValue()).intValue();
            int i = 1;
            boolean z = (57344 & intValue) != 0;
            boolean z2 = (intValue & 7680) != 0;
            if (z && z2) {
                i = 6;
            } else if (z) {
                i = 5;
            } else if (z2) {
                i = 4;
            } else if ((intValue & 256) != 0) {
                i = 3;
            } else if ((intValue & Opcodes.IRETURN) != 0) {
                i = 2;
            } else if ((intValue & 83) == 0) {
                i = 0;
            }
            sb.append(i);
        }
        if (sb.length() > 0) {
            sb.append(':');
        }
        return sb.toString();
    }

    public Collection<String> a(int i) {
        SortedMap<String, String> sortedMap = this.scopeToNamesIds.get(Integer.valueOf(i));
        return sortedMap != null ? Collections.unmodifiableCollection(sortedMap.keySet()) : Collections.emptyList();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RcdTargets.class != obj.getClass()) {
            return false;
        }
        RcdTargets rcdTargets = (RcdTargets) obj;
        return aa.a(this.nameToScopesIds, rcdTargets.nameToScopesIds) && aa.a(this.scopeToNamesIds, rcdTargets.scopeToNamesIds);
    }

    public int hashCode() {
        Object[] objArr = {this.nameToScopesIds, this.scopeToNamesIds};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
