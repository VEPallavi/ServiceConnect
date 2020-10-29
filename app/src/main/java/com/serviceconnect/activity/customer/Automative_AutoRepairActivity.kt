package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.Automative_AutoRepairAdapter
import kotlinx.android.synthetic.main.customer_activity_automative_autorepair.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class Automative_AutoRepairActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: Automative_AutoRepairAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_automative_autorepair)

        tv_title.text ="Automative/AutoRepair"

        setOnClickListener()
        rv_automative_autorepair.setHasFixedSize(true)
        rv_automative_autorepair.layoutManager = GridLayoutManager(this, 2)
        rv_automative_autorepair.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_automative_autorepair.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        adapter = Automative_AutoRepairAdapter(this)
        rv_automative_autorepair.adapter = adapter

    }

    private fun setOnClickListener() {
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back->{
                finish()
            }
        }
    }


}