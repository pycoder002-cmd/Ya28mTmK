package cu.uci.android.apklis.presentation.model;

import com.github.mikephil.charting.utils.Utils;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes.dex */
public class AppDetails {
    private String author;
    private String author_url;
    private ApiCategory[] category;
    private String description;
    private Integer downloadCount;
    private String icon;
    private Integer id;
    private Release lastRelease;
    private String name;
    private String packageName;
    private Double price;
    private String rating;
    private Release[] releases;
    private Integer reviewsCount;
    private Integer[] reviewsStart;

    public AppDetails() {
    }

    public AppDetails(Integer num, String str, String str2, String str3, String str4, String str5, ApiCategory[] apiCategoryArr, String str6, Release[] releaseArr, String str7, String str8, Integer[] numArr, Integer num2, Integer num3) {
        this.id = num;
        this.name = str;
        this.packageName = str2;
        this.description = str3;
        this.icon = str4;
        this.author = str5;
        this.category = apiCategoryArr;
        this.author_url = str6;
        this.releases = releaseArr;
        this.rating = str7;
        this.reviewsStart = numArr;
        this.reviewsCount = num2;
        this.downloadCount = num3;
        try {
            this.price = Double.valueOf(Double.parseDouble(str8));
        } catch (Exception unused) {
            this.price = Double.valueOf(Utils.DOUBLE_EPSILON);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppDetails)) {
            return false;
        }
        AppDetails appDetails = (AppDetails) obj;
        return Objects.equals(getId(), appDetails.getId()) && Objects.equals(getName(), appDetails.getName()) && Objects.equals(getDescription(), appDetails.getDescription()) && Objects.equals(getPackageName(), appDetails.getPackageName()) && Objects.equals(getIcon(), appDetails.getIcon()) && Objects.equals(getAuthor(), appDetails.getAuthor()) && Arrays.equals(getCategory(), appDetails.getCategory()) && Objects.equals(getAuthor_url(), appDetails.getAuthor_url()) && Arrays.equals(getReleases(), appDetails.getReleases()) && Objects.equals(getLastRelease(), appDetails.getLastRelease()) && Arrays.equals(getReviewsStart(), appDetails.getReviewsStart()) && Objects.equals(getReviewsCount(), appDetails.getReviewsCount()) && Objects.equals(getDownloadCount(), appDetails.getDownloadCount()) && Objects.equals(getRating(), appDetails.getRating()) && Objects.equals(getPrice(), appDetails.getPrice());
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

    public String getExtension() {
        return this.lastRelease.getDownloadUrl().split("\\?")[0].endsWith(".apk") ? ".apk" : ".apklis";
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

    public Double getPrice() {
        return this.price;
    }

    public String getPriceString() {
        return this.price != null ? new DecimalFormat("##.##").format(this.price) : "0";
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

    public int hashCode() {
        return (31 * ((((Objects.hash(getId(), getName(), getDescription(), getPackageName(), getIcon(), getAuthor(), getAuthor_url(), getLastRelease(), getReviewsCount(), getDownloadCount(), getRating(), getPrice()) * 31) + Arrays.hashCode(getCategory())) * 31) + Arrays.hashCode(getReleases()))) + Arrays.hashCode(getReviewsStart());
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

    public void setPrice(Double d) {
        this.price = d;
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
