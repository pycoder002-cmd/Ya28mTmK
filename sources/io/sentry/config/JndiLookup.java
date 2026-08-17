package io.sentry.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public final class JndiLookup {
    private static final String JNDI_PREFIX = "java:comp/env/sentry/";
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) JndiLookup.class);

    private JndiLookup() {
    }

    public static String jndiLookup(String str) {
        try {
            return (String) new InitialContext().lookup(JNDI_PREFIX + str);
        } catch (RuntimeException e) {
            logger.warn("Odd RuntimeException while testing for JNDI", (Throwable) e);
            return null;
        } catch (NamingException unused) {
            logger.trace("No /sentry/" + str + " in JNDI");
            return null;
        } catch (NoInitialContextException unused2) {
            logger.trace("JNDI not configured for Sentry (NoInitialContextEx)");
            return null;
        }
    }
}
