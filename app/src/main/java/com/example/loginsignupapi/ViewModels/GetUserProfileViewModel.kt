package com.example.loginsignupapi.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignupapi.Models.ModelGetUserProfile
import com.example.loginsignupapi.Retrofit.ApiClient
import com.example.loginsignupapi.Retrofit.JsonApi
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class GetUserProfileViewModel : ViewModel()
{
    var jsonApi = ApiClient.client?.create(JsonApi::class.java)
    var getUserProfile_result = MutableLiveData<ModelGetUserProfile?>()

    fun getUserProfileResult(userId: String?): LiveData<ModelGetUserProfile?> {
        val call = jsonApi?.userProfile(userId)
        call?.enqueue(object : Callback<ModelGetUserProfile?> {
            override fun onResponse(call: Call<ModelGetUserProfile?>, response: Response<ModelGetUserProfile?>) {
                if (response.isSuccessful) {
                    getUserProfile_result.postValue(response.body())
                    Log.i("RespCode0", response.code().toString() + "")
                } else {
                    try {
                        val responseBody = response.errorBody()!!.string()
                        val doc = Jsoup.parse(responseBody)
                        //Elements element = doc.getAllElements();

//                        for(org.jsoup.nodes.Element e: element)
//                        {
//
//                            Elements str = e.getElementsByTag("h1");
//                            for(org.jsoup.nodes.Element el: str)
//                            {
//                                String title = el.getElementsByAttribute("title").text();
//                                System.out.println("The Title:"+title);
//                            }
//                        }
//                        Gson gson = new Gson();
//                        ErrorResponse errorResponse = gson.fromJson(
//                                responseBody,
//                                ErrorResponse.class
                        Log.i("RespCode0", responseBody)
                        Log.i("RespCode1", doc.title())
                        //                        Log.i("RespCode2", errorResponse.getError().toString());
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ModelGetUserProfile?>, t: Throwable) {
                Log.i("User Profile Error", t.message)
            }
        })
        return getUserProfile_result
    }
}