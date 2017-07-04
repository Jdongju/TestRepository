package com.mycompany.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mycompany.myapplication.content.FoodList;
import com.mycompany.myapplication.content.MonsterList;
import com.mycompany.myapplication.content.ReviewList;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Monster;
import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout linearLayoutTop;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutTop=(LinearLayout) findViewById(R.id.linearLayoutTop);
        frameLayout=(FrameLayout) findViewById(R.id.frameLayout);
    }

    public void handleRadioButton1(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo1);
    }
    public void handleRadioButton2(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo2);
    }
    public void handleRadioButton3(View v){
        linearLayoutTop.setBackgroundResource(R.drawable.photo3);
    }

    public void handleButton1(View v) {
        frameLayout.removeAllViews();
        ReviewList reviewList = new ReviewList(this);
        frameLayout.addView(reviewList);

        for(int i=1; i<=10; i++) {
            Review review = new Review();
            review.setPhoto(R.drawable.member1);
            review.setTitle("ListView와 Adapter");
            review.setStar(R.drawable.star10);
            review.setContent("Adapter는 item XML를 Inflation해서 데이터를 세팅한 다음 ListView에 제공하는 역할을 합니다.");
            reviewList.addItem(review);
        }
    }

        //layout얻는 방법1
//        LayoutInflater inflater = getLayoutInflater();
        //layout얻는 방법2
//        LayoutInflater inflater=LayoutInflater.from(this);
        //layout얻는 방법3
//        LayoutInflater inflater= (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        //파싱한 결과를 frameLayout에 넣는다.
//        1))
// View view = inflater.inflate(R.layout.content1, null);
//        frameLayout.addView(view);
//        2))
// View view = inflater.inflate(R.layout.content1, frameLayout, false);
//        frameLayout.addView(view);

//        3)
//        inflater.inflate(R.layout.content1, frameLayout);
//          4)
//        inflater.inflate(R.layout.content1, frameLayout, true);
    public void handleButton2(View v){
        frameLayout.removeAllViews();
        FoodList foodList = new FoodList(this);
        frameLayout.addView(foodList);

        for(int i=1; i<=6; i++) {
            Food food = new Food();
            food.setFno(i);
            food.setFname("삼겹살"+i);
            food.setFphoto(getResources().getIdentifier("food"+i, "drawable", getPackageName()));
            food.setFstar(getResources().getIdentifier("star"+i, "drawable", getPackageName()));
            food.setFdesc("한국인이 즐겨 먹는 음식입니다. 쌈장과 쌈과 함께 먹으면 끝내줍니다. 가락동에서 유명합니다.");
            foodList.addItem(food);
        }
    }
    public void handleButton3(View v){
        frameLayout.removeAllViews();
        MonsterList monsterList= new MonsterList(this);
        frameLayout.addView(monsterList);

        for (int i=1; i<=8; i++){
            Monster monster= new Monster();
            monster.setMno(i);
            monster.setMname("포켓몬"+i);
            monster.setMphoto(getResources().getIdentifier("monster"+i,"drawable", getPackageName()));
            monster.setMstar(getResources().getIdentifier("star"+i,"drawable", getPackageName()));
            monster.setMdesc("귀여운 포켓몬은 정말 귀엽습니다. 각자의 울음소리도 다릅니다. 각자의 에피소드를 갖고 있습니다.");
            monsterList.addItem(monster);
        }




    }

}
