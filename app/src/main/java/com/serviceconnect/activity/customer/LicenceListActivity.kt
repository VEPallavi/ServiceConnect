package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.LicenceListAdapter
import kotlinx.android.synthetic.main.customer_activity_licence_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class LicenceListActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: LicenceListAdapter?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_licence_list)
         initViews()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    private fun initViews() {
        tv_title.text = "Licence"
        rv_licence_list.layoutManager = GridLayoutManager(this, 2)
        adapter = LicenceListAdapter(this);
        rv_licence_list.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }


}