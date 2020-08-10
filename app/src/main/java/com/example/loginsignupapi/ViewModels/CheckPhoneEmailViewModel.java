package com.example.loginsignupapi.ViewModels;


import android.util.Log;

import com.example.loginsignupapi.Models.ModelCheckPhoneEmail;
import com.example.loginsignupapi.Retrofit.ApiClient;
import com.example.loginsignupapi.Retrofit.JsonApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckPhoneEmailViewModel extends ViewModel
{
    JsonApi jsonApi = ApiClient.getClient().create(JsonApi.class);

    MutableLiveData<ModelCheckPhoneEmail> result = new MutableLiveData<>();


    public LiveData<ModelCheckPhoneEmail> getResult(String phoneNumber, String email)
    {
        Call<ModelCheckPhoneEmail> call = jsonApi.checkNumberAndEmail(phoneNumber,email);

        call.enqueue(new Callback<ModelCheckPhoneEmail>()
        {
            @Override
            public void onResponse(Call<ModelCheckPhoneEmail> call, Response<ModelCheckPhoneEmail> response)
            {
                result.setValue(response.body());
                Log.i("Result",response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ModelCheckPhoneEmail> call, Throwable t)
            {
                Log.i("Result",t.getMessage());
            }
        });

        return result;
    }



}
