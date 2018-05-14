package com.mohit.program.google_plus_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:38 PM.
 */

public class GoogleLogout extends Activity {
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_logout_layout);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MasterGooglePlus.SIGNOUT();
                Intent intent = new Intent(GoogleLogout.this, MasterGooglePlus.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
