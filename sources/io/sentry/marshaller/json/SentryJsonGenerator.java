package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import io.sentry.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class SentryJsonGenerator extends JsonGenerator {
    private static final String ELIDED = "...";
    private static final int MAX_LENGTH_LIST = 10;
    private static final int MAX_LENGTH_STRING = 400;
    private static final int MAX_NESTING = 3;
    private static final int MAX_SIZE_MAP = 50;
    private static final String RECURSION_LIMIT_HIT = "<recursion limit hit>";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) Util.class);
    private JsonGenerator generator;
    private int maxLengthList = 10;
    private int maxLengthString = 400;
    private int maxSizeMap = 50;
    private int maxNesting = 3;

    public SentryJsonGenerator(JsonGenerator jsonGenerator) {
        this.generator = jsonGenerator;
    }

    private void writeArray(Object obj, int i) throws IOException {
        int i2 = 0;
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            while (i2 < bArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber((int) bArr[i2]);
                i2++;
            }
            if (bArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            while (i2 < sArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber((int) sArr[i2]);
                i2++;
            }
            if (sArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            while (i2 < iArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber(iArr[i2]);
                i2++;
            }
            if (iArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            while (i2 < jArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber(jArr[i2]);
                i2++;
            }
            if (jArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            while (i2 < fArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber(fArr[i2]);
                i2++;
            }
            if (fArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            while (i2 < dArr.length && i2 < this.maxLengthList) {
                this.generator.writeNumber(dArr[i2]);
                i2++;
            }
            if (dArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            while (i2 < cArr.length && i2 < this.maxLengthList) {
                this.generator.writeString(String.valueOf(cArr[i2]));
                i2++;
            }
            if (cArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            while (i2 < zArr.length && i2 < this.maxLengthList) {
                this.generator.writeBoolean(zArr[i2]);
                i2++;
            }
            if (zArr.length > this.maxLengthList) {
                writeElided();
                return;
            }
            return;
        }
        Object[] objArr = (Object[]) obj;
        while (i2 < objArr.length && i2 < this.maxLengthList) {
            writeObject(objArr[i2], i + 1);
            i2++;
        }
        if (objArr.length > this.maxLengthList) {
            writeElided();
        }
    }

    private void writeElided() throws IOException {
        this.generator.writeString(ELIDED);
    }

    private void writeObject(Object obj, int i) throws IOException {
        if (i >= this.maxNesting) {
            this.generator.writeString(RECURSION_LIMIT_HIT);
            return;
        }
        if (obj == null) {
            this.generator.writeNull();
            return;
        }
        if (obj.getClass().isArray()) {
            this.generator.writeStartArray();
            writeArray(obj, i);
            this.generator.writeEndArray();
            return;
        }
        int i2 = 0;
        if (obj instanceof Map) {
            this.generator.writeStartObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (i2 >= this.maxSizeMap) {
                    break;
                }
                if (entry.getKey() == null) {
                    this.generator.writeFieldName("null");
                } else {
                    this.generator.writeFieldName(Util.trimString(entry.getKey().toString(), this.maxLengthString));
                }
                writeObject(entry.getValue(), i + 1);
                i2++;
            }
            this.generator.writeEndObject();
            return;
        }
        if (!(obj instanceof Collection)) {
            if (obj instanceof String) {
                this.generator.writeString(Util.trimString((String) obj, this.maxLengthString));
                return;
            }
            try {
                this.generator.writeObject(obj);
                return;
            } catch (IllegalStateException unused) {
                logger.debug("Couldn't marshal '{}' of type '{}', had to be converted into a String", obj, obj.getClass());
                this.generator.writeString(Util.trimString(obj.toString(), this.maxLengthString));
                return;
            }
        }
        this.generator.writeStartArray();
        Iterator it = ((Collection) obj).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (i2 >= this.maxLengthList) {
                writeElided();
                break;
            } else {
                writeObject(next, i + 1);
                i2++;
            }
        }
        this.generator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.generator.close();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        return this.generator.disable(feature);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        return this.generator.enable(feature);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
        this.generator.flush();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public ObjectCodec getCodec() {
        return this.generator.getCodec();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getFeatureMask() {
        return this.generator.getFeatureMask();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this.generator.getOutputContext();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean isClosed() {
        return this.generator.isClosed();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this.generator.isEnabled(feature);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        return this.generator.setCodec(objectCodec);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setFeatureMask(int i) {
        return this.generator.setFeatureMask(i);
    }

    public void setMaxLengthList(int i) {
        this.maxLengthList = i;
    }

    public void setMaxLengthString(int i) {
        this.maxLengthString = i;
    }

    public void setMaxNesting(int i) {
        this.maxNesting = i;
    }

    public void setMaxSizeMap(int i) {
        this.maxSizeMap = i;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return this.generator.useDefaultPrettyPrinter();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return this.generator.version();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException {
        return this.generator.writeBinary(base64Variant, inputStream, i);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException {
        this.generator.writeBinary(base64Variant, bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
        this.generator.writeBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndArray() throws IOException {
        this.generator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeEndObject() throws IOException {
        this.generator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException {
        this.generator.writeFieldName(serializableString);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) throws IOException {
        this.generator.writeFieldName(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() throws IOException {
        this.generator.writeNull();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d) throws IOException {
        this.generator.writeNumber(d);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f) throws IOException {
        this.generator.writeNumber(f);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i) throws IOException {
        this.generator.writeNumber(i);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j) throws IOException {
        this.generator.writeNumber(j);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) throws IOException {
        this.generator.writeNumber(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        this.generator.writeNumber(bigDecimal);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException {
        this.generator.writeNumber(bigInteger);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeObject(Object obj) throws IOException {
        writeObject(obj, 0);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c) throws IOException {
        this.generator.writeRaw(c);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) throws IOException {
        this.generator.writeRaw(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException {
        this.generator.writeRaw(str, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException {
        this.generator.writeRaw(cArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException {
        this.generator.writeRawUTF8String(bArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) throws IOException {
        this.generator.writeRawValue(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException {
        this.generator.writeRawValue(str, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException {
        this.generator.writeRawValue(cArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray() throws IOException {
        this.generator.writeStartArray();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject() throws IOException {
        this.generator.writeStartObject();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException {
        this.generator.writeString(serializableString);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) throws IOException {
        this.generator.writeString(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException {
        this.generator.writeString(cArr, i, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeTree(TreeNode treeNode) throws IOException {
        this.generator.writeTree(treeNode);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException {
        this.generator.writeUTF8String(bArr, i, i2);
    }
}
