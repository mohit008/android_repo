package com.mohit.program.custom_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.mohit.program.R;

/** <p>
 * This class show hiow to call youtube stand alone player in application.You need to have
 * youtube account to access and get <b>Youtube API</b> key and then you have to import
 * {@link YouTubeStandalonePlayer} in your class.
 * </p>
 * <p>
 * You should read this parameter to change them
 *</p>
 * <p>
 * Parameters
 *</p>
 *
 * <p>
 * add <b>YouTubeStandalonePlayer.jar</b> file in your project
 *</p>
 *
 * <p>activity : The calling activity from which the standalone player will be started.</p>
 * <p>developerKey :  A valid API key which is enabled to use the YouTube Data API v3 service. To generate a new key, visit the Google APIs Console.</p>
 * <p>videoId :  The id of the video to be played.</p>
 * <p>timeMillis :  The time, in milliseconds, where playback should start in the video.</p>
 * <p>autoplay :  true to have the video start playback as soon as the standalone player loads, false to cue the video.</p>
 * <p>lightboxMode :  true to have the video play in a dialog view above your current Activity, false to have the video play fullscreen.</p>
 *
 *<p>
 * Author @ Mohit Soni on 27-04-2018 13:20.
 * </p>
 */

public class YoutubePlayerSample extends Activity {
    // video id
    String id = "9EoON6EfHjA";

    // developer id
    public static final String DEVELOPER_KEY = "AIzaSyAIBsJW9Vspd0wuUbh9C-UyvkAu3d6VE9g";

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        button = (Button)this.findViewById(R.id.btItem);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(YoutubePlayerSample.this).equals(YouTubeInitializationResult.SUCCESS)) {
                    Intent intent = YouTubeStandalonePlayer.createVideoIntent(YoutubePlayerSample.this, DEVELOPER_KEY, id,0,true,true);
                    startActivity(intent);
                }
            }
        });
    }

}
