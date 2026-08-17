package cu.uci.android.apklis.storage.repository.model;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ApiUser {
    private String avatar;
    private String email;
    private ArrayList<Integer> favorite_apps;
    private String first_name;
    private Integer id;
    private boolean is_active;
    private String last_name;
    private String password;
    private String phone_number;
    private String username;

    public ApiUser() {
    }

    public ApiUser(Integer num, String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, ArrayList<Integer> arrayList) {
        this.id = num;
        this.username = str;
        this.avatar = str2;
        this.first_name = str3;
        this.last_name = str4;
        this.is_active = z;
        this.phone_number = str5;
        this.email = str6;
        this.password = str7;
        this.favorite_apps = arrayList;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Integer> getFavorite_apps() {
        return this.favorite_apps;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getFullName() {
        return this.first_name + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.last_name;
    }

    public Integer getId() {
        return this.id;
    }

    public boolean getIs_active() {
        return this.is_active;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public String getUsername() {
        return this.username;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setFavorite_apps(ArrayList<Integer> arrayList) {
        this.favorite_apps = arrayList;
    }

    public void setFirst_name(String str) {
        this.first_name = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setIs_active(boolean z) {
        this.is_active = z;
    }

    public void setLast_name(String str) {
        this.last_name = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPhone_number(String str) {
        this.phone_number = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
