package cu.uci.android.apklis.presentation.converter;

import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.presentation.model.AppView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AppViewModelConverter {
    public static AppView convertToViewModel(App app) {
        return new AppView(app.getId(), app.getName(), app.getPackageName(), app.getRating(), app.getIcon(), app.getPrice());
    }

    public static ArrayList<AppView> convertToViewModelList(App[] appArr) {
        ArrayList<AppView> arrayList = new ArrayList<>();
        for (App app : appArr) {
            arrayList.add(convertToViewModel(app));
        }
        return arrayList;
    }
}
