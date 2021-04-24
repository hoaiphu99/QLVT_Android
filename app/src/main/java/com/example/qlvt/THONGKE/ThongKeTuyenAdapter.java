package com.example.qlvt.THONGKE;

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

public class ThongKeTuyenAdapter extends ArrayAdapter<ThongKeTuyen> {
    Context context;
    int resource;
    ArrayList<ThongKeTuyen> data = null;

    public ThongKeTuyenAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ThongKeTuyen> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class TuyenHolder {
        TextView tv_maTuyen, tv_soLan;
        public TuyenHolder() {
        }
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        TuyenHolder holder = null;
        if(row != null) {
            holder = (TuyenHolder) row.getTag();
        }

        else {
            holder = new TuyenHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.thongke_tuyen_list, parent, false);

            holder.tv_maTuyen = row.findViewById(R.id.tv_matuyen);
            holder.tv_soLan = row.findViewById(R.id.tv_solan);

            row.setTag(holder);
        }

        final ThongKeTuyen xe = data.get(position);

        holder.tv_maTuyen.setText("Mã tuyến: " + xe.getMaTuyen());
        holder.tv_soLan.setText("Số lần: " + xe.getSoLan());
        return row;
    }
}
