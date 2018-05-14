package com.mohit.program.util;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Author @ Mohit Soni on 10-05-2018 05:56 PM.
 */

public class Global {

    public static String ROOT_PACKAGE = "";

    /**
     * get required fonts
     *
     * @param activity context requesting for font
     * @return font
     */
    public static Typeface getRobotoBlackFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_black.ttf");
    }

    public static Typeface getRobotoBlackItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_blackItalic.ttf");
    }

    public static Typeface getRobotoBoldFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robot_bold.ttf");
    }

    public static Typeface getRobotoBoldItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_bold_italic.ttf");
    }

    public static Typeface getRobotoItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_italic.ttf");
    }

    public static Typeface getRobotoLightFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_light.ttf");
    }

    public static Typeface getRobotoLightItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_light_italic.ttf");
    }

    public static Typeface getRobotoMediumFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_medium.ttf");
    }

    public static Typeface getRobotoMediumItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_medium_italic.ttf");
    }

    public static Typeface getRobotoRegularFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_regular.ttf");
    }

    public static Typeface getRobotoThinFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_thin.ttf");
    }

    public static Typeface getRobotoThinItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/roboto_thin_italic.ttf");
    }

    public static Typeface getRobotoCondensedBoldFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_bold.ttf");
    }

    public static Typeface getRobotoCondensedBoldItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_bold_italic.ttf");
    }

    public static Typeface getRobotoCondensedItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_italic.ttf");
    }

    public static Typeface getRobotoCondensedLightFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_light.ttf");
    }

    public static Typeface getRobotoCondensedLightItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_light_italic.ttf");
    }

    public static Typeface getRobotoCondensedRegularFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/robotocondensed_regular.ttf");
    }

    public static Typeface getIbmPlexmonoThinItalicFont(Activity activity) {
        return Typeface.createFromAsset(activity.getAssets(), "font/ibm_plexmono_thinItalic.ttf");
    }

    // local variable
    public static String APPNAME = "IndianExpress";

    static String yr = "", day = "", month = "";

    public static final String SYSTEM_ROOT = Environment.getExternalStorageDirectory() + "/" + APPNAME + "/";
    public static final String ROOT = Environment.getExternalStorageDirectory() + "/" + APPNAME + "/log/";

    static File system_root = new File(SYSTEM_ROOT);
    static File root = new File(ROOT);

    public ProgressDialog progressDialog;
    public static SharedPrefUtility preferences;


    public Global(Activity activity) {
        super();
        preferences = new SharedPrefUtility(activity.getApplicationContext());
    }

    public Global(Context context) {
        super();
        preferences = new SharedPrefUtility(context);
    }

    /**
     * get user detail
     *
     * @return user
     */
    /*public UserData getUser() {
        return preferences.getPrefData();
    }*/


    /**
     * get progress dialog
     *
     * @param activity context requesting for progress bar
     * @param message  string to display
     * @return progress bar
     */
    public ProgressDialog getProgress(Activity activity, String message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Please Wait..");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    /**
     * save error report
     *
     * @param tag     text to attach
     * @param strings array of error
     * @param error   actual error
     */
    public void saveReport(String tag, StackTraceElement[] strings, String error) {
        checkDir();
        File dir = new File(ROOT + "/" + tag + "__" + getTodayDate() + "__" + getCurrentTime() + ".log");
        StringBuilder builder = new StringBuilder();
        String report;
        if (root.isDirectory()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
                builder.append("TAG : " + tag);
                builder.append("\n");
                builder.append("---------------Error--------------");
                builder.append("\n");
                builder.append("\n");
                builder.append(error);
                builder.append("\n");
                builder.append("\n");
                builder.append("---------------MapDetail-------------");
                builder.append("\n");
                for (StackTraceElement element : strings) {
                    builder.append(element);
                    builder.append("\n");
                }
                report = builder.toString();
                bufferedWriter.write(report);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * save run time exception to sdcard
     *
     * @param strings error
     */
    public void saveRunCaught(String strings) {
        checkDir();
        File dir = new File(ROOT + "/" + getTodayDate() + "__" + getCurrentTime() + ".txt");
        if (root.isDirectory()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir)));
                bufferedWriter.write(strings);
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    /**
     * get current date
     *
     * @return date string
     */
    public String getTodayDate() {
        final Calendar c = Calendar.getInstance();
        int todayDate = (c.get(Calendar.YEAR) * 10000) +
                ((c.get(Calendar.MONTH) + 1) * 100) +
                (c.get(Calendar.DAY_OF_MONTH));
        Log.w("DATE:", String.valueOf(todayDate));
        return (String.valueOf(todayDate));
    }

    /**
     * get current date
     *
     * @return date string
     */
    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return (String.valueOf(dateFormat.format(new Date())));
    }

    /**
     * get current date
     *
     * @return date string
     */
    public String[] getDateTimeArray() {
        String dateTime = getDateTime();
        return dateTime.split(" ");
    }


    /**
     * get current time
     *
     * @return time string
     */
    public String getCurrentTime() {
        final Calendar c = Calendar.getInstance();
        int currentTime = (c.get(Calendar.HOUR_OF_DAY) * 10000) +
                (c.get(Calendar.MINUTE) * 100) +
                (c.get(Calendar.SECOND));
        Log.w("TIME:", String.valueOf(currentTime));
        return (String.valueOf(currentTime));
    }

    /**
     * check if directory exist or not
     */
    public void checkDir() {
        if (!system_root.exists()) {
            system_root.mkdir();
        }
        if (!root.exists()) {
            root.mkdir();
        }
    }


    /**
     * set data in session
     *
     * @param message user getter setter
     */
    /*public void setPreference(User message) {
        preferences.clearPrefData();
        preferences.setPrefData(message);
        preferences.setRemember(true);
    }*/

    /**
     * set data in session
     *
     * @param message user getter setter
     */
    public void setMember(Object message) {
        preferences.setMemberData(message);
    }

    /**
     * set data in session
     */
    public static Object getMemberData() {
        return preferences.getMemberData();
    }


    /**
     * clear data in session
     */
    public void setClearPreference() {
        preferences.clearPrefData();
    }


    /**
     * go to intent activity
     *
     * @param activity context requesting for method
     * @param toClass  context to where intent is fire
     */
    public void goToIntent(Activity activity, Class<?> toClass) {
        Intent intent = new Intent(activity, toClass);
        activity.startActivity(intent);
        activity.finish();
    }


    /**
     * get date formatted
     *
     * @return date string
     */
    public static String formateDate() {
        return day + "/" + month + "/" + yr;
    }

    /**
     * log to console
     *
     * @param TAG activity name
     * @param msg message to print
     */
    public static void log(String TAG, String msg) {
        Log.i(TAG, msg);
    }

    /**
     //     * show date picker and set value to text view
     //     *
     //     * @param textView TextView to store value
     //     * @param activity context requesting for method
     //     */
    /*public static void showDatePicker(TextView textView, Activity activity) {
        //get text view
        final TextView view = (TextView) textView;
        // get calender instance
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog();
        datePickerDialog.setMinDate(calendar);
        datePickerDialog.show(activity.getFragmentManager(), "datePicker");
        datePickerDialog.setThemeDark(true);
        datePickerDialog.setAccentColor(activity.getResources().getColor(R.color.light_blue));
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
                int mon = monthOfYear + 1;
                // format date
                DATE = year + "-" + updateDay((mon))+ "-"+ updateDay(dayOfMonth);
                view.setText(DATE);
            }
        });
    }*/


    /**
     * open camera
     *
     * @param reqCode request code
     */
    public void openImageIntent(int reqCode, Activity activity, Uri outputFileUri) {
        /*final File root = new File(Environment.getExternalStorageDirectory() + File.separator +
                getResources().getString(R.string.app_name) + File.separator);
        // Determine Uri of camera image to save.

        root.mkdirs();
        final String fname = System.currentTimeMillis() + ".jpg";
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);*/

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = activity.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // file system.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of file system options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
        activity.startActivityForResult(chooserIntent, reqCode);
    }

    /**
     * get image as string from byte
     *
     * @param bmp bitmap
     * @return base 64 of image
     */
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 20, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


    /**
     * get byte of base64 string image to set in imageview
     *
     * @param img_str string
     * @return byte
     */
    public static byte[] getByteofBase64Image(String img_str) {
        return Base64.decode(img_str.getBytes(), Base64.DEFAULT);
    }


    /**
     * set image from base64 string
     *
     * @param img       string
     * @param imageView image
     */
    public void setImageFromBase64(String img, ImageView imageView, Drawable drawable) {
        if (img != null) {
            if (!img.equals("")) {
                byte[] img_arr = Global.getByteofBase64Image(img);
                Bitmap bitmap = BitmapFactory.decodeByteArray(img_arr, 0, img_arr.length);
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setBackgroundDrawable(drawable);
            }
        }
        System.gc();
    }

    /**
     * set image from base64 string
     *
     * @param img       string
     * @param imageView image
     */
    public void setImageFromBase64(String img, ImageView imageView, Drawable drawable,
                                   ProgressBar bar) {
        if (img != null) {
            if (!img.equals("")) {
                byte[] img_arr = Global.getByteofBase64Image(img);
                Bitmap bitmap = BitmapFactory.decodeByteArray(img_arr, 0, img_arr.length);
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setBackgroundDrawable(drawable);
            }
        }
        bar.setVisibility(View.INVISIBLE);
        System.gc();
    }

    /**
     * set image from base64 string
     *
     * @param img string
     */
    public Bitmap setBitmapImageFromBase64(String img) {
        Bitmap bitmap = null;
        if (img != null) {
            if (!img.equals("")) {
                byte[] img_arr = Global.getByteofBase64Image(img);
                bitmap = BitmapFactory.decodeByteArray(img_arr, 0, img_arr.length);
            }
        }
        System.gc();
        return bitmap;
    }


    /**
     * post method for webservice
     * @param BASE_URL
     */
    public void post(String BASE_URL) {
        try {
            String data = URLEncoder.encode("api_token", "UTF-8")
                    + "=" + URLEncoder.encode("indian-express-token-id", "UTF-8");
            data += "&" + URLEncoder.encode("member_email", "UTF-8") + "="
                    + URLEncoder.encode("hemant.bhattrai@webdunia.net", "UTF-8");
            data += "&" + URLEncoder.encode("member_password", "UTF-8")
                    + "=" + URLEncoder.encode("123456", "UTF-8");


            String text = "";
            BufferedReader reader = null;
            URL url = new URL(BASE_URL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            text = sb.toString();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception ex) {
        }
    }

    /**
     * check id version is greater than 23 api (M)
     * @return
     */
    public static boolean checkVersion23(){
        return  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * check id version is greater than 21 api (L)
     * @return
     */
    public static boolean checkVersion21(){
        return  Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

}
