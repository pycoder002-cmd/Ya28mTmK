package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/MultiANewArrayInsnNode.class */
public class MultiANewArrayInsnNode extends AbstractInsnNode {
    public String desc;
    public int dims;

    public MultiANewArrayInsnNode(String desc, int dims) {
        super(Opcodes.MULTIANEWARRAY);
        this.desc = desc;
        this.dims = dims;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public int getType() {
        return 13;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor mv) {
        mv.visitMultiANewArrayInsn(this.desc, this.dims);
        acceptAnnotations(mv);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> labels) {
        return new MultiANewArrayInsnNode(this.desc, this.dims).cloneAnnotations(this);
    }
}
