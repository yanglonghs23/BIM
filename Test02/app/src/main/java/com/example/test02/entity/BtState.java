package com.example.test02.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class BtState implements Serializable {
    String dateTime;
    int isOpen;
    int isClose;
    double Longitude;
    double Latitude;

    public BtState(String dateTime, int isOpen, int isClose, double longitude, double latitude) {
        this.dateTime = dateTime;
        this.isOpen = isOpen;
        this.isClose = isClose;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
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
                "," + isOpen +
                "," + isClose +
                "," + Longitude +
                "," + Latitude + "\n";
    }
}
