package com.example.loginsignupapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsignupapi.Models.ModelUserLogin;
import com.example.loginsignupapi.ViewModels.UserLoginViewModel;

public class LoginActivity extends AppCompatActivity
{

    String emailPhone,password;
    EditText et_email_login, et_password_login;
    Button btn_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email_login = findViewById(R.id.et_email_login);
        et_password_login = findViewById(R.id.et_password_login);
        btn_log_in = findViewById(R.id.btn_log_in);

        emailPhone  = et_email_login.getText().toString();
        password = et_password_login.getText().toString();

        btn_log_in.setOnClickListener(view->{
            UserLoginViewModel model = ViewModelProviders.of(this).get(UserLoginViewModel.class);

            model.getLoginResult(emailPhone,password,"1","")
                    .observe(this, new Observer<ModelUserLogin>() {
                        @Override
                        public void onChanged(ModelUserLogin modelUserLogin)
                        {
                            Toast.makeText(LoginActivity.this, modelUserLogin.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}