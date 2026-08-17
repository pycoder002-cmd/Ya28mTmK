package org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr;

import org.jacoco.agent.rt.internal_b0d6a23.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/instr/IProbeArrayStrategy.class */
public interface IProbeArrayStrategy {
    int storeInstance(MethodVisitor methodVisitor, int i);

    void addMembers(ClassVisitor classVisitor, int i);
}
