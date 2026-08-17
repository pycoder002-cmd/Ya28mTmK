package com.blankj.utilcode.util;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class LogUtils {
    public static final int A = 32;
    private static final String ARGS = "args";
    private static final String BOTTOM_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final int D = 2;
    public static final int E = 16;
    private static final int FILE = 241;
    public static final int I = 4;
    private static final int JSON = 242;
    private static final String LEFT_BORDER = "║ ";
    private static final int MAX_LEN = 4000;
    private static final String NULL = "null";
    private static final String NULL_TIPS = "Log with null object.";
    private static final String TOP_BORDER = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final int V = 1;
    public static final int W = 8;
    private static final int XML = 244;
    private static String defaultDir = null;
    private static String dir = null;
    private static ExecutorService executor = null;
    private static String sGlobalTag = null;
    private static boolean sLog2FileSwitch = false;
    private static boolean sLogBorderSwitch = true;
    private static int sLogFilter = 1;
    private static boolean sLogHeadSwitch = true;
    private static boolean sLogSwitch = true;
    private static boolean sTagIsSpace = true;
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault());

    /* loaded from: classes.dex */
    public static class Builder {
        public Builder() {
            if (LogUtils.defaultDir != null) {
                return;
            }
            if (!"mounted".equals(Environment.getExternalStorageState()) || Utils.getContext().getExternalCacheDir() == null) {
                String unused = LogUtils.defaultDir = Utils.getContext().getCacheDir() + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
                return;
            }
            String unused2 = LogUtils.defaultDir = Utils.getContext().getExternalCacheDir() + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
        }

        public Builder setBorderSwitch(boolean z) {
            boolean unused = LogUtils.sLogBorderSwitch = z;
            return this;
        }

        public Builder setDir(File file) {
            String str;
            if (file == null) {
                str = null;
            } else {
                str = file.getAbsolutePath() + LogUtils.FILE_SEP;
            }
            String unused = LogUtils.dir = str;
            return this;
        }

        public Builder setDir(String str) {
            if (LogUtils.isSpace(str)) {
                String unused = LogUtils.dir = null;
            } else {
                if (!str.endsWith(LogUtils.FILE_SEP)) {
                    str = str + LogUtils.FILE_SEP;
                }
                String unused2 = LogUtils.dir = str;
            }
            return this;
        }

        public Builder setGlobalTag(String str) {
            if (LogUtils.isSpace(str)) {
                String unused = LogUtils.sGlobalTag = "";
                boolean unused2 = LogUtils.sTagIsSpace = true;
            } else {
                String unused3 = LogUtils.sGlobalTag = str;
                boolean unused4 = LogUtils.sTagIsSpace = false;
            }
            return this;
        }

        public Builder setLog2FileSwitch(boolean z) {
            boolean unused = LogUtils.sLog2FileSwitch = z;
            return this;
        }

        public Builder setLogFilter(int i) {
            int unused = LogUtils.sLogFilter = i;
            return this;
        }

        public Builder setLogHeadSwitch(boolean z) {
            boolean unused = LogUtils.sLogHeadSwitch = z;
            return this;
        }

        public Builder setLogSwitch(boolean z) {
            boolean unused = LogUtils.sLogSwitch = z;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("switch: ");
            sb.append(LogUtils.sLogSwitch);
            sb.append(LogUtils.LINE_SEP);
            sb.append("tag: ");
            sb.append(LogUtils.sGlobalTag.equals("") ? LogUtils.NULL : LogUtils.sGlobalTag);
            sb.append(LogUtils.LINE_SEP);
            sb.append("head: ");
            sb.append(LogUtils.sLogHeadSwitch);
            sb.append(LogUtils.LINE_SEP);
            sb.append("file: ");
            sb.append(LogUtils.sLog2FileSwitch);
            sb.append(LogUtils.LINE_SEP);
            sb.append("dir: ");
            sb.append(LogUtils.dir == null ? LogUtils.defaultDir : LogUtils.dir);
            sb.append(LogUtils.LINE_SEP);
            sb.append("border: ");
            sb.append(LogUtils.sLogBorderSwitch);
            sb.append(LogUtils.LINE_SEP);
            sb.append("filter: ");
            sb.append(LogUtils.sLogFilter == 1 ? "verbose" : "not verbose");
            return sb.toString();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    private @interface TYPE {
    }

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(Object obj) {
        log(32, sGlobalTag, obj);
    }

    public static void a(String str, Object... objArr) {
        log(32, str, objArr);
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void d(Object obj) {
        log(2, sGlobalTag, obj);
    }

    public static void d(String str, Object... objArr) {
        log(2, str, objArr);
    }

    public static void e(Object obj) {
        log(16, sGlobalTag, obj);
    }

    public static void e(String str, Object... objArr) {
        log(16, str, objArr);
    }

    public static void file(Object obj) {
        log(FILE, sGlobalTag, obj);
    }

    public static void file(String str, Object obj) {
        log(FILE, str, obj);
    }

    private static String formatJson(String str) {
        String jSONArray;
        try {
            if (str.startsWith("{")) {
                jSONArray = new JSONObject(str).toString(4);
            } else {
                if (!str.startsWith("[")) {
                    return str;
                }
                jSONArray = new JSONArray(str).toString(4);
            }
            return jSONArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    private static String formatXml(String str) {
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            newTransformer.transform(streamSource, streamResult);
            return streamResult.getWriter().toString().replaceFirst(">", ">" + LINE_SEP);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static void i(Object obj) {
        log(4, sGlobalTag, obj);
    }

    public static void i(String str, Object... objArr) {
        log(4, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void json(String str) {
        log(JSON, sGlobalTag, str);
    }

    public static void json(String str, String str2) {
        log(JSON, str, str2);
    }

    private static void log(int i, String str, Object... objArr) {
        if (sLogSwitch) {
            String[] processContents = processContents(i, str, objArr);
            String str2 = processContents[0];
            String str3 = processContents[1];
            switch (i) {
                case 1:
                case 2:
                case 4:
                case 8:
                case 16:
                case 32:
                    if (i >= sLogFilter) {
                        printLog(i, str2, str3);
                        if (sLog2FileSwitch) {
                            print2File(str2, str3);
                            return;
                        }
                        return;
                    }
                    return;
                case FILE /* 241 */:
                    print2File(str2, str3);
                    return;
                case JSON /* 242 */:
                    printLog(2, str2, str3);
                    return;
                case XML /* 244 */:
                    printLog(2, str2, str3);
                    return;
                default:
                    return;
            }
        }
    }

    private static void print(int i, String str, String str2) {
        if (i == 4) {
            Log.i(str, str2);
            return;
        }
        if (i == 8) {
            Log.w(str, str2);
            return;
        }
        if (i == 16) {
            Log.e(str, str2);
            return;
        }
        if (i == 32) {
            Log.wtf(str, str2);
            return;
        }
        switch (i) {
            case 1:
                Log.v(str, str2);
                return;
            case 2:
                Log.d(str, str2);
                return;
            default:
                return;
        }
    }

    private static void print2File(final String str, String str2) {
        String format = FORMAT.format(new Date(System.currentTimeMillis()));
        String substring = format.substring(0, 5);
        String substring2 = format.substring(6);
        StringBuilder sb = new StringBuilder();
        sb.append(dir == null ? defaultDir : dir);
        sb.append(substring);
        sb.append(".txt");
        final String sb2 = sb.toString();
        if (!createOrExistsFile(sb2)) {
            Log.e(str, "log to " + sb2 + " failed!");
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        if (sLogBorderSwitch) {
            sb3.append(TOP_BORDER);
            sb3.append(LINE_SEP);
            sb3.append(LEFT_BORDER);
            sb3.append(substring2);
            sb3.append(str);
            sb3.append(LINE_SEP);
            sb3.append(str2);
            sb3.append(BOTTOM_BORDER);
            sb3.append(LINE_SEP);
        } else {
            sb3.append(substring2);
            sb3.append(str);
            sb3.append(LINE_SEP);
            sb3.append(str2);
            sb3.append(LINE_SEP);
        }
        sb3.append(LINE_SEP);
        final String sb4 = sb3.toString();
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(new Runnable() { // from class: com.blankj.utilcode.util.LogUtils.1
            /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r6 = this;
                    r0 = 0
                    java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
                    java.io.FileWriter r2 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
                    java.lang.String r3 = r1     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
                    r4 = 1
                    r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L38 java.io.IOException -> L3d
                    java.lang.String r0 = r2     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    r1.write(r0)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.String r0 = r3     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    r2.<init>()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.String r3 = "log to "
                    r2.append(r3)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.String r3 = r1     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    r2.append(r3)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.String r3 = " success!"
                    r2.append(r3)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    java.lang.String r2 = r2.toString()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    android.util.Log.d(r0, r2)     // Catch: java.io.IOException -> L36 java.lang.Throwable -> L6c
                    if (r1 == 0) goto L6b
                    r1.close()     // Catch: java.io.IOException -> L67
                    goto L6b
                L36:
                    r0 = move-exception
                    goto L41
                L38:
                    r1 = move-exception
                    r5 = r1
                    r1 = r0
                    r0 = r5
                    goto L6d
                L3d:
                    r1 = move-exception
                    r5 = r1
                    r1 = r0
                    r0 = r5
                L41:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L6c
                    java.lang.String r0 = r3     // Catch: java.lang.Throwable -> L6c
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
                    r2.<init>()     // Catch: java.lang.Throwable -> L6c
                    java.lang.String r3 = "log to "
                    r2.append(r3)     // Catch: java.lang.Throwable -> L6c
                    java.lang.String r3 = r1     // Catch: java.lang.Throwable -> L6c
                    r2.append(r3)     // Catch: java.lang.Throwable -> L6c
                    java.lang.String r3 = " failed!"
                    r2.append(r3)     // Catch: java.lang.Throwable -> L6c
                    java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L6c
                    android.util.Log.e(r0, r2)     // Catch: java.lang.Throwable -> L6c
                    if (r1 == 0) goto L6b
                    r1.close()     // Catch: java.io.IOException -> L67
                    goto L6b
                L67:
                    r0 = move-exception
                    r0.printStackTrace()
                L6b:
                    return
                L6c:
                    r0 = move-exception
                L6d:
                    if (r1 == 0) goto L77
                    r1.close()     // Catch: java.io.IOException -> L73
                    goto L77
                L73:
                    r1 = move-exception
                    r1.printStackTrace()
                L77:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.LogUtils.AnonymousClass1.run():void");
            }
        });
    }

    private static void printLog(int i, String str, String str2) {
        if (sLogBorderSwitch) {
            print(i, str, TOP_BORDER);
        }
        int length = str2.length();
        int i2 = length / MAX_LEN;
        if (i2 > 0) {
            int i3 = MAX_LEN;
            print(i, str, str2.substring(0, MAX_LEN));
            int i4 = 1;
            while (i4 < i2) {
                int i5 = i3 + MAX_LEN;
                String substring = str2.substring(i3, i5);
                if (sLogBorderSwitch) {
                    substring = LEFT_BORDER + substring;
                }
                print(i, str, substring);
                i4++;
                i3 = i5;
            }
            String substring2 = str2.substring(i3, length);
            if (sLogBorderSwitch) {
                substring2 = LEFT_BORDER + substring2;
            }
            print(i, str, substring2);
        } else {
            print(i, str, str2);
        }
        if (sLogBorderSwitch) {
            print(i, str, BOTTOM_BORDER);
        }
    }

    private static String[] processContents(int i, String str, Object... objArr) {
        String formatXml;
        String str2 = "";
        if (sTagIsSpace || sLogHeadSwitch) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
            String className = stackTraceElement.getClassName();
            String[] split = className.split("\\.");
            if (split.length > 0) {
                className = split[split.length - 1];
            }
            if (className.contains("$")) {
                className = className.split("\\$")[0];
            }
            if (sTagIsSpace && isSpace(str)) {
                str = className;
            }
            if (sLogHeadSwitch) {
                str2 = new Formatter().format("Thread: %s, %s(%s.java:%d)" + LINE_SEP, Thread.currentThread().getName(), stackTraceElement.getMethodName(), className, Integer.valueOf(stackTraceElement.getLineNumber())).toString();
            }
        } else {
            str = sGlobalTag;
        }
        String str3 = NULL_TIPS;
        if (objArr != null) {
            if (objArr.length == 1) {
                Object obj = objArr[0];
                String obj2 = obj == null ? NULL : obj.toString();
                if (i == JSON) {
                    formatXml = formatJson(obj2);
                } else if (i == XML) {
                    formatXml = formatXml(obj2);
                } else {
                    str3 = obj2;
                }
                str3 = formatXml;
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj3 = objArr[i2];
                    sb.append(ARGS);
                    sb.append("[");
                    sb.append(i2);
                    sb.append("]");
                    sb.append(" = ");
                    sb.append(obj3 == null ? NULL : obj3.toString());
                    sb.append(LINE_SEP);
                }
                str3 = sb.toString();
            }
        }
        String str4 = str2 + str3;
        if (sLogBorderSwitch) {
            StringBuilder sb2 = new StringBuilder();
            for (String str5 : str4.split(LINE_SEP)) {
                sb2.append(LEFT_BORDER);
                sb2.append(str5);
                sb2.append(LINE_SEP);
            }
            str4 = sb2.toString();
        }
        return new String[]{str, str4};
    }

    public static void v(Object obj) {
        log(1, sGlobalTag, obj);
    }

    public static void v(String str, Object... objArr) {
        log(1, str, objArr);
    }

    public static void w(Object obj) {
        log(8, sGlobalTag, obj);
    }

    public static void w(String str, Object... objArr) {
        log(8, str, objArr);
    }

    public static void xml(String str) {
        log(XML, sGlobalTag, str);
    }

    public static void xml(String str, String str2) {
        log(XML, str, str2);
    }
}
