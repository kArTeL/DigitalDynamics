package com.soin.digitaldynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.soin.digitaldynamics.Model.SlotMachineDynamic;
import com.soin.digitaldynamics.Model.SocketManager;

public class MainActivity extends AppCompatActivity {

    private SlotMachineDynamic _slotMachineDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initSocket()
    {
        String beaconString = "";
        SocketManager socketManager = new SocketManager();
        _slotMachineDynamic = new SlotMachineDynamic(socketManager,beaconString);
        _slotMachineDynamic.initSocket();
    }

}
