package org.jacoco.agent.rt.internal_b0d6a23.asm.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Handle;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Label;
import org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Type;
import org.jacoco.agent.rt.internal_b0d6a23.core.internal.instr.InstrSupport;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/asm/commons/AnalyzerAdapter.class */
public class AnalyzerAdapter extends MethodVisitor {
    public List<Object> locals;
    public List<Object> stack;
    private List<Label> labels;
    public Map<Object, Object> uninitializedTypes;
    private int maxStack;
    private int maxLocals;
    private String owner;

    public AnalyzerAdapter(String owner, int access, String name, String desc, MethodVisitor mv) {
        this(327680, owner, access, name, desc, mv);
        if (getClass() != AnalyzerAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected AnalyzerAdapter(int api, String owner, int access, String name, String desc, MethodVisitor mv) {
        super(api, mv);
        this.owner = owner;
        this.locals = new ArrayList();
        this.stack = new ArrayList();
        this.uninitializedTypes = new HashMap();
        if ((access & 8) == 0) {
            if ("<init>".equals(name)) {
                this.locals.add(Opcodes.UNINITIALIZED_THIS);
            } else {
                this.locals.add(owner);
            }
        }
        Type[] types = Type.getArgumentTypes(desc);
        for (int i = 0; i < types.length; i++) {
            Type type = types[i];
            switch (type.getSort()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    this.locals.add(Opcodes.INTEGER);
                    break;
                case 6:
                    this.locals.add(Opcodes.FLOAT);
                    break;
                case 7:
                    this.locals.add(Opcodes.LONG);
                    this.locals.add(Opcodes.TOP);
                    break;
                case 8:
                    this.locals.add(Opcodes.DOUBLE);
                    this.locals.add(Opcodes.TOP);
                    break;
                case 9:
                    this.locals.add(types[i].getDescriptor());
                    break;
                default:
                    this.locals.add(types[i].getInternalName());
                    break;
            }
        }
        this.maxLocals = this.locals.size();
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        if (type != -1) {
            throw new IllegalStateException("ClassReader.accept() should be called with EXPAND_FRAMES flag");
        }
        if (this.mv != null) {
            this.mv.visitFrame(type, nLocal, local, nStack, stack);
        }
        if (this.locals != null) {
            this.locals.clear();
            this.stack.clear();
        } else {
            this.locals = new ArrayList();
            this.stack = new ArrayList();
        }
        visitFrameTypes(nLocal, local, this.locals);
        visitFrameTypes(nStack, stack, this.stack);
        this.maxStack = Math.max(this.maxStack, this.stack.size());
    }

    private static void visitFrameTypes(int n, Object[] types, List<Object> result) {
        for (int i = 0; i < n; i++) {
            Object type = types[i];
            result.add(type);
            if (type == Opcodes.LONG || type == Opcodes.DOUBLE) {
                result.add(Opcodes.TOP);
            }
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitInsn(int opcode) {
        if (this.mv != null) {
            this.mv.visitInsn(opcode);
        }
        execute(opcode, 0, null);
        if ((opcode >= 172 && opcode <= 177) || opcode == 191) {
            this.locals = null;
            this.stack = null;
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitIntInsn(int opcode, int operand) {
        if (this.mv != null) {
            this.mv.visitIntInsn(opcode, operand);
        }
        execute(opcode, operand, null);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitVarInsn(int opcode, int var) {
        if (this.mv != null) {
            this.mv.visitVarInsn(opcode, var);
        }
        execute(opcode, var, null);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == 187) {
            if (this.labels == null) {
                Label l = new Label();
                this.labels = new ArrayList(3);
                this.labels.add(l);
                if (this.mv != null) {
                    this.mv.visitLabel(l);
                }
            }
            for (int i = 0; i < this.labels.size(); i++) {
                this.uninitializedTypes.put(this.labels.get(i), type);
            }
        }
        if (this.mv != null) {
            this.mv.visitTypeInsn(opcode, type);
        }
        execute(opcode, 0, type);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(opcode, owner, name, desc);
        }
        execute(opcode, 0, desc);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    @Deprecated
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        if (this.api >= 327680) {
            super.visitMethodInsn(opcode, owner, name, desc);
        } else {
            doVisitMethodInsn(opcode, owner, name, desc, opcode == 185);
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (this.api < 327680) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        } else {
            doVisitMethodInsn(opcode, owner, name, desc, itf);
        }
    }

    private void doVisitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        Object u;
        if (this.mv != null) {
            this.mv.visitMethodInsn(opcode, owner, name, desc, itf);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        pop(desc);
        if (opcode != 184) {
            Object t = pop();
            if (opcode == 183 && name.charAt(0) == '<') {
                if (t == Opcodes.UNINITIALIZED_THIS) {
                    u = this.owner;
                } else {
                    u = this.uninitializedTypes.get(t);
                }
                for (int i = 0; i < this.locals.size(); i++) {
                    if (this.locals.get(i) == t) {
                        this.locals.set(i, u);
                    }
                }
                for (int i2 = 0; i2 < this.stack.size(); i2++) {
                    if (this.stack.get(i2) == t) {
                        this.stack.set(i2, u);
                    }
                }
            }
        }
        pushDesc(desc);
        this.labels = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        pop(desc);
        pushDesc(desc);
        this.labels = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitJumpInsn(int opcode, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(opcode, label);
        }
        execute(opcode, 0, null);
        if (opcode == 167) {
            this.locals = null;
            this.stack = null;
        }
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
        if (this.labels == null) {
            this.labels = new ArrayList(3);
        }
        this.labels.add(label);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLdcInsn(Object cst) {
        if (this.mv != null) {
            this.mv.visitLdcInsn(cst);
        }
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        if (cst instanceof Integer) {
            push(Opcodes.INTEGER);
        } else if (cst instanceof Long) {
            push(Opcodes.LONG);
            push(Opcodes.TOP);
        } else if (cst instanceof Float) {
            push(Opcodes.FLOAT);
        } else if (cst instanceof Double) {
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
        } else if (cst instanceof String) {
            push("java/lang/String");
        } else if (cst instanceof Type) {
            int sort = ((Type) cst).getSort();
            if (sort == 10 || sort == 9) {
                push("java/lang/Class");
            } else if (sort == 11) {
                push("java/lang/invoke/MethodType");
            } else {
                throw new IllegalArgumentException();
            }
        } else if (cst instanceof Handle) {
            push("java/lang/invoke/MethodHandle");
        } else {
            throw new IllegalArgumentException();
        }
        this.labels = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitIincInsn(int var, int increment) {
        if (this.mv != null) {
            this.mv.visitIincInsn(var, increment);
        }
        execute(Opcodes.IINC, var, null);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(min, max, dflt, labels);
        }
        execute(Opcodes.TABLESWITCH, 0, null);
        this.locals = null;
        this.stack = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(dflt, keys, labels);
        }
        execute(171, 0, null);
        this.locals = null;
        this.stack = null;
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String desc, int dims) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(desc, dims);
        }
        execute(Opcodes.MULTIANEWARRAY, dims, desc);
    }

    @Override // org.jacoco.agent.rt.internal_b0d6a23.asm.MethodVisitor
    public void visitMaxs(int maxStack, int maxLocals) {
        if (this.mv != null) {
            this.maxStack = Math.max(this.maxStack, maxStack);
            this.maxLocals = Math.max(this.maxLocals, maxLocals);
            this.mv.visitMaxs(this.maxStack, this.maxLocals);
        }
    }

    private Object get(int local) {
        this.maxLocals = Math.max(this.maxLocals, local + 1);
        return local < this.locals.size() ? this.locals.get(local) : Opcodes.TOP;
    }

    private void set(int local, Object type) {
        this.maxLocals = Math.max(this.maxLocals, local + 1);
        while (local >= this.locals.size()) {
            this.locals.add(Opcodes.TOP);
        }
        this.locals.set(local, type);
    }

    private void push(Object type) {
        this.stack.add(type);
        this.maxStack = Math.max(this.maxStack, this.stack.size());
    }

    private void pushDesc(String desc) {
        int index = desc.charAt(0) == '(' ? desc.indexOf(41) + 1 : 0;
        switch (desc.charAt(index)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                push(Opcodes.INTEGER);
                return;
            case 'D':
                push(Opcodes.DOUBLE);
                push(Opcodes.TOP);
                return;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
                if (index == 0) {
                    push(desc.substring(1, desc.length() - 1));
                    return;
                } else {
                    push(desc.substring(index + 1, desc.length() - 1));
                    return;
                }
            case 'F':
                push(Opcodes.FLOAT);
                return;
            case 'J':
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                return;
            case 'V':
                return;
            case '[':
                if (index == 0) {
                    push(desc);
                    return;
                } else {
                    push(desc.substring(index, desc.length()));
                    return;
                }
        }
    }

    private Object pop() {
        return this.stack.remove(this.stack.size() - 1);
    }

    private void pop(int n) {
        int size = this.stack.size();
        int end = size - n;
        for (int i = size - 1; i >= end; i--) {
            this.stack.remove(i);
        }
    }

    private void pop(String desc) {
        char c = desc.charAt(0);
        if (c != '(') {
            if (c == 'J' || c == 'D') {
                pop(2);
                return;
            } else {
                pop(1);
                return;
            }
        }
        int n = 0;
        Type[] types = Type.getArgumentTypes(desc);
        for (Type type : types) {
            n += type.getSize();
        }
        pop(n);
    }

    private void execute(int opcode, int iarg, String sarg) {
        Object t2;
        Object t22;
        if (this.locals == null) {
            this.labels = null;
            return;
        }
        switch (opcode) {
            case 0:
            case 116:
            case 117:
            case 118:
            case 119:
            case Opcodes.I2B /* 145 */:
            case Opcodes.I2C /* 146 */:
            case Opcodes.I2S /* 147 */:
            case Opcodes.GOTO /* 167 */:
            case Opcodes.RETURN /* 177 */:
                break;
            case 1:
                push(Opcodes.NULL);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 16:
            case 17:
                push(Opcodes.INTEGER);
                break;
            case 9:
            case 10:
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                break;
            case 11:
            case 12:
            case 13:
                push(Opcodes.FLOAT);
                break;
            case 14:
            case 15:
                push(Opcodes.DOUBLE);
                push(Opcodes.TOP);
                break;
            case 18:
            case 19:
            case 20:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case Opcodes.INVOKEVIRTUAL /* 182 */:
            case Opcodes.INVOKESPECIAL /* 183 */:
            case Opcodes.INVOKESTATIC /* 184 */:
            case Opcodes.INVOKEINTERFACE /* 185 */:
            case Opcodes.INVOKEDYNAMIC /* 186 */:
            case 196:
            case Opcodes.MULTIANEWARRAY /* 197 */:
            default:
                pop(iarg);
                pushDesc(sarg);
                break;
            case 21:
            case 23:
            case 25:
                push(get(iarg));
                break;
            case 22:
            case 24:
                push(get(iarg));
                push(Opcodes.TOP);
                break;
            case 46:
            case 51:
            case 52:
            case 53:
                pop(2);
                push(Opcodes.INTEGER);
                break;
            case 47:
            case Opcodes.D2L /* 143 */:
                pop(2);
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                break;
            case 48:
                pop(2);
                push(Opcodes.FLOAT);
                break;
            case 49:
            case Opcodes.L2D /* 138 */:
                pop(2);
                push(Opcodes.DOUBLE);
                push(Opcodes.TOP);
                break;
            case 50:
                pop(1);
                Object t1 = pop();
                if (t1 instanceof String) {
                    pushDesc(((String) t1).substring(1));
                    break;
                } else {
                    push("java/lang/Object");
                    break;
                }
            case 54:
            case 56:
            case 58:
                Object t12 = pop();
                set(iarg, t12);
                if (iarg > 0 && ((t22 = get(iarg - 1)) == Opcodes.LONG || t22 == Opcodes.DOUBLE)) {
                    set(iarg - 1, Opcodes.TOP);
                    break;
                }
                break;
            case 55:
            case 57:
                pop(1);
                Object t13 = pop();
                set(iarg, t13);
                set(iarg + 1, Opcodes.TOP);
                if (iarg > 0 && ((t2 = get(iarg - 1)) == Opcodes.LONG || t2 == Opcodes.DOUBLE)) {
                    set(iarg - 1, Opcodes.TOP);
                    break;
                }
                break;
            case 79:
            case 81:
            case 83:
            case 84:
            case 85:
            case 86:
                pop(3);
                break;
            case 80:
            case 82:
                pop(4);
                break;
            case 87:
            case Opcodes.IFEQ /* 153 */:
            case Opcodes.IFNE /* 154 */:
            case Opcodes.IFLT /* 155 */:
            case Opcodes.IFGE /* 156 */:
            case Opcodes.IFGT /* 157 */:
            case Opcodes.IFLE /* 158 */:
            case Opcodes.TABLESWITCH /* 170 */:
            case 171:
            case Opcodes.IRETURN /* 172 */:
            case Opcodes.FRETURN /* 174 */:
            case Opcodes.ARETURN /* 176 */:
            case Opcodes.ATHROW /* 191 */:
            case Opcodes.MONITORENTER /* 194 */:
            case Opcodes.MONITOREXIT /* 195 */:
            case Opcodes.IFNULL /* 198 */:
            case Opcodes.IFNONNULL /* 199 */:
                pop(1);
                break;
            case 88:
            case Opcodes.IF_ICMPEQ /* 159 */:
            case Opcodes.IF_ICMPNE /* 160 */:
            case Opcodes.IF_ICMPLT /* 161 */:
            case Opcodes.IF_ICMPGE /* 162 */:
            case Opcodes.IF_ICMPGT /* 163 */:
            case Opcodes.IF_ICMPLE /* 164 */:
            case Opcodes.IF_ACMPEQ /* 165 */:
            case Opcodes.IF_ACMPNE /* 166 */:
            case Opcodes.LRETURN /* 173 */:
            case Opcodes.DRETURN /* 175 */:
                pop(2);
                break;
            case 89:
                Object t14 = pop();
                push(t14);
                push(t14);
                break;
            case 90:
                Object t15 = pop();
                Object t23 = pop();
                push(t15);
                push(t23);
                push(t15);
                break;
            case 91:
                Object t16 = pop();
                Object t24 = pop();
                Object t3 = pop();
                push(t16);
                push(t3);
                push(t24);
                push(t16);
                break;
            case 92:
                Object t17 = pop();
                Object t25 = pop();
                push(t25);
                push(t17);
                push(t25);
                push(t17);
                break;
            case 93:
                Object t18 = pop();
                Object t26 = pop();
                Object t32 = pop();
                push(t26);
                push(t18);
                push(t32);
                push(t26);
                push(t18);
                break;
            case 94:
                Object t19 = pop();
                Object t27 = pop();
                Object t33 = pop();
                Object t4 = pop();
                push(t27);
                push(t19);
                push(t4);
                push(t33);
                push(t27);
                push(t19);
                break;
            case 95:
                Object t110 = pop();
                Object t28 = pop();
                push(t110);
                push(t28);
                break;
            case 96:
            case 100:
            case 104:
            case 108:
            case 112:
            case Opcodes.ISHL /* 120 */:
            case Opcodes.ISHR /* 122 */:
            case Opcodes.IUSHR /* 124 */:
            case Opcodes.IAND /* 126 */:
            case 128:
            case Opcodes.IXOR /* 130 */:
            case Opcodes.L2I /* 136 */:
            case Opcodes.D2I /* 142 */:
            case Opcodes.FCMPL /* 149 */:
            case Opcodes.FCMPG /* 150 */:
                pop(2);
                push(Opcodes.INTEGER);
                break;
            case 97:
            case 101:
            case 105:
            case 109:
            case 113:
            case Opcodes.LAND /* 127 */:
            case Opcodes.LOR /* 129 */:
            case Opcodes.LXOR /* 131 */:
                pop(4);
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                break;
            case 98:
            case 102:
            case 106:
            case 110:
            case 114:
            case Opcodes.L2F /* 137 */:
            case Opcodes.D2F /* 144 */:
                pop(2);
                push(Opcodes.FLOAT);
                break;
            case 99:
            case 103:
            case 107:
            case 111:
            case 115:
                pop(4);
                push(Opcodes.DOUBLE);
                push(Opcodes.TOP);
                break;
            case Opcodes.LSHL /* 121 */:
            case Opcodes.LSHR /* 123 */:
            case Opcodes.LUSHR /* 125 */:
                pop(3);
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                break;
            case Opcodes.IINC /* 132 */:
                set(iarg, Opcodes.INTEGER);
                break;
            case Opcodes.I2L /* 133 */:
            case Opcodes.F2L /* 140 */:
                pop(1);
                push(Opcodes.LONG);
                push(Opcodes.TOP);
                break;
            case Opcodes.I2F /* 134 */:
                pop(1);
                push(Opcodes.FLOAT);
                break;
            case Opcodes.I2D /* 135 */:
            case Opcodes.F2D /* 141 */:
                pop(1);
                push(Opcodes.DOUBLE);
                push(Opcodes.TOP);
                break;
            case Opcodes.F2I /* 139 */:
            case Opcodes.ARRAYLENGTH /* 190 */:
            case Opcodes.INSTANCEOF /* 193 */:
                pop(1);
                push(Opcodes.INTEGER);
                break;
            case Opcodes.LCMP /* 148 */:
            case Opcodes.DCMPL /* 151 */:
            case Opcodes.DCMPG /* 152 */:
                pop(4);
                push(Opcodes.INTEGER);
                break;
            case Opcodes.JSR /* 168 */:
            case Opcodes.RET /* 169 */:
                throw new RuntimeException("JSR/RET are not supported");
            case Opcodes.GETSTATIC /* 178 */:
                pushDesc(sarg);
                break;
            case Opcodes.PUTSTATIC /* 179 */:
                pop(sarg);
                break;
            case Opcodes.GETFIELD /* 180 */:
                pop(1);
                pushDesc(sarg);
                break;
            case Opcodes.PUTFIELD /* 181 */:
                pop(sarg);
                pop();
                break;
            case Opcodes.NEW /* 187 */:
                push(this.labels.get(0));
                break;
            case Opcodes.NEWARRAY /* 188 */:
                pop();
                switch (iarg) {
                    case 4:
                        pushDesc(InstrSupport.DATAFIELD_DESC);
                        break;
                    case 5:
                        pushDesc("[C");
                        break;
                    case 6:
                        pushDesc("[F");
                        break;
                    case 7:
                        pushDesc("[D");
                        break;
                    case 8:
                        pushDesc("[B");
                        break;
                    case 9:
                        pushDesc("[S");
                        break;
                    case 10:
                        pushDesc("[I");
                        break;
                    default:
                        pushDesc("[J");
                        break;
                }
            case Opcodes.ANEWARRAY /* 189 */:
                pop();
                pushDesc("[" + Type.getObjectType(sarg));
                break;
            case Opcodes.CHECKCAST /* 192 */:
                pop();
                pushDesc(Type.getObjectType(sarg).getDescriptor());
                break;
        }
        this.labels = null;
    }
}
