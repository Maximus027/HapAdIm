package com.example.hapadim.googlefit;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by maxrosado on 3/9/17.
 */

public class GoogleFitService extends IntentService {

    private GoogleApiClient googleApiClient;
    private GoogleApiClient.Builder builder;

    public static final String SERVICE_REQUEST_TYPE = "requestType";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GoogleFitService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        int requestType = intent.getIntExtra(SERVICE_REQUEST_TYPE, -1);

        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
        }

        if (googleApiClient.isConnected()) {
        }

    }
}
