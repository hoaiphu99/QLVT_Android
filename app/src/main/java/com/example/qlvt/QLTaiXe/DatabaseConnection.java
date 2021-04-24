package com.example.qlvt.QLTaiXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseConnection extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLVT.db";
    private static final int DB_VER = 1;

    // Define table TaiXe
    private static final String TB_TAIXE = "TAIXE";
    private static final String COL_TAIXE_MATX = "MaTX";
    private static final String COL_TAIXE_TENTX = "TenTX";
    private static final String COL_TAIXE_NGAYSINH = "NgaySinh";
    private static final String COL_TAIXE_DIACHI = "DiaChi";

    public DatabaseConnection(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public DatabaseConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String script = "CREATE TABLE " + TB_PHANCONG + " (" +
//                COL_PHANCONG_SOPHIEU + " INTEGER PRIMARY KEY NOT NULL," +
//                COL_PHANCONG_MAXE + " TEXT," +
//                COL_PHANCONG_MATUYEN + " TEXT," +
//                COL_PHANCONG_NGAY + " TEXT," +
//                COL_PHANCONG_XUATPHAT + " TEXT)";
//        // execute script
//        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_TAIXE);
        onCreate(db);
    }

    public void getTaiXe(ArrayList<TaiXe> taiXes) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_TAIXE, new String[]{
                COL_TAIXE_MATX, COL_TAIXE_TENTX, COL_TAIXE_NGAYSINH, COL_TAIXE_DIACHI
        }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                TaiXe taiXe = new TaiXe();

                taiXe.setMaTaiXe(cursor.getString(cursor.getColumnIndex(COL_TAIXE_MATX)));
                taiXe.setTenTaiXe(cursor.getString(cursor.getColumnIndex(COL_TAIXE_TENTX)));
                taiXe.setNgaySinh(cursor.getString(cursor.getColumnIndex(COL_TAIXE_NGAYSINH)));
                taiXe.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_TAIXE_DIACHI)));
                taiXes.add(taiXe);
            } while (cursor.moveToNext());
        }
    }

    public void insert(TaiXe taiXe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TAIXE_MATX, taiXe.getMaTaiXe());
        values.put(COL_TAIXE_TENTX, taiXe.getTenTaiXe());
        values.put(COL_TAIXE_NGAYSINH, taiXe.getNgaySinh());
        values.put(COL_TAIXE_DIACHI, taiXe.getDiaChi());


        db.insert(TB_TAIXE, null, values);

        db.close();
    }

    public void update(TaiXe taiXe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TAIXE_MATX, taiXe.getMaTaiXe());
        values.put(COL_TAIXE_TENTX, taiXe.getTenTaiXe());
        values.put(COL_TAIXE_NGAYSINH, taiXe.getNgaySinh());
        values.put(COL_TAIXE_DIACHI, taiXe.getDiaChi());

        db.update(TB_TAIXE, values, COL_TAIXE_MATX + " ='" + taiXe.getMaTaiXe() + "'", null);
        db.close();
    }

    public void delete(String maTX) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_TAIXE, COL_TAIXE_MATX + " = '" + maTX + "'", null);
        db.close();
    }
}
