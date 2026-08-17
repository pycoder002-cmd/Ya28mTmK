package com.downloader.internal;

import com.downloader.Error;
import com.downloader.Progress;
import com.downloader.Response;
import com.downloader.Status;
import com.downloader.database.DownloadModel;
import com.downloader.handler.ProgressHandler;
import com.downloader.httpclient.HttpClient;
import com.downloader.request.DownloadRequest;
import com.downloader.utils.Utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;

/* loaded from: classes.dex */
public class DownloadTask {
    private static final int BUFFER_SIZE = 4096;
    private static final long MIN_BYTES_FOR_SYNC = 65536;
    private static final long TIME_GAP_FOR_SYNC = 2000;
    private String eTag;
    private HttpClient httpClient;
    private InputStream inputStream;
    private boolean isResumeSupported;
    private long lastSyncBytes;
    private long lastSyncTime;
    private ProgressHandler progressHandler;
    private final DownloadRequest request;
    private int responseCode;
    private String tempPath;
    private long totalBytes;

    private DownloadTask(DownloadRequest downloadRequest) {
        this.request = downloadRequest;
    }

    private boolean checkIfFreshStartRequiredAndStart(DownloadModel downloadModel) throws IOException, IllegalAccessException {
        if (this.responseCode != 416 && !isETagChanged(downloadModel)) {
            return false;
        }
        if (downloadModel != null) {
            removeNoMoreNeededModelFromDatabase();
        }
        deleteTempFile();
        this.request.setDownloadedBytes(0L);
        this.request.setTotalBytes(0L);
        this.httpClient = ComponentHolder.getInstance().getHttpClient();
        this.httpClient.connect(this.request);
        this.httpClient = Utils.getRedirectedConnectionIfAny(this.httpClient, this.request);
        this.responseCode = this.httpClient.getResponseCode();
        return true;
    }

    private void closeAllSafely(BufferedOutputStream bufferedOutputStream, FileDescriptor fileDescriptor) {
        if (this.httpClient != null) {
            try {
                this.httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.flush();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.sync();
                } catch (SyncFailedException e4) {
                    e4.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DownloadTask create(DownloadRequest downloadRequest) {
        return new DownloadTask(downloadRequest);
    }

    private void createAndInsertNewModel() {
        DownloadModel downloadModel = new DownloadModel();
        downloadModel.setId(this.request.getDownloadId());
        downloadModel.setUrl(this.request.getUrl());
        downloadModel.setETag(this.eTag);
        downloadModel.setDirPath(this.request.getDirPath());
        downloadModel.setFileName(this.request.getFileName());
        downloadModel.setDownloadedBytes(this.request.getDownloadedBytes());
        downloadModel.setTotalBytes(this.totalBytes);
        downloadModel.setLastModifiedAt(System.currentTimeMillis());
        ComponentHolder.getInstance().getDbHelper().insert(downloadModel);
    }

    private void deleteTempFile() {
        File file = new File(this.tempPath);
        if (file.exists()) {
            file.delete();
        }
    }

    private DownloadModel getDownloadModelIfAlreadyPresentInDatabase() {
        return ComponentHolder.getInstance().getDbHelper().find(this.request.getDownloadId());
    }

    private boolean isETagChanged(DownloadModel downloadModel) {
        return (this.eTag == null || downloadModel == null || downloadModel.getETag() == null || downloadModel.getETag().equals(this.eTag)) ? false : true;
    }

    private boolean isSuccessful() {
        return this.responseCode >= 200 && this.responseCode < 300;
    }

    private void removeNoMoreNeededModelFromDatabase() {
        ComponentHolder.getInstance().getDbHelper().remove(this.request.getDownloadId());
    }

    private void sendProgress() {
        if (this.request.getStatus() == Status.CANCELLED || this.progressHandler == null) {
            return;
        }
        this.progressHandler.obtainMessage(1, new Progress(this.request.getDownloadedBytes(), this.totalBytes)).sendToTarget();
    }

    private void setResumeSupportedOrNot() {
        this.isResumeSupported = this.responseCode == 206;
    }

    private void sync(BufferedOutputStream bufferedOutputStream, FileDescriptor fileDescriptor) {
        boolean z;
        try {
            bufferedOutputStream.flush();
            fileDescriptor.sync();
            z = true;
        } catch (IOException e) {
            e.printStackTrace();
            z = false;
        }
        if (z && this.isResumeSupported) {
            ComponentHolder.getInstance().getDbHelper().updateProgress(this.request.getDownloadId(), this.request.getDownloadedBytes(), System.currentTimeMillis());
        }
    }

    private void syncIfRequired(BufferedOutputStream bufferedOutputStream, FileDescriptor fileDescriptor) throws IOException {
        long downloadedBytes = this.request.getDownloadedBytes();
        long currentTimeMillis = System.currentTimeMillis();
        long j = downloadedBytes - this.lastSyncBytes;
        long j2 = currentTimeMillis - this.lastSyncTime;
        if (j <= 65536 || j2 <= TIME_GAP_FOR_SYNC) {
            return;
        }
        sync(bufferedOutputStream, fileDescriptor);
        this.lastSyncBytes = downloadedBytes;
        this.lastSyncTime = currentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Response run() {
        Throwable th;
        FileDescriptor fileDescriptor;
        Response response = new Response();
        boolean z = true;
        if (this.request.getStatus() == Status.CANCELLED) {
            response.setCancelled(true);
            return response;
        }
        if (this.request.getStatus() == Status.PAUSED) {
            response.setPaused(true);
            return response;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                if (this.request.getOnProgressListener() != null) {
                    this.progressHandler = new ProgressHandler(this.request.getOnProgressListener());
                }
                this.tempPath = Utils.getTempPath(this.request.getDirPath(), this.request.getFileName());
                File file = new File(this.tempPath);
                DownloadModel downloadModelIfAlreadyPresentInDatabase = getDownloadModelIfAlreadyPresentInDatabase();
                if (downloadModelIfAlreadyPresentInDatabase != null) {
                    if (file.exists()) {
                        this.request.setTotalBytes(downloadModelIfAlreadyPresentInDatabase.getTotalBytes());
                        this.request.setDownloadedBytes(downloadModelIfAlreadyPresentInDatabase.getDownloadedBytes());
                    } else {
                        removeNoMoreNeededModelFromDatabase();
                        this.request.setDownloadedBytes(0L);
                        this.request.setTotalBytes(0L);
                        downloadModelIfAlreadyPresentInDatabase = null;
                    }
                }
                this.httpClient = ComponentHolder.getInstance().getHttpClient();
                this.httpClient.connect(this.request);
                if (this.request.getStatus() == Status.CANCELLED) {
                    response.setCancelled(true);
                    closeAllSafely(null, null);
                    return response;
                }
                if (this.request.getStatus() == Status.PAUSED) {
                    response.setPaused(true);
                    closeAllSafely(null, null);
                    return response;
                }
                this.httpClient = Utils.getRedirectedConnectionIfAny(this.httpClient, this.request);
                this.responseCode = this.httpClient.getResponseCode();
                this.eTag = this.httpClient.getResponseHeader("ETag");
                if (checkIfFreshStartRequiredAndStart(downloadModelIfAlreadyPresentInDatabase)) {
                    downloadModelIfAlreadyPresentInDatabase = null;
                }
                if (!isSuccessful()) {
                    Error error = new Error();
                    error.setServerError(true);
                    response.setError(error);
                    closeAllSafely(null, null);
                    return response;
                }
                setResumeSupportedOrNot();
                this.totalBytes = this.request.getTotalBytes();
                if (!this.isResumeSupported) {
                    deleteTempFile();
                }
                if (this.totalBytes == 0) {
                    this.totalBytes = this.httpClient.getContentLength();
                    this.request.setTotalBytes(this.totalBytes);
                }
                if (this.isResumeSupported && downloadModelIfAlreadyPresentInDatabase == null) {
                    createAndInsertNewModel();
                }
                if (this.request.getStatus() == Status.CANCELLED) {
                    response.setCancelled(true);
                    closeAllSafely(null, null);
                    return response;
                }
                if (this.request.getStatus() == Status.PAUSED) {
                    response.setPaused(true);
                    closeAllSafely(null, null);
                    return response;
                }
                this.request.deliverStartEvent();
                this.inputStream = this.httpClient.getInputStream();
                int i = 4096;
                byte[] bArr = new byte[4096];
                if (!file.exists()) {
                    if (file.getParentFile() == null || file.getParentFile().exists()) {
                        file.createNewFile();
                    } else if (file.getParentFile().mkdirs()) {
                        file.createNewFile();
                    }
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                fileDescriptor = randomAccessFile.getFD();
                try {
                    try {
                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(randomAccessFile.getFD()));
                        try {
                            try {
                                if (this.isResumeSupported && this.request.getDownloadedBytes() != 0) {
                                    randomAccessFile.seek(this.request.getDownloadedBytes());
                                }
                                if (this.request.getStatus() == Status.CANCELLED) {
                                    response.setCancelled(true);
                                    closeAllSafely(bufferedOutputStream2, fileDescriptor);
                                    return response;
                                }
                                if (this.request.getStatus() == Status.PAUSED) {
                                    response.setPaused(true);
                                    closeAllSafely(bufferedOutputStream2, fileDescriptor);
                                    return response;
                                }
                                while (true) {
                                    int read = this.inputStream.read(bArr, 0, i);
                                    if (read == -1) {
                                        Utils.renameFileName(this.tempPath, Utils.getPath(this.request.getDirPath(), this.request.getFileName()));
                                        response.setSuccessful(z);
                                        if (this.isResumeSupported) {
                                            removeNoMoreNeededModelFromDatabase();
                                        }
                                        closeAllSafely(bufferedOutputStream2, fileDescriptor);
                                    } else {
                                        bufferedOutputStream2.write(bArr, 0, read);
                                        this.request.setDownloadedBytes(this.request.getDownloadedBytes() + read);
                                        sendProgress();
                                        syncIfRequired(bufferedOutputStream2, fileDescriptor);
                                        if (this.request.getStatus() == Status.CANCELLED) {
                                            response.setCancelled(true);
                                            closeAllSafely(bufferedOutputStream2, fileDescriptor);
                                            return response;
                                        }
                                        if (this.request.getStatus() == Status.PAUSED) {
                                            sync(bufferedOutputStream2, fileDescriptor);
                                            response.setPaused(true);
                                            closeAllSafely(bufferedOutputStream2, fileDescriptor);
                                            return response;
                                        }
                                        z = true;
                                        i = 4096;
                                    }
                                }
                            } catch (IOException | IllegalAccessException unused) {
                                bufferedOutputStream = bufferedOutputStream2;
                                if (!this.isResumeSupported) {
                                    deleteTempFile();
                                }
                                Error error2 = new Error();
                                error2.setConnectionError(true);
                                response.setError(error2);
                                closeAllSafely(bufferedOutputStream, fileDescriptor);
                                return response;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedOutputStream = bufferedOutputStream2;
                            closeAllSafely(bufferedOutputStream, fileDescriptor);
                            throw th;
                        }
                    } catch (IOException | IllegalAccessException unused2) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException | IllegalAccessException unused3) {
                fileDescriptor = null;
            }
        } catch (Throwable th4) {
            th = th4;
            fileDescriptor = null;
        }
    }
}
