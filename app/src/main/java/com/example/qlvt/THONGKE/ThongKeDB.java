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
    private static final String TB_THONGKE = "THONGKE";
    private static final String COL_THONGKE_MAXE = "MaXe";
    private static final String COL_THONGKE_SOLAN = "SoLan";

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

    public void thongKe(ArrayList<ThongKe> thongKes) {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery("Select MaXe, (count(MaXe)) as SL  From PHANCONG Group by MaXe", null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        ThongKe tk = new ThongKe();
                        tk.setMaXe(cursor.getString(cursor.getColumnIndex("MaXe")));
                        tk.setSoLan(cursor.getString(cursor.getColumnIndex("SL")));
                        thongKes.add(tk);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
    }

    public void insert(PhanCong phanCong) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_THONGKE_MAXE, phanCong.getMaXe());
//        values.put(COL_THONGKE_SOLAN, phanCong.get);

        db.insert(TB_THONGKE, null, values);

        db.close();
    }
}
