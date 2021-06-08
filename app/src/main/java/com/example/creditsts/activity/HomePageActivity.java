package com.example.creditsts.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.creditsts.R;
import com.example.creditsts.fragment.ActivitiesListFragment;
import com.example.creditsts.fragment.PersonalCenterFragment;
import com.example.creditsts.fragment.UploadScoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {
    private ActivitiesListFragment activitiesListFragment;
    private PersonalCenterFragment personalCenterFragment;
    private UploadScoreFragment uploadScoreFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_item1:
                            showFragment(R.id.navigation_item1);
                            return true;
                        case R.id.navigation_item2:
                            showFragment(R.id.navigation_item2);
                            return true;
                        case R.id.navigation_item3:
                            showFragment(R.id.navigation_item3);
                            return true;

                    }

                    return false;
                }
            };

    private void initView(){
        activitiesListFragment = new ActivitiesListFragment();
        personalCenterFragment = new PersonalCenterFragment();
        uploadScoreFragment = new UploadScoreFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content,activitiesListFragment).add(R.id.content,personalCenterFragment).
                add(R.id.content,uploadScoreFragment);
        fragmentTransaction.hide(activitiesListFragment).hide(personalCenterFragment).hide(uploadScoreFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        showFragment(R.id.navigation_item1);
    }

    private void showFragment(int item) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (item){
            case R.id.navigation_item1:
                fragmentTransaction.hide(personalCenterFragment).hide(uploadScoreFragment);
                fragmentTransaction.show(activitiesListFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item2:
                fragmentTransaction.hide(personalCenterFragment).hide(activitiesListFragment);
                fragmentTransaction.show(uploadScoreFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.navigation_item3:
                fragmentTransaction.hide(uploadScoreFragment).hide(activitiesListFragment);
                fragmentTransaction.show(personalCenterFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bv);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }
}
