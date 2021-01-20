package com.Servicehubconnect.network

import com.com.Servicehubconnect.network.ApiList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @POST(ApiList.SIGNUP_URL)
    @FormUrlEncoded
    fun signUpUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("mobile_no") mobile_no: String,
        @Field("country_code") country_code: String,
        @Field("local_city") localCity: String,
        @Field("refferal_code") referral_code: String
    ): Call<JsonObject>


    @POST(ApiList.VERIFY_OTP_URL)
    @FormUrlEncoded
    fun verificationOTP(
            @Field("email") email: String,
            @Field("otp") otp: String
    ): Call<JsonObject>


    @POST(ApiList.RESEND_OTP_URL)
    @FormUrlEncoded
    fun resendOTP(
            @Field("email") email: String
    ): Call<JsonObject>

    @POST(ApiList.LOGIN_URL)
    @FormUrlEncoded
    fun loginUser(
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<JsonObject>


}