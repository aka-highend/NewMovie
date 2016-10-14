package com.fachrur_122.newmovie.sortMovies;

import com.fachrur_122.newmovie.view.SortType;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class SortingDialogInteractor implements com.fachrur_122.newmovie.Interface.SortingDialogInteractor {

    private SortingOptionStore mSortingOptionStore;

    public SortingDialogInteractor()
    {
        mSortingOptionStore = new SortingOptionStore();
    }


    @Override
    public int getSelectedSortingOption() {
        return mSortingOptionStore.getSelectedOption();
    }

    @Override
    public void setSortingOption(SortType sortType) {
        mSortingOptionStore.setSelectedOption(sortType);
    }

}
