package cu.uci.android.apklis.presentation.model;

import cu.uci.android.apklis.R;

/* loaded from: classes.dex */
public enum GroupType {
    RECOMMENDED_APPLICATIONS(0, R.string.recommended_applications_title),
    CUBAN_GAMES_APPLICATIONS(1, R.string.cuban_games_applications_title),
    LATEST_UPDATES(2, R.string.latest_updates_title),
    FASHION_GAME_APPLICATIONS(3, R.string.fashion_game_applications_title),
    RECENT_RELEASES(4, R.string.recent_releases_title),
    BEST_APPLICATIONS(5, R.string.best_applications_title),
    BEST_GAMES(6, R.string.best_games_title),
    TOOLS_UTILITIES(7, R.string.tools_utilities_title);

    private int resTitle;
    private int value;

    GroupType(int i, int i2) {
        this.value = i;
        this.resTitle = i2;
    }

    public static GroupType getTypeByValue(int i) {
        switch (i) {
            case 0:
                return RECOMMENDED_APPLICATIONS;
            case 1:
                return CUBAN_GAMES_APPLICATIONS;
            case 2:
                return LATEST_UPDATES;
            case 3:
                return FASHION_GAME_APPLICATIONS;
            case 4:
                return RECENT_RELEASES;
            case 5:
                return BEST_APPLICATIONS;
            case 6:
                return BEST_GAMES;
            case 7:
                return TOOLS_UTILITIES;
            default:
                return RECOMMENDED_APPLICATIONS;
        }
    }

    public int resTitle() {
        return this.resTitle;
    }

    public int value() {
        return this.value;
    }
}
