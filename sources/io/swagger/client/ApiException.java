package io.swagger.client;

/* loaded from: classes2.dex */
public class ApiException extends Exception {
    int code;
    String message;

    public ApiException() {
        this.code = 0;
        this.message = null;
    }

    public ApiException(int i, String str) {
        this.code = 0;
        this.message = null;
        this.code = i;
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
