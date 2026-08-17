package com.vladium.emma.rt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: apklis.apk:jacocoagent.jar:com/vladium/emma/rt/RT.class */
public final class RT {
    private RT() {
    }

    public static void dumpCoverageData(File outFile, boolean merge, boolean stopDataCollection) throws IOException {
        OutputStream out = new FileOutputStream(outFile, merge);
        try {
            out.write(org.jacoco.agent.rt.RT.getAgent().getExecutionData(false));
            out.close();
        } catch (Throwable th) {
            out.close();
            throw th;
        }
    }

    public static synchronized void dumpCoverageData(File outFile, boolean stopDataCollection) throws IOException {
        dumpCoverageData(outFile, true, stopDataCollection);
    }
}
