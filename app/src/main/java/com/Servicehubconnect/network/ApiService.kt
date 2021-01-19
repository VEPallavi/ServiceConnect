package com.freeluancer.seeker.network

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
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


    @POST(ApiList.SIGNUP_URL)
    @FormUrlEncoded
    fun verificationOTP(

    ): Call<JsonObject>


}