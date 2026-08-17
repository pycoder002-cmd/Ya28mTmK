package com.iab.omid.library.startapp.adsession;

import android.view.View;
import com.iab.omid.library.startapp.b.c;
import com.iab.omid.library.startapp.b.f;
import com.iab.omid.library.startapp.d.e;
import com.iab.omid.library.startapp.publisher.AdSessionStatePublisher;
import com.iab.omid.library.startapp.publisher.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a extends AdSession {
    private static final Pattern a = Pattern.compile("^[a-zA-Z0-9 ]+$");
    private final AdSessionContext b;
    private final AdSessionConfiguration c;
    private com.iab.omid.library.startapp.e.a e;
    private AdSessionStatePublisher f;
    private boolean j;
    private boolean k;
    private final List<c> d = new ArrayList();
    private boolean g = false;
    private boolean h = false;
    private final String i = UUID.randomUUID().toString();

    public a(AdSessionConfiguration adSessionConfiguration, AdSessionContext adSessionContext) {
        this.c = adSessionConfiguration;
        this.b = adSessionContext;
        c(null);
        this.f = (adSessionContext.getAdSessionContextType() == AdSessionContextType.HTML || adSessionContext.getAdSessionContextType() == AdSessionContextType.JAVASCRIPT) ? new com.iab.omid.library.startapp.publisher.a(adSessionContext.getWebView()) : new b(adSessionContext.getInjectedResourcesMap(), adSessionContext.getOmidJsScriptContent());
        this.f.a();
        com.iab.omid.library.startapp.b.a.a().a(this);
        this.f.a(adSessionConfiguration);
    }

    private c a(View view) {
        for (c cVar : this.d) {
            if (cVar.a().get() == view) {
                return cVar;
            }
        }
        return null;
    }

    private void a(String str) {
        if (str != null) {
            if (str.length() > 50) {
                throw new IllegalArgumentException("FriendlyObstruction has detailed reason over 50 characters in length");
            }
            if (!a.matcher(str).matches()) {
                throw new IllegalArgumentException("FriendlyObstruction has detailed reason that contains characters not in [a-z][A-Z][0-9] or space");
            }
        }
    }

    private static void b(View view) {
        if (view == null) {
            throw new IllegalArgumentException("FriendlyObstruction is null");
        }
    }

    private void c(View view) {
        this.e = new com.iab.omid.library.startapp.e.a(view);
    }

    private void d(View view) {
        Collection<a> b = com.iab.omid.library.startapp.b.a.a().b();
        if (b == null || b.isEmpty()) {
            return;
        }
        for (a aVar : b) {
            if (aVar != this && aVar.d() == view) {
                aVar.e.clear();
            }
        }
    }

    private void j() {
        if (this.j) {
            throw new IllegalStateException("Impression event can only be sent once");
        }
    }

    private void k() {
        if (this.k) {
            throw new IllegalStateException("Loaded event can only be sent once");
        }
    }

    public List<c> a() {
        return this.d;
    }

    public void a(JSONObject jSONObject) {
        k();
        getAdSessionStatePublisher().a(jSONObject);
        this.k = true;
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void addFriendlyObstruction(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        if (this.h) {
            return;
        }
        b(view);
        a(str);
        if (a(view) == null) {
            this.d.add(new c(view, friendlyObstructionPurpose, str));
        }
    }

    public void b() {
        j();
        getAdSessionStatePublisher().g();
        this.j = true;
    }

    public void c() {
        k();
        getAdSessionStatePublisher().h();
        this.k = true;
    }

    public View d() {
        return this.e.get();
    }

    public boolean e() {
        return this.g && !this.h;
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void error(ErrorType errorType, String str) {
        if (this.h) {
            throw new IllegalStateException("AdSession is finished");
        }
        e.a(errorType, "Error type is null");
        e.a(str, "Message is null");
        getAdSessionStatePublisher().a(errorType, str);
    }

    public boolean f() {
        return this.g;
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void finish() {
        if (this.h) {
            return;
        }
        this.e.clear();
        removeAllFriendlyObstructions();
        this.h = true;
        getAdSessionStatePublisher().f();
        com.iab.omid.library.startapp.b.a.a().c(this);
        getAdSessionStatePublisher().b();
        this.f = null;
    }

    public boolean g() {
        return this.h;
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public String getAdSessionId() {
        return this.i;
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public AdSessionStatePublisher getAdSessionStatePublisher() {
        return this.f;
    }

    public boolean h() {
        return this.c.isNativeImpressionOwner();
    }

    public boolean i() {
        return this.c.isNativeMediaEventsOwner();
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void registerAdView(View view) {
        if (this.h) {
            return;
        }
        e.a(view, "AdView is null");
        if (d() == view) {
            return;
        }
        c(view);
        getAdSessionStatePublisher().i();
        d(view);
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void removeAllFriendlyObstructions() {
        if (this.h) {
            return;
        }
        this.d.clear();
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void removeFriendlyObstruction(View view) {
        if (this.h) {
            return;
        }
        b(view);
        c a2 = a(view);
        if (a2 != null) {
            this.d.remove(a2);
        }
    }

    @Override // com.iab.omid.library.startapp.adsession.AdSession
    public void start() {
        if (this.g) {
            return;
        }
        this.g = true;
        com.iab.omid.library.startapp.b.a.a().b(this);
        this.f.a(f.a().d());
        this.f.a(this, this.b);
    }
}
