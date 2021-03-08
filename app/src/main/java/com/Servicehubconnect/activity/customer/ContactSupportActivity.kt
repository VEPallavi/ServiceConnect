package com.Servicehubconnect.activity.customer

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.Servicehubconnect.R
import com.Servicehubconnect.helper.Utils
import kotlinx.android.synthetic.main.customer_activity_contact_support.*
import kotlinx.android.synthetic.main.toolbar_layout_subcategories.*


class ContactSupportActivity: AppCompatActivity(), View.OnClickListener{
    private var isFirstTime = false
    var dialog : Dialog?= null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_activity_contact_support)

        isFirstTime = true
        initViews()
        setOnClickListener()

    }

    private fun initViews() {
        tv_title.text = "Contact Support"
        etMailAddress.text= "info@servicehubconnect.com"
    }

    private fun setOnClickListener() {
        ivBack.setOnClickListener(this)
        tv_send.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ivBack ->{
                finish()
            }
            R.id.tv_send ->{

                sendEmail()
            }
            R.id.tv_cancel ->{
                Utils.hideSoftKeyboard(this)
                if ((etMailSubject.text.toString().length >= 1
                                || etMailContent.text.toString().length >= 1) && isFirstTime){

                    cancelEmail()
                }
                else{
                    finish()
                }

            }
        }
    }

    private fun cancelEmail() {
        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setContentView(R.layout.dialog_common)
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog!!.setCanceledOnTouchOutside(false)

        var tv_common_msg = dialog!!.findViewById<TextView>(R.id.tv_common_msg)
        var tv_no = dialog!!.findViewById<Button>(R.id.tv_no)
        var tv_yes = dialog!!.findViewById<Button>(R.id.tv_yes)

        tv_common_msg.setText("Your mail will be discarded.Are you sure want to exit?")
        tv_no.setText("No")
        tv_yes.setText("Yes")
        dialog!!.show()

        tv_yes.setOnClickListener {
            dialog!!.dismiss()
            finish()
        }
        tv_no.setOnClickListener {
            dialog!!.dismiss()
        }


    }

    private fun sendEmail() {
        val emailAdd: String = etMailAddress.getText().toString()

        val emailSubject: String = etMailSubject.getText().toString()

        val emailContent: String = etMailContent.getText().toString()



        if (emailContent.equals("", ignoreCase = true)
                && emailSubject.equals("", ignoreCase = true)) {

            Utils.showToast(this, "Sorry, cannot send mail without subject or content.")
            return
        }

        if (emailSubject.equals("", ignoreCase = true)) {

            Utils.showToast(this, "Sorry, cannot send mail without subject or content.")
            return
        }

        if (emailContent.equals("", ignoreCase = true)) {
            Utils.showToast(this, "Cannot send mail without content.")
            return
        }

        var emailIntent= Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/html"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAdd))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, etMailSubject.text.toString().trim())
        emailIntent.putExtra(Intent.EXTRA_TEXT, etMailContent.getText().toString())
        startActivityForResult(emailIntent, 123)
        emailIntent.setPackage("com.google.android.gm");
        isFirstTime = false


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Utils.hideSoftKeyboard(this)
        finish()
    }



}