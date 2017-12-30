package truthstick.sample.mvp;

import android.support.annotation.CallSuper;

public abstract class BasePresenter<View> implements MvpPresenter<View> {
    private View view;

    @Override
    @CallSuper
    public void takeView(View view) {
        this.view = view;
    }

    @Override
    @CallSuper
    public void dropView() {
        view = null;
    }

    protected final View view() {
        return view;
    }
}
