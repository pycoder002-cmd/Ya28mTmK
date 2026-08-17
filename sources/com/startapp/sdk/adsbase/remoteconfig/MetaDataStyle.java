package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class MetaDataStyle implements Serializable {
    public static final Integer a = 18;
    public static final Integer b = -1;
    public static final Set<String> c = new HashSet(Arrays.asList("BOLD"));
    public static final Integer d = 14;
    public static final Integer e = -1;
    public static final Set<String> f = new HashSet();
    private static final long serialVersionUID = 1;
    private String name = "";
    private Integer itemGradientTop = -14014151;
    private Integer itemGradientBottom = -8750199;
    private Integer itemTitleTextSize = a;
    private Integer itemTitleTextColor = b;

    @f(type = HashSet.class)
    private Set<String> itemTitleTextDecoration = c;
    private Integer itemDescriptionTextSize = d;
    private Integer itemDescriptionTextColor = e;

    @f(type = HashSet.class)
    private Set<String> itemDescriptionTextDecoration = f;

    public Integer a() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> b() {
        return this.itemDescriptionTextDecoration;
    }

    public Integer c() {
        return this.itemDescriptionTextSize;
    }

    public Integer d() {
        return this.itemGradientBottom;
    }

    public Integer e() {
        return this.itemGradientTop;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetaDataStyle.class != obj.getClass()) {
            return false;
        }
        MetaDataStyle metaDataStyle = (MetaDataStyle) obj;
        return aa.a(this.name, metaDataStyle.name) && aa.a(this.itemGradientTop, metaDataStyle.itemGradientTop) && aa.a(this.itemGradientBottom, metaDataStyle.itemGradientBottom) && aa.a(this.itemTitleTextSize, metaDataStyle.itemTitleTextSize) && aa.a(this.itemTitleTextColor, metaDataStyle.itemTitleTextColor) && aa.a(this.itemTitleTextDecoration, metaDataStyle.itemTitleTextDecoration) && aa.a(this.itemDescriptionTextSize, metaDataStyle.itemDescriptionTextSize) && aa.a(this.itemDescriptionTextColor, metaDataStyle.itemDescriptionTextColor) && aa.a(this.itemDescriptionTextDecoration, metaDataStyle.itemDescriptionTextDecoration);
    }

    public Integer f() {
        return this.itemTitleTextColor;
    }

    public Set<String> g() {
        return this.itemTitleTextDecoration;
    }

    public Integer h() {
        return this.itemTitleTextSize;
    }

    public int hashCode() {
        Object[] objArr = {this.name, this.itemGradientTop, this.itemGradientBottom, this.itemTitleTextSize, this.itemTitleTextColor, this.itemTitleTextDecoration, this.itemDescriptionTextSize, this.itemDescriptionTextColor, this.itemDescriptionTextDecoration};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
