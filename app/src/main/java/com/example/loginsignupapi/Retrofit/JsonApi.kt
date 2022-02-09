package com.example.loginsignupapi.Retrofit

import com.example.loginsignupapi.Models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface JsonApi {
    @FormUrlEncoded
    @POST("checkPhoneNumberAndEmail")
    fun checkNumberAndEmail(@Field("phoneNumber") phoneNumber: String?,
                            @Field("email") email: String?): Call<ModelCheckPhoneEmail?>?

    @FormUrlEncoded
    @POST("userRegister")
    fun userRegister(@Field("name") name: String?,
                     @Field("email") email: String?,
                     @Field("phone") phone: String?,
                     @Field("password") password: String?,
                     @Field("device_type") device_type: String?,
                     @Field("reg_id") reg_id: String?): Call<ModelUserRegister?>?

    @FormUrlEncoded
    @POST("userLogin")
    fun userLogin(@Field("emailPhone") emailPhone: String?,
                  @Field("password") password: String?,
                  @Field("reg_id") reg_id: String?,
                  @Field("device_type") device_type: String?): Call<ModelUserLogin?>?

    @FormUrlEncoded
    @POST("getUserProfile")
    fun userProfile(@Field("userId") userId: String?): Call<ModelGetUserProfile?>?

    @Multipart
    @POST("userUpdateProfile")
    fun updateProfile(@Part("userId") userId: RequestBody?,
                      @Part("name") name: RequestBody?,
                      @Part("email") email: RequestBody?,
                      @Part("phoneNumber") phoneNumber: RequestBody?,
                      @Part("address") address: RequestBody?,
                      @Part image: MultipartBody.Part?): Call<ModelUpdateProfile?>?
}