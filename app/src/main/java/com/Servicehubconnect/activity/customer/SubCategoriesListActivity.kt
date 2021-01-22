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
import com.Servicehubconnect.adapter.customerApp.DashboardAdapterCustomer
import com.Servicehubconnect.adapter.customerApp.SubCategoriesListAdapter
import com.Servicehubconnect.callback.ItemListener
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.modal.customer.CategoryListDataModel
import com.Servicehubconnect.modal.customer.SubCategoriesModal
import com.Servicehubconnect.viewModel.customer.SubCategoriesListViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_salon_beauty.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import java.lang.Exception

class SubCategoriesListActivity : AppCompatActivity(), View.OnClickListener, ItemListener {
    var adapter: SubCategoriesListAdapter?= null
    var subCategoryList = ArrayList<SubCategoriesModal>()
    var viewModel: SubCategoriesListViewModel?=  null
    var categoryId: String?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_salon_beauty)
        viewModel = ViewModelProviders.of(this).get(SubCategoriesListViewModel::class.java)

        try {
            if(intent!= null){
                categoryId = intent.getStringExtra("categoryId")
            }

        }catch (e: Exception){
            e.printStackTrace()
        }

        tv_title.text = "Salon & Beauty"
        setOnClickListener()
        getData()

//        var list1 = SubCategoriesModal(R.drawable.hair_beautysalon_icon, this.resources.getString(R.string.txt_hair_beauty_salon))
//        var list2 = SubCategoriesModal(R.drawable.barbershop_and_hair_removal_icon, this.resources.getString(R.string.txt_barber_shop_hair_removal))
//        var list3 = SubCategoriesModal(R.drawable.nail_slon, this.resources.getString(R.string.txt_nail_salon_eyebrows_lashes))
//        var list4 = SubCategoriesModal(R.drawable.tattooshop_piercing_icon, this.resources.getString(R.string.txt_tattoo_shop_piercing))
//        var list5 = SubCategoriesModal(R.drawable.makeup_weddingmakupartis_icon, this.resources.getString(R.string.txt_makeup_wedding_makeup_artist))
//        var list6 = SubCategoriesModal(R.drawable.dentist_physicaltherapy_icon, this.resources.getString(R.string.txt_dentist_and_physical_therapy))
//        var list7 = SubCategoriesModal(R.drawable.image_icon, this.resources.getString(R.string.txt_day_spa_and_massage))
//        var list8 = SubCategoriesModal(R.drawable.petservices_icon, this.resources.getString(R.string.txt_pet_services))
//        var list9 = SubCategoriesModal(R.drawable.image_icon, this.resources.getString(R.string.txt_braiding_hair_style))
//
//        subCategoryList.add(list1)
//        subCategoryList.add(list2)
//        subCategoryList.add(list3)
//        subCategoryList.add(list4)
//        subCategoryList.add(list5)
//        subCategoryList.add(list6)
//        subCategoryList.add(list7)
//        subCategoryList.add(list8)
//        subCategoryList.add(list9)


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

        Log.e("<<< ", dataModel.name)
        var intent = Intent(this, SetLocationActivity::class.java)
        intent.putExtra("serviceName", dataModel.name)
        startActivity(intent)
    }


}