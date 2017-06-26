package com.mycompany.myapp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgTitle;
    private Button btnImg1;
    private Button btnImg2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgTitle=(ImageView) findViewById(R.id.imgTitle);
        btnImg1=(Button) findViewById(R.id.btnImg1);
        btnImg2=(Button) findViewById(R.id.btnImg2);

        btnImg1.setOnClickListener(handleBtnImg1);
        btnImg2.setOnClickListener(handleBtnImg2);
    }

    private View.OnClickListener handleBtnImg1= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imgTitle.setImageResource(R.drawable.photo1);
        }
    };

    private View.OnClickListener handleBtnImg2= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imgTitle.setImageResource(R.drawable.photo07);
        }
    };

}
