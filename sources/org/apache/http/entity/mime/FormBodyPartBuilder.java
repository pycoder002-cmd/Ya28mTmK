package org.apache.http.entity.mime;

import java.util.Iterator;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

/* loaded from: classes2.dex */
public class FormBodyPartBuilder {
    private ContentBody body;
    private final Header header;
    private String name;

    FormBodyPartBuilder() {
        this.header = new Header();
    }

    FormBodyPartBuilder(String str, ContentBody contentBody) {
        this();
        this.name = str;
        this.body = contentBody;
    }

    public static FormBodyPartBuilder create() {
        return new FormBodyPartBuilder();
    }

    public static FormBodyPartBuilder create(String str, ContentBody contentBody) {
        return new FormBodyPartBuilder(str, contentBody);
    }

    public FormBodyPartBuilder addField(String str, String str2) {
        Args.notNull(str, "Field name");
        this.header.addField(new MinimalField(str, str2));
        return this;
    }

    public FormBodyPart build() {
        Asserts.notBlank(this.name, "Name");
        Asserts.notNull(this.body, "Content body");
        Header header = new Header();
        Iterator<MinimalField> it = this.header.getFields().iterator();
        while (it.hasNext()) {
            header.addField(it.next());
        }
        if (header.getField("Content-Disposition") == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("form-data; name=\"");
            sb.append(this.name);
            sb.append("\"");
            if (this.body.getFilename() != null) {
                sb.append("; filename=\"");
                sb.append(this.body.getFilename());
                sb.append("\"");
            }
            header.addField(new MinimalField("Content-Disposition", sb.toString()));
        }
        if (header.getField("Content-Type") == null) {
            ContentType contentType = this.body instanceof AbstractContentBody ? ((AbstractContentBody) this.body).getContentType() : null;
            if (contentType != null) {
                header.addField(new MinimalField("Content-Type", contentType.toString()));
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.body.getMimeType());
                if (this.body.getCharset() != null) {
                    sb2.append("; charset=");
                    sb2.append(this.body.getCharset());
                }
                header.addField(new MinimalField("Content-Type", sb2.toString()));
            }
        }
        if (header.getField("Content-Transfer-Encoding") == null) {
            header.addField(new MinimalField("Content-Transfer-Encoding", this.body.getTransferEncoding()));
        }
        return new FormBodyPart(this.name, this.body, header);
    }

    public FormBodyPartBuilder removeFields(String str) {
        Args.notNull(str, "Field name");
        this.header.removeFields(str);
        return this;
    }

    public FormBodyPartBuilder setBody(ContentBody contentBody) {
        this.body = contentBody;
        return this;
    }

    public FormBodyPartBuilder setField(String str, String str2) {
        Args.notNull(str, "Field name");
        this.header.setField(new MinimalField(str, str2));
        return this;
    }

    public FormBodyPartBuilder setName(String str) {
        this.name = str;
        return this;
    }
}
