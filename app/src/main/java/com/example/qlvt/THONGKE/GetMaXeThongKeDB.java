package com.example.qlvt.THONGKE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.qlvt.QLPhanCong.InsertPhanCongActivity;
import com.example.qlvt.QLXe.Xe;

import java.util.ArrayList;

public class GetMaXeThongKeDB extends SQLiteOpenHelper{
    private static final String DB_NAME = "QLVT.db";
    private static final int DB_VER = 1;

    // Define table TaiXe
    private static final String TB_XE = "XE";
    private static final String COL_XE_MAXE = "MaXe";

    public GetMaXeThongKeDB(InsertPhanCongActivity context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public GetMaXeThongKeDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_XE);
        onCreate(db);
    }

    public void getMaXe(ArrayList<String> xes) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_XE, new String[]{
                COL_XE_MAXE
        }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Xe xe = new Xe();

                xe.setMaXe(cursor.getString(cursor.getColumnIndex(COL_XE_MAXE)));
                xes.add(xe.getMaXe());
            } while (cursor.moveToNext());
        }
    }

}
