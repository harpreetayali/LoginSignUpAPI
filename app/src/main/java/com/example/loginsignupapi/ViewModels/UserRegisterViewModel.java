package com.example.loginsignupapi.ViewModels;


import android.util.Log;

import com.example.loginsignupapi.Models.ModelCheckPhoneEmail;
import com.example.loginsignupapi.Models.ModelUserRegister;
import com.example.loginsignupapi.Retrofit.ApiClient;
import com.example.loginsignupapi.Retrofit.JsonApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterViewModel extends ViewModel
{
    JsonApi jsonApi = ApiClient.getClient().create(JsonApi.class);

    MutableLiveData<ModelUserRegister> register_result = new MutableLiveData<>();

    public LiveData<ModelUserRegister> getRegisterResullt(String name, String email, String phone, String password, String device_type, String reg_id)
    {
        Call<ModelUserRegister> call = jsonApi.userRegister(name,email,phone,password,device_type,reg_id);
        call.enqueue(new Callback<ModelUserRegister>()
        {
            @Override
            public void onResponse(Call<ModelUserRegister> call, Response<ModelUserRegister> response)
            {
              register_result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ModelUserRegister> call, Throwable t)
            {
                Log.i("Result",t.getMessage());

            }
        });

        return  register_result;
    }
}
