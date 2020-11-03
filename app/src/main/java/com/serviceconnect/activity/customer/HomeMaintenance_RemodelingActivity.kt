package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.HomeMaintenance_RemodelingAdapter
import com.serviceconnect.modal.customer.SubCategoriesModal
import kotlinx.android.synthetic.main.customer_activity_home_maintenance_remodeling.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class HomeMaintenance_RemodelingActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: HomeMaintenance_RemodelingAdapter?= null
    var dataList= ArrayList<SubCategoriesModal>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_home_maintenance_remodeling)

        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "Home Maintenance/Remodeling"
        var list1 = SubCategoriesModal(R.drawable.roofing_siding_window_image, "Roofing, Siding & Window")
        var list2 = SubCategoriesModal(R.drawable.bathroom_kitchen_image,"Bathrooms & Kitchen")
        var list3 = SubCategoriesModal(R.drawable.concrete_cabinets_ceiling_carpenter_image, "Concrete, cabinets, ceiling & Carpenters")
        var list4 = SubCategoriesModal(R.drawable.cleaning_maid_image, "Cleaning & Maid Service")
        var list5 = SubCategoriesModal(R.drawable.landscaping_lawn_care_image,"Landscape, Lawn care, Sprinkling, Snow Removal")
        var list6 = SubCategoriesModal(R.drawable.plumbing_power_image, "Plumbing, Power-coating, Heating & Air Conditioning")
        var list7 = SubCategoriesModal(R.drawable.painting_staining_image, "Painting & Staining")
        var list8 = SubCategoriesModal(R.drawable.carpet_upholstery_cleaning_image, "Carpet & upholstery cleaning")
        var list9 = SubCategoriesModal(R.drawable.labor_junk_removal_image, "Labour & Junk Removal")
        var list10 = SubCategoriesModal(R.drawable.electrical_fans_image, "Electrical, Fans, Wall Covering & Tile")
        var list11 = SubCategoriesModal(R.drawable.power_washing_fences_image, "Power Washing & Fences")

        dataList.add(list1)
        dataList.add(list2)
        dataList.add(list3)
        dataList.add(list4)
        dataList.add(list5)
        dataList.add(list6)
        dataList.add(list7)
        dataList.add(list8)
        dataList.add(list9)
        dataList.add(list10)
        dataList.add(list11)



        rv_homeMaintenance_remodeling.setHasFixedSize(true)
        rv_homeMaintenance_remodeling.layoutManager = GridLayoutManager(this, 2)
        rv_homeMaintenance_remodeling.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_homeMaintenance_remodeling.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = HomeMaintenance_RemodelingAdapter(this, dataList)
        rv_homeMaintenance_remodeling.adapter = adapter

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }


}