package com.serviceconnect.activity.customer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.TimeListAdapter
import kotlinx.android.synthetic.main.customer_activity_order_place.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class OrderPlaceActivity : AppCompatActivity(), View.OnClickListener{
    var timeListAdapter : TimeListAdapter?= null
    var dialog: Dialog ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_order_place)

        initViews()
        setOnClickListener()
        rv_time_list.setHasFixedSize(true)
        rv_time_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        timeListAdapter = TimeListAdapter()
        rv_time_list.adapter = timeListAdapter

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        tv_order_now.setOnClickListener(this)
        tv_apply_coupon.setOnClickListener(this)
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

            }
            R.id.tv_order_now ->{
                openSuccessDialog()
            }

        }
    }

    private fun openSuccessDialog() {
        dialog = Dialog(this)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.customer_dialog_order_successful)
        dialog?.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()

        val tv_ok: Button = dialog?.findViewById(R.id.tv_ok)!!

        tv_ok.setOnClickListener {
            dialog?.dismiss()
        }
    }


}