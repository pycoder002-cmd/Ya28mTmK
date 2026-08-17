package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data19 {

    @SerializedName(ClientCookie.COMMENT_ATTR)
    private String comment = null;

    @SerializedName("application")
    private String application = null;

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
        Data19 data19 = (Data19) obj;
        if (this.comment != null ? this.comment.equals(data19.comment) : data19.comment == null) {
            if (this.application != null ? this.application.equals(data19.application) : data19.application == null) {
                if (this._public != null ? this._public.equals(data19._public) : data19._public == null) {
                    if (this.rating == null) {
                        if (data19.rating == null) {
                            return true;
                        }
                    } else if (this.rating.equals(data19.rating)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty(required = true, value = "")
    public String getApplication() {
        return this.application;
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

    public int hashCode() {
        return (31 * (((((527 + (this.comment == null ? 0 : this.comment.hashCode())) * 31) + (this.application == null ? 0 : this.application.hashCode())) * 31) + (this._public == null ? 0 : this._public.hashCode()))) + (this.rating != null ? this.rating.hashCode() : 0);
    }

    public void setApplication(String str) {
        this.application = str;
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

    public String toString() {
        return "class Data19 {\n  comment: " + this.comment + "\n  application: " + this.application + "\n  _public: " + this._public + "\n  rating: " + this.rating + "\n}\n";
    }
}
