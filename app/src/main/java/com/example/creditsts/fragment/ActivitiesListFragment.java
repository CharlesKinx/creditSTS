package com.example.creditsts.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.creditsts.R;
import com.example.creditsts.activity.HomePageActivity;
import com.example.creditsts.activity.LoginActivity;
import com.example.creditsts.adapter.ActivitiesListAdapter;
import com.example.creditsts.model.ScoreItemInfo;

import java.util.ArrayList;

public class ActivitiesListFragment extends Fragment implements ActivitiesListAdapter.InnerItemOnclickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private ActivitiesListAdapter activitiesListAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actvitieslist, container, false);
        listView = view.findViewById(android.R.id.list);
        activitiesListAdapter = new ActivitiesListAdapter(getActivity(), LoginActivity.scoreItemInfoArrayList);
        activitiesListAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(activitiesListAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void removeItem(int id){

        for(int i=0;i<LoginActivity.studentInfo.getArrayList().size();i++){
            if(id==LoginActivity.studentInfo.getArrayList().get(i).getId()){
                LoginActivity.studentInfo.getArrayList().remove(i);
            }
        }

    }
    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()){
            case R.id.join:
                Button button = v.findViewById(R.id.join);
                if(button.getText().equals("参加")){
                    button.setText("已参加");
                    LoginActivity.studentInfo.setTotalScore(LoginActivity.studentInfo.getTotalScore()+LoginActivity.scoreItemInfoArrayList.get(position).getScore());
                    LoginActivity.studentInfo.getArrayList().add(LoginActivity.scoreItemInfoArrayList.get(position));

                }else{
                    removeItem(LoginActivity.scoreItemInfoArrayList.get(position).getId());
                    LoginActivity.studentInfo.setTotalScore(LoginActivity.studentInfo.getTotalScore()-LoginActivity.scoreItemInfoArrayList.get(position).getScore());
                    button.setText("参加");
                }
                break;
            default:
                break;
        }

    }
}
