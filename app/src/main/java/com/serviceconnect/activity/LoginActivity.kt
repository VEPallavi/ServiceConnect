package com.serviceconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.serviceconnect.R
import com.serviceconnect.activity.customer.HomeActivity
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

        ed_login.addTextChangedListener (object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                if(ed_login.text.toString().length != 0){
                    ed_login.setBackgroundResource(R.drawable.edittext_rounded_rect_blue)
                }
                else{
                    ed_login.setBackgroundResource(R.drawable.edittext_rounded_rect)
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
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }


}