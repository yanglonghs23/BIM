package com.example.test02;


import android.os.Environment;

import com.example.test02.entity.BtState;
import com.example.test02.entity.DeviceConnect;
import com.example.test02.entity.DevicePair;
import com.example.test02.entity.UserBehavior;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 数据集合  懒汉单例 序列化存储
public class Collection implements Serializable {
    private static final long serialVersionUID = 8829975621220483374L;
    private volatile static Collection collection;

    List<BtState> btStates;
    List<DeviceConnect> deviceConnects;
    List<DevicePair> devicePairs;
    List<UserBehavior> userBehaviors;

    private Collection(){
        btStates = new ArrayList<>();
        deviceConnects = new ArrayList<>();
        devicePairs = new ArrayList<>();
        userBehaviors = new ArrayList<>();
    }

    public static Collection getInstance(){
        if (collection == null) {  //2:减少不要同步，优化性能
            synchronized (Collection.class) {  // 3：同步，线程安全
                if (collection == null) {
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Environment.getExternalStorageDirectory()+ File.separator +"object.txt"))) {
                        collection = (Collection)ois.readObject();
                    } catch (Exception e) {
                        collection = Collection.getInstance();
                        e.printStackTrace();
                    }
                }
            }
        }
        return collection;
    }

    public void addBtState(BtState btState){
        btStates.add(btState);
    }
    public void addDeviceConnect(DeviceConnect deviceConnect){
        deviceConnects.add(deviceConnect);
    }
    public void addDevicePair(DevicePair devicePair){
        devicePairs.add(devicePair);
    }
    public void addUserBehavior(UserBehavior userBehavior){
        userBehaviors.add(userBehavior);
    }

    public List<BtState> getBtStates() {
        return btStates;
    }
    public List<DeviceConnect> getDeviceConnects() {
        return deviceConnects;
    }
    public List<DevicePair> getDevicePairs() {
        return devicePairs;
    }
    public List<UserBehavior> getUserBehaviors() {
        return userBehaviors;
    }
}
