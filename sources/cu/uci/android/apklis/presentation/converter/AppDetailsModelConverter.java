package cu.uci.android.apklis.presentation.converter;

import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AppDetailsModelConverter {
    public static AppDetails convertToViewModel(App app) {
        if (app == null) {
            return null;
        }
        Integer id = app.getId();
        String name = app.getName();
        String packageName = app.getPackageName();
        String description = app.getDescription();
        String icon = app.getIcon();
        String author = app.getAuthor();
        ApiCategory[] category = app.getCategory();
        String author_url = app.getAuthor_url();
        Release[] releases = app.getReleases();
        String rating = app.getRating();
        String price = app.getPrice();
        Release lastRelease = app.getLastRelease();
        AppDetails appDetails = new AppDetails(id, name, packageName, description, icon, author, category, author_url, releases, rating, price, app.getReviewsStart(), app.getReviewsCount(), app.getDownloadCount());
        appDetails.setLastRelease(lastRelease);
        return appDetails;
    }

    public static ArrayList<AppDetails> convertToViewModelList(App[] appArr) {
        ArrayList<AppDetails> arrayList = new ArrayList<>();
        for (App app : appArr) {
            arrayList.add(convertToViewModel(app));
        }
        return arrayList;
    }
}
