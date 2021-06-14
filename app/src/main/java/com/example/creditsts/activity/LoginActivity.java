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
import com.example.creditsts.model.ScoreItemInfo;
import com.example.creditsts.model.StudentInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    public static StudentInfo studentInfo;
    public static ArrayList<ScoreItemInfo> scoreItemInfoArrayList;


    private static final int REGISTER_REQUEST = 1;
    private static final int REGISTER_RESULT = 2;


    private ArrayList<ScoreItemInfo> getDate(){
        ArrayList<ScoreItemInfo> arrayList = new ArrayList<>();
        ScoreItemInfo scoreItemInfo = new ScoreItemInfo();

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH");
        String datetime = tempDate.format(new java.util.Date())+":00";
        scoreItemInfo.setId(4);
        scoreItemInfo.setName("演讲比赛");
        scoreItemInfo.setTime(datetime);
        scoreItemInfo.setScore(1.5);
        arrayList.add(scoreItemInfo);

        return arrayList;
    }

    /**
     * 初始化组件
     */
    void initComponent(){
        userName = findViewById(R.id.et_login_username);
        userPassword = findViewById(R.id.et_login_userPs);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        scoreItemInfoArrayList = getDate();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponent();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String password = userPassword.getText().toString();

                if(name.equals("")){
                    Toast.makeText(LoginActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(studentInfo == null){
                    Toast.makeText(LoginActivity.this,"没有该用户信息",Toast.LENGTH_SHORT).show();

                }else if(name.equals(studentInfo.getName())&&password.equals(studentInfo.getPassword())){
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"密码不正确！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REGISTER_REQUEST:
                if(resultCode == REGISTER_RESULT){
                    studentInfo = (StudentInfo) data.getSerializableExtra("studentInfo");
                }
                break;
        }

    }
}