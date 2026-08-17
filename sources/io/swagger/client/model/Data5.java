package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data5 {

    @SerializedName("group")
    private String group = null;

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
        Data5 data5 = (Data5) obj;
        if (this.group != null ? this.group.equals(data5.group) : data5.group == null) {
            if (this.name != null ? this.name.equals(data5.name) : data5.name == null) {
                if (this.icon == null) {
                    if (data5.icon == null) {
                        return true;
                    }
                } else if (this.icon.equals(data5.icon)) {
                    return true;
                }
            }
        }
        return false;
    }

    @ApiModelProperty(required = true, value = "")
    public String getGroup() {
        return this.group;
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
        return (31 * (((527 + (this.group == null ? 0 : this.group.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode()))) + (this.icon != null ? this.icon.hashCode() : 0);
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "class Data5 {\n  group: " + this.group + "\n  name: " + this.name + "\n  icon: " + this.icon + "\n}\n";
    }
}
