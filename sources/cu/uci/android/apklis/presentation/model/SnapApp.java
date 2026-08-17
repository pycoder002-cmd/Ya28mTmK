package cu.uci.android.apklis.presentation.model;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class SnapApp {
    private ArrayList<AppDetails> apps;
    private boolean hasMore;
    private String title;
    private int type;

    public SnapApp(int i, String str, ArrayList<AppDetails> arrayList, boolean z) {
        this.type = i;
        this.title = str;
        this.apps = arrayList;
        this.hasMore = z;
    }

    public ArrayList<AppDetails> getApps() {
        return this.apps;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getType() {
        return Integer.valueOf(this.type);
    }

    public boolean isHasMore() {
        return this.hasMore;
    }
}
