package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.customer.ChangePasswordViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.ed_password
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.customer_activity_change_password.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ChangePasswordActivity : AppCompatActivity(), View.OnClickListener{
    var viewModel: ChangePasswordViewModel?= null
    var showOldPassword: Boolean = false
    var showNewPassword: Boolean = false
    var showConfirmNewPassword: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_change_password)
        viewModel = ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)

        tv_title.text = "Change Password"
        setOnClickListener()

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        iv_old_password_toggle.setOnClickListener(this)
        iv_new_password_toggle.setOnClickListener(this)
        iv_confirm_new_password_toggle.setOnClickListener(this)
        tv_save.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
      when(v?.id){
          R.id.ivBack ->{
              finish()
          }
          R.id.iv_old_password_toggle ->{
              if (showOldPassword) {
                  ed_old_password.transformationMethod = PasswordTransformationMethod.getInstance()
                  iv_old_password_toggle.setImageResource(R.drawable.eye_icon_off)
              } else {
                  ed_old_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                  iv_old_password_toggle.setImageResource(R.drawable.eye_icon_on)
              }
              ed_old_password.setSelection(ed_old_password.text!!.length)
              showOldPassword = !showOldPassword
          }
          R.id.iv_new_password_toggle ->{
              if (showNewPassword) {
                  ed_new_password.transformationMethod = PasswordTransformationMethod.getInstance()
                  iv_new_password_toggle.setImageResource(R.drawable.eye_icon_off)
              } else {
                  ed_new_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                  iv_new_password_toggle.setImageResource(R.drawable.eye_icon_on)
              }
              ed_new_password.setSelection(ed_new_password.text!!.length)
              showNewPassword = !showNewPassword
          }
          R.id.iv_confirm_new_password_toggle ->{
              if (showConfirmNewPassword) {
                  ed_confirm_new_password.transformationMethod = PasswordTransformationMethod.getInstance()
                  iv_confirm_new_password_toggle.setImageResource(R.drawable.eye_icon_off)
              } else {
                  ed_confirm_new_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                  iv_confirm_new_password_toggle.setImageResource(R.drawable.eye_icon_on)
              }
              ed_confirm_new_password.setSelection(ed_confirm_new_password.text!!.length)
              showConfirmNewPassword = !showConfirmNewPassword
          }

          R.id.tv_save ->{
              if(checkValidations()){
                  hitApiChangePassword()
              }
          }
      }
    }

    private fun hitApiChangePassword() {

        viewModel!!.changePassword(this, ed_old_password.text.toString(), ed_new_password.text.toString()).observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("1")){

                    if(it.has("message") && !it.get("message").isJsonNull){

                        Utils.showToast(this, it.get("message").asString)

                    }

                }
                else {

                }
            }
        })
    }






    private fun checkValidations(): Boolean {
        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        else if(ed_old_password.text.length == 0){
            Utils.showToast(this, "Please enter the old password.")
            return false
        }
        else if (ed_new_password.text!!.length == 0) {
            Utils.showToast(this, "Please enter the new password.")
            return false
        }
        else if (ed_new_password.text!!.length < 6) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_pass))
            return false
        }
        else if(!(ed_confirm_new_password.text.toString()).equals(ed_new_password.text.toString())){
            Utils.showToast(this, resources.getString(R.string.msg_not_same_pass))
            return false
        }
        return true
    }



}