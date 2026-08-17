package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data24 {

    @SerializedName("description")
    private String description = null;

    @SerializedName("release")
    private String release = null;

    @SerializedName("image")
    private String image = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data24 data24 = (Data24) obj;
        if (this.description != null ? this.description.equals(data24.description) : data24.description == null) {
            if (this.release != null ? this.release.equals(data24.release) : data24.release == null) {
                if (this.image == null) {
                    if (data24.image == null) {
                        return true;
                    }
                } else if (this.image.equals(data24.image)) {
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

    @ApiModelProperty("")
    public String getImage() {
        return this.image;
    }

    @ApiModelProperty("")
    public String getRelease() {
        return this.release;
    }

    public int hashCode() {
        return (31 * (((527 + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.release == null ? 0 : this.release.hashCode()))) + (this.image != null ? this.image.hashCode() : 0);
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
        return "class Data24 {\n  description: " + this.description + "\n  release: " + this.release + "\n  image: " + this.image + "\n}\n";
    }
}
