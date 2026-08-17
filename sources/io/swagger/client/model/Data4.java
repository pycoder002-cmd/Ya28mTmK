package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(description = "")
/* loaded from: classes2.dex */
public class Data4 {

    @SerializedName("reviews_count")
    private Integer reviewsCount = null;

    @SerializedName("reviews_star_5")
    private Integer reviewsStar5 = null;

    @SerializedName("reviews_star_4")
    private Integer reviewsStar4 = null;

    @SerializedName("price")
    private BigDecimal price = null;

    @SerializedName("reviews_star_3")
    private Integer reviewsStar3 = null;

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public = null;

    @SerializedName("reviews_star_2")
    private Integer reviewsStar2 = null;

    @SerializedName("sponsored")
    private Integer sponsored = null;

    @SerializedName("reviews_star_1")
    private Integer reviewsStar1 = null;

    @SerializedName("deleted")
    private Boolean deleted = null;

    @SerializedName("description")
    private String description = null;

    @SerializedName("releases_count")
    private Integer releasesCount = null;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Data4 data4 = (Data4) obj;
        if (this.reviewsCount != null ? this.reviewsCount.equals(data4.reviewsCount) : data4.reviewsCount == null) {
            if (this.reviewsStar5 != null ? this.reviewsStar5.equals(data4.reviewsStar5) : data4.reviewsStar5 == null) {
                if (this.reviewsStar4 != null ? this.reviewsStar4.equals(data4.reviewsStar4) : data4.reviewsStar4 == null) {
                    if (this.price != null ? this.price.equals(data4.price) : data4.price == null) {
                        if (this.reviewsStar3 != null ? this.reviewsStar3.equals(data4.reviewsStar3) : data4.reviewsStar3 == null) {
                            if (this._public != null ? this._public.equals(data4._public) : data4._public == null) {
                                if (this.reviewsStar2 != null ? this.reviewsStar2.equals(data4.reviewsStar2) : data4.reviewsStar2 == null) {
                                    if (this.sponsored != null ? this.sponsored.equals(data4.sponsored) : data4.sponsored == null) {
                                        if (this.reviewsStar1 != null ? this.reviewsStar1.equals(data4.reviewsStar1) : data4.reviewsStar1 == null) {
                                            if (this.deleted != null ? this.deleted.equals(data4.deleted) : data4.deleted == null) {
                                                if (this.description != null ? this.description.equals(data4.description) : data4.description == null) {
                                                    if (this.releasesCount == null) {
                                                        if (data4.releasesCount == null) {
                                                            return true;
                                                        }
                                                    } else if (this.releasesCount.equals(data4.releasesCount)) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @ApiModelProperty("")
    public Boolean getDeleted() {
        return this.deleted;
    }

    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    @ApiModelProperty("")
    public BigDecimal getPrice() {
        return this.price;
    }

    @ApiModelProperty("")
    public Boolean getPublic() {
        return this._public;
    }

    @ApiModelProperty("")
    public Integer getReleasesCount() {
        return this.releasesCount;
    }

    @ApiModelProperty("")
    public Integer getReviewsCount() {
        return this.reviewsCount;
    }

    @ApiModelProperty("")
    public Integer getReviewsStar1() {
        return this.reviewsStar1;
    }

    @ApiModelProperty("")
    public Integer getReviewsStar2() {
        return this.reviewsStar2;
    }

    @ApiModelProperty("")
    public Integer getReviewsStar3() {
        return this.reviewsStar3;
    }

    @ApiModelProperty("")
    public Integer getReviewsStar4() {
        return this.reviewsStar4;
    }

    @ApiModelProperty("")
    public Integer getReviewsStar5() {
        return this.reviewsStar5;
    }

    @ApiModelProperty("")
    public Integer getSponsored() {
        return this.sponsored;
    }

    public int hashCode() {
        return (31 * (((((((((((((((((((((527 + (this.reviewsCount == null ? 0 : this.reviewsCount.hashCode())) * 31) + (this.reviewsStar5 == null ? 0 : this.reviewsStar5.hashCode())) * 31) + (this.reviewsStar4 == null ? 0 : this.reviewsStar4.hashCode())) * 31) + (this.price == null ? 0 : this.price.hashCode())) * 31) + (this.reviewsStar3 == null ? 0 : this.reviewsStar3.hashCode())) * 31) + (this._public == null ? 0 : this._public.hashCode())) * 31) + (this.reviewsStar2 == null ? 0 : this.reviewsStar2.hashCode())) * 31) + (this.sponsored == null ? 0 : this.sponsored.hashCode())) * 31) + (this.reviewsStar1 == null ? 0 : this.reviewsStar1.hashCode())) * 31) + (this.deleted == null ? 0 : this.deleted.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode()))) + (this.releasesCount != null ? this.releasesCount.hashCode() : 0);
    }

    public void setDeleted(Boolean bool) {
        this.deleted = bool;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }

    public void setPublic(Boolean bool) {
        this._public = bool;
    }

    public void setReleasesCount(Integer num) {
        this.releasesCount = num;
    }

    public void setReviewsCount(Integer num) {
        this.reviewsCount = num;
    }

    public void setReviewsStar1(Integer num) {
        this.reviewsStar1 = num;
    }

    public void setReviewsStar2(Integer num) {
        this.reviewsStar2 = num;
    }

    public void setReviewsStar3(Integer num) {
        this.reviewsStar3 = num;
    }

    public void setReviewsStar4(Integer num) {
        this.reviewsStar4 = num;
    }

    public void setReviewsStar5(Integer num) {
        this.reviewsStar5 = num;
    }

    public void setSponsored(Integer num) {
        this.sponsored = num;
    }

    public String toString() {
        return "class Data4 {\n  reviewsCount: " + this.reviewsCount + "\n  reviewsStar5: " + this.reviewsStar5 + "\n  reviewsStar4: " + this.reviewsStar4 + "\n  price: " + this.price + "\n  reviewsStar3: " + this.reviewsStar3 + "\n  _public: " + this._public + "\n  reviewsStar2: " + this.reviewsStar2 + "\n  sponsored: " + this.sponsored + "\n  reviewsStar1: " + this.reviewsStar1 + "\n  deleted: " + this.deleted + "\n  description: " + this.description + "\n  releasesCount: " + this.releasesCount + "\n}\n";
    }
}
