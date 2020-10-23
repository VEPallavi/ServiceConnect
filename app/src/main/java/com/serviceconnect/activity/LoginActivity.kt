package com.serviceconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import com.serviceconnect.activity.customer.HomeActivity
import com.serviceconnect.helper.Utils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setOnClickListener()
        getEditTextData()
    }

    private fun setOnClickListener() {
        tv_signUp.setOnClickListener(this)
        tv_login.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
    }


    private fun getEditTextData() {

        ed_email.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_email.text.toString().length != 0){
                    ed_email.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_email.setBackgroundResource(R.drawable.edittext_rounded_rect)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        ed_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_password.text.toString().length != 0){
                    ed_password.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_password.setBackgroundResource(R.drawable.edittext_rounded_rect)
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

            R.id.tv_signUp->{
                var intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_forgot_password->{
                var intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_login ->{
                loginInUser()
            }
        }
    }

    private fun loginInUser() {
        if(checkValidation()){
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun checkValidation(): Boolean {

        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        else if (!Utils.isEmailValid(ed_email.text.toString())) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_email))
            return false
        } else if (ed_password.text!!.toString().length == 0) {
            Utils.showToast(this, resources.getString(R.string.msg_empty_pass))
            return false
        } else if (ed_password.text!!.toString().length < 6) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_pass))
            return false
        }
        return true
    }


}