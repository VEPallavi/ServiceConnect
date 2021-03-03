package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.SupportViewModel
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class SupportActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: SupportViewModel?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_support)
        viewModel = ViewModelProviders.of(this).get(SupportViewModel::class.java)

        tv_title.text = "Support"
        ivBack.setOnClickListener(this)


      //  getData()
    }

    private fun getData() {
        viewModel!!.getSupportData(this).observe(this, Observer {

            if(it!= null){

            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
        }
    }


}