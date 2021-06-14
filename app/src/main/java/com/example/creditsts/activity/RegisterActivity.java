package com.example.creditsts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.creditsts.R;
import com.example.creditsts.model.StudentInfo;

public class   RegisterActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etUserID;
    private EditText etUserPassword;
    private EditText etUserPassword1;

    private Button registerConfirm;
    private Button back;
    private static final int REGISTER_RESULT=2;

    private void initView(){
        etUserName = findViewById(R.id.et_register_name);
        etUserID = findViewById(R.id.et_register_id);
        etUserPassword = findViewById(R.id.et_register_password);
        etUserPassword1 = findViewById(R.id.et_register_password1);
        registerConfirm = findViewById(R.id.btn_register_confirm);
        back = findViewById(R.id.btn_register_back);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        registerConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfo studentInfo = new StudentInfo();

                String name = etUserName.getText().toString();
                String ID = etUserID.getText().toString();
                String password = etUserPassword.getText().toString();
                String password1 = etUserPassword1.getText().toString();


                if(name.equals("")){
                    Toast.makeText(RegisterActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(ID.equals("")){
                    Toast.makeText(RegisterActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password1.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password1)){
                    Toast.makeText(RegisterActivity.this,"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else{
                    studentInfo.setName(name);
                    studentInfo.setPassword(password);
                    studentInfo.setStudentID(ID);
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("studentInfo",studentInfo);
                    setResult(REGISTER_RESULT,intent);
                    finish();
                }
            }
        });

    }
}
