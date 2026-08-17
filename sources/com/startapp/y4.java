package com.startapp;

import android.text.TextUtils;
import com.startapp.sdk.ads.video.tracking.VideoTrackingLink;
import com.startapp.sdk.ads.video.tracking.VideoTrackingParams;
import com.startapp.sdk.ads.video.vast.VASTErrorCodes;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y4 {
    public VideoTrackingLink[] a;
    public VideoTrackingParams b;
    public String c;
    public int d;
    public String e = "";
    public VASTErrorCodes f;

    public y4(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, String str, int i) {
        this.a = videoTrackingLinkArr;
        this.b = videoTrackingParams;
        this.c = str;
        this.d = i;
    }

    public x4 a() {
        Object obj;
        Object obj2 = null;
        if (!((this.a == null || this.b == null) ? false : true)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        VideoTrackingLink[] videoTrackingLinkArr = this.a;
        int length = videoTrackingLinkArr.length;
        int i = 0;
        while (i < length) {
            VideoTrackingLink videoTrackingLink = videoTrackingLinkArr[i];
            if (videoTrackingLink.c() == null || (this.b.b() > 0 && !videoTrackingLink.d())) {
                obj = obj2;
            } else {
                StringBuilder sb = new StringBuilder();
                VideoTrackingLink.TrackingSource b = videoTrackingLink.b();
                if (b == null) {
                    b = aa.d(videoTrackingLink.c()) ? VideoTrackingLink.TrackingSource.STARTAPP : VideoTrackingLink.TrackingSource.EXTERNAL;
                }
                VideoTrackingParams videoTrackingParams = this.b;
                videoTrackingParams.internalParamsIndicator = b == VideoTrackingLink.TrackingSource.STARTAPP;
                VideoTrackingParams c = videoTrackingParams.a(videoTrackingLink.d()).c(videoTrackingLink.a());
                String c2 = videoTrackingLink.c();
                String str = this.c;
                String replace = c2.replace("[ASSETURI]", str != null ? TextUtils.htmlEncode(str) : "");
                int i2 = this.d;
                long convert = TimeUnit.SECONDS.convert(i2, TimeUnit.MILLISECONDS);
                long j = i2 % 1000;
                Locale locale = Locale.US;
                String replace2 = replace.replace("[CONTENTPLAYHEAD]", TextUtils.htmlEncode(String.format(locale, "%02d:%02d:%02d.%03d", Long.valueOf(convert / 3600), Long.valueOf((convert % 3600) / 60), Long.valueOf(convert % 60), Long.valueOf(j)))).replace("[CACHEBUSTING]", TextUtils.htmlEncode(String.valueOf(new SecureRandom().nextInt(90000000) + 10000000)));
                String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", locale).format(new Date());
                int length2 = format.length() - 2;
                String replace3 = replace2.replace("[TIMESTAMP]", TextUtils.htmlEncode(format.substring(0, length2) + ":" + format.substring(length2)));
                VASTErrorCodes vASTErrorCodes = this.f;
                if (vASTErrorCodes != null) {
                    replace3 = replace3.replace("[ERRORCODE]", String.valueOf(vASTErrorCodes.a()));
                }
                sb.append(replace3);
                sb.append(c.e());
                if (c.internalParamsIndicator) {
                    obj = null;
                    sb.append(wa.c(g5.a(c2, (String) null)));
                } else {
                    obj = null;
                }
                arrayList.add(sb.toString());
            }
            i++;
            obj2 = obj;
        }
        return new x4(arrayList, this.e);
    }
}
