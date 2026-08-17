package com.nononsenseapps.filepicker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class Utils {
    private static final String SEP = "/";

    @NonNull
    public static String appendPath(@NonNull String str, @NonNull String str2) {
        String str3 = str + SEP + str2;
        while (str3.contains("//")) {
            str3 = str3.replaceAll("//", SEP);
        }
        return (str3.length() <= 1 || !str3.endsWith(SEP)) ? str3 : str3.substring(0, str3.length() - 1);
    }

    @NonNull
    public static File getFileForUri(@NonNull Uri uri) {
        String encodedPath = uri.getEncodedPath();
        int indexOf = encodedPath.indexOf(47, 1);
        String decode = Uri.decode(encodedPath.substring(1, indexOf));
        String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
        if (!"root".equalsIgnoreCase(decode)) {
            throw new IllegalArgumentException(String.format("Can't decode paths to '%s', only for 'root' paths.", decode));
        }
        File file = new File(SEP);
        File file2 = new File(file, decode2);
        try {
            File canonicalFile = file2.getCanonicalFile();
            if (canonicalFile.getPath().startsWith(file.getPath())) {
                return canonicalFile;
            }
            throw new SecurityException("Resolved path jumped beyond configured root");
        } catch (IOException unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
        }
    }

    @NonNull
    public static List<Uri> getSelectedFilesFromResult(@NonNull Intent intent) {
        ArrayList arrayList = new ArrayList();
        if (intent.getBooleanExtra(AbstractFilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(AbstractFilePickerActivity.EXTRA_PATHS);
            if (stringArrayListExtra != null) {
                Iterator<String> it = stringArrayListExtra.iterator();
                while (it.hasNext()) {
                    arrayList.add(Uri.parse(it.next()));
                }
            }
        } else {
            arrayList.add(intent.getData());
        }
        return arrayList;
    }

    public static boolean isValidFileName(@Nullable String str) {
        return (TextUtils.isEmpty(str) || str.contains(SEP) || str.equals(".") || str.equals("..")) ? false : true;
    }
}
