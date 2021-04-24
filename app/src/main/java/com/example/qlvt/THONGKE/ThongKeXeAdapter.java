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

public class ThongKeXeAdapter extends ArrayAdapter<ThongKeXe> {
    Context context;
    int resource;
    ArrayList<ThongKeXe> data = null;

    public ThongKeXeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ThongKeXe> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    static class XeHolder {
        TextView tv_maXe, tv_soLan;
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
            row = inflater.inflate(R.layout.thongke_xe_list, parent, false);

            holder.tv_maXe = row.findViewById(R.id.tv_maxe);
            holder.tv_soLan = row.findViewById(R.id.tv_solan);

            row.setTag(holder);
        }

        final ThongKeXe xe = data.get(position);

        holder.tv_maXe.setText("Mã xe: " + xe.getMaXe());
        holder.tv_soLan.setText("Số lần: " + xe.getSoLan());
        return row;
    }
}
