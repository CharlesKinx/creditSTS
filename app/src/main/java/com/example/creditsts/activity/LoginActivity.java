package com.example.creditsts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.example.creditsts.R;
import com.example.creditsts.model.ResultInfo;
import com.example.creditsts.model.ScoreItemInfo;
import com.example.creditsts.model.StudentInfo;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {


    private EditText userName;
    private EditText userPassword;
    private Button login;
    private Button register;

    public static StudentInfo studentInfo;
    public static ArrayList<ScoreItemInfo> scoreItemInfoArrayList;


    private static final int REGISTER_REQUEST = 1;
    private static final int REGISTER_RESULT = 2;


    private ArrayList<ScoreItemInfo> getDate() {
        ArrayList<ScoreItemInfo> arrayList = new ArrayList<>();
        ScoreItemInfo scoreItemInfo = new ScoreItemInfo();

        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH");
        String datetime = tempDate.format(new java.util.Date()) + ":00";
        scoreItemInfo.setId(4);
        scoreItemInfo.setName("演讲比赛");
        scoreItemInfo.setTime(datetime);
        scoreItemInfo.setScore(1.5);
        arrayList.add(scoreItemInfo);

        scoreItemInfo = new ScoreItemInfo();
        scoreItemInfo.setId(5);
        scoreItemInfo.setName("歌唱比赛");
        scoreItemInfo.setTime(datetime);
        scoreItemInfo.setScore(2.0);
        arrayList.add(scoreItemInfo);
        return arrayList;
    }

    /**
     * 初始化组件
     */
    void initComponent() {
        userName = findViewById(R.id.et_login_username);
        userPassword = findViewById(R.id.et_login_userPs);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        scoreItemInfoArrayList = getDate();
        studentInfo = new StudentInfo();

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

                if (name.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }  else {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS)
                            .build();

                    StudentInfo student = new StudentInfo();

                    student.setAccount(name);
                    student.setPassword(password);

                    Gson gson = new Gson();
                    String json = gson.toJson(student);

                    Request request = new Request.Builder()
                            .url("http://10.0.116.6:8081/user/login")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String res = response.body().string();
                            ResultInfo resultInfo = JSONObject.parseObject(res,ResultInfo.class);
                            if(resultInfo.getMsg().equals("登录成功")){
                                studentInfo  =student;
                                ArrayList<ScoreItemInfo> arrayList = new ArrayList<>();
                                studentInfo.setArrayList(arrayList);
                                System.out.println(resultInfo.getMsg());
                                //Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                //startActivity(intent);
                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(LoginActivity.this,resultInfo.getMsg(),Toast.LENGTH_SHORT).show();
                                });
                            }
                        }
                    });
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
