package net.gotev.uploadservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.gotev.uploadservice.http.HttpStack;
import net.gotev.uploadservice.http.impl.HurlStack;

/* loaded from: classes2.dex */
public final class UploadService extends Service {
    private static final String ACTION_UPLOAD_SUFFIX = ".uploadservice.action.upload";
    public static int BACKOFF_MULTIPLIER = 10;
    private static final String BROADCAST_ACTION_SUFFIX = ".uploadservice.broadcast.status";
    public static int BUFFER_SIZE = 4096;
    public static boolean EXECUTE_IN_FOREGROUND = true;
    public static int INITIAL_RETRY_WAIT_TIME = 1000;
    public static int KEEP_ALIVE_TIME_IN_SECONDS = 1;
    public static int MAX_RETRY_WAIT_TIME = 600000;
    public static String NAMESPACE = "net.gotev";
    protected static final String PARAM_BROADCAST_DATA = "broadcastData";
    protected static final String PARAM_TASK_CLASS = "taskClass";
    protected static final String PARAM_TASK_PARAMETERS = "taskParameters";
    protected static final long PROGRESS_REPORT_INTERVAL = 166;
    private static final String TAG = "UploadService";
    protected static final int UPLOAD_NOTIFICATION_BASE_ID = 1234;
    private static volatile String foregroundUploadId;
    private int notificationIncrementalId = 0;
    private final BlockingQueue<Runnable> uploadTasksQueue = new LinkedBlockingQueue();
    private ThreadPoolExecutor uploadThreadPool;
    private PowerManager.WakeLock wakeLock;
    public static int UPLOAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    public static HttpStack HTTP_STACK = new HurlStack();
    private static final Map<String, UploadTask> uploadTasksMap = new ConcurrentHashMap();
    private static final Map<String, UploadStatusDelegate> uploadDelegates = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getActionBroadcast() {
        return NAMESPACE + BROADCAST_ACTION_SUFFIX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getActionUpload() {
        return NAMESPACE + ACTION_UPLOAD_SUFFIX;
    }

    public static synchronized List<String> getTaskList() {
        ArrayList arrayList;
        synchronized (UploadService.class) {
            if (uploadTasksMap.isEmpty()) {
                arrayList = new ArrayList(1);
            } else {
                arrayList = new ArrayList(uploadTasksMap.size());
                arrayList.addAll(uploadTasksMap.keySet());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static UploadStatusDelegate getUploadStatusDelegate(String str) {
        return uploadDelegates.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setUploadStatusDelegate(String str, UploadStatusDelegate uploadStatusDelegate) {
        if (uploadStatusDelegate == null) {
            return;
        }
        uploadDelegates.put(str, uploadStatusDelegate);
    }

    private int shutdownIfThereArentAnyActiveTasks() {
        if (!uploadTasksMap.isEmpty()) {
            return 1;
        }
        stopSelf();
        return 2;
    }

    public static synchronized void stopAllUploads() {
        synchronized (UploadService.class) {
            if (uploadTasksMap.isEmpty()) {
                return;
            }
            Iterator<String> it = uploadTasksMap.keySet().iterator();
            while (it.hasNext()) {
                uploadTasksMap.get(it.next()).cancel();
            }
        }
    }

    public static synchronized void stopUpload(String str) {
        synchronized (UploadService.class) {
            UploadTask uploadTask = uploadTasksMap.get(str);
            if (uploadTask != null) {
                uploadTask.cancel();
            }
        }
    }

    UploadTask getTask(Intent intent) {
        String stringExtra = intent.getStringExtra(PARAM_TASK_CLASS);
        UploadTask uploadTask = null;
        if (stringExtra == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName(stringExtra);
            if (UploadTask.class.isAssignableFrom(cls)) {
                UploadTask uploadTask2 = (UploadTask) UploadTask.class.cast(cls.newInstance());
                try {
                    uploadTask2.init(this, intent);
                    uploadTask = uploadTask2;
                } catch (Exception e) {
                    e = e;
                    uploadTask = uploadTask2;
                    Logger.error(TAG, "Error while instantiating new task", e);
                    return uploadTask;
                }
            } else {
                Logger.error(TAG, stringExtra + " does not extend UploadTask!");
            }
            Logger.debug(TAG, "Successfully created new task with class: " + stringExtra);
        } catch (Exception e2) {
            e = e2;
        }
        return uploadTask;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized boolean holdForegroundNotification(String str, Notification notification) {
        if (!EXECUTE_IN_FOREGROUND) {
            return false;
        }
        if (foregroundUploadId == null) {
            foregroundUploadId = str;
            Logger.debug(TAG, str + " now holds the foreground notification");
        }
        if (!str.equals(foregroundUploadId)) {
            return false;
        }
        startForeground(UPLOAD_NOTIFICATION_BASE_ID, notification);
        return true;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, TAG);
        if (UPLOAD_POOL_SIZE <= 0) {
            UPLOAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
        }
        this.uploadThreadPool = new ThreadPoolExecutor(UPLOAD_POOL_SIZE, UPLOAD_POOL_SIZE, KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, this.uploadTasksQueue);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopAllUploads();
        this.uploadThreadPool.shutdown();
        if (EXECUTE_IN_FOREGROUND) {
            Logger.debug(TAG, "Stopping foreground execution");
            stopForeground(true);
        }
        if (this.wakeLock != null && this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
        uploadTasksMap.clear();
        uploadDelegates.clear();
        Logger.debug(TAG, "UploadService destroyed");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || !getActionUpload().equals(intent.getAction())) {
            return shutdownIfThereArentAnyActiveTasks();
        }
        String str = TAG;
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[4];
        objArr[0] = NAMESPACE;
        objArr[1] = Integer.valueOf(UPLOAD_POOL_SIZE);
        objArr[2] = Integer.valueOf(KEEP_ALIVE_TIME_IN_SECONDS);
        objArr[3] = EXECUTE_IN_FOREGROUND ? "enabled" : "disabled";
        Logger.info(str, String.format(locale, "Starting service with namespace: %s, upload pool size: %d, %ds idle thread keep alive time. Foreground execution is %s", objArr));
        UploadTask task = getTask(intent);
        if (task == null) {
            return shutdownIfThereArentAnyActiveTasks();
        }
        this.notificationIncrementalId += 2;
        task.setLastProgressNotificationTime(0L).setNotificationId(UPLOAD_NOTIFICATION_BASE_ID + this.notificationIncrementalId);
        if (this.wakeLock != null && !this.wakeLock.isHeld()) {
            this.wakeLock.acquire();
        }
        uploadTasksMap.put(task.params.getId(), task);
        this.uploadThreadPool.execute(task);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void taskCompleted(String str) {
        UploadTask remove = uploadTasksMap.remove(str);
        uploadDelegates.remove(str);
        if (EXECUTE_IN_FOREGROUND && remove != null && remove.params.getId().equals(foregroundUploadId)) {
            Logger.debug(TAG, str + " now un-holded the foreground notification");
            foregroundUploadId = null;
        }
        if (uploadTasksMap.isEmpty()) {
            Logger.debug(TAG, "All tasks finished. UploadService is about to shutdown...");
            this.wakeLock.release();
            stopSelf();
        }
    }
}
