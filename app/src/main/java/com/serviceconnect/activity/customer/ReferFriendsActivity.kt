package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ReferFriendsActivity : AppCompatActivity(), View.OnClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_refer_friends)

        initViews()
    }

    private fun initViews() {
        tv_title.text ="Refer Friends"
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