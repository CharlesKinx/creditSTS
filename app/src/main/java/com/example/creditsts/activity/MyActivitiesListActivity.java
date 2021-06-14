package com.example.creditsts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.creditsts.R;
import com.example.creditsts.adapter.MyActivitiesListAdapter;
import com.example.creditsts.model.ScoreItemInfo;

import java.util.ArrayList;

public class MyActivitiesListActivity extends AppCompatActivity {
    private ListView listView;
    private Button back;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivities);
        listView = findViewById(R.id.my_list);


        ArrayList<ScoreItemInfo> scoreItemInfos = LoginActivity.studentInfo.getArrayList();
        MyActivitiesListAdapter myActivitiesListAdapter = new MyActivitiesListAdapter(getApplicationContext(),scoreItemInfos);
        listView.setAdapter(myActivitiesListAdapter);
        textView = findViewById(R.id.tx_score_total);
        textView.setText(String.valueOf(LoginActivity.studentInfo.getTotalScore()));

        back = findViewById(R.id.my_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(MyActivitiesListActivity.this,HomePageActivity.class);
                finish();
            }
        });

    }
}
