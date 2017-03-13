package com.example.hapadim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hapadim.googlefit.GoogleFitService;

public class MockGoogleFit extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_google_fit);
        startGoogleFitService();
    }

    private void startGoogleFitService() {
        Intent intent = new Intent(this, GoogleFitService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
