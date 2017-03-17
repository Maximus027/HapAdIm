package com.example.hapadim;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by queenabergen on 3/16/17.
 */

public class InProgressActivity extends Activity {
    VrPanoramaView vrPanoramaView;
    VrPanoramaView.Options panoOptions1 = null;
    public boolean loadImageSuccessful;
    private Uri fileUri;
    Bitmap panoImage;
    InputStream istr = null;
    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();
    private ImageLoaderTask backgroundImageLoaderTask;
    private static final String TAG2 = "HERE IS LOG STATEMENT: ";
    InputStream assetManager;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.inprogressscreen);
        vrPanoramaView = (VrPanoramaView) findViewById(R.id.pano_view);
        toolbarTransparent();
        panoImage = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.andes);
        vrPanoramaView.loadImageFromBitmap(panoImage, panoOptions);

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
    }

    @Override
    protected void onDestroy() {
        vrPanoramaView.shutdown();
        if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }
        super.onDestroy();
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

    private void toolbarTransparent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}

