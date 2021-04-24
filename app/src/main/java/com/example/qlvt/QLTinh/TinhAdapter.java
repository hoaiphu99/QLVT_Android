package com.example.qlvt.QLTinh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlvt.QLTaiXe.TaiXe;
import com.example.qlvt.R;

import java.util.ArrayList;

public class TinhAdapter  extends ArrayAdapter<Tinh> {
    Context context;
    int resource;
    ArrayList<Tinh> data = null;

    public TinhAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Tinh> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class TinhHolder {
        TextView tv_maTinh, tv_tenTinh;

        public TinhHolder() {
        }
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TinhAdapter.TinhHolder holder = null;
        if (row != null) {
            holder = (TinhAdapter.TinhHolder) row.getTag();
        } else {
            holder = new TinhAdapter.TinhHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.qltinh_list, parent, false);

            //holder.imgDelete = row.findViewById(R.id.imgDelete);
            holder.tv_maTinh = row.findViewById(R.id.ma_tinh);
            holder.tv_tenTinh = row.findViewById(R.id.ten_tinh);
            row.setTag(holder);
        }

        final Tinh tinh = data.get(position);
        holder.tv_maTinh.setText(tinh.getMaTinh());
        holder.tv_tenTinh.setText(tinh.getTenTinh());


        return row;
    }
}
