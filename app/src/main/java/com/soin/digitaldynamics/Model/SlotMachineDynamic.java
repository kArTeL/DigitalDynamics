package com.soin.digitaldynamics.Model;

import org.json.JSONObject;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public class SlotMachineDynamic extends MDynamic {

    public SlotMachineDynamic(SocketManager manager, String beaconIdentifier) {
        super(manager, beaconIdentifier);
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onConnectTimeOut()
    {

    }

    @Override
    public void onSessionKill()
    {

    }

    @Override
    public void onDisconnect()
    {

    }

    @Override
    public void onError(JSONObject jsonObject)
    {

    }

    @Override
    public void onRefresh()
    {

    }

    @Override
    public void onActivate(JSONObject jsonObject)
    {

    }
//    @Override
//    void onConnect()
//    {
//
//    }
//
//    void onConnectError();
//    void onConnectTimeOut();
//    void onSessionKill();
//    void onDisconnect();
//    void onError(JSONObject jsonObject);
//    void onRefresh();
//    void onActivate(JSONObject jsonObject);

}
