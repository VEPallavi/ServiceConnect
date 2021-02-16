//package com.Servicehubconnect.adapter.customerApp
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//import com.Servicehubconnect.fragment.customerApp.ServiceAndOrderMenuFragment
//
//class ServiceAndProductOrderPagerAdapter(fm: FragmentManager, var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {
//
//    override fun getItem(position: Int): Fragment {
//        return ServiceAndOrderMenuFragment.newInstance(position)
//    }
//
//    override fun getCount(): Int {
//        return mNumOfTabs
//    }
//
//
//}