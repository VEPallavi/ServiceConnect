package com.Servicehubconnect.network

import com.Servicehubconnect.modal.customer.OrderServiceAndProduct.ServiceAndProductResponseModal
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
            @Field("email") email: String,
            @Field("from") from: String
    ): Call<JsonObject>

    @POST(ApiList.LOGIN_URL)
    @FormUrlEncoded
    fun loginUser(
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<JsonObject>

    @POST(ApiList.FORGOT_PASSWORD_URL)
    @FormUrlEncoded
    fun forgotPassword(
            @Field("email") email: String
    ): Call<JsonObject>


    @POST(ApiList.RESET_PASSWORD_URL)
    @FormUrlEncoded
    fun resetPassword(
            @Field("password") password: String,
            @Field("email") email: String
    ): Call<JsonObject>


    @GET(ApiList.CATEGORY_LIST_URL)
    fun getCategoryData(

    ): Call<JsonObject>


    @POST(ApiList.SUB_CATEGORY_LIST_URL)
    @FormUrlEncoded
    fun getSubCategoryData(
     @Field("categoryId") categoryId: String
    ): Call<JsonObject>

    @POST(ApiList.GET_PROFESSIONAL_LIST)
    @FormUrlEncoded
    fun getProfessionalList(
      @Field("subCategoryId") subCategoryId: String,
      @Field("longitude") longitude: String,
      @Field("latitude") latitude: String,
      @Field("Country") Country: String,
      @Field("City") City: String,
      @Field("keyword") keyword: String,
      @Field("time_zone") time_zone: String
    ): Call<JsonObject>


    @POST(ApiList.GET_PROFESSIONAL_DETAILS)
    @FormUrlEncoded
    fun getProfessionalDetails(
            @Field("professionalId") professionalId: String,
            @Field("time_zone") time_zone: String

    ): Call<JsonObject>


    @POST(ApiList.GET_PRODUCT_AND_SERVICE_LIST_URL)
    @FormUrlEncoded
    fun getProductAndServiceList(
            @Field("bussinessId") bussinessId: String
    ): Call<JsonObject>



    @POST(ApiList.GET_COMMENT_LIST_URL)
    @FormUrlEncoded
    fun getCommentList(
        @Field("professionalId") professionalId: String
    ): Call<JsonObject>


    @POST(ApiList.GET_EXTRA_PACKAGE_LIST_URL)
    @FormUrlEncoded
    fun getExtraPackageList(
            @Field("Id") Id: String,
            @Field("category_type") category_type: String
    ): Call<JsonObject>



    @GET(ApiList.GET_RATING_COMMENTS_URL)
    fun getRatingAndComment(
        @Header("Authorization") token: String
    ): Call<JsonObject>



    @GET(ApiList.PRIVACY_POLICY_URL)
    fun getPrivacyPolicy(
        @Header("Authorization") token: String
    ): Call<JsonObject>

    @GET(ApiList.TERM_AND_CONDITION_URL)
    fun getTermCondition(
       @Header("Authorization") token: String
    ): Call<JsonObject>


    @PUT(ApiList.SUBMIT_PRIVACY_POLICY)
    fun submitPrivacyPolicy(
            @Header("Authorization") token: String
    ): Call<JsonObject>


    @PUT(ApiList.SUBMIT_TERM_AND_CONDITION_URL)
    fun submitTermAndCondition(
            @Header("Authorization") token: String
    ): Call<JsonObject>


    @GET(ApiList.GET_CONTACT_SUPPORT_DETAILS_URL)
    fun getSupportData(

    ): Call<JsonObject>


    @GET(ApiList.ABOUT_US_URL)
    fun aboutUs(
    ): Call<JsonObject>

    @GET(ApiList.PRIVACY_POLICY_URL)
    fun privacyPolicy(
    ): Call<JsonObject>


    @GET(ApiList.TERM_AND_CONDITION_URL)
    fun termAndCondition(
    ): Call<JsonObject>


    @POST(ApiList.CHANGE_PASSWORD_URL)
    @FormUrlEncoded
    fun changePassword(
            @Header("Authorization") token: String,
            @Field("old_password") old_password: String,
            @Field("new_password") new_password: String
    ): Call<JsonObject>

    @POST(ApiList.REFER_FRIENDS_URL)
    @FormUrlEncoded
    fun getReferFriends(

    ): Call<JsonObject>





    @GET(ApiList.LOGOUT_URL)
    fun logOutUser(
            @Header("Authorization") token: String
    ): Call<JsonObject>




}