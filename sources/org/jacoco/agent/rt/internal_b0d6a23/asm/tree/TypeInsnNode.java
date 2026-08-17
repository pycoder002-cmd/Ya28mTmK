package org.jacoco.agent.rt.internal_b0d6a23.asm.tree;

import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/tree/TypeInsnNode.class */
public class TypeInsnNode extends AbstractInsnNode {
    public String desc;

    public TypeInsnNode(int opcode, String desc) {
        super(opcode);
        this.desc = desc;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public int getType() {
        return 3;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor mv) {
        mv.visitTypeInsn(this.opcode, this.desc);
        acceptAnnotations(mv);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> labels) {
        return new TypeInsnNode(this.opcode, this.desc).cloneAnnotations(this);
    }
}
