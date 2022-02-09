package com.example.loginsignupapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginsignupapi.Models.ModelUserRegister
import com.example.loginsignupapi.ViewModels.UserRegisterViewModel

class VerificationActivity : AppCompatActivity() {
    private var et_code1: EditText? = null
    private var et_code2: EditText? = null
    private var et_code3: EditText? = null
    private var et_code4: EditText? = null
    private var btn_submit_code: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        et_code1 = findViewById(R.id.et_code1)
        et_code2 = findViewById(R.id.et_code2)
        et_code3 = findViewById(R.id.et_code3)
        et_code4 = findViewById(R.id.et_code4)
        btn_submit_code = findViewById(R.id.btn_submit_code)
        val intent = intent
        val OTP = intent.getStringExtra("otp")
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("mobile")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        Toast.makeText(this, OTP, Toast.LENGTH_SHORT).show()
        btn_submit_code?.setOnClickListener(View.OnClickListener { view: View? ->
            val enteredOtp = et_code1?.getText().toString() +
                    et_code2?.getText().toString() +
                    et_code3?.getText().toString() +
                    et_code4?.getText().toString()
            if (enteredOtp == OTP) {
                val model = ViewModelProviders.of(this).get(UserRegisterViewModel::class.java)
                model.getRegisterResullt(name, email, phone, password, "", "1")
                        .observe(this, Observer<ModelUserRegister?> { modelUserRegister ->
                            Toast.makeText(this@VerificationActivity, modelUserRegister?.message, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@VerificationActivity, LoginActivity::class.java))
                        })
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
        })
    }
}