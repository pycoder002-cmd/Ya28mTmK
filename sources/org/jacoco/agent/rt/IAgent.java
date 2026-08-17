package org.jacoco.agent.rt;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import java.io.IOException;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/IAgent.class */
public interface IAgent {
    Thing.Builder put(String str, Thing[] thingArr);

    Thing.Builder put(String str, String[] strArr);

    Action.Builder setActionStatus(String str);

    /* renamed from: setName */
    Action.Builder m34setName(String str);

    /* renamed from: setName, reason: collision with other method in class */
    Thing.Builder m34setName(String str);

    Action.Builder setObject(Thing thing) throws IOException;
}
