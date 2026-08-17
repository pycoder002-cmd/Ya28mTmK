package com.startapp.sdk.adsbase.adinformation;

import android.app.Activity;
import android.content.Context;
import com.startapp.a6;
import com.startapp.aa;
import com.startapp.b9;
import com.startapp.f;
import com.startapp.sdk.adsbase.adinformation.AdInformationPositions;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdInformationConfig implements Serializable {
    public static final String a = "https://funnel-assets.startappservice.com/consent/index.html";
    private static final long serialVersionUID = 8911501868319500986L;
    private Integer consentTypeInfo;
    private boolean enabled = true;
    private float fatFingersFactor = 200.0f;
    private String dialogUrlSecured = a;
    private String eulaUrlSecured = "https://www.startapp.com/policy/privacy-policy/";

    @f(key = AdPreferences.Placement.class, type = HashMap.class, value = AdInformationPositions.Position.class)
    public HashMap<AdPreferences.Placement, AdInformationPositions.Position> Positions = new HashMap<>();
    public transient EnumMap<ImageResourceType, ImageResourceConfig> b = new EnumMap<>(ImageResourceType.class);

    @f(type = ArrayList.class, value = ImageResourceConfig.class)
    private List<ImageResourceConfig> ImageResources = new ArrayList();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum ImageResourceType {
        INFO_S(17, 14),
        INFO_EX_S(88, 14),
        INFO_L(25, 21),
        INFO_EX_L(Opcodes.IXOR, 21);

        private final int height;
        private final int width;

        ImageResourceType(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public static ImageResourceType getByName(String str) {
            ImageResourceType imageResourceType = INFO_S;
            ImageResourceType[] values = values();
            for (int i = 0; i < 4; i++) {
                ImageResourceType imageResourceType2 = values[i];
                if (imageResourceType2.name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    imageResourceType = imageResourceType2;
                }
            }
            return imageResourceType;
        }

        public int getDefaultHeight() {
            return this.height;
        }

        public int getDefaultWidth() {
            return this.width;
        }
    }

    public static AdInformationConfig a() {
        AdInformationConfig adInformationConfig = new AdInformationConfig();
        a(adInformationConfig);
        return adInformationConfig;
    }

    public static void a(AdInformationConfig adInformationConfig) {
        boolean z;
        adInformationConfig.getClass();
        ImageResourceType[] values = ImageResourceType.values();
        for (int i = 0; i < 4; i++) {
            ImageResourceType imageResourceType = values[i];
            ImageResourceConfig imageResourceConfig = adInformationConfig.b.get(imageResourceType);
            if (imageResourceConfig == null) {
                imageResourceConfig = ImageResourceConfig.a(imageResourceType.name());
                Iterator<ImageResourceConfig> it = adInformationConfig.ImageResources.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (ImageResourceType.getByName(it.next().c()).equals(imageResourceType)) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                adInformationConfig.b.put((EnumMap<ImageResourceType, ImageResourceConfig>) imageResourceType, (ImageResourceType) imageResourceConfig);
                if (z) {
                    adInformationConfig.ImageResources.add(imageResourceConfig);
                }
            }
            imageResourceConfig.b(imageResourceType.getDefaultWidth());
            imageResourceConfig.a(imageResourceType.getDefaultHeight());
            imageResourceConfig.b(imageResourceType.name().toLowerCase() + ".png");
        }
        ImageResourceType[] values2 = ImageResourceType.values();
        for (int i2 = 0; i2 < 4; i2++) {
            ImageResourceType imageResourceType2 = values2[i2];
            if (adInformationConfig.b.get(imageResourceType2) == null) {
                throw new IllegalArgumentException("AdInformation error in ImageResource [" + imageResourceType2 + "] cannot be found in MetaData");
            }
        }
    }

    public void a(Context context) {
        for (ImageResourceConfig imageResourceConfig : this.ImageResources) {
            this.b.put((EnumMap<ImageResourceType, ImageResourceConfig>) ImageResourceType.getByName(imageResourceConfig.c()), (ImageResourceType) imageResourceConfig);
            imageResourceConfig.a = null;
            new b9(context, imageResourceConfig.b(), new a6(imageResourceConfig), 0).a();
        }
    }

    public Integer b() {
        return this.consentTypeInfo;
    }

    public boolean b(Context context) {
        return !ComponentLocator.a(context).d().getBoolean("userDisabledAdInformation", false) && this.enabled;
    }

    public String c() {
        String str = this.dialogUrlSecured;
        return str != null ? str : a;
    }

    public String d() {
        String str = this.eulaUrlSecured;
        return (str == null || str.equals("")) ? "https://www.startapp.com/policy/privacy-policy/" : this.eulaUrlSecured;
    }

    public float e() {
        return this.fatFingersFactor / 100.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdInformationConfig.class != obj.getClass()) {
            return false;
        }
        AdInformationConfig adInformationConfig = (AdInformationConfig) obj;
        return this.enabled == adInformationConfig.enabled && Float.compare(adInformationConfig.fatFingersFactor, this.fatFingersFactor) == 0 && aa.a(this.consentTypeInfo, adInformationConfig.consentTypeInfo) && aa.a(this.dialogUrlSecured, adInformationConfig.dialogUrlSecured) && aa.a(this.eulaUrlSecured, adInformationConfig.eulaUrlSecured) && aa.a(this.Positions, adInformationConfig.Positions) && aa.a(this.ImageResources, adInformationConfig.ImageResources);
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), this.consentTypeInfo, Float.valueOf(this.fatFingersFactor), this.dialogUrlSecured, this.eulaUrlSecured, this.Positions, this.ImageResources};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
