package com.example.qlvt.QLPhanCong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PhanCongDatabase extends SQLiteOpenHelper {

    private static String DB_NAME = "QLVT.db";
    private static int DB_VERSION = 1;

    // Define table PhanCong
    private static final String TB_PHANCONG = "PHANCONG";
    private static final String COL_PHANCONG_SOPHIEU = "SoPhieu";
    private static final String COL_PHANCONG_MAXE = "MaXe";
    private static final String COL_PHANCONG_MATUYEN = "MaTuyen";
    private static final String COL_PHANCONG_NGAY = "Ngay";
    private static final String COL_PHANCONG_XUATPHAT = "XuatPhat";
    private static final String COL_PHANCONG_NOIDEN = "NoiDen";

    public PhanCongDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public PhanCongDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
        db.execSQL("DROP TABLE IF EXISTS " + TB_PHANCONG);
        onCreate(db);
    }

    public void getPhanCong(ArrayList<PhanCong> phanCongs) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_PHANCONG, new String[]{
                COL_PHANCONG_SOPHIEU, COL_PHANCONG_MAXE, COL_PHANCONG_MATUYEN, COL_PHANCONG_NGAY, COL_PHANCONG_XUATPHAT, COL_PHANCONG_NOIDEN
        }, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            do {
                PhanCong phanCong = new PhanCong();
                phanCong.setSoPhieu(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_SOPHIEU)));
                phanCong.setMaXe(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_MAXE)));
                phanCong.setMaTuyen(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_MATUYEN)));
                phanCong.setNgay(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_NGAY)));
                phanCong.setXuatPhat(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_XUATPHAT)));
                phanCong.setNoiDen(cursor.getString(cursor.getColumnIndex(COL_PHANCONG_NOIDEN)));
                phanCongs.add(phanCong);
            } while (cursor.moveToNext());
        }
    }

    public void insert(PhanCong phanCong) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_PHANCONG_SOPHIEU, phanCong.getSoPhieu());
        values.put(COL_PHANCONG_MAXE, phanCong.getMaXe());
        values.put(COL_PHANCONG_MATUYEN, phanCong.getMaTuyen());
        values.put(COL_PHANCONG_NGAY, phanCong.getNgay());
        values.put(COL_PHANCONG_XUATPHAT, phanCong.getXuatPhat());
        values.put(COL_PHANCONG_NOIDEN, phanCong.getNoiDen());

        db.insert(TB_PHANCONG, null, values);

        db.close();
    }

    public void update(PhanCong phanCong) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_PHANCONG_MAXE, phanCong.getMaXe());
        values.put(COL_PHANCONG_MATUYEN, phanCong.getMaTuyen());
        values.put(COL_PHANCONG_NGAY, phanCong.getNgay());
        values.put(COL_PHANCONG_XUATPHAT, phanCong.getXuatPhat());
        values.put(COL_PHANCONG_NOIDEN, phanCong.getNoiDen());

        db.update(TB_PHANCONG, values, COL_PHANCONG_SOPHIEU + " ='" + phanCong.getSoPhieu() + "'", null);
        db.close();
    }

    public void delete(String soPhieu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_PHANCONG, COL_PHANCONG_SOPHIEU + " = '" + soPhieu + "'", null);
        db.close();
    }


}
