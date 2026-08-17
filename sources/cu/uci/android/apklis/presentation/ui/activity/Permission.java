package cu.uci.android.apklis.presentation.ui.activity;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class Permission {
    private String description;
    private Drawable icon;
    private String label;
    private String name;

    public Permission(Drawable drawable, String str, String str2, String str3) {
        this.icon = drawable;
        this.name = str;
        this.label = str2;
        this.description = str3;
    }

    public String getDescription() {
        return this.description;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
