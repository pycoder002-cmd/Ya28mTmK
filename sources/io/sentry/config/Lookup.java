package io.sentry.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public final class Lookup {
    private static final String CONFIG_FILE_NAME = "sentry.properties";
    private static boolean checkJndi = true;
    private static Properties configProps;
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) Lookup.class);

    static {
        String configFilePath = getConfigFilePath();
        try {
            InputStream inputStream = getInputStream(configFilePath);
            if (inputStream != null) {
                configProps = new Properties();
                configProps.load(inputStream);
            } else {
                logger.debug("Sentry configuration file not found in filesystem or classpath: '{}'.", configFilePath);
            }
        } catch (Exception e) {
            logger.error("Error loading Sentry configuration file '{}': ", configFilePath, e);
        }
    }

    private Lookup() {
    }

    private static String getConfigFilePath() {
        String property = System.getProperty("sentry.properties.file");
        if (property == null) {
            property = System.getenv("SENTRY_PROPERTIES_FILE");
        }
        return property == null ? CONFIG_FILE_NAME : property;
    }

    private static InputStream getInputStream(String str) throws FileNotFoundException {
        File file = new File(str);
        return (file.isFile() && file.canRead()) ? new FileInputStream(file) : Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
    }

    public static String lookup(String str) {
        return lookup(str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:5:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String lookup(java.lang.String r7, io.sentry.dsn.Dsn r8) {
        /*
            boolean r0 = io.sentry.config.Lookup.checkJndi
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L40
            java.lang.String r0 = "javax.naming.InitialContext"
            java.lang.Class<io.sentry.dsn.Dsn> r3 = io.sentry.dsn.Dsn.class
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch: java.lang.Throwable -> L21
            java.lang.Class.forName(r0, r2, r3)     // Catch: java.lang.Throwable -> L21
            java.lang.String r0 = io.sentry.config.JndiLookup.jndiLookup(r7)     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L41
            org.slf4j.Logger r3 = io.sentry.config.Lookup.logger     // Catch: java.lang.Throwable -> L1f
            java.lang.String r4 = "Found {}={} in JNDI."
            r3.debug(r4, r7, r0)     // Catch: java.lang.Throwable -> L1f
            goto L41
        L1f:
            r3 = move-exception
            goto L23
        L21:
            r3 = move-exception
            r0 = r1
        L23:
            org.slf4j.Logger r4 = io.sentry.config.Lookup.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "JNDI is not available: "
            r5.append(r6)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.trace(r3)
            io.sentry.config.Lookup.checkJndi = r2
            goto L41
        L40:
            r0 = r1
        L41:
            if (r0 != 0) goto L65
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "sentry."
            r0.append(r3)
            java.lang.String r3 = r7.toLowerCase()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = java.lang.System.getProperty(r0)
            if (r0 == 0) goto L65
            org.slf4j.Logger r3 = io.sentry.config.Lookup.logger
            java.lang.String r4 = "Found {}={} in Java System Properties."
            r3.debug(r4, r7, r0)
        L65:
            if (r0 != 0) goto L91
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "SENTRY_"
            r0.append(r3)
            java.lang.String r3 = "."
            java.lang.String r4 = "_"
            java.lang.String r3 = r7.replace(r3, r4)
            java.lang.String r3 = r3.toUpperCase()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = java.lang.System.getenv(r0)
            if (r0 == 0) goto L91
            org.slf4j.Logger r3 = io.sentry.config.Lookup.logger
            java.lang.String r4 = "Found {}={} in System Environment Variables."
            r3.debug(r4, r7, r0)
        L91:
            if (r0 != 0) goto La9
            if (r8 == 0) goto La9
            java.util.Map r8 = r8.getOptions()
            java.lang.Object r8 = r8.get(r7)
            r0 = r8
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto La9
            org.slf4j.Logger r8 = io.sentry.config.Lookup.logger
            java.lang.String r3 = "Found {}={} in DSN."
            r8.debug(r3, r7, r0)
        La9:
            if (r0 != 0) goto Lcb
            java.util.Properties r8 = io.sentry.config.Lookup.configProps
            if (r8 == 0) goto Lcb
            java.util.Properties r8 = io.sentry.config.Lookup.configProps
            java.lang.String r0 = r8.getProperty(r7)
            if (r0 == 0) goto Lcb
            org.slf4j.Logger r8 = io.sentry.config.Lookup.logger
            java.lang.String r3 = "Found {}={} in {}."
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r2] = r7
            r7 = 1
            r4[r7] = r0
            r7 = 2
            java.lang.String r2 = "sentry.properties"
            r4[r7] = r2
            r8.debug(r3, r4)
        Lcb:
            if (r0 == 0) goto Ld2
            java.lang.String r7 = r0.trim()
            return r7
        Ld2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.config.Lookup.lookup(java.lang.String, io.sentry.dsn.Dsn):java.lang.String");
    }
}
