package org.springframework.core.convert.support;

import java.beans.PropertyEditorSupport;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class ConvertingPropertyEditorAdapter extends PropertyEditorSupport {
    private final boolean canConvertToString;
    private final ConversionService conversionService;
    private final TypeDescriptor targetDescriptor;

    public ConvertingPropertyEditorAdapter(ConversionService conversionService, TypeDescriptor typeDescriptor) {
        Assert.notNull(conversionService, "ConversionService must not be null");
        Assert.notNull(typeDescriptor, "TypeDescriptor must not be null");
        this.conversionService = conversionService;
        this.targetDescriptor = typeDescriptor;
        this.canConvertToString = conversionService.canConvert(this.targetDescriptor, TypeDescriptor.valueOf(String.class));
    }

    public String getAsText() {
        if (this.canConvertToString) {
            return (String) this.conversionService.convert(getValue(), this.targetDescriptor, TypeDescriptor.valueOf(String.class));
        }
        return null;
    }

    public void setAsText(String str) throws IllegalArgumentException {
        setValue(this.conversionService.convert(str, TypeDescriptor.valueOf(String.class), this.targetDescriptor));
    }
}
