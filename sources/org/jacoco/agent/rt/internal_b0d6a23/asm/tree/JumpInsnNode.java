package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/JumpInsnNode.class */
public class JumpInsnNode extends AbstractInsnNode {
    public LabelNode label;

    public JumpInsnNode(int opcode, LabelNode label) {
        super(opcode);
        this.label = label;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public int getType() {
        return 7;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor mv) {
        mv.visitJumpInsn(this.opcode, this.label.getLabel());
        acceptAnnotations(mv);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> labels) {
        return new JumpInsnNode(this.opcode, clone(this.label, labels)).cloneAnnotations(this);
    }
}
