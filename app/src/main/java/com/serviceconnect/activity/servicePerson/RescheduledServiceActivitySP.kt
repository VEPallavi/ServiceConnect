package com.serviceconnect.activity.servicePerson

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceconnect.R
import com.serviceconnect.adapter.customerApp.TimeListAdapter
import kotlinx.android.synthetic.main.sp_activity_rescheduled_service.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class RescheduledServiceActivitySP: AppCompatActivity(), View.OnClickListener{
    var adapter: TimeListAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sp_activity_rescheduled_service)
        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@RescheduledServiceActivitySP, msg, Toast.LENGTH_SHORT).show()
        }

        initViews()
        setOnClickListener()
    }

    private fun initViews() {
        tv_title.text = "Rescheduled Service"
        rv_time_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = TimeListAdapter()
        rv_time_list.adapter = adapter
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        iv_text.setOnClickListener(this)
        iv_call.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
        tv_submit.setOnClickListener(this)
        calendar_view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.iv_text ->{

            }
            R.id.iv_call ->{

            }
            R.id.tv_cancel ->{

            }
            R.id.tv_submit ->{

            }
            R.id.calendar_view ->{
              //  getRescheduleDate()
            }
        }
    }




}