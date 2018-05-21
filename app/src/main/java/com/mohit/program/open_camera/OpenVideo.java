package com.mohit.program.open_camera;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mohit.program.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * Author @ Mohit Soni on 21-05-2018 03:33 PM.
 * <p>
 * permission required
 * <p>
 * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
 * <uses-permission android:name="android.permission.CAMERA" />
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.ACTION_MANAGE_OVERLAY_PERMISSION" />
 * <p>
 * <uses-feature android:name="android.hardware.camera" />
 * <uses-feature android:name="android.hardware.camera.autofocus" />
 */

public class OpenVideo extends Activity {

    private static int CAMERA_RULE = 3;

    private static int REQUEST_VIDEO_CAPTURE = 4;
    String select, path;

    Uri uri;
    File dirPath;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        dirPath = new File(Environment.getExternalStorageDirectory(), "/mip");
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        progressDialog = new ProgressDialog(OpenVideo.this);
        progressDialog.setMessage("Please Wait");

        ((Button) findViewById(R.id.btItem)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.btItem)).setText("Open Gallery");
        ((Button) findViewById(R.id.btItem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkVersion23()) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                CAMERA_RULE);
                    } else {
                        callCamera();
                    }
                }
            }
        });

        /*((Button) findViewById(R.id.move)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = new File(dirPath + "").getAbsolutePath();
                new CompressVideo().execute(a);
            }
        });*/
    }

    public class CompressVideo extends AsyncTask<String, Void, Void> {
        String filePath;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... voids) {
            /*String str = voids[0];
            try {
                filePath = SiliCompressor.with(OpenVideo.this).compressVideo(path, str);
                saveVideo(filePath);
                Log.i("str", filePath);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.hide();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_RULE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
                uri = data.getData();
                select = getPath(uri);
                saveVideo(select);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveVideo(String select) {
        try {
            String currentTimeStamp = "" + new Date().getTime();
            File file = new File(dirPath, currentTimeStamp + ".mp4");

            FileInputStream fileInputStream = new FileInputStream(new File(select));

            int bufferSize = fileInputStream.available();
            byte[] buffer = new byte[bufferSize];

            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            fos.flush();
            fos.close();

            return path = file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * call camera
     */
    public void callCamera() {
        Intent galleryIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(galleryIntent, REQUEST_VIDEO_CAPTURE);
    }

    public boolean checkVersion23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * get video path form storage
     *
     * @param uri
     * @return
     */
    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
