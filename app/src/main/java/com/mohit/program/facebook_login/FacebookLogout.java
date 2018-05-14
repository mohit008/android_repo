package com.mohit.program.facebook_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:38 PM.
 */

public class FacebookLogout extends Activity {
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.google_logout_layout);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(FacebookLogout.this, MasterFaceBook.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
