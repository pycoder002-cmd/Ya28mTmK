package cu.uci.android.apklis.domain.model;

import android.support.annotation.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class RxBus {
    private static final BehaviorSubject<Object> behaviorSubject = BehaviorSubject.create();

    public static void publish(@NonNull Object obj) {
        behaviorSubject.onNext(obj);
    }

    public static Disposable subscribe(@NonNull Consumer<Object> consumer) {
        return behaviorSubject.subscribe((io.reactivex.functions.Consumer<? super Object>) consumer);
    }
}
