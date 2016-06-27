package com.soin.digitaldynamics.Model.SlotMachine;


import com.soin.digitaldynamics.Model.MDynamic;
import com.soin.digitaldynamics.Model.SocketManager;
import com.soin.digitaldynamics.Presenter.SpinOnEvent;

import org.json.JSONObject;

import static com.soin.digitaldynamics.Util.LogUtils.LOGE;
import static com.soin.digitaldynamics.Util.LogUtils.makeLogTag;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public class SlotMachineDynamic extends MDynamic {

    private static String TAG = makeLogTag(SlotMachineDynamic.class);

    private SpinOnEvent _spinOnEvent;
    //private MSlotItem[] _slotItems;

    public SlotMachineDynamic(SocketManager manager, String beaconIdentifier, SpinOnEvent spinOnEvent) {
        super(manager, beaconIdentifier);
        _spinOnEvent = spinOnEvent;
        //_slotItems = slotItems;
    }


    @Override
    public void onConnect() {
        LOGE(TAG,"Connected");
    }

    @Override
    public void onConnectError() {
        LOGE(TAG,"Connect Error");
    }

    @Override
    public void onConnectTimeOut()
    {
        LOGE(TAG,"Connect timeout");
    }

    @Override
    public void onSessionKill()
    {
        LOGE(TAG,"Session killed");
    }

    @Override
    public void onDisconnect()
    {
        LOGE(TAG,"Disconnected");
    }

    @Override
    public void onError(JSONObject jsonObject)
    {
        LOGE(TAG,"Error");
    }

    @Override
    public void onRefresh()
    {
        LOGE(TAG,"Need refresh");
    }

    @Override
    public void onActivate()
    {
        LOGE(TAG,"activate (spin on)");
        _spinOnEvent.spinOn();
    }

}
