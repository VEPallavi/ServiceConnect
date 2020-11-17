package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.SpecialityAdapter
import com.serviceconnect.modal.customer.SubCategoriesModal
import kotlinx.android.synthetic.main.customer_activity_speciality.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SpecialityActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: SpecialityAdapter?= null
    var dataList= ArrayList<SubCategoriesModal>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_speciality)

        initViews()

        rv_speciality.setHasFixedSize(true)
        rv_speciality.setHasFixedSize(true)
        rv_speciality.layoutManager = GridLayoutManager(this, 2)
        rv_speciality.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_speciality.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = SpecialityAdapter(this, dataList)
        rv_speciality.adapter = adapter

    }

    private fun initViews() {
        tv_title.text = "Speciality"
        var list1 = SubCategoriesModal(R.drawable.hair_beautysalon_icon, "Event Wait Staff")
        var list2 = SubCategoriesModal(R.drawable.barbershop_and_hair_removal_icon, "Event Musician")
        var list3 = SubCategoriesModal(R.drawable.nail_slon, "Event DJ")
        var list4 = SubCategoriesModal(R.drawable.tattooshop_piercing_icon, "Computer Repair")
        var list5 = SubCategoriesModal(R.drawable.makeup_weddingmakupartis_icon, "Catering")
        var list6 = SubCategoriesModal(R.drawable.dentist_physicaltherapy_icon, "Event Planner")
        var list7 = SubCategoriesModal(R.drawable.image_icon, "Wedding Planner")
        var list8 = SubCategoriesModal(R.drawable.petservices_icon, "Party Planner")
        var list9 = SubCategoriesModal(R.drawable.image_icon, "Alteration/Tailoring/Dry Cleaner")
        var list10 = SubCategoriesModal(R.drawable.image_icon, "Shoe Repair")

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