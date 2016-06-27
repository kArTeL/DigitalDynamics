package com.soin.digitaldynamics.Model;

import org.json.JSONObject;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public interface SocketEventListener {

    void onConnect();
    void onConnectError();
    void onConnectTimeOut();
    void onSessionKill();
    void onDisconnect();
    void onError(JSONObject jsonObject);
    void onRefresh();
    void onActivate();
}
