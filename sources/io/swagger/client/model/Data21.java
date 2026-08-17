package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data21 {

    @SerializedName(ClientCookie.COMMENT_ATTR)
    private String comment = null;

    @SerializedName("user")
    private Object user = null;

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public = null;

    @SerializedName("rating")
    private BigDecimal rating = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data21 data21 = (Data21) obj;
        if (this.comment != null ? this.comment.equals(data21.comment) : data21.comment == null) {
            if (this.user != null ? this.user.equals(data21.user) : data21.user == null) {
                if (this._public != null ? this._public.equals(data21._public) : data21._public == null) {
                    if (this.rating == null) {
                        if (data21.rating == null) {
                            return true;
                        }
                    } else if (this.rating.equals(data21.rating)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty("")
    public String getComment() {
        return this.comment;
    }

    @ApiModelProperty("")
    public Boolean getPublic() {
        return this._public;
    }

    @ApiModelProperty("")
    public BigDecimal getRating() {
        return this.rating;
    }

    @ApiModelProperty("")
    public Object getUser() {
        return this.user;
    }

    public int hashCode() {
        return (31 * (((((527 + (this.comment == null ? 0 : this.comment.hashCode())) * 31) + (this.user == null ? 0 : this.user.hashCode())) * 31) + (this._public == null ? 0 : this._public.hashCode()))) + (this.rating != null ? this.rating.hashCode() : 0);
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setPublic(Boolean bool) {
        this._public = bool;
    }

    public void setRating(BigDecimal bigDecimal) {
        this.rating = bigDecimal;
    }

    public void setUser(Object obj) {
        this.user = obj;
    }

    public String toString() {
        return "class Data21 {\n  comment: " + this.comment + "\n  user: " + this.user + "\n  _public: " + this._public + "\n  rating: " + this.rating + "\n}\n";
    }
}
