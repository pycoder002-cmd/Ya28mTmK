package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data27 {

    @SerializedName("release")
    private String release = null;

    @SerializedName("description")
    private String description = null;

    @SerializedName("image")
    private String image = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data27 data27 = (Data27) obj;
        if (this.release != null ? this.release.equals(data27.release) : data27.release == null) {
            if (this.description != null ? this.description.equals(data27.description) : data27.description == null) {
                if (this.image == null) {
                    if (data27.image == null) {
                        return true;
                    }
                } else if (this.image.equals(data27.image)) {
                    return true;
                }
            }
        }
        return false;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    @ApiModelProperty(required = true, value = "")
    public String getImage() {
        return this.image;
    }

    @ApiModelProperty(required = true, value = "")
    public String getRelease() {
        return this.release;
    }

    public int hashCode() {
        return (31 * (((527 + (this.release == null ? 0 : this.release.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode()))) + (this.image != null ? this.image.hashCode() : 0);
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public String toString() {
        return "class Data27 {\n  release: " + this.release + "\n  description: " + this.description + "\n  image: " + this.image + "\n}\n";
    }
}
