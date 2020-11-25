package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.Automative_AutoRepairAdapter
import com.Servicehubconnect.callback.ItemListener
import com.Servicehubconnect.modal.customer.SubCategoriesModal
import kotlinx.android.synthetic.main.customer_activity_automative_autorepair.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class Automative_AutoRepairActivity : AppCompatActivity(), View.OnClickListener, ItemListener{
    var adapter: Automative_AutoRepairAdapter?= null
    var automativeAutorepairList = ArrayList<SubCategoriesModal>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_automative_autorepair)

        tv_title.text ="Automative/AutoRepair"
        setOnClickListener()

        var list1 = SubCategoriesModal(R.drawable.auto_repair_image, this.resources.getString(R.string.txt_auto_repair))
        var list2 = SubCategoriesModal(R.drawable.auto_body_work_image, this.resources.getString(R.string.txt_auto_body_work))
        var list3 = SubCategoriesModal(R.drawable.lac_image, this.resources.getString(R.string.txt_lube_auto_car))
        var list4 = SubCategoriesModal(R.drawable.tyre_repair_image, this.resources.getString(R.string.txt_tyre_reapir))
        var list5 = SubCategoriesModal(R.drawable.tow_image, this.resources.getString(R.string.txt_towing))
        var list6 = SubCategoriesModal(R.drawable.image_icon, this.resources.getString(R.string.txt_driving_school))
        var list7 = SubCategoriesModal(R.drawable.image_icon, "Car Rental")
        var list8 = SubCategoriesModal(R.drawable.image_icon, "Car Pick up and drop off at auto shop")

        automativeAutorepairList.add(list1)
        automativeAutorepairList.add(list2)
        automativeAutorepairList.add(list3)
        automativeAutorepairList.add(list4)
        automativeAutorepairList.add(list5)
        automativeAutorepairList.add(list6)
        automativeAutorepairList.add(list7)
        automativeAutorepairList.add(list8)

        rv_automative_autorepair.setHasFixedSize(true)
        rv_automative_autorepair.layoutManager = GridLayoutManager(this, 2)
        rv_automative_autorepair.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_automative_autorepair.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = Automative_AutoRepairAdapter(this, automativeAutorepairList, this)
        rv_automative_autorepair.adapter = adapter

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
        var dataModal = dataModel as SubCategoriesModal
    }


}