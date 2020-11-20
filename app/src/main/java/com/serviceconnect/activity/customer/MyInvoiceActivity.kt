package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.MyInvoice.ProductItemListAdapter
import com.serviceconnect.adapter.customerApp.MyInvoice.ServiceItemListAdapter
import kotlinx.android.synthetic.main.customer_activity_my_invoice.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class MyInvoiceActivity : AppCompatActivity(), View.OnClickListener{
    var serviceItemListAdapter: ServiceItemListAdapter?= null
    var productItemListAdapter: ProductItemListAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_my_invoice)
        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "My Invoice"

        rvServiceList.layoutManager = LinearLayoutManager(this)
        serviceItemListAdapter = ServiceItemListAdapter(this)
        rvServiceList.adapter = serviceItemListAdapter

        rvProductList.layoutManager = LinearLayoutManager(this)
        productItemListAdapter = ProductItemListAdapter(this)
        rvProductList.adapter = productItemListAdapter

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack -> {
                finish()
            }

        }
    }

}