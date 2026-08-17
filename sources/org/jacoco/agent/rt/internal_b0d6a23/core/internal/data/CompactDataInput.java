package org.jacoco.agent.rt.internal_b0d6a23.core.internal.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/data/CompactDataInput.class */
public class CompactDataInput extends DataInputStream {
    public CompactDataInput(InputStream in) {
        super(in);
    }

    public int readVarInt() throws IOException {
        int value = 255 & readByte();
        if ((value & 128) == 0) {
            return value;
        }
        return (value & Opcodes.LAND) | (readVarInt() << 7);
    }

    public boolean[] readBooleanArray() throws IOException {
        boolean[] value = new boolean[readVarInt()];
        int buffer = 0;
        for (int i = 0; i < value.length; i++) {
            if (i % 8 == 0) {
                buffer = readByte();
            }
            value[i] = (buffer & 1) != 0;
            buffer >>>= 1;
        }
        return value;
    }
}
