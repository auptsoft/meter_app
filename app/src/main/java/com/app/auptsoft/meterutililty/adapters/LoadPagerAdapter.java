package com.app.auptsoft.meterutililty.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.fragments.LoadFragment;
import com.app.auptsoft.meterutililty.model.Load;

/**
 * Created by Administrator on 12/9/2018.
 */

public class LoadPagerAdapter extends FragmentStatePagerAdapter {

    public LoadPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Load.getLoadNames(AppState.allLoads).get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return LoadFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return AppState.allLoads.size();
    }
}
