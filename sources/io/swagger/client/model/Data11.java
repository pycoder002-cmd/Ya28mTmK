package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data11 {

    @SerializedName("title")
    private String title = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data11 data11 = (Data11) obj;
        return this.title == null ? data11.title == null : this.title.equals(data11.title);
    }

    @ApiModelProperty(required = true, value = "")
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
        return "class Data11 {\n  title: " + this.title + "\n}\n";
    }
}
