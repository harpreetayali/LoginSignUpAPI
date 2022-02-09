package com.example.loginsignupapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.loginsignupapi.Models.ModelCheckPhoneEmail
import com.example.loginsignupapi.ViewModels.CheckPhoneEmailViewModel

class RegisterActivity : AppCompatActivity() {
    private var et_first_name: EditText? = null
    private var et_last_name: EditText? = null
    private var et_mobile: EditText? = null
    private var et_email: EditText? = null
    private var et_password: EditText? = null
    private var btn_sign_up: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        et_first_name = findViewById(R.id.et_first_name)
        et_last_name = findViewById(R.id.et_last_name)
        et_mobile = findViewById(R.id.et_mobile)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        btn_sign_up = findViewById(R.id.btn_sign_up)
        btn_sign_up?.setOnClickListener { view: View? ->
            val model = ViewModelProvider(this)
                    .get(CheckPhoneEmailViewModel::class.java)
            val name = et_first_name?.text.toString() + " " + et_last_name?.text.toString()
            val mobile = et_mobile?.text.toString()
            val email = et_email?.text.toString()
            val password = et_password?.text.toString()
            model.getResult(mobile, email)
                    .observe(this, Observer {
                        if (it?.success == "1") {
                            Toast.makeText(this@RegisterActivity, "${it?.message} OTP : ${it?.otp}".trimIndent(), Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, VerificationActivity::class.java)
                            intent.putExtra("otp", it?.otp)
                            intent.putExtra("name", name)
                            intent.putExtra("mobile", mobile)
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            startActivity(intent)
                        }
                    })
        }
    }
}