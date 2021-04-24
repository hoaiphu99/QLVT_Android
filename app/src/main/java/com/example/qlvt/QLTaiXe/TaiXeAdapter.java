package com.example.qlvt.QLTaiXe;

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

import com.example.qlvt.R;

import java.util.ArrayList;

public class TaiXeAdapter extends ArrayAdapter<TaiXe> {
    Context context;
    int resource;
    ArrayList<TaiXe> data = null;

    public TaiXeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TaiXe> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class TaiXeHolder {
        TextView tv_matx, tv_tentx, tv_ngaySinh, tv_diaChi;

        public TaiXeHolder() {
        }
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TaiXeHolder holder = null;
        if (row != null) {
            holder = (TaiXeHolder) row.getTag();
        } else {
            holder = new TaiXeHolder();
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.qltaixe_list, parent, false);

            //holder.imgDelete = row.findViewById(R.id.imgDelete);
            holder.tv_matx = row.findViewById(R.id.ma_tx);
            holder.tv_tentx = row.findViewById(R.id.ten_tx);
//            holder.tv_ngaySinh = row.findViewById(R.id.ngay_sinh_tx);
//            holder.tv_diaChi = row.findViewById(R.id.dia_chi_tx);
            row.setTag(holder);
        }

        final TaiXe taiXe = data.get(position);
        holder.tv_matx.setText(taiXe.getMaTaiXe());
        holder.tv_tentx.setText(taiXe.getTenTaiXe());
//        holder.tv_ngaySinh.setText(taiXe.getNgaySinh());
//        holder.tv_diaChi.setText(taiXe.getDiaChi());
        /*holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseConnection db = new DatabaseConnection(context);
                db.delete(phanCong.getSoPhieu());
            }
        });*/

        return row;
    }
}
