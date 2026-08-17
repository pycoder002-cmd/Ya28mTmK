package cu.uci.android.apklis.domain.model;

import com.blankj.utilcode.util.StringUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class UserAccount {
    private String accessToken;
    private String avatar;
    private String email;
    private ArrayList<Integer> favoriteApps;
    private String firstName;
    private Integer id;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String username;

    public UserAccount() {
    }

    public UserAccount(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ArrayList<Integer> arrayList) {
        this.id = num;
        this.username = str;
        this.firstName = str2;
        this.lastName = str3;
        this.email = str4;
        this.phoneNumber = str5;
        this.avatar = str6;
        this.accessToken = str7;
        this.password = str8;
        this.favoriteApps = arrayList;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Integer> getFavoriteApps() {
        return this.favoriteApps;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFullName() {
        String str = "";
        if (this.firstName != null && this.firstName.length() > 0) {
            str = "" + this.firstName;
        }
        if (this.lastName != null && this.lastName.length() > 0) {
            if (str.length() > 0) {
                str = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            str = str + this.lastName;
        }
        return str.length() == 0 ? this.username : str;
    }

    public Integer getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(this.username) || StringUtils.isEmpty(this.password);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setFavoriteApps(ArrayList<Integer> arrayList) {
        this.favoriteApps = arrayList;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String toString() {
        return "UserAccount{id=" + this.id + ", username='" + this.username + "', firstName='" + this.firstName + "', lastName='" + this.lastName + "', email='" + this.email + "', phoneNumber='" + this.phoneNumber + "', avatar='" + this.avatar + "', accessToken='" + this.accessToken + "', password='" + this.password + "', favoriteApps=" + this.favoriteApps + '}';
    }
}
