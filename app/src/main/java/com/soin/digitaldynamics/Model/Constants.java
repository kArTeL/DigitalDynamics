package com.soin.digitaldynamics.Model;

import com.soin.digitaldynamics.BuildConfig;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public class Constants {
   // private static String SERVER_URL = "http://chat.socket.io";
   // 172.20.2.119:1337
    //private static String SERVER_URL = "http://tappqa.soinlabs.net:1725";
   //private static String SERVER_URL = "http://172.20.5.149:1337";
    //http://localhost:1337/dynamic
    private static String SERVER_URL = "http://127.0.0.1:1337";

    //
    //private static String PATH = "/digitaldynamics/setChannel?beacon=";
    private static String PATH = "/dynamic";

    public static final String getFullServerURL() {
        String returnValue = SERVER_URL + PATH;
        return returnValue;
    }
    public static final String getPath() {
        return PATH;
    }
    public static final String getHost() {
        return SERVER_URL;
    }
}
