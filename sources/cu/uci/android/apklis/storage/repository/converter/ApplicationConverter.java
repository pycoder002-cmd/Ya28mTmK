package cu.uci.android.apklis.storage.repository.converter;

import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.storage.repository.model.ApiApplication;
import cu.uci.android.apklis.storage.repository.model.ApiRelease;

/* loaded from: classes.dex */
public class ApplicationConverter {
    public static App[] convertToViewModel(ApiApplication[] apiApplicationArr) {
        App[] appArr = new App[apiApplicationArr.length];
        for (int i = 0; i < apiApplicationArr.length; i++) {
            ApiApplication apiApplication = apiApplicationArr[i];
            App app = new App();
            try {
                app.setId(apiApplication.getId());
                app.setIcon(apiApplication.getLast_release().getIcon());
                app.setName(apiApplication.getName());
                app.setCategory(apiApplication.getCategories());
                app.setPrice(apiApplication.getPrice().toString());
                app.setPackageName(apiApplication.getPackage_name());
                app.setRating(apiApplication.getRating().toString());
                app.setDescription(apiApplication.getDescription());
                app.setAuthor(apiApplication.getDeveloper().getFullname());
                app.setReviewsCount(apiApplication.getReviews_count());
                app.setDownloadCount(apiApplication.getDownload_count());
                app.setReviewsStart(new Integer[]{apiApplication.getReviews_star_1(), apiApplication.getReviews_star_2(), apiApplication.getReviews_star_3(), apiApplication.getReviews_star_4(), apiApplication.getReviews_star_5()});
                Release release = new Release();
                ApiRelease last_release = apiApplication.getLast_release();
                if (last_release != null) {
                    release.setDownloadUrl(last_release.getApk_file());
                    release.setSize(last_release.getHuman_readable_size());
                    release.setPackage_versionCode(last_release.getVersion_code().toString());
                    release.setPackage_versionName(last_release.getVersion_name());
                    release.setVersionSdkName(last_release.getVersion_sdk_name());
                    release.setSdkVersion(last_release.getVersion_sdk().toString());
                    release.setTargetSdkVersion(last_release.getVersion_target_sdk().toString());
                    release.setPublishedAt(last_release.getPublished());
                    release.setDownloadSha256(last_release.getSha256());
                    release.setChangelog(last_release.getChangelog());
                    String[] strArr = new String[last_release.getPermissions().length];
                    for (int i2 = 0; i2 < last_release.getPermissions().length; i2++) {
                        strArr[i2] = last_release.getPermissions()[i2].getHuman_readable_name();
                    }
                    release.setUses_permission(strArr);
                    String[] strArr2 = new String[last_release.getScreenshots().length];
                    for (int i3 = 0; i3 < last_release.getScreenshots().length; i3++) {
                        strArr2[i3] = last_release.getScreenshots()[i3].getImage();
                    }
                    release.setScreenshots(strArr2);
                    app.setLastRelease(release);
                }
            } catch (Exception e) {
                MainApp.log(ApplicationConverter.class.getName(), e);
            }
            appArr[i] = app;
        }
        return appArr;
    }
}
