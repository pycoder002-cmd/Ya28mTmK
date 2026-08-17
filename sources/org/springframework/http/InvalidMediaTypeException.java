package org.springframework.http;

/* loaded from: classes2.dex */
public class InvalidMediaTypeException extends IllegalArgumentException {
    private String mediaType;

    public InvalidMediaTypeException(String str, String str2) {
        super("Invalid media type \"" + str + "\": " + str2);
        this.mediaType = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }
}
