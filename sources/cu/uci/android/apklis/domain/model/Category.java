package cu.uci.android.apklis.domain.model;

/* loaded from: classes.dex */
public class Category {
    private String group;
    private String icon;
    private Integer id;
    private String name;

    public Category() {
    }

    public Category(Integer num, String str, String str2, String str3) {
        this.id = num;
        this.name = str;
        this.icon = str2;
        this.group = str3;
    }

    public String getGroup() {
        return this.group;
    }

    public String getIcon() {
        return this.icon;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setName(String str) {
        this.name = str;
    }
}
