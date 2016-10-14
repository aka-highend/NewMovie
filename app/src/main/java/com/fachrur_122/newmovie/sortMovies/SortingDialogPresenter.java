package com.fachrur_122.newmovie.sortMovies;

import com.fachrur_122.newmovie.Interface.SortingDialogView;
import com.fachrur_122.newmovie.view.SortType;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class SortingDialogPresenter implements com.fachrur_122.newmovie.Interface.SortingDialogPresenter {

    private SortingDialogView mSortingDialogView;
    private com.fachrur_122.newmovie.Interface.SortingDialogInteractor mSortingDialogInteractor;

    public SortingDialogPresenter (SortingDialogView sortingDialogView) {
        mSortingDialogView = sortingDialogView;
        mSortingDialogInteractor = new SortingDialogInteractor();
    }

    @Override
    public void setLastSavedOption() {
        int selectedOption = mSortingDialogInteractor.getSelectedSortingOption();

        if (selectedOption == SortType.MOST_POPULAR.getValue()) {
            mSortingDialogView.setPopularChecked();
        } else if (selectedOption == SortType.HIGHEST_RATED.getValue()) {
            mSortingDialogView.setHighestRatedChecked();
        } else {
            mSortingDialogView.setFavoritesChecked();
        }
    }

    @Override
    public void onPopularMoviesSelected() {
        mSortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR);
        mSortingDialogView.dismissDialog();
    }

    @Override
    public void onHighestRatedMoviesSelected() {
        mSortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED);
        mSortingDialogView.dismissDialog();
    }

    @Override
    public void onFavoritesSelected() {
        mSortingDialogInteractor.setSortingOption(SortType.FAVORITES);
        mSortingDialogView.dismissDialog();
    }

}
