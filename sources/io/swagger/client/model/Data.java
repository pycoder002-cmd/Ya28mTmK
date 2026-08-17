package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data {

    @SerializedName("description")
    private String description = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("icon")
    private String icon = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data data = (Data) obj;
        if (this.description != null ? this.description.equals(data.description) : data.description == null) {
            if (this.name != null ? this.name.equals(data.name) : data.name == null) {
                if (this.icon == null) {
                    if (data.icon == null) {
                        return true;
                    }
                } else if (this.icon.equals(data.icon)) {
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
    public String getIcon() {
        return this.icon;
    }

    @ApiModelProperty(required = true, value = "")
    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return (31 * (((527 + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode()))) + (this.icon != null ? this.icon.hashCode() : 0);
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "class Data {\n  description: " + this.description + "\n  name: " + this.name + "\n  icon: " + this.icon + "\n}\n";
    }
}
