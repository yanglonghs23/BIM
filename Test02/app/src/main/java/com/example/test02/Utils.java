package com.example.test02;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;

import com.example.test02.entity.UserBehavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Utils {
    public static void saveCollection(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory()+ File.separator +"object.txt"))) {
            oos.writeObject(Collection.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePerHour(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                saveCollection();
                System.out.println(new Date() +"\t"+"---saveCollection---");
            }
        }, 60*60*1000, 60*60*1000);
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            System.out.println("************************");
            System.out.println(appProcess.pid + ": " + appProcess.processName);
            System.out.println("************************");
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
