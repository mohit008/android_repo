package com.mohit.program.google_plus_login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 02:37 PM.
 */

public class MasterGooglePlus extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    SignInButton sign;
    private static GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_plus_login_layout);
        sign = (SignInButton) findViewById(R.id.login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult != null) {
            mGoogleApiClient.reconnect();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        try {
            Status status = result.getStatus();
            String url = "";
            Log.i("result", status.toString());
//            Toast.makeText(MainActivity.this,result.getStatus().toString(),Toast.LENGTH_LONG).show();
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                if (acct != null) {
                    String name = acct.getDisplayName();
                    String email = acct.getEmail();
                    String id = acct.getId();
                    Uri uri = acct.getPhotoUrl();
                    if (uri != null) {
                        url = uri.toString();
                    }
                    Intent intent = new Intent(MasterGooglePlus.this, GoogleLogout.class);
                    intent.putExtra("name", name);
                    intent.putExtra("id", id);
                    intent.putExtra("email", email);
                    intent.putExtra("img", url);
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception e) {
        }
    }

    public static void SIGNOUT() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        mGoogleApiClient.disconnect();
                    }
                }
            });
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.clearDefaultAccountAndReconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.reconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
}
