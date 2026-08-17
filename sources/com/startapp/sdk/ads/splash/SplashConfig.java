package com.startapp.sdk.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.g5;
import com.startapp.p7;
import com.startapp.x3;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SplashConfig implements Serializable {
    public static final Theme a = Theme.OCEAN;
    public static final MinSplashTime b = MinSplashTime.REGULAR;
    public static final long c = 7500;
    public static final MaxAdDisplayTime d = MaxAdDisplayTime.FOR_EVER;
    public static final Orientation e = Orientation.AUTO;
    private static final long serialVersionUID = 1;
    private boolean forceNative = false;
    private int customScreen = -1;
    private String appName = "";
    public transient Drawable f = null;
    private byte[] logoByteArray = null;
    private int logoRes = -1;

    @f(type = Theme.class)
    private Theme defaultTheme = a;

    @f(type = MinSplashTime.class)
    private MinSplashTime defaultMinSplashTime = b;
    private Long defaultMaxLoadTime = Long.valueOf(c);

    @f(type = MaxAdDisplayTime.class)
    private MaxAdDisplayTime defaultMaxAdDisplayTime = d;

    @f(type = Orientation.class)
    private Orientation defaultOrientation = e;
    private boolean htmlSplash = true;
    private String splashBgColor = "#066CAA";
    private String splashFontColor = "ffffff";
    private String splashLoadingType = "LoadingDots";
    public transient String g = "";

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.sdk.ads.splash.SplashConfig$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme;

        static {
            Theme.values();
            int[] iArr = new int[7];
            $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme = iArr;
            try {
                iArr[Theme.DEEP_BLUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.SKY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.ASHEN_SKY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.BLAZE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.GLOOMY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.OCEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$startapp$sdk$ads$splash$SplashConfig$Theme[Theme.USER_DEFINED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum MaxAdDisplayTime {
        SHORT(5000),
        LONG(10000),
        FOR_EVER(86400000);

        private long index;

        MaxAdDisplayTime(long j) {
            this.index = j;
        }

        public static MaxAdDisplayTime getByIndex(long j) {
            MaxAdDisplayTime maxAdDisplayTime = SHORT;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].getIndex() == j) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }

        public static MaxAdDisplayTime getByName(String str) {
            MaxAdDisplayTime maxAdDisplayTime = FOR_EVER;
            MaxAdDisplayTime[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    maxAdDisplayTime = values[i];
                }
            }
            return maxAdDisplayTime;
        }

        public long getIndex() {
            return this.index;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum MinSplashTime {
        REGULAR(3000),
        SHORT(2000),
        LONG(5000);

        private long index;

        MinSplashTime(int i) {
            this.index = i;
        }

        public static MinSplashTime getByIndex(long j) {
            MinSplashTime minSplashTime = SHORT;
            MinSplashTime[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].getIndex() == j) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }

        public static MinSplashTime getByName(String str) {
            MinSplashTime minSplashTime = LONG;
            MinSplashTime[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    minSplashTime = values[i];
                }
            }
            return minSplashTime;
        }

        public long getIndex() {
            return this.index;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Orientation {
        PORTRAIT(1),
        LANDSCAPE(2),
        AUTO(3);

        private int index;

        Orientation(int i) {
            this.index = i;
        }

        public static Orientation getByIndex(int i) {
            Orientation orientation = PORTRAIT;
            Orientation[] values = values();
            for (int i2 = 0; i2 < 3; i2++) {
                if (values[i2].getIndex() == i) {
                    orientation = values[i2];
                }
            }
            return orientation;
        }

        public static Orientation getByName(String str) {
            Orientation orientation = AUTO;
            Orientation[] values = values();
            for (int i = 0; i < 3; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    orientation = values[i];
                }
            }
            return orientation;
        }

        public int getIndex() {
            return this.index;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Theme {
        DEEP_BLUE(1),
        SKY(2),
        ASHEN_SKY(3),
        BLAZE(4),
        GLOOMY(5),
        OCEAN(6),
        USER_DEFINED(0);

        private int index;

        Theme(int i) {
            this.index = i;
        }

        public static Theme getByIndex(int i) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i2 = 0; i2 < 7; i2++) {
                if (values[i2].getIndex() == i) {
                    theme = values[i2];
                }
            }
            return theme;
        }

        public static Theme getByName(String str) {
            Theme theme = DEEP_BLUE;
            Theme[] values = values();
            for (int i = 0; i < 7; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    theme = values[i];
                }
            }
            return theme;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static SplashConfig getDefaultSplashConfig() {
        SplashConfig splashConfig = new SplashConfig();
        SplashConfig minSplashTime = splashConfig.setTheme(a).setMinSplashTime(b);
        minSplashTime.defaultMaxLoadTime = Long.valueOf(c);
        minSplashTime.setMaxAdDisplayTime(d).setOrientation(e).setLoadingType("LoadingDots").setAppName("");
        return splashConfig;
    }

    public View a(Context context) {
        if (this.defaultTheme.ordinal() == 6) {
            try {
                return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getCustomScreen(), (ViewGroup) null);
            } catch (Resources.NotFoundException unused) {
                throw new Resources.NotFoundException("StartApp: Can't find Custom layout resource");
            } catch (InflateException unused2) {
                throw new InflateException("StartApp: Can't inflate layout in Custom mode, Are you sure layout resource is valid?");
            } catch (Throwable th) {
                p7.a(context, th);
                return null;
            }
        }
        int ordinal = this.defaultTheme.ordinal();
        if (ordinal == 0) {
            View a2 = x3.a(context, this);
            a2.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-16356182, -15029533, -16356182}));
            ((TextView) a2.findViewById(100)).setTextColor(Color.rgb(255, 255, 255));
            ((TextView) a2.findViewById(105)).setTextColor(Color.rgb(208, 210, 210));
            return a2;
        }
        if (ordinal == 1) {
            View a3 = x3.a(context, this);
            int i = context.getResources().getDisplayMetrics().widthPixels;
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-921103, -6040347});
            gradientDrawable.setGradientType(1);
            gradientDrawable.setGradientRadius(i / 2);
            a3.setBackgroundDrawable(gradientDrawable);
            ((TextView) a3.findViewById(100)).setTextColor(Color.rgb(51, 51, 51));
            ((TextView) a3.findViewById(105)).setTextColor(Color.rgb(Opcodes.IF_ICMPGE, Opcodes.IRETURN, Opcodes.DRETURN));
            return a3;
        }
        if (ordinal == 2) {
            View a4 = x3.a(context, this);
            a4.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-3881788, -1}));
            ((TextView) a4.findViewById(100)).setTextColor(Color.rgb(51, 51, 51));
            ((TextView) a4.findViewById(105)).setTextColor(Color.rgb(Opcodes.IFEQ, Opcodes.IFEQ, Opcodes.IFEQ));
            return a4;
        }
        if (ordinal == 3) {
            View a5 = x3.a(context, this);
            int i2 = context.getResources().getDisplayMetrics().widthPixels;
            GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{-92376, -40960});
            gradientDrawable2.setGradientType(1);
            gradientDrawable2.setGradientRadius(i2 / 2);
            a5.setBackgroundDrawable(gradientDrawable2);
            ((TextView) a5.findViewById(100)).setTextColor(Color.rgb(255, 255, 255));
            ((TextView) a5.findViewById(105)).setTextColor(Color.rgb(255, Opcodes.IFNULL, Opcodes.DCMPL));
            return a5;
        }
        if (ordinal == 4) {
            View a6 = x3.a(context, this);
            a6.setBackgroundColor(Color.rgb(47, 53, 63));
            ((TextView) a6.findViewById(100)).setTextColor(Color.rgb(51, Opcodes.PUTFIELD, 229));
            ((TextView) a6.findViewById(105)).setTextColor(Color.rgb(Opcodes.ISHR, Opcodes.IXOR, Opcodes.F2I));
            return a6;
        }
        if (ordinal != 5) {
            return null;
        }
        View a7 = x3.a(context, this);
        a7.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-14451558, -7876130}));
        ((TextView) a7.findViewById(100)).setTextColor(Color.rgb(6, 61, 82));
        ((TextView) a7.findViewById(105)).setTextColor(Color.rgb(6, 61, 82));
        return a7;
    }

    public SplashHtml a(Activity activity) {
        int ordinal = this.defaultTheme.ordinal();
        String str = "#333333";
        String str2 = "#066CAA";
        if (ordinal != 0) {
            if (ordinal == 1) {
                str2 = "#a3d4e5";
            } else if (ordinal == 2) {
                str2 = "#E3E3E3";
            } else if (ordinal == 3) {
                str2 = "#FF6600";
            } else if (ordinal == 4) {
                str = "#33B5E5";
                str2 = "#2F353F";
            } else if (ordinal != 5) {
                str = "ffffff";
            } else {
                str = "#063D51";
                str2 = "#237C9A";
            }
            this.splashBgColor = str2;
            this.splashFontColor = str;
            SplashHtml splashHtml = new SplashHtml(activity);
            splashHtml.a(this);
            splashHtml.d();
            return splashHtml;
        }
        str = "#FFFFFF";
        this.splashBgColor = str2;
        this.splashFontColor = str;
        SplashHtml splashHtml2 = new SplashHtml(activity);
        splashHtml2.a(this);
        splashHtml2.d();
        return splashHtml2;
    }

    public Long a() {
        return this.defaultMaxLoadTime;
    }

    public boolean b() {
        return this.defaultTheme == Theme.USER_DEFINED || getCustomScreen() != -1;
    }

    public boolean b(Context context) {
        if (this.defaultTheme.ordinal() != 6) {
            if (getAppName().equals("")) {
                setAppName(g5.a(context, "Welcome!"));
            }
            if (getLogo() == null && getLogoByteArray() == null) {
                try {
                    if (getLogoRes() == -1) {
                        setLogo(context.getApplicationInfo().icon);
                        this.f = context.getResources().getDrawable(context.getApplicationInfo().icon);
                    } else {
                        this.f = context.getResources().getDrawable(getLogoRes());
                    }
                } catch (Throwable th) {
                    p7.a(context, th);
                }
            }
        } else if (getCustomScreen() == -1) {
            this.g = "StartApp: Exception getting custom screen resource id, make sure it is set";
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SplashConfig splashConfig = (SplashConfig) obj;
        return this.forceNative == splashConfig.forceNative && this.customScreen == splashConfig.customScreen && this.logoRes == splashConfig.logoRes && this.htmlSplash == splashConfig.htmlSplash && aa.a(this.appName, splashConfig.appName) && Arrays.equals(this.logoByteArray, splashConfig.logoByteArray) && this.defaultTheme == splashConfig.defaultTheme && this.defaultMinSplashTime == splashConfig.defaultMinSplashTime && aa.a(this.defaultMaxLoadTime, splashConfig.defaultMaxLoadTime) && this.defaultMaxAdDisplayTime == splashConfig.defaultMaxAdDisplayTime && this.defaultOrientation == splashConfig.defaultOrientation && aa.a(this.splashBgColor, splashConfig.splashBgColor) && aa.a(this.splashFontColor, splashConfig.splashFontColor) && aa.a(this.splashLoadingType, splashConfig.splashLoadingType);
    }

    public String getAppName() {
        return this.appName;
    }

    public String getBgColor() {
        return this.splashBgColor;
    }

    public int getCustomScreen() {
        return this.customScreen;
    }

    public String getErrorMessage() {
        return this.g;
    }

    public String getFontColor() {
        return this.splashFontColor;
    }

    public String getLoadingType() {
        return this.splashLoadingType;
    }

    public Drawable getLogo() {
        return this.f;
    }

    public byte[] getLogoByteArray() {
        return this.logoByteArray;
    }

    public int getLogoRes() {
        return this.logoRes;
    }

    public MaxAdDisplayTime getMaxAdDisplayTime() {
        return this.defaultMaxAdDisplayTime;
    }

    public MinSplashTime getMinSplashTime() {
        return this.defaultMinSplashTime;
    }

    public Orientation getOrientation() {
        return this.defaultOrientation;
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.forceNative), Integer.valueOf(this.customScreen), this.appName, Integer.valueOf(this.logoRes), this.defaultTheme, this.defaultMinSplashTime, this.defaultMaxLoadTime, this.defaultMaxAdDisplayTime, this.defaultOrientation, Boolean.valueOf(this.htmlSplash), this.splashBgColor, this.splashFontColor, this.splashLoadingType};
        Map<Activity, Integer> map = aa.a;
        return (Arrays.deepHashCode(objArr) * 31) + Arrays.hashCode(this.logoByteArray);
    }

    public boolean isHtmlSplash() {
        if (this.forceNative) {
            return false;
        }
        return this.htmlSplash;
    }

    public SplashConfig setAppName(String str) {
        this.appName = str;
        return this;
    }

    public SplashConfig setCustomScreen(int i) {
        this.customScreen = i;
        return this;
    }

    public void setDefaults(Context context) {
        ApplicationInfo applicationInfo;
        SplashConfig a2 = SplashMetaData.a.a();
        if (a2 == null) {
            a2 = getDefaultSplashConfig();
        } else {
            this.htmlSplash = a2.isHtmlSplash();
        }
        SplashConfig defaultSplashConfig = getDefaultSplashConfig();
        if (a2.defaultTheme == null) {
            a2.setTheme(defaultSplashConfig.defaultTheme);
        }
        if (a2.getMinSplashTime() == null) {
            a2.setMinSplashTime(defaultSplashConfig.getMinSplashTime());
        }
        if (a2.defaultMaxLoadTime == null) {
            a2.defaultMaxLoadTime = Long.valueOf(defaultSplashConfig.defaultMaxLoadTime.longValue());
        }
        if (a2.getMaxAdDisplayTime() == null) {
            a2.setMaxAdDisplayTime(defaultSplashConfig.getMaxAdDisplayTime());
        }
        if (a2.getOrientation() == null) {
            a2.setOrientation(defaultSplashConfig.getOrientation());
        }
        if (a2.getLoadingType() == null) {
            a2.setLoadingType(defaultSplashConfig.getLoadingType());
        }
        if (a2.getAppName().equals("")) {
            a2.setAppName(g5.a(context, "Welcome!"));
        }
        if (getMaxAdDisplayTime() == null) {
            setMaxAdDisplayTime(a2.getMaxAdDisplayTime());
        }
        if (this.defaultMaxLoadTime == null) {
            this.defaultMaxLoadTime = Long.valueOf(a2.defaultMaxLoadTime.longValue());
        }
        if (getMinSplashTime() == null) {
            setMinSplashTime(a2.getMinSplashTime());
        }
        if (getOrientation() == null) {
            setOrientation(a2.getOrientation());
        }
        if (this.defaultTheme == null) {
            setTheme(a2.defaultTheme);
        }
        if (getLogoRes() == -1 && (applicationInfo = context.getApplicationInfo()) != null) {
            setLogo(applicationInfo.icon);
        }
        if (getAppName().equals("")) {
            setAppName(a2.getAppName());
        }
    }

    public SplashConfig setLoadingType(String str) {
        this.splashLoadingType = str;
        return this;
    }

    public SplashConfig setLogo(int i) {
        this.logoRes = i;
        return this;
    }

    public SplashConfig setLogo(byte[] bArr) {
        this.logoByteArray = bArr;
        return this;
    }

    public SplashConfig setMaxAdDisplayTime(MaxAdDisplayTime maxAdDisplayTime) {
        this.defaultMaxAdDisplayTime = maxAdDisplayTime;
        return this;
    }

    public SplashConfig setMinSplashTime(MinSplashTime minSplashTime) {
        this.defaultMinSplashTime = minSplashTime;
        return this;
    }

    public SplashConfig setOrientation(Orientation orientation) {
        this.defaultOrientation = orientation;
        return this;
    }

    public SplashConfig setTheme(Theme theme) {
        this.defaultTheme = theme;
        return this;
    }
}
