package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return new File(readlink);
            }
            return null;
        } catch (ErrnoException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[Catch: all -> 0x0061, Throwable -> 0x0064, SYNTHETIC, TRY_LEAVE, TryCatch #1 {all -> 0x0061, blocks: (B:9:0x0018, B:11:0x001e, B:14:0x0025, B:18:0x002f, B:22:0x003e, B:38:0x0054, B:35:0x005d, B:42:0x0059, B:36:0x0060), top: B:8:0x0018 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[Catch: IOException -> 0x007b, SYNTHETIC, TRY_LEAVE, TryCatch #7 {IOException -> 0x007b, blocks: (B:7:0x000e, B:16:0x002b, B:24:0x0043, B:54:0x006e, B:51:0x0077, B:58:0x0073, B:52:0x007a), top: B:6:0x000e, inners: #4 }] */
    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r5, android.os.CancellationSignal r6, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r7, int r8) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            int r2 = r7.length
            if (r2 >= r1) goto L6
            return r0
        L6:
            android.support.v4.provider.FontsContractCompat$FontInfo r7 = r4.findBestInfo(r7, r8)
            android.content.ContentResolver r8 = r5.getContentResolver()
            android.net.Uri r7 = r7.getUri()     // Catch: java.io.IOException -> L7b
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r6 = r8.openFileDescriptor(r7, r1, r6)     // Catch: java.io.IOException -> L7b
            java.io.File r7 = r4.getFile(r6)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            if (r7 == 0) goto L2f
            boolean r8 = r7.canRead()     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            if (r8 != 0) goto L25
            goto L2f
        L25:
            android.graphics.Typeface r5 = android.graphics.Typeface.createFromFile(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            if (r6 == 0) goto L2e
            r6.close()     // Catch: java.io.IOException -> L7b
        L2e:
            return r5
        L2f:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            java.io.FileDescriptor r8 = r6.getFileDescriptor()     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            android.graphics.Typeface r5 = super.createFromInputStream(r5, r7)     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L4a
            if (r7 == 0) goto L41
            r7.close()     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
        L41:
            if (r6 == 0) goto L46
            r6.close()     // Catch: java.io.IOException -> L7b
        L46:
            return r5
        L47:
            r5 = move-exception
            r8 = r0
            goto L50
        L4a:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L4c
        L4c:
            r8 = move-exception
            r3 = r8
            r8 = r5
            r5 = r3
        L50:
            if (r7 == 0) goto L60
            if (r8 == 0) goto L5d
            r7.close()     // Catch: java.lang.Throwable -> L58 java.lang.Throwable -> L61
            goto L60
        L58:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
            goto L60
        L5d:
            r7.close()     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
        L60:
            throw r5     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L64
        L61:
            r5 = move-exception
            r7 = r0
            goto L6a
        L64:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L66
        L66:
            r7 = move-exception
            r3 = r7
            r7 = r5
            r5 = r3
        L6a:
            if (r6 == 0) goto L7a
            if (r7 == 0) goto L77
            r6.close()     // Catch: java.lang.Throwable -> L72 java.io.IOException -> L7b
            goto L7a
        L72:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch: java.io.IOException -> L7b
            goto L7a
        L77:
            r6.close()     // Catch: java.io.IOException -> L7b
        L7a:
            throw r5     // Catch: java.io.IOException -> L7b
        L7b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
