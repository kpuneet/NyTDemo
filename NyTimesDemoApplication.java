package com.puneet.android;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.fuzz.android.common.GlobalContext;
import com.puneet.android.network.OkHttpDownloader;
import com.puneet.android.network.RetrofitManager;
import com.puneet.android.utils.Preferences;
import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

public class NyTimesDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.initialize(NyTimesDemoApplication.this);
        Preferences.sInstance.init(this);
        RetrofitManager.sInstance.init(NyTimesDemoApplication.this);
        try {
            Picasso.Builder builder = new Picasso.Builder(NyTimesDemoApplication.this);
            builder.executor(Executors.newFixedThreadPool(4));
            builder.downloader(new OkHttpDownloader(this));
            builder.listener((picasso, uri, exception) -> Log.d(NyTimesDemoApplication.class.getSimpleName(), "error", exception));
            builder.memoryCache(Cache.NONE);
            Picasso built = builder.build();
            built.setLoggingEnabled(false);
            //built.setIndicatorsEnabled(BuildConfig.DEBUG);
            Picasso.setSingletonInstance(built);
        } catch (Throwable e) {
            Log.d(NyTimesDemoApplication.class.getSimpleName(), "error", e);
        }
        Stetho.initializeWithDefaults(this);

    }

}
