package cu.uci.android.apklis.storage.repository.converter;

import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;

/* loaded from: classes.dex */
public class CategoryConverter {
    public static Category[] convertToViewModel(ApiCategory[] apiCategoryArr) {
        Category[] categoryArr = new Category[apiCategoryArr.length];
        for (int i = 0; i < apiCategoryArr.length; i++) {
            ApiCategory apiCategory = apiCategoryArr[i];
            Category category = new Category();
            category.setId(apiCategory.getId());
            category.setName(apiCategory.getName());
            category.setIcon(apiCategory.getIcon());
            category.setGroup(apiCategory.getGroup());
            categoryArr[i] = category;
        }
        return categoryArr;
    }
}
