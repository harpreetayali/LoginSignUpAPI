package com.example.loginsignupapi

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.loginsignupapi.Models.ModelUpdateProfile
import com.example.loginsignupapi.ViewModels.UpdateProfileViewModel
import com.mikhaellopez.circularimageview.CircularImageView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {
    var et_name_update: EditText? = null
    var et_email_update: EditText? = null
    var et_mobile_update: EditText? = null
    var et_address_update: EditText? = null
    var userId: String? = null
    var name: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    var address: String? = null
    var picturePath: String? = null
    var btn_update_profile: Button? = null
    var imageView: CircularImageView? = null
    var imageFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)
        et_name_update = findViewById(R.id.et_name_update)
        et_email_update = findViewById(R.id.et_email_update)
        et_mobile_update = findViewById(R.id.et_mobile_update)
        et_address_update = findViewById(R.id.et_address_update)
        imageView = findViewById(R.id.profile_image_view_update)
        btn_update_profile = findViewById(R.id.btn_update_profile)
        et_name_update?.setText(intent.getStringExtra("name"))
        et_email_update?.setText(intent.getStringExtra("email"))
        et_mobile_update?.setText(intent.getStringExtra("mobile"))
        et_address_update?.setText(intent.getStringExtra("address"))
        Glide.with(this)
                .load(intent.getStringExtra("image"))
                .into(imageView!!)
        imageView?.setOnClickListener(View.OnClickListener { view: View? -> selectImage() })
        btn_update_profile?.setOnClickListener(View.OnClickListener { view: View? ->
            userId = "45"
            name = et_name_update?.getText().toString()
            email = et_email_update?.getText().toString()
            phoneNumber = et_mobile_update?.getText().toString()
            address = et_address_update?.getText().toString()
            val userIdRequestBody = RequestBody.create(MediaType.parse("text/plain"), userId)
            val nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name)
            val emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email)
            val phoneNumberRequestBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber)
            val addressRequestBody = RequestBody.create(MediaType.parse("text/plain"), address)
            if (picturePath != null) {
                val imageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
                val image = MultipartBody.Part.createFormData("image", imageFile!!.name, imageRequestBody)
                val model = ViewModelProviders.of(this).get(UpdateProfileViewModel::class.java)
                model.getUpdateResult(this, userIdRequestBody, nameRequestBody, emailRequestBody, phoneNumberRequestBody, addressRequestBody, image)
                        .observe(this, Observer<ModelUpdateProfile?> { modelProfileUpdate: ModelUpdateProfile? ->
                            Toast.makeText(this@UpdateProfileActivity, modelProfileUpdate?.details?.email + " : " + modelProfileUpdate?.details?.image, Toast.LENGTH_SHORT).show()
                            if (modelProfileUpdate?.success == "1") {
                                startActivity(Intent(this@UpdateProfileActivity, GetProfileActivity::class.java))
                                finish()
                            }
                        })
            } else {
                Toast.makeText(this, "Select Image", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun selectImage() {
        val gallery_intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery_intent, 124)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 124) {
            val selectedImage = data!!.data
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            val c = contentResolver.query(selectedImage!!, filePath, null, null, null)
            c!!.moveToFirst()
            val columnIndex = c.getColumnIndex(filePath[0])
            picturePath = c.getString(columnIndex)
            c.close()
            imageFile = File(picturePath)
            val gallery_image = BitmapFactory.decodeFile(picturePath)
            imageView!!.setImageBitmap(gallery_image)
            Log.i("imagePath", picturePath)
        }
    }
}