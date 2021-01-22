package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.ForgotPasswordViewModel
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_forgot_password.ed_emailID
import kotlinx.android.synthetic.main.toolbar_layout.*


class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: ForgotPasswordViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        viewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel::class.java)
        setOnClickListener()
        getEditTextData()


    }

    private fun getData() {
        TODO("Not yet implemented")
    }

    private fun setOnClickListener() {
        tv_send_otp.setOnClickListener(this)
        iv_back.setOnClickListener(this)
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
                if(checkValidation()){
                    forgotPassword()

                }
            }
            R.id.iv_back->{
                finish()
            }
        }
    }



    fun forgotPassword(){

        viewModel?.getForgotPasswordData(this, ed_emailID.text.toString())?.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("message") && !it.get("message").isJsonNull){
                        Utils.showToast(this, it.get("message").asString)

                        var intent = Intent(this, VerificationOTPActivity::class.java)
                        intent.putExtra("email", ed_emailID.text.toString())
                        intent.putExtra("from", "from_forgotPassword")
                        startActivity(intent)
                    }
                }
                else {
                    if(it.has("message") && !it.get("message").isJsonNull){
                        Utils.showToast(this, it.get("message").asString)
                    }
                }
            }
            else{

            }
        })
    }



    fun checkValidation(): Boolean{
        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        else if (!Utils.isEmailValid(ed_emailID.text.toString())) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_email))
            return false
        }
        else if(ed_emailID.text.length == 0){
            Utils.showToast(this, "Please enter the emailID.")
            return false
        }
        return true
    }

}