package io.sentry.connection;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* loaded from: classes2.dex */
public class ProxyAuthenticator extends Authenticator {
    private String pass;
    private String user;

    public ProxyAuthenticator(String str, String str2) {
        this.user = str;
        this.pass = str2;
    }

    @Override // java.net.Authenticator
    protected PasswordAuthentication getPasswordAuthentication() {
        if (getRequestorType() == Authenticator.RequestorType.PROXY) {
            return new PasswordAuthentication(this.user, this.pass.toCharArray());
        }
        return null;
    }
}
