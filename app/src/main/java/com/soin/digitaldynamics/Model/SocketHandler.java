package com.soin.digitaldynamics.Model;

import android.content.Context;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by neilgarciavargas on 17/6/16.
 */

class SocketManager {

    /**
     * Subscribed event.
     */

    private Socket _socket;


    private SocketEventListener _eventListener;


    private Context _context;

    public SocketManager(Context context) {
        _context = context;
    }

    /**
     * Get Context
     *
     * @return Context as Context
     */
    public Context getContext() {
        return _context;
    }

    /**
     * Set Context
     */
    public void setContext(Context context) {
        _context = context;
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
        String connectionString = Constants.getFullServerURL() + beacon;

        try {
            _socket = IO.socket(connectionString);

            _socket.on(Socket.EVENT_CONNECT,onConnect);
            _socket.on(Socket.EVENT_DISCONNECT,onDisconnect);
            _socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            _socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeOut);

            _socket.on("spin-on",onActive );

            _socket.on("cantSubscribe",onSessionKill);
            _socket.on("handle-error",onError);
            _socket.on("refresh-dynamic",onRefresh);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
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
