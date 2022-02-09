package com.example.loginsignupapi.ViewModels

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignupapi.Models.ModelUpdateProfile
import com.example.loginsignupapi.Retrofit.ApiClient
import com.example.loginsignupapi.Retrofit.JsonApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileViewModel : ViewModel() {
    var jsonApi = ApiClient.client?.create(JsonApi::class.java)
    var update_result = MutableLiveData<ModelUpdateProfile?>()
    fun getUpdateResult(ctx: Context?, userId: RequestBody?, name: RequestBody?, email: RequestBody?, phoneNumber: RequestBody?, address: RequestBody?, image: MultipartBody.Part?): LiveData<ModelUpdateProfile?> {
        val call = jsonApi?.updateProfile(userId, name, email, phoneNumber, address, image)
        val progressDoalog: ProgressDialog
        progressDoalog = ProgressDialog(ctx)
        progressDoalog.max = 100
        progressDoalog.setMessage("Uploading...")
        progressDoalog.setProgressStyle(ProgressDialog.BUTTON_NEGATIVE)
        // show it
        progressDoalog.show()
        call!!.enqueue(object : Callback<ModelUpdateProfile?> {
            override fun onResponse(call: Call<ModelUpdateProfile?>, response: Response<ModelUpdateProfile?>) {
                progressDoalog.dismiss()
                update_result.postValue(response.body())
            }

            override fun onFailure(call: Call<ModelUpdateProfile?>, t: Throwable) {
                progressDoalog.dismiss()
                Toast.makeText(ctx, t.message, Toast.LENGTH_SHORT).show()
                //                Log.i("updateError",t.getMessage());
            }
        })
        return update_result
    }
}