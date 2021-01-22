//package com.Servicehubconnect.activity.customer
//
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.GridLayoutManager
//import com.Servicehubconnect.R
//import com.Servicehubconnect.adapter.customerApp.HomeCareProductPickUpRideAdapter
//import com.Servicehubconnect.modal.customer.SubCategoriesModal
//import kotlinx.android.synthetic.main.customer_activity_homecare_productpickup_ride.*
//import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*
//
//
//class HomeCareProductPickUpRideActivity : AppCompatActivity(), View.OnClickListener{
//    var adapter: HomeCareProductPickUpRideAdapter?= null
//    var dataList= ArrayList<SubCategoriesModal>()
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.customer_activity_homecare_productpickup_ride)
//
//
//        initViews()
//        setOnClickListener()
//
//    }
//
//    private fun initViews() {
//        tv_title.text = "Home Care/Product PickUp/Wine/Ride/Restaurant/Grocery"
//
//        var list1 = SubCategoriesModal(R.drawable.tutoring_image, this.resources.getString(R.string.txt_tutoring))
//        var list2 = SubCategoriesModal(R.drawable.baby_sitter, this.resources.getString(R.string.txt_baby_sitter_child_care))
//        var list3 = SubCategoriesModal(R.drawable.nanny, this.resources.getString(R.string.txt_nanny))
//        var list4 = SubCategoriesModal(R.drawable.pet_care, this.resources.getString(R.string.txt_petcare_petwalk))
//        var list5 = SubCategoriesModal(R.drawable.senior_care, this.resources.getString(R.string.txt_senior_care))
//        var list6 = SubCategoriesModal(R.drawable.house_keeping, this.resources.getString(R.string.txt_house_keeping))
//        var list7 = SubCategoriesModal(R.drawable.product_pick_up, this.resources.getString(R.string.txt_product_pickup))
//        var list8 = SubCategoriesModal(R.drawable.ride_image, this.resources.getString(R.string.txt_ride))
//        var list9 = SubCategoriesModal(R.drawable.personal_driver, this.resources.getString(R.string.txt_personal_driver))
//        var list10 = SubCategoriesModal(R.drawable.cleaning_service, this.resources.getString(R.string.txt_cleaning_service))
//        var list11 = SubCategoriesModal(R.drawable.wine_image, this.resources.getString(R.string.txt_wine))
//        var list12 = SubCategoriesModal(R.drawable.grocery, "Grocery Delivery/Pick Up")
//        var list13 = SubCategoriesModal(R.drawable.restaurant, "Restaurants Delivery/Pick Up/Dine-In")
//        var list14 = SubCategoriesModal(R.drawable.product_store, this.resources.getString(R.string.txt_product_store))
//        var list15 = SubCategoriesModal(R.drawable.product_store,"Bodyguard/Security Guard")
//
//        dataList.add(list1)
//        dataList.add(list2)
//        dataList.add(list3)
//        dataList.add(list4)
//        dataList.add(list5)
//        dataList.add(list6)
//        dataList.add(list7)
//        dataList.add(list8)
//        dataList.add(list9)
//        dataList.add(list10)
//        dataList.add(list11)
//        dataList.add(list12)
//        dataList.add(list13)
//        dataList.add(list14)
//        dataList.add(list15)
//
//
//        rv_homeCare_productPickup_ride.setHasFixedSize(true)
//        rv_homeCare_productPickup_ride.layoutManager = GridLayoutManager(this, 2)
//        rv_homeCare_productPickup_ride.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        rv_homeCare_productPickup_ride.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
//        adapter = HomeCareProductPickUpRideAdapter(this, dataList)
//        rv_homeCare_productPickup_ride.adapter = adapter
//
//    }
//
//    private fun setOnClickListener() {
//        ivBack.setOnClickListener(this)
//    }
//
//    override fun onClick(v: View?) {
//        when(v?.id){
//            R.id.ivBack ->{
//                finish()
//            }
//        }
//    }
//
//
//}