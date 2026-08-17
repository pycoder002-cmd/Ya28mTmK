package cu.uci.android.apklis.presentation.model;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class SnapCategory {
    private ArrayList<CategoryView> categories;
    private int icon;
    private String title;

    public SnapCategory(int i, String str, ArrayList<CategoryView> arrayList) {
        this.icon = i;
        this.title = str;
        this.categories = arrayList;
    }

    public ArrayList<CategoryView> getCategories() {
        return this.categories;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCategories(ArrayList<CategoryView> arrayList) {
        this.categories = arrayList;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
