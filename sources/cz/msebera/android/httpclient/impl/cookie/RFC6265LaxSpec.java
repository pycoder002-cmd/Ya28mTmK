package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import java.util.List;

@ThreadSafe
/* loaded from: classes.dex */
public class RFC6265LaxSpec extends RFC6265CookieSpecBase {
    public RFC6265LaxSpec() {
        super(new BasicPathHandler(), new BasicDomainHandler(), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RFC6265LaxSpec(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        super(commonCookieAttributeHandlerArr);
    }

    @Override // cz.msebera.android.httpclient.impl.cookie.RFC6265CookieSpecBase, cz.msebera.android.httpclient.cookie.CookieSpec
    public /* bridge */ /* synthetic */ List formatCookies(List list) {
        return super.formatCookies(list);
    }

    public String toString() {
        return "rfc6265-lax";
    }
}
