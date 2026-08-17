package cu.uci.android.apklis.presentation.model;

import com.github.mikephil.charting.utils.Utils;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class AppView {
    private String icon_url;
    private Integer id;
    private String name;
    private String packageName;
    private Double price;
    private String rating;

    public AppView(Integer num, String str, String str2, String str3, String str4, String str5) {
        this.id = num;
        this.name = str2;
        this.packageName = str;
        this.rating = str3;
        this.icon_url = str4;
        try {
            this.price = Double.valueOf(Double.parseDouble(str5));
        } catch (Exception unused) {
            this.price = Double.valueOf(Utils.DOUBLE_EPSILON);
        }
    }

    public String getIcon_url() {
        return this.icon_url;
    }

    public Integer getId() {
        return this.id;
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

    public void setIcon_url(String str) {
        this.icon_url = str;
    }

    public void setId(Integer num) {
        this.id = num;
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
}
