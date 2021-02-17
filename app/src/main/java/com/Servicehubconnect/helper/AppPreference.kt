package com.Servicehubconnect.helper

import android.content.Context
import android.content.SharedPreferences

class AppPreference()
{
    var mContext: Context? = null
    var APP_PREFERENCE = "APP_PREFERENCE"
    var TOKEN = "TOKEN"
    var APP_TYPE = "APP_TYPE"
    var CUSTOMER_USER_ID = "CUSTOMER_USER_ID"
    var CUSTOMER_NAME = "CUSTOMER_NAME"
    var CUSTOMER_PROFILE_PIC = "CUSTOMER_PROFILE_PIC"
    var CUSTOMER_EMAIL_ID = "CUSTOMER_EMAIL_ID"
    var CUSTOMER_MOBILE_NUMBER = "CUSTOMER_MOBILE_NUMBER"
    var CUSTOMER_COUNTRY_CODE = "CUSTOMER_COUNTRY_CODE"

    var PROFESSIONAL_USER_ID = "PROFESSIONAL_USER_ID"
    var PROFESSIONAL_NAME = "PROFESSIONAL_NAME"



    var mPreference: SharedPreferences? = null

    constructor(mContext: Context) : this() {
        this.mContext = mContext
        mPreference = mContext.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE)

    }


    companion object {
        var instance: AppPreference? = null
        fun getInstance(mContext: Context): AppPreference {
            if (instance == null)
                instance = AppPreference(mContext)
            return instance as AppPreference
        }
    }


    fun setString(key: String, value: String) {
        val editor = mPreference!!.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getString(key: String): String {
        return mPreference!!.getString(key, "")!!
    }





    fun setInt(key: String, value: Int) {
        val editor = mPreference!!.edit()
        editor.putInt(key, value)
        editor.apply()
    }
    fun getInt(key: String): Int {
        return mPreference!!.getInt(key, 0)
    }



    fun setBoolean(key: String, value: Boolean) {
        val editor = mPreference!!.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
    fun getBoolean(key: String): Boolean {
        return mPreference!!.getBoolean(key, false)
    }


    fun getAuthToken(): String {
        return getString(TOKEN)
    }
    fun setAuthToken(token: String) {
        setString(TOKEN, token)
    }


    fun getCustomerUserID(): String {
        return getString(CUSTOMER_USER_ID)
    }
    fun setCustomerUserID(userId: String) {
        setString(CUSTOMER_USER_ID, userId)
    }


    fun getCustomerName(): String {
        return getString(CUSTOMER_NAME)
    }
    fun setCustomerName(userId: String) {
        setString(CUSTOMER_NAME, userId)
    }


    fun getCustomerProfilePic(): String {
        return getString(CUSTOMER_PROFILE_PIC)
    }
    fun setCustomerProfilePic(profilePic: String) {
        setString(CUSTOMER_PROFILE_PIC, profilePic)
    }



    fun getCustomerCountryCode(): String {
        return getString(CUSTOMER_COUNTRY_CODE)
    }
    fun setCustomerCountryCode(countryCode: String) {
        setString(CUSTOMER_COUNTRY_CODE, countryCode)
    }



    fun getCustomerMobileNo(): String {
        return getString(CUSTOMER_MOBILE_NUMBER)
    }
    fun setCustomerMobileNo(mobileNumber: String) {
        setString(CUSTOMER_MOBILE_NUMBER, mobileNumber)
    }

    fun getCustomerEmailID(): String {
        return getString(CUSTOMER_EMAIL_ID)
    }
    fun setCustomerEmailID(emailId: String) {
        setString(CUSTOMER_EMAIL_ID, emailId)
    }



    fun getProfessionalUserID(): String {
        return getString(PROFESSIONAL_USER_ID)
    }
    fun setProfessionalUserID(userId: String) {
        setString(PROFESSIONAL_USER_ID, userId)
    }


    fun getProfessionalName(): String {
        return getString(PROFESSIONAL_NAME)
    }
    fun setProfessionalName(userId: String) {
        setString(PROFESSIONAL_NAME, userId)
    }



    fun getAppType(): String {
        return getString(APP_TYPE)
    }
    fun setAppType(app_type: String) {
        setString(APP_TYPE, app_type)
    }






}