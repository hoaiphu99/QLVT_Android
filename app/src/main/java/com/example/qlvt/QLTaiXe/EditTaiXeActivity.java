package com.example.qlvt.QLTaiXe;

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

public class EditTaiXeActivity extends AppCompatActivity {
    static final String MA_TAI_XE = "maTX", TEN_TAI_XE = "tenTX", NGAYSINH = "ngaySinh", DIACHI = "diaChi";
    boolean isUpdate;
    //int idTaiXe;
    EditText editMa, editTen, editNgaySinh, editDiaChi;
    TaiXe taiXe;
    int secs = 1;
    Button btnSua, btnXoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltaixe_edit);

        setControl();
        setEvent();
    }

    public void setControl() {
        editMa = findViewById(R.id.ma_tx);
        editTen = findViewById(R.id.ten_tx);
        editNgaySinh = findViewById(R.id.ngay_sinh_tx);
        editDiaChi = findViewById(R.id.dia_chi_tx);
        btnSua = findViewById(R.id.btnSave);
        btnXoa = findViewById(R.id.btnDelete);
    }

    public void setEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            editMa.setText(bundle.getString(MA_TAI_XE));
            editTen.setText(bundle.getString(TEN_TAI_XE));
            editNgaySinh.setText(bundle.getString(NGAYSINH));
            editDiaChi.setText(bundle.getString(DIACHI));

        }

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMa.length() > 0 || editTen.length() > 0 || editNgaySinh.length() > 0 || editDiaChi.length() > 0) {

                    TaiXe taiXe = getTaiXe();
                    Log.e("update", "" + taiXe.getMaTaiXe());
                    update(taiXe);
                    isUpdate = true;
                    Snackbar.make(v, "Sửa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    Utils.delay(secs, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            Intent intent = new Intent(EditTaiXeActivity.this, QuanLyTaiXe.class);
                            startActivity(intent);
                        }
                    });
                    //Toast.makeText(UpdateDeleteActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditTaiXeActivity.this, "Có thông tin chưa nhập! Kiểm tra lại", Toast.LENGTH_SHORT).show();
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

    public TaiXe getTaiXe() {
        TaiXe taiXe = new TaiXe();
        taiXe.setMaTaiXe(editMa.getText().toString());
        taiXe.setTenTaiXe(editTen.getText().toString());
        taiXe.setNgaySinh(editNgaySinh.getText().toString());
        taiXe.setDiaChi(editDiaChi.getText().toString());

        return taiXe;
    }

    public void update(TaiXe taiXe) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.update(taiXe);
    }

    public void delete(String maTX) {
        DatabaseConnection db = new DatabaseConnection(this);
        db.delete(maTX);
    }

    public void openAlertDialogDelete(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa?")
                .setMessage("Bạn có chắc chắn xóa tài xế này không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaiXe taiXe = getTaiXe();
                        delete(taiXe.getMaTaiXe());
                        Snackbar.make(view, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                        Utils.delay(secs, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                Intent intent = new Intent(EditTaiXeActivity.this, QuanLyTaiXe.class);
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
                        TaiXe taiXe = getTaiXe();
                        update(taiXe);
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


        /*Intent intent = getIntent();
        isUpdate = intent.getBooleanExtra("isUpdate", false);

        if (isUpdate) {
            //Activity hoạt động biên tập dữ liệu Sản phẩm đã

            //Đọc sản phẩm

            idTaiXe = intent.getIntExtra("idTaiXe", 0);

            SQLiteDatabase db = openOrCreateDatabase(QuanLyTaiXe.DB_NAME, Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * from TAIXE where MaTX = ?",
                    new String[]{idTaiXe + ""});
            cursor.moveToFirst();
            String maTX  = cursor.getString(0);
            String tenTX = cursor.getString(1);
            String ngaySinh = cursor.getString(2);
            String diaChi = cursor.getString(3);
            taiXe = new TaiXe(maTX, tenTX, ngaySinh, diaChi);
            cursor.close();

            findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = openOrCreateDatabase(QuanLyTaiXe.DB_NAME, Context.MODE_PRIVATE, null);
                    db.execSQL("DELETE FROM TAIXE where MaTX = ?", new String[]{String.valueOf(idTaiXe)});
                    db.close();
                    finish();
                }
            });


        } else {
            //Activity nhâp dữ liệu thêm Sản phẩm mới

            taiXe = new TaiXe("", "", "", "");
            //khi vào form edit với chức năng thêm thì set nút save bằng tạo tài xế mới
            findViewById(R.id.btnDelete).setVisibility(View.GONE);
            ((Button) findViewById(R.id.btnSave)).setText("Tạo tài xế mới");
        }

        //Update to View
        editMa = findViewById(R.id.ma_tx);
        editTen = findViewById(R.id.ten_tx);
        editNgaySinh = findViewById(R.id.ngay_sinh_tx);
        editDiaChi = findViewById(R.id.dia_chi_tx);

        editMa.setText(taiXe.maTaiXe);
        editTen.setText(taiXe.tenTaiXe);
        editNgaySinh.setText(taiXe.ngaySinh);
        editDiaChi.setText(taiXe.diaChi);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase(QuanLyTaiXe.DB_NAME, Context.MODE_PRIVATE, null);
                taiXe.maTaiXe = editMa.getText().toString();
                taiXe.tenTaiXe = editTen.getText().toString();
                taiXe.diaChi = editDiaChi.getText().toString();
                taiXe.ngaySinh = editNgaySinh.getText().toString();


                if (isUpdate) {
                    //Cập nhật
                    db.execSQL("UPDATE TAIXE SET TenTX = ?, NgaySinh = ?, DiaChi = ? where  MaTX = ?",
                            new String[]{taiXe.tenTaiXe + "", taiXe.diaChi + "", taiXe.ngaySinh + ""});
                } else {
                    //Tạo
                    //Cập nhật
                    db.execSQL("INSERT INTO TAIXE (MaTX, TenTX, NgaySinh, DiaChi ) VALUES (?,?,?,?)",
                            new String[]{taiXe.maTaiXe+"",taiXe.tenTaiXe+"", taiXe.ngaySinh + "", taiXe.diaChi + ""});
                }
                db.close();
                finish();
            }
        });
    }*/

    }
