package com.soin.digitaldynamics.Model;

import com.soin.digitaldynamics.BuildConfig;

/**
 * Created by neilgarciavargas on 17/6/16.
 */
public class Constants {
    private static String SERVER_URL = "http://192.29923.293:80";

    private static String PATH = "/digitaldynamics/setChannel?beacon=";


    public static final String getFullServerURL() {
        String returnValue = SERVER_URL + PATH;
        return returnValue;
    }
}
