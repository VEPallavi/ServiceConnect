package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.viewModel.customer.ReferFriendsViewModel
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ReferFriendsActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: ReferFriendsViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_refer_friends)
        viewModel = ViewModelProviders.of(this).get(ReferFriendsViewModel::class.java)

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