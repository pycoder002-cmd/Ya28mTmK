package cu.uci.android.apklis.domain.model;

import android.support.media.ExifInterface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cu.uci.android.apklis.MainApp;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class Release {
    private String application_id;
    private String changelog;
    private String downloadSha256;
    private String downloadUrl;
    private String id;
    private String package_name;
    private String package_versionCode;
    private String package_versionName;
    private String publishedAt;
    private String[] screenshots;
    private String sdkVersion;
    private String size;
    private String targetSdkVersion;
    private String[] uses_permission;
    private String versionSdkName;

    public Release() {
    }

    public Release(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String[] strArr, String str12, String[] strArr2, String str13) {
        this.id = str;
        this.application_id = str2;
        this.package_name = str3;
        this.package_versionCode = str4;
        this.package_versionName = str6;
        this.sdkVersion = str7;
        this.versionSdkName = str5;
        this.targetSdkVersion = str8;
        this.publishedAt = str9;
        this.downloadUrl = str10;
        this.downloadSha256 = str11;
        this.uses_permission = strArr;
        this.size = str12;
        this.screenshots = strArr2;
        this.changelog = str13;
    }

    public String getApplication_id() {
        return this.application_id;
    }

    public String getChangelog() {
        return this.changelog;
    }

    public String getDownloadSha256() {
        return this.downloadSha256;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getId() {
        return this.id;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    public String getPackage_versionCode() {
        return this.package_versionCode;
    }

    public String getPackage_versionName() {
        return this.package_versionName;
    }

    public String getPublishedAt() {
        return this.publishedAt;
    }

    public String getPublishedAtFormated() {
        String str = this.publishedAt;
        String[] split = this.publishedAt.split(ExifInterface.GPS_DIRECTION_TRUE);
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

    public String[] getScreenshots() {
        return this.screenshots;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSize() {
        return this.size;
    }

    public String getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    public String getUsesPermissionString() {
        StringBuilder sb = new StringBuilder();
        if (this.uses_permission != null && this.uses_permission.length > 0) {
            StringBuilder sb2 = sb;
            for (String str : this.uses_permission) {
                String[] split = str.split("\\.");
                if (split.length > 0) {
                    str = split[split.length - 1].toLowerCase().replace("_", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                String substring = str.substring(0, 1);
                String str2 = substring.toUpperCase() + str.substring(1);
                if (sb2.length() > 0) {
                    sb2.append(" / ");
                    sb2.append(str2);
                } else {
                    sb2 = new StringBuilder(str2);
                }
            }
            sb = sb2;
        }
        return sb.toString();
    }

    public String[] getUses_permission() {
        return this.uses_permission;
    }

    public String getVersionSdkName() {
        return this.versionSdkName;
    }

    public void setApplication_id(String str) {
        this.application_id = str;
    }

    public void setChangelog(String str) {
        this.changelog = str;
    }

    public void setDownloadSha256(String str) {
        this.downloadSha256 = str;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPackage_name(String str) {
        this.package_name = str;
    }

    public void setPackage_versionCode(String str) {
        this.package_versionCode = str;
    }

    public void setPackage_versionName(String str) {
        this.package_versionName = str;
    }

    public void setPublishedAt(String str) {
        this.publishedAt = str;
    }

    public void setScreenshots(String[] strArr) {
        this.screenshots = strArr;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public void setTargetSdkVersion(String str) {
        this.targetSdkVersion = str;
    }

    public void setUses_permission(String[] strArr) {
        this.uses_permission = strArr;
    }

    public void setVersionSdkName(String str) {
        this.versionSdkName = str;
    }
}
