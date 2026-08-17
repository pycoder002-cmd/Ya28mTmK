package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import io.sentry.event.interfaces.UserInterface;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes2.dex */
public class UserInterfaceBinding implements InterfaceBinding<UserInterface> {
    private static final String DATA = "data";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String IP_ADDRESS = "ip_address";
    private static final String USERNAME = "username";

    @Override // io.sentry.marshaller.json.InterfaceBinding
    public void writeInterface(JsonGenerator jsonGenerator, UserInterface userInterface) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", userInterface.getId());
        jsonGenerator.writeStringField(USERNAME, userInterface.getUsername());
        jsonGenerator.writeStringField("email", userInterface.getEmail());
        jsonGenerator.writeStringField(IP_ADDRESS, userInterface.getIpAddress());
        if (userInterface.getData() != null && !userInterface.getData().isEmpty()) {
            jsonGenerator.writeObjectFieldStart(DATA);
            for (Map.Entry<String, Object> entry : userInterface.getData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    jsonGenerator.writeNullField(key);
                } else {
                    jsonGenerator.writeObjectField(key, value);
                }
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndObject();
    }
}
