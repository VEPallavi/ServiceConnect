package com.serviceconnect.activity.customer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.ProductItemsAdapter
import com.serviceconnect.adapter.customerApp.ServiceItemsAdapter
import com.serviceconnect.adapter.customerApp.TimeListAdapter
import kotlinx.android.synthetic.main.customer_activity_order_place.*
import kotlinx.android.synthetic.main.customer_activity_order_place.tv_apply_coupon
import kotlinx.android.synthetic.main.customer_dialog_apply_coupon.*
import kotlinx.android.synthetic.main.layout_price_detail.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class OrderPlaceActivity : AppCompatActivity(), View.OnClickListener{
    var timeListAdapter : TimeListAdapter?= null
    var successDialog: Dialog ?= null
    var serviceFeeDialog: Dialog ?= null
    var couponDialog: Dialog ?= null
    var serviceItemsAdapter: ServiceItemsAdapter?= null
    var productItemsAdapter: ProductItemsAdapter?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_order_place)

        initViews()
        setOnClickListener()
        rv_time_list.setHasFixedSize(true)
        rv_time_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        timeListAdapter = TimeListAdapter()
        rv_time_list.adapter = timeListAdapter



        rv_serviceList.setHasFixedSize(true)
        rv_serviceList.layoutManager = LinearLayoutManager(this)
        serviceItemsAdapter = ServiceItemsAdapter(this)
        rv_serviceList.adapter = serviceItemsAdapter


        rv_productList.setHasFixedSize(true)
        rv_productList.layoutManager = LinearLayoutManager(this)
        productItemsAdapter = ProductItemsAdapter(this)
        rv_productList.adapter = productItemsAdapter

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        tv_order_now.setOnClickListener(this)
        tv_apply_coupon.setOnClickListener(this)
        iv_serviceFee.setOnClickListener(this)
    }

    private fun initViews() {
        tv_title.text = "Service Details"
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_apply_coupon ->{
                openCouponDialog()
            }
            R.id.tv_order_now ->{
                openSuccessDialog()
            }
            R.id.iv_serviceFee ->{
                serviceFeeDialog()
            }

        }
    }

    private fun serviceFeeDialog() {
        serviceFeeDialog = Dialog(this)
        serviceFeeDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        serviceFeeDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        serviceFeeDialog?.setContentView(R.layout.customer_dialog_service_fee)
        serviceFeeDialog?.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        serviceFeeDialog?.setCanceledOnTouchOutside(false)
        serviceFeeDialog?.show()

        val ivCancel: ImageView = serviceFeeDialog?.findViewById(R.id.ivCancel)!!

        ivCancel.setOnClickListener {
            serviceFeeDialog?.dismiss()
        }
    }

    private fun openCouponDialog() {
        couponDialog = Dialog(this)
        couponDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        couponDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        couponDialog?.setContentView(R.layout.customer_dialog_apply_coupon)
        couponDialog?.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        couponDialog?.setCanceledOnTouchOutside(false)
        couponDialog?.show()

        val tv_apply_code: Button = couponDialog?.findViewById(R.id.tv_apply_code)!!
        val ivCancel : ImageView = couponDialog!!.findViewById(R.id.ivCancel)

        tv_apply_code.setOnClickListener {
            couponDialog?.dismiss()
        }


        ivCancel.setOnClickListener {
            couponDialog?.dismiss()
        }


    }

    private fun openSuccessDialog() {
        successDialog = Dialog(this)
        successDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        successDialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        successDialog?.setContentView(R.layout.customer_dialog_order_successful)
        successDialog?.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        successDialog?.setCanceledOnTouchOutside(false)
        successDialog?.show()

        val tv_ok: Button = successDialog?.findViewById(R.id.tv_ok)!!

        tv_ok.setOnClickListener {
            successDialog?.dismiss()
        }
    }


}