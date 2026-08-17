package org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr;

import org.jacoco.agent.rt.internal_b0d6a23.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/instr/NoneProbeArrayStrategy.class */
class NoneProbeArrayStrategy implements IProbeArrayStrategy {
    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr.IProbeArrayStrategy
    public int storeInstance(MethodVisitor mv, int variable) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr.IProbeArrayStrategy
    public void addMembers(ClassVisitor delegate, int probeCount) {
    }
}
