package com.downloader.database;

import java.util.List;

/* loaded from: classes.dex */
public class NoOpsDbHelper implements DbHelper {
    @Override // com.downloader.database.DbHelper
    public void clear() {
    }

    @Override // com.downloader.database.DbHelper
    public DownloadModel find(int i) {
        return null;
    }

    @Override // com.downloader.database.DbHelper
    public List<DownloadModel> getUnwantedModels(int i) {
        return null;
    }

    @Override // com.downloader.database.DbHelper
    public void insert(DownloadModel downloadModel) {
    }

    @Override // com.downloader.database.DbHelper
    public void remove(int i) {
    }

    @Override // com.downloader.database.DbHelper
    public void update(DownloadModel downloadModel) {
    }

    @Override // com.downloader.database.DbHelper
    public void updateProgress(int i, long j, long j2) {
    }
}
