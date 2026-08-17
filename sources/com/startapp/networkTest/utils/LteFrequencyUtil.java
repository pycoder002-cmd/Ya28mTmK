package com.startapp.networkTest.utils;

import com.startapp.c0;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class LteFrequencyUtil {
    private static final List<LteFrequencyUtil> a = new ArrayList<LteFrequencyUtil>() { // from class: com.startapp.networkTest.utils.LteFrequencyUtil.1
        {
            add(new LteFrequencyUtil(1, 2110.0f, 0.0f, 599.0f, 1920.0f, 18000.0f, 18599.0f));
            add(new LteFrequencyUtil(2, 1930.0f, 600.0f, 1199.0f, 1850.0f, 18600.0f, 19199.0f));
            add(new LteFrequencyUtil(3, 1805.0f, 1200.0f, 1949.0f, 1710.0f, 19200.0f, 19949.0f));
            add(new LteFrequencyUtil(4, 2110.0f, 1950.0f, 2399.0f, 1710.0f, 19950.0f, 20399.0f));
            add(new LteFrequencyUtil(5, 869.0f, 2400.0f, 2649.0f, 824.0f, 20400.0f, 20649.0f));
            add(new LteFrequencyUtil(6, 875.0f, 2650.0f, 2749.0f, 830.0f, 20650.0f, 20749.0f));
            add(new LteFrequencyUtil(7, 2620.0f, 2750.0f, 3449.0f, 2500.0f, 20750.0f, 21449.0f));
            add(new LteFrequencyUtil(8, 925.0f, 3450.0f, 3799.0f, 880.0f, 21450.0f, 21799.0f));
            add(new LteFrequencyUtil(9, 1844.9f, 3800.0f, 4149.0f, 1749.9f, 21800.0f, 22149.0f));
            add(new LteFrequencyUtil(10, 2110.0f, 4150.0f, 4749.0f, 1710.0f, 22150.0f, 22749.0f));
            add(new LteFrequencyUtil(11, 1475.9f, 4750.0f, 4949.0f, 1427.0f, 22750.0f, 22949.0f));
            add(new LteFrequencyUtil(12, 729.0f, 5010.0f, 5179.0f, 699.0f, 23010.0f, 23179.0f));
            add(new LteFrequencyUtil(13, 746.0f, 5180.0f, 5279.0f, 777.0f, 23180.0f, 23279.0f));
            add(new LteFrequencyUtil(14, 758.0f, 5280.0f, 5379.0f, 788.0f, 23280.0f, 23379.0f));
            add(new LteFrequencyUtil(17, 734.0f, 5730.0f, 5849.0f, 704.0f, 23730.0f, 23849.0f));
            add(new LteFrequencyUtil(18, 860.0f, 5850.0f, 5999.0f, 815.0f, 23850.0f, 23999.0f));
            add(new LteFrequencyUtil(19, 875.0f, 6000.0f, 6149.0f, 830.0f, 24000.0f, 24149.0f));
            add(new LteFrequencyUtil(20, 791.0f, 6150.0f, 6449.0f, 832.0f, 24150.0f, 24449.0f));
            add(new LteFrequencyUtil(21, 1495.9f, 6450.0f, 6599.0f, 1447.9f, 24450.0f, 24599.0f));
            add(new LteFrequencyUtil(22, 3510.0f, 6600.0f, 7399.0f, 3410.0f, 24600.0f, 25399.0f));
            add(new LteFrequencyUtil(23, 2180.0f, 7500.0f, 7699.0f, 2000.0f, 25500.0f, 25699.0f));
            add(new LteFrequencyUtil(24, 1525.0f, 7700.0f, 8039.0f, 1626.5f, 25700.0f, 26039.0f));
            add(new LteFrequencyUtil(25, 1930.0f, 8040.0f, 8689.0f, 1850.0f, 26040.0f, 26689.0f));
            add(new LteFrequencyUtil(26, 859.0f, 8690.0f, 9039.0f, 814.0f, 26690.0f, 27039.0f));
            add(new LteFrequencyUtil(27, 852.0f, 9040.0f, 9209.0f, 807.0f, 27040.0f, 27209.0f));
            add(new LteFrequencyUtil(28, 758.0f, 9210.0f, 9659.0f, 703.0f, 27210.0f, 27659.0f));
            add(new LteFrequencyUtil(29, 717.0f, 9660.0f, 9769.0f, 0.0f, 0.0f, 0.0f));
            add(new LteFrequencyUtil(30, 2350.0f, 9770.0f, 9869.0f, 2305.0f, 27660.0f, 27759.0f));
            add(new LteFrequencyUtil(31, 462.5f, 9870.0f, 9919.0f, 452.5f, 27760.0f, 27809.0f));
            add(new LteFrequencyUtil(32, 1452.0f, 9920.0f, 10359.0f, 0.0f, 0.0f, 0.0f));
            add(new LteFrequencyUtil(33, 1900.0f, 36000.0f, 36199.0f, 1900.0f, 36000.0f, 36199.0f));
            add(new LteFrequencyUtil(34, 2010.0f, 36200.0f, 36349.0f, 2010.0f, 36200.0f, 36349.0f));
            add(new LteFrequencyUtil(35, 1850.0f, 36350.0f, 36949.0f, 1850.0f, 36350.0f, 36949.0f));
            add(new LteFrequencyUtil(36, 1930.0f, 36950.0f, 37549.0f, 1930.0f, 36950.0f, 37549.0f));
            add(new LteFrequencyUtil(37, 1910.0f, 37550.0f, 37749.0f, 1910.0f, 37550.0f, 37749.0f));
            add(new LteFrequencyUtil(38, 2570.0f, 37750.0f, 38249.0f, 2570.0f, 37750.0f, 38249.0f));
            add(new LteFrequencyUtil(39, 1880.0f, 38250.0f, 38649.0f, 1880.0f, 38250.0f, 38649.0f));
            add(new LteFrequencyUtil(40, 2300.0f, 38650.0f, 39649.0f, 2300.0f, 38650.0f, 39649.0f));
            add(new LteFrequencyUtil(41, 2496.0f, 39650.0f, 41589.0f, 2496.0f, 39650.0f, 41589.0f));
            add(new LteFrequencyUtil(42, 2400.0f, 41590.0f, 43589.0f, 3400.0f, 41590.0f, 43589.0f));
            add(new LteFrequencyUtil(43, 3600.0f, 43590.0f, 45589.0f, 3600.0f, 43590.0f, 45589.0f));
            add(new LteFrequencyUtil(44, 703.0f, 45590.0f, 46589.0f, 703.0f, 45590.0f, 46589.0f));
            add(new LteFrequencyUtil(45, 1447.0f, 46590.0f, 46789.0f, 1447.0f, 46590.0f, 46789.0f));
            add(new LteFrequencyUtil(46, 5150.0f, 46790.0f, 54539.0f, 5150.0f, 46790.0f, 54539.0f));
            add(new LteFrequencyUtil(47, 5855.0f, 54540.0f, 55239.0f, 5855.0f, 54540.0f, 55239.0f));
            add(new LteFrequencyUtil(48, 3550.0f, 55240.0f, 56739.0f, 3550.0f, 55240.0f, 56739.0f));
            add(new LteFrequencyUtil(50, 1432.0f, 58240.0f, 59089.0f, 1432.0f, 58240.0f, 59089.0f));
            add(new LteFrequencyUtil(51, 1427.0f, 59090.0f, 59139.0f, 1427.0f, 59090.0f, 59139.0f));
            add(new LteFrequencyUtil(65, 2110.0f, 65536.0f, 66435.0f, 1920.0f, 131072.0f, 131971.0f));
            add(new LteFrequencyUtil(66, 2110.0f, 66436.0f, 67335.0f, 1710.0f, 131972.0f, 132671.0f));
            float f = 0.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            add(new LteFrequencyUtil(67, 738.0f, 67336.0f, 67535.0f, f, f2, f3));
            add(new LteFrequencyUtil(68, 753.0f, 67536.0f, 67835.0f, 698.0f, 132672.0f, 132971.0f));
            add(new LteFrequencyUtil(69, 2570.0f, 67836.0f, 68335.0f, f, f2, f3));
            add(new LteFrequencyUtil(70, 1995.0f, 68336.0f, 68585.0f, 1695.0f, 132972.0f, 133121.0f));
            add(new LteFrequencyUtil(71, 617.0f, 68586.0f, 68935.0f, 663.0f, 133122.0f, 133471.0f));
            add(new LteFrequencyUtil(72, 461.0f, 68936.0f, 68985.0f, 451.0f, 133472.0f, 133521.0f));
            add(new LteFrequencyUtil(74, 1475.0f, 69036.0f, 69465.0f, 1427.0f, 133572.0f, 134001.0f));
            add(new LteFrequencyUtil(75, 1432.0f, 69466.0f, 70315.0f, 0.0f, 0.0f, 0.0f));
            add(new LteFrequencyUtil(76, 1427.0f, 70316.0f, 70365.0f, 0.0f, 0.0f, 0.0f));
        }
    };
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private c0 h;

    private LteFrequencyUtil(int i, float f, float f2, float f3, float f4, float f5, float f6) {
        this.h = null;
        c0 c0Var = new c0();
        this.h = c0Var;
        c0Var.band = i;
        this.b = f4;
        this.c = f6;
        this.e = f;
        this.f = f3;
        this.d = f5;
        this.g = f2;
    }

    private float a(float f, int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        return Math.round(f * r6) / ((float) Math.pow(10.0d, i));
    }

    public static c0 a(int i) {
        c0 c0Var = null;
        for (LteFrequencyUtil lteFrequencyUtil : a) {
            if (lteFrequencyUtil.b(i)) {
                c0Var = lteFrequencyUtil.c(i).h;
            } else if (i > 0 && lteFrequencyUtil.a(i)) {
                c0Var = lteFrequencyUtil.d(i).h;
            }
        }
        return c0Var;
    }

    private boolean a(long j) {
        float f = (float) j;
        return f >= this.d && f <= this.c;
    }

    private boolean b(int i) {
        float f = i;
        return f >= this.g && f <= this.f;
    }

    private LteFrequencyUtil c(int i) {
        c0 c0Var = this.h;
        float f = i;
        float f2 = this.e + ((f - this.g) * 0.1f);
        c0Var.download_frequency = f2;
        c0Var.download_frequency = a(f2, 1);
        c0 c0Var2 = this.h;
        c0Var2.download_earfcn = i;
        float f3 = this.b;
        if (f3 != 0.0f || this.d != 0.0f) {
            float f4 = f3 + (c0Var2.download_frequency - this.e);
            c0Var2.upload_frequency = f4;
            c0Var2.upload_frequency = a(f4, 1);
            this.h.upload_earfcn = (int) (this.d + (f - this.g));
        }
        return this;
    }

    private LteFrequencyUtil d(int i) {
        c0 c0Var = this.h;
        float f = i;
        float f2 = this.b + ((f - this.d) * 0.1f);
        c0Var.upload_frequency = f2;
        c0Var.upload_frequency = a(f2, 1);
        c0 c0Var2 = this.h;
        c0Var2.upload_earfcn = i;
        float f3 = this.e + (c0Var2.upload_frequency - this.b);
        c0Var2.download_frequency = f3;
        c0Var2.download_frequency = a(f3, 1);
        this.h.download_earfcn = (int) (this.g + (f - this.d));
        return this;
    }
}
