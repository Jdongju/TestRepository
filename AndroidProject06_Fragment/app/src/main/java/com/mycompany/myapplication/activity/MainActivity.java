package com.mycompany.myapplication.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Monster;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.R;
import com.mycompany.myapplication.fragment.FoodListFragment;
import com.mycompany.myapplication.fragment.MonsterListFragment;
import com.mycompany.myapplication.fragment.ReviewListFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
    }

    public void handleRadioButton1(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo1);
    }
    public void handleRadioButton2(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo2);
    }
    public void handleRadioButton3(View v) {
        linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }

    public void handleButton1(View v) {
        ReviewListFragment rf = new ReviewListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, rf);
        ft.commit();


    }

    public void handleButton2(View v) {
        FoodListFragment rf = new FoodListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, rf);
        ft.commit();
    }

    public void handleButton3(View v) {
       MonsterListFragment rf = new MonsterListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, rf);
        ft.commit();
    }

}
