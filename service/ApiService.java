package com.puneet.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.fuzz.android.util.FZLog;
import com.puneet.android.BuildConfig;
import com.puneet.android.R;
import com.puneet.android.model.ServerResponse;
import com.puneet.android.network.RetrofitManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiService extends Service {

    private static final String COMMAND = "COMMAND";
    private static final int LOAD_RESULTS = 123;
    private static final String PATH = "";

    public static void getResults(Context context, String section_name) {
        Intent intent = new Intent(context, ApiService.class);
        intent.putExtra(COMMAND, LOAD_RESULTS);
        intent.putExtra(PATH, section_name);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return START_NOT_STICKY;
        }
        switch (intent.getIntExtra(COMMAND, -1)) {
            case LOAD_RESULTS:
                ResultsApi resultsApi = RetrofitManager.sInstance.getClient(getString(R.string.Endpoint)).create(ResultsApi.class);
                Call call = resultsApi.getResults(intent.getStringExtra(PATH).concat(".json"), BuildConfig.API_KEY);
                call.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        FZLog.d(ApiService.class.getSimpleName(), "Response Code: " + response.code());
                        try {
                            if (response != null && response.body() != null) {
                                /*List<Entry> response1 = response.body().getResults();
                                EntryApiResponse entryApiResponse = new EntryApiResponse(response1);
                                EventBus.getDefault().postSticky(entryApiResponse);*/

                                Map<String, Object> map = new HashMap<>();
                                map.put("category", intent.getStringExtra(PATH));
                                map.put("response", response.body());
                                EventBus.getDefault().postSticky(map);
                            } else {
                                Toast.makeText(getApplicationContext(), "There are no articles with the criteria specified", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            FZLog.d(ApiService.class.getSimpleName(), "Response error: " + ex.getStackTrace().toString());
                            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Issues connecting to API", Toast.LENGTH_SHORT).show();
                        Log.d(ApiService.class.getSimpleName(), "throwable error: " + t);
                        FZLog.d(ApiService.class.getSimpleName(), "throwable error: " + t.toString());

                    }
                });
                break;
        }
        return START_NOT_STICKY;
    }

    /**
     * Having the Retrofit callback ensure that the callback is always received independent of the Fragment availability
     * Results are then dispatched using a sticky broadcast, such that if the Fragment is paused it will received
     * the callback when it resumed automatically, response callbacks are therefore not lost
     */

    //Reserved for IPC predominantly
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public interface ResultsApi {
        @GET("{path}")
        Call<ServerResponse> getResults(@Path("path") String path, @Query("api-key") String apiKey);
    }
}
