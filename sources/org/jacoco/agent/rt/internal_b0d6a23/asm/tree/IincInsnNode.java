package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/IincInsnNode.class */
public class IincInsnNode extends AbstractInsnNode {
    public int var;
    public int incr;

    public IincInsnNode(int var, int incr) {
        super(Opcodes.IINC);
        this.var = var;
        this.incr = incr;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public int getType() {
        return 10;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor mv) {
        mv.visitIincInsn(this.var, this.incr);
        acceptAnnotations(mv);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> labels) {
        return new IincInsnNode(this.var, this.incr).cloneAnnotations(this);
    }
}
