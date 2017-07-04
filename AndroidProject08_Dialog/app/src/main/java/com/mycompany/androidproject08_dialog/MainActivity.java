package com.mycompany.androidproject08_dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleBtnMessageDialog(View v) {
        //setIcon이 builder를 리턴하고 setTitle이 buidler를 리턴하고 계속 내려가는 방식
        // 만약 하나라도 builder를 리턴하지 않고 다른 것을 리턴한다면 builder.setIcon과 같이 앞에 변수 붙여서 써주어야함.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("제목")
                .setMessage("사랑해")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "닫기 버튼 클릭");
                    }
                })
                .setNegativeButton("Cancel", null)
                .setNeutralButton("Yes", null)
                .create();
        dialog.show();
    }

    public void handleBtnListDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setItems(menus, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnSingleChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setSingleChoiceItems(menus, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String selectedMenu = menus[which];
                        Log.i(TAG, selectedMenu);
                        dialog.dismiss();
                    }
                })
                .create();

        dialog.show();
    }

    public void handleBtnMultiChoiceDialog(View v) {
        final String[] menus = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6"};
        final boolean[] selected = {false, true, false, false, true, false};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("선택하세요")
                .setMultiChoiceItems(menus, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                })
                .setPositiveButton("취소", null)
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                String menu = menus[i];
                                Log.i(TAG, menu);


                            }
                        }
                    }
                })
                .create();
        dialog.show();
    }

    public void handleBtnStickProgressDialog(final View v) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(1024);
        dialog.setProgress(1000);
        dialog.show();
        Thread thread= new Thread(){
            @Override
            public void run() {
                for (int i=0; i<=1024; i++){
                    final int value=i;
                    Runnable runnable= new Runnable() {
                        @Override
                        public void run() {
                            dialog.setProgress(value);
                            dialog.setSecondaryProgress(value*5);
                        }
                    };
                    v.post(runnable);
                    try { Thread.sleep(10); } catch (Exception e) { e.printStackTrace(); } }
            }
        };
        thread.start();
    }
    public void handleBtnCircleProgressDialog(final View v){
       final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("통신상태");
        dialog.setMessage("다운로드 중입니다.");
        dialog.show();

        //로컬변수가 익명객체 안에 들어갈 때에는 final특성을 갖기 때문에 이때 사용되는 변수는 final로 선언되어야한다.
        Thread thread= new Thread(){
            @Override
            public void run() {
                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
                Runnable runnable= new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                };
                v.post(runnable);

            }
        };
        thread.start();


    }
    public void handleBtnCustomDialog(View v){
        CustomDialog dialog= new CustomDialog();
        dialog.show(getSupportFragmentManager(), null);
    }
}
