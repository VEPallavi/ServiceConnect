package com.Servicehubconnect.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.customer.SignUpViewModel
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: SignUpViewModel?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        getCountryCode()
        iv_back.setOnClickListener {
            finish()
        }
        setOnClickListener()
        getEditTextData()
    }

    private fun setOnClickListener() {
        iv_back.setOnClickListener(this)
        tv_signUp.setOnClickListener(this)
    }

    fun getCountryCode() {
        ccp_signUp.resetToDefaultCountry()
    }


    private fun getEditTextData() {

        ed_first_name.addTextChangedListener (object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun afterTextChanged(s: Editable?) {
                if(ed_first_name.text.toString().length != 0){
                    ed_first_name.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_firstName.setCardElevation(10F)
                    cv_firstName.radius = 37F
                    ed_first_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon_on, 0, 0, 0)
                }
                else{
                    ed_first_name.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_firstName.setCardElevation(15F)
                    cv_firstName.radius = 37F
                    ed_first_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon_off, 0, 0, 0)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        ed_last_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_last_name.text.toString().length != 0){
                    ed_last_name.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_lastName.setCardElevation(10F)
                    cv_lastName.radius = 37F
                    ed_last_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon_on, 0, 0, 0)
                }
                else{
                    ed_last_name.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_lastName.setCardElevation(15F)
                    cv_lastName.radius = 37F
                    ed_last_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon_off, 0, 0, 0)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        ed_mobile_number.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_mobile_number.text.toString().length != 0){
                    cl_mobileNumber.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    iv_phone.setImageResource(R.drawable.phone_icon_on)
                    iv_phone.setPadding(20,20,20,20)
                }
                else{
                    cl_mobileNumber.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    iv_phone.setImageResource(R.drawable.phone_icon_off)
                    iv_phone.setPadding(20,20,20,20)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        ed_emailID.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_emailID.text.toString().length != 0){
                    ed_emailID.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_emailId.setCardElevation(10F)
                    cv_emailId.radius = 37F
                    ed_emailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon_on, 0, 0, 0)
                }
                else{
                    ed_emailID.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_emailId.setCardElevation(15F)
                    cv_emailId.radius = 37F
                    ed_emailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon_off, 0, 0, 0)
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
                    cv_password.setCardElevation(10F)
                    cv_password.radius = 37F
                    ed_password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon_on, 0, 0, 0)
                }
                else{
                    ed_password.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_password.setCardElevation(15F)
                    cv_password.radius = 37F
                    ed_password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon_off, 0, 0, 0)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        ed_confirm_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_confirm_password.text.toString().length != 0){
                    ed_confirm_password.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_confirm_password.setCardElevation(10F)
                    cv_confirm_password.radius = 37F
                    ed_confirm_password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon_on, 0, 0, 0)
                }
                else{
                    ed_confirm_password.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_confirm_password.setCardElevation(15F)
                    cv_confirm_password.radius = 37F
                    ed_confirm_password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password_icon_off, 0, 0, 0)

                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



        ed_local_city.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_local_city.text.toString().length != 0){
                    ed_local_city.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_local_city.setCardElevation(10F)
                    cv_local_city.radius = 37F
                    ed_local_city.setCompoundDrawablesWithIntrinsicBounds(R.drawable.refferl_icon_on, 0, 0, 0)
                }
                else{
                    ed_local_city.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_local_city.setCardElevation(15F)
                    cv_local_city.radius = 37F
                    ed_local_city.setCompoundDrawablesWithIntrinsicBounds(R.drawable.refferl_icon_off, 0, 0, 0)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        ed_referral_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_referral_code.text.toString().length != 0){
                    ed_referral_code.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                    cv_referral_code.setCardElevation(10F)
                    cv_referral_code.radius = 37F
                    ed_referral_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.refferl_icon_on, 0, 0, 0)
                }
                else{
                    ed_referral_code.setBackgroundResource(R.drawable.edittext_rounded_rect_border)
                    cv_referral_code.setCardElevation(15F)
                    cv_referral_code.radius = 37F
                    ed_referral_code.setCompoundDrawablesWithIntrinsicBounds(R.drawable.refferl_icon_off, 0, 0, 0)
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
            R.id.iv_back->{
                finish()
            }
            R.id.tv_signUp ->{
                signupUser()
            }
        }
    }

    private fun signupUser() {

        if(checkValidations()){
            var name = ed_first_name.text.toString() + ed_last_name.text.toString()

            viewModel!!.signUpData(this
                    , name
                    , ed_emailID.text.toString()
                    , ed_password.text.toString()
                    , ed_mobile_number.text.toString()
                    , ed_local_city.text.toString()
                    , ed_referral_code.text.toString()
                    , "+"+ccp_signUp.selectedCountryCode).observe(this, Observer {

                if(it!= null){
                    if(it.has("status")){

                        if(it.get("status").asString.equals("200")){
                            if(it.has("message")){
                                Utils.showToast(this, it.get("message").asString )
                            }
                            var intent = Intent(this, VerificationOTPActivity::class.java)
                            intent.putExtra("email", ed_emailID.text.toString())
                            startActivity(intent)
                        }
                        else if(it.get("status").asString.equals("1")){
                            if(it.has("message")){
                                Utils.showToast(this, it.get("message").asString )
                            }
                            var intent = Intent(this, VerificationOTPActivity::class.java)
                            intent.putExtra("email", ed_emailID.text.toString())
                            startActivity(intent)
                        }
                        else if(it.get("status").asString.equals("0")){
                            if(it.has("message")){
                                Utils.showToast(this, it.get("message").asString)
                            }
                        }
                    }
                }
            })
        }
    }


    private fun checkValidations(): Boolean {
        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        else if (ed_first_name.text!!.length < 2) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_first_name))
            return false
        }
        else if (ed_last_name.text!!.length < 2) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_last_name))
            return false
        }
        else if (ed_mobile_number.text!!.length == 0) {
            Utils.showToast(this, resources.getString(R.string.msg_empty_mobile_no))
            return false
        }
        else if(ed_mobile_number.text!!.length < 6){
            Utils.showToast(this, resources.getString(R.string.msg_invalid_mobile_no))
            return false
        }
        else if (!Utils.isEmailValid(ed_emailID.text.toString())) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_email))
            return false
        }
        else if (ed_password.text!!.length == 0) {
            Utils.showToast(this, resources.getString(R.string.msg_empty_pass))
            return false
        }
        else if (ed_password.text!!.length < 6) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_pass))
            return false
        }
        else if(ed_local_city.text.length == null){
            Utils.showToast(this, "Please enter your local city." )
            return false
        }
        else if(!(ed_confirm_password.text.toString()).equals(ed_password.text.toString())){
            Utils.showToast(this, resources.getString(R.string.msg_not_same_pass))
            return false
        }
        return true
    }


}