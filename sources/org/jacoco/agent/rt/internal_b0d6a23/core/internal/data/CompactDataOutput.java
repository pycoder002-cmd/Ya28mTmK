package org.jacoco.agent.rt.internal_b0d6a23.core.internal.data;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: apklis.apk:jacocoagent.jar:org/jacoco/agent/rt/internal_b0d6a23/core/internal/data/CompactDataOutput.class */
public class CompactDataOutput extends DataOutputStream {
    public CompactDataOutput(OutputStream out) {
        super(out);
    }

    public void writeVarInt(int value) throws IOException {
        if ((value & (-128)) == 0) {
            writeByte(value);
        } else {
            writeByte(128 | (value & Opcodes.LAND));
            writeVarInt(value >>> 7);
        }
    }

    public void writeBooleanArray(boolean[] value) throws IOException {
        writeVarInt(value.length);
        int buffer = 0;
        int bufferSize = 0;
        for (boolean b : value) {
            if (b) {
                buffer |= 1 << bufferSize;
            }
            bufferSize++;
            if (bufferSize == 8) {
                writeByte(buffer);
                buffer = 0;
                bufferSize = 0;
            }
        }
        if (bufferSize > 0) {
            writeByte(buffer);
        }
    }
}
