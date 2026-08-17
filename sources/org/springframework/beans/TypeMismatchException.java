package org.springframework.beans;

import java.beans.PropertyChangeEvent;

/* loaded from: classes2.dex */
public class TypeMismatchException extends PropertyAccessException {
    public static final String ERROR_CODE = "typeMismatch";
    private static final long serialVersionUID = 1;
    private Class<?> requiredType;
    private transient Object value;

    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class<?> cls) {
        this(propertyChangeEvent, cls, (Throwable) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TypeMismatchException(java.beans.PropertyChangeEvent r4, java.lang.Class<?> r5, java.lang.Throwable r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to convert property value of type '"
            r0.append(r1)
            java.lang.Object r1 = r4.getNewValue()
            java.lang.String r1 = org.springframework.util.ClassUtils.getDescriptiveType(r1)
            r0.append(r1)
            java.lang.String r1 = "'"
            r0.append(r1)
            if (r5 == 0) goto L37
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " to required type '"
            r1.append(r2)
            java.lang.String r2 = org.springframework.util.ClassUtils.getQualifiedName(r5)
            r1.append(r2)
            java.lang.String r2 = "'"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L39
        L37:
            java.lang.String r1 = ""
        L39:
            r0.append(r1)
            java.lang.String r1 = r4.getPropertyName()
            if (r1 == 0) goto L5d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " for property '"
            r1.append(r2)
            java.lang.String r2 = r4.getPropertyName()
            r1.append(r2)
            java.lang.String r2 = "'"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L5f
        L5d:
            java.lang.String r1 = ""
        L5f:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r4, r0, r6)
            java.lang.Object r4 = r4.getNewValue()
            r3.value = r4
            r3.requiredType = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.springframework.beans.TypeMismatchException.<init>(java.beans.PropertyChangeEvent, java.lang.Class, java.lang.Throwable):void");
    }

    public TypeMismatchException(Object obj, Class<?> cls) {
        this(obj, cls, (Throwable) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TypeMismatchException(java.lang.Object r4, java.lang.Class<?> r5, java.lang.Throwable r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to convert value of type '"
            r0.append(r1)
            java.lang.String r1 = org.springframework.util.ClassUtils.getDescriptiveType(r4)
            r0.append(r1)
            java.lang.String r1 = "'"
            r0.append(r1)
            if (r5 == 0) goto L33
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " to required type '"
            r1.append(r2)
            java.lang.String r2 = org.springframework.util.ClassUtils.getQualifiedName(r5)
            r1.append(r2)
            java.lang.String r2 = "'"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L35
        L33:
            java.lang.String r1 = ""
        L35:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0, r6)
            r3.value = r4
            r3.requiredType = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.springframework.beans.TypeMismatchException.<init>(java.lang.Object, java.lang.Class, java.lang.Throwable):void");
    }

    @Override // org.springframework.core.ErrorCoded
    public String getErrorCode() {
        return ERROR_CODE;
    }

    public Class<?> getRequiredType() {
        return this.requiredType;
    }

    @Override // org.springframework.beans.PropertyAccessException
    public Object getValue() {
        return this.value;
    }
}
