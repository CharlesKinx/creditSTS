package com.example.creditsts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.creditsts.R;

public class LoginActivity extends AppCompatActivity {


    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    /**
     * 初始化组件
     */
    void initComponent(){
        userName = findViewById(R.id.et_login_username);
        userPassword = findViewById(R.id.et_login_userPs);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}