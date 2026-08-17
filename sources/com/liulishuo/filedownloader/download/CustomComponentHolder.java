package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.services.ForegroundServiceConfig;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class CustomComponentHolder {
    private FileDownloadHelper.ConnectionCountAdapter connectionCountAdapter;
    private FileDownloadHelper.ConnectionCreator connectionCreator;
    private FileDownloadDatabase database;
    private ForegroundServiceConfig foregroundServiceConfig;
    private FileDownloadHelper.IdGenerator idGenerator;
    private DownloadMgrInitialParams initialParams;
    private FileDownloadHelper.OutputStreamCreator outputStreamCreator;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LazyLoader {
        private static final CustomComponentHolder INSTANCE = new CustomComponentHolder();

        private LazyLoader() {
        }
    }

    private FileDownloadHelper.ConnectionCountAdapter getConnectionCountAdapter() {
        if (this.connectionCountAdapter != null) {
            return this.connectionCountAdapter;
        }
        synchronized (this) {
            if (this.connectionCountAdapter == null) {
                this.connectionCountAdapter = getDownloadMgrInitialParams().createConnectionCountAdapter();
            }
        }
        return this.connectionCountAdapter;
    }

    private FileDownloadHelper.ConnectionCreator getConnectionCreator() {
        if (this.connectionCreator != null) {
            return this.connectionCreator;
        }
        synchronized (this) {
            if (this.connectionCreator == null) {
                this.connectionCreator = getDownloadMgrInitialParams().createConnectionCreator();
            }
        }
        return this.connectionCreator;
    }

    private DownloadMgrInitialParams getDownloadMgrInitialParams() {
        if (this.initialParams != null) {
            return this.initialParams;
        }
        synchronized (this) {
            if (this.initialParams == null) {
                this.initialParams = new DownloadMgrInitialParams();
            }
        }
        return this.initialParams;
    }

    public static CustomComponentHolder getImpl() {
        return LazyLoader.INSTANCE;
    }

    private FileDownloadHelper.OutputStreamCreator getOutputStreamCreator() {
        if (this.outputStreamCreator != null) {
            return this.outputStreamCreator;
        }
        synchronized (this) {
            if (this.outputStreamCreator == null) {
                this.outputStreamCreator = getDownloadMgrInitialParams().createOutputStreamCreator();
            }
        }
        return this.outputStreamCreator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d6, code lost:
    
        if (r7.getSoFar() > 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x004b, code lost:
    
        if (r7.getSoFar() <= 0) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0106 A[Catch: all -> 0x0100, TryCatch #1 {all -> 0x0100, blocks: (B:28:0x00f4, B:16:0x0106, B:18:0x011c, B:20:0x0120, B:21:0x0137, B:22:0x0144, B:54:0x00a6, B:55:0x00c7, B:57:0x00ce, B:60:0x00dc, B:63:0x00e7), top: B:27:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066 A[Catch: all -> 0x014d, TryCatch #3 {all -> 0x014d, blocks: (B:7:0x0021, B:11:0x0057, B:39:0x0066, B:41:0x0071, B:10:0x0054), top: B:6:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ce A[Catch: all -> 0x0100, TryCatch #1 {all -> 0x0100, blocks: (B:28:0x00f4, B:16:0x0106, B:18:0x011c, B:20:0x0120, B:21:0x0137, B:22:0x0144, B:54:0x00a6, B:55:0x00c7, B:57:0x00ce, B:60:0x00dc, B:63:0x00e7), top: B:27:0x00f4 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void maintainDatabase(com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer r26) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.CustomComponentHolder.maintainDatabase(com.liulishuo.filedownloader.database.FileDownloadDatabase$Maintainer):void");
    }

    public FileDownloadConnection createConnection(String str) throws IOException {
        return getConnectionCreator().create(str);
    }

    public FileDownloadOutputStream createOutputStream(File file) throws IOException {
        return getOutputStreamCreator().create(file);
    }

    public int determineConnectionCount(int i, String str, String str2, long j) {
        return getConnectionCountAdapter().determineConnectionCount(i, str, str2, j);
    }

    public FileDownloadDatabase getDatabaseInstance() {
        if (this.database != null) {
            return this.database;
        }
        synchronized (this) {
            if (this.database == null) {
                this.database = getDownloadMgrInitialParams().createDatabase();
                maintainDatabase(this.database.maintainer());
            }
        }
        return this.database;
    }

    public ForegroundServiceConfig getForegroundConfigInstance() {
        if (this.foregroundServiceConfig != null) {
            return this.foregroundServiceConfig;
        }
        synchronized (this) {
            if (this.foregroundServiceConfig == null) {
                this.foregroundServiceConfig = getDownloadMgrInitialParams().createForegroundServiceConfig();
            }
        }
        return this.foregroundServiceConfig;
    }

    public FileDownloadHelper.IdGenerator getIdGeneratorInstance() {
        if (this.idGenerator != null) {
            return this.idGenerator;
        }
        synchronized (this) {
            if (this.idGenerator == null) {
                this.idGenerator = getDownloadMgrInitialParams().createIdGenerator();
            }
        }
        return this.idGenerator;
    }

    public int getMaxNetworkThreadCount() {
        return getDownloadMgrInitialParams().getMaxNetworkThreadCount();
    }

    public boolean isSupportSeek() {
        return getOutputStreamCreator().supportSeek();
    }

    public void setInitCustomMaker(DownloadMgrInitialParams.InitCustomMaker initCustomMaker) {
        synchronized (this) {
            this.initialParams = new DownloadMgrInitialParams(initCustomMaker);
            this.connectionCreator = null;
            this.outputStreamCreator = null;
            this.database = null;
            this.idGenerator = null;
        }
    }
}
