package org.jacoco.agent.rt.internal_b0d6a23.core.internal.flow;

import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Label;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.jacoco.agent.rt.internal_b0d6a23.asm.commons.AnalyzerAdapter;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/flow/MethodProbesAdapter.class */
public final class MethodProbesAdapter extends MethodVisitor {
    private final MethodProbesVisitor probesVisitor;
    private final IProbeIdGenerator idGenerator;
    private AnalyzerAdapter analyzer;
    private final Map<Label, Label> tryCatchProbeLabels;

    public MethodProbesAdapter(MethodProbesVisitor probesVisitor, IProbeIdGenerator idGenerator) {
        super(327680, probesVisitor);
        this.probesVisitor = probesVisitor;
        this.idGenerator = idGenerator;
        this.tryCatchProbeLabels = new HashMap();
    }

    public void setAnalyzer(AnalyzerAdapter analyzer) {
        this.analyzer = analyzer;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        if (this.tryCatchProbeLabels.containsKey(start)) {
            start = this.tryCatchProbeLabels.get(start);
        } else if (LabelInfo.needsProbe(start)) {
            Label probeLabel = new Label();
            LabelInfo.setSuccessor(probeLabel);
            this.tryCatchProbeLabels.put(start, probeLabel);
            start = probeLabel;
        }
        this.probesVisitor.visitTryCatchBlock(start, end, handler, type);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLabel(Label label) {
        if (LabelInfo.needsProbe(label)) {
            if (this.tryCatchProbeLabels.containsKey(label)) {
                this.probesVisitor.visitLabel(this.tryCatchProbeLabels.get(label));
            }
            this.probesVisitor.visitProbe(this.idGenerator.nextId());
        }
        this.probesVisitor.visitLabel(label);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitInsn(int opcode) {
        switch (opcode) {
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.DRETURN /* 175 */:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.RETURN /* 177 */:
            case Opcodes.ATHROW /* 191 */:
                this.probesVisitor.visitInsnWithProbe(opcode, this.idGenerator.nextId());
                return;
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
                this.probesVisitor.visitInsn(opcode);
                return;
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitJumpInsn(int opcode, Label label) {
        if (LabelInfo.isMultiTarget(label)) {
            this.probesVisitor.visitJumpInsnWithProbe(opcode, label, this.idGenerator.nextId(), frame(jumpPopCount(opcode)));
        } else {
            this.probesVisitor.visitJumpInsn(opcode, label);
        }
    }

    private int jumpPopCount(int opcode) {
        switch (opcode) {
            case Opcodes.IFEQ /* 153 */:
            case Opcodes.IFNE /* 154 */:
            case Opcodes.IFLT /* 155 */:
            case Opcodes.IFGE /* 156 */:
            case Opcodes.IFGT /* 157 */:
            case Opcodes.IFLE /* 158 */:
            case Opcodes.IFNULL /* 198 */:
            case Opcodes.IFNONNULL /* 199 */:
                return 1;
            case Opcodes.GOTO /* 167 */:
                return 0;
            default:
                return 2;
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        if (markLabels(dflt, labels)) {
            this.probesVisitor.visitLookupSwitchInsnWithProbes(dflt, keys, labels, frame(1));
        } else {
            this.probesVisitor.visitLookupSwitchInsn(dflt, keys, labels);
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        if (markLabels(dflt, labels)) {
            this.probesVisitor.visitTableSwitchInsnWithProbes(min, max, dflt, labels, frame(1));
        } else {
            this.probesVisitor.visitTableSwitchInsn(min, max, dflt, labels);
        }
    }

    private boolean markLabels(Label dflt, Label[] labels) {
        boolean probe = false;
        LabelInfo.resetDone(labels);
        if (LabelInfo.isMultiTarget(dflt)) {
            LabelInfo.setProbeId(dflt, this.idGenerator.nextId());
            probe = true;
        }
        LabelInfo.setDone(dflt);
        for (Label l : labels) {
            if (LabelInfo.isMultiTarget(l) && !LabelInfo.isDone(l)) {
                LabelInfo.setProbeId(l, this.idGenerator.nextId());
                probe = true;
            }
            LabelInfo.setDone(l);
        }
        return probe;
    }

    private IFrame frame(int popCount) {
        return FrameSnapshot.create(this.analyzer, popCount);
    }
}
