package com.example.loginsignupapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.loginsignupapi.Models.ModelGetUserProfile
import com.example.loginsignupapi.ViewModels.GetUserProfileViewModel
import com.mikhaellopez.circularimageview.CircularImageView

class GetProfileActivity : AppCompatActivity()
{
    var imageView: CircularImageView? = null
    var et_name_show: EditText? = null
    var et_email_show: EditText? = null
    var et_mobile_show: EditText? = null
    var et_address_show: EditText? = null
    var btn_edit_profile: Button? = null
    var baseUrl: String? = null
    var imageName: String? = null
    var splitString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_profile)
        et_name_show = findViewById(R.id.et_name_show)
        et_email_show = findViewById(R.id.et_email_show)
        et_mobile_show = findViewById(R.id.et_mobile_show)
        et_address_show = findViewById(R.id.et_address_show)
        imageView = findViewById(R.id.profile_image_view_show)
        btn_edit_profile = findViewById(R.id.btn_edit_profile)
        val model = ViewModelProvider(this).get(GetUserProfileViewModel::class.java)
        model.getUserProfileResult("45").observe(this, Observer<ModelGetUserProfile?> { modelGetUserProfile: ModelGetUserProfile? ->
            Log.i("getdetails", modelGetUserProfile?.details?.get(0)?.name)
            et_name_show?.setText(modelGetUserProfile?.details!![0]?.name)
            et_email_show?.setText(modelGetUserProfile?.details!![0]?.email)
            et_mobile_show?.setText(modelGetUserProfile?.details!![0]?.phone)
            et_address_show?.setText(modelGetUserProfile?.details!![0]?.address)
            Glide.with(this@GetProfileActivity)
                    .load("http://futurewings.biz/ezeshopy/" + modelGetUserProfile!!.details[0].image)
                    .into(imageView!!)
            baseUrl = "http://futurewings.biz/ezeshopy/" + modelGetUserProfile!!.details[0].image
        })
        btn_edit_profile?.setOnClickListener(View.OnClickListener { view: View? ->
            val updateIntent = Intent(this@GetProfileActivity, UpdateProfileActivity::class.java)
            updateIntent.putExtra("name", et_name_show?.text.toString())
            updateIntent.putExtra("email", et_email_show?.text.toString())
            updateIntent.putExtra("mobile", et_mobile_show?.text.toString())
            updateIntent.putExtra("address", et_address_show?.text.toString())
            updateIntent.putExtra("image", baseUrl)
            startActivity(updateIntent)
        })
    }

    override fun onStart() {
        super.onStart()
        Log.i("onStart","onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("onRestart","onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.i("onStop","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPause","onPause")
    }

}