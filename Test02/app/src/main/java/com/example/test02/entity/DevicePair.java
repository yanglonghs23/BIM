package com.example.test02.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class DevicePair implements Serializable {
    String dateTime;
    String deviceName;
    String deviceMac;
    String deviceType;
    int isPair;
    int isNoPair;
    double Longitude;
    double Latitude;

    public DevicePair(String dateTime, String deviceName, String deviceMac, String deviceType, int isPair, int isNoPair, double longitude, double latitude) {
        this.dateTime = dateTime;
        this.deviceName = deviceName;
        this.deviceMac = deviceMac;
        this.deviceType = deviceType;
        this.isPair = isPair;
        this.isNoPair = isNoPair;
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

    public int getIsPair() {
        return isPair;
    }

    public void setIsPair(int isPair) {
        this.isPair = isPair;
    }

    public int getIsNoPair() {
        return isNoPair;
    }

    public void setIsNoPair(int isNoPair) {
        this.isNoPair = isNoPair;
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
        return  dateTime +
                "," + deviceName +
                "," + deviceMac +
                "," + deviceType +
                "," + isPair +
                "," + isNoPair +
                "," + Longitude +
                "," + Latitude + "\n";
    }
}
