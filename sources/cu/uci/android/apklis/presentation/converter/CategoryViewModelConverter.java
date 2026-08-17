package cu.uci.android.apklis.presentation.converter;

import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.presentation.model.CategoryView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CategoryViewModelConverter {
    public static CategoryView convertToViewModel(Category category) {
        return new CategoryView(category.getId(), category.getName(), category.getIcon(), category.getGroup());
    }

    public static ArrayList<CategoryView> convertToViewModelList(Category[] categoryArr) {
        ArrayList<CategoryView> arrayList = new ArrayList<>();
        for (Category category : categoryArr) {
            arrayList.add(convertToViewModel(category));
        }
        return arrayList;
    }
}
