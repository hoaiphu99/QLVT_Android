package com.example.qlvt.QLPhanCong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlvt.QLXe.XeDatabase;
import com.example.qlvt.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class EditPhanCongActivity extends AppCompatActivity {
    static String SO_PHIEU = "soPhieu", MA_XE = "maXa", MA_TUYEN = "maTuyen", NGAY = "ngay", XUAT_PHAT = "xuatPhat", NOI_DEN = "noiDen";
    boolean isSave = false;
    int secs = 1, indexMaxe = 0;
    EditText et_soPhieu, et_maTuyen, et_ngay, et_xuatPhat, et_noiDen;
    Button btn_Luu, btn_Xoa;
    ArrayList<String> spnXes = new ArrayList<>();
    ArrayList<String> spnTuyens = new ArrayList<>();
    ArrayList<String> spnTinhs = new ArrayList<>();
    ArrayList<PhanCong> data = new ArrayList<>();
    Spinner spnXe, spnTuyen;
    String et_maXe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlphancong_edit);

        setControl();
        setEvent();
    }

    public void setControl() {
        et_soPhieu = findViewById(R.id.et_soPhieu);
        //et_maXe = findViewById(R.id.et_maXe);
        et_maTuyen = findViewById(R.id.et_maTuyen);
        et_ngay = findViewById(R.id.et_ngay);
        et_xuatPhat = findViewById(R.id.et_xuatPhat);
        et_noiDen = findViewById(R.id.et_noiDen);
        spnXe = findViewById(R.id.spin_maXe);
        btn_Luu = findViewById(R.id.btn_Luu);
        btn_Xoa = findViewById(R.id.btn_Xoa);
    }

    public void setEvent() {
        XeDatabase db = new XeDatabase(this);
        spnXes.clear();
        db.getMaXe(spnXes);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            et_soPhieu.setText(bundle.getString(SO_PHIEU));
            setSpinerXe(bundle.getString(MA_XE));
            //et_maXe.setText(bundle.getString(MA_XE));
            et_maTuyen.setText(bundle.getString(MA_TUYEN));
            et_ngay.setText(bundle.getString(NGAY));
            et_xuatPhat.setText(bundle.getString(XUAT_PHAT));
            et_noiDen.setText(bundle.getString(NOI_DEN));
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spnXes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnXe.setAdapter(adapter);
        spnXe.setSelection(indexMaxe);
        spnXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_maXe = spnXes.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_soPhieu.length() > 0 && et_maXe.length() > 0 && et_maTuyen.length() > 0 && et_ngay.length() > 0 && et_xuatPhat.length() > 0 && et_noiDen.length() > 0) {
                    PhanCong phanCong = getPhanCong();
                    if(et_xuatPhat.getText().toString().equalsIgnoreCase(et_noiDen.getText().toString())){
                        Snackbar.make(v, "Điếm đến và xuất phát không được trùng!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    }
                    else if (checkAgenda(phanCong) == 0) {
                        update(phanCong);
                        isSave = true;
                        Snackbar.make(v, "Sửa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    }
                    else
                        Snackbar.make(v, "Trùng lịch xe! Vui lòng xem lại", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    //Toast.makeText(InsertActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(v, "Chưa nhập thông tin!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                }

            }
        });


        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(openAlertDialogDelete() == 1) {
                    PhanCong phanCong = getPhanCong();
                    delete(phanCong.getSoPhieu());
                    Snackbar.make(v, "Xóa thành công!", Snackbar.LENGTH_SHORT).setAction(null, null).show();
                    Utils.delay(secs, new Utils.DelayCallback() {
                        @Override
                        public void afterDelay() {
                            Intent intent = new Intent(EditPhanCongActivity.this, QuanLyPhanCong.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    public void setSpinerXe(String maXe) {
        for (int i = 0; i < spnXes.size(); i++) {
            if(maXe.equalsIgnoreCase(spnXes.get(i)))
                indexMaxe = i;
        }
    }

    public PhanCong getPhanCong() {
        PhanCong phanCong = new PhanCong();
        phanCong.setSoPhieu(et_soPhieu.getText().toString());
        phanCong.setMaXe(et_maXe);
        phanCong.setMaTuyen(et_maTuyen.getText().toString());
        phanCong.setNgay(et_ngay.getText().toString());
        phanCong.setXuatPhat(et_xuatPhat.getText().toString());
        phanCong.setNoiDen(et_noiDen.getText().toString());

        return phanCong;
    }

    public void update(PhanCong phanCong) {
        PhanCongDatabase db = new PhanCongDatabase(this);
        db.update(phanCong);
    }

    public void delete(String soPhieu) {
        PhanCongDatabase db = new PhanCongDatabase(this);
        db.delete(soPhieu);
    }

    public int openAlertDialogDelete() {
        int[] check = {0};
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa?")
                .setMessage("Bạn có chắc chắn xóa số phiếu này không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        check[0] = 1;
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        check[0] = 0;
                    }
                })
                .show();
        return check[0];
    }
    public void loadData() {
        PhanCongDatabase db = new PhanCongDatabase(this);
        data.clear();
        db.getPhanCong(data);
    }
    public int checkAgenda(PhanCong phanCong) {
        ArrayList<PhanCong> tempNgay = new ArrayList<>();
        ArrayList<PhanCong> tempXe = new ArrayList<>();
        loadData();
        for (int i = 0; i < data.size(); i++) {
            if (phanCong.getNgay().equalsIgnoreCase(data.get(i).getNgay())) {
                tempNgay.add(data.get(i));
            }
        }

        for (int i = 0; i < tempNgay.size(); i++) {
            if (phanCong.getMaXe().equalsIgnoreCase(tempNgay.get(i).getMaXe())) {
                tempXe.add(tempNgay.get(i));
            }
        }
        tempNgay.clear();

        for (int i = 0; i < tempXe.size(); i++) {
            Log.e("zzz", "Xe " + tempXe.get(i).getMaXe());
            if (phanCong.getXuatPhat().equalsIgnoreCase(tempXe.get(i).getXuatPhat()) && phanCong.getNoiDen().equalsIgnoreCase(tempXe.get(i).getNoiDen())) {
                return 1;
            }
        }

        return 0;
    }

    public void openAlertDialogUpdate(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận lưu?")
                .setMessage("Nhưng thay đổi bạn đã nhập chưa được lưu. Bạn có muốn lưu lại không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhanCong phanCong = getPhanCong();
                        update(phanCong);
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