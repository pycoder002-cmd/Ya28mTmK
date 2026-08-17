package com.startapp.sdk.ads.video.tracking;

import com.startapp.a5;
import com.startapp.f;
import com.startapp.f5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoTrackingDetails implements Serializable {
    private static final long serialVersionUID = 1;

    @f(type = AbsoluteTrackingLink.class)
    private AbsoluteTrackingLink[] absoluteTrackingUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] creativeViewUrls;

    @f(type = FractionTrackingLink.class)
    private FractionTrackingLink[] fractionTrackingUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] impressionUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] inlineErrorTrackingUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] soundMuteUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] soundUnmuteUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClickTrackingUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClosedUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPausedUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollClosedUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollImpressionUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoResumedUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoRewardedUrls;

    @f(type = ActionTrackingLink.class)
    private ActionTrackingLink[] videoSkippedUrls;

    public VideoTrackingDetails() {
    }

    public VideoTrackingDetails(a5 a5Var) {
        this.impressionUrls = b(a5Var.i());
        this.soundMuteUrls = b(a5Var.k());
        this.soundUnmuteUrls = b(a5Var.p());
        this.videoPausedUrls = b(a5Var.l());
        this.videoResumedUrls = b(a5Var.m());
        this.videoSkippedUrls = b(a5Var.o());
        this.videoClosedUrls = b(a5Var.e());
        this.inlineErrorTrackingUrls = b(a5Var.g());
        this.videoClickTrackingUrls = b(a5Var.d());
        this.absoluteTrackingUrls = a(a5Var.a());
        this.fractionTrackingUrls = a(a5Var.h(), a5Var.f());
    }

    public static ActionTrackingLink[] b(List<String> list) {
        if (list == null) {
            return new ActionTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            ActionTrackingLink actionTrackingLink = new ActionTrackingLink();
            actionTrackingLink.b(str);
            actionTrackingLink.a(true);
            actionTrackingLink.a("");
            arrayList.add(actionTrackingLink);
        }
        return (ActionTrackingLink[]) arrayList.toArray(new ActionTrackingLink[arrayList.size()]);
    }

    public AbsoluteTrackingLink[] a() {
        return this.absoluteTrackingUrls;
    }

    public final AbsoluteTrackingLink[] a(List<f5<Integer>> list) {
        if (list == null) {
            return new AbsoluteTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (f5<Integer> f5Var : list) {
            AbsoluteTrackingLink absoluteTrackingLink = new AbsoluteTrackingLink();
            absoluteTrackingLink.b(f5Var.c);
            if (f5Var.d.intValue() != -1) {
                absoluteTrackingLink.a(f5Var.d.intValue());
            }
            absoluteTrackingLink.a(true);
            absoluteTrackingLink.a("");
            arrayList.add(absoluteTrackingLink);
        }
        return (AbsoluteTrackingLink[]) arrayList.toArray(new AbsoluteTrackingLink[arrayList.size()]);
    }

    public final FractionTrackingLink[] a(List<f5<Float>> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list.size());
        for (f5<Float> f5Var : list) {
            FractionTrackingLink fractionTrackingLink = new FractionTrackingLink();
            fractionTrackingLink.b(f5Var.c);
            fractionTrackingLink.a((int) (f5Var.d.floatValue() * 100.0f));
            fractionTrackingLink.a(true);
            fractionTrackingLink.a("");
            arrayList.add(fractionTrackingLink);
        }
        for (String str : list2) {
            FractionTrackingLink fractionTrackingLink2 = new FractionTrackingLink();
            fractionTrackingLink2.b(str);
            fractionTrackingLink2.a(100);
            fractionTrackingLink2.a(true);
            fractionTrackingLink2.a("");
            arrayList.add(fractionTrackingLink2);
        }
        return arrayList.size() > 0 ? (FractionTrackingLink[]) arrayList.toArray(new FractionTrackingLink[arrayList.size()]) : new FractionTrackingLink[0];
    }

    public ActionTrackingLink[] b() {
        return this.creativeViewUrls;
    }

    public FractionTrackingLink[] c() {
        return this.fractionTrackingUrls;
    }

    public ActionTrackingLink[] d() {
        return this.impressionUrls;
    }

    public ActionTrackingLink[] e() {
        return this.inlineErrorTrackingUrls;
    }

    public ActionTrackingLink[] f() {
        return this.soundMuteUrls;
    }

    public ActionTrackingLink[] g() {
        return this.soundUnmuteUrls;
    }

    public ActionTrackingLink[] h() {
        return this.videoClickTrackingUrls;
    }

    public ActionTrackingLink[] i() {
        return this.videoClosedUrls;
    }

    public ActionTrackingLink[] j() {
        return this.videoPausedUrls;
    }

    public ActionTrackingLink[] k() {
        return this.videoPostRollClosedUrls;
    }

    public ActionTrackingLink[] l() {
        return this.videoPostRollImpressionUrls;
    }

    public ActionTrackingLink[] m() {
        return this.videoResumedUrls;
    }

    public ActionTrackingLink[] n() {
        return this.videoRewardedUrls;
    }

    public ActionTrackingLink[] o() {
        return this.videoSkippedUrls;
    }

    public String toString() {
        return super.toString();
    }
}
