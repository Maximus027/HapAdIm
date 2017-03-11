package com.example.hapadim.youtube;

import android.os.Bundle;

import com.example.hapadim.R;
import com.google.android.youtube.player.YouTubeBaseActivity;

/**
 * Created by queenabergen on 3/9/17.
 */

public class YoutubePlayer extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mockpage);
    }
}
