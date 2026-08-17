package io.sentry.event.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class MessageInterface implements SentryInterface {
    public static final String MESSAGE_INTERFACE = "sentry.interfaces.Message";
    private final String formatted;
    private final String message;
    private final List<String> parameters;

    public MessageInterface(String str) {
        this(str, (List<String>) Collections.emptyList());
    }

    public MessageInterface(String str, List<String> list) {
        this(str, list, null);
    }

    public MessageInterface(String str, List<String> list, String str2) {
        this.message = str;
        this.parameters = Collections.unmodifiableList(new ArrayList(list));
        this.formatted = str2;
    }

    public MessageInterface(String str, String... strArr) {
        this(str, (List<String>) Arrays.asList(strArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageInterface messageInterface = (MessageInterface) obj;
        return Objects.equals(this.message, messageInterface.message) && Objects.equals(this.parameters, messageInterface.parameters) && Objects.equals(this.formatted, messageInterface.formatted);
    }

    public String getFormatted() {
        return this.formatted;
    }

    @Override // io.sentry.event.interfaces.SentryInterface
    public String getInterfaceName() {
        return MESSAGE_INTERFACE;
    }

    public String getMessage() {
        return this.message;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public int hashCode() {
        return Objects.hash(this.message, this.parameters, this.formatted);
    }

    public String toString() {
        return "MessageInterface{message='" + this.message + "', parameters=" + this.parameters + ", formatted=" + this.formatted + '}';
    }
}
