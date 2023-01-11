package com.example.test02;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.example.test02.entity.BtState;
import com.example.test02.entity.DeviceConnect;
import com.example.test02.entity.DevicePair;
import com.example.test02.entity.UserBehavior;

import java.text.SimpleDateFormat;
import java.util.Date;

// 数据收集操作
public class DataCollect {
    private final Collection collection = Collection.getInstance();
    private final MainActivity mainActivity;

    public DataCollect(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void collectBtState(int state){
        double[] temp = getLocation(mainActivity);
        if(state == 0){
            collection.addBtState(new BtState(getTime(),0,1,temp[0],temp[1]));
        }else{
            collection.addBtState(new BtState(getTime(),1,0,temp[0],temp[1]));
        }
    }

    public void collectDeviceConnect(BluetoothDevice device,int state){
        double[] temp = getLocation(mainActivity);
        if(state == 0){
            collection.addDeviceConnect(new DeviceConnect(getTime(),device.getName(),device.getAddress(),
                    String.valueOf(device.getType()),0,1,temp[0],temp[1]));
        }else{
            collection.addDeviceConnect(new DeviceConnect(getTime(),device.getName(),device.getAddress(),
                    String.valueOf(device.getType()),1,0,temp[0],temp[1]));
        }
    }

    public void collectDevicePair(BluetoothDevice device,int state){
        double[] temp = getLocation(mainActivity);
        if(state == 0){
            collection.addDevicePair(new DevicePair(getTime(),device.getName(),device.getAddress(),
                    String.valueOf(device.getType()),0,1,temp[0],temp[1]));
        }else{
            collection.addDevicePair(new DevicePair(getTime(),device.getName(),device.getAddress(),
                    String.valueOf(device.getType()),1,0,temp[0],temp[1]));
        }
    }

    public void collectUserBehavior(int decide){
        double[] temp = getLocation(mainActivity);
        if(decide == 0){
            collection.addUserBehavior(new UserBehavior(getTime(),0,1,temp[0],temp[1]));
        }else{
            collection.addUserBehavior(new UserBehavior(getTime(),1,0,temp[0],temp[1]));
        }
    }

    private double[] getLocation(MainActivity mainActivity) {
        LocationManager locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;// 指定LocationManager的定位方法
        Location location = locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
        if(location != null){
            double lat = location.getLatitude();//获取纬度
            double lng = location.getLongitude();//获取经度
            return new double[]{lng,lat};
        }
        return new double[]{0,0};
    }
    private String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
