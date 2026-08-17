package org.springframework.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes2.dex */
public abstract class NumberUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> cls) throws IllegalArgumentException {
        Assert.notNull(number, "Number must not be null");
        Assert.notNull(cls, "Target class must not be null");
        if (cls.isInstance(number)) {
            return number;
        }
        if (cls.equals(Byte.class)) {
            long longValue = number.longValue();
            if (longValue < -128 || longValue > 127) {
                raiseOverflowException(number, cls);
            }
            return new Byte(number.byteValue());
        }
        if (cls.equals(Short.class)) {
            long longValue2 = number.longValue();
            if (longValue2 < -32768 || longValue2 > 32767) {
                raiseOverflowException(number, cls);
            }
            return new Short(number.shortValue());
        }
        if (cls.equals(Integer.class)) {
            long longValue3 = number.longValue();
            if (longValue3 < -2147483648L || longValue3 > 2147483647L) {
                raiseOverflowException(number, cls);
            }
            return new Integer(number.intValue());
        }
        if (cls.equals(Long.class)) {
            return new Long(number.longValue());
        }
        if (cls.equals(BigInteger.class)) {
            return number instanceof BigDecimal ? ((BigDecimal) number).toBigInteger() : BigInteger.valueOf(number.longValue());
        }
        if (cls.equals(Float.class)) {
            return new Float(number.floatValue());
        }
        if (cls.equals(Double.class)) {
            return new Double(number.doubleValue());
        }
        if (cls.equals(BigDecimal.class)) {
            return new BigDecimal(number.toString());
        }
        throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to unknown target class [" + cls.getName() + "]");
    }

    private static BigInteger decodeBigInteger(String str) {
        boolean z;
        int i;
        int i2 = 0;
        if (str.startsWith("-")) {
            z = true;
            i2 = 1;
        } else {
            z = false;
        }
        int i3 = 16;
        if (str.startsWith("0x", i2) || str.startsWith("0X", i2)) {
            i2 += 2;
        } else if (str.startsWith("#", i2)) {
            i2++;
        } else if (!str.startsWith("0", i2) || str.length() <= (i = 1 + i2)) {
            i3 = 10;
        } else {
            i3 = 8;
            i2 = i;
        }
        BigInteger bigInteger = new BigInteger(str.substring(i2), i3);
        return z ? bigInteger.negate() : bigInteger;
    }

    private static boolean isHexNumber(String str) {
        boolean startsWith = str.startsWith("-");
        return str.startsWith("0x", startsWith ? 1 : 0) || str.startsWith("0X", startsWith ? 1 : 0) || str.startsWith("#", startsWith ? 1 : 0);
    }

    public static <T extends Number> T parseNumber(String str, Class<T> cls) {
        Assert.notNull(str, "Text must not be null");
        Assert.notNull(cls, "Target class must not be null");
        String trimAllWhitespace = StringUtils.trimAllWhitespace(str);
        if (cls.equals(Byte.class)) {
            return isHexNumber(trimAllWhitespace) ? Byte.decode(trimAllWhitespace) : Byte.valueOf(trimAllWhitespace);
        }
        if (cls.equals(Short.class)) {
            return isHexNumber(trimAllWhitespace) ? Short.decode(trimAllWhitespace) : Short.valueOf(trimAllWhitespace);
        }
        if (cls.equals(Integer.class)) {
            return isHexNumber(trimAllWhitespace) ? Integer.decode(trimAllWhitespace) : Integer.valueOf(trimAllWhitespace);
        }
        if (cls.equals(Long.class)) {
            return isHexNumber(trimAllWhitespace) ? Long.decode(trimAllWhitespace) : Long.valueOf(trimAllWhitespace);
        }
        if (cls.equals(BigInteger.class)) {
            return isHexNumber(trimAllWhitespace) ? decodeBigInteger(trimAllWhitespace) : new BigInteger(trimAllWhitespace);
        }
        if (cls.equals(Float.class)) {
            return Float.valueOf(trimAllWhitespace);
        }
        if (cls.equals(Double.class)) {
            return Double.valueOf(trimAllWhitespace);
        }
        if (cls.equals(BigDecimal.class) || cls.equals(Number.class)) {
            return new BigDecimal(trimAllWhitespace);
        }
        throw new IllegalArgumentException("Cannot convert String [" + str + "] to target class [" + cls.getName() + "]");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T extends java.lang.Number> T parseNumber(java.lang.String r4, java.lang.Class<T> r5, java.text.NumberFormat r6) {
        /*
            if (r6 == 0) goto L5f
            java.lang.String r0 = "Text must not be null"
            org.springframework.util.Assert.notNull(r4, r0)
            java.lang.String r0 = "Target class must not be null"
            org.springframework.util.Assert.notNull(r5, r0)
            r0 = 0
            boolean r1 = r6 instanceof java.text.DecimalFormat
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L28
            r0 = r6
            java.text.DecimalFormat r0 = (java.text.DecimalFormat) r0
            java.lang.Class<java.math.BigDecimal> r1 = java.math.BigDecimal.class
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L28
            boolean r1 = r0.isParseBigDecimal()
            if (r1 != 0) goto L28
            r0.setParseBigDecimal(r2)
            goto L29
        L28:
            r2 = r3
        L29:
            java.lang.String r4 = org.springframework.util.StringUtils.trimAllWhitespace(r4)     // Catch: java.lang.Throwable -> L3b java.text.ParseException -> L3d
            java.lang.Number r4 = r6.parse(r4)     // Catch: java.lang.Throwable -> L3b java.text.ParseException -> L3d
            java.lang.Number r4 = convertNumberToTargetClass(r4, r5)     // Catch: java.lang.Throwable -> L3b java.text.ParseException -> L3d
            if (r2 == 0) goto L3a
            r0.setParseBigDecimal(r3)
        L3a:
            return r4
        L3b:
            r4 = move-exception
            goto L59
        L3d:
            r4 = move-exception
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L3b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3b
            r6.<init>()     // Catch: java.lang.Throwable -> L3b
            java.lang.String r1 = "Could not parse number: "
            r6.append(r1)     // Catch: java.lang.Throwable -> L3b
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L3b
            r6.append(r4)     // Catch: java.lang.Throwable -> L3b
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> L3b
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L3b
            throw r5     // Catch: java.lang.Throwable -> L3b
        L59:
            if (r2 == 0) goto L5e
            r0.setParseBigDecimal(r3)
        L5e:
            throw r4
        L5f:
            java.lang.Number r4 = parseNumber(r4, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.springframework.util.NumberUtils.parseNumber(java.lang.String, java.lang.Class, java.text.NumberFormat):java.lang.Number");
    }

    private static void raiseOverflowException(Number number, Class cls) {
        throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to target class [" + cls.getName() + "]: overflow");
    }
}
