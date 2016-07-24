package com.bignerdranch.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

/**
 * Created by dustin on 2016-07-21.
 */
public class RemoteControlActivity extends SingleFragmentHostActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setupToolbar(R.string.app_name);
    }

    @Override
    protected Fragment getFragmentInstance() {
        return new RemoteControlFragment();
    }
}
