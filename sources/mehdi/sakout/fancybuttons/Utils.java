package mehdi.sakout.fancybuttons;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class Utils {
    private static Map<String, Typeface> cachedFontMap = new HashMap();

    public static Typeface findFont(Context context, String str, String str2) {
        if (str == null) {
            return Typeface.DEFAULT;
        }
        String name = new File(str).getName();
        String name2 = TextUtils.isEmpty(str2) ? "" : new File(str2).getName();
        if (cachedFontMap.containsKey(name)) {
            return cachedFontMap.get(name);
        }
        try {
            AssetManager assets = context.getResources().getAssets();
            if (Arrays.asList(assets.list("")).contains(str)) {
                Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), name);
                cachedFontMap.put(name, createFromAsset);
                return createFromAsset;
            }
            if (Arrays.asList(assets.list("fonts")).contains(name)) {
                Typeface createFromAsset2 = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", name));
                cachedFontMap.put(name, createFromAsset2);
                return createFromAsset2;
            }
            if (Arrays.asList(assets.list("iconfonts")).contains(name)) {
                Typeface createFromAsset3 = Typeface.createFromAsset(context.getAssets(), String.format("iconfonts/%s", name));
                cachedFontMap.put(name, createFromAsset3);
                return createFromAsset3;
            }
            if (TextUtils.isEmpty(str2) || !Arrays.asList(assets.list("")).contains(str2)) {
                throw new Exception("Font not Found");
            }
            Typeface createFromAsset4 = Typeface.createFromAsset(context.getAssets(), str2);
            cachedFontMap.put(name2, createFromAsset4);
            return createFromAsset4;
        } catch (Exception unused) {
            Log.e(FancyButton.TAG, String.format("Unable to find %s font. Using Typeface.DEFAULT instead.", name));
            cachedFontMap.put(name, Typeface.DEFAULT);
            return Typeface.DEFAULT;
        }
    }

    public static int pxToSp(Context context, float f) {
        return Math.round(f / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(Context context, float f) {
        return Math.round(f * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
