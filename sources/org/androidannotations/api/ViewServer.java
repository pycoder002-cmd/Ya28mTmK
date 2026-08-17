package org.androidannotations.api;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes2.dex */
public class ViewServer implements Runnable {
    private static final String BUILD_TYPE_USER = "user";
    private static final String COMMAND_PROTOCOL_VERSION = "PROTOCOL";
    private static final String COMMAND_SERVER_VERSION = "SERVER";
    private static final String COMMAND_WINDOW_MANAGER_AUTOLIST = "AUTOLIST";
    private static final String COMMAND_WINDOW_MANAGER_GET_FOCUS = "GET_FOCUS";
    private static final String COMMAND_WINDOW_MANAGER_LIST = "LIST";
    private static final String LOG_TAG = "ViewServer";
    private static final String VALUE_PROTOCOL_VERSION = "4";
    private static final String VALUE_SERVER_VERSION = "4";
    private static final int VIEW_SERVER_DEFAULT_PORT = 4939;
    private static final int VIEW_SERVER_MAX_CONNECTIONS = 10;
    private static ViewServer sServer;
    private final ReentrantReadWriteLock mFocusLock;
    private View mFocusedWindow;
    private final List<WindowListener> mListeners;
    private final int mPort;
    private ServerSocket mServer;
    private Thread mThread;
    private ExecutorService mThreadPool;
    private final HashMap<View, String> mWindows;
    private final ReentrantReadWriteLock mWindowsLock;

    /* loaded from: classes2.dex */
    private static class NoopViewServer extends ViewServer {
        private NoopViewServer() {
            super();
        }

        @Override // org.androidannotations.api.ViewServer
        public void addWindow(Activity activity) {
        }

        @Override // org.androidannotations.api.ViewServer
        public void addWindow(View view, String str) {
        }

        @Override // org.androidannotations.api.ViewServer
        public boolean isRunning() {
            return false;
        }

        @Override // org.androidannotations.api.ViewServer
        public void removeWindow(Activity activity) {
        }

        @Override // org.androidannotations.api.ViewServer
        public void removeWindow(View view) {
        }

        @Override // org.androidannotations.api.ViewServer, java.lang.Runnable
        public void run() {
        }

        @Override // org.androidannotations.api.ViewServer
        public void setFocusedWindow(Activity activity) {
        }

        @Override // org.androidannotations.api.ViewServer
        public void setFocusedWindow(View view) {
        }

        @Override // org.androidannotations.api.ViewServer
        public boolean start() throws IOException {
            return false;
        }

        @Override // org.androidannotations.api.ViewServer
        public boolean stop() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class UncloseableOuputStream extends OutputStream {
        private final OutputStream mStream;

        UncloseableOuputStream(OutputStream outputStream) {
            this.mStream = outputStream;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public boolean equals(Object obj) {
            return this.mStream.equals(obj);
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            this.mStream.flush();
        }

        public int hashCode() {
            return this.mStream.hashCode();
        }

        public String toString() {
            return this.mStream.toString();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.mStream.write(i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.mStream.write(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.mStream.write(bArr, i, i2);
        }
    }

    /* loaded from: classes2.dex */
    private class ViewServerWorker implements Runnable, WindowListener {
        private Socket mClient;
        private final Object[] mLock = new Object[0];
        private boolean mNeedWindowListUpdate = false;
        private boolean mNeedFocusedWindowUpdate = false;

        public ViewServerWorker(Socket socket) {
            this.mClient = socket;
        }

        private View findWindow(int i) {
            if (i == -1) {
                ViewServer.this.mWindowsLock.readLock().lock();
                try {
                    return ViewServer.this.mFocusedWindow;
                } finally {
                }
            }
            ViewServer.this.mWindowsLock.readLock().lock();
            try {
                for (Map.Entry entry : ViewServer.this.mWindows.entrySet()) {
                    if (System.identityHashCode(entry.getKey()) == i) {
                        return (View) entry.getKey();
                    }
                }
                ViewServer.this.mWindowsLock.readLock().unlock();
                return null;
            } finally {
            }
        }

        private boolean getFocusedWindow(Socket socket) {
            BufferedWriter bufferedWriter;
            try {
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
                    try {
                        ViewServer.this.mFocusLock.readLock().lock();
                        try {
                            View view = ViewServer.this.mFocusedWindow;
                            if (view != null) {
                                ViewServer.this.mWindowsLock.readLock().lock();
                                try {
                                    String str = (String) ViewServer.this.mWindows.get(ViewServer.this.mFocusedWindow);
                                    ViewServer.this.mWindowsLock.readLock().unlock();
                                    bufferedWriter.write(Integer.toHexString(System.identityHashCode(view)));
                                    bufferedWriter.write(32);
                                    bufferedWriter.append((CharSequence) str);
                                } catch (Throwable th) {
                                    ViewServer.this.mWindowsLock.readLock().unlock();
                                    throw th;
                                }
                            }
                            bufferedWriter.write(10);
                            bufferedWriter.flush();
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                            return true;
                        } finally {
                            ViewServer.this.mFocusLock.readLock().unlock();
                        }
                    } catch (Exception unused) {
                        if (bufferedWriter == null) {
                            return false;
                        }
                        bufferedWriter.close();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                    return false;
                }
            } catch (Exception unused4) {
                bufferedWriter = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
            }
        }

        private boolean listWindows(Socket socket) {
            BufferedWriter bufferedWriter;
            try {
                try {
                    ViewServer.this.mWindowsLock.readLock().lock();
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
                    try {
                        for (Map.Entry entry : ViewServer.this.mWindows.entrySet()) {
                            bufferedWriter.write(Integer.toHexString(System.identityHashCode(entry.getKey())));
                            bufferedWriter.write(32);
                            bufferedWriter.append((CharSequence) entry.getValue());
                            bufferedWriter.write(10);
                        }
                        bufferedWriter.write("DONE.\n");
                        bufferedWriter.flush();
                        ViewServer.this.mWindowsLock.readLock().unlock();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        return true;
                    } catch (Exception unused) {
                        ViewServer.this.mWindowsLock.readLock().unlock();
                        if (bufferedWriter == null) {
                            return false;
                        }
                        bufferedWriter.close();
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        ViewServer.this.mWindowsLock.readLock().unlock();
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException unused3) {
                    return false;
                }
            } catch (Exception unused4) {
                bufferedWriter = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter = null;
            }
        }

        private boolean windowCommand(Socket socket, String str, String str2) {
            BufferedWriter bufferedWriter;
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                    try {
                        int indexOf = str2.indexOf(32);
                        if (indexOf == -1) {
                            indexOf = str2.length();
                        }
                        int parseLong = (int) Long.parseLong(str2.substring(0, indexOf), 16);
                        str2 = indexOf < str2.length() ? str2.substring(indexOf + 1) : "";
                        View findWindow = findWindow(parseLong);
                        if (findWindow == null) {
                            return false;
                        }
                        Method declaredMethod = ViewDebug.class.getDeclaredMethod("dispatchCommand", View.class, String.class, String.class, OutputStream.class);
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(null, findWindow, str, str2, new UncloseableOuputStream(socket.getOutputStream()));
                        if (socket.isOutputShutdown()) {
                            bufferedWriter = null;
                        } else {
                            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            try {
                                bufferedWriter.write("DONE\n");
                                bufferedWriter.flush();
                            } catch (Exception e) {
                                e = e;
                                bufferedWriter2 = bufferedWriter;
                                Log.w(ViewServer.LOG_TAG, "Could not send command " + str + " with parameters " + str2, e);
                                if (bufferedWriter2 == null) {
                                    return false;
                                }
                                bufferedWriter2.close();
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                bufferedWriter2 = bufferedWriter;
                                if (bufferedWriter2 != null) {
                                    try {
                                        bufferedWriter2.close();
                                    } catch (IOException unused) {
                                    }
                                }
                                throw th;
                            }
                        }
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        return true;
                    } catch (IOException unused2) {
                        return false;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x0058, code lost:
        
            if (r2 != null) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x006d, code lost:
        
            r2.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x006b, code lost:
        
            if (r2 == null) goto L40;
         */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean windowManagerAutolistLoop() {
            /*
                r7 = this;
                org.androidannotations.api.ViewServer r0 = org.androidannotations.api.ViewServer.this
                org.androidannotations.api.ViewServer.access$700(r0, r7)
                r0 = 1
                r1 = 0
                java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
                java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
                java.net.Socket r4 = r7.mClient     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
                java.io.OutputStream r4 = r4.getOutputStream()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L60
            L17:
                boolean r1 = java.lang.Thread.interrupted()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
                if (r1 != 0) goto L58
                java.lang.Object[] r1 = r7.mLock     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
                monitor-enter(r1)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
            L20:
                boolean r3 = r7.mNeedWindowListUpdate     // Catch: java.lang.Throwable -> L55
                if (r3 != 0) goto L2e
                boolean r3 = r7.mNeedFocusedWindowUpdate     // Catch: java.lang.Throwable -> L55
                if (r3 != 0) goto L2e
                java.lang.Object[] r3 = r7.mLock     // Catch: java.lang.Throwable -> L55
                r3.wait()     // Catch: java.lang.Throwable -> L55
                goto L20
            L2e:
                boolean r3 = r7.mNeedWindowListUpdate     // Catch: java.lang.Throwable -> L55
                r4 = 0
                if (r3 == 0) goto L37
                r7.mNeedWindowListUpdate = r4     // Catch: java.lang.Throwable -> L55
                r3 = r0
                goto L38
            L37:
                r3 = r4
            L38:
                boolean r5 = r7.mNeedFocusedWindowUpdate     // Catch: java.lang.Throwable -> L55
                if (r5 == 0) goto L3f
                r7.mNeedFocusedWindowUpdate = r4     // Catch: java.lang.Throwable -> L55
                r4 = r0
            L3f:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L55
                if (r3 == 0) goto L4a
                java.lang.String r1 = "LIST UPDATE\n"
                r2.write(r1)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
                r2.flush()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
            L4a:
                if (r4 == 0) goto L17
                java.lang.String r1 = "FOCUS UPDATE\n"
                r2.write(r1)     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
                r2.flush()     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
                goto L17
            L55:
                r3 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L55
                throw r3     // Catch: java.lang.Exception -> L5b java.lang.Throwable -> L76
            L58:
                if (r2 == 0) goto L70
                goto L6d
            L5b:
                r1 = move-exception
                goto L64
            L5d:
                r0 = move-exception
                r2 = r1
                goto L77
            L60:
                r2 = move-exception
                r6 = r2
                r2 = r1
                r1 = r6
            L64:
                java.lang.String r3 = "ViewServer"
                java.lang.String r4 = "Connection error: "
                android.util.Log.w(r3, r4, r1)     // Catch: java.lang.Throwable -> L76
                if (r2 == 0) goto L70
            L6d:
                r2.close()     // Catch: java.io.IOException -> L70
            L70:
                org.androidannotations.api.ViewServer r1 = org.androidannotations.api.ViewServer.this
                org.androidannotations.api.ViewServer.access$800(r1, r7)
                return r0
            L76:
                r0 = move-exception
            L77:
                if (r2 == 0) goto L7c
                r2.close()     // Catch: java.io.IOException -> L7c
            L7c:
                org.androidannotations.api.ViewServer r1 = org.androidannotations.api.ViewServer.this
                org.androidannotations.api.ViewServer.access$800(r1, r7)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.androidannotations.api.ViewServer.ViewServerWorker.windowManagerAutolistLoop():boolean");
        }

        @Override // org.androidannotations.api.ViewServer.WindowListener
        public void focusChanged() {
            synchronized (this.mLock) {
                this.mNeedFocusedWindowUpdate = true;
                this.mLock.notifyAll();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            BufferedReader bufferedReader;
            IOException e;
            String substring;
            BufferedReader bufferedReader2 = null;
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(this.mClient.getInputStream()), 1024);
                    try {
                        String readLine = bufferedReader.readLine();
                        int indexOf = readLine.indexOf(32);
                        if (indexOf == -1) {
                            substring = "";
                        } else {
                            String substring2 = readLine.substring(0, indexOf);
                            substring = readLine.substring(indexOf + 1);
                            readLine = substring2;
                        }
                        if (!(ViewServer.COMMAND_PROTOCOL_VERSION.equalsIgnoreCase(readLine) ? ViewServer.writeValue(this.mClient, "4") : ViewServer.COMMAND_SERVER_VERSION.equalsIgnoreCase(readLine) ? ViewServer.writeValue(this.mClient, "4") : ViewServer.COMMAND_WINDOW_MANAGER_LIST.equalsIgnoreCase(readLine) ? listWindows(this.mClient) : ViewServer.COMMAND_WINDOW_MANAGER_GET_FOCUS.equalsIgnoreCase(readLine) ? getFocusedWindow(this.mClient) : ViewServer.COMMAND_WINDOW_MANAGER_AUTOLIST.equalsIgnoreCase(readLine) ? windowManagerAutolistLoop() : windowCommand(this.mClient, readLine, substring))) {
                            Log.w(ViewServer.LOG_TAG, "An error occurred with the command: " + readLine);
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        Log.w(ViewServer.LOG_TAG, "Connection error: ", e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (this.mClient != null) {
                            this.mClient.close();
                        }
                        return;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return;
                }
            } catch (IOException e6) {
                bufferedReader = null;
                e = e6;
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                if (this.mClient == null) {
                    throw th;
                }
                try {
                    this.mClient.close();
                    throw th;
                } catch (IOException e8) {
                    e8.printStackTrace();
                    throw th;
                }
            }
            if (this.mClient != null) {
                this.mClient.close();
            }
        }

        @Override // org.androidannotations.api.ViewServer.WindowListener
        public void windowsChanged() {
            synchronized (this.mLock) {
                this.mNeedWindowListUpdate = true;
                this.mLock.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface WindowListener {
        void focusChanged();

        void windowsChanged();
    }

    private ViewServer() {
        this.mListeners = new CopyOnWriteArrayList();
        this.mWindows = new HashMap<>();
        this.mWindowsLock = new ReentrantReadWriteLock();
        this.mFocusLock = new ReentrantReadWriteLock();
        this.mPort = -1;
    }

    private ViewServer(int i) {
        this.mListeners = new CopyOnWriteArrayList();
        this.mWindows = new HashMap<>();
        this.mWindowsLock = new ReentrantReadWriteLock();
        this.mFocusLock = new ReentrantReadWriteLock();
        this.mPort = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWindowListener(WindowListener windowListener) {
        if (this.mListeners.contains(windowListener)) {
            return;
        }
        this.mListeners.add(windowListener);
    }

    private void fireFocusChangedEvent() {
        Iterator<WindowListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().focusChanged();
        }
    }

    private void fireWindowsChangedEvent() {
        Iterator<WindowListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().windowsChanged();
        }
    }

    public static ViewServer get(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (!BUILD_TYPE_USER.equals(Build.TYPE) || (applicationInfo.flags & 2) == 0) {
            sServer = new NoopViewServer();
        } else {
            if (sServer == null) {
                sServer = new ViewServer(VIEW_SERVER_DEFAULT_PORT);
            }
            if (!sServer.isRunning()) {
                try {
                    sServer.start();
                } catch (IOException e) {
                    Log.d(LOG_TAG, "Error:", e);
                }
            }
        }
        return sServer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeWindowListener(WindowListener windowListener) {
        this.mListeners.remove(windowListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean writeValue(Socket socket, String str) {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()), 8192);
                try {
                    bufferedWriter2.write(str);
                    bufferedWriter2.write("\n");
                    bufferedWriter2.flush();
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    return true;
                } catch (Exception unused) {
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter == null) {
                        return false;
                    }
                    bufferedWriter.close();
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused3) {
                return false;
            }
        } catch (Exception unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void addWindow(Activity activity) {
        String str;
        String charSequence = activity.getTitle().toString();
        if (TextUtils.isEmpty(charSequence)) {
            str = activity.getClass().getCanonicalName() + "/0x" + System.identityHashCode(activity);
        } else {
            str = charSequence + "(" + activity.getClass().getCanonicalName() + ")";
        }
        addWindow(activity.getWindow().getDecorView(), str);
    }

    public void addWindow(View view, String str) {
        this.mWindowsLock.writeLock().lock();
        try {
            this.mWindows.put(view.getRootView(), str);
            this.mWindowsLock.writeLock().unlock();
            fireWindowsChangedEvent();
        } catch (Throwable th) {
            this.mWindowsLock.writeLock().unlock();
            throw th;
        }
    }

    public boolean isRunning() {
        return this.mThread != null && this.mThread.isAlive();
    }

    public void removeWindow(Activity activity) {
        removeWindow(activity.getWindow().getDecorView());
    }

    public void removeWindow(View view) {
        this.mWindowsLock.writeLock().lock();
        try {
            this.mWindows.remove(view.getRootView());
            this.mWindowsLock.writeLock().unlock();
            fireWindowsChangedEvent();
        } catch (Throwable th) {
            this.mWindowsLock.writeLock().unlock();
            throw th;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mServer = new ServerSocket(this.mPort, 10, InetAddress.getLocalHost());
        } catch (Exception e) {
            Log.w(LOG_TAG, "Starting ServerSocket error: ", e);
        }
        while (this.mServer != null && Thread.currentThread() == this.mThread) {
            try {
                Socket accept = this.mServer.accept();
                if (this.mThreadPool != null) {
                    this.mThreadPool.submit(new ViewServerWorker(accept));
                } else {
                    try {
                        accept.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                Log.w(LOG_TAG, "Connection error: ", e3);
            }
        }
    }

    public void setFocusedWindow(Activity activity) {
        setFocusedWindow(activity.getWindow().getDecorView());
    }

    public void setFocusedWindow(View view) {
        View rootView;
        this.mFocusLock.writeLock().lock();
        if (view == null) {
            rootView = null;
        } else {
            try {
                rootView = view.getRootView();
            } catch (Throwable th) {
                this.mFocusLock.writeLock().unlock();
                throw th;
            }
        }
        this.mFocusedWindow = rootView;
        this.mFocusLock.writeLock().unlock();
        fireFocusChangedEvent();
    }

    public boolean start() throws IOException {
        if (this.mThread != null) {
            return false;
        }
        this.mThread = new Thread(this, "Local View Server [port=" + this.mPort + "]");
        this.mThreadPool = Executors.newFixedThreadPool(10);
        this.mThread.start();
        return true;
    }

    public boolean stop() {
        if (this.mThread != null) {
            this.mThread.interrupt();
            if (this.mThreadPool != null) {
                try {
                    this.mThreadPool.shutdownNow();
                } catch (SecurityException unused) {
                    Log.w(LOG_TAG, "Could not stop all view server threads");
                }
            }
            this.mThreadPool = null;
            this.mThread = null;
            try {
                this.mServer.close();
                this.mServer = null;
                return true;
            } catch (IOException unused2) {
                Log.w(LOG_TAG, "Could not close the view server");
            }
        }
        this.mWindowsLock.writeLock().lock();
        try {
            this.mWindows.clear();
            this.mWindowsLock.writeLock().unlock();
            this.mFocusLock.writeLock().lock();
            try {
                this.mFocusedWindow = null;
                this.mFocusLock.writeLock().unlock();
                return false;
            } catch (Throwable th) {
                this.mFocusLock.writeLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            this.mWindowsLock.writeLock().unlock();
            throw th2;
        }
    }
}
