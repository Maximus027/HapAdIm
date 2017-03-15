package com.example.hapadim;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hapadim.googlefit.GoogleFitService;
import com.google.android.gms.common.api.GoogleApiClient;

public class MockGoogleFit extends AppCompatActivity implements View.OnClickListener{

    private GoogleApiClient googleApiClient;
    private TextView stepTextView;
    private Button stopStepsButton;

    private GoogleFitService googleFitService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GoogleFitService.LocalBinder binder = (GoogleFitService.LocalBinder) service;
            googleFitService = binder.getService();
            googleFitService.bindActivity(MockGoogleFit.this);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            googleFitService.unbindActivity();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GoogleFitService.REQUEST_RESOLVE_ERROR) {
            if (resultCode == RESULT_OK) {
                googleFitService.connect();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_google_fit);

        stepTextView = (TextView) findViewById(R.id.count_of_steps);
        stopStepsButton = (Button) findViewById(R.id.stop_step_count_mock_button);
    }

    private void startGoogleFitService() {
        Intent intent = new Intent(this, GoogleFitService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {

    }

    private void displaySteps () {
        if (googleApiClient != null) {

        }
    }

    public void startService(View view) {
        Intent service = new Intent(this, GoogleFitService.class);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(service);
    }

    public void stopService(View view) {
        Intent service = new Intent(this, GoogleFitService.class);
        Toast.makeText(this, "Unbinding service", Toast.LENGTH_SHORT).show();
        unbindService(serviceConnection);
        stopService(service);
        
    }
}
