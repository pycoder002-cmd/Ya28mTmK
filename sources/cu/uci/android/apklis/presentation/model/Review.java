package cu.uci.android.apklis.presentation.model;

import android.support.media.ExifInterface;
import cu.uci.android.apklis.MainApp;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class Review {
    private String avatar;
    private String comment;
    private String fullName;
    private Integer id;
    private String published;
    private Integer rating;

    public Review() {
    }

    public Review(Integer num, String str, String str2, Integer num2, String str3, String str4) {
        this.id = num;
        this.avatar = str;
        this.fullName = str2;
        this.rating = num2;
        this.comment = str3;
        this.published = str4;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getComment() {
        return this.comment;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Integer getId() {
        return this.id;
    }

    public String getPublished() {
        return this.published;
    }

    public String getPublishedAtFormated() {
        String str = this.published;
        String[] split = this.published.split(ExifInterface.GPS_DIRECTION_TRUE);
        if (split.length > 0) {
            str = split[0];
        }
        try {
            return new SimpleDateFormat("MMM d, yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (Exception e) {
            MainApp.log(e.getClass().getName(), e);
            return str;
        }
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setFullName(String str) {
        this.fullName = str;
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
}
