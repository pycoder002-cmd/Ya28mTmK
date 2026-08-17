package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data7 {

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
        Data7 data7 = (Data7) obj;
        if (this.group != null ? this.group.equals(data7.group) : data7.group == null) {
            if (this.name != null ? this.name.equals(data7.name) : data7.name == null) {
                if (this.icon == null) {
                    if (data7.icon == null) {
                        return true;
                    }
                } else if (this.icon.equals(data7.icon)) {
                    return true;
                }
            }
        }
        return false;
    }

    @ApiModelProperty("")
    public String getGroup() {
        return this.group;
    }

    @ApiModelProperty("")
    public String getIcon() {
        return this.icon;
    }

    @ApiModelProperty("")
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
        return "class Data7 {\n  group: " + this.group + "\n  name: " + this.name + "\n  icon: " + this.icon + "\n}\n";
    }
}
