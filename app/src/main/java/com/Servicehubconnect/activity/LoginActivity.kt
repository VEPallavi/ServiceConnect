package com.Servicehubconnect.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.Servicehubconnect.R
import com.Servicehubconnect.activity.customer.HomeActivityCustomer
import com.Servicehubconnect.activity.customer.PrivacyPolicyActivityCustomer
import com.Servicehubconnect.activity.customer.TermAndConditionActivityCustomer
import com.Servicehubconnect.activity.servicePerson.HomeActivitySP
import com.Servicehubconnect.activity.servicePerson.PrivacyPolicyActivityProfessional
import com.Servicehubconnect.activity.servicePerson.SubscriptionActivitySP
import com.Servicehubconnect.activity.servicePerson.TermAndConditionActivityProfessional
import com.Servicehubconnect.helper.AppPreference
import com.Servicehubconnect.helper.Utils
import com.Servicehubconnect.viewModel.LoginViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity(), View.OnClickListener{
    var showPassword: Boolean = false
    var viewModel: LoginViewModel?= null
    var appPreference: AppPreference?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        appPreference = AppPreference.getInstance(this)

        setOnClickListener()
        getEditTextData()
    }

    private fun setOnClickListener() {
        tv_signUp.setOnClickListener(this)
        tv_login.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        iv_password_toggle.setOnClickListener(this)
        iv_logo.setOnClickListener {
            var intent = Intent(this, PrivacyPolicyActivityCustomer::class.java)
            startActivity(intent)
        }


//        txt_login.setOnClickListener {
////            val picker = CurrencyPicker.newInstance("Select Currency") // dialog title
////
////            picker.setListener { name, code, symbol, flagDrawableResID ->
////               Utils.showLog(name+ code+ symbol+ flagDrawableResID)
////                txt_login.setText(code)
////                picker.dismiss()
////            }
////            picker.show(supportFragmentManager, "CURRENCY_PICKER")
//
//
//            val pickerDialog = CountryCurrencyPicker.newInstance(PickerType.COUNTRYandCURRENCY, object : CountryCurrencyPickerListener {
//                override fun onSelectCountry(country: Country?) {}
//                override fun onSelectCurrency(currency: Currency) {
//                    if (currency.getCountries() == null) {
//
//                        Utils.showLog(String.format("name: %s\nsymbol: %s", currency.getName(), currency.getSymbol()))
//                        Utils.showLog(String.format("name: %s\nsymbol: %s", currency.getName()))
////                        Toast.makeText(this@MainActivity,
////                                String.format("name: %s\nsymbol: %s", currency.getName(), currency.getSymbol())
////                                , Toast.LENGTH_SHORT).show()
//                    } else {
//                        Utils.showLog(String.format("name: %s\nsymbol: %s", currency.getName(), currency.getSymbol()))
//                        Utils.showLog(String.format("name: %s\nsymbol: %s", currency.getName()))
////                        Toast.makeText(this@MainActivity,
////                                String.format("name: %s\ncurrencySymbol: %s\ncountries: %s", currency.getName(), currency.getSymbol(), TextUtils.join(", ", currency.getCountriesNames()))
////                                , Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
//
//            pickerDialog.show(supportFragmentManager, CountryCurrencyPicker.DIALOG_NAME)
//
//        }

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


        viewModel?.loginUser(this
                , ed_email.text.toString()
                , ed_password.text.toString())?.observe(this, Observer {

            if(it!= null){

                if(it.has("status") && it.get("status").asString.equals("200")){

                    Utils.showToast(this, "Login successfully.")



                    if(it.has("data") && it.get("data") is JsonObject){
                        var dataObj= it.getAsJsonObject("data")

                        if(dataObj.has("user_type") && !dataObj.get("user_type").isJsonNull){

                            //  Todo USER
                            if(dataObj.get("user_type").asString.equals("user")){


                                if(dataObj.has("token")){
                                    appPreference?.setAuthToken(dataObj.get("token").asString)
                                }

                                appPreference?.setAppType("1")  // app type "1" - customer

                                if(dataObj.has("_id")){
                                    appPreference?.setCustomerUserID(dataObj.get("_id").asString)
                                }

                                if(dataObj.has("name")){
                                    appPreference?.setCustomerName(dataObj.get("name").asString)
                                }

                                if(dataObj.has("profile_pic") && !dataObj.get("profile_pic").isJsonNull){
                                    appPreference?.setCustomerMobileNo(dataObj.get("profile_pic").asString)
                                }


                                if(dataObj.has("email") && !dataObj.get("email").isJsonNull){
                                    appPreference?.setCustomerEmailID(dataObj.get("email").asString)
                                }


                                if(dataObj.has("mobile_no") && !dataObj.get("mobile_no").isJsonNull){
                                    appPreference?.setCustomerMobileNo(dataObj.get("mobile_no").asString)
                                }

                                if(dataObj.has("country_code") && !dataObj.get("country_code").isJsonNull){
                                    appPreference?.setCustomerCountryCode(dataObj.get("country_code").asString)
                                }



                                if(dataObj.has("is_signed_privacyPolicy")
                                        && dataObj.has("is_signed_termAndCondition")){

                                    if(dataObj.get("is_signed_privacyPolicy").asBoolean == false){
                                        var intent = Intent(this, PrivacyPolicyActivityCustomer::class.java)
                                        startActivity(intent)
                                    }
                                    else{
                                        if(dataObj.get("is_signed_termAndCondition").asBoolean == false){
                                            var intent = Intent(this, TermAndConditionActivityCustomer::class.java)
                                            startActivity(intent)
                                        }
                                        else{
                                            var intent = Intent(this, HomeActivityCustomer::class.java)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }

                             // Todo PROFESSIONAL
                            else if(dataObj.get("user_type").asString.equals("professional")
                                    || dataObj.get("user_type").asString.equals("business_user"))
                            {

                                appPreference?.setAppType("2")  // app type "2" - Professional/ service-person/ business_user

                                if(dataObj.has("_id")){
                                    appPreference?.setProfessionalUserID(dataObj.get("_id").asString)
                                }

                                if(dataObj.has("name")){
                                    appPreference?.setProfessionalName(dataObj.get("name").asString)
                                }

                                if(dataObj.has("is_signed_privacyPolicy")
                                        && dataObj.has("is_signed_termAndCondition")
                                        && dataObj.has("is_subscribed")) {

                                    if(dataObj.get("is_signed_privacyPolicy").asBoolean == false){
                                        var intent = Intent(this, PrivacyPolicyActivityProfessional::class.java)
                                        startActivity(intent)
                                    }
                                    else{
                                        if(dataObj.get("is_signed_termAndCondition").asBoolean == false){
                                            var intent = Intent(this, TermAndConditionActivityProfessional::class.java)
                                            startActivity(intent)
                                        }
                                        else{

                                            if(dataObj.get("is_subscribed").asBoolean == false){
                                                var intent = Intent(this, SubscriptionActivitySP::class.java)
                                                startActivity(intent)
                                            }
                                            else{
                                                var intent = Intent(this, HomeActivitySP::class.java)
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
                else {
                    if(it.has("status") && it.get("status").asString.equals("0")){
                        Utils.showToast(this, it.get("message").asString)
                    }
                }
            }
        })


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