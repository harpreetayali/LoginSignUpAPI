package com.example.loginsignupapi.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignupapi.Models.ModelUserRegister
import com.example.loginsignupapi.Retrofit.ApiClient
import com.example.loginsignupapi.Retrofit.JsonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegisterViewModel : ViewModel() {
    var jsonApi = ApiClient.client?.create(JsonApi::class.java)
    var register_result = MutableLiveData<ModelUserRegister?>()
    fun getRegisterResullt(name: String?, email: String?, phone: String?, password: String?, device_type: String?, reg_id: String?): LiveData<ModelUserRegister?> {
        val call = jsonApi?.userRegister(name, email, phone, password, device_type, reg_id)
        call!!.enqueue(object : Callback<ModelUserRegister?> {
            override fun onResponse(call: Call<ModelUserRegister?>, response: Response<ModelUserRegister?>) {
                register_result.value = response.body()
            }

            override fun onFailure(call: Call<ModelUserRegister?>, t: Throwable) {
                Log.i("Result", t.message)
            }
        })
        return register_result
    }
}