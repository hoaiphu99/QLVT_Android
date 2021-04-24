package com.example.qlvt.QLXe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlvt.R;

import java.util.ArrayList;

public class XeAdapter extends ArrayAdapter<Xe> {
    Context context;
    int resource;
    ArrayList<Xe> data = null;

    public XeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Xe> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class XeHolder {
        //ImageView imgDelete;
        TextView tv_maXe, tv_tenXe, tv_namSX, tv_maTaiXe;
        ImageView img_xe;
        public XeHolder() {
        }
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        XeHolder holder = null;
        if(row != null) {
            holder = (XeHolder) row.getTag();
        }

        else {
            holder = new XeHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.qlxe_list, parent, false);

            //holder.imgDelete = row.findViewById(R.id.imgDelete);
            holder.tv_maXe = row.findViewById(R.id.ma_xe);
            holder.tv_tenXe = row.findViewById(R.id.ten_xe);
            holder.img_xe = row.findViewById(R.id.img_xe);
            row.setTag(holder);
        }

        final Xe xe = data.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(xe.getImgXe(), 0, xe.getImgXe().length);
        holder.img_xe.setImageBitmap(bitmap);
        holder.tv_maXe.setText(xe.getMaXe());
        holder.tv_tenXe.setText(xe.getTenXe());
        return row;
    }
}
