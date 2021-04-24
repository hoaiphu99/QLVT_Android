package com.example.qlvt.THONGKE;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.R;

import java.util.ArrayList;

public class ThongKeXeActivity extends AppCompatActivity {

    ListView lstThongKe;
    ArrayList<ThongKeXe> thongKes = new ArrayList<>();
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
        ThongKeXeAdapter adapter = new ThongKeXeAdapter(this, R.layout.thongke_xe_list, thongKes);
        lstThongKe.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void loadData() {
        ThongKeDB db = new ThongKeDB(this);
        thongKes.clear();
        db.thongKeXe(thongKes);
    }
}
