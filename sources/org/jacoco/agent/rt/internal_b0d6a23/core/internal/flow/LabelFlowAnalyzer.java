package org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow;

import org.jacoco.agent.rt.internal_b0d6a23.asm.Handle;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Label;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.jacoco.agent.rt.internal_b0d6a23.asm.tree.MethodNode;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/flow/LabelFlowAnalyzer.class */
public final class LabelFlowAnalyzer extends MethodVisitor {
    boolean successor;
    boolean first;
    Label lineStart;

    public static void markLabels(MethodNode method) {
        MethodVisitor lfa = new LabelFlowAnalyzer();
        int i = method.tryCatchBlocks.size();
        while (true) {
            i--;
            if (i >= 0) {
                method.tryCatchBlocks.get(i).accept(lfa);
            } else {
                method.instructions.accept(lfa);
                return;
            }
        }
    }

    public LabelFlowAnalyzer() {
        super(327680);
        this.successor = false;
        this.first = true;
        this.lineStart = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        LabelInfo.setTarget(start);
        LabelInfo.setTarget(handler);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitJumpInsn(int opcode, Label label) {
        LabelInfo.setTarget(label);
        if (opcode == 168) {
            throw new AssertionError("Subroutines not supported.");
        }
        this.successor = opcode != 167;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLabel(Label label) {
        if (this.first) {
            LabelInfo.setTarget(label);
        }
        if (this.successor) {
            LabelInfo.setSuccessor(label);
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLineNumber(int line, Label start) {
        this.lineStart = start;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        visitSwitchInsn(dflt, labels);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        visitSwitchInsn(dflt, labels);
    }

    private void visitSwitchInsn(Label dflt, Label[] labels) {
        LabelInfo.resetDone(dflt);
        LabelInfo.resetDone(labels);
        setTargetIfNotDone(dflt);
        for (Label l : labels) {
            setTargetIfNotDone(l);
        }
        this.successor = false;
        this.first = false;
    }

    private static void setTargetIfNotDone(Label label) {
        if (!LabelInfo.isDone(label)) {
            LabelInfo.setTarget(label);
            LabelInfo.setDone(label);
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitInsn(int opcode) {
        switch (opcode) {
            case Opcodes.RET /* 169 */:
                throw new AssertionError("Subroutines not supported.");
            case Opcodes.TABLESWITCH /* 170 */:
            case 171:
            case Opcodes.GETSTATIC /* 178 */:
            case Opcodes.PUTSTATIC /* 179 */:
            case Opcodes.GETFIELD /* 180 */:
            case Opcodes.PUTFIELD /* 181 */:
            case Opcodes.INVOKEVIRTUAL /* 182 */:
            case Opcodes.INVOKESPECIAL /* 183 */:
            case Opcodes.INVOKESTATIC /* 184 */:
            case Opcodes.INVOKEINTERFACE /* 185 */:
            case Opcodes.INVOKEDYNAMIC /* 186 */:
            case Opcodes.NEW /* 187 */:
            case Opcodes.NEWARRAY /* 188 */:
            case Opcodes.ANEWARRAY /* 189 */:
            case Opcodes.ARRAYLENGTH /* 190 */:
            default:
                this.successor = true;
                break;
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.DRETURN /* 175 */:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.RETURN /* 177 */:
            case Opcodes.ATHROW /* 191 */:
                this.successor = false;
                break;
        }
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitIntInsn(int opcode, int operand) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitVarInsn(int opcode, int var) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTypeInsn(int opcode, String type) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        this.successor = true;
        this.first = false;
        markMethodInvocationLine();
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        this.successor = true;
        this.first = false;
        markMethodInvocationLine();
    }

    private void markMethodInvocationLine() {
        if (this.lineStart != null) {
            LabelInfo.setMethodInvocationLine(this.lineStart);
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLdcInsn(Object cst) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitIincInsn(int var, int increment) {
        this.successor = true;
        this.first = false;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String desc, int dims) {
        this.successor = true;
        this.first = false;
    }
}
