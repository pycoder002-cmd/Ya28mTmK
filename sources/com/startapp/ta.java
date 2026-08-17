package com.startapp;

import android.content.Context;
import android.content.pm.PackageManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ta {
    public final Context a;

    public ta(Context context) {
        this.a = context;
    }

    public boolean a() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        String[] strArr = new String[0];
        try {
            strArr = new Scanner(Runtime.getRuntime().exec("getprop").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }
        boolean z = false;
        for (String str : strArr) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    if (str.contains("[" + ((String) hashMap.get(str2)) + "]")) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean a(String str) {
        boolean z = false;
        for (String str2 : ua.c) {
            if (new File(str2, str).exists()) {
                z = true;
            }
        }
        return z;
    }

    public final boolean a(List<String> list) {
        PackageManager packageManager = this.a.getPackageManager();
        Iterator<String> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                packageManager.getPackageInfo(it.next(), 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean b() {
        String[] strArr = new String[0];
        try {
            strArr = new Scanner(Runtime.getRuntime().exec("mount").getInputStream()).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }
        boolean z = false;
        for (String str : strArr) {
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length >= 4) {
                String str2 = split[1];
                String str3 = split[3];
                for (String str4 : ua.d) {
                    if (str2.equalsIgnoreCase(str4)) {
                        String[] split2 = str3.split(",");
                        int length = split2.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            if (split2[i].equalsIgnoreCase("rw")) {
                                z = true;
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
        }
        return z;
    }

    public boolean c() {
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            try {
                boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
                process.destroy();
                return z;
            } catch (Throwable unused) {
                if (process != null) {
                    process.destroy();
                }
                return false;
            }
        } catch (Throwable unused2) {
            process = null;
        }
    }
}
