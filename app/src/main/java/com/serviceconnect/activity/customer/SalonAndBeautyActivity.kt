package com.serviceconnect.activity.customer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.SalonAndBeautyAdapter
import com.serviceconnect.callback.ItemListener
import com.serviceconnect.modal.customer.SubCategoriesModal
import kotlinx.android.synthetic.main.customer_activity_salon_beauty.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*

class SalonAndBeautyActivity : AppCompatActivity(), View.OnClickListener, ItemListener {
    var adapter: SalonAndBeautyAdapter?= null
    var salonBeautyList = ArrayList<SubCategoriesModal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_salon_beauty)

        tv_title.text = "Salon & Beauty"
        setOnClickListener()

        var list1 = SubCategoriesModal(R.drawable.hair_beautysalon_icon, this.resources.getString(R.string.txt_hair_beauty_salon))
        var list2 = SubCategoriesModal(R.drawable.barbershop_and_hair_removal_icon, this.resources.getString(R.string.txt_barber_shop_hair_removal))
        var list3 = SubCategoriesModal(R.drawable.nail_slon, this.resources.getString(R.string.txt_nail_salon_eyebrows_lashes))
        var list4 = SubCategoriesModal(R.drawable.tattooshop_piercing_icon, this.resources.getString(R.string.txt_tattoo_shop_piercing))
        var list5 = SubCategoriesModal(R.drawable.makeup_weddingmakupartis_icon, this.resources.getString(R.string.txt_makeup_wedding_makeup_artist))
        var list6 = SubCategoriesModal(R.drawable.dentist_physicaltherapy_icon, this.resources.getString(R.string.txt_dentist_and_physical_therapy))
        var list7 = SubCategoriesModal(R.drawable.image_icon, this.resources.getString(R.string.txt_day_spa_and_massage))
        var list8 = SubCategoriesModal(R.drawable.petservices_icon, this.resources.getString(R.string.txt_pet_services))
        var list9 = SubCategoriesModal(R.drawable.image_icon, this.resources.getString(R.string.txt_braiding_hair_style))

        salonBeautyList.add(list1)
        salonBeautyList.add(list2)
        salonBeautyList.add(list3)
        salonBeautyList.add(list4)
        salonBeautyList.add(list5)
        salonBeautyList.add(list6)
        salonBeautyList.add(list7)
        salonBeautyList.add(list8)
        salonBeautyList.add(list9)


        rv_salon_and_beauty.setHasFixedSize(true)
        rv_salon_and_beauty.layoutManager = GridLayoutManager(this, 2)
        rv_salon_and_beauty.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_salon_and_beauty.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = SalonAndBeautyAdapter(this, salonBeautyList, this)
        rv_salon_and_beauty.adapter = adapter

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

        var intent = Intent(this, SetLocationActivity::class.java)
        startActivity(intent)
    }


}