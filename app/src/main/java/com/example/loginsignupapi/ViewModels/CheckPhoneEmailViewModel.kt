package com.example.loginsignupapi.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignupapi.Models.ModelCheckPhoneEmail
import com.example.loginsignupapi.Retrofit.ApiClient
import com.example.loginsignupapi.Retrofit.JsonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckPhoneEmailViewModel : ViewModel() {
    var jsonApi = ApiClient.client?.create(JsonApi::class.java)
    var result = MutableLiveData<ModelCheckPhoneEmail?>()
    fun getResult(phoneNumber: String?, email: String?): LiveData<ModelCheckPhoneEmail?> {
        val call = jsonApi?.checkNumberAndEmail(phoneNumber, email)
        call!!.enqueue(object : Callback<ModelCheckPhoneEmail?> {
            override fun onResponse(call: Call<ModelCheckPhoneEmail?>, response: Response<ModelCheckPhoneEmail?>) {
                result.setValue(response.body())
                Log.i("Result", response.body()!!.message)
            }

            override fun onFailure(call: Call<ModelCheckPhoneEmail?>, t: Throwable)
            {

            }
        })
        return result
    }
}