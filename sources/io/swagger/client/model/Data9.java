package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data9 {

    @SerializedName("application")
    private String application = null;

    @SerializedName("description")
    private String description = null;

    @SerializedName("kind")
    private String kind = null;

    @SerializedName("user")
    private String user = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data9 data9 = (Data9) obj;
        if (this.application != null ? this.application.equals(data9.application) : data9.application == null) {
            if (this.description != null ? this.description.equals(data9.description) : data9.description == null) {
                if (this.kind != null ? this.kind.equals(data9.kind) : data9.kind == null) {
                    if (this.user == null) {
                        if (data9.user == null) {
                            return true;
                        }
                    } else if (this.user.equals(data9.user)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty(required = true, value = "")
    public String getApplication() {
        return this.application;
    }

    @ApiModelProperty(required = true, value = "")
    public String getDescription() {
        return this.description;
    }

    @ApiModelProperty(required = true, value = "")
    public String getKind() {
        return this.kind;
    }

    @ApiModelProperty(required = true, value = "")
    public String getUser() {
        return this.user;
    }

    public int hashCode() {
        return (31 * (((((527 + (this.application == null ? 0 : this.application.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.kind == null ? 0 : this.kind.hashCode()))) + (this.user != null ? this.user.hashCode() : 0);
    }

    public void setApplication(String str) {
        this.application = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setKind(String str) {
        this.kind = str;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public String toString() {
        return "class Data9 {\n  application: " + this.application + "\n  description: " + this.description + "\n  kind: " + this.kind + "\n  user: " + this.user + "\n}\n";
    }
}
