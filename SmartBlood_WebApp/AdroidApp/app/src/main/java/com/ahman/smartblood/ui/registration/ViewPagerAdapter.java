package com.ahman.smartblood.ui.registration;

import androidx.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] childFragments;
    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new UserRegistrationFrag(), //0
                new DonorRegistrationFrag() //1
        };
    }
    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "User Details";
        } else if (position == 1) {
            title = "Donor Details";
        }
        return title;
    }
}