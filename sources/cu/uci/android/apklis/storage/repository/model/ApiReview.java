package cu.uci.android.apklis.storage.repository.model;

/* loaded from: classes.dex */
public class ApiReview {
    private Integer application;
    private String comment;
    private Integer id;
    private String published;
    private Integer rating;
    private ApiUser user;

    public ApiReview(Integer num, ApiUser apiUser, String str, Integer num2, String str2, Integer num3) {
        this.id = num;
        this.user = apiUser;
        this.comment = str;
        this.rating = num2;
        this.published = str2;
        this.application = num3;
    }

    public Integer getApplication() {
        return this.application;
    }

    public String getComment() {
        return this.comment;
    }

    public Integer getId() {
        return this.id;
    }

    public String getPublished() {
        return this.published;
    }

    public Integer getRating() {
        return this.rating;
    }

    public ApiUser getUser() {
        return this.user;
    }

    public void setApplication(Integer num) {
        this.application = num;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setPublished(String str) {
        this.published = str;
    }

    public void setRating(Integer num) {
        this.rating = num;
    }

    public void setUser(ApiUser apiUser) {
        this.user = apiUser;
    }
}
