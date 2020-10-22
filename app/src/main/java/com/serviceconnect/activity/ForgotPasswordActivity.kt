package com.serviceconnect.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*


class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setOnClickListener()
        getEditTextData()

    }

    private fun setOnClickListener() {
        tv_send_otp.setOnClickListener(this)
    }


    private fun getEditTextData() {
        ed_emailID.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_emailID.text.toString().length != 0){
                    ed_emailID.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_emailID.setBackgroundResource(R.drawable.edittext_rounded_rect)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_send_otp->{

            }

        }
    }

}