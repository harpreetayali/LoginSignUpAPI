package com.example.loginsignupapi.ViewModels;

import android.util.Log;

import com.example.loginsignupapi.Models.ModelUserLogin;
import com.example.loginsignupapi.Retrofit.ApiClient;
import com.example.loginsignupapi.Retrofit.JsonApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginViewModel extends ViewModel
{
    JsonApi jsonApi = ApiClient.getClient().create(JsonApi.class);

    MutableLiveData<ModelUserLogin> login_result = new MutableLiveData<>();

    public LiveData<ModelUserLogin> getLoginResult(String emailPhone, String password, String reg_id, String device_type)
    {
        Call<ModelUserLogin> call = jsonApi.userLogin(emailPhone,password,reg_id,device_type);
        call.enqueue(new Callback<ModelUserLogin>()
        {
            @Override
            public void onResponse(Call<ModelUserLogin> call, Response<ModelUserLogin> response)
            {
                login_result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ModelUserLogin> call, Throwable t)
            {
                Log.i("LoginError",t.getMessage());
            }
        });

        return login_result;
    }
}
