package com.example.test02;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import com.example.test02.entity.BtState;
import com.example.test02.entity.DeviceConnect;
import com.example.test02.entity.DevicePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


// 蓝牙广播接收器 消息驱动收集数据 统计数据
// 广播接收多次，其实是一个广播被注册了多次，或者多个广播实例
public class BluetoothMonitorReceiver extends BroadcastReceiver{
    private static boolean isOpen = false;
    private static int connections = 0;
    private static DataCollect dataCollect;

    public BluetoothMonitorReceiver(MainActivity mainActivity) {
//        File file = new File(Environment.getExternalStorageDirectory()+ File.separator +"object.txt");
//        file.delete();
        System.out.println("***********************");
        System.out.println("in BluetoothMonitorReceiver");
        System.out.println("***********************");
        dataCollect = new DataCollect(mainActivity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("1");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&");
        // 获取广播事件
        String action = intent.getAction();
        // 获取设备信息
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if (action != null) {
            switch (action) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_ON:
                            Toast.makeText(context, "蓝牙已经打开", Toast.LENGTH_SHORT).show();
                            isOpen = true;
                            dataCollect.collectBtState(1);
                            break;
                        case BluetoothAdapter.STATE_OFF:
                            Toast.makeText(context, "蓝牙已经关闭", Toast.LENGTH_SHORT).show();
                            isOpen = false;
                            dataCollect.collectBtState(0);
                            break;
                    }
                    break;

                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    Toast.makeText(context, "蓝牙设备已连接" + device.getName() + " " + device.getAddress(), Toast.LENGTH_SHORT).show();
                    connections += 1;
                    dataCollect.collectDeviceConnect(device,1);
                    break;

                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    Toast.makeText(context,"蓝牙设备已断开" + device.getName() + " " + device.getAddress(),Toast.LENGTH_SHORT).show();
                    connections -= 1;
                    dataCollect.collectDeviceConnect(device,0);
                    break;

                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    switch (device.getBondState()) {
                        case BluetoothDevice.BOND_BONDED:
                            //配对结束
                            Toast.makeText(context, "配对结束", Toast.LENGTH_SHORT).show();
                            System.out.println("配对结束");
                            dataCollect.collectDevicePair(device,1);
                            break;
                        case BluetoothDevice.BOND_NONE:
                            //取消配对/未配对
                            Toast.makeText(context, "取消配对/未配对", Toast.LENGTH_SHORT).show();
                            System.out.println("取消配对/未配对");
                            dataCollect.collectDevicePair(device,0);
                            break;
                    }
            }
        }
    }

    public static boolean isIsOpen() {
        return isOpen;
    }

    public static int getConnections() {
        return connections;
    }
}