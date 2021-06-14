package com.example.creditsts.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.creditsts.R;
import com.example.creditsts.activity.LoginActivity;

public class PersonalCenterFragment extends Fragment {

    private TextView userID;
    private TextView userName;
    private TextView userScore;
    private TextView scoreList;

    private void initView(View view){
        userID = view.findViewById(R.id.tx_userID);
        userName = view.findViewById(R.id.tx_userName);
        userScore = view.findViewById(R.id.tx_score_total);

        scoreList = view.findViewById(R.id.user_list);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalcenter, container, false);
        initView(view);

        userName.setText(LoginActivity.studentInfo.getName());
        userID.setText(LoginActivity.studentInfo.getStudentID());
        userScore.setText(String.valueOf(LoginActivity.studentInfo.getTotalScore()));

        scoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了列表活动",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
