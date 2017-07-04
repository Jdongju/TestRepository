package com.mycompany.myapplication.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.dto.Food;
import com.mycompany.myapplication.dto.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterList extends LinearLayout {
    private ListView listView;
    private List<Monster> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    public MonsterList(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        listView = (ListView) inflater.inflate(R.layout.monster_list, null);
        itemAdapter = new ItemAdapter();
        listView.setAdapter(itemAdapter);
        addView(listView);
    }

    class ItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return list.get(position).getMno();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.monster_item, null);
            }

            ImageView mphoto = (ImageView) convertView.findViewById(R.id.mphoto);
            TextView mname = (TextView) convertView.findViewById(R.id.mname);
            ImageView mstar = (ImageView) convertView.findViewById(R.id.mstar);
            TextView mdesc = (TextView) convertView.findViewById(R.id.mdesc);

         Monster monster= list.get(position);

            mphoto.setImageResource(monster.getMphoto());
          mname.setText(monster.getMname());
            mstar.setImageResource(monster.getMstar());
            mdesc.setText(monster.getMdesc());



            return convertView;
        }
    }

    public void addItem(Monster item) {
        list.add(item);
        itemAdapter.notifyDataSetChanged();
    }

    public void removeItem(Monster item) {
        list.remove(item);
        itemAdapter.notifyDataSetChanged();
    }
}
