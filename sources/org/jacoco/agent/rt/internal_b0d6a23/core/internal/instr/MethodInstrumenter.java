package org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr;

import org.jacoco.agent.rt.internal_b0d6a23.asm.Label;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.IFrame;
import org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.LabelInfo;
import org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/instr/MethodInstrumenter.class */
class MethodInstrumenter extends MethodProbesVisitor {
    private final IProbeInserter probeInserter;

    public MethodInstrumenter(MethodVisitor mv, IProbeInserter probeInserter) {
        super(mv);
        this.probeInserter = probeInserter;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor
    public void visitProbe(int probeId) {
        this.probeInserter.insertProbe(probeId);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor
    public void visitInsnWithProbe(int opcode, int probeId) {
        this.probeInserter.insertProbe(probeId);
        this.mv.visitInsn(opcode);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor
    public void visitJumpInsnWithProbe(int opcode, Label label, int probeId, IFrame frame) {
        if (opcode == 167) {
            this.probeInserter.insertProbe(probeId);
            this.mv.visitJumpInsn(Opcodes.GOTO, label);
            return;
        }
        Label intermediate = new Label();
        this.mv.visitJumpInsn(getInverted(opcode), intermediate);
        this.probeInserter.insertProbe(probeId);
        this.mv.visitJumpInsn(Opcodes.GOTO, label);
        this.mv.visitLabel(intermediate);
        frame.accept(this.mv);
    }

    private int getInverted(int opcode) {
        switch (opcode) {
            case Opcodes.IFEQ /* 153 */:
                return Opcodes.IFNE;
            case Opcodes.IFNE /* 154 */:
                return Opcodes.IFEQ;
            case Opcodes.IFLT /* 155 */:
                return Opcodes.IFGE;
            case Opcodes.IFGE /* 156 */:
                return Opcodes.IFLT;
            case Opcodes.IFGT /* 157 */:
                return Opcodes.IFLE;
            case Opcodes.IFLE /* 158 */:
                return Opcodes.IFGT;
            case Opcodes.IF_ICMPEQ /* 159 */:
                return Opcodes.IF_ICMPNE;
            case Opcodes.IF_ICMPNE /* 160 */:
                return Opcodes.IF_ICMPEQ;
            case Opcodes.IF_ICMPLT /* 161 */:
                return Opcodes.IF_ICMPGE;
            case Opcodes.IF_ICMPGE /* 162 */:
                return Opcodes.IF_ICMPLT;
            case Opcodes.IF_ICMPGT /* 163 */:
                return Opcodes.IF_ICMPLE;
            case Opcodes.IF_ICMPLE /* 164 */:
                return Opcodes.IF_ICMPGT;
            case Opcodes.IF_ACMPEQ /* 165 */:
                return Opcodes.IF_ACMPNE;
            case Opcodes.IF_ACMPNE /* 166 */:
                return Opcodes.IF_ACMPEQ;
            case Opcodes.GOTO /* 167 */:
            case Opcodes.JSR /* 168 */:
            case Opcodes.RET /* 169 */:
            case Opcodes.TABLESWITCH /* 170 */:
            case 171:
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.DRETURN /* 175 */:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.RETURN /* 177 */:
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
            case Opcodes.ATHROW /* 191 */:
            case Opcodes.CHECKCAST /* 192 */:
            case Opcodes.INSTANCEOF /* 193 */:
            case Opcodes.MONITORENTER /* 194 */:
            case Opcodes.MONITOREXIT /* 195 */:
            case 196:
            case Opcodes.MULTIANEWARRAY /* 197 */:
            default:
                throw new IllegalArgumentException();
            case Opcodes.IFNULL /* 198 */:
                return Opcodes.IFNONNULL;
            case Opcodes.IFNONNULL /* 199 */:
                return Opcodes.IFNULL;
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor
    public void visitTableSwitchInsnWithProbes(int min, int max, Label dflt, Label[] labels, IFrame frame) {
        LabelInfo.resetDone(dflt);
        LabelInfo.resetDone(labels);
        Label newDflt = createIntermediate(dflt);
        Label[] newLabels = createIntermediates(labels);
        this.mv.visitTableSwitchInsn(min, max, newDflt, newLabels);
        insertIntermediateProbes(dflt, labels, frame);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow.MethodProbesVisitor
    public void visitLookupSwitchInsnWithProbes(Label dflt, int[] keys, Label[] labels, IFrame frame) {
        LabelInfo.resetDone(dflt);
        LabelInfo.resetDone(labels);
        Label newDflt = createIntermediate(dflt);
        Label[] newLabels = createIntermediates(labels);
        this.mv.visitLookupSwitchInsn(newDflt, keys, newLabels);
        insertIntermediateProbes(dflt, labels, frame);
    }

    private Label[] createIntermediates(Label[] labels) {
        Label[] intermediates = new Label[labels.length];
        for (int i = 0; i < labels.length; i++) {
            intermediates[i] = createIntermediate(labels[i]);
        }
        return intermediates;
    }

    private Label createIntermediate(Label label) {
        Label intermediate;
        if (LabelInfo.getProbeId(label) == -1) {
            intermediate = label;
        } else if (LabelInfo.isDone(label)) {
            intermediate = LabelInfo.getIntermediateLabel(label);
        } else {
            intermediate = new Label();
            LabelInfo.setIntermediateLabel(label, intermediate);
            LabelInfo.setDone(label);
        }
        return intermediate;
    }

    private void insertIntermediateProbe(Label label, IFrame frame) {
        int probeId = LabelInfo.getProbeId(label);
        if (probeId != -1 && !LabelInfo.isDone(label)) {
            this.mv.visitLabel(LabelInfo.getIntermediateLabel(label));
            frame.accept(this.mv);
            this.probeInserter.insertProbe(probeId);
            this.mv.visitJumpInsn(Opcodes.GOTO, label);
            LabelInfo.setDone(label);
        }
    }

    private void insertIntermediateProbes(Label dflt, Label[] labels, IFrame frame) {
        LabelInfo.resetDone(dflt);
        LabelInfo.resetDone(labels);
        insertIntermediateProbe(dflt, frame);
        for (Label l : labels) {
            insertIntermediateProbe(l, frame);
        }
    }
}
