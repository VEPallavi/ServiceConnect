package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.CommentListAdapter
import com.Servicehubconnect.modal.customer.CommentListModal
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.Servicehubconnect.viewModel.customer.CommentListViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_comment_list.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*

class CommentListActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: CommentListAdapter ?= null
    var viewModel: CommentListViewModel ?= null
    var professionalId: String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_comment_list)
        viewModel = ViewModelProviders.of(this).get(CommentListViewModel::class.java)

        professionalId = intent.getStringExtra("professionalId")


        ivBack.setOnClickListener(this)
        tv_title.text = "Comments"

        rv_comment_list.setHasFixedSize(true)
        rv_comment_list.layoutManager = LinearLayoutManager(this)



        getData()


    }

    private fun getData() {
        viewModel!!.getCommentList(this, professionalId!!).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){
                        val type = object : TypeToken<ArrayList<CommentListModal>>() {}.type
                        var commentList = Gson().fromJson<ArrayList<CommentListModal>>(it.get("data"), type)



                        if(commentList.size >0 ){
                            adapter = CommentListAdapter(this, commentList)
                            rv_comment_list.adapter = adapter
                        }


                    }




                }


            }


        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack->{
                finish()
            }
        }
    }


}