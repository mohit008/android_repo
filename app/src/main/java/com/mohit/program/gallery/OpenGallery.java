package com.mohit.program.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 10-05-2018 06:16 PM.
 */

public class OpenGallery extends Activity {
    public final String TAG = this.getClass().getSimpleName();

    private static int RESULT_LOAD_IMG = 1;
    private static int GALLERY_RULE = 4;

    private String[] galleryPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        ((Button) findViewById(R.id.btItem)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.btItem)).setText("Open Gallery");
        ((Button) findViewById(R.id.btItem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(OpenGallery.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(OpenGallery.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(OpenGallery.this, galleryPermissions, GALLERY_RULE);
                } else {
                    callGallery();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == GALLERY_RULE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgString = "";
        Bitmap bit = null;
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getData();
                    String[] str = {MediaStore.Images.Media.DATA};

                    Cursor cur = getContentResolver().query(uri, str, null, null, null);
                    cur.moveToFirst();
                    int col = cur.getColumnIndex(str[0]);
                    imgString = cur.getString(col);
                    cur.close();
                    bit = BitmapFactory.decodeFile(imgString);

                    /*ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bit.compress(Bitmap.CompressFormat.PNG, 100, out);
                    bit = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));*/
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /**
     * call gallery
     */
    public void callGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

}
