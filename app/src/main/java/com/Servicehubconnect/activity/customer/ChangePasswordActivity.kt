package com.Servicehubconnect.activity.customer

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.customer_activity_change_password.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ChangePasswordActivity : AppCompatActivity(), View.OnClickListener{
    var showOldPassword: Boolean = false
    var showNewPassword: Boolean = false
    var showConfirmNewPassword: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_change_password)

        tv_title.text = "Change Password"
        setOnClickListener()

    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        iv_old_password_toggle.setOnClickListener(this)
        iv_new_password_toggle.setOnClickListener(this)
        iv_confirm_new_password_toggle.setOnClickListener(this)
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
      }
    }


}