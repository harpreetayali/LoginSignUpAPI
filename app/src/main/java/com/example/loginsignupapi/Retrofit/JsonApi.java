package com.example.loginsignupapi.Retrofit;

import com.example.loginsignupapi.Models.ModelCheckPhoneEmail;
import com.example.loginsignupapi.Models.ModelUserLogin;
import com.example.loginsignupapi.Models.ModelUserRegister;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonApi
{

    @FormUrlEncoded
    @POST("checkPhoneNumberAndEmail")
    Call<ModelCheckPhoneEmail> checkNumberAndEmail(@Field("phoneNumber") String phoneNumber,
                                                   @Field("email") String email);
    @FormUrlEncoded
    @POST("userRegister")
    Call<ModelUserRegister> userRegister(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("device_type") String device_type,
                                         @Field("reg_id") String reg_id);
    @FormUrlEncoded
    @POST("userLogin")
    Call<ModelUserLogin> userLogin(@Field("emailPhone") String emailPhone,
                                   @Field("password") String password,
                                   @Field("reg_id") String reg_id,
                                   @Field("device_type") String device_type);
}
