package com.example.hapadim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hapadim.adapters.BadgesEarnedAdapter;
import com.example.hapadim.complexsharedprefs.ComplexPreferences;
import com.example.hapadim.models.Badge;
import com.example.hapadim.models.Place;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.hapadim.adapters.LandMarksAdapter.TAG;

/**
 * Created by queenabergen on 3/16/17.
 */

public class InProgressActivity extends Activity implements SensorEventListener {
    final String statueofLibertyNum = "354";
    final String statueStepLeft = "0";
    VrPanoramaView vrPanoramaView;
    VrPanoramaView.Options panoOptions1 = null;
    public boolean loadImageSuccessful;
    Button threesixty;
    private Uri fileUri;
    Bitmap panoImage;
    ArrayList<String> tips;
    int newVal;

    InputStream istr = null;
    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();
    private ImageLoaderTask backgroundImageLoaderTask;
    private static final String TAG2 = "In Progress LOG: ";
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;

    private TextView stepsLeft;
    private TextView stepsTaken;
    private TextView healthTips;

    private int totalSteps;
    private int initialDemoCounter = 334;
    private Place place;
//    private BadgesEarnedAdapter adapter;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.inprogressscreen);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        stepsLeft = (TextView) findViewById(R.id.steps_left);
        stepsTaken = (TextView) findViewById(R.id.steps_taken);
        healthTips = (TextView) findViewById(R.id.healthtips);
        setRandomTips();


        place = Parcels.unwrap(getIntent().getParcelableExtra(Constants.IN_PROGRESS_PLACE_BUNDLE_KEY));
        totalSteps = place.getStepNumber();

        stepsLeft.setText(String.valueOf(totalSteps - initialDemoCounter));
        stepsTaken.setText(String.valueOf(initialDemoCounter));

        vrPanoramaView = (VrPanoramaView) findViewById(R.id.pano_view);
        threesixty = (Button) findViewById(R.id.VR_Btn);
        panoImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.libetythree);
        toolbarTransparent();
    }

    private void setRandomTips() {
        tips = new ArrayList<>();
        tips.add(0, "Let's take the steps instead of the elevator. ");
        tips.add(1, "Did you know that walking releases nature's pain relieving hormone called endorphins? ");
        tips.add(2, "Why not come off the bus or train a stop early and get some extra steps! ");
        healthTips.setText(tips.get(2));
    }


    private void saveToUserBadges(Badge newBadge) {
        boolean containsBadge = false;
        List<Badge> userEarnedBadges = getEarnedBadges();

        for (int i = 0; i < userEarnedBadges.size(); i++) {

            if (userEarnedBadges.get(i).getBadgedName().equals(newBadge.getBadgedName())) {
                containsBadge = true;
            } else {
                containsBadge = false;
            }

            if (containsBadge) {
                Log.d(TAG, "saveToUserBadges: User already has badge");
            } else {
                userEarnedBadges.add(newBadge);
            }
        }


        ListComplexBadge complexObject = new ListComplexBadge();
        complexObject.setBadges(userEarnedBadges);

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, Constants.SHARED_PREFS_KEY, MODE_PRIVATE);
        complexPreferences.putObject(Constants.SHARED_PREFS_BADGES_KEY, complexObject);
        complexPreferences.commit();
    }

    public List<Badge> getEarnedBadges() {
        ComplexPreferences complexPreferences = ComplexPreferences
                .getComplexPreferences(this, Constants.SHARED_PREFS_KEY, MODE_PRIVATE);
        ListComplexBadge complexObject = complexPreferences
                .getObject(Constants.SHARED_PREFS_BADGES_KEY, ListComplexBadge.class);

        List<Badge> userEarnedBadges = new ArrayList<>();
        if (complexObject != null) {
            for (Badge item : complexObject.getBadges()) {
                userEarnedBadges.add(item);
            }
        }
        return userEarnedBadges;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG2, this.hashCode() + ".onNewIntent()");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Log.i(TAG2, "ACTION_VIEW Intent recieved");

            fileUri = intent.getData();
            if (fileUri == null) {

                Log.w(TAG2, "No data uri specified. Use \"-d /path/filename\".");
            } else {
                vrPanoramaView.loadImageFromBitmap(panoImage, panoOptions);
                Log.i(TAG2, "Using file " + fileUri.toString());
            }

            panoOptions.inputType = intent.getIntExtra("inputType", VrPanoramaView.Options.TYPE_MONO);
            Log.i(TAG2, "Options.inputType = " + panoOptions.inputType);
        } else {
            Log.i(TAG2, "Intent is not ACTION_VIEW. Using default pano image.");
            fileUri = null;
            panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;
        }

        if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new ImageLoaderTask();
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }

    @Override
    protected void onPause() {
        vrPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vrPanoramaView.resumeRendering();
        mSensorManager.registerListener(this, mStepCounterSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = 1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // For test/demo only. Only allowed value is 1.0 i.e. for step taken
            if (value == 1.0) {
                if (newVal <= 353) {
                    newVal = initialDemoCounter++;
                }

                if (newVal == place.getStepNumber()) {
                    Toast.makeText(this
                            , "Great Job, you conquered " + place.getPlaceName() + " !"
                            , Toast.LENGTH_SHORT).show();
                    stepsTaken.setText(statueofLibertyNum);
                    stepsLeft.setText(statueStepLeft);
                    vrPanoramaView.loadImageFromBitmap(panoImage, panoOptions);
                } else {
                    stepsTaken.setText(String.valueOf(newVal));
                    int newTotal = totalSteps - newVal;
                    stepsLeft.setText(String.valueOf(newTotal));
                    checkForEarnedBadges(place);
                }
            }
        }
    }


    private void checkForEarnedBadges(Place place) {
        for (int i = 0; i < place.getBadges().size(); i++) {
            if (initialDemoCounter >= (place.getStepNumber() / 2)) {
                saveToUserBadges(place.getBadges().get(0));
            } else if (initialDemoCounter >= (place.getStepNumber())) {
                saveToUserBadges(place.getBadges().get(1));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class ImageLoaderTask extends AsyncTask<Pair<Uri, VrPanoramaView.Options>, Void, Boolean> {
        protected Boolean doInBackground(Pair<Uri, VrPanoramaView.Options>... fileInformation) {
            if (fileInformation == null || fileInformation.length < 1
                    || fileInformation[0] == null || fileInformation[0].first == null) {
                AssetManager assetManager = getAssets();
                try {
                    istr = assetManager.open("andes.jpg");
                    panoOptions1 = new VrPanoramaView.Options();
                    panoOptions1.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
                } catch (IOException e) {
                    Log.e(TAG2, "Could not decode default bitmap: " + e);
                    return false;
                }
            } else {
                try {
                    istr = new FileInputStream(new File(fileInformation[0].first.getPath()));
                    panoOptions1 = fileInformation[0].second;
                } catch (IOException e) {
                    Log.e(TAG2, "Could not load file: " + e);
                    return false;
                }
            }

            try {
                istr.close();
            } catch (IOException e) {
                Log.e(TAG2, "Could not close input stream: " + e);
            }

            return true;

        }
    }

    private class ActivityEventListener extends VrPanoramaEventListener {
        @Override
        public void onLoadSuccess() {
            loadImageSuccessful = true;
        }

        @Override
        public void onLoadError(String errorMessage) {
            loadImageSuccessful = false;
            Toast.makeText(
                    InProgressActivity.this, "Error loading pano: " + errorMessage, Toast.LENGTH_LONG)
                    .show();
            Log.e(TAG2, "Error loading pano: " + errorMessage);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
        startActivity(intent);
    }

    private void toolbarTransparent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }

}

