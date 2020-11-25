package com.Servicehubconnect.activity.servicePerson

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.servicePerson.MyServiceAdapterSP
import kotlinx.android.synthetic.main.sp_activity_my_service.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class MyServiceActivitySP : AppCompatActivity(), View.OnClickListener{
    var adapter: MyServiceAdapterSP ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sp_activity_my_service)

        tv_title.text = "My Service"

        rv_myServiceList.layoutManager = LinearLayoutManager(this)
        adapter = MyServiceAdapterSP(this)
        rv_myServiceList.adapter = adapter
        setOnClickListener()
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }

}