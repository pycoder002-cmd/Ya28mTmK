package org.androidannotations.api;

import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class BackgroundExecutor {
    private static final String TAG = "BackgroundExecutor";
    public static Executor DEFAULT_EXECUTOR = Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors());
    private static Executor executor = DEFAULT_EXECUTOR;
    public static final WrongThreadListener DEFAULT_WRONG_THREAD_LISTENER = new WrongThreadListener() { // from class: org.androidannotations.api.BackgroundExecutor.1
        @Override // org.androidannotations.api.BackgroundExecutor.WrongThreadListener
        public void onBgExpected(String... strArr) {
            if (strArr.length == 0) {
                throw new IllegalStateException("Method invocation is expected from a background thread, but it was called from the UI thread");
            }
            throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(strArr) + ", but it was called from the UI thread");
        }

        @Override // org.androidannotations.api.BackgroundExecutor.WrongThreadListener
        public void onUiExpected() {
            throw new IllegalStateException("Method invocation is expected from the UI thread");
        }

        @Override // org.androidannotations.api.BackgroundExecutor.WrongThreadListener
        public void onWrongBgSerial(String str, String... strArr) {
            if (str == null) {
                str = "anonymous";
            }
            throw new IllegalStateException("Method invocation is expected from one of serials " + Arrays.toString(strArr) + ", but it was called from " + str + " serial");
        }
    };
    private static WrongThreadListener wrongThreadListener = DEFAULT_WRONG_THREAD_LISTENER;
    private static final List<Task> tasks = new ArrayList();
    private static final ThreadLocal<String> currentSerial = new ThreadLocal<>();

    /* loaded from: classes2.dex */
    public static abstract class Task implements Runnable {
        private boolean executionAsked;
        private Future<?> future;
        private String id;
        private AtomicBoolean managed = new AtomicBoolean();
        private int remainingDelay;
        private String serial;
        private long targetTimeMillis;

        public Task(String str, int i, String str2) {
            if (!"".equals(str)) {
                this.id = str;
            }
            if (i > 0) {
                this.remainingDelay = i;
                this.targetTimeMillis = System.currentTimeMillis() + i;
            }
            if ("".equals(str2)) {
                return;
            }
            this.serial = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void postExecute() {
            Task take;
            if (this.id == null && this.serial == null) {
                return;
            }
            BackgroundExecutor.currentSerial.set(null);
            synchronized (BackgroundExecutor.class) {
                BackgroundExecutor.tasks.remove(this);
                if (this.serial != null && (take = BackgroundExecutor.take(this.serial)) != null) {
                    if (take.remainingDelay != 0) {
                        take.remainingDelay = Math.max(0, (int) (this.targetTimeMillis - System.currentTimeMillis()));
                    }
                    BackgroundExecutor.execute(take);
                }
            }
        }

        public abstract void execute();

        @Override // java.lang.Runnable
        public void run() {
            if (this.managed.getAndSet(true)) {
                return;
            }
            try {
                BackgroundExecutor.currentSerial.set(this.serial);
                execute();
            } finally {
                postExecute();
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface WrongThreadListener {
        void onBgExpected(String... strArr);

        void onUiExpected();

        void onWrongBgSerial(String str, String... strArr);
    }

    public static synchronized void cancelAll(String str, boolean z) {
        synchronized (BackgroundExecutor.class) {
            for (int size = tasks.size() - 1; size >= 0; size--) {
                Task task = tasks.get(size);
                if (str.equals(task.id)) {
                    if (task.future != null) {
                        task.future.cancel(z);
                        if (!task.managed.getAndSet(true)) {
                            task.postExecute();
                        }
                    } else if (task.executionAsked) {
                        Log.w(TAG, "A task with id " + task.id + " cannot be cancelled (the executor set does not support it)");
                    } else {
                        tasks.remove(size);
                    }
                }
            }
        }
    }

    public static void checkBgThread(String... strArr) {
        if (strArr.length == 0) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                wrongThreadListener.onBgExpected(strArr);
                return;
            }
            return;
        }
        String str = currentSerial.get();
        if (str == null) {
            wrongThreadListener.onWrongBgSerial(null, strArr);
            return;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return;
            }
        }
        wrongThreadListener.onWrongBgSerial(str, strArr);
    }

    public static void checkUiThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            wrongThreadListener.onUiExpected();
        }
    }

    private static Future<?> directExecute(Runnable runnable, int i) {
        if (i > 0) {
            if (executor instanceof ScheduledExecutorService) {
                return ((ScheduledExecutorService) executor).schedule(runnable, i, TimeUnit.MILLISECONDS);
            }
            throw new IllegalArgumentException("The executor set does not support scheduling");
        }
        if (executor instanceof ExecutorService) {
            return ((ExecutorService) executor).submit(runnable);
        }
        executor.execute(runnable);
        return null;
    }

    public static void execute(Runnable runnable) {
        directExecute(runnable, 0);
    }

    public static void execute(Runnable runnable, int i) {
        directExecute(runnable, i);
    }

    public static void execute(final Runnable runnable, String str, int i, String str2) {
        execute(new Task(str, i, str2) { // from class: org.androidannotations.api.BackgroundExecutor.2
            @Override // org.androidannotations.api.BackgroundExecutor.Task
            public void execute() {
                runnable.run();
            }
        });
    }

    public static void execute(Runnable runnable, String str, String str2) {
        execute(runnable, str, 0, str2);
    }

    public static synchronized void execute(Task task) {
        synchronized (BackgroundExecutor.class) {
            Future<?> future = null;
            if (task.serial == null || !hasSerialRunning(task.serial)) {
                task.executionAsked = true;
                future = directExecute(task, task.remainingDelay);
            }
            if (task.id != null || task.serial != null) {
                task.future = future;
                tasks.add(task);
            }
        }
    }

    private static boolean hasSerialRunning(String str) {
        for (Task task : tasks) {
            if (task.executionAsked && str.equals(task.serial)) {
                return true;
            }
        }
        return false;
    }

    public static void setExecutor(Executor executor2) {
        executor = executor2;
    }

    public static void setWrongThreadListener(WrongThreadListener wrongThreadListener2) {
        wrongThreadListener = wrongThreadListener2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Task take(String str) {
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(tasks.get(i).serial)) {
                return tasks.remove(i);
            }
        }
        return null;
    }
}
