package io.sentry.event.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DebugMetaInterface implements SentryInterface {
    public static final String DEBUG_META_INTERFACE = "debug_meta";
    private ArrayList<DebugImage> debugImages = new ArrayList<>();

    /* loaded from: classes2.dex */
    public static class DebugImage implements Serializable {
        private static final String DEFAULT_TYPE = "proguard";
        private final String type;
        private final String uuid;

        public DebugImage(String str) {
            this(str, DEFAULT_TYPE);
        }

        public DebugImage(String str, String str2) {
            this.uuid = str;
            this.type = str2;
        }

        public String getType() {
            return this.type;
        }

        public String getUuid() {
            return this.uuid;
        }

        public String toString() {
            return "DebugImage{uuid='" + this.uuid + "', type='" + this.type + "'}";
        }
    }

    public void addDebugImage(DebugImage debugImage) {
        this.debugImages.add(debugImage);
    }

    public ArrayList<DebugImage> getDebugImages() {
        return this.debugImages;
    }

    @Override // io.sentry.event.interfaces.SentryInterface
    public String getInterfaceName() {
        return DEBUG_META_INTERFACE;
    }

    public int hashCode() {
        return this.debugImages.hashCode();
    }

    public String toString() {
        return "DebugMetaInterface{debugImages=" + this.debugImages + '}';
    }
}
