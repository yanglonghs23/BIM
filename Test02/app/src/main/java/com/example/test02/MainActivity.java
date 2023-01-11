package com.example.test02;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test02.entity.BtState;
import com.example.test02.entity.DeviceConnect;
import com.example.test02.entity.DevicePair;
import com.example.test02.entity.UserBehavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// activity 生命周期
// onCreate onStart onResume onPause onStop onDestroy
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static int nums = 0;  // 饿汉初始化

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nums += 1;
        System.out.println("***********************");
        System.out.println("in onCreate:" + nums);
        System.out.println("***********************");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 保证只第一次调用
        if(nums == 1){
            //权限检查
            checkPermission();
            // 开始采集
            beginCollect();
        }
        // 显示数据
        getData();
//        // 开启服务
//        Intent intent = new Intent(MainActivity.this, MainService.class);
//        startService(intent);

        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_open).setOnClickListener(this);
        findViewById(R.id.btn_get_data).setOnClickListener(this);
        findViewById(R.id.btn_end_collect).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @RequiresApi(api = Build.VERSION_CODES.S)

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                new BluetoothManager(this).close();
                break;
            case R.id.btn_open:
                new BluetoothManager(this).open();
                break;
            case R.id.btn_get_data:
                getData();
                break;
            case R.id.btn_end_collect:
                endCollect();
                break;
//            case R.id.btn_get_data:
//                System.out.println(getFilesDir().getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_ALARMS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_AUDIOBOOKS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_PODCASTS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_RECORDINGS).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_RINGTONES).getAbsolutePath());
//                System.out.println(getExternalFilesDir(Environment.DIRECTORY_SCREENSHOTS).getAbsolutePath());
//
//                System.out.println(Environment.getDataDirectory());
//                System.out.println(Environment.getDownloadCacheDirectory());
//                System.out.println(Environment.getExternalStorageDirectory());
//                System.out.println(Environment.getRootDirectory());
//                System.out.println(Environment.getExternalStorageState());
//                System.out.println(Environment.getStorageDirectory());
                // 权限检查和动态申请
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);
//                    return;  // 被拒绝要退出
//                }

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    // 先判断有没有权限
//                    if (!Environment.isExternalStorageManager()) {
//                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                        intent.setData(Uri.parse("package:" + getPackageName()));
//                        startActivityForResult(intent, 1);
//                    }
//                }
//                readLocalFile();
        }
    }

    private void getData() {
        Collection collection = Collection.getInstance();
        System.out.println("--------obj----------");
        System.out.println(collection);
        System.out.println("in BtState");
        TableLayout table = findViewById(R.id.table1);
        table.removeViews(1,table.getChildCount()-1);
        for(BtState e : collection.getBtStates()){
            System.out.println(e);
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText(e.getDateTime());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(String.valueOf(e.getIsOpen()));
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(String.valueOf(e.getIsClose()));
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView3);
            TextView textView4 = new TextView(this);
            textView4.setText(String.valueOf(e.getLongitude()));
            textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView4);
            TextView textView5 = new TextView(this);
            textView5.setText(String.valueOf(e.getLatitude()));
            textView5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView5);
            table.addView(tableRow);
        }
        System.out.println("in DeviceConnect");
        table = findViewById(R.id.table2);
        table.removeViews(1,table.getChildCount()-1);
        for(DeviceConnect e : collection.getDeviceConnects()){
            System.out.println(e);
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText(e.getDateTime());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(e.getDeviceName());
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(e.getDeviceMac());
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView3);
            TextView textView4 = new TextView(this);
            textView4.setText(e.getDeviceType());
            textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView4);
            TextView textView5 = new TextView(this);
            textView5.setText(String.valueOf(e.getIsConnect()));
            textView5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView5);
            TextView textView6 = new TextView(this);
            textView6.setText(String.valueOf(e.getIsDisConnect()));
            textView6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView6);
            TextView textView7 = new TextView(this);
            textView7.setText(String.valueOf(e.getLongitude()));
            textView7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView7);
            TextView textView8 = new TextView(this);
            textView8.setText(String.valueOf(e.getLatitude()));
            textView8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView8);
            table.addView(tableRow);
        }
        System.out.println("in UserBehavior");
        table = findViewById(R.id.table3);
        table.removeViews(1,table.getChildCount()-1);
        for(UserBehavior e : collection.getUserBehaviors()){
            System.out.println(e);
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText(e.getDateTime());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(String.valueOf(e.getIsAllow()));
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(String.valueOf(e.getIsReject()));
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView3);
            TextView textView4 = new TextView(this);
            textView4.setText(String.valueOf(e.getLongitude()));
            textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView4);
            TextView textView5 = new TextView(this);
            textView5.setText(String.valueOf(e.getLatitude()));
            textView5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView5);
            table.addView(tableRow);
        }
        System.out.println("in DevicePair");
        table = findViewById(R.id.table4);
        table.removeViews(1,table.getChildCount()-1);
        for(DevicePair e : collection.getDevicePairs()){
            System.out.println(e);
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText(e.getDateTime());
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView);
            TextView textView2 = new TextView(this);
            textView2.setText(e.getDeviceName());
            textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(this);
            textView3.setText(e.getDeviceMac());
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView3);
            TextView textView4 = new TextView(this);
            textView4.setText(e.getDeviceType());
            textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView4);
            TextView textView5 = new TextView(this);
            textView5.setText(String.valueOf(e.getIsPair()));
            textView5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView5);
            TextView textView6 = new TextView(this);
            textView6.setText(String.valueOf(e.getIsNoPair()));
            textView6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView6);
            TextView textView7 = new TextView(this);
            textView7.setText(String.valueOf(e.getLongitude()));
            textView7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView7);
            TextView textView8 = new TextView(this);
            textView8.setText(String.valueOf(e.getLatitude()));
            textView8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tableRow.addView(textView8);
            table.addView(tableRow);
        }
    }

    private void endCollect() {
        System.out.println("取消采集");
        Toast.makeText(this,"取消采集",Toast.LENGTH_SHORT).show();
    }

    private void beginCollect() {
        // 初始化广播
        BluetoothMonitorReceiver bleListenerReceiver = new BluetoothMonitorReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        // 监听蓝牙状态变化、设备连接情况、设备配对情况
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        // 注册广播
        registerReceiver(bleListenerReceiver, intentFilter);  // 保证只注册一个
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 申请成功
                    System.out.println(permissions[i] + "=================申请成功=================");
                } else {
                    // 申请失败
                    System.out.println(permissions[i] + "=================申请失败=================");
                }
            }
        }
    }
}