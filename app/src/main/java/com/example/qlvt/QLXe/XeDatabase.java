package com.example.qlvt.QLXe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class XeDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLVT.db";
    private static final int DB_VER = 1;

    // Define table TaiXe
    private static final String TB_XE = "XE";
    private static final String COL_XE_MAXE = "MaXe";
    private static final String COL_XE_TENXE = "TenXe";
    private static final String COL_XE_NAMSX = "NamSX";
    private static final String COL_XE_MATX = "MaTX";
    private static final String COL_XE_IMG = "IMG";


    public XeDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public XeDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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

    public void getXe(ArrayList<Xe> xes) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_XE, new String[]{
                COL_XE_MAXE, COL_XE_TENXE, COL_XE_NAMSX, COL_XE_MATX, COL_XE_IMG
        }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Xe xe = new Xe();

                xe.setMaXe(cursor.getString(cursor.getColumnIndex(COL_XE_MAXE)));
                xe.setTenXe(cursor.getString(cursor.getColumnIndex(COL_XE_TENXE)));
                xe.setNamSX(cursor.getString(cursor.getColumnIndex(COL_XE_NAMSX)));
                xe.setMaTX(cursor.getString(cursor.getColumnIndex(COL_XE_MATX)));
                xe.setImgXe(cursor.getBlob(cursor.getColumnIndex(COL_XE_IMG)));
                xes.add(xe);
            } while (cursor.moveToNext());
        }
    }

    public void insert(Xe xe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_XE_MAXE, xe.getMaXe());
        values.put(COL_XE_TENXE, xe.getTenXe());
        values.put(COL_XE_NAMSX, xe.getNamSX());
        values.put(COL_XE_MATX, xe.getMaTX());
        values.put(COL_XE_IMG, xe.getImgXe());

        db.insert(TB_XE, null, values);

        db.close();
    }


    public void update(Xe xe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_XE_MAXE, xe.getMaXe());
        values.put(COL_XE_TENXE, xe.getTenXe());
        values.put(COL_XE_NAMSX, xe.getNamSX());
        values.put(COL_XE_MATX, xe.getMaTX());
        values.put(COL_XE_IMG, xe.getImgXe());

        db.update(TB_XE, values, COL_XE_MAXE + " ='" + xe.getMaXe() + "'", null);
        db.close();
    }

    public void delete(String maXe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_XE, COL_XE_MAXE + " = '" + maXe + "'", null);
        db.close();
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
