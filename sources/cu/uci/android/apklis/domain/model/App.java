package cu.uci.android.apklis.domain.model;

import com.google.gson.annotations.SerializedName;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;

/* loaded from: classes.dex */
public class App {
    private String author;
    private String author_url;
    private ApiCategory[] category;
    private String description;
    private Integer downloadCount;
    private String icon;

    @SerializedName("package_name")
    private Integer id;
    private Release lastRelease;
    private String name;
    private String packageName;
    private String price;
    private String rating;
    private Release[] releases;
    private Integer reviewsCount;
    private Integer[] reviewsStart;

    public App() {
    }

    public App(Integer num, String str, String str2, String str3, String str4, String str5, ApiCategory[] apiCategoryArr, String str6, Release[] releaseArr, Release release, String str7, String str8, Integer[] numArr, Integer num2, Integer num3) {
        this.id = num;
        this.name = str;
        this.packageName = str2;
        this.description = str3;
        this.icon = str4;
        this.author = str5;
        this.category = apiCategoryArr;
        this.author_url = str6;
        this.releases = releaseArr;
        this.lastRelease = release;
        this.rating = str7;
        this.price = str8;
        this.reviewsStart = numArr;
        this.reviewsCount = num2;
        this.downloadCount = num3;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getAuthor_url() {
        return this.author_url;
    }

    public ApiCategory[] getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getDownloadCount() {
        return this.downloadCount;
    }

    public String getIcon() {
        return this.icon;
    }

    public Integer getId() {
        return this.id;
    }

    public Release getLastRelease() {
        return this.lastRelease;
    }

    public String getName() {
        return this.name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPrice() {
        return this.price;
    }

    public String getRating() {
        return this.rating;
    }

    public Release[] getReleases() {
        return this.releases;
    }

    public Integer getReviewsCount() {
        return this.reviewsCount;
    }

    public Integer[] getReviewsStart() {
        return this.reviewsStart;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public void setAuthor_url(String str) {
        this.author_url = str;
    }

    public void setCategory(ApiCategory[] apiCategoryArr) {
        this.category = apiCategoryArr;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDownloadCount(Integer num) {
        this.downloadCount = num;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setLastRelease(Release release) {
        this.lastRelease = release;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setReleases(Release[] releaseArr) {
        this.releases = releaseArr;
    }

    public void setReviewsCount(Integer num) {
        this.reviewsCount = num;
    }

    public void setReviewsStart(Integer[] numArr) {
        this.reviewsStart = numArr;
    }
}
