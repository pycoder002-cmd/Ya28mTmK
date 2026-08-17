package io.sentry.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import io.sentry.DefaultSentryClientFactory;
import io.sentry.SentryClient;
import io.sentry.android.event.helper.AndroidEventBuilderHelper;
import io.sentry.buffer.Buffer;
import io.sentry.buffer.DiskBuffer;
import io.sentry.config.Lookup;
import io.sentry.context.ContextManager;
import io.sentry.context.SingletonContextManager;
import io.sentry.dsn.Dsn;
import io.sentry.util.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public class AndroidSentryClientFactory extends DefaultSentryClientFactory {
    private static final String DEFAULT_BUFFER_DIR = "sentry-buffered-events";
    public static final String TAG = "io.sentry.android.AndroidSentryClientFactory";
    private Context ctx;

    public AndroidSentryClientFactory(Context context) {
        Log.d(TAG, "Construction of Android Sentry.");
        this.ctx = context.getApplicationContext();
    }

    private boolean checkPermission(String str) {
        return this.ctx.checkCallingOrSelfPermission(str) == 0;
    }

    @Override // io.sentry.DefaultSentryClientFactory, io.sentry.SentryClientFactory
    public SentryClient createSentryClient(Dsn dsn) {
        if (!checkPermission("android.permission.INTERNET")) {
            Log.e(TAG, "android.permission.INTERNET is required to connect to the Sentry server, please add it to your AndroidManifest.xml");
        }
        Log.d(TAG, "Sentry init with ctx='" + this.ctx.toString() + "' and dsn='" + dsn + "'");
        String protocol = dsn.getProtocol();
        if (protocol.equalsIgnoreCase("noop")) {
            Log.w(TAG, "*** Couldn't find a suitable DSN, Sentry operations will do nothing! See documentation: https://docs.sentry.io/clients/java/modules/android/ ***");
        } else if (!protocol.equalsIgnoreCase("http") && !protocol.equalsIgnoreCase("https")) {
            String lookup = Lookup.lookup(DefaultSentryClientFactory.ASYNC_OPTION, dsn);
            if (lookup != null && lookup.equalsIgnoreCase("false")) {
                throw new IllegalArgumentException("Sentry Android cannot use synchronous connections, remove 'async=false' from your options.");
            }
            throw new IllegalArgumentException("Only 'http' or 'https' connections are supported in Sentry Android, but received: " + protocol);
        }
        SentryClient createSentryClient = super.createSentryClient(dsn);
        createSentryClient.addBuilderHelper(new AndroidEventBuilderHelper(this.ctx));
        return createSentryClient;
    }

    @Override // io.sentry.DefaultSentryClientFactory
    protected Buffer getBuffer(Dsn dsn) {
        String lookup = Lookup.lookup(DefaultSentryClientFactory.BUFFER_DIR_OPTION, dsn);
        File file = lookup != null ? new File(lookup) : new File(this.ctx.getCacheDir().getAbsolutePath(), DEFAULT_BUFFER_DIR);
        Log.d(TAG, "Using buffer dir: " + file.getAbsolutePath());
        return new DiskBuffer(file, getBufferSize(dsn));
    }

    @Override // io.sentry.DefaultSentryClientFactory
    protected ContextManager getContextManager(Dsn dsn) {
        return new SingletonContextManager();
    }

    @Override // io.sentry.DefaultSentryClientFactory
    protected Collection<String> getInAppFrames(Dsn dsn) {
        Collection<String> inAppFrames = super.getInAppFrames(dsn);
        if (inAppFrames.isEmpty()) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = this.ctx.getPackageManager().getPackageInfo(this.ctx.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Error getting package information.", e);
            }
            if (packageInfo != null && !Util.isNullOrEmpty(packageInfo.packageName)) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(packageInfo.packageName);
                return arrayList;
            }
        }
        return inAppFrames;
    }
}
