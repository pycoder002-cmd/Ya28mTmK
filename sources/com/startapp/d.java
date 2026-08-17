package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.iab.omid.library.startapp.Omid;
import com.iab.omid.library.startapp.adsession.AdEvents;
import com.iab.omid.library.startapp.adsession.AdSession;
import com.iab.omid.library.startapp.adsession.AdSessionConfiguration;
import com.iab.omid.library.startapp.adsession.AdSessionContext;
import com.iab.omid.library.startapp.adsession.CreativeType;
import com.iab.omid.library.startapp.adsession.ImpressionType;
import com.iab.omid.library.startapp.adsession.Owner;
import com.iab.omid.library.startapp.adsession.Partner;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.startapp.p5;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.BannerOptions;
import com.startapp.sdk.ads.banner.banner3d.Banner3D;
import com.startapp.sdk.ads.banner.banner3d.Banner3DSize$Size;
import com.startapp.sdk.ads.interstitials.InterstitialAd;
import com.startapp.sdk.ads.offerWall.offerWallJson.OfferWall3DAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.apppresence.AppPresenceDetails;
import com.startapp.sdk.adsbase.cache.DiskAdCacheManager$DiskCachedAd;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.mraid.bridge.MraidState;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.simple.bloomfilter.algo.OpenBitSet;
import com.startapp.simple.bloomfilter.version.BloomVersion;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.CookieManager;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d {
    public static ta a;
    public static CookieManager b;

    public static double a(double d, double d2, double d3) {
        return 1.0d / (Math.exp((d2 - d) * d3) + 1.0d);
    }

    public static double a(double d, double d2, double d3, double d4) {
        return (a(d, d2, d3) - d4) / (1.0d - d4);
    }

    public static int a(Context context, int i) {
        return Math.round(TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics()));
    }

    public static long a(ByteBuffer byteBuffer, int i, int i2, long j) {
        long j2;
        long j3 = (j & 4294967295L) ^ (i2 * (-4132994306676758123L));
        for (int i3 = 0; i3 < (i2 >> 3); i3++) {
            int i4 = i + (i3 << 3);
            long j4 = ((byteBuffer.get(i4 + 0) & 255) + ((byteBuffer.get(i4 + 1) & 255) << 8) + ((byteBuffer.get(i4 + 2) & 255) << 16) + ((byteBuffer.get(i4 + 3) & 255) << 24) + ((byteBuffer.get(i4 + 4) & 255) << 32) + ((byteBuffer.get(i4 + 5) & 255) << 40) + ((byteBuffer.get(i4 + 6) & 255) << 48) + ((byteBuffer.get(i4 + 7) & 255) << 56)) * (-4132994306676758123L);
            j3 = (j3 ^ ((j4 ^ (j4 >>> 47)) * (-4132994306676758123L))) * (-4132994306676758123L);
        }
        switch (i2 & 7) {
            case 7:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 6) << 48;
            case 6:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 5) << 40;
            case 5:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 4) << 32;
            case 4:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 3) << 24;
            case 3:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 2) << 16;
            case 2:
                j3 ^= byteBuffer.get(((i + i2) - r4) + 1) << 8;
            case 1:
                long j5 = byteBuffer.get((i + i2) - r4) ^ j3;
                j2 = -4132994306676758123L;
                j3 = j5 * (-4132994306676758123L);
                break;
            default:
                j2 = -4132994306676758123L;
                break;
        }
        long j6 = (j3 ^ (j3 >>> 47)) * j2;
        return j6 ^ (j6 >>> 47);
    }

    public static ImageView a(Context context, ImageView imageView, Bitmap bitmap, int i) {
        ImageView imageView2 = new ImageView(context);
        imageView2.setImageBitmap(bitmap);
        imageView2.setId(i);
        return imageView2;
    }

    public static RelativeLayout.LayoutParams a(Context context, int[] iArr, int[] iArr2) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        for (int i : iArr2) {
            layoutParams.addRule(i);
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = iArr[i2] == 0 ? 0 : Math.round(TypedValue.applyDimension(1, iArr[i2], context.getResources().getDisplayMetrics()));
        }
        layoutParams.setMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
        return layoutParams;
    }

    public static TextView a(Context context, TextView textView, Typeface typeface, int i, float f, int i2, int i3) {
        TextView textView2 = new TextView(context);
        textView2.setTypeface(typeface, i);
        textView2.setTextSize(1, f);
        textView2.setSingleLine(true);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setTextColor(i2);
        textView2.setId(i3);
        return textView2;
    }

    public static AdEvents a(Context context, AdSession adSession) {
        if (adSession == null) {
            return null;
        }
        try {
            return AdEvents.createAdEvents(adSession);
        } catch (Throwable th) {
            p7.a(context, th);
            return null;
        }
    }

    public static AdSession a(WebView webView) {
        boolean z;
        Context context = webView.getContext();
        try {
            if (!Omid.isActive()) {
                Omid.activate(context);
            }
            z = true;
        } catch (Throwable th) {
            p7.a(context, th);
            z = false;
        }
        if (z) {
            return a(AdSessionContext.createHtmlAdSessionContext(Partner.createPartner("StartApp", "4.9.1"), webView, null, ""), false);
        }
        return null;
    }

    public static AdSession a(AdSessionContext adSessionContext, boolean z) {
        CreativeType creativeType = z ? CreativeType.VIDEO : CreativeType.HTML_DISPLAY;
        ImpressionType impressionType = ImpressionType.VIEWABLE;
        Owner owner = Owner.NATIVE;
        return AdSession.createAdSession(AdSessionConfiguration.createAdSessionConfiguration(creativeType, impressionType, owner, z ? owner : Owner.NONE, false), adSessionContext);
    }

    public static NotDisplayedReason a(View view, int i, AtomicReference<JSONObject> atomicReference) {
        if (view == null) {
            return NotDisplayedReason.INTERNAL_ERROR;
        }
        if (view.getParent() != null && view.getRootView() != null && view.getRootView().getParent() != null) {
            if (!view.hasWindowFocus()) {
                return NotDisplayedReason.WINDOW_NOT_FOCUSED;
            }
            if (!view.isShown()) {
                return NotDisplayedReason.VIEW_NOT_VISIBLE;
            }
            if (view.getWidth() < 1 || view.getHeight() < 1) {
                return NotDisplayedReason.VIEW_INVALID_SIZE;
            }
            int width = ((view.getWidth() * view.getHeight()) * Math.min(Math.max(1, i), 100)) / 100;
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect) && !rect.isEmpty()) {
                Region region = new Region(rect);
                Rect rect2 = new Rect();
                atomicReference.set(a(view, rect, true));
                NotDisplayedReason notDisplayedReason = NotDisplayedReason.AD_CLIPPED;
                while (true) {
                    int i2 = 0;
                    if (!(view.getParent() instanceof ViewGroup)) {
                        RegionIterator regionIterator = new RegionIterator(region);
                        while (regionIterator.next(rect2)) {
                            i2 += rect2.width() * rect2.height();
                            if (i2 >= width) {
                                return null;
                            }
                        }
                        return notDisplayedReason;
                    }
                    if (Build.VERSION.SDK_INT >= 11 && view.getAlpha() < 1.0f) {
                        return NotDisplayedReason.VIEW_TRANSPARENT;
                    }
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    JSONObject a2 = a((View) viewGroup, (Rect) null, false);
                    a(a2, atomicReference.get());
                    atomicReference.set(a2);
                    int childCount = viewGroup.getChildCount();
                    for (int indexOfChild = viewGroup.indexOfChild(view) + 1; indexOfChild < childCount; indexOfChild++) {
                        View childAt = viewGroup.getChildAt(indexOfChild);
                        if (childAt != null && childAt.getVisibility() == 0 && childAt.getGlobalVisibleRect(rect2) && Rect.intersects(rect, rect2)) {
                            region.op(rect2, Region.Op.DIFFERENCE);
                            a(a2, a(childAt, rect2, false));
                            notDisplayedReason = NotDisplayedReason.AD_WAS_COVERED;
                        }
                    }
                    view = viewGroup;
                }
            }
            return NotDisplayedReason.AD_CLIPPED;
        }
        return NotDisplayedReason.VIEW_NOT_ATTACHED;
    }

    public static Boolean a(Context context, List<AppPresenceDetails> list, int i, Set<String> set, List<AppPresenceDetails> list2) {
        boolean z = false;
        for (AppPresenceDetails appPresenceDetails : list) {
            boolean startsWith = appPresenceDetails.b().startsWith("!");
            boolean a2 = ya.a(context, startsWith ? appPresenceDetails.b().substring(1) : appPresenceDetails.b(), appPresenceDetails.a());
            if ((!startsWith && a2) || (startsWith && !a2)) {
                appPresenceDetails.a(a2);
                z = i == 0;
                if (z && !startsWith) {
                    set.add(appPresenceDetails.b());
                } else if (!z && appPresenceDetails.c() != null) {
                    appPresenceDetails.a(appPresenceDetails.c() + "&isShown=" + appPresenceDetails.e() + "&appPresence=" + appPresenceDetails.d());
                }
            }
            list2.add(appPresenceDetails);
        }
        if (z) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                list2.get(i2).b(false);
            }
        }
        return Boolean.valueOf(z);
    }

    public static Class<?> a(String str) throws ClassNotFoundException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1808118735:
                if (str.equals("String")) {
                    c = 0;
                    break;
                }
                break;
            case -1325958191:
                if (str.equals("double")) {
                    c = 1;
                    break;
                }
                break;
            case -891985903:
                if (str.equals("string")) {
                    c = 2;
                    break;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    c = 3;
                    break;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    c = 4;
                    break;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    c = 5;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    c = 6;
                    break;
                }
                break;
            case 64711720:
                if (str.equals("boolean")) {
                    c = 7;
                    break;
                }
                break;
            case 97526364:
                if (str.equals("float")) {
                    c = '\b';
                    break;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
                return String.class;
            case 1:
                return Double.TYPE;
            case 3:
                return Integer.TYPE;
            case 4:
                return Byte.TYPE;
            case 5:
                return Character.TYPE;
            case 6:
                return Long.TYPE;
            case 7:
                return Boolean.TYPE;
            case '\b':
                return Float.TYPE;
            case '\t':
                return Short.TYPE;
            default:
                return Class.forName(str);
        }
    }

    public static Object a(Class cls, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cls == Byte.TYPE) {
            if (obj instanceof Number) {
                return Byte.valueOf(((Number) obj).byteValue());
            }
        } else if (cls == Short.TYPE) {
            if (obj instanceof Number) {
                return Short.valueOf(((Number) obj).shortValue());
            }
        } else if (cls == Integer.TYPE) {
            if (obj instanceof Number) {
                return Integer.valueOf(((Number) obj).intValue());
            }
        } else if (cls == Long.TYPE) {
            if (obj instanceof Number) {
                return Long.valueOf(((Number) obj).longValue());
            }
        } else if (cls == Float.TYPE) {
            if (obj instanceof Number) {
                return Float.valueOf(((Number) obj).floatValue());
            }
        } else if (cls == Double.TYPE) {
            if (obj instanceof Number) {
                return Double.valueOf(((Number) obj).doubleValue());
            }
        } else if (cls == String.class) {
            if (obj != null) {
                return obj.toString();
            }
        } else if (cls == Boolean.TYPE && (obj instanceof Boolean)) {
            return obj;
        }
        if (obj == null) {
            return null;
        }
        return cls.isAssignableFrom(obj.getClass()) ? cls.cast(obj) : cls.getConstructor(obj.getClass()).newInstance(obj);
    }

    public static String a(Context context, String str) {
        return context.getFilesDir() + "/" + str;
    }

    /* JADX WARN: Not initialized variable reg: 7, insn: 0x00b3: MOVE (r1 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY]), block:B:55:0x00b3 */
    public static String a(Context context, URL url, String str) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        DataInputStream dataInputStream;
        FileOutputStream fileOutputStream2;
        String a2;
        File file;
        FileOutputStream fileOutputStream3 = null;
        fileOutputStream3 = null;
        r1 = null;
        String str2 = null;
        try {
            try {
                a2 = a(context, str);
                file = new File(a2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream3 = fileOutputStream;
            }
        } catch (Exception e) {
            e = e;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        if (file.exists()) {
            try {
                throw null;
            } catch (Exception unused) {
                return a2;
            }
        }
        inputStream = url.openStream();
        try {
            dataInputStream = new DataInputStream(inputStream);
            try {
                byte[] bArr = new byte[4096];
                fileOutputStream2 = context.openFileOutput(str + ".temp", 0);
                while (true) {
                    try {
                        int read = dataInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                        Log.e("StartAppWall.VideoUtil", "Error downloading video from " + url, e);
                        new File(a(context, str + ".temp")).delete();
                        try {
                            inputStream.close();
                            dataInputStream.close();
                            fileOutputStream2.close();
                            return str2;
                        } catch (Exception unused2) {
                            return str2;
                        }
                    }
                }
                new File(a(context, str + ".temp")).renameTo(file);
                try {
                    inputStream.close();
                    dataInputStream.close();
                    str2 = a2;
                } catch (Exception unused3) {
                    return a2;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream2 = null;
            } catch (Throwable th3) {
                th = th3;
                try {
                    inputStream.close();
                    dataInputStream.close();
                    fileOutputStream3.close();
                } catch (Exception unused4) {
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            dataInputStream = null;
            fileOutputStream2 = null;
            Log.e("StartAppWall.VideoUtil", "Error downloading video from " + url, e);
            new File(a(context, str + ".temp")).delete();
            inputStream.close();
            dataInputStream.close();
            fileOutputStream2.close();
            return str2;
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = null;
            inputStream.close();
            dataInputStream.close();
            fileOutputStream3.close();
            throw th;
        }
        fileOutputStream2.close();
        return str2;
    }

    public static String a(View view) {
        String name = view.getClass().getName();
        if (name.startsWith("android.") || name.startsWith("androidx.") || name.startsWith("com.android.")) {
            return view.getClass().getSimpleName();
        }
        String packageName = view.getContext().getPackageName();
        StringBuilder sb = new StringBuilder();
        sb.append(packageName);
        sb.append(".");
        return name.startsWith(sb.toString()) ? name.substring(packageName.length()) : name;
    }

    public static String a(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations != null && declaredAnnotations.length > 0) {
            Annotation annotation = field.getDeclaredAnnotations()[0];
            if (annotation.annotationType().equals(f.class)) {
                f fVar = (f) annotation;
                if (!"".equals(fVar.name())) {
                    return fVar.name();
                }
            }
        }
        return field.getName();
    }

    public static String a(List<String> list) {
        td tdVar = new td();
        long currentTimeMillis = System.currentTimeMillis();
        BloomVersion bloomVersion = BloomVersion.FOUR;
        sd sdVar = tdVar.b.a.get(bloomVersion).b;
        sdVar.getClass();
        OpenBitSet openBitSet = new OpenBitSet(sdVar.a * sdVar.b);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            ByteBuffer wrap = ByteBuffer.wrap(it.next().getBytes());
            long c = openBitSet.c();
            int i = sdVar.a;
            long[] jArr = new long[i];
            long j = c / i;
            long j2 = currentTimeMillis;
            long a2 = a(wrap, wrap.position(), wrap.remaining(), 0L);
            long a3 = a(wrap, wrap.position(), wrap.remaining(), a2);
            Iterator<String> it2 = it;
            int i2 = 0;
            while (i2 < sdVar.a) {
                BloomVersion bloomVersion2 = bloomVersion;
                long j3 = i2;
                jArr[i2] = (j3 * j) + Math.abs(((j3 * a3) + a2) % j);
                i2++;
                bloomVersion = bloomVersion2;
                sdVar = sdVar;
            }
            BloomVersion bloomVersion3 = bloomVersion;
            sd sdVar2 = sdVar;
            for (int i3 = 0; i3 < i; i3++) {
                openBitSet.b(jArr[i3]);
            }
            bloomVersion = bloomVersion3;
            currentTimeMillis = j2;
            it = it2;
            sdVar = sdVar2;
        }
        long j4 = currentTimeMillis;
        BloomVersion bloomVersion4 = bloomVersion;
        he heVar = tdVar.a;
        heVar.getClass();
        try {
            String a4 = heVar.a.a(openBitSet);
            return j4 + "-" + bloomVersion4.c() + "-" + heVar.b.a.get(bloomVersion4).a.a(a4);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        return new String(bArr, wd.a);
    }

    public static List<AdDetails> a(Context context, List<AdDetails> list, int i, Set<String> set, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        boolean z2 = false;
        for (AdDetails adDetails : list) {
            ArrayList arrayList5 = (ArrayList) aa.a((List<String>) Arrays.asList(adDetails.v()));
            AppPresenceDetails appPresenceDetails = new AppPresenceDetails(arrayList5.isEmpty() ? null : (String) arrayList5.get(0), adDetails.b(), i, adDetails.n());
            boolean z3 = adDetails.b() != null && adDetails.b().startsWith("!");
            boolean a2 = ya.a(context, z3 ? adDetails.b().substring(1) : adDetails.b(), adDetails.n());
            boolean z4 = AdsCommonMetaData.h.H() && ((a2 && !z3) || (!a2 && z3));
            arrayList3.add(appPresenceDetails);
            if (z4) {
                appPresenceDetails.a(a2);
                appPresenceDetails.b(false);
                if (!z3) {
                    arrayList2.add(adDetails);
                    arrayList4.add(appPresenceDetails);
                }
                set.add(adDetails.o());
                z2 = true;
            } else {
                arrayList.add(adDetails);
            }
        }
        if (arrayList.size() < 5 && (list.size() != 1 || i > 0)) {
            int min = Math.min(5 - arrayList.size(), arrayList2.size());
            arrayList.addAll(arrayList2.subList(0, min));
            Iterator it = arrayList4.subList(0, min).iterator();
            while (it.hasNext()) {
                ((AppPresenceDetails) it.next()).b(true);
            }
        }
        if (z2) {
            SimpleTokenUtils.f(context);
            if (z) {
                new k6(context, arrayList3).a();
            }
        }
        return arrayList;
    }

    public static List<Object> a(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(b(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static List<AppPresenceDetails> a(String str, int i) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = new String[0];
        String a2 = aa.a(str, "@tracking@", "@tracking@");
        if (a2 != null) {
            strArr = a2.split(",");
        }
        String[] strArr2 = new String[0];
        String a3 = aa.a(str, "@appPresencePackage@", "@appPresencePackage@");
        if (a3 != null) {
            strArr2 = a3.split(",");
        }
        String[] strArr3 = new String[0];
        String a4 = aa.a(str, "@minAppVersion@", "@minAppVersion@");
        if (a4 != null) {
            strArr3 = a4.split(",");
        }
        int i2 = 0;
        while (i2 < strArr2.length) {
            arrayList.add(new AppPresenceDetails(strArr.length > i2 ? strArr[i2] : null, strArr2[i2], i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        while (i2 < strArr.length) {
            arrayList.add(new AppPresenceDetails(strArr[i2], "", i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        return arrayList;
    }

    public static List<Node> a(Node node, String str, String str2, List<String> list) {
        NamedNodeMap attributes;
        Node namedItem;
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        int length = childNodes.getLength();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeName().equals(str)) {
                if (TextUtils.isEmpty(str2) || list == null || !((attributes = item.getAttributes()) == null || (namedItem = attributes.getNamedItem(str2)) == null || !list.contains(namedItem.getNodeValue()))) {
                    arrayList.add(item);
                }
            }
        }
        return arrayList;
    }

    public static JSONObject a(View view, Rect rect, boolean z) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("class", a(view));
            if (view.getId() == -1) {
                str = null;
            } else {
                try {
                    str = view.getContext().getResources().getResourceName(view.getId());
                } catch (Resources.NotFoundException unused) {
                    str = "0x" + Integer.toHexString(view.getId());
                }
            }
            if (str != null) {
                jSONObject.put(ConnectionModel.ID, str);
            }
            if (z) {
                jSONObject.put("target", true);
            }
            if (Build.VERSION.SDK_INT >= 11 && view.getAlpha() < 1.0f) {
                jSONObject.put("alpha", view.getAlpha());
            }
            if (rect != null) {
                jSONObject.put("left", rect.left);
                jSONObject.put("top", rect.top);
                jSONObject.put("right", rect.right);
                jSONObject.put("bottom", rect.bottom);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void a(Context context, int i, int i2, int i3, int i4, WebView webView) {
        aa.a(webView, true, "mraid.setCurrentPosition", Integer.valueOf(Math.round(i / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i2 / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i3 / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i4 / context.getResources().getDisplayMetrics().density)));
    }

    public static void a(Context context, int i, int i2, WebView webView) {
        aa.a(webView, true, "mraid.setMaxSize", Integer.valueOf(Math.round(i / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i2 / context.getResources().getDisplayMetrics().density)));
    }

    public static void a(Context context, Point point, View view) {
        point.x = Math.round(view.getMeasuredWidth() / context.getResources().getDisplayMetrics().density);
        point.y = Math.round(view.getMeasuredHeight() / context.getResources().getDisplayMetrics().density);
    }

    public static void a(Context context, WindowManager windowManager, Point point) {
        if (Build.VERSION.SDK_INT >= 13) {
            windowManager.getDefaultDisplay().getSize(point);
        } else {
            point.x = windowManager.getDefaultDisplay().getWidth();
            point.y = windowManager.getDefaultDisplay().getHeight();
        }
        point.x = Math.round(point.x / context.getResources().getDisplayMetrics().density);
        point.y = Math.round(point.y / context.getResources().getDisplayMetrics().density);
    }

    public static void a(Context context, WebView webView, d8 d8Var) {
        if (d8Var == null) {
            d8Var = new d8(context);
        }
        a(webView, "mraid.SUPPORTED_FEATURES.CALENDAR", d8Var.b.contains("calendar") && Build.VERSION.SDK_INT >= 14 && ya.a(d8Var.a, "android.permission.WRITE_CALENDAR"));
        a(webView, "mraid.SUPPORTED_FEATURES.INLINEVIDEO", d8Var.b.contains("inlineVideo"));
        a(webView, "mraid.SUPPORTED_FEATURES.SMS", d8Var.b.contains("sms") && ya.a(d8Var.a, "android.permission.SEND_SMS"));
        a(webView, "mraid.SUPPORTED_FEATURES.STOREPICTURE", d8Var.b.contains("storePicture"));
        a(webView, "mraid.SUPPORTED_FEATURES.TEL", d8Var.b.contains("tel") && ya.a(d8Var.a, "android.permission.CALL_PHONE"));
    }

    public static void a(Context context, BannerListener bannerListener, View view) {
        g5.a(bannerListener == null ? null : new i2(bannerListener, view, context));
    }

    public static void a(Context context, AdDisplayListener adDisplayListener, Ad ad) {
        g5.a(adDisplayListener == null ? null : new e6(adDisplayListener, ad, context));
    }

    public static void a(Context context, AdEventListener adEventListener, Ad ad) {
        g5.a(adEventListener == null ? null : new g6(adEventListener, ad, context));
    }

    public static void a(Context context, DiskAdCacheManager$DiskCachedAd diskAdCacheManager$DiskCachedAd, g7 g7Var, AdEventListener adEventListener) {
        n5 a2 = diskAdCacheManager$DiskCachedAd.a();
        a2.setContext(context);
        Map<Activity, Integer> map = aa.a;
        boolean z = true;
        if (a2 instanceof InterstitialAd) {
            InterstitialAd interstitialAd = (InterstitialAd) a2;
            String b2 = diskAdCacheManager$DiskCachedAd.b();
            if (b2 == null || b2.equals("")) {
                a(context, adEventListener, (Ad) null);
                return;
            }
            if (AdsCommonMetaData.h.H()) {
                List<AppPresenceDetails> a3 = a(b2, 0);
                ArrayList arrayList = new ArrayList();
                if (a(context, a3, 0, new HashSet(), arrayList).booleanValue()) {
                    new k6(context, arrayList).a();
                    z = false;
                }
            }
            if (!z) {
                a(context, adEventListener, (Ad) null);
                return;
            }
            v6 v6Var = v6.a;
            v6Var.c.put(interstitialAd.k(), b2);
            ((a7) g7Var).a.e = interstitialAd;
            ComponentLocator.a(context).e.b().a(b2, new f7(context, adEventListener, interstitialAd));
            return;
        }
        if (!(a2 instanceof OfferWall3DAd)) {
            a(context, adEventListener, (Ad) null);
            return;
        }
        OfferWall3DAd offerWall3DAd = (OfferWall3DAd) a2;
        List<AdDetails> g = offerWall3DAd.g();
        if (g == null) {
            a(context, adEventListener, (Ad) null);
            return;
        }
        if (AdsCommonMetaData.h.H()) {
            g = a(context, g, 0, (Set<String>) new HashSet(), true);
        }
        if (g.size() <= 0) {
            a(context, adEventListener, (Ad) null);
            return;
        }
        ((a7) g7Var).a.e = offerWall3DAd;
        g3 a4 = h3.a.a(offerWall3DAd.h());
        a4.getClass();
        a4.b = new ArrayList();
        a4.c = "";
        Iterator<AdDetails> it = g.iterator();
        while (it.hasNext()) {
            a4.a(it.next());
        }
        b(context, adEventListener, offerWall3DAd);
    }

    public static void a(Context context, x4 x4Var) {
        if (x4Var != null) {
            Iterator<String> it = x4Var.a.iterator();
            while (it.hasNext()) {
                g5.b(context, it.next());
            }
        }
    }

    public static void a(WebView webView, String str, String str2) {
        aa.a(webView, true, "mraid.fireErrorEvent", str, str2);
    }

    public static void a(WebView webView, String str, boolean z) {
        aa.a(webView, false, "mraid.setSupports", str, Boolean.valueOf(z));
    }

    public static void a(TextView textView, Set<String> set) {
        if (set.contains("UNDERLINE")) {
            textView.setPaintFlags(textView.getPaintFlags() | 8);
        }
        int i = 0;
        if (set.contains("BOLD") && set.contains("ITALIC")) {
            i = 3;
        } else if (set.contains("BOLD")) {
            i = 1;
        } else if (set.contains("ITALIC")) {
            i = 2;
        }
        textView.setTypeface(null, i);
    }

    public static void a(MraidState mraidState, WebView webView) {
        aa.a(webView, true, "mraid.fireStateChangeEvent", mraidState.name().toLowerCase());
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray("children");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
            try {
                jSONObject.put("children", optJSONArray);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        optJSONArray.put(jSONObject2);
    }

    public static boolean a() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.d.a(android.content.Context):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(Context context, ViewParent viewParent, BannerOptions bannerOptions, Banner3D banner3D, l2 l2Var) {
        Point point = new Point();
        point.x = bannerOptions.o();
        point.y = bannerOptions.d();
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().width > 0) {
            point.x = Math.round((banner3D.getLayoutParams().width + 1) / context.getResources().getDisplayMetrics().density);
        }
        if (banner3D.getLayoutParams() != null && banner3D.getLayoutParams().height > 0) {
            point.y = Math.round((banner3D.getLayoutParams().height + 1) / context.getResources().getDisplayMetrics().density);
        }
        if (banner3D.getLayoutParams() == null || banner3D.getLayoutParams().width <= 0 || banner3D.getLayoutParams().height <= 0) {
            if (context instanceof Activity) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                try {
                    View view = (View) viewParent;
                    if (view instanceof Banner) {
                        view = (View) view.getParent();
                    }
                    boolean z = false;
                    boolean z2 = false;
                    while (view != null && (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0)) {
                        if (view.getMeasuredWidth() > 0 && !z) {
                            c(context, point, view);
                            z = true;
                        }
                        if (view.getMeasuredHeight() > 0 && !z2) {
                            b(context, point, view);
                            z2 = true;
                        }
                        view = (View) view.getParent();
                    }
                    if (view == null) {
                        a(context, point, decorView);
                    } else {
                        if (!z) {
                            c(context, point, view);
                        }
                        if (!z2) {
                            b(context, point, view);
                        }
                    }
                } catch (Throwable th) {
                    a(context, point, decorView);
                    p7.a(context, th);
                }
            } else {
                try {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    if (windowManager != null) {
                        a(context, windowManager, point);
                    }
                } catch (Throwable th2) {
                    p7.a(context, th2);
                }
            }
        }
        l2 l2Var2 = new l2(point.x, point.y);
        Point point2 = l2Var2.a;
        l2Var.a(point2.x, point2.y);
        Banner3DSize$Size[] values = Banner3DSize$Size.values();
        boolean z3 = false;
        for (int i = 0; i < 6; i++) {
            Banner3DSize$Size banner3DSize$Size = values[i];
            if (banner3DSize$Size.getSize().a.x <= l2Var2.a.x && banner3DSize$Size.getSize().a.y <= l2Var2.a.y) {
                bannerOptions.a(banner3DSize$Size.getSize().a.x, banner3DSize$Size.getSize().a.y);
                z3 = true;
            }
        }
        if (!z3) {
            bannerOptions.a(0, 0);
        }
        return z3;
    }

    public static int b(Context context, int i) {
        return Math.round(i / context.getResources().getDisplayMetrics().density);
    }

    public static AdSession b(WebView webView) {
        try {
            return a(webView);
        } catch (Throwable th) {
            p7.a(webView.getContext(), th);
            return null;
        }
    }

    public static ja b(String str) {
        try {
            try {
                ArrayList arrayList = (ArrayList) a(new JsonReader(new StringReader(str)));
                String str2 = (String) arrayList.get(0);
                if (str2 == null) {
                    throw new IllegalArgumentException(str);
                }
                String str3 = (String) arrayList.get(1);
                if (str3 == null) {
                    throw new IllegalArgumentException(str);
                }
                List list = (List) arrayList.get(2);
                if (list == null) {
                    throw new IllegalArgumentException(str);
                }
                int size = list.size();
                String[] strArr = new String[size];
                Class[] clsArr = new Class[size];
                Object[] objArr = new Object[size];
                for (int i = 0; i < size; i++) {
                    Map map = (Map) list.get(i);
                    if (map == null) {
                        throw new IllegalArgumentException(str);
                    }
                    if (map.size() != 1) {
                        throw new IllegalArgumentException(str);
                    }
                    Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                    String str4 = (String) entry.getKey();
                    if (str4 == null) {
                        throw new IllegalArgumentException(str);
                    }
                    try {
                        Class<?> a2 = a(str4);
                        Object a3 = a(a2, entry.getValue());
                        strArr[i] = str4;
                        clsArr[i] = a2;
                        objArr[i] = a3;
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException(str, e);
                    } catch (ClassNotFoundException e2) {
                        throw new IllegalArgumentException(str, e2);
                    } catch (IllegalAccessException e3) {
                        throw new IllegalArgumentException(str, e3);
                    } catch (InstantiationException e4) {
                        throw new IllegalArgumentException(str, e4);
                    } catch (NoSuchMethodException e5) {
                        throw new IllegalArgumentException(str, e5);
                    } catch (InvocationTargetException e6) {
                        throw new IllegalArgumentException(str, e6);
                    }
                }
                List list2 = (List) arrayList.get(3);
                if (list2 == null) {
                    throw new IllegalArgumentException(str);
                }
                String[] strArr2 = new String[list2.size()];
                int size2 = list2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str5 = (String) list2.get(i2);
                    if (str5 == null) {
                        throw new IllegalArgumentException(str);
                    }
                    strArr2[i2] = str5;
                }
                return new ja(str2, str3, strArr, clsArr, objArr, strArr2);
            } catch (IOException e7) {
                throw new IllegalArgumentException(str, e7);
            }
        } catch (ClassCastException e8) {
            throw new IllegalArgumentException(str, e8);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00ad, code lost:
    
        if ((r4 / android.support.v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) > (com.startapp.sdk.adsbase.AdsCommonMetaData.h.G().f() * android.support.v4.media.session.PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.startapp.sdk.ads.video.VideoUtil$VideoEligibility b(android.content.Context r10) {
        /*
            com.startapp.sdk.ads.video.VideoUtil$VideoEligibility r0 = com.startapp.sdk.ads.video.VideoUtil$VideoEligibility.ELIGIBLE
            com.startapp.sdk.adsbase.AdsCommonMetaData r1 = com.startapp.sdk.adsbase.AdsCommonMetaData.h
            com.startapp.sdk.adsbase.VideoConfig r1 = r1.G()
            int r1 = r1.o()
            r2 = 1
            r3 = 0
            if (r1 >= 0) goto L11
            goto L2d
        L11:
            com.startapp.sdk.components.ComponentLocator r1 = com.startapp.sdk.components.ComponentLocator.a(r10)
            com.startapp.p5 r1 = r1.d()
            java.lang.String r4 = "videoErrorsCount"
            int r1 = r1.getInt(r4, r3)
            com.startapp.sdk.adsbase.AdsCommonMetaData r4 = com.startapp.sdk.adsbase.AdsCommonMetaData.h
            com.startapp.sdk.adsbase.VideoConfig r4 = r4.G()
            int r4 = r4.o()
            if (r1 < r4) goto L2d
            r1 = 1
            goto L2e
        L2d:
            r1 = 0
        L2e:
            if (r1 == 0) goto L32
            com.startapp.sdk.ads.video.VideoUtil$VideoEligibility r0 = com.startapp.sdk.ads.video.VideoUtil$VideoEligibility.INELIGIBLE_ERRORS_THRESHOLD_REACHED
        L32:
            java.lang.Class<com.startapp.sdk.ads.interstitials.OverlayActivity> r1 = com.startapp.sdk.ads.interstitials.OverlayActivity.class
            java.util.Map<android.app.Activity, java.lang.Integer> r4 = com.startapp.aa.a
            android.content.pm.PackageManager r4 = r10.getPackageManager()     // Catch: java.lang.Exception -> L5b
            java.lang.String r5 = r10.getPackageName()     // Catch: java.lang.Exception -> L5b
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r5, r2)     // Catch: java.lang.Exception -> L5b
            android.content.pm.ActivityInfo[] r4 = r4.activities     // Catch: java.lang.Exception -> L5b
            int r5 = r4.length     // Catch: java.lang.Exception -> L5b
            r6 = 0
        L46:
            if (r6 >= r5) goto L5b
            r7 = r4[r6]     // Catch: java.lang.Exception -> L5b
            java.lang.String r7 = r7.name     // Catch: java.lang.Exception -> L5b
            java.lang.String r8 = r1.getName()     // Catch: java.lang.Exception -> L5b
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Exception -> L5b
            if (r7 == 0) goto L58
            r1 = 1
            goto L5c
        L58:
            int r6 = r6 + 1
            goto L46
        L5b:
            r1 = 0
        L5c:
            if (r1 != 0) goto L60
            com.startapp.sdk.ads.video.VideoUtil$VideoEligibility r0 = com.startapp.sdk.ads.video.VideoUtil$VideoEligibility.INELIGIBLE_MISSING_ACTIVITY
        L60:
            java.io.File r10 = r10.getFilesDir()
            r4 = -1
            java.util.Map<android.app.Activity, java.lang.Integer> r1 = com.startapp.aa.a
            int r1 = com.startapp.ya.a
            if (r10 == 0) goto L95
            boolean r1 = r10.isDirectory()     // Catch: java.lang.Throwable -> L94
            if (r1 != 0) goto L73
            goto L95
        L73:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L94
            r6 = 9
            if (r1 < r6) goto L7e
            long r4 = r10.getFreeSpace()     // Catch: java.lang.Throwable -> L94
            goto L95
        L7e:
            android.os.StatFs r1 = new android.os.StatFs     // Catch: java.lang.Throwable -> L94
            java.lang.String r10 = r10.getPath()     // Catch: java.lang.Throwable -> L94
            r1.<init>(r10)     // Catch: java.lang.Throwable -> L94
            int r10 = r1.getBlockSize()     // Catch: java.lang.Throwable -> L94
            long r6 = (long) r10     // Catch: java.lang.Throwable -> L94
            int r10 = r1.getFreeBlocks()     // Catch: java.lang.Throwable -> L94
            long r4 = (long) r10
            long r4 = r4 * r6
            goto L95
        L94:
        L95:
            r6 = 0
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 >= 0) goto L9c
            goto Lb0
        L9c:
            com.startapp.sdk.adsbase.AdsCommonMetaData r10 = com.startapp.sdk.adsbase.AdsCommonMetaData.h
            com.startapp.sdk.adsbase.VideoConfig r10 = r10.G()
            long r6 = r10.f()
            r8 = 1024(0x400, double:5.06E-321)
            long r4 = r4 / r8
            long r6 = r6 * r8
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto Lb0
            goto Lb1
        Lb0:
            r2 = 0
        Lb1:
            if (r2 != 0) goto Lb5
            com.startapp.sdk.ads.video.VideoUtil$VideoEligibility r0 = com.startapp.sdk.ads.video.VideoUtil$VideoEligibility.INELIGIBLE_NO_STORAGE
        Lb5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.d.b(android.content.Context):com.startapp.sdk.ads.video.VideoUtil$VideoEligibility");
    }

    public static Object b(JsonReader jsonReader) throws IOException {
        switch (r9.a[jsonReader.peek().ordinal()]) {
            case 1:
                return a(jsonReader);
            case 2:
                HashMap hashMap = new HashMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    hashMap.put(jsonReader.nextName(), b(jsonReader));
                }
                jsonReader.endObject();
                return hashMap;
            case 3:
                return jsonReader.nextString();
            case 4:
                return new BigDecimal(jsonReader.nextString());
            case 5:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 6:
                jsonReader.nextNull();
                return null;
            default:
                throw new IOException(String.valueOf(jsonReader.peek()));
        }
    }

    public static void b(Context context, int i, int i2, int i3, int i4, WebView webView) {
        aa.a(webView, true, "mraid.setDefaultPosition", Integer.valueOf(Math.round(i / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i2 / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i3 / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i4 / context.getResources().getDisplayMetrics().density)));
    }

    public static void b(Context context, int i, int i2, WebView webView) {
        aa.a(webView, true, "mraid.setScreenSize", Integer.valueOf(Math.round(i / context.getResources().getDisplayMetrics().density)), Integer.valueOf(Math.round(i2 / context.getResources().getDisplayMetrics().density)));
    }

    public static void b(Context context, Point point, View view) {
        point.y = Math.round(((view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()) / context.getResources().getDisplayMetrics().density);
    }

    public static void b(Context context, AdEventListener adEventListener, Ad ad) {
        g5.a(adEventListener == null ? null : new f6(adEventListener, ad, context));
    }

    public static boolean b() {
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            try {
                boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
                process.destroy();
                return z;
            } catch (Throwable unused) {
                if (process != null) {
                    process.destroy();
                }
                return false;
            }
        } catch (Throwable unused2) {
            process = null;
        }
    }

    public static boolean b(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations == null || declaredAnnotations.length == 0) {
            return false;
        }
        Annotation annotation = field.getDeclaredAnnotations()[0];
        if (annotation.annotationType().equals(f.class)) {
            return ((f) annotation).complex();
        }
        return false;
    }

    public static String c() {
        return "startapp_ads".concat(File.separator).concat("interstitials");
    }

    public static void c(Context context) {
        p5 d = ComponentLocator.a(context).d();
        int i = d.getInt("videoErrorsCount", 0);
        p5.a edit = d.edit();
        int i2 = i + 1;
        edit.a("videoErrorsCount", (String) Integer.valueOf(i2));
        edit.a.putInt("videoErrorsCount", i2);
        edit.apply();
    }

    public static void c(Context context, Point point, View view) {
        point.x = Math.round(((view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()) / context.getResources().getDisplayMetrics().density);
    }

    public static String d() {
        return "startapp_ads".concat(File.separator).concat("keys");
    }
}
