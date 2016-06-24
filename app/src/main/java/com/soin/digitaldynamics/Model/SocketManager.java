package com.soin.digitaldynamics.Model;
import com.soin.digitaldynamics.Model.SlotMachine.SlotMachineDynamic;

import org.json.JSONObject;

import java.net.URI;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.soin.digitaldynamics.Util.LogUtils.LOGE;
import static com.soin.digitaldynamics.Util.LogUtils.makeLogTag;

/**
 * Created by neilgarciavargas on 17/6/16.
 */

public class SocketManager {

    private static String TAG = makeLogTag(SlotMachineDynamic.class);
    /**
     * Subscribed event.
     */

    private Socket _socket;


    private SocketEventListener _eventListener;


    public SocketManager() {
    }

    /**
     * Get EventListener
     *
     * @return EventListener as SocketEventListener
     */
    public SocketEventListener getEventListener() {
        return _eventListener;
    }

    /**
     * Set EventListener
     */
    public void setEventListener(SocketEventListener eventListener) {
        _eventListener = eventListener;
    }

    void connectWithBeacon(String beacon)
    {
        //String connectionString = Constants.getFullServerURL();// + beacon;

        try {
            //URL connectionURL = new URL(connectionString);
            IO.Options opts = new IO.Options();
            opts.forceNew = true;

            opts.path = Constants.getPath();
            //opts.query = "beacon=" + beacon;
            opts.reconnection = false;
            opts.reconnectionAttempts = 1;


            //opts.timeout = -1;
           // URI connectionURI = new URI(connectionString);
           // Manager manager = new Manager(new URI(""));
            _socket = IO.socket(Constants.getHost());


            _socket.on(Socket.EVENT_CONNECT,onConnect);
            _socket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            _socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            _socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeOut);

            _socket.on("spin-on",onActive );

            _socket.on("cantSubscribe",onSessionKill);
            _socket.on("handle-error",onError);
            _socket.on("refresh-dynamic",onRefresh);
            //_socket.io().open();
            _socket.connect();
        } catch (Exception e) {
            LOGE(TAG,e.getMessage());
        }
    }
    void disconnect()
    {
        _socket.disconnect();
    }



    /** Socket Listeners **/
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
           _eventListener.onConnect();
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            _eventListener.onDisconnect();
        }
    };
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            _eventListener.onConnectError();
        }
    };
    private Emitter.Listener onConnectTimeOut = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            _eventListener.onConnectTimeOut();
        }
    };
    private Emitter.Listener onActive = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            _eventListener.onActivate(data);
        }
    };
    private Emitter.Listener onSessionKill = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            _eventListener.onSessionKill();
        }
    };
    private Emitter.Listener onRefresh = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            _eventListener.onRefresh();
        }
    };

    private Emitter.Listener onError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            _eventListener.onError(data);
        }
    };

}
