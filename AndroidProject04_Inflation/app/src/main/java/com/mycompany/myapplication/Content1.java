package com.mycompany.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-28.
 */

public class Content1 extends LinearLayout{
    private LinearLayout itemContainer;

    public Content1(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater= LayoutInflater.from(context);
        inflater.inflate(R.layout.content1,this);
        itemContainer=(LinearLayout) findViewById(R.id.itemContainer);
    }

    public void addItem(Item1 item1){
        LayoutInflater inflater=LayoutInflater.from(getContext());
//        항목이 여러개이기 때문에 읽은 것을 부모에 저장하지 않고 addVIew로 넣는다.
        View view=inflater.inflate(R.layout.content1_item,null);


        ImageView photo= (ImageView) view.findViewById(R.id.photo);
        photo.setImageResource(item1.getPhoto());
        TextView title= (TextView) view.findViewById(R.id.title);
        title.setText(item1.getTitle());
        ImageView star= (ImageView) view.findViewById(R.id.star);
        star.setImageResource(item1.getStar());
        TextView content=(TextView) view.findViewById(R.id.content);
        content.setText(item1.getContent());

        itemContainer.addView(view);
    }
}
