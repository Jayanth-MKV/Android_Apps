package com.vugido.litholearning.application;

import android.app.Application;

import com.facebook.soloader.SoLoader;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
    }
}
