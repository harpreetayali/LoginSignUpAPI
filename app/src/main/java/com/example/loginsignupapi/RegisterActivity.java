package com.example.loginsignupapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsignupapi.Models.ModelCheckPhoneEmail;
import com.example.loginsignupapi.ViewModels.CheckPhoneEmailViewModel;

public class RegisterActivity extends AppCompatActivity
{

    private EditText et_first_name,et_last_name,et_mobile,et_email,et_password;
    private Button btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_first_name = findViewById(R.id.et_first_name);
        et_last_name = findViewById(R.id.et_last_name);
        et_mobile = findViewById(R.id.et_mobile);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);




        btn_sign_up.setOnClickListener(view->
        {
            CheckPhoneEmailViewModel model = ViewModelProviders
                    .of(this)
                    .get(CheckPhoneEmailViewModel.class);

            String name = et_first_name.getText().toString() + " " + et_last_name.getText().toString();
            String mobile = et_mobile.getText().toString();
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();

            model.getResult(mobile,email)
                    .observe(this, new Observer<ModelCheckPhoneEmail>()
                    {
                        @Override
                        public void onChanged(ModelCheckPhoneEmail modelCheckPhoneEmail)
                        {

                            if (modelCheckPhoneEmail.getSuccess().equals("1"))
                            {
                                Toast.makeText(RegisterActivity.this, modelCheckPhoneEmail.getMessage() + "\n" + "OTP : "+ modelCheckPhoneEmail.getOtp(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this,VerificationActivity.class);
                                intent.putExtra("otp",modelCheckPhoneEmail.getOtp());
                                intent.putExtra("name" ,name);
                                intent.putExtra("mobile" ,mobile);
                                intent.putExtra("email" ,email);
                                intent.putExtra("password" ,password);
                                startActivity(intent);
                            }
                        }
                    });
        });

    }
}