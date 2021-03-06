package com.Servicehubconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.SubCategoriesListAdapter
import com.Servicehubconnect.callback.ItemListener
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.helper.customer.ConstantName
import com.Servicehubconnect.modal.customer.SubCategoriesModal
import com.Servicehubconnect.viewModel.customer.SubCategoriesListViewModel
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_subcategory_list.*


class SubCategoryListActivity : AppCompatActivity(), View.OnClickListener, ItemListener {
    var adapter: SubCategoriesListAdapter?= null
    var subCategoryList = ArrayList<SubCategoriesModal>()
    var viewModel: SubCategoriesListViewModel?=  null
    var categoryId: String?= null
    var categoryName: String?= null
    var isRequiredTwoLocation: Boolean?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_subcategory_list)
        viewModel = ViewModelProviders.of(this).get(SubCategoriesListViewModel::class.java)

        try {
            if(intent!= null){
                categoryId = intent.getStringExtra("categoryId")
                categoryName = intent.getStringExtra("categoryName")
                isRequiredTwoLocation = intent.getBooleanExtra("is_required_two_location", false)
            }

        }catch (e: Exception){
            e.printStackTrace()
        }

        tv_title.text = categoryName
        setOnClickListener()
        getData()

        rv_subCategoriesList.setHasFixedSize(true)
        rv_subCategoriesList.layoutManager = GridLayoutManager(this, 2)
        rv_subCategoriesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_subCategoriesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = SubCategoriesListAdapter(this, subCategoryList, this)
        rv_subCategoriesList.adapter = adapter

    }


    private fun getData() {
        viewModel?.getSubCategoryList(this, categoryId!!)!!.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){

                        val type = object : TypeToken<ArrayList<SubCategoriesModal>>() {}.type
                        var dataList = Gson().fromJson<ArrayList<SubCategoriesModal>>(it.get("data"), type)

                        if(dataList.size > 0){
                            subCategoryList.addAll(dataList)
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }
                else {
                    if(it.has("message") && !it.get("message").isJsonNull){
                        Utils.showToast(this, it.get("message").asString)
                    }
                }
            }
        })
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack->{
                finish()
            }
        }
    }

    override fun itemListener(dataModel: Any) {
        var dataModel = dataModel as SubCategoriesModal
        var locality = dataModel.locality

        if(locality.equals("global")){
            val intent = Intent(this, ProfessionalListActivity::class.java)
            intent.putExtra("serviceName", dataModel.name)
            intent.putExtra("subCategoryId", dataModel._id)
            intent.putExtra("SCREEN_TYPE", "SubCategoriesListActivity")
            startActivity(intent)
        }
        else if(locality.equals("local")){
            Log.e("<<< ", dataModel.name)
            var intent = Intent(this, SetLocationAddressActivity::class.java)
            intent.putExtra("serviceName", dataModel.name)
            intent.putExtra("subCategoryId", dataModel._id)
            intent.putExtra("is_required_two_location", isRequiredTwoLocation)
            startActivity(intent)
        }


    }


}