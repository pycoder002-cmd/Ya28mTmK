package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data14 {

    @SerializedName("description")
    private String description = null;

    @SerializedName("is_active")
    private Boolean isActive = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data14 data14 = (Data14) obj;
        if (this.description != null ? this.description.equals(data14.description) : data14.description == null) {
            if (this.isActive == null) {
                if (data14.isActive == null) {
                    return true;
                }
            } else if (this.isActive.equals(data14.isActive)) {
                return true;
            }
        }
        return false;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    @ApiModelProperty("")
    public Boolean getIsActive() {
        return this.isActive;
    }

    public int hashCode() {
        return (31 * (527 + (this.description == null ? 0 : this.description.hashCode()))) + (this.isActive != null ? this.isActive.hashCode() : 0);
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIsActive(Boolean bool) {
        this.isActive = bool;
    }

    public String toString() {
        return "class Data14 {\n  description: " + this.description + "\n  isActive: " + this.isActive + "\n}\n";
    }
}
