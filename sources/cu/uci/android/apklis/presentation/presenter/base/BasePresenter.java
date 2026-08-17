package cu.uci.android.apklis.presentation.presenter.base;

/* loaded from: classes.dex */
public interface BasePresenter {
    void destroy();

    void onError(String str);

    void pause();

    void resume();

    void stop();
}
