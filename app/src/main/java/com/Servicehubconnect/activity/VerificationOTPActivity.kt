package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.VerificationOTPViewModel
import kotlinx.android.synthetic.main.activity_verification.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class VerificationOTPActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: VerificationOTPViewModel?= null
    var email: String?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        viewModel = ViewModelProviders.of(this).get(VerificationOTPViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        try {
            email = intent.getStringExtra("email")

        }catch (e: Exception){
            e.printStackTrace()
        }


        setOnClickListener()
        getEditText()
    }

    private fun setOnClickListener() {
        tv_verifyOtp.setOnClickListener(this)
        tv_reSendOTP.setOnClickListener(this)
        iv_back.setOnClickListener(this)
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

           R.id.iv_back ->{
               finish()
           }

           R.id.tv_verifyOtp ->{
               if(!Utils.isInternetAvailable(this)){
                   Utils.showToast(this, resources.getString(R.string.msg_no_internet))
               }
               else{
                   verifyOTP()
               }
           }

           R.id.tv_reSendOTP ->{
               et_otp_1.setText("")
               et_otp_2.setText("")
               et_otp_3.setText("")
               et_otp_4.setText("")
               if(!Utils.isInternetAvailable(this)){
                   Utils.showToast(this, resources.getString(R.string.msg_no_internet))
               }
               else{
                   resendOTP()
               }
           }

       }
    }

    private fun resendOTP() {
        viewModel?.resendOTP(this, email!!)?.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){
                    Utils.showToast(this, it.get("message").asString)
                }
                else if(it.has("status") && it.get("status").asString.equals("0")){
                    Utils.showToast(this, it.get("message").asString)
                }
            }
        })

    }

    private fun verifyOTP() {
        var otp = et_otp_1.text.toString()+et_otp_2.text.toString()+ et_otp_3.text.toString()+ et_otp_4.text.toString()

        viewModel?.getVerificationOTPData(this, email!!, otp)?.observe(this, Observer {

            if(it != null){

                if(it.has("status")){

                    if(it.get("status").asString.equals("200")){
                        Utils.showToast(this, it.get("message").asString)

                        Handler().postDelayed(Runnable {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()

                        }, 1000)

                    }
                    else if(it.get("status").asString.equals("0")){
                        Utils.showToast(this, it.get("message").asString)
                    }
                }
            }


        })
    }


}