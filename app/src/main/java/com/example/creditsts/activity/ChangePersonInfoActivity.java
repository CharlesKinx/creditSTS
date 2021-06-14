package com.example.creditsts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.creditsts.R;

public class ChangePersonInfoActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserID;
    private EditText etUserPassword;
    private EditText etUserPassword1;

    private Button changeConfirm;
    private Button back;

    private void initView(){
        etUserName = findViewById(R.id.et_change_name);
        etUserID = findViewById(R.id.et_change_id);
        etUserPassword = findViewById(R.id.et_change_password);
        etUserPassword1 = findViewById(R.id.et_change_password1);
        changeConfirm = findViewById(R.id.btn_change_confirm);
        back = findViewById(R.id.btn_change_back);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeinfo);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etUserName.getText().toString();
                String ID = etUserID.getText().toString();
                String password = etUserPassword.getText().toString();
                String password1 = etUserPassword1.getText().toString();


                if(name.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(ID.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password1.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password1)){
                    Toast.makeText(ChangePersonInfoActivity.this,"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else {
                    LoginActivity.studentInfo.setPassword(password);
                    LoginActivity.studentInfo.setStudentID(ID);
                    LoginActivity.studentInfo.setName(name);
                    Intent intent = new Intent(ChangePersonInfoActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
