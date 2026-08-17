package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.iab.omid.library.startapp.ScriptInjector;
import com.startapp.aa;
import com.startapp.bd;
import com.startapp.g8;
import com.startapp.p7;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.adsbase.adinformation.AdInformationPositions;
import com.startapp.sdk.adsbase.apppresence.AppPresenceDetails;
import com.startapp.sdk.adsbase.consent.ConsentData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.v6;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class HtmlAd extends Ad {
    public static String c = null;
    private static final long serialVersionUID = 1;
    private List<AppPresenceDetails> apps;
    private String[] closingUrl;
    private Long delayImpressionInSeconds;
    private int height;
    private String htmlUuid;
    public boolean[] inAppBrowserEnabled;
    private boolean isMraidAd;
    private int orientation;
    private String[] packageNames;
    private int rewardDuration;
    private boolean rewardedHideTimer;
    private Boolean[] sendRedirectHops;
    public boolean[] smartRedirect;
    private String[] trackingClickUrls;
    public String[] trackingUrls;
    private int width;

    public HtmlAd(Context context, AdPreferences.Placement placement) {
        super(context, placement);
        this.packageNames = new String[]{""};
        this.htmlUuid = "";
        this.orientation = 0;
        this.trackingClickUrls = new String[]{""};
        this.closingUrl = new String[]{""};
        this.sendRedirectHops = null;
        this.rewardDuration = 0;
        this.rewardedHideTimer = false;
        this.smartRedirect = new boolean[]{false};
        this.trackingUrls = new String[]{""};
        this.inAppBrowserEnabled = new boolean[]{true};
        this.isMraidAd = false;
        if (c == null) {
            q();
        }
    }

    public void a(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public void a(SplashConfig.Orientation orientation) {
        this.orientation = 0;
        Map<Activity, Integer> map = aa.a;
        if (orientation != null) {
            if (orientation.equals(SplashConfig.Orientation.PORTRAIT)) {
                this.orientation = 1;
            } else if (orientation.equals(SplashConfig.Orientation.LANDSCAPE)) {
                this.orientation = 2;
            }
        }
    }

    public void a(List<AppPresenceDetails> list) {
        this.apps = list;
    }

    public boolean a(int i) {
        boolean[] zArr = this.inAppBrowserEnabled;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    public Boolean b(int i) {
        Boolean[] boolArr = this.sendRedirectHops;
        if (boolArr == null || i < 0 || i >= boolArr.length) {
            return null;
        }
        return boolArr[i];
    }

    public void b(String str) {
        Long l = null;
        for (String str2 : str.split(",")) {
            if (!str2.equals("")) {
                try {
                    long parseLong = Long.parseLong(str2);
                    if (parseLong > 0 && (l == null || parseLong < l.longValue())) {
                        l = Long.valueOf(parseLong);
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
    }

    public void c(String str) {
        if (Pattern.compile("<script\\s+[^>]*\\bsrc\\s*=\\s*([\\\"\\'])mraid\\.js\\1[^>]*>\\s*</script>\\n*", 2).matcher(new StringBuffer(str)).find()) {
            if (Build.VERSION.SDK_INT < 11) {
                StringBuffer stringBuffer = new StringBuffer(str);
                String property = System.getProperty("line.separator");
                Matcher matcher = Pattern.compile("<script\\s+[^>]*\\bsrc\\s*=\\s*([\\\"\\'])mraid\\.js\\1[^>]*>\\s*</script>\\n*", 2).matcher(stringBuffer);
                if (matcher.find()) {
                    stringBuffer.delete(matcher.start(), matcher.end());
                }
                Matcher matcher2 = Pattern.compile("<head[^>]*>", 2).matcher(stringBuffer);
                for (int i = 0; matcher2.find(i); i = matcher2.end()) {
                    stringBuffer.insert(matcher2.end(), property + "<script>" + property + g8.a() + property + "</script>");
                }
                str = stringBuffer.toString();
            }
            this.isMraidAd = true;
        }
        if (MetaData.h.O()) {
            try {
                str = ScriptInjector.injectScriptContentIntoHtml(bd.a(), str);
            } catch (Throwable th) {
                p7.a(this.b, th);
            }
        }
        Map<Activity, Integer> map = aa.a;
        v6 v6Var = v6.a;
        v6Var.getClass();
        String uuid = UUID.randomUUID().toString();
        v6Var.c.put(uuid, str);
        this.htmlUuid = uuid;
        String a = aa.a(str, "@smartRedirect@", "@smartRedirect@");
        if (a != null) {
            String[] split = a.split(",");
            this.smartRedirect = new boolean[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                if (split[i2].compareTo("true") == 0) {
                    this.smartRedirect[i2] = true;
                } else {
                    this.smartRedirect[i2] = false;
                }
            }
        }
        String a2 = aa.a(str, "@trackingClickUrl@", "@trackingClickUrl@");
        if (a2 != null) {
            this.trackingClickUrls = a2.split(",");
        }
        String a3 = aa.a(str, "@closeUrl@", "@closeUrl@");
        if (a3 != null) {
            this.closingUrl = a3.split(",");
        }
        String a4 = aa.a(str, "@tracking@", "@tracking@");
        if (a4 != null) {
            this.trackingUrls = a4.split(",");
        }
        String a5 = aa.a(str, "@packageName@", "@packageName@");
        if (a5 != null) {
            this.packageNames = a5.split(",");
        }
        String a6 = aa.a(str, "@startappBrowserEnabled@", "@startappBrowserEnabled@");
        if (a6 != null) {
            String[] split2 = a6.split(",");
            this.inAppBrowserEnabled = new boolean[split2.length];
            for (int i3 = 0; i3 < split2.length; i3++) {
                if (split2[i3].compareTo("false") == 0) {
                    this.inAppBrowserEnabled[i3] = false;
                } else {
                    this.inAppBrowserEnabled[i3] = true;
                }
            }
        }
        String a7 = aa.a(str, "@orientation@", "@orientation@");
        if (a7 != null) {
            Map<Activity, Integer> map2 = aa.a;
            a(SplashConfig.Orientation.getByName(a7));
        }
        String a8 = aa.a(str, "@adInfoEnable@", "@adInfoEnable@");
        if (a8 != null) {
            getAdInfoOverride().a(Boolean.parseBoolean(a8));
        }
        String a9 = aa.a(str, "@adInfoPosition@", "@adInfoPosition@");
        if (a9 != null) {
            getAdInfoOverride().a(AdInformationPositions.Position.getByName(a9));
        }
        String a10 = aa.a(str, "@ttl@", "@ttl@");
        if (a10 != null) {
            b(a10);
        }
        String a11 = aa.a(str, "@belowMinCPM@", "@belowMinCPM@");
        if (a11 != null) {
            if (Arrays.asList(a11.split(",")).contains("false")) {
                this.belowMinCPM = false;
            } else {
                this.belowMinCPM = true;
            }
        }
        String a12 = aa.a(str, "@delayImpressionInSeconds@", "@delayImpressionInSeconds@");
        if (a12 != null && !a12.equals("")) {
            try {
                this.delayImpressionInSeconds = Long.valueOf(Long.parseLong(a12));
            } catch (NumberFormatException unused) {
            }
        }
        String a13 = aa.a(str, "@rewardDuration@", "@rewardDuration@");
        if (a13 != null) {
            try {
                this.rewardDuration = Integer.parseInt(a13);
            } catch (Throwable th2) {
                p7.a(this.b, th2);
            }
        }
        String a14 = aa.a(str, "@rewardedHideTimer@", "@rewardedHideTimer@");
        if (a14 != null) {
            try {
                this.rewardedHideTimer = Boolean.parseBoolean(a14);
            } catch (Throwable th3) {
                p7.a(this.b, th3);
            }
        }
        String a15 = aa.a(str, "@sendRedirectHops@", "@sendRedirectHops@");
        if (a15 != null && !a15.equals("")) {
            String[] split3 = a15.split(",");
            this.sendRedirectHops = new Boolean[split3.length];
            for (int i4 = 0; i4 < split3.length; i4++) {
                if (split3[i4].compareTo("true") == 0) {
                    this.sendRedirectHops[i4] = Boolean.TRUE;
                } else if (split3[i4].compareTo("false") == 0) {
                    this.sendRedirectHops[i4] = Boolean.FALSE;
                } else {
                    this.sendRedirectHops[i4] = null;
                }
            }
        }
        ConsentData consentData = new ConsentData();
        this.consentData = consentData;
        consentData.b(aa.a(str, "@infoDparam@", "@infoDparam@"));
        this.consentData.c(aa.a(str, "@infoImpUrl@", "@infoImpUrl@"));
        this.consentData.a(aa.a(str, "@infoClickUrl@", "@infoClickUrl@"));
        try {
            String a16 = aa.a(str, "@ct@", "@ct@");
            if (!TextUtils.isEmpty(a16)) {
                this.consentData.a(Integer.valueOf(Integer.parseInt(a16)));
            }
        } catch (Throwable th4) {
            p7.a(this.b, th4);
        }
        try {
            String a17 = aa.a(str, "@tsc@", "@tsc@");
            if (!TextUtils.isEmpty(a17)) {
                this.consentData.a(Long.valueOf(Long.parseLong(a17)));
            }
        } catch (Throwable th5) {
            p7.a(this.b, th5);
        }
        try {
            String a18 = aa.a(str, "@apc@", "@apc@");
            if (!TextUtils.isEmpty(a18)) {
                this.consentData.a(Boolean.valueOf(Boolean.parseBoolean(a18)));
            }
        } catch (Throwable th6) {
            p7.a(this.b, th6);
        }
        int length = this.smartRedirect.length;
        String[] strArr = this.trackingUrls;
        if (length < strArr.length) {
            boolean[] zArr = new boolean[strArr.length];
            int i5 = 0;
            while (true) {
                boolean[] zArr2 = this.smartRedirect;
                if (i5 >= zArr2.length) {
                    break;
                }
                zArr[i5] = zArr2[i5];
                i5++;
            }
            while (i5 < this.trackingUrls.length) {
                zArr[i5] = false;
                i5++;
            }
            this.smartRedirect = zArr;
        }
    }

    public String[] g() {
        return this.closingUrl;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getAdId() {
        return aa.a(j(), "adId", "adId");
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getBidToken() {
        return aa.a(j(), "bidToken", "bidToken");
    }

    public Long h() {
        return this.delayImpressionInSeconds;
    }

    public int i() {
        return this.height;
    }

    public String j() {
        v6 v6Var = v6.a;
        return v6Var.c.get(this.htmlUuid);
    }

    public String k() {
        return this.htmlUuid;
    }

    public int l() {
        return this.orientation;
    }

    public String[] m() {
        return this.packageNames;
    }

    public int n() {
        return this.rewardDuration;
    }

    public String[] o() {
        return this.trackingClickUrls;
    }

    public int p() {
        return this.width;
    }

    public final void q() {
        c = aa.a(getContext());
    }

    public boolean r() {
        return this.isMraidAd;
    }

    public boolean s() {
        return this.rewardedHideTimer;
    }

    public Boolean[] t() {
        return this.sendRedirectHops;
    }
}
