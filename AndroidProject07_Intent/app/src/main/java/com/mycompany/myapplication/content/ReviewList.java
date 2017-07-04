package com.mycompany.myapplication.content;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.dto.Review;
import com.mycompany.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewList extends LinearLayout{
    private static final String TAG=ReviewList.class.getSimpleName();
    private ListView listView;
    private List<Review> list=new ArrayList<>();
    private ItemAdapter itemAdapter;

    public ReviewList(Context context) {
        super(context);
        //ListView 생성
        LayoutInflater inflater= LayoutInflater.from(context);

        // content1의 레이아웃을 읽고, content1의 root태그를 listView에 저장..
    listView=(ListView) inflater.inflate(R.layout.review_list,null);

        listView.setAdapter(itemAdapter);

        //listVIew를 내용으로 추가
       addView(listView);

        //listView 이벤트 처리




    }

    private AdapterView.OnItemClickListener itemClickListener= new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Review review= (Review)itemAdapter.getItem(position);
            Log.i(TAG,review.getTitle());
            Log.i(TAG,review.getContent());
        }
    };
    class ItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
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
            // Item UI 객체 생성(Inflation)
            // super()에서 context를  넘겼기 때문에 getContext로 얻을 수 있다.
            LayoutInflater inflater= LayoutInflater.from(getContext());
            //content1_item의 루트태그가  LinearLayout이기 떄문에 LinearLayout으로 받는다. 하지만 View로 받아도 상관 없음
          convertView= inflater.inflate(R.layout.review_item,null);

            //데이터세팅
            //View가 들어간 이유는 이 item 하나 안에서 찾아야 하기 때문
            ImageView photo=(ImageView) convertView.findViewById(R.id.photo);
            TextView title=(TextView) convertView.findViewById(R.id.title);
            ImageView star=(ImageView) convertView.findViewById(R.id.star);
            TextView content=(TextView) convertView.findViewById(R.id.content);

            Review item=list.get(position);
            photo.setImageResource(item.getPhoto());
            title.setText(item.getTitle());
            star.setImageResource(item.getStar());
            content.setText(item.getContent());
    //convertView : 화면내에서 사라지는 아이템은 안보이는 곳으로 내려갔다가 다시 올라와서 재사용한다.

            return   convertView;
        }
    }

    // 추가제거시 어댑터에게 통보한다.
    public void addItem(Review item){
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(Review item){
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
