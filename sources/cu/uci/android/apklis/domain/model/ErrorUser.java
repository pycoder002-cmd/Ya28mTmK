package cu.uci.android.apklis.domain.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class ErrorUser {

    @SerializedName("password")
    private String[] password;

    @SerializedName("phone_number")
    private String[] phoneNumber;
    private boolean serverError;

    @SerializedName("username")
    private String[] username;

    public ErrorUser() {
        this.serverError = false;
    }

    public ErrorUser(String[] strArr, String[] strArr2, String[] strArr3, boolean z) {
        this.username = strArr;
        this.phoneNumber = strArr2;
        this.password = strArr3;
        this.serverError = z;
    }

    public String[] getPassword() {
        return this.password;
    }

    public String[] getPhoneNumber() {
        return this.phoneNumber;
    }

    public String[] getUsername() {
        return this.username;
    }

    public boolean isServerError() {
        return this.serverError;
    }

    public void setPassword(String[] strArr) {
        this.password = strArr;
    }

    public void setPhoneNumber(String[] strArr) {
        this.phoneNumber = strArr;
    }

    public void setServerError(boolean z) {
        this.serverError = z;
    }

    public void setUsername(String[] strArr) {
        this.username = strArr;
    }
}
