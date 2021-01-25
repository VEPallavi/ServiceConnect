package com.com.Servicehubconnect.network

class ApiList {
    companion object {

        const val BASE_URL = "http://35.166.234.255:5000/api/"
        const val SIGNUP_URL = "user/register"
        const val VERIFY_OTP_URL = "user/verify-account"
        const val RESEND_OTP_URL = "user/resend-otp"
        const val LOGIN_URL ="user/login"
        const val FORGOT_PASSWORD_URL ="user/forgot-password"
        const val RESET_PASSWORD_URL = "user/reset-password"
        const val CATEGORY_LIST_URL = "user/get-category-list"
        const val SUB_CATEGORY_LIST_URL = "user/get-subCategory-list"

    }

}