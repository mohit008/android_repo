package com.mohit.program.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 17-05-2018 12:03 PM.
 */

public class VideoViewMaster extends Activity {

    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        video = (VideoView) findViewById(R.id.video);
        video.setVisibility(View.VISIBLE);
        //	String uri = "android.resource://"+getPackageName()+"/"+R.raw.man_mast;
        //	String link = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        Uri uri = Uri.parse("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
        video.setMediaController(mediaController);
        video.setVideoURI(uri);
        video.requestFocus();

        video.start();
    /*	try {
			MediaController media = new MediaController(MainActivity.this);
			media.setAnchorView(video);
			Uri path = Uri.parse(link);
			video.setVideoURI(path);

		} catch (Exception e) {
			Log.e("error", e.getMessage());
			e.printStackTrace();
		}
		video.requestFocus();
		video.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				video.start();

			}
		});*/

    }

}
