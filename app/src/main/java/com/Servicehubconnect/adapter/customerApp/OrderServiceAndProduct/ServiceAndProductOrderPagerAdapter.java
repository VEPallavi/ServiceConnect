package com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.Servicehubconnect.fragment.customerApp.ServiceAndProductOrderMenuFragment;

import java.util.ArrayList;



public class ServiceAndProductOrderPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    ArrayList<String> tabTitle;
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();
    private final ArrayList<String> fragmentTitleList = new ArrayList<>();


    public ServiceAndProductOrderPagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> tabTitle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitle = tabTitle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
       // return new ServiceAndProductOrderMenuFragment().newInstance(tabTitle.get(position));
    }


    @Override
    public int getCount() {
       // return mNumOfTabs;
        return fragmentList.size();
    }



    public void addFragment(Fragment fragment, String title) {

        fragmentList.add(fragment);

        fragmentTitleList.add(title);
    }


}
