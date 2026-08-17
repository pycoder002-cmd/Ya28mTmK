package cu.uci.android.apklis.storage.repository.model;

import com.github.mikephil.charting.utils.Utils;
import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;

/* loaded from: classes.dex */
public class ApiApplication {

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public;
    private ApiCategory[] categories;
    private String description;
    private ApiDeveloper developer;
    private Integer download_count;
    private Integer id;
    private ApiRelease last_release;
    private String name;
    private String owner;
    private String package_name;
    private Double price;
    private Double rating;
    private Integer[] releases;
    private Integer releases_count;
    private Integer reviews_count;
    private Integer reviews_star_1;
    private Integer reviews_star_2;
    private Integer reviews_star_3;
    private Integer reviews_star_4;
    private Integer reviews_star_5;
    private String updated;

    public ApiApplication() {
    }

    public ApiApplication(Integer num, String str, String str2, Double d, String str3, String str4, Double d2, Boolean bool, String str5, ApiCategory[] apiCategoryArr, Integer[] numArr, ApiDeveloper apiDeveloper, ApiRelease apiRelease, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9) {
        this.id = num;
        this.name = str;
        this.package_name = str2;
        this.price = d;
        this.description = str3;
        this.updated = str4;
        this.rating = d2;
        this._public = bool;
        this.owner = str5;
        this.categories = apiCategoryArr;
        this.releases = numArr;
        this.developer = apiDeveloper;
        this.last_release = apiRelease;
        this.releases_count = num2;
        this.reviews_count = num3;
        this.reviews_star_1 = num4;
        this.reviews_star_2 = num5;
        this.reviews_star_3 = num6;
        this.reviews_star_4 = num7;
        this.reviews_star_5 = num8;
        this.download_count = num9;
    }

    public ApiCategory[] getCategories() {
        return this.categories;
    }

    public String getDescription() {
        return this.description;
    }

    public ApiDeveloper getDeveloper() {
        return this.developer;
    }

    public Integer getDownload_count() {
        return this.download_count;
    }

    public Integer getId() {
        return this.id;
    }

    public ApiRelease getLast_release() {
        return this.last_release;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    public Double getPrice() {
        return this.price == null ? Double.valueOf(Utils.DOUBLE_EPSILON) : this.price;
    }

    public Double getRating() {
        return this.rating;
    }

    public Integer[] getReleases() {
        return this.releases;
    }

    public Integer getReleases_count() {
        return this.releases_count;
    }

    public Integer getReviews_count() {
        return this.reviews_count;
    }

    public Integer getReviews_star_1() {
        return this.reviews_star_1;
    }

    public Integer getReviews_star_2() {
        return this.reviews_star_2;
    }

    public Integer getReviews_star_3() {
        return this.reviews_star_3;
    }

    public Integer getReviews_star_4() {
        return this.reviews_star_4;
    }

    public Integer getReviews_star_5() {
        return this.reviews_star_5;
    }

    public String getUpdated() {
        return this.updated;
    }

    public Boolean get_public() {
        return this._public;
    }

    public void setCategories(ApiCategory[] apiCategoryArr) {
        this.categories = apiCategoryArr;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDeveloper(ApiDeveloper apiDeveloper) {
        this.developer = apiDeveloper;
    }

    public void setDownload_count(Integer num) {
        this.download_count = num;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setLast_release(ApiRelease apiRelease) {
        this.last_release = apiRelease;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public void setPackage_name(String str) {
        this.package_name = str;
    }

    public void setPrice(Double d) {
        this.price = d;
    }

    public void setRating(Double d) {
        this.rating = d;
    }

    public void setReleases(Integer[] numArr) {
        this.releases = numArr;
    }

    public void setReleases_count(Integer num) {
        this.releases_count = num;
    }

    public void setReviews_count(Integer num) {
        this.reviews_count = num;
    }

    public void setReviews_star_1(Integer num) {
        this.reviews_star_1 = num;
    }

    public void setReviews_star_2(Integer num) {
        this.reviews_star_2 = num;
    }

    public void setReviews_star_3(Integer num) {
        this.reviews_star_3 = num;
    }

    public void setReviews_star_4(Integer num) {
        this.reviews_star_4 = num;
    }

    public void setReviews_star_5(Integer num) {
        this.reviews_star_5 = num;
    }

    public void setUpdated(String str) {
        this.updated = str;
    }

    public void set_public(Boolean bool) {
        this._public = bool;
    }
}
