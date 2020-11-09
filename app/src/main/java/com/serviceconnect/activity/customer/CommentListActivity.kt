package com.serviceconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.CommentListAdapter
import kotlinx.android.synthetic.main.customer_activity_comment_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*

class CommentListActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: CommentListAdapter ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_comment_list)

        ivBack.setOnClickListener(this)
        tv_title.text = "Comments"

        rv_comment_list.setHasFixedSize(true)
        rv_comment_list.layoutManager = LinearLayoutManager(this)
        adapter = CommentListAdapter(this)
        rv_comment_list.adapter = adapter


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack->{
                finish()
            }
        }
    }


}