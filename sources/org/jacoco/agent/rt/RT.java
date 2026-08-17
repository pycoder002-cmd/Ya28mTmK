package org.jacoco.agent.rt;

import org.jacoco.agent.rt.internal_b0d6a23.Agent;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/RT.class */
public final class RT {
    private RT() {
    }

    public static IAgent getAgent() throws IllegalStateException {
        return Agent.getInstance();
    }
}
