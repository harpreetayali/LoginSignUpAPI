package com.example.loginsignupapi.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignupapi.Models.ModelUserLogin
import com.example.loginsignupapi.Retrofit.ApiClient
import com.example.loginsignupapi.Retrofit.JsonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLoginViewModel : ViewModel() {
    var jsonApi = ApiClient.client?.create(JsonApi::class.java)
    var login_result = MutableLiveData<ModelUserLogin?>()
    fun getLoginResult(emailPhone: String?, password: String?, reg_id: String?, device_type: String?):
            LiveData<ModelUserLogin?>
    {
        val call = jsonApi?.userLogin(emailPhone, password, reg_id, device_type)

        call!!.enqueue(object : Callback<ModelUserLogin?> {
            override fun onResponse(call: Call<ModelUserLogin?>, response: Response<ModelUserLogin?>) {
                login_result.value = response.body()
            }

            override fun onFailure(call: Call<ModelUserLogin?>, t: Throwable) {
                Log.i("LoginError", t.message)
            }
        })
        return login_result
    }
}