package com.soin.digitaldynamics.Model;

import org.json.JSONObject;


/**
 * Created by neilgarciavargas on 17/6/16.
 */
public abstract  class MDynamic implements SocketEventListener {

    private String _beaconIdentifier;

    private SocketManager _manager;

    private MCustomer _customer;

    /**
     * Default constructor
     * @param manager
     * @param beaconIdentifier
     */
    public MDynamic(SocketManager manager, String beaconIdentifier) {
        _manager = manager;
        _beaconIdentifier = beaconIdentifier;
    }

    /**
     * Get BeaconIdentifier
     *
     * @return BeaconIdentifier as String
     */
    public String getBeaconIdentifier() {
        return _beaconIdentifier;
    }

    /**
     * Set BeaconIdentifier
     */
    public void setBeaconIdentifier(String beaconIdentifier) {
        _beaconIdentifier = beaconIdentifier;
    }

    /**
     * Get Manager
     *
     * @return Manager as SocketManager
     */
    public SocketManager getManager() {
        return _manager;
    }

    /**
     * Set Manager
     */
    public void setManager(SocketManager manager) {
        _manager = manager;
    }

    /**
     * Get Customer
     *
     * @return Customer as MCustomer
     */
    public MCustomer getCustomer() {
        return _customer;
    }

    /**
     * Set Customer
     */
    public void setCustomer(MCustomer customer) {
        _customer = customer;
    }


    /** Socket methods **/
    public void initSocket() {
        if (getManager() != null)
        {

            _manager.setEventListener(this);
            _manager.connectWithBeacon(_beaconIdentifier);
        }
    }

    public void disconnectSocket()
    {
        _manager.disconnect();
    }



}
