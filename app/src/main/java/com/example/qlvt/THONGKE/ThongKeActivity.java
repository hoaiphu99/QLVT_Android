package com.example.qlvt.THONGKE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.QLPhanCong.PhanCongDatabase;
import com.example.qlvt.R;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {

    ListView lstThongKe;
    ArrayList<ThongKe> thongKes = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongke_xe_view);

        setControl();
        setEvent();
    }

    public void setControl() {
        lstThongKe = findViewById(R.id.list_thongkexe);
    }

    public void setEvent() {
        loadData();
        ThongKeAdapter adapter = new ThongKeAdapter(this, R.layout.thongke_xe_list, thongKes);
        lstThongKe.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        lstThongKe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public void loadData() {
        ThongKeDB db = new ThongKeDB(this);
        thongKes.clear();
        db.thongKe(thongKes);
    }
}
