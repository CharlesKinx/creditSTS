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
import com.example.creditsts.model.StudentInfo;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                String telephone = etUserID.getText().toString();
                String password = etUserPassword.getText().toString();
                String password1 = etUserPassword1.getText().toString();


                if(name.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                }else if(telephone.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(password1.equals("")){
                    Toast.makeText(ChangePersonInfoActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password1)){
                    Toast.makeText(ChangePersonInfoActivity.this,"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else {


                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .readTimeout(120, TimeUnit.SECONDS)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(120, TimeUnit.SECONDS)
                            .build();
                    StudentInfo studentInfo = new StudentInfo();
                    studentInfo.setId(LoginActivity.studentInfo.getId());
                    studentInfo.setAccount(name);
                    studentInfo.setPassword(password);
                    studentInfo.setTelephone(telephone);
                    Gson gson = new Gson();

                    String json = gson.toJson(studentInfo);

                    Request request = new Request.Builder()
                            .url("http://10.0.116.6:8081/user/change")
                            .post(RequestBody.create(MediaType.parse("application/json"),json))
                            .build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            System.out.println("请求失败！");
                            System.out.println(e);
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                            String res = response.body().string();
                            ResultInfo resultInfo = JSONObject.parseObject(res,ResultInfo.class);
                            if(resultInfo.getMsg().equals("修改成功")){
                                LoginActivity.studentInfo.setPassword(resultInfo.getUser().getPassword());
                                LoginActivity.studentInfo.setTelephone(resultInfo.getUser().getTelephone());
                                LoginActivity.studentInfo.setAccount(resultInfo.getUser().getAccount());
                                LoginActivity.studentInfo.setId(resultInfo.getUser().getId());
                                Intent intent = new Intent(ChangePersonInfoActivity.this,HomePageActivity.class);
                                startActivity(intent);

                            }else{
                                runOnUiThread(()->{
                                    Toast.makeText(ChangePersonInfoActivity.this,resultInfo.getMsg(),Toast.LENGTH_SHORT).show();
                                });
                            }
                        }
                    });

                }
            }
        });

    }
}
