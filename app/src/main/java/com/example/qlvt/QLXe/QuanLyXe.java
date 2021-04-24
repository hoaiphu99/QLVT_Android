package com.example.qlvt.QLXe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.qlvt.MainActivity;
import com.example.qlvt.QLPhanCong.QuanLyPhanCong;
import com.example.qlvt.QLTaiXe.QuanLyTaiXe;
import com.example.qlvt.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

class Utils {
    public interface DelayCallback {
        void afterDelay();
    }

    public static void delay(int secs, final DelayCallback delayCallback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}

public class QuanLyXe extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static String DB_NAME = "QLVT.db", MA_XE = "maXe", TEN_XE = "tenTX", NAM_SX = "namSX", MA_TX = "maTX", IMG = "IMG";
    private static final int DB_VERSION = 1;

    ImageView img_xe;
    ListView list_DSXe;
    Button btnThem;
    ArrayList<Xe> data = new ArrayList<>();
    XeAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlxe_main);


        setControl();
        setEvent();
        loadData();
        adapter.notifyDataSetChanged();

    }


    private void setControl() {
        list_DSXe = findViewById(R.id.list_DSXe);
        btnThem = findViewById(R.id.btn_add);
        img_xe = findViewById(R.id.img_xe);
        DrawerLayout drawer = findViewById(R.id.draw_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setEvent() {
        loadData();

        adapter = new XeAdapter(this, R.layout.qlxe_list, data);
        list_DSXe.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list_DSXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Xe xe = data.get(position);

                Intent intent = new Intent(QuanLyXe.this, EditXeActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(MA_XE, xe.getMaXe());
                bundle.putString(TEN_XE, xe.getTenXe());
                bundle.putString(NAM_SX, xe.getNamSX());
                bundle.putString(MA_TX, xe.getMaTX());
                bundle.putByteArray(IMG, xe.getImgXe());

                intent.putExtras(bundle);

                startActivity(intent);
            }

        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyXe.this, InsertXeActivity.class);
                startActivity(intent);
            }
        });
    }

    //load dữ liệu từ table xe ra
    public void loadData() {
        XeDatabase db = new XeDatabase(this);
        data.clear();
        db.getXe(data);
        //adapter.notifyDataSetChanged();
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
            Intent intent = new Intent(QuanLyXe.this, InsertXeActivity.class);
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
    public void openAlertDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Opps...")
                .setMessage("Chức năng này chưa thêm! Vui lòng thử lại sau!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("xxx", "OnResume");
        loadData();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.e("vlxx", "delra");
        if(id == R.id.dashboard){
            Intent cartIntent = new Intent(QuanLyXe.this, MainActivity.class);
            startActivity(cartIntent);


        }
        if (id == R.id.qlphancong) {
            Intent cartIntent = new Intent(QuanLyXe.this, QuanLyPhanCong.class);
            startActivity(cartIntent);


        } else if (id == R.id.qltaixe) {
            Intent cartIntent = new Intent(QuanLyXe.this, QuanLyTaiXe.class);
            startActivity(cartIntent);


        } else if (id == R.id.qltuyen) {
            /*Intent orderIntent = new Intent(Home.this, OrderStatus.class);
            startActivity(orderIntent);*/

        } else if (id == R.id.qlxe) {
            Log.e("vlxx", "delra");
            Intent cartIntent = new Intent(QuanLyXe.this, QuanLyXe.class);
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
