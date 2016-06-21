package com.soin.digitaldynamics.Presenter;

/**
 * Created by neilgarciavargas on 21/6/16.
 * Base presenter. Usually the presenter is responsible to fill data to activities, fragments and others layouts.
 */

public abstract class BasePresenter {

    ///Do something when start
    public abstract void onStart();

    //Do something when stop
    public abstract void onStop();
}

