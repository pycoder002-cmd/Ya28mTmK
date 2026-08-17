package cu.uci.android.apklis.storage.repository.model;

import com.github.mikephil.charting.utils.Utils;
import com.google.gson.annotations.SerializedName;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;

/* loaded from: classes.dex */
public class ApiRelease {

    @SerializedName(HeaderConstants.PUBLIC)
    private Boolean _public;
    private String apk_file;
    private String changelog;
    private String human_readable_size;
    private String icon;
    private Integer id;
    private ApiPermission[] permissions;
    private Double price;
    private String published;
    private ApiScreenshot[] screenshots;
    private String sha256;
    private Long size;
    private Integer version_code;
    private String version_name;
    private Integer version_sdk;
    private String version_sdk_name;
    private Integer version_target_sdk;

    public ApiRelease(Integer num, String str, Double d, Integer num2, String str2, Integer num3, String str3, Integer num4, String str4, String str5, String str6, String str7, Long l, String str8, Boolean bool, ApiPermission[] apiPermissionArr, ApiScreenshot[] apiScreenshotArr) {
        this.id = num;
        this.human_readable_size = str;
        this.price = d;
        this.version_code = num2;
        this.version_name = str2;
        this.version_sdk = num3;
        this.version_sdk_name = str3;
        this.version_target_sdk = num4;
        this.published = str4;
        this.sha256 = str5;
        this.changelog = str6;
        this.apk_file = str7;
        this.size = l;
        this.icon = str8;
        this._public = bool;
        this.permissions = apiPermissionArr;
        this.screenshots = apiScreenshotArr;
    }

    public String getApk_file() {
        return this.apk_file;
    }

    public String getChangelog() {
        return this.changelog;
    }

    public String getHuman_readable_size() {
        return this.human_readable_size;
    }

    public String getIcon() {
        return this.icon;
    }

    public Integer getId() {
        return this.id;
    }

    public ApiPermission[] getPermissions() {
        return this.permissions;
    }

    public Double getPrice() {
        return this.price == null ? Double.valueOf(Utils.DOUBLE_EPSILON) : this.price;
    }

    public String getPublished() {
        return this.published;
    }

    public ApiScreenshot[] getScreenshots() {
        return this.screenshots;
    }

    public String getSha256() {
        return this.sha256;
    }

    public Long getSize() {
        return this.size;
    }

    public Integer getVersion_code() {
        return this.version_code;
    }

    public String getVersion_name() {
        return this.version_name;
    }

    public Integer getVersion_sdk() {
        return this.version_sdk;
    }

    public String getVersion_sdk_name() {
        return this.version_sdk_name;
    }

    public Integer getVersion_target_sdk() {
        return this.version_target_sdk;
    }

    public Boolean get_public() {
        return this._public;
    }

    public void setApk_file(String str) {
        this.apk_file = str;
    }

    public void setChangelog(String str) {
        this.changelog = str;
    }

    public void setHuman_readable_size(String str) {
        this.human_readable_size = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public void setPermissions(ApiPermission[] apiPermissionArr) {
        this.permissions = apiPermissionArr;
    }

    public void setPrice(Double d) {
        this.price = d;
    }

    public void setPublished(String str) {
        this.published = str;
    }

    public void setScreenshots(ApiScreenshot[] apiScreenshotArr) {
        this.screenshots = apiScreenshotArr;
    }

    public void setSha256(String str) {
        this.sha256 = str;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public void setVersion_code(Integer num) {
        this.version_code = num;
    }

    public void setVersion_name(String str) {
        this.version_name = str;
    }

    public void setVersion_sdk(Integer num) {
        this.version_sdk = num;
    }

    public void setVersion_sdk_name(String str) {
        this.version_sdk_name = str;
    }

    public void setVersion_target_sdk(Integer num) {
        this.version_target_sdk = num;
    }

    public void set_public(Boolean bool) {
        this._public = bool;
    }
}
