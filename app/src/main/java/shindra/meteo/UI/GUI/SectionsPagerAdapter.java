package shindra.meteo.UI.GUI;

/**
 * Created by Guillaume on 14/11/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import shindra.meteo.CitiesManager;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private CitiesManager aManager;

    public SectionsPagerAdapter(FragmentManager fm, CitiesManager aManager) {
        super(fm);
        this.aManager = aManager;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1,aManager);
    }

    @Override
    public int getCount() {
        // Show pages
        return aManager.getCities().size();
    }



}