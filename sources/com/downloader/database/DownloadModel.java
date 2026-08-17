package com.downloader.database;

/* loaded from: classes.dex */
public class DownloadModel {
    static final String DIR_PATH = "dir_path";
    static final String DOWNLOADED_BYTES = "downloaded_bytes";
    static final String ETAG = "etag";
    static final String FILE_NAME = "file_name";
    static final String ID = "id";
    static final String LAST_MODIFIED_AT = "last_modified_at";
    static final String TOTAL_BYTES = "total_bytes";
    static final String URL = "url";
    private String dirPath;
    private long downloadedBytes;
    private String eTag;
    private String fileName;
    private int id;
    private long lastModifiedAt;
    private long totalBytes;
    private String url;

    public String getDirPath() {
        return this.dirPath;
    }

    public long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public String getETag() {
        return this.eTag;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getId() {
        return this.id;
    }

    public long getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDirPath(String str) {
        this.dirPath = str;
    }

    public void setDownloadedBytes(long j) {
        this.downloadedBytes = j;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setLastModifiedAt(long j) {
        this.lastModifiedAt = j;
    }

    public void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
