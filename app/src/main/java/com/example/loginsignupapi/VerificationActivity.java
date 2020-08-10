package com.example.loginsignupapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsignupapi.Models.ModelUserRegister;
import com.example.loginsignupapi.ViewModels.UserRegisterViewModel;

public class VerificationActivity extends AppCompatActivity
{
    private EditText et_code1, et_code2, et_code3, et_code4;
    private Button btn_submit_code;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        et_code1 = findViewById(R.id.et_code1);
        et_code2 = findViewById(R.id.et_code2);
        et_code3 = findViewById(R.id.et_code3);
        et_code4 = findViewById(R.id.et_code4);
        btn_submit_code = findViewById(R.id.btn_submit_code);

        Intent intent = getIntent();
        String OTP  = intent.getStringExtra("otp");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("mobile");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        Toast.makeText(this, OTP, Toast.LENGTH_SHORT).show();

        btn_submit_code.setOnClickListener(view->
        {
            String enteredOtp = et_code1.getText().toString() +
                    et_code2.getText().toString() +
                    et_code3.getText().toString() +
                    et_code4.getText().toString();

            if (enteredOtp.equals(OTP))
            {
                UserRegisterViewModel model = ViewModelProviders.of(this).get(UserRegisterViewModel.class);

                model.getRegisterResullt(name,email,phone,password,"","1")
                        .observe(this, new Observer<ModelUserRegister>() {
                    @Override
                    public void onChanged(ModelUserRegister modelUserRegister)
                    {
                        Toast.makeText(VerificationActivity.this, modelUserRegister.getMessage(),Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(VerificationActivity.this,LoginActivity.class));
                    }
                });

                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}