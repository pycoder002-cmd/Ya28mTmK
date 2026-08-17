package cu.uci.android.apklis.domain.model;

import com.kingfisher.easy_sharedpreference_library.BuildConfig;

/* loaded from: classes.dex */
public class AndroidVersion {
    public static String version(String str) {
        switch (Integer.parseInt(str)) {
            case 1:
                return "1.0";
            case 2:
                return BuildConfig.VERSION_NAME;
            case 3:
                return "Cupcake 1.5";
            case 4:
                return "Donut 1.6";
            case 5:
                return "Eclair 2.0";
            case 6:
                return "Eclair 2.0.1";
            case 7:
                return "Eclair 2.1";
            case 8:
                return "Froyo 2.2.x";
            case 9:
                return "Gingerbread 2.3 - 2.3.2";
            case 10:
                return "Gingerbread 2.3.3 - 2.3.7";
            case 11:
                return "Honeycomb 3.0";
            case 12:
                return "Honeycomb 3.1";
            case 13:
                return "Honeycomb 3.2.x";
            case 14:
                return "Ice Cream Sandwich 4.0.1 - 4.0.2";
            case 15:
                return "Ice Cream Sandwich 4.0.3 - 4.0.4";
            case 16:
                return "Jelly Bean 4.1.x";
            case 17:
                return "Jelly Bean 4.2.x";
            case 18:
                return "Jelly Bean 4.3.x";
            case 19:
                return "KitKat 4.4 - 4.4.4";
            case 20:
                return "KitKat 4.4W – 4.4W.2";
            case 21:
                return "Lollipop 5.0";
            case 22:
                return "Lollipop 5.1";
            case 23:
                return "Marshmallow 6.0 - 6.0.1";
            case 24:
                return "Nougat 7.0";
            case 25:
                return "Nougat 7.1 - 7.1.2";
            case 26:
                return "Oreo 8.0 - 8.1";
            case 27:
                return "Oreo 8.0 - 8.1";
            default:
                return str;
        }
    }
}
