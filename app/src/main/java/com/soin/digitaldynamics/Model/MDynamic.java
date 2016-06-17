package com.soin.digitaldynamics.Model;

import org.json.JSONObject;

import java.net.Socket;


/**
 * Created by neilgarciavargas on 17/6/16.
 */
public abstract  class MDynamic implements SocketEventListener {

    private String beaconIdentifier;

    private SocketManager _manager;



    public abstract void setSocketManager(SocketManager socketManager);

    public abstract SocketManager getSocketManager();


    public void connectToSocket() {
        if (getSocketManager() != null)
        {

            _manager.connectWithBeacon(beaconIdentifier);
        }
    }

    public void disconnectToSocket()
    {
        _manager.disconnect();
    }

    public void activated(JSONObject object)
    {

    }

    void error(JSONObject object)
    {

    }



}
