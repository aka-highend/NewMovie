package com.fachrur_122.newmovie.view;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public enum SortType {

    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(2);

    private final int value;

    SortType(int value) { this.value = value; }

    public int getValue() { return value; }

}
