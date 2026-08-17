package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import io.sentry.event.interfaces.DebugMetaInterface;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class DebugMetaInterfaceBinding implements InterfaceBinding<DebugMetaInterface> {
    private static final String IMAGES = "images";
    private static final String TYPE = "type";
    private static final String UUID = "uuid";

    private void writeDebugImages(JsonGenerator jsonGenerator, DebugMetaInterface debugMetaInterface) throws IOException {
        jsonGenerator.writeArrayFieldStart(IMAGES);
        Iterator<DebugMetaInterface.DebugImage> it = debugMetaInterface.getDebugImages().iterator();
        while (it.hasNext()) {
            DebugMetaInterface.DebugImage next = it.next();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField(UUID, next.getUuid());
            jsonGenerator.writeStringField(TYPE, next.getType());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

    @Override // io.sentry.marshaller.json.InterfaceBinding
    public void writeInterface(JsonGenerator jsonGenerator, DebugMetaInterface debugMetaInterface) throws IOException {
        jsonGenerator.writeStartObject();
        writeDebugImages(jsonGenerator, debugMetaInterface);
        jsonGenerator.writeEndObject();
    }
}
