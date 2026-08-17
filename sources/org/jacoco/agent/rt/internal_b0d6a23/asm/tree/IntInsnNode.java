package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/IntInsnNode.class */
public class IntInsnNode extends AbstractInsnNode {
    public int operand;

    public IntInsnNode(int opcode, int operand) {
        super(opcode);
        this.operand = operand;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public int getType() {
        return 1;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor mv) {
        mv.visitIntInsn(this.opcode, this.operand);
        acceptAnnotations(mv);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> labels) {
        return new IntInsnNode(this.opcode, this.operand).cloneAnnotations(this);
    }
}
