package truthstick.sample.mvp;

public interface MvpPresenter<View> {

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    void takeView(View view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

}