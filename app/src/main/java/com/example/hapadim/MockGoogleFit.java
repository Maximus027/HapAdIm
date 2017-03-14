package com.example.hapadim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hapadim.googlefit.GoogleFitService;
import com.google.android.gms.common.api.GoogleApiClient;

public class MockGoogleFit extends AppCompatActivity implements View.OnClickListener{

    private GoogleApiClient googleApiClient;
    TextView stepTextView;
    Button stopStepsButton;

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
        startService(service);
    }

    public void stopService(View view) {
        Intent service = new Intent(this, GoogleFitService.class);
        if (googleApiClient == null) {
            Toast.makeText(this, "Service is not active", Toast.LENGTH_SHORT).show();
        } else {
            stopService(service);
        }
        
    }
}
