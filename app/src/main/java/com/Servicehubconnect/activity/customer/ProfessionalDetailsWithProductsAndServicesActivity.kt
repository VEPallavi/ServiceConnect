package com.Servicehubconnect.activity.customer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.OrderServiceAndProduct.ServiceAndProductOrderPagerAdapter
import com.Servicehubconnect.fragment.customerApp.ServiceAndProductOrderMenuFragment
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.CategoryInfo
import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ServiceAndProductListDataModal
import com.Servicehubconnect.viewModel.customer.ProfessionalDetailsWithProductsAndServicesViewModel
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_service_detail_and_order.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
import java.util.*
import kotlin.collections.ArrayList


class ProfessionalDetailsWithProductsAndServicesActivity: AppCompatActivity(), View.OnClickListener{
    var viewModel: ProfessionalDetailsWithProductsAndServicesViewModel?= null
    var tabs: TabLayout?= null
    var viewPager: ViewPager?= null
    var pagerAdapter: ServiceAndProductOrderPagerAdapter?= null
    var professionalId: String?= null
    var bussinessId: String?= null
    var tabTitle = ArrayList<String>()
    var allJsonData: JsonObject? = null
    var serviceAndProductListModal: ServiceAndProductListDataModal?= null
    var serviceAndProductList = ArrayList<ServiceAndProductListDataModal>()
    private var categoryList: ArrayList<CategoryInfo> = ArrayList<CategoryInfo>()
    var categoryInfoModal: CategoryInfo?= null
    var activity: Activity?= null
    var categoryName: String =""
    var categoryType: String =""
    var finalListHashMap: HashMap<String, java.util.ArrayList<CategoryInfo>> = LinkedHashMap<String, java.util.ArrayList<CategoryInfo>>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_service_detail_and_order)
        viewModel = ViewModelProviders.of(this).get(ProfessionalDetailsWithProductsAndServicesViewModel::class.java)

        activity =  this
        professionalId = intent.getStringExtra("professionalId")
        bussinessId = intent.getStringExtra("bussinessId")

        initViews()
        setOnClickListener()
        getProfessionalDetailsData()
        getProductAndServiceList()

    }

    private fun getProfessionalDetailsData() {
        viewModel!!.getProfessionalDetails(this, professionalId!!).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObj = it.getAsJsonObject("data")

                        if(dataObj.has("name") && !dataObj.get("name").isJsonNull){
                            tv_professionName.setText(dataObj.get("name").asString)
                        }

                        if(dataObj.has("bussinessName") && !dataObj.get("bussinessName").isJsonNull){
                            tv_businessName.setText(dataObj.get("bussinessName").asString)
                        }

                        if(dataObj.has("ratingAverage") && !dataObj.get("ratingAverage").isJsonNull){

                            tv_ratingValue.setText(""+dataObj.get("ratingAverage").asInt)
                        }

                        if(dataObj.has("totalRating") && !dataObj.get("totalRating").isJsonNull){
                            tv_ratingCount.setText("("+dataObj.get("totalRating").asInt + "Ratings)")
                        }

                        if(dataObj.has("totalComment") && !dataObj.get("totalComment").isJsonNull){
                            tv_commentsCount.setText(""+dataObj.get("totalComment").asInt + " Comments")
                        }



                        if(dataObj.has("bussiness_info") && dataObj.get("bussiness_info") is JsonObject){
                            var businessObj = dataObj.getAsJsonObject("bussiness_info")

                            if(businessObj.has("open_time") && businessObj.has("close_time")){

                                tv_open_and_close_time.setText(businessObj.get("open_time").asString
                                        +"-" + businessObj.get("close_time").asString)

                            }
                        }

                        if(dataObj.has("description") && !dataObj.get("description").isJsonNull){
                            tv_description.setText(dataObj.get("description").asString)
                        }

                    }

                }

            }
        })

    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.activities_tabs)
        tabs!!.setupWithViewPager(viewPager)


 /*       for (k in 0..9) {
            tabs!!.addTab(tabs!!.newTab().setText("" + k))
            tabTitle.add("P - " + k)
        }
        tabs!!.setTabTextColors(Color.parseColor("#898989"), Color.parseColor("#FF4081"))

        pagerAdapter = ServiceAndProductOrderPagerAdapter(supportFragmentManager, tabs!!.getTabCount(), tabTitle)
        viewPager!!.adapter = pagerAdapter
        viewPager!!.setOffscreenPageLimit(1)
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))


        if (tabs!!.getTabCount() == 2) {
            tabs!!.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabs!!.setTabMode(TabLayout.MODE_SCROLLABLE);
        }*/

    }

    private fun setOnClickListener() {
        tv_licence.setOnClickListener(this)
        tv_comment.setOnClickListener(this)
        tv_order.setOnClickListener(this)
        ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }

            R.id.tv_licence ->{
                var intent = Intent(this, LicenceListActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_comment ->{
                var intent = Intent(this, CommentListActivity::class.java)
                intent.putExtra("professionalId", professionalId)
                startActivity(intent)
            }
            R.id.tv_order ->{
                var intent = Intent(this, OrderPlaceActivity::class.java)
                startActivity(intent)
            }

        }
    }




    fun getProductAndServiceList(){
        viewModel!!.getProductAndServiceList(this, bussinessId!!).observe(this, Observer {

            if(it!= null){

                allJsonData = it

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){

                        val type = object : TypeToken<ArrayList<ServiceAndProductListDataModal>>() {}.type
                        var dataList = Gson().fromJson<ArrayList<ServiceAndProductListDataModal>>(it.get("data"), type)


                        if(dataList.size >0){
                            serviceAndProductList.addAll(dataList)

                            pagerAdapter = ServiceAndProductOrderPagerAdapter(supportFragmentManager)
                            viewPager!!.offscreenPageLimit = serviceAndProductList.size     // Used to maintain state of each page in viewpager

                            for (i in 0 until serviceAndProductList.size) {
                                categoryName = serviceAndProductList.get(i).category_name
                                categoryType = serviceAndProductList.get(i).category_type
                                categoryList = serviceAndProductList.get(i).info

                                pagerAdapter!!.addFragment(ServiceAndProductOrderMenuFragment(activity, categoryList, categoryType), categoryName)

                            }
                            viewPager!!.setAdapter(pagerAdapter)
                        }

                    }

                }


//                if(allJsonData!!.status == 200){
//
//                    if(allJsonData!!.data.size >0){
//                        serviceAndProductList.clear()
//                            for (i in 0 until allJsonData!!.data.size) {
//                                serviceAndProductListModal = allJsonData?.data?.get(i)
//                                serviceAndProductList.add(serviceAndProductListModal!!)
//
//
//                                for (i in 0 until serviceAndProductList.size) {
//                                    categoryList.clear()
//                                    try {
//                                        categoryName = serviceAndProductList.get(i).category_name
//                                        categoryList = serviceAndProductList.get(i).info
//                                        // categoryInfoModal = serviceAndProductList.get(i).info
//                                    } catch (e: Exception) {
//                                        e.printStackTrace()
//                                    }
//                                }
//                            }
//                            pagerAdapter!!.addFragment(ServiceAndProductOrderMenuFragment(activity, categoryList), categoryName)
//                    }
//                }
//                else{
//
//
//                }
            }
        })
    }


}