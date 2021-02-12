package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.Servicehubconnect.R
import com.Servicehubconnect.adapter.customerApp.MyRatingAndCommentAdapter
import com.Servicehubconnect.modal.customer.ProfessionalListDataModel
import com.Servicehubconnect.modal.customer.RatingAndCommentModal
import com.Servicehubconnect.viewModel.customer.MyRatingAndCommentViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.customer_activity_myrating_and_comment.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class MyRatingAndCommentActivity : AppCompatActivity(), View.OnClickListener{
    var adapter: MyRatingAndCommentAdapter?= null
    var viewModel: MyRatingAndCommentViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_myrating_and_comment)
        viewModel = ViewModelProviders.of(this).get(MyRatingAndCommentViewModel::class.java)

        getData()
        initViews()
        setOnClickListener()
    }


    private fun getData() {
        viewModel!!.getRatingAndComments(this).observe(this, Observer {

            if(it != null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("data") && it.get("data") is JsonArray){

                        val type = object : TypeToken<ArrayList<RatingAndCommentModal>>() {}.type
                        var ratingAndCommentList = Gson().fromJson<ArrayList<RatingAndCommentModal>>(it.get("data"), type)

                        if(ratingAndCommentList.size >0){
                            tv_no_data_found.visibility = View.GONE
                            adapter = MyRatingAndCommentAdapter(this, ratingAndCommentList)
                            rv_ratingAndCommentList.adapter = adapter
                        }
                        else{
                            tv_no_data_found.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }

    private fun initViews() {
        tv_title.text = "My Rating & Comment"

        rv_ratingAndCommentList.setHasFixedSize(true)
        rv_ratingAndCommentList.layoutManager = LinearLayoutManager(this)

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