package org.springframework.http.converter.xml;

import org.springframework.http.converter.FormHttpMessageConverter;

@Deprecated
/* loaded from: classes2.dex */
public class XmlAwareFormHttpMessageConverter extends FormHttpMessageConverter {
    public XmlAwareFormHttpMessageConverter() {
        addPartConverter(new SourceHttpMessageConverter());
    }
}
