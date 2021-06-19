package com.example.creditsts.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.creditsts.R;
import com.example.creditsts.activity.ChangePersonInfoActivity;
import com.example.creditsts.activity.LoginActivity;
import com.example.creditsts.activity.MyActivitiesListActivity;

public class PersonalCenterFragment extends Fragment {

    private TextView userID;
    private TextView userName;
    private TextView userScore;
    private TextView scoreList;
    private TextView changeInfo;

    private void initView(View view){
        userID = view.findViewById(R.id.tx_userID);
        userName = view.findViewById(R.id.tx_userName);
        changeInfo = view.findViewById(R.id.user_change);
        scoreList = view.findViewById(R.id.user_list);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalcenter, container, false);
        initView(view);

        userName.setText(LoginActivity.studentInfo.getAccount());
        userID.setText(LoginActivity.studentInfo.getTelephone());

        scoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyActivitiesListActivity.class);
                startActivity(intent);
            }
        });

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePersonInfoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
