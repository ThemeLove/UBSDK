package com.umbrella.game.ubsdk.listener;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public interface UBApplicationListener {
	
    public void onProxyCreate(Application application);

    public void onProxyAttachBaseContext(Application application,Context base);

    public void onProxyConfigurationChanged(Application application,Configuration config);
}
