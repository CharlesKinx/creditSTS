package com.example.creditsts.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.creditsts.R;
import com.example.creditsts.activity.HomePageActivity;
import com.example.creditsts.activity.LoginActivity;
import com.example.creditsts.model.ScoreItemInfo;

public class UploadScoreFragment extends Fragment {

    private EditText title;
    private EditText score;
    private EditText time;
    private Button upload;

    private void initView(View view){
        title = view.findViewById(R.id.et_name);
        score = view.findViewById(R.id.et_score);
        time = view.findViewById(R.id.et_time);
        upload = view.findViewById(R.id.upload);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uploadscore, container, false);
        initView(view);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aTitle = title.getText().toString();
                String aScore = score.getText().toString();
                String aTime = time.getText().toString();
                ScoreItemInfo scoreItemInfo = new ScoreItemInfo();
                if(aTitle.equals("")){
                    Toast.makeText(getActivity(),"活动名称不能为空！",Toast.LENGTH_SHORT).show();
                }else if(aScore.equals("")){
                    Toast.makeText(getActivity(),"活动学分不能为空！",Toast.LENGTH_SHORT).show();
                }else if(aTime.equals("")){
                    Toast.makeText(getActivity(),"活动时间不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    scoreItemInfo.setScore(Double.valueOf(aScore));
                    scoreItemInfo.setTime(aTime);
                    scoreItemInfo.setName(aTitle);
                    LoginActivity.studentInfo.getArrayList().add(scoreItemInfo);
                    LoginActivity.studentInfo.setTotalScore(LoginActivity.studentInfo.getTotalScore()+scoreItemInfo.getScore());
                    //Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    //startActivity(intent);
                    title.setText("");
                    score.setText("");
                    time.setText("");
                    Toast.makeText(getActivity(),"上传成功！！",Toast.LENGTH_SHORT).show();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.show(new UploadScoreFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }
}
