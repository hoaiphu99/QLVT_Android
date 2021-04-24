package com.example.qlvt.THONGKE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.qlvt.QLPhanCong.PhanCong;

import java.util.ArrayList;

public class ThongKeDB extends SQLiteOpenHelper {

    ArrayList<String> maXes = new ArrayList<>();

    private static String DB_NAME = "QLVT.db";
    private static int DB_VERSION = 1;

    // Define table PhanCong
    private static final String TB_PHANCONG = "PHANCONG";
    private static final String COL_THONGKE_MAXE = "MaXe";
    private static final String COL_THONGKE_MATUYEN = "MaTuyen";
    private static final String COL_THONGKE_SOLAN = "SL";

    public ThongKeDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public ThongKeDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void thongKeXe(ArrayList<ThongKeXe> thongKes) {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery("Select " + COL_THONGKE_MAXE + ", (count(" + COL_THONGKE_MAXE + ")) as " + COL_THONGKE_SOLAN + "  From " + TB_PHANCONG + " Group by " + COL_THONGKE_MAXE, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        ThongKeXe tk = new ThongKeXe();
                        tk.setMaXe(cursor.getString(cursor.getColumnIndex(COL_THONGKE_MAXE)));
                        tk.setSoLan(cursor.getString(cursor.getColumnIndex(COL_THONGKE_SOLAN)));
                        thongKes.add(tk);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
    }

    public void thongKeTuyen(ArrayList<ThongKeTuyen> thongKes) {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery("Select " + COL_THONGKE_MATUYEN + ", (count(" + COL_THONGKE_MATUYEN + ")) as " + COL_THONGKE_SOLAN + "  From " + TB_PHANCONG + " Group by " + COL_THONGKE_MATUYEN, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        ThongKeTuyen tk = new ThongKeTuyen();
                        tk.setMaTuyen(cursor.getString(cursor.getColumnIndex(COL_THONGKE_MATUYEN)));
                        tk.setSoLan(cursor.getString(cursor.getColumnIndex(COL_THONGKE_SOLAN)));
                        thongKes.add(tk);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
    }

}
