package com.example.test02;

import android.bluetooth.BluetoothAdapter;


public class BluetoothManager {
    private final DataCollect dataCollect;

    public BluetoothManager(MainActivity activity) {
        dataCollect = new DataCollect(activity);
    }

    // 关闭蓝牙
    public void close() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }
    // 打开蓝牙
    public void open(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }

        // 检查用户行为
        new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(BluetoothMonitorReceiver.isIsOpen()){
                dataCollect.collectUserBehavior(1);
            }else{
                dataCollect.collectUserBehavior(0);
            }
        }).start();
    }
}
