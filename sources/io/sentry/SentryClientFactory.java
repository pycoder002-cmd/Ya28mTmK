package io.sentry;

import io.sentry.config.Lookup;
import io.sentry.dsn.Dsn;
import io.sentry.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public abstract class SentryClientFactory {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) SentryClientFactory.class);

    private static Dsn resolveDsn(String str) {
        try {
            if (Util.isNullOrEmpty(str)) {
                str = Dsn.dsnLookup();
            }
            return new Dsn(str);
        } catch (Exception e) {
            logger.error("Error creating valid DSN from: '{}'.", str, e);
            throw e;
        }
    }

    public static SentryClient sentryClient() {
        return sentryClient(null, null);
    }

    public static SentryClient sentryClient(String str) {
        return sentryClient(str, null);
    }

    public static SentryClient sentryClient(String str, SentryClientFactory sentryClientFactory) {
        Dsn resolveDsn = resolveDsn(str);
        if (sentryClientFactory == null) {
            String lookup = Lookup.lookup("factory", resolveDsn);
            if (Util.isNullOrEmpty(lookup)) {
                sentryClientFactory = new DefaultSentryClientFactory();
            } else {
                try {
                    sentryClientFactory = (SentryClientFactory) Class.forName(lookup).newInstance();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    logger.error("Error creating SentryClient using factory class: '" + lookup + "'.", e);
                    return null;
                }
            }
        }
        return sentryClientFactory.createSentryClient(resolveDsn);
    }

    public abstract SentryClient createSentryClient(Dsn dsn);

    public String toString() {
        return "SentryClientFactory{name='" + getClass().getName() + "'}";
    }
}
