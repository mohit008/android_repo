package com.mohit.program.scan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mohit.program.R;

/**
 * add  compile 'com.journeyapps:zxing-android-embedded:3.2.0@aar' to build.gradle
 * Author @ Mohit Soni on 17-05-2018 12:37 PM.
 */

public class ScanMaster extends Activity {

    private static final String TAG = "ScanActivity";
    TextView tvScanSave;
    String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        Button scan = ((Button) findViewById(R.id.scan));
        scan.setVisibility(View.VISIBLE);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                this.data = result.getContents();
                Log.i(TAG, this.data);
            } else {
                this.data = "";
                Log.i(TAG, "Cancelled");
                onBackPressed();
                Toast.makeText(ScanMaster.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void openScanActivity() {
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Scan");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }
}
