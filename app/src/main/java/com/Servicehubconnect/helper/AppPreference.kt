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

    fun setCustomerUSerID(userId: String) {
        setString(CUSTOMER_USER_ID, userId)
    }



}