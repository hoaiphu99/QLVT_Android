package com.example.qlvt.THONGKE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.QLPhanCong.PhanCong;
import com.example.qlvt.R;

import java.util.ArrayList;

public class UpdateThongKeXe extends AppCompatActivity {
    boolean isSave = false;
    String maXe, soLan;
    ArrayList<String> maXes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlxe_insert);

        setControl();

    }

    public void setControl() {

    }
    public void insert(PhanCong pc) {
//        GetMaXeThongKeDB getMaXeThongKeDB = new GetMaXeThongKeDB();
        ThongKeDB db = new ThongKeDB(this);
        db.insert(pc);

    }
}
