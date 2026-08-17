package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.sentry.event.Breadcrumb;
import io.sentry.event.Event;
import io.sentry.event.Sdk;
import io.sentry.event.interfaces.SentryInterface;
import io.sentry.marshaller.Marshaller;
import io.sentry.util.Util;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentCodingType;

/* loaded from: classes2.dex */
public class JsonMarshaller implements Marshaller {
    public static final String BREADCRUMBS = "breadcrumbs";
    public static final String CHECKSUM = "checksum";
    public static final String CONTEXTS = "contexts";
    public static final String CULPRIT = "culprit";
    public static final int DEFAULT_MAX_MESSAGE_LENGTH = 1000;
    public static final String DIST = "dist";
    public static final String ENVIRONMENT = "environment";
    public static final String EVENT_ID = "event_id";
    public static final String EXTRA = "extra";
    public static final String FINGERPRINT = "fingerprint";
    public static final String LEVEL = "level";
    public static final String LOGGER = "logger";
    public static final String MESSAGE = "message";
    public static final String MODULES = "modules";
    public static final String PLATFORM = "platform";
    public static final String RELEASE = "release";
    public static final String SDK = "sdk";
    public static final String SERVER_NAME = "server_name";
    public static final String TAGS = "tags";
    public static final String TIMESTAMP = "timestamp";
    public static final String TRANSACTION = "transaction";
    private boolean compression;
    private final Map<Class<? extends SentryInterface>, InterfaceBinding<?>> interfaceBindings;
    private final JsonFactory jsonFactory;
    private final int maxMessageLength;
    private static final ThreadLocal<DateFormat> ISO_FORMAT = new ThreadLocal<DateFormat>() { // from class: io.sentry.marshaller.json.JsonMarshaller.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) JsonMarshaller.class);

    public JsonMarshaller() {
        this(1000);
    }

    public JsonMarshaller(int i) {
        this.jsonFactory = new JsonFactory();
        this.interfaceBindings = new HashMap();
        this.compression = true;
        this.maxMessageLength = i;
    }

    private String formatId(UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }

    private String formatLevel(Event.Level level) {
        if (level == null) {
            return null;
        }
        switch (level) {
            case DEBUG:
                return "debug";
            case FATAL:
                return "fatal";
            case WARNING:
                return "warning";
            case INFO:
                return "info";
            case ERROR:
                return "error";
            default:
                logger.error("The level '{}' isn't supported, this should NEVER happen, contact Sentry developers", level.name());
                return null;
        }
    }

    private <T extends SentryInterface> InterfaceBinding<? super T> getInterfaceBinding(T t) {
        return (InterfaceBinding) this.interfaceBindings.get(t.getClass());
    }

    private void writeBreadcumbs(JsonGenerator jsonGenerator, List<Breadcrumb> list) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        jsonGenerator.writeObjectFieldStart(BREADCRUMBS);
        jsonGenerator.writeArrayFieldStart("values");
        for (Breadcrumb breadcrumb : list) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField(TIMESTAMP, breadcrumb.getTimestamp().getTime() / 1000);
            if (breadcrumb.getType() != null) {
                jsonGenerator.writeStringField("type", breadcrumb.getType().getValue());
            }
            if (breadcrumb.getLevel() != null) {
                jsonGenerator.writeStringField(LEVEL, breadcrumb.getLevel().getValue());
            }
            if (breadcrumb.getMessage() != null) {
                jsonGenerator.writeStringField(MESSAGE, breadcrumb.getMessage());
            }
            if (breadcrumb.getCategory() != null) {
                jsonGenerator.writeStringField("category", breadcrumb.getCategory());
            }
            if (breadcrumb.getData() != null && !breadcrumb.getData().isEmpty()) {
                jsonGenerator.writeObjectFieldStart("data");
                for (Map.Entry<String, String> entry : breadcrumb.getData().entrySet()) {
                    jsonGenerator.writeStringField(entry.getKey(), entry.getValue());
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeCollection(JsonGenerator jsonGenerator, String str, Collection<String> collection) throws IOException {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        jsonGenerator.writeArrayFieldStart(str);
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            jsonGenerator.writeString(it.next());
        }
        jsonGenerator.writeEndArray();
    }

    private void writeContent(JsonGenerator jsonGenerator, Event event) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(EVENT_ID, formatId(event.getId()));
        jsonGenerator.writeStringField(MESSAGE, Util.trimString(event.getMessage(), this.maxMessageLength));
        jsonGenerator.writeStringField(TIMESTAMP, ISO_FORMAT.get().format(event.getTimestamp()));
        jsonGenerator.writeStringField(LEVEL, formatLevel(event.getLevel()));
        jsonGenerator.writeStringField(LOGGER, event.getLogger());
        jsonGenerator.writeStringField(PLATFORM, event.getPlatform());
        jsonGenerator.writeStringField(CULPRIT, event.getCulprit());
        jsonGenerator.writeStringField(TRANSACTION, event.getTransaction());
        writeSdk(jsonGenerator, event.getSdk());
        writeTags(jsonGenerator, event.getTags());
        writeBreadcumbs(jsonGenerator, event.getBreadcrumbs());
        writeContexts(jsonGenerator, event.getContexts());
        jsonGenerator.writeStringField(SERVER_NAME, event.getServerName());
        jsonGenerator.writeStringField("release", event.getRelease());
        jsonGenerator.writeStringField("dist", event.getDist());
        jsonGenerator.writeStringField("environment", event.getEnvironment());
        writeExtras(jsonGenerator, event.getExtra());
        writeCollection(jsonGenerator, FINGERPRINT, event.getFingerprint());
        jsonGenerator.writeStringField(CHECKSUM, event.getChecksum());
        writeInterfaces(jsonGenerator, event.getSentryInterfaces());
        jsonGenerator.writeEndObject();
    }

    private void writeContexts(JsonGenerator jsonGenerator, Map<String, Map<String, Object>> map) throws IOException {
        if (map.isEmpty()) {
            return;
        }
        jsonGenerator.writeObjectFieldStart(CONTEXTS);
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            jsonGenerator.writeObjectFieldStart(entry.getKey());
            for (Map.Entry<String, Object> entry2 : entry.getValue().entrySet()) {
                jsonGenerator.writeObjectField(entry2.getKey(), entry2.getValue());
            }
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndObject();
    }

    private void writeExtras(JsonGenerator jsonGenerator, Map<String, Object> map) throws IOException {
        jsonGenerator.writeObjectFieldStart("extra");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            jsonGenerator.writeObject(entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }

    private void writeInterfaces(JsonGenerator jsonGenerator, Map<String, SentryInterface> map) throws IOException {
        for (Map.Entry<String, SentryInterface> entry : map.entrySet()) {
            SentryInterface value = entry.getValue();
            if (this.interfaceBindings.containsKey(value.getClass())) {
                jsonGenerator.writeFieldName(entry.getKey());
                getInterfaceBinding(value).writeInterface(jsonGenerator, entry.getValue());
            } else {
                logger.error("Couldn't parse the content of '{}' provided in {}.", entry.getKey(), value);
            }
        }
    }

    private void writeSdk(JsonGenerator jsonGenerator, Sdk sdk) throws IOException {
        jsonGenerator.writeObjectFieldStart(SDK);
        jsonGenerator.writeStringField("name", sdk.getName());
        jsonGenerator.writeStringField(ClientCookie.VERSION_ATTR, sdk.getVersion());
        if (sdk.getIntegrations() != null && !sdk.getIntegrations().isEmpty()) {
            jsonGenerator.writeArrayFieldStart("integrations");
            Iterator<String> it = sdk.getIntegrations().iterator();
            while (it.hasNext()) {
                jsonGenerator.writeString(it.next());
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }

    private void writeTags(JsonGenerator jsonGenerator, Map<String, String> map) throws IOException {
        jsonGenerator.writeObjectFieldStart("tags");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonGenerator.writeStringField(entry.getKey(), entry.getValue());
        }
        jsonGenerator.writeEndObject();
    }

    public <T extends SentryInterface, F extends T> void addInterfaceBinding(Class<F> cls, InterfaceBinding<T> interfaceBinding) {
        this.interfaceBindings.put(cls, interfaceBinding);
    }

    protected JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return new SentryJsonGenerator(this.jsonFactory.createGenerator(outputStream));
    }

    @Override // io.sentry.marshaller.Marshaller
    public String getContentEncoding() {
        if (isCompressed()) {
            return ContentCodingType.GZIP_VALUE;
        }
        return null;
    }

    @Override // io.sentry.marshaller.Marshaller
    public String getContentType() {
        return "application/json";
    }

    public boolean isCompressed() {
        return this.compression;
    }

    @Override // io.sentry.marshaller.Marshaller
    public void marshall(Event event, OutputStream outputStream) throws IOException {
        Marshaller.UncloseableOutputStream uncloseableOutputStream = new Marshaller.UncloseableOutputStream(outputStream);
        OutputStream gZIPOutputStream = this.compression ? new GZIPOutputStream(uncloseableOutputStream) : uncloseableOutputStream;
        try {
            try {
                try {
                    JsonGenerator createJsonGenerator = createJsonGenerator(gZIPOutputStream);
                    Throwable th = null;
                    try {
                        writeContent(createJsonGenerator, event);
                        if (createJsonGenerator != null) {
                            createJsonGenerator.close();
                        }
                        gZIPOutputStream.close();
                    } catch (Throwable th2) {
                        if (createJsonGenerator != null) {
                            if (th != null) {
                                try {
                                    createJsonGenerator.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            } else {
                                createJsonGenerator.close();
                            }
                        }
                        throw th2;
                    }
                } catch (IOException e) {
                    logger.error("An exception occurred while serialising the event.", (Throwable) e);
                    gZIPOutputStream.close();
                }
            } catch (IOException e2) {
                logger.error("An exception occurred while serialising the event.", (Throwable) e2);
            }
        } catch (Throwable th4) {
            try {
                gZIPOutputStream.close();
            } catch (IOException e3) {
                logger.error("An exception occurred while serialising the event.", (Throwable) e3);
            }
            throw th4;
        }
    }

    public void setCompression(boolean z) {
        this.compression = z;
    }
}
