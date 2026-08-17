package cu.uci.android.apklis.storage.repository.model;

/* loaded from: classes.dex */
public class ApiPermission {
    private String description;
    private String human_readable_name;
    private String icon;
    private Integer id;
    private String name;

    public ApiPermission(Integer num, String str, String str2, String str3, String str4) {
        this.id = num;
        this.name = str;
        this.human_readable_name = str2;
        this.description = str3;
        this.icon = str4;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHuman_readable_name() {
        return this.human_readable_name;
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

    public void setDescription(String str) {
        this.description = str;
    }

    public void setHuman_readable_name(String str) {
        this.human_readable_name = str;
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
