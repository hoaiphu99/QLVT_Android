package com.example.qlvt.QLXe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.R;
import com.google.android.material.snackbar.Snackbar;

public class EditXeActivity extends AppCompatActivity {
    static final String MA_XE = "maXe", TEN_XE = "tenTX", NAM_SX = "namSX", MA_TX = "maTX";
    boolean isUpdate;
    //int idTaiXe;
    EditText editMaXe, editTenXe, editNamSX, editMaTX;
    Xe xe;
    int secs = 1;
    Button btnSua, btnXoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlxe_edit);

        setControl();
        setEvent();
    }

    public void setControl() {
        editMaXe = findViewById(R.id.ma_xe);
        editTenXe = findViewById(R.id.ten_xe);
        editNamSX = findViewById(R.id.nam_sx);
        editMaTX = findViewById(R.id.ma_tx);
        btnSua = findViewById(R.id.btnSave);
        btnXoa = findViewById(R.id.btnDelete);
    }

    public void setEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            editMaXe.setText(bundle.getString(MA_XE));
            editTenXe.setText(bundle.getString(TEN_XE));
            editNamSX.setText(bundle.getString(NAM_SX));
            editMaTX.setText(bundle.getString(MA_TX));

        }

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMaXe.length() > 0 || editTenXe.length() > 0 || editNamSX.length() > 0 || editMaTX.length() > 0) {

                    xe = getXe();
                    Log.e("update", "" + xe.getMaXe());
                    update(xe);
                    isUpdate = true;
                    Snackbar.make(v, "Sửa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    Utils.delay(secs, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            Intent intent = new Intent(EditXeActivity.this, QuanLyXe.class);
                            startActivity(intent);
                        }
                    });
                    //Toast.makeText(UpdateDeleteActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditXeActivity.this, "Có thông tin chưa nhập! Kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogDelete(v);
                //Snackbar.make(v, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                //Toast.makeText(UpdateDeleteActivity.this, "Đã xóa " + phanCong.getSoPhieu(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Xe getXe() {
        Xe xe = new Xe();
        xe.setMaXe(editMaXe.getText().toString());
        xe.setTenXe(editTenXe.getText().toString());
        xe.setNamSX(editNamSX.getText().toString());
        xe.setMaTX(editMaTX.getText().toString());

        return xe;
    }

    public void update(Xe xe) {
        XeDatabase db = new XeDatabase(this);
        db.update(xe);
    }

    public void delete(String maTX) {
        XeDatabase db = new XeDatabase(this);
        db.delete(maTX);
    }

    public void openAlertDialogDelete(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa?")
                .setMessage("Bạn có chắc chắn xóa tài xế này không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Xe xe = getXe();
                        delete(xe.getMaXe());
                        Snackbar.make(view, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                        Utils.delay(secs, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                Intent intent = new Intent(EditXeActivity.this, QuanLyXe.class);
                                startActivity(intent);
                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void openAlertDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận lưu?")
                .setMessage("Nhưng thay đổi bạn đã nhập chưa được lưu. Bạn có muốn lưu lại không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Xe xe = getXe();
                        update(xe);
                        Snackbar.make(view, "Lưu thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
