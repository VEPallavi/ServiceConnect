package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.ResetPasswordViewModel
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: ResetPasswordViewModel?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        viewModel = ViewModelProviders.of(this).get(ResetPasswordViewModel::class.java)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back ->{
                finish()
            }
            R.id.tv_verify ->{
                if(checkValidations()){
                    resetPassword()
                }
            }
        }
    }

    private fun resetPassword() {
        viewModel?.resetPassword(this, ed_password.text.toString())?.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    if(it.has("message")){
                        Utils.showToast(this, it.get("message").asString)
                    }
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    if(it.has("message")){
                        Utils.showToast(this, it.get("message").asString)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        })
    }


    private fun checkValidations(): Boolean {
        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
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
        else if(!(ed_confirm_password.text.toString()).equals(ed_password.text.toString())){
            Utils.showToast(this, resources.getString(R.string.msg_not_same_pass))
            return false
        }
        return true
    }

}