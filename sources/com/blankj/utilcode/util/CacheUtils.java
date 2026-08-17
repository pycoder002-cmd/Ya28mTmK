package com.blankj.utilcode.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CacheUtils {
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static final int MAX_SIZE = 50000000;
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, CacheUtils> mInstanceMap = new HashMap();
    private CacheManager mCacheManager;

    /* loaded from: classes.dex */
    public class CacheManager {
        private final AtomicInteger cacheCount;
        protected File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final long sizeLimit;

        private CacheManager(File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() { // from class: com.blankj.utilcode.util.CacheUtils.CacheManager.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles = CacheManager.this.cacheDir.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : listFiles) {
                            i = (int) (i + CacheManager.this.calculateSize(file));
                            i2++;
                            CacheManager.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                        }
                        CacheManager.this.cacheSize.set(i);
                        CacheManager.this.cacheCount.set(i2);
                    }
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long calculateSize(File file) {
            return file.length();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this.lastUsageDates.clear();
            this.cacheSize.set(0L);
            File[] listFiles = this.cacheDir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File get(String str) {
            File newFile = newFile(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            newFile.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(newFile, valueOf);
            return newFile;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File newFile(String str) {
            return new File(this.cacheDir, str.hashCode() + "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void put(File file) {
            int i = this.cacheCount.get();
            while (i + 1 > this.countLimit) {
                this.cacheSize.addAndGet(-removeNext());
                i = this.cacheCount.addAndGet(-1);
            }
            this.cacheCount.addAndGet(1);
            long calculateSize = calculateSize(file);
            long j = this.cacheSize.get();
            while (j + calculateSize > this.sizeLimit) {
                j = this.cacheSize.addAndGet(-removeNext());
            }
            this.cacheSize.addAndGet(calculateSize);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean remove(String str) {
            return get(str).delete();
        }

        private long removeNext() {
            File file;
            if (this.lastUsageDates.isEmpty()) {
                return 0L;
            }
            Set<Map.Entry<File, Long>> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                file = null;
                Long l = null;
                for (Map.Entry<File, Long> entry : entrySet) {
                    if (file == null) {
                        file = entry.getKey();
                        l = entry.getValue();
                    } else {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            file = entry.getKey();
                            l = value;
                        }
                    }
                }
            }
            long calculateSize = calculateSize(file);
            if (file.delete()) {
                this.lastUsageDates.remove(file);
            }
            return calculateSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Utils {
        private static final char mSeparator = ' ';

        private Utils() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] Bitmap2Bytes(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap Bytes2Bimap(byte[] bArr) {
            if (bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Drawable bitmap2Drawable(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            return new BitmapDrawable(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String clearDateInfo(String str) {
            return (str == null || !hasDateInfo(str.getBytes())) ? str : str.substring(str.indexOf(32) + 1, str.length());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] clearDateInfo(byte[] bArr) {
            return hasDateInfo(bArr) ? copyOfRange(bArr, indexOf(bArr, ' ') + 1, bArr.length) : bArr;
        }

        private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        private static String createDateInfo(int i) {
            String str = System.currentTimeMillis() + "";
            while (str.length() < 13) {
                str = "0" + str;
            }
            return str + "-" + i + ' ';
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap drawable2Bitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        private static String[] getDateInfoFromDate(byte[] bArr) {
            if (hasDateInfo(bArr)) {
                return new String[]{new String(copyOfRange(bArr, 0, 13)), new String(copyOfRange(bArr, 14, indexOf(bArr, ' ')))};
            }
            return null;
        }

        private static boolean hasDateInfo(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && indexOf(bArr, ' ') > 14;
        }

        private static int indexOf(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(String str) {
            return isDue(str.getBytes());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(byte[] bArr) {
            String[] dateInfoFromDate = getDateInfoFromDate(bArr);
            if (dateInfoFromDate != null && dateInfoFromDate.length == 2) {
                String str = dateInfoFromDate[0];
                while (str.startsWith("0")) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(dateInfoFromDate[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] newByteArrayWithDateInfo(int i, byte[] bArr) {
            byte[] bytes = createDateInfo(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String newStringWithDateInfo(int i, String str) {
            return createDateInfo(i) + str;
        }
    }

    private CacheUtils(File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.mCacheManager = new CacheManager(file, j, i);
            return;
        }
        throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
    }

    public static CacheUtils get(Context context) {
        return get(context, "CacheUtils");
    }

    public static CacheUtils get(Context context, long j, int i) {
        return get(new File(context.getCacheDir(), "CacheUtils"), j, i);
    }

    public static CacheUtils get(Context context, String str) {
        return get(new File(context.getCacheDir(), str), 50000000L, Integer.MAX_VALUE);
    }

    public static CacheUtils get(File file) {
        return get(file, 50000000L, Integer.MAX_VALUE);
    }

    public static CacheUtils get(File file, long j, int i) {
        CacheUtils cacheUtils = mInstanceMap.get(file.getAbsoluteFile() + myPid());
        if (cacheUtils != null) {
            return cacheUtils;
        }
        CacheUtils cacheUtils2 = new CacheUtils(file, j, i);
        mInstanceMap.put(file.getAbsolutePath() + myPid(), cacheUtils2);
        return cacheUtils2;
    }

    private static String myPid() {
        return "_" + Process.myPid();
    }

    public void clear() {
        this.mCacheManager.clear();
    }

    public File file(String str) {
        File newFile = this.mCacheManager.newFile(str);
        if (newFile.exists()) {
            return newFile;
        }
        return null;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0057: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:39:0x0057 */
    /* JADX WARN: Removed duplicated region for block: B:42:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getAsBinary(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            com.blankj.utilcode.util.CacheUtils$CacheManager r1 = r5.mCacheManager     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            java.io.File r1 = com.blankj.utilcode.util.CacheUtils.CacheManager.access$400(r1, r6)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            if (r2 != 0) goto Le
            return r0
        Le:
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            java.lang.String r3 = "r"
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L46
            long r3 = r2.length()     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            int r1 = (int) r3     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            byte[] r1 = new byte[r1]     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            r2.read(r1)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            boolean r3 = com.blankj.utilcode.util.CacheUtils.Utils.access$800(r1)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            if (r3 != 0) goto L34
            byte[] r6 = com.blankj.utilcode.util.CacheUtils.Utils.access$900(r1)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L56
            if (r2 == 0) goto L33
            r2.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r0 = move-exception
            r0.printStackTrace()
        L33:
            return r6
        L34:
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r1 = move-exception
            r1.printStackTrace()
        L3e:
            r5.remove(r6)
            return r0
        L42:
            r6 = move-exception
            goto L48
        L44:
            r6 = move-exception
            goto L58
        L46:
            r6 = move-exception
            r2 = r0
        L48:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L56
            if (r2 == 0) goto L55
            r2.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r6 = move-exception
            r6.printStackTrace()
        L55:
            return r0
        L56:
            r6 = move-exception
            r0 = r2
        L58:
            if (r0 == 0) goto L62
            r0.close()     // Catch: java.io.IOException -> L5e
            goto L62
        L5e:
            r0 = move-exception
            r0.printStackTrace()
        L62:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.CacheUtils.getAsBinary(java.lang.String):byte[]");
    }

    public Bitmap getAsBitmap(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.Bytes2Bimap(getAsBinary(str));
    }

    public Drawable getAsDrawable(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(str)));
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject getAsJSONObject(String str) {
        try {
            return new JSONObject(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getAsObject(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = r4.getAsBinary(r5)
            r0 = 0
            if (r5 == 0) goto L68
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L34
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L34
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.lang.Object r2 = r5.readObject()     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L4f
            if (r1 == 0) goto L1f
            r1.close()     // Catch: java.io.IOException -> L1b
            goto L1f
        L1b:
            r0 = move-exception
            r0.printStackTrace()
        L1f:
            if (r5 == 0) goto L29
            r5.close()     // Catch: java.io.IOException -> L25
            goto L29
        L25:
            r5 = move-exception
            r5.printStackTrace()
        L29:
            return r2
        L2a:
            r2 = move-exception
            goto L37
        L2c:
            r5 = move-exception
            goto L53
        L2e:
            r2 = move-exception
            r5 = r0
            goto L37
        L31:
            r5 = move-exception
            r1 = r0
            goto L53
        L34:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L37:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L44
            r1.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r1 = move-exception
            r1.printStackTrace()
        L44:
            if (r5 == 0) goto L4e
            r5.close()     // Catch: java.io.IOException -> L4a
            goto L4e
        L4a:
            r5 = move-exception
            r5.printStackTrace()
        L4e:
            return r0
        L4f:
            r0 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
        L53:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.io.IOException -> L59
            goto L5d
        L59:
            r1 = move-exception
            r1.printStackTrace()
        L5d:
            if (r0 == 0) goto L67
            r0.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r0 = move-exception
            r0.printStackTrace()
        L67:
            throw r5
        L68:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.CacheUtils.getAsObject(java.lang.String):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getAsString(java.lang.String r6) {
        /*
            r5 = this;
            com.blankj.utilcode.util.CacheUtils$CacheManager r0 = r5.mCacheManager
            java.io.File r0 = com.blankj.utilcode.util.CacheUtils.CacheManager.access$400(r0, r6)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto Le
            return r2
        Le:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L58
            java.lang.String r0 = ""
        L1a:
            java.lang.String r3 = r1.readLine()     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            if (r3 == 0) goto L30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            r4.<init>()     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            r4.append(r0)     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            r4.append(r3)     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            java.lang.String r0 = r4.toString()     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            goto L1a
        L30:
            boolean r3 = com.blankj.utilcode.util.CacheUtils.Utils.access$500(r0)     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            if (r3 != 0) goto L45
            java.lang.String r6 = com.blankj.utilcode.util.CacheUtils.Utils.access$600(r0)     // Catch: java.io.IOException -> L53 java.lang.Throwable -> L68
            if (r1 == 0) goto L44
            r1.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r0 = move-exception
            r0.printStackTrace()
        L44:
            return r6
        L45:
            if (r1 == 0) goto L4f
            r1.close()     // Catch: java.io.IOException -> L4b
            goto L4f
        L4b:
            r0 = move-exception
            r0.printStackTrace()
        L4f:
            r5.remove(r6)
            return r2
        L53:
            r6 = move-exception
            goto L5a
        L55:
            r6 = move-exception
            r1 = r2
            goto L69
        L58:
            r6 = move-exception
            r1 = r2
        L5a:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L68
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r6 = move-exception
            r6.printStackTrace()
        L67:
            return r2
        L68:
            r6 = move-exception
        L69:
            if (r1 == 0) goto L73
            r1.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r0 = move-exception
            r0.printStackTrace()
        L73:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.CacheUtils.getAsString(java.lang.String):java.lang.String");
    }

    public void put(String str, Bitmap bitmap) {
        put(str, Utils.Bitmap2Bytes(bitmap));
    }

    public void put(String str, Bitmap bitmap, int i) {
        put(str, Utils.Bitmap2Bytes(bitmap), i);
    }

    public void put(String str, Drawable drawable) {
        put(str, Utils.drawable2Bitmap(drawable));
    }

    public void put(String str, Drawable drawable, int i) {
        put(str, Utils.drawable2Bitmap(drawable), i);
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    public void put(String str, Serializable serializable, int i) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                objectOutputStream.writeObject(serializable);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    put(str, byteArray, i);
                } else {
                    put(str, byteArray);
                }
                objectOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                objectOutputStream2 = objectOutputStream;
                e.printStackTrace();
                objectOutputStream2.close();
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream2 = objectOutputStream;
                try {
                    objectOutputStream2.close();
                } catch (IOException unused) {
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
    }

    public void put(String str, String str2) {
        BufferedWriter bufferedWriter;
        File newFile = this.mCacheManager.newFile(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(newFile), 1024);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(str2);
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    this.mCacheManager.put(newFile);
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    this.mCacheManager.put(newFile);
                }
            }
            this.mCacheManager.put(newFile);
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCacheManager.put(newFile);
            throw th;
        }
        this.mCacheManager.put(newFile);
    }

    public void put(String str, String str2, int i) {
        put(str, Utils.newStringWithDateInfo(i, str2));
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public void put(String str, JSONArray jSONArray, int i) {
        put(str, jSONArray.toString(), i);
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public void put(String str, JSONObject jSONObject, int i) {
        put(str, jSONObject.toString(), i);
    }

    public void put(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        File newFile = this.mCacheManager.newFile(str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(newFile);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            fileOutputStream.write(bArr);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    this.mCacheManager.put(newFile);
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    this.mCacheManager.put(newFile);
                }
            }
            this.mCacheManager.put(newFile);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCacheManager.put(newFile);
            throw th;
        }
        this.mCacheManager.put(newFile);
    }

    public void put(String str, byte[] bArr, int i) {
        put(str, Utils.newByteArrayWithDateInfo(i, bArr));
    }

    public boolean remove(String str) {
        return this.mCacheManager.remove(str);
    }
}
