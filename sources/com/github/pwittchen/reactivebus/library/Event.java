package com.github.pwittchen.reactivebus.library;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/* loaded from: classes.dex */
public final class Event {
    private final Serializable data;
    private final String id;
    private final String name;

    /* loaded from: classes.dex */
    public static final class Builder {
        private Serializable data;
        private String id;
        private String name;

        private Builder() {
            this.id = UUID.randomUUID().toString();
            this.name = "";
        }

        public Event build() {
            return new Event(this);
        }

        public Builder data(Serializable serializable) {
            this.data = serializable;
            return this;
        }

        public Builder id(String str) {
            this.id = str;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }
    }

    private Event() {
        this(builder());
    }

    private Event(Builder builder) {
        this(builder.id, builder.name, builder.data);
    }

    private Event(String str, String str2, Serializable serializable) {
        this.id = str;
        this.name = str2;
        this.data = serializable;
    }

    private static Builder builder() {
        return new Builder();
    }

    public static Event create() {
        return builder().build();
    }

    public static Builder data(Serializable serializable) {
        return builder().data(serializable);
    }

    public static Builder id(String str) {
        return builder().id(str);
    }

    public static Builder name(String str) {
        return builder().name(str);
    }

    public Serializable data() {
        return this.data;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        return Objects.equals(this.id, event.id) && Objects.equals(this.name, event.name);
    }

    public boolean hasData() {
        return this.data != null;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        return "Event {id='" + this.id + "', name='" + this.name + "'}";
    }
}
