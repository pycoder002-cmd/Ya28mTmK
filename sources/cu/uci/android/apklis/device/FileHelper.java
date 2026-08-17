package cu.uci.android.apklis.device;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import cu.uci.android.apklis.MainApp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class FileHelper {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    static final String FILES_PATH = "Compressor";

    private FileHelper() {
    }

    static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long copyLarge = copyLarge(inputStream, outputStream);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copyLarge(inputStream, outputStream, new byte[4096]);
    }

    static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += read;
        }
    }

    public static File from(Context context, Uri uri) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] splitFileName = splitFileName(fileName);
        File rename = rename(File.createTempFile(splitFileName[0], splitFileName[1]), fileName);
        rename.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(rename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MainApp.log("FileHelper", e);
            fileOutputStream = null;
        }
        if (openInputStream != null) {
            copy(openInputStream, fileOutputStream);
            openInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return rename;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
    
        if (r8 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String getFileName(android.content.Context r8, android.net.Uri r9) {
        /*
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L47
            android.content.ContentResolver r2 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)
            if (r8 == 0) goto L42
            boolean r0 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r0 == 0) goto L42
            java.lang.String r0 = "_display_name"
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            java.lang.String r0 = r8.getString(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r1 = r0
            goto L42
        L2e:
            r9 = move-exception
            goto L3c
        L30:
            r0 = move-exception
            java.lang.String r2 = "FileHelper"
            cu.uci.android.apklis.MainApp.log(r2, r0)     // Catch: java.lang.Throwable -> L2e
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L47
            goto L44
        L3c:
            if (r8 == 0) goto L41
            r8.close()
        L41:
            throw r9
        L42:
            if (r8 == 0) goto L47
        L44:
            r8.close()
        L47:
            if (r1 != 0) goto L5c
            java.lang.String r1 = r9.getPath()
            java.lang.String r8 = java.io.File.separator
            int r8 = r1.lastIndexOf(r8)
            r9 = -1
            if (r8 == r9) goto L5c
            int r8 = r8 + 1
            java.lang.String r1 = r1.substring(r8)
        L5c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.device.FileHelper.getFileName(android.content.Context, android.net.Uri):java.lang.String");
    }

    static String getRealPathFromURI(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            return uri.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }

    static File rename(File file, String str) {
        File file2 = new File(file.getParent(), str);
        if (!file2.equals(file)) {
            if (file2.exists() && file2.delete()) {
                Log.d("FileUtil", "Delete old " + str + " file");
            }
            if (file.renameTo(file2)) {
                Log.d("FileUtil", "Rename file to " + str);
            }
        }
        return file2;
    }

    static String[] splitFileName(String str) {
        String str2 = "";
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            str2 = str.substring(lastIndexOf);
            str = substring;
        }
        return new String[]{str, str2};
    }
}
