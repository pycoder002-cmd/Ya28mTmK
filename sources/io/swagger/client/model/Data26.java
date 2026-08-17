package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data26 {

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public = null;

    @SerializedName("user")
    private Object user = null;

    @SerializedName("rating")
    private BigDecimal rating = null;

    @SerializedName(ClientCookie.COMMENT_ATTR)
    private String comment = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data26 data26 = (Data26) obj;
        if (this._public != null ? this._public.equals(data26._public) : data26._public == null) {
            if (this.user != null ? this.user.equals(data26.user) : data26.user == null) {
                if (this.rating != null ? this.rating.equals(data26.rating) : data26.rating == null) {
                    if (this.comment == null) {
                        if (data26.comment == null) {
                            return true;
                        }
                    } else if (this.comment.equals(data26.comment)) {
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
        return (31 * (((((527 + (this._public == null ? 0 : this._public.hashCode())) * 31) + (this.user == null ? 0 : this.user.hashCode())) * 31) + (this.rating == null ? 0 : this.rating.hashCode()))) + (this.comment != null ? this.comment.hashCode() : 0);
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
        return "class Data26 {\n  _public: " + this._public + "\n  user: " + this.user + "\n  rating: " + this.rating + "\n  comment: " + this.comment + "\n}\n";
    }
}
