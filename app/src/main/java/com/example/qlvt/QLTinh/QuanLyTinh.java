package com.example.qlvt.QLTinh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qlvt.MainActivity;
import com.example.qlvt.QLPhanCong.QuanLyPhanCong;
import com.example.qlvt.QLTaiXe.QuanLyTaiXe;
import com.example.qlvt.QLXe.QuanLyXe;
import com.example.qlvt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

class Utils {
    public interface DelayCallback {
        void afterDelay();
    }

    public static void delay(int secs, final com.example.qlvt.QLTinh.Utils.DelayCallback delayCallback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}

public class QuanLyTinh extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static String MA_TINH = "MaTinh", TEN_TINH = "TenTinh";
    ListView list_DSTinh;
    ArrayList<Tinh> data = new ArrayList<>();
    TinhAdapter adapter = null;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qltinh_main);

        setControl();
        setEvent();
        loadData();
        adapter.notifyDataSetChanged();
    }

    public void setControl() {
        list_DSTinh = findViewById(R.id.list_DSTinh);
        btnThem = findViewById(R.id.btn_add);
    }

    public void setEvent() {

        //loadData();


        adapter = new TinhAdapter(this, R.layout.qltinh_list, data);
        list_DSTinh.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list_DSTinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tinh tinh = data.get(position);

                Intent intent = new Intent(QuanLyTinh.this, EditTinhActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(MA_TINH, tinh.getMaTinh());
                bundle.putString(TEN_TINH, tinh.getTenTinh());

                intent.putExtras(bundle);

                startActivity(intent);
            }

        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyTinh.this, InsertTinhActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadData() {
        DatabaseConnection db = new DatabaseConnection(this);
        data.clear();
        db.getTinh(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_insert) {
            Intent intent = new Intent(QuanLyTinh.this, InsertTinhActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_refresh) {
            Log.e("xxx", "Vao refresh");
            loadData();
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e("vlxx", "delra");
        if (id == R.id.dashboard) {
            Intent cartIntent = new Intent(QuanLyTinh.this, MainActivity.class);
            startActivity(cartIntent);


        }
        if (id == R.id.qlphancong) {
            Intent cartIntent = new Intent(QuanLyTinh.this, QuanLyPhanCong.class);
            startActivity(cartIntent);


        } else if (id == R.id.qltaixe) {
            Log.e("vlxx", "delra");
            Intent cartIntent = new Intent(QuanLyTinh.this, QuanLyTaiXe.class);
            startActivity(cartIntent);


        } else if (id == R.id.qltuyen) {
            /*Intent orderIntent = new Intent(Home.this, OrderStatus.class);
            startActivity(orderIntent);*/

        } else if (id == R.id.qlxe) {
            Intent cartIntent = new Intent(QuanLyTinh.this, QuanLyXe.class);
            startActivity(cartIntent);

        } else if (id == R.id.qltinh) {
            /*Intent signIn = new Intent(Home.this, SignIn.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(signIn);*/

        }

        DrawerLayout drawerLayout = findViewById(R.id.draw_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
