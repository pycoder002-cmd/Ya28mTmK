package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import io.sentry.event.interfaces.MessageInterface;
import io.sentry.util.Util;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class MessageInterfaceBinding implements InterfaceBinding<MessageInterface> {
    public static final int DEFAULT_MAX_MESSAGE_LENGTH = 1000;
    private static final String FORMATTED_PARAMETER = "formatted";
    private static final String MESSAGE_PARAMETER = "message";
    private static final String PARAMS_PARAMETER = "params";
    private final int maxMessageLength;

    public MessageInterfaceBinding() {
        this.maxMessageLength = 1000;
    }

    public MessageInterfaceBinding(int i) {
        this.maxMessageLength = i;
    }

    @Override // io.sentry.marshaller.json.InterfaceBinding
    public void writeInterface(JsonGenerator jsonGenerator, MessageInterface messageInterface) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("message", Util.trimString(messageInterface.getMessage(), this.maxMessageLength));
        jsonGenerator.writeArrayFieldStart(PARAMS_PARAMETER);
        Iterator<String> it = messageInterface.getParameters().iterator();
        while (it.hasNext()) {
            jsonGenerator.writeString(it.next());
        }
        jsonGenerator.writeEndArray();
        if (messageInterface.getFormatted() != null) {
            jsonGenerator.writeStringField(FORMATTED_PARAMETER, Util.trimString(messageInterface.getFormatted(), this.maxMessageLength));
        }
        jsonGenerator.writeEndObject();
    }
}
