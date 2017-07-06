package com.mycompany.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.content.MonsterList;
import com.mycompany.myapplication.dto.Monster;
import com.mycompany.myapplication.dto.Review;

import java.util.ArrayList;
import java.util.List;

public class MonsterListFragment extends Fragment {
    private static final String TAG = MonsterListFragment.class.getSimpleName();
    private ListView listView;
    private List<Monster> list = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = (ListView) inflater.inflate(R.layout.fragment_monster_list, container, false);
        itemAdapter = new MonsterListFragment.ItemAdapter();
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(itemClickListener);
        return listView;
    }



    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Monster monster = (Monster)itemAdapter.getItem(position);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        for (int i=1; i<=8; i++){
            Monster monster= new Monster();
            monster.setMno(i);
            monster.setMname("포켓몬"+i);
            monster.setMphoto(getResources().getIdentifier("monster"+i,"drawable", getContext().getPackageName()));
            monster.setMstar(getResources().getIdentifier("star"+i,"drawable", getContext().getPackageName()));
            monster.setMdesc("귀여운 포켓몬은 정말 귀엽습니다. 각자의 울음소리도 다릅니다. 각자의 에피소드를 갖고 있습니다.");
           addItem(monster);
        }
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
