package com.serviceconnect.activity.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.MyRatingAndCommentAdapter
import kotlinx.android.synthetic.main.customer_activity_myrating_and_comment.*


class MyRatingAndCommentActivity : AppCompatActivity(){
    var adapter: MyRatingAndCommentAdapter?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_myrating_and_comment)

        rv_myRatingAndCommentList.setHasFixedSize(true)
        rv_myRatingAndCommentList.layoutManager = LinearLayoutManager(this)

        adapter = MyRatingAndCommentAdapter(this)
        rv_myRatingAndCommentList.adapter = adapter

    }


}