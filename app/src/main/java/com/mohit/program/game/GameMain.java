package com.mohit.program.game;

import android.app.Activity;
import android.os.Bundle;

/**
 * Author @ Mohit Soni on 14-05-2018 11:03 AM.
 */

public class GameMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
