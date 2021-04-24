package com.example.qlvt.QLTaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.R;
import com.google.android.material.snackbar.Snackbar;

public class InsertTaiXeActivity extends AppCompatActivity {
    boolean isSave = false;
    int secs = 1;
    EditText et_maTX, et_tenTX, et_ngaySinh, et_diaChi;
    Button btn_Them;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltaixe_insert);

        setControl();
        setEvent();
    }

    public void setControl() {
        et_maTX = findViewById(R.id.et_maTX);
        et_tenTX = findViewById(R.id.et_tenTX);
        et_ngaySinh = findViewById(R.id.et_ngaySinh);
        et_diaChi = findViewById(R.id.et_diaChi);

        btn_Them = findViewById(R.id.btn_Them);
    }

    public void setEvent() {
        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_maTX.length() > 0 || et_tenTX.length() > 0 || et_ngaySinh.length() > 0 || et_diaChi.length() > 0) {
                    TaiXe taiXe = getTaiXe();
                    insert(taiXe);
                    isSave = true;
                    Snackbar.make(v, "Thêm thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    Utils.delay(secs, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            Intent intent = new Intent(InsertTaiXeActivity.this, QuanLyTaiXe.class);
                            startActivity(intent);
                        }
                    });
                    //Toast.makeText(InsertActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InsertTaiXeActivity.this, "Có thông tin chưa nhập! Kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public TaiXe getTaiXe() {
        TaiXe taiXe = new TaiXe();
        taiXe.setMaTaiXe(et_maTX.getText().toString());
        taiXe.setTenTaiXe(et_tenTX.getText().toString());
        taiXe.setNgaySinh(et_ngaySinh.getText().toString());
        taiXe.setDiaChi(et_diaChi.getText().toString());


        return taiXe;
    }

    public void insert(TaiXe taiXe) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.insert(taiXe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("xxx", "OnDestroy");
        if (isSave == false) {
            Toast.makeText(InsertTaiXeActivity.this, "Chưa lưu lại!", Toast.LENGTH_SHORT).show();

        }
    }
}