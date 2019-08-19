package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param page
     * @return
     */
    @Override
    public Fragment getItem(int page) {
        if (page == 1) {
            return NeighbourFragment.newInstance("favoris");
        }
        return NeighbourFragment.newInstance("neighbours");
    }

    /**
     * get the number of pages
     * @return
     */

    @Override
    public int getCount() {
        return 2;
    }
}