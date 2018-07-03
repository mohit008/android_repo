package com.mohit.program.sell_comand;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GetAllPackages {

    private ArrayList<String> pkgPathList = new ArrayList<>();
    private ArrayList<AppDetail> pkgList = new ArrayList<>();
    Context context;

    public GetAllPackages(Context context){
        this.context = context;
    }

    public void getPackageDetail() {
        try {
            Process process = Runtime.getRuntime().exec("pm list packages -f");
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            InputStream in = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String out;
            while ((out = bufferedReader.readLine()) != null) {
                pkgPathList.add(out);
            }
            Log.i("package list", pkgPathList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        getPackage(pkgPathList);
        Log.i("SDFS", pkgList.toString());
    }

    public void getPackage(ArrayList<String> pdl) {
        for (String s : pdl) {
            String[] pck = s.substring(s.lastIndexOf("/") + 1, s.length()).split("=");
            PackageManager manager = context.getPackageManager();
            try {
                ApplicationInfo applicationInfo = manager.getApplicationInfo(pck[1], PackageManager.GET_META_DATA);
                AppDetail detail = new AppDetail();
                detail.setId(applicationInfo.uid);
                detail.setName((String) manager.getApplicationLabel(applicationInfo));
                detail.setApk(pck[0]);
                detail.setPkg(pck[1]);
                detail.setIcon(manager.getApplicationIcon(applicationInfo));
                pkgList.add(detail);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
