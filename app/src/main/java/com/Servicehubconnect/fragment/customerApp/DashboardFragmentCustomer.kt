package com.Servicehubconnect.fragment.customerApp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.*
import com.Servicehubconnect.adapter.customerApp.DashboardAdapterCustomer
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.helper.customer.ConstantFragmentName
import com.Servicehubconnect.modal.customer.CategoryListDataModel
import com.Servicehubconnect.viewModel.customer.DashboardViewModelCustomer
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_fragment_dashboard.*


class DashboardFragmentCustomer : Fragment(){
    var rvCategoriesList: RecyclerView?= null
    var viewModel: DashboardViewModelCustomer? = null
    var adapter: DashboardAdapterCustomer?= null



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.customer_fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModelCustomer::class.java)
        rvCategoriesList = view.findViewById(R.id.rv_categories_list)

        rvCategoriesList!!.setHasFixedSize(true)
        var linearLayoutManager = LinearLayoutManager(activity!!)
        rvCategoriesList!!.layoutManager = linearLayoutManager

        updateToolbar()

      //  getCategoresList()
        return view
    }

    private fun updateToolbar() {
        (activity as HomeActivityCustomer).setToolbarTitle(ConstantFragmentName.DASHBOARD_FRAGMENT_CUSTOMER)
        (activity as HomeActivityCustomer).setToolbarMenuVisibility(true)
        (activity as HomeActivityCustomer).setToolbarBackVisibility(false)
        (activity as HomeActivityCustomer).setToolbarNotificationVisibility(true)
    }



    fun getCategoresList(){
        viewModel?.getCategoryData(activity!!)?.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){

                        val type = object : TypeToken<ArrayList<CategoryListDataModel>>() {}.type
                        var categoryList = Gson().fromJson<ArrayList<CategoryListDataModel>>(it.get("data"), type)

                        if(categoryList.size > 0){
                            adapter = DashboardAdapterCustomer(activity!!, categoryList)
                            rvCategoriesList!!.adapter = adapter
                        }
                    }
                }
                else {
                    if(it.has("message") && !it.get("message").isJsonNull){
                        Utils.showToast(activity!!, it.get("message").asString)
                    }
                }
            }
        })
    }



}