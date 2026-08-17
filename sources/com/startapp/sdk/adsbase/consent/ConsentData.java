package com.startapp.sdk.adsbase.consent;

import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class ConsentData implements Serializable {
    private static final long serialVersionUID = 4245437752472461229L;
    private Boolean apc;
    private String infoDialogClickUrl;
    private String infoDialogDParam;
    private String infoDialogImpressionUrl;
    private Long timeStamp;
    private Integer type;

    public Boolean a() {
        return this.apc;
    }

    public void a(Boolean bool) {
        this.apc = bool;
    }

    public void a(Integer num) {
        this.type = num;
    }

    public void a(Long l) {
        this.timeStamp = l;
    }

    public void a(String str) {
        this.infoDialogClickUrl = str;
    }

    public String b() {
        return this.infoDialogClickUrl;
    }

    public void b(String str) {
        this.infoDialogDParam = str;
    }

    public String c() {
        return this.infoDialogDParam;
    }

    public void c(String str) {
        this.infoDialogImpressionUrl = str;
    }

    public String d() {
        return this.infoDialogImpressionUrl;
    }

    public Long e() {
        return this.timeStamp;
    }

    public Integer f() {
        return this.type;
    }
}
