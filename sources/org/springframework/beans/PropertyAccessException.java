package org.springframework.beans;

import java.beans.PropertyChangeEvent;
import org.springframework.core.ErrorCoded;

/* loaded from: classes2.dex */
public abstract class PropertyAccessException extends BeansException implements ErrorCoded {
    private static final long serialVersionUID = 1;
    private transient PropertyChangeEvent propertyChangeEvent;

    public PropertyAccessException(PropertyChangeEvent propertyChangeEvent, String str, Throwable th) {
        super(str, th);
        this.propertyChangeEvent = propertyChangeEvent;
    }

    public PropertyAccessException(String str, Throwable th) {
        super(str, th);
    }

    public PropertyChangeEvent getPropertyChangeEvent() {
        return this.propertyChangeEvent;
    }

    public String getPropertyName() {
        if (this.propertyChangeEvent != null) {
            return this.propertyChangeEvent.getPropertyName();
        }
        return null;
    }

    public Object getValue() {
        if (this.propertyChangeEvent != null) {
            return this.propertyChangeEvent.getNewValue();
        }
        return null;
    }
}
