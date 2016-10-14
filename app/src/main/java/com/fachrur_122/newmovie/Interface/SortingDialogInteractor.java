package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.SortType;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface SortingDialogInteractor {

    int getSelectedSortingOption();

    void setSortingOption(SortType sortType);

}
