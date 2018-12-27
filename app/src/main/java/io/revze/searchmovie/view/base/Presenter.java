package io.revze.searchmovie.view.base;

public interface Presenter<T extends View> {
    void onAttach(T View);

    void onDetach();
}
