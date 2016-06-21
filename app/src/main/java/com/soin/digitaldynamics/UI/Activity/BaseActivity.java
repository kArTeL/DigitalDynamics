package com.soin.digitaldynamics.UI.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.soin.digitaldynamics.Presenter.BasePresenter;

import butterknife.ButterKnife;


/**
 * Created by neilgarciavargas on 25/2/16.
 */

/**
 * Base Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(getLayout());

        //Disable rotation
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        injectViews();
        initObs();
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (getPresenter() != null)
            getPresenter().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (getPresenter() != null)
            getPresenter().onStop();
    }

    /**
     * Get layout associated to the activity.
     * @return
     */
    protected abstract int getLayout();

    @Nullable
    /**
     * The presenter could be null, but usually we need a data source to fill the view.
     */
    protected abstract BasePresenter getPresenter();

    /**
     * Injects the view in each activity.
     */
    private void injectViews() {
        ButterKnife.bind(this);
    }

    /**
     * Responsible for initializing observers and not ui classes
     */
    protected abstract void initObs();

    /**
     * Responsible for initializing ui classes
     */
    protected abstract void initUI();

}
