package com.downloader.core;

/* loaded from: classes.dex */
public class Core {
    private static Core instance;
    private final ExecutorSupplier executorSupplier = new DefaultExecutorSupplier();

    private Core() {
    }

    public static Core getInstance() {
        if (instance == null) {
            synchronized (Core.class) {
                if (instance == null) {
                    instance = new Core();
                }
            }
        }
        return instance;
    }

    public static void shutDown() {
        if (instance != null) {
            instance = null;
        }
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.executorSupplier;
    }
}
