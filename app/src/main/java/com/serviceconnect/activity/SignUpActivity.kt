package com.serviceconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import com.serviceconnect.helper.Utils
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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


    private fun getEditTextData() {

        ed_first_name.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_first_name.text.toString().length != 0){
                    ed_first_name.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_first_name.setBackgroundResource(R.drawable.edittext_rounded_rect)
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
                }
                else{
                    ed_last_name.setBackgroundResource(R.drawable.edittext_rounded_rect)
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
                    ed_mobile_number.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_mobile_number.setBackgroundResource(R.drawable.edittext_rounded_rect)
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

        ed_confirm_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(ed_confirm_password.text.toString().length != 0){
                    ed_confirm_password.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_confirm_password.setBackgroundResource(R.drawable.edittext_rounded_rect)
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
                }
                else{
                    ed_referral_code.setBackgroundResource(R.drawable.edittext_rounded_rect)
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
            var intent = Intent(this, VerificationOTPActivity::class.java)
            startActivity(intent)
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
        else if(ed_referral_code.text.length == null){
            Utils.showToast(this, resources.getString(R.string.msg_empty_referral_code))
            return false
        }
        else if(!(ed_confirm_password.text.toString()).equals(ed_password.text.toString())){
            Utils.showToast(this, resources.getString(R.string.msg_not_same_pass))
            return false
        }
        return true
    }


}