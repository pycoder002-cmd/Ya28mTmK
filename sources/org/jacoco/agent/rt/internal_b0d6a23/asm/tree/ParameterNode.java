package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/ParameterNode.class */
public class ParameterNode {
    public String name;
    public int access;

    public ParameterNode(String name, int access) {
        this.name = name;
        this.access = access;
    }

    public void accept(MethodVisitor mv) {
        mv.visitParameter(this.name, this.access);
    }
}
