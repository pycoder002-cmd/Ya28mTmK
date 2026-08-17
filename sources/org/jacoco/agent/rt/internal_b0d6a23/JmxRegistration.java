package org.jacoco.agent.rt.internal_b0d6a23;

import java.util.concurrent.Callable;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/JmxRegistration.class */
class JmxRegistration implements Callable<Void> {
    private static final String JMX_NAME = "org.jacoco:type=Runtime";
    private final MBeanServer server;
    private final ObjectName name;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Void, android.content.Intent] */
    @Override // java.util.concurrent.Callable
    public /* bridge */ /* synthetic */ Void call() throws Exception {
        return putExtra(this, this);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v2 javax.management.StandardMBean, still in use, count: 2, list:
          (r1v2 javax.management.StandardMBean) from 0x002a: INVOKE 
          (r0v4 ?? I:android.animation.ObjectAnimator)
          (r1v2 javax.management.StandardMBean)
          (r2v3 javax.management.ObjectName)
          (r0v4 ?? I:float[])
         INTERFACE call: android.animation.ObjectAnimator.ofFloat(java.lang.Object, java.lang.String, float[]):android.animation.ObjectAnimator A[MD:(java.lang.Object, java.lang.String, float[]):android.animation.ObjectAnimator VARARG (c)]
          (r1v2 javax.management.StandardMBean) from 0x0023: INVOKE 
          (r1v2 javax.management.StandardMBean)
          (r7v0 'agent' org.jacoco.agent.rt.IAgent A[D('agent' org.jacoco.agent.rt.IAgent)])
          (wrap:java.lang.Class:0x0021: CONST_CLASS  A[WRAPPED] org.jacoco.agent.rt.IAgent.class)
          (r0v4 ?? I:java.lang.String)
          (r0v4 ?? I:android.graphics.Path)
         SUPER call: android.animation.ObjectAnimator.ofFloat(java.lang.Object, java.lang.String, java.lang.String, android.graphics.Path):android.animation.ObjectAnimator A[MD:(java.lang.Object, java.lang.String, java.lang.String, android.graphics.Path):android.animation.ObjectAnimator (c)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.graphics.Path, javax.management.MBeanServer, android.animation.ObjectAnimator, float[], java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean, javax.management.MBeanServer] */
    JmxRegistration(org.jacoco.agent.rt.IAgent r7) throws java.lang.Exception {
        /*
            r6 = this;
            r0 = r6
            super/*android.animation.Animator*/.isRunning()
            r0 = r6
            boolean r1 = android.animation.Animator.isStarted()
            r0.server = r1
            r0 = r6
            javax.management.ObjectName r1 = new javax.management.ObjectName
            r2 = r1
            java.lang.String r3 = "org.jacoco:type=Runtime"
            r2.<init>()
            r0.name = r1
            r0 = r6
            javax.management.MBeanServer r0 = r0.server
            javax.management.StandardMBean r1 = new javax.management.StandardMBean
            r2 = r1
            r3 = r7
            java.lang.Class<org.jacoco.agent.rt.IAgent> r4 = org.jacoco.agent.rt.IAgent.class
            super/*android.animation.ObjectAnimator*/.ofFloat(r3, r4, r0, r0)
            r2 = r6
            javax.management.ObjectName r2 = r2.name
            android.animation.ObjectAnimator r0 = r0.ofFloat(r1, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jacoco.agent.rt.internal_b0d6a23.JmxRegistration.<init>(org.jacoco.agent.rt.IAgent):void");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        MBeanServer mBeanServer = this.server;
        mBeanServer.ofInt(this.name, mBeanServer, mBeanServer, mBeanServer);
        return null;
    }
}
