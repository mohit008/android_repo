package com.mohit.program.open_camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.mohit.program.R;
import com.mohit.program.util.Global;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Author @ Mohit Soni on 10-05-2018 06:16 PM.
 */

public class OpenCamera extends Activity {
    public final String TAG = this.getClass().getSimpleName();

    private static int RESULT_LOAD_IMG = 1;
    private static int CAMERA_RULE = 3;
    private static int CAMERA_LOAD_IMG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        ((Button) findViewById(R.id.btItem)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.btItem)).setText("Open Camera");
        ((Button) findViewById(R.id.btItem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.checkVersion23()) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                CAMERA_RULE);
                    }else{
                        callCamera();
                    }
                } else {
                     callCamera();
                }
            }
        });
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
        String imgString = "";
        Bitmap bit = null;
        try {
            if (requestCode == CAMERA_LOAD_IMG && resultCode == RESULT_OK) {
                if (data != null) {
                    bit = (Bitmap) data.getExtras().get("data");
                    imgString = saveBitmap(bit);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * save bitmap to storage
     * @param bitmap
     * @return
     */
    private String saveBitmap(Bitmap bitmap) {
        try {
            File dirPath = new File(Environment.getExternalStorageDirectory(), ".mutipic");
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
            String currentTimeStamp = "" + new Date().getTime();
            File file = new File(dirPath, currentTimeStamp + ".jpg");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            byte[] bitmapdata = bos.toByteArray();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * call camera
     */
    public void callCamera() {
        Intent galleryIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(galleryIntent, CAMERA_LOAD_IMG);
    }
}
