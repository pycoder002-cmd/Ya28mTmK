package com.startapp.sdk.triggeredlinks;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class TriggeredLinksMetadata implements Serializable {
    private static final long serialVersionUID = 1082253451843108611L;

    @f(complex = true, name = "AppEvents")
    private AppEventsMetadata appEvents;
    private int ief;

    @f(name = "ThrottlingPublisherIDs", type = ArrayList.class, value = Integer.class)
    private List<Integer> throttlingPublisherIDs;
    private boolean enabled = true;
    private int smartRedirectTimeoutInSec = 10;
    private boolean triggeredLinkInfoEvent = true;

    public AppEventsMetadata a() {
        return this.appEvents;
    }

    public int b() {
        return this.ief;
    }

    public int c() {
        return this.smartRedirectTimeoutInSec;
    }

    public List<Integer> d() {
        return this.throttlingPublisherIDs;
    }

    public boolean e() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TriggeredLinksMetadata.class != obj.getClass()) {
            return false;
        }
        TriggeredLinksMetadata triggeredLinksMetadata = (TriggeredLinksMetadata) obj;
        return this.enabled == triggeredLinksMetadata.enabled && this.smartRedirectTimeoutInSec == triggeredLinksMetadata.smartRedirectTimeoutInSec && this.triggeredLinkInfoEvent == triggeredLinksMetadata.triggeredLinkInfoEvent && this.ief == triggeredLinksMetadata.ief && aa.a(this.appEvents, triggeredLinksMetadata.appEvents) && aa.a(this.throttlingPublisherIDs, triggeredLinksMetadata.throttlingPublisherIDs);
    }

    public boolean f() {
        return this.triggeredLinkInfoEvent;
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), Integer.valueOf(this.smartRedirectTimeoutInSec), Boolean.valueOf(this.triggeredLinkInfoEvent), this.appEvents, this.throttlingPublisherIDs, Integer.valueOf(this.ief)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
