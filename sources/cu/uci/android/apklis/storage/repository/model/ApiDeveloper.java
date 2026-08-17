package cu.uci.android.apklis.storage.repository.model;

/* loaded from: classes.dex */
public class ApiDeveloper {
    private String avatar;
    private String description;
    private String first_name;
    private String fullname;
    private Integer id;
    private String last_name;
    private Integer user;
    private String username;

    public ApiDeveloper() {
    }

    public ApiDeveloper(Integer num, String str, String str2, String str3, String str4, String str5, String str6, Integer num2) {
        this.id = num;
        this.username = str;
        this.first_name = str2;
        this.last_name = str3;
        this.avatar = str4;
        this.description = str5;
        this.fullname = str6;
        this.user = num2;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getFullname() {
        return this.fullname;
    }

    public Integer getId() {
        return this.id;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public Integer getUser() {
        return this.user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFirst_name(String str) {
        this.first_name = str;
    }

    public void setFullname(String str) {
        this.fullname = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setLast_name(String str) {
        this.last_name = str;
    }

    public void setUser(Integer num) {
        this.user = num;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
