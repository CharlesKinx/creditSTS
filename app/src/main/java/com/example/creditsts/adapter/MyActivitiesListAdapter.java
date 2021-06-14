package com.example.creditsts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.creditsts.R;
import com.example.creditsts.model.ScoreItemInfo;

import java.util.ArrayList;

public class MyActivitiesListAdapter extends BaseAdapter {
    private ArrayList<ScoreItemInfo> arrayListInfos;
    private Context context;

    private TextView title;
    private TextView time;
    private TextView score;

    private void initView(View view){
        title = view.findViewById(R.id.my_title);
        time = view.findViewById(R.id.my_time);
        score = view.findViewById(R.id.my_score);

    }

    public MyActivitiesListAdapter(Context context ,ArrayList<ScoreItemInfo> arrayList){
        this.context = context;
        this.arrayListInfos = arrayList;
    }

    @Override
    public int getCount() {
        if(arrayListInfos == null){
            return 0;
        }else{
            return arrayListInfos.size();
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
        View view = inflater.inflate(R.layout.item_myitem,null);

        initView(view);

        title.setText(arrayListInfos.get(position).getName());
        time.setText(arrayListInfos.get(position).getTime());
        score.setText(String.valueOf(arrayListInfos.get(position).getScore()));

        return view;
    }
}
