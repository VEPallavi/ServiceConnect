package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.MyRatingAndCommentAdapter
import kotlinx.android.synthetic.main.customer_activity_myrating_and_comment.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class MyRatingAndCommentActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: MyRatingAndCommentAdapter?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_myrating_and_comment)
        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "My Rating & Comment"

        rv_ratingAndCommentList.setHasFixedSize(true)
        rv_ratingAndCommentList.layoutManager = LinearLayoutManager(this)
        adapter = MyRatingAndCommentAdapter(this)
        rv_ratingAndCommentList.adapter = adapter
    }

    private fun setOnClickListener() {
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