package com.serviceconnect.activity.customer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.serviceconnect.fragment.customerApp.CompletedMyServiceFragment
import com.serviceconnect.fragment.customerApp.UpComingMyServiceFragment


class MyServiceViewPager(fm: FragmentManager, internal var tabCount: Int): FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return UpComingMyServiceFragment()
            }
            1 -> {
                return CompletedMyServiceFragment()
            }
            else -> return null!!
        }
    }

    override fun getCount(): Int {
        return tabCount
    }


}