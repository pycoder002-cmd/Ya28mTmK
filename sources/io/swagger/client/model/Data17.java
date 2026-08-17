package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data17 {

    @SerializedName("application")
    private Object application = null;

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public = null;

    @SerializedName("apk_file")
    private String apkFile = null;

    @SerializedName("changelog")
    private String changelog = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data17 data17 = (Data17) obj;
        if (this.application != null ? this.application.equals(data17.application) : data17.application == null) {
            if (this._public != null ? this._public.equals(data17._public) : data17._public == null) {
                if (this.apkFile != null ? this.apkFile.equals(data17.apkFile) : data17.apkFile == null) {
                    if (this.changelog == null) {
                        if (data17.changelog == null) {
                            return true;
                        }
                    } else if (this.changelog.equals(data17.changelog)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty(required = true, value = "")
    public String getApkFile() {
        return this.apkFile;
    }

    @ApiModelProperty(required = true, value = "")
    public Object getApplication() {
        return this.application;
    }

    @ApiModelProperty(required = true, value = "")
    public String getChangelog() {
        return this.changelog;
    }

    @ApiModelProperty("")
    public Boolean getPublic() {
        return this._public;
    }

    public int hashCode() {
        return (31 * (((((527 + (this.application == null ? 0 : this.application.hashCode())) * 31) + (this._public == null ? 0 : this._public.hashCode())) * 31) + (this.apkFile == null ? 0 : this.apkFile.hashCode()))) + (this.changelog != null ? this.changelog.hashCode() : 0);
    }

    public void setApkFile(String str) {
        this.apkFile = str;
    }

    public void setApplication(Object obj) {
        this.application = obj;
    }

    public void setChangelog(String str) {
        this.changelog = str;
    }

    public void setPublic(Boolean bool) {
        this._public = bool;
    }

    public String toString() {
        return "class Data17 {\n  application: " + this.application + "\n  _public: " + this._public + "\n  apkFile: " + this.apkFile + "\n  changelog: " + this.changelog + "\n}\n";
    }
}
