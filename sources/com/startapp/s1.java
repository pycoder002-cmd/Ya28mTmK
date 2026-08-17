package com.startapp;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.startapp.networkTest.data.TimeInfo;
import java.util.TimeZone;
import org.slf4j.Marker;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s1 {
    public static e2 a(long j, int i) {
        int i2;
        long j2 = i + j;
        long j3 = j2 / 1000;
        int i3 = (int) (j2 % 1000);
        long j4 = j3 / 60;
        int i4 = (int) (j3 % 60);
        long j5 = j4 / 60;
        int i5 = (int) (j4 % 60);
        int i6 = (int) (j5 / 24);
        int i7 = (int) (j5 % 24);
        int i8 = 365;
        int i9 = 1;
        int i10 = 1970;
        int i11 = 0;
        boolean z = false;
        while (true) {
            i2 = i6 + 1;
            if (i8 >= i2) {
                break;
            }
            i10++;
            int i12 = i8 + 365;
            if ((i10 % 4 != 0 || i10 % 100 == 0) && i10 % 400 != 0) {
                z = false;
            } else {
                i12++;
                z = true;
            }
            int i13 = i12;
            i11 = i8;
            i8 = i13;
        }
        int i14 = i2 - i11;
        int i15 = 0;
        int i16 = 31;
        while (i16 < i14) {
            i9++;
            int i17 = i16;
            i16 = (z && i9 == 2) ? i16 + 29 : i9 == 2 ? i16 + 28 : (i9 == 4 || i9 == 6 || i9 == 9 || i9 == 11) ? i16 + 30 : i16 + 31;
            i15 = i17;
        }
        return new e2(i10, i9, i14 - i15, i7, i5, i4, i3, i);
    }

    public static TimeInfo a(TimeInfo timeInfo, long j) {
        TimeInfo timeInfo2 = new TimeInfo();
        timeInfo2.DeviceDriftMillis = timeInfo.DeviceDriftMillis;
        timeInfo2.IsSynced = timeInfo.IsSynced;
        timeInfo2.MillisSinceLastSync = timeInfo.MillisSinceLastSync;
        timeInfo2.TimeSource = timeInfo.TimeSource;
        timeInfo2.TimestampDateTime = a(timeInfo.TimestampMillis + j);
        timeInfo2.TimestampMillis = timeInfo.TimestampMillis + j;
        timeInfo2.TimestampOffset = timeInfo.TimestampOffset;
        timeInfo2.TimestampTableau = b(timeInfo.TimestampMillis + j);
        return timeInfo2;
    }

    public static String a(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("-");
        if (i2 < 10) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append("-");
        if (i3 < 10) {
            sb.append("0");
        }
        sb.append(i3);
        return sb.toString();
    }

    public static String a(int i, int i2, int i3, int i4, int i5, int i6) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("-");
        if (i2 < 10) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append("-");
        if (i3 < 10) {
            sb.append("0");
        }
        sb.append(i3);
        sb.append("-");
        if (i4 < 10) {
            sb.append("0");
        }
        sb.append(i4);
        sb.append("-");
        if (i5 < 10) {
            sb.append("0");
        }
        sb.append(i5);
        sb.append("-");
        if (i6 < 10) {
            sb.append("0");
        }
        sb.append(i6);
        return sb.toString();
    }

    public static String a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        String a = a(i, i2, i3, i4, i5, i6);
        String str = "" + i7;
        if (i7 < 10) {
            str = "00" + i7;
        } else if (i7 < 100) {
            str = "0" + i7;
        }
        return a + "-" + str;
    }

    public static String a(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8) {
        String str = "" + i3;
        String str2 = "" + i2;
        String str3 = "" + i4;
        String str4 = "" + i5;
        String str5 = "" + i6;
        String str6 = "" + i7;
        if (i3 < 10) {
            str = "0" + i3;
        }
        if (i2 < 10) {
            str2 = "0" + i2;
        }
        if (i4 < 10) {
            str3 = "0" + i4;
        }
        if (i5 < 10) {
            str4 = "0" + i5;
        }
        if (i6 < 10) {
            str5 = "0" + i6;
        }
        if (i7 < 10) {
            str6 = "00" + i7;
        } else if (i7 < 100) {
            str6 = "0" + i7;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        String str7 = "-";
        sb.append("-");
        sb.append(str2);
        sb.append("-");
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(str3);
        sb.append(":");
        sb.append(str4);
        sb.append(":");
        sb.append(str5);
        sb.append(".");
        sb.append(str6);
        String sb2 = sb.toString();
        if (!z) {
            return sb2;
        }
        int i9 = (i8 / 1000) / 60;
        if (i8 < 0) {
            i9 *= -1;
        } else {
            str7 = Marker.ANY_NON_NULL_MARKER;
        }
        int i10 = i9 / 60;
        int i11 = i9 % 60;
        String str8 = "" + i10;
        String str9 = "" + i11;
        if (i10 < 10) {
            str8 = "0" + i10;
        }
        if (i11 < 10) {
            str9 = "0" + i11;
        }
        return sb2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str7 + str8 + str9;
    }

    public static String a(long j) {
        return a(j, false);
    }

    private static String a(long j, boolean z) {
        e2 c = c(j);
        return a(c.a, c.b, c.c, c.d, c.e, c.f, c.g, z, c.h);
    }

    public static String a(Context context) {
        return b(n1.d());
    }

    public static String b(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        if (i < 10) {
            sb.append("0");
        }
        sb.append(i);
        sb.append("-");
        if (i2 < 10) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append("-");
        if (i3 < 10) {
            sb.append("0");
        }
        sb.append(i3);
        return sb.toString();
    }

    public static String b(long j) {
        return a(j, true);
    }

    public static e2 c(long j) {
        return a(j, TimeZone.getDefault().getOffset(j));
    }
}
