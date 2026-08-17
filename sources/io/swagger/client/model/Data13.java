package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data13 {

    @SerializedName("title")
    private String title = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data13 data13 = (Data13) obj;
        return this.title == null ? data13.title == null : this.title.equals(data13.title);
    }

    @ApiModelProperty("")
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return 527 + (this.title == null ? 0 : this.title.hashCode());
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "class Data13 {\n  title: " + this.title + "\n}\n";
    }
}
