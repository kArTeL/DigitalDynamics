package com.soin.digitaldynamics.Model;

import com.soin.digitaldynamics.BuildConfig;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public class Constants {
   // private static String SERVER_URL = "http://chat.socket.io";
    private static String SERVER_URL = "http://tappqa.soinlabs.net:1725";

    //private static String PATH = "/digitaldynamics/setChannel?beacon=";
    private static String PATH = "/digitaldynamics/setChannel";

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
