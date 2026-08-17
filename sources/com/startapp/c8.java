package com.startapp;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c8 extends WebViewClient {
    private static final String LOG_TAG = "c8";
    private static final String MRAID_JS = "mraid.js";
    private static final String MRAID_PREFIX = "mraid://";
    private b8 controller;
    private boolean isMraidInjected = false;

    public c8(b8 b8Var) {
        this.controller = b8Var;
    }

    private WebResourceResponse createMraidInjectionResponse() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(("javascript:" + g8.a()).getBytes()));
    }

    public boolean invokeMraidMethod(String str) {
        String[] strArr = {"close", "resize"};
        String[] strArr2 = {"createCalendarEvent", "expand", "open", "playVideo", "storePicture", "useCustomClose"};
        String[] strArr3 = {"setOrientationProperties", "setResizeProperties"};
        try {
            Map<String, String> a = h8.a(str);
            String str2 = a.get("command");
            if (Arrays.asList(strArr).contains(str2)) {
                b8.class.getDeclaredMethod(str2, new Class[0]).invoke(this.controller, new Object[0]);
            } else if (Arrays.asList(strArr2).contains(str2)) {
                Method declaredMethod = b8.class.getDeclaredMethod(str2, String.class);
                char c = 65535;
                int hashCode = str2.hashCode();
                String str3 = "useCustomClose";
                if (hashCode != -733616544) {
                    if (hashCode == 1614272768 && str2.equals("useCustomClose")) {
                        c = 1;
                    }
                } else if (str2.equals("createCalendarEvent")) {
                    c = 0;
                }
                if (c == 0) {
                    str3 = "eventJSON";
                } else if (c != 1) {
                    str3 = FileDownloadModel.URL;
                }
                declaredMethod.invoke(this.controller, a.get(str3));
            } else if (Arrays.asList(strArr3).contains(str2)) {
                b8.class.getDeclaredMethod(str2, Map.class).invoke(this.controller, a);
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isMraidUrl(String str) {
        return str != null && str.startsWith(MRAID_PREFIX);
    }

    public boolean matchesInjectionUrl(String str) {
        try {
            return MRAID_JS.equals(Uri.parse(str.toLowerCase(Locale.US)).getLastPathSegment());
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (this.isMraidInjected || !matchesInjectionUrl(str)) {
            return super.shouldInterceptRequest(webView, str);
        }
        this.isMraidInjected = true;
        return createMraidInjectionResponse();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (webView == null || str == null || aa.b(webView.getContext(), str)) {
            return true;
        }
        return isMraidUrl(str) ? invokeMraidMethod(str) : this.controller.open(str);
    }
}
