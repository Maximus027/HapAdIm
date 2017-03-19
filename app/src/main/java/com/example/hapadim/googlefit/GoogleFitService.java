package com.example.hapadim.googlefit;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 * Created by maxrosado on 3/9/17.
 */

public class GoogleFitService extends Service implements OnDataPointListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_OAUTH = 1;
    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean authInProgress = false;
    private GoogleApiClient googleApiClient;
    private OnDataPointListener onDataPointListener;
    private final LocalBinder binder = new LocalBinder();
    public static final int REQUEST_RESOLVE_ERROR = 1001;
    private boolean resolvingError = false;
    private Activity activity;
    private String steps;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        googleApiClient.connect();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected: ");
        DataSourcesRequest dataSourceRequest = new DataSourcesRequest.Builder()
                .setDataTypes(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .setDataSourceTypes(DataSource.TYPE_RAW)
                .build();

        ResultCallback<DataSourcesResult> dataSourcesResultCallback = new ResultCallback<DataSourcesResult>() {
            @Override
            public void onResult(DataSourcesResult dataSourcesResult) {
                for (DataSource dataSource : dataSourcesResult.getDataSources()) {
                    if (DataType.TYPE_STEP_COUNT_CUMULATIVE.equals(dataSource.getDataType())) {
                        registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_CUMULATIVE);
                    }
                }
            }
        };

        Fitness.SensorsApi.findDataSources(googleApiClient, dataSourceRequest)
                .setResultCallback(dataSourcesResultCallback);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("GoogleFit", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("GoogleFit", "onConnectionFailed");
        if (resolvingError) {
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                resolvingError = true;
                connectionResult.startResolutionForResult(activity, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                googleApiClient.connect();
            }
        } else {
            resolvingError = true;
        }

    }

    @Override
    public void onDataPoint(DataPoint dataPoint) {
        for (final Field field : dataPoint.getDataType().getFields()) {
            Value value = dataPoint.getValue(field);
            steps = value.toString();
        }
    }

    private void registerFitnessDataListener(DataSource dataSource, DataType dataType) {

        SensorRequest request = new SensorRequest.Builder()
                .setDataSource(dataSource)
                .setDataType(dataType)
                .setSamplingRate(3, TimeUnit.SECONDS)
                .build();

        Fitness.SensorsApi.add(googleApiClient, request, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.e("GoogleFit", "SensorApi successfully added");
                        }
                    }
                });
    }

    private void unregisterFitnessDataListener() {
        if (onDataPointListener == null) {
            return;
        }

        Fitness.SensorsApi.remove(
                googleApiClient,
                onDataPointListener)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.i(TAG, "Listener was removed!");
                        } else {
                            Log.i(TAG, "Listener was not removed.");
                        }
                    }
                });
    }

    public class LocalBinder extends Binder {

        public GoogleFitService getService() {
            return GoogleFitService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return binder;
    }

    public String getSteps() {
        return steps;
    }

    public void connect() {
        resolvingError = false;
        googleApiClient.connect();
    }

    public void bindActivity(Activity activity) {
        this.activity = activity;
    }

    public void unbindActivity() {
        this.activity = null;
    }
}
