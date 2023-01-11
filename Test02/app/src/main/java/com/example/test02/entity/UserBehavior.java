package com.example.test02.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class UserBehavior implements Serializable {
    String dateTime;
    int isAllow;
    int isReject;
    double Longitude;
    double Latitude;

    public UserBehavior(String dateTime, int isAllow, int isReject, double longitude, double latitude) {
        this.dateTime = dateTime;
        this.isAllow = isAllow;
        this.isReject = isReject;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(int isAllow) {
        this.isAllow = isAllow;
    }

    public int getIsReject() {
        return isReject;
    }

    public void setIsReject(int isReject) {
        this.isReject = isReject;
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
                "," + isAllow +
                "," + isReject +
                "," + Longitude +
                "," + Latitude + "\n";
    }
}
