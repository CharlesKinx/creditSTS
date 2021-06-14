package com.example.creditsts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.creditsts.R;
import com.example.creditsts.fragment.ActivitiesListFragment;
import com.example.creditsts.model.ScoreItemInfo;

import java.util.ArrayList;

public class ActivitiesListAdapter extends BaseAdapter implements View.OnClickListener{

    private InnerItemOnclickListener mListener;
    private ArrayList<ScoreItemInfo> scoreItemInfos;
    private Context context;
    private TextView activityTitle;
    private TextView activityScore;
    private TextView activityTime;
    private Button button;

    public ActivitiesListAdapter(Context context,ArrayList<ScoreItemInfo> arrayList){
        this.context = context;
        this.scoreItemInfos = arrayList;
    }

    private void initView(View view){
        activityTitle = view.findViewById(R.id.title);
        activityScore = view.findViewById(R.id.score);
        activityTime = view.findViewById(R.id.time);
        button = view.findViewById(R.id.join);

    }

    @Override
    public int getCount() {
        if (scoreItemInfos == null){
            return 0;
        }else{
            return scoreItemInfos.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_score,null);
        initView(view);

        button.setOnClickListener(this);
        button.setTag(position);

        activityTitle.setText(scoreItemInfos.get(position).getName());
        activityScore.setText(String.valueOf(scoreItemInfos.get(position).getScore()));
        activityTime.setText(scoreItemInfos.get(position).getTime());

        return view;
    }

    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }


    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
