package com.example.loginsignupapi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.loginsignupapi.Models.ModelUserLogin
import com.example.loginsignupapi.ViewModels.UserLoginViewModel

class LoginActivity : AppCompatActivity() {
    var emailPhone: String? = null
    var password: String? = null
    var et_email_login: EditText? = null
    var et_password_login: EditText? = null
    var btn_log_in: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_email_login = findViewById(R.id.et_email_login)
        et_password_login = findViewById(R.id.et_password_login)
        btn_log_in = findViewById(R.id.btn_log_in)
        emailPhone = et_email_login?.getText().toString()
        password = et_password_login?.getText().toString()
        btn_log_in?.setOnClickListener(View.OnClickListener { view: View? ->
            val model = ViewModelProvider(this).get(UserLoginViewModel::class.java)
            model.getLoginResult(emailPhone, password, "1", "")
                    .observe(this, Observer<ModelUserLogin?>
                    {
                        modelUserLogin -> Toast.makeText(this@LoginActivity, modelUserLogin?.message, Toast.LENGTH_SHORT).show()
                    })
        })
    }
}