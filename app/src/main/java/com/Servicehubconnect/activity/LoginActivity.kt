package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.HomeActivity
import com.Servicehubconnect.activity.servicePerson.HomeActivitySP
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener{
    var showPassword: Boolean = false
    var viewModel: LoginViewModel?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        setOnClickListener()
        getEditTextData()
    }

    private fun setOnClickListener() {
        tv_signUp.setOnClickListener(this)
        tv_login.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        iv_password_toggle.setOnClickListener(this)
        iv_logo.setOnClickListener {
            var intent = Intent(this, HomeActivitySP::class.java)
            startActivity(intent)
        }
    }


    private fun getEditTextData() {

        ed_email.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_email.text.toString().length != 0){
                    ed_email.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_email.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
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
                    cl_password.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    cl_password.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
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
            R.id.iv_password_toggle ->{
                if (showPassword) {
                    ed_password.transformationMethod = PasswordTransformationMethod.getInstance()
                    iv_password_toggle.setImageResource(R.drawable.eye_icon_off)
                } else {
                    ed_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    iv_password_toggle.setImageResource(R.drawable.eye_icon_on)
                }
                ed_password.setSelection(ed_password.text!!.length)
                showPassword = !showPassword
            }
            R.id.tv_forgot_password->{
                var intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_login ->{
                if(checkValidation()){
                    loginInUser()
                }
            }
        }
    }

    private fun loginInUser() {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

//        viewModel?.loginUser(this
//                , ed_email.text.toString()
//                , ed_password.text.toString())?.observe(this, Observer {
//
//            if(it!= null){
//
//            }
//        })


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