package com.example.qlvt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.qlvt.QLPhanCong.QuanLyPhanCong;
import com.example.qlvt.QLTaiXe.QuanLyTaiXe;
import com.example.qlvt.QLTinh.QuanLyTinh;
import com.example.qlvt.QLTuyen.QuanLyTuyen;
import com.example.qlvt.QLXe.QuanLyXe;
import com.example.qlvt.THONGKE.ThongKeTuyenActivity;
import com.example.qlvt.THONGKE.ThongKeXeActivity;

public class MainActivity extends AppCompatActivity {
    //    Button btn_menu_1, btn_menu_2, btn_menu_3, btn_menu_4, btn_menu_5;
    LinearLayout layout_menu_phancong, layout_menu_taixe, layout_menu_tuyen, layout_menu_xe, layout_menu_tinh, layout_menu_thongke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setControl() {

        layout_menu_phancong = findViewById(R.id.layout_menu_phancong);
        layout_menu_taixe = findViewById(R.id.layout_menu_taixe);
        layout_menu_tuyen = findViewById(R.id.layout_menu_tuyen);
        layout_menu_xe = findViewById(R.id.layout_menu_xe);
        layout_menu_tinh = findViewById(R.id.layout_menu_tinh);
        layout_menu_thongke = findViewById(R.id.layout_menu_thongke);
    }

    private void setEvent() {
        layout_menu_phancong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuanLyPhanCong.class);
                startActivity(intent);
            }
        });
        layout_menu_taixe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuanLyTaiXe.class);
                startActivity(intent);
            }
        });
        layout_menu_tuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuanLyTuyen.class);
                startActivity(intent);
            }
        });

        layout_menu_xe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuanLyXe.class);
                startActivity(intent);
            }
        });
        layout_menu_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, QuanLyTinh.class);
                startActivity(intent);
            }
        });
        layout_menu_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ThongKeTuyenActivity.class);
                startActivity(intent);
            }
        });

    }
}