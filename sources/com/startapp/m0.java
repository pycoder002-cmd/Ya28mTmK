package com.startapp;

import com.startapp.networkTest.enums.MultiSimVariants;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m0 implements Cloneable {
    public int ActiveSimCount = -1;
    public int ActiveSimCountMax = -1;
    public int DefaultDataSimId = -1;
    public int DefaultSmsSimId = -1;
    public int DefaultSimId = -1;
    public int DefaultVoiceSimId = -1;
    public MultiSimVariants MultiSimVariant = MultiSimVariants.Unknown;

    @f(type = ArrayList.class, value = n0.class)
    public ArrayList<n0> SimInfos = new ArrayList<>();

    public n0 getDefaultDataSimInfo() {
        Iterator<n0> it = this.SimInfos.iterator();
        while (it.hasNext()) {
            n0 next = it.next();
            if (next.SubscriptionId == this.DefaultDataSimId) {
                return next;
            }
        }
        return new n0();
    }

    public n0 getDefaultSmsSimInfo() {
        Iterator<n0> it = this.SimInfos.iterator();
        while (it.hasNext()) {
            n0 next = it.next();
            if (next.SubscriptionId == this.DefaultSmsSimId) {
                return next;
            }
        }
        return new n0();
    }

    public n0 getDefaultVoiceSimInfo() {
        Iterator<n0> it = this.SimInfos.iterator();
        while (it.hasNext()) {
            n0 next = it.next();
            if (next.SubscriptionId == this.DefaultVoiceSimId) {
                return next;
            }
        }
        return new n0();
    }

    public n0 getSimInfoSubId(int i) {
        Iterator<n0> it = this.SimInfos.iterator();
        while (it.hasNext()) {
            n0 next = it.next();
            if (next.SubscriptionId == i) {
                return next;
            }
        }
        return new n0();
    }
}
