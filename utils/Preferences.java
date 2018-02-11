package com.puneet.android.utils;

import android.app.Application;
import android.content.Context;

public class Preferences {

    public static Preferences sInstance = new Preferences();
    private Context context;

    public void init(Application context) {
        this.context = context;
    }

}
