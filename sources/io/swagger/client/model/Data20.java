package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data20 {

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
        Data20 data20 = (Data20) obj;
        if (this.comment != null ? this.comment.equals(data20.comment) : data20.comment == null) {
            if (this.user != null ? this.user.equals(data20.user) : data20.user == null) {
                if (this._public != null ? this._public.equals(data20._public) : data20._public == null) {
                    if (this.rating == null) {
                        if (data20.rating == null) {
                            return true;
                        }
                    } else if (this.rating.equals(data20.rating)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty(required = true, value = "")
    public String getComment() {
        return this.comment;
    }

    @ApiModelProperty("")
    public Boolean getPublic() {
        return this._public;
    }

    @ApiModelProperty(required = true, value = "")
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
        return "class Data20 {\n  comment: " + this.comment + "\n  user: " + this.user + "\n  _public: " + this._public + "\n  rating: " + this.rating + "\n}\n";
    }
}
