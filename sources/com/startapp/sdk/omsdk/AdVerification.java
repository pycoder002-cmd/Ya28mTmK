package com.startapp.sdk.omsdk;

import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdVerification implements Serializable {
    private static final long serialVersionUID = 1;

    @f(name = "adVerifications", type = VerificationDetails.class)
    private VerificationDetails[] adVerification;

    public AdVerification() {
    }

    public AdVerification(VerificationDetails[] verificationDetailsArr) {
        this.adVerification = verificationDetailsArr;
    }

    public List<VerificationDetails> a() {
        VerificationDetails[] verificationDetailsArr = this.adVerification;
        if (verificationDetailsArr == null) {
            return null;
        }
        return Arrays.asList(verificationDetailsArr);
    }
}
