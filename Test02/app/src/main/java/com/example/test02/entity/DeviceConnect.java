package com.example.test02.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class DeviceConnect implements Serializable {
    String dateTime;
    String deviceName;
    String deviceMac;
    String deviceType;
    int isConnect;
    int isDisConnect;
    double Longitude;
    double Latitude;

    public DeviceConnect(String dateTime, String deviceName, String deviceMac, String deviceType, int isConnect, int isDisConnect, double longitude, double latitude) {
        this.dateTime = dateTime;
        this.deviceName = deviceName;
        this.deviceMac = deviceMac;
        this.deviceType = deviceType;
        this.isConnect = isConnect;
        this.isDisConnect = isDisConnect;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getIsConnect() {
        return isConnect;
    }

    public void setIsConnect(int isConnect) {
        this.isConnect = isConnect;
    }

    public int getIsDisConnect() {
        return isDisConnect;
    }

    public void setIsDisConnect(int isDisConnect) {
        this.isDisConnect = isDisConnect;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    @NonNull
    @Override
    public String toString() {
        return dateTime +
                "," + deviceName +
                "," + deviceMac +
                "," + deviceType +
                "," + isConnect +
                "," + isDisConnect +
                "," + Longitude +
                "," + Latitude + "\n";
    }
}
