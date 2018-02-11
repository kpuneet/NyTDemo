package com.puneet.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.puneet.android.fragments.SectionFragment;

public class SectionViewPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{ "Home", "Opinion", "World", "National" };

    public SectionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String category;

        Fragment fragment;
     /*    switch(position) {
             default:
             case 0:
                 category = "home";
                 fragment = SectionFragment.newInstance(category);
                 return fragment;
             case 1:
                 category = "opinion";
                 fragment = SectionFragment.newInstance(category);
                 return fragment;
             case 2:
                 category = "world";
                 fragment = SectionFragment.newInstance(category);
                 return fragment;
             case 3:
                 category = "national";
                 fragment = SectionFragment.newInstance(category);
                 return fragment;
        }*/
        if (position == 0) {
            category = "home";
            fragment = SectionFragment.newInstance(category);
            return fragment;

        } else if (position == 1) {
            category = "opinion";
            fragment = SectionFragment.newInstance(category);
            return fragment;

        } else if (position == 2) {
            category = "world";
            fragment = SectionFragment.newInstance(category);
            return fragment;

        } else {
            category = "national";
            fragment = SectionFragment.newInstance(category);
            return fragment;
        }
    }

    /** Informs the adapter of the total number of available fragments views */
    @Override
    public int getCount() {
        return 4;
    }

    /** Set tab title */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
