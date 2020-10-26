package com.serviceconnect.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import kotlinx.android.synthetic.main.activity_verification.*



class VerificationOTPActivity : AppCompatActivity(), View.OnClickListener{




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        initViews()
    }

    private fun initViews() {
        setOnClickListener()
        getEditText()
    }

    private fun setOnClickListener() {
        tv_verifyOtp.setOnClickListener(this)
    }

    private fun getEditText() {

        et_otp_1.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {


            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

            }
            override fun afterTextChanged(s: Editable) {
                if (et_otp_1.getText().toString().length === 1) {
                    et_otp_2.requestFocus()
                }

            }
        })

        et_otp_2.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {


            }
            override fun afterTextChanged(s: Editable) {

                if (et_otp_2.getText().toString().length === 1) {
                    et_otp_3.requestFocus()
                }
                else if(et_otp_2.text.toString().length == 0){
                    et_otp_1.requestFocus()
                }
            }
        })


        et_otp_3.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(et_otp_3.text.toString().length == 1){
                    et_otp_4.requestFocus()
                }
                else if(et_otp_3.text.toString().length == 0){
                    et_otp_2.requestFocus()
                }
            }
        })


        et_otp_4.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if(et_otp_4.text.toString().length == 0){
                    et_otp_3.requestFocus()
                }
            }

        })

    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.tv_verifyOtp ->{

           }
       }
    }


}