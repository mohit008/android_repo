package com.mohit.program.sell_comand;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class AppDetail implements Parcelable {

    int id;
    String name;
    String apk;
    String pkg;
    Drawable icon;

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public AppDetail(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        apk = parcel.readString();
        pkg = parcel.readString();
    }

    public AppDetail() {
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(apk);
        dest.writeString(pkg);
    }

    public static final Parcelable.Creator<AppDetail> CREATOR = new Parcelable.Creator<AppDetail>() {

        @Override
        public AppDetail createFromParcel(Parcel parcel) {
            return new AppDetail(parcel);
        }

        @Override
        public AppDetail[] newArray(int size) {
            return new AppDetail[0];
        }
    };
}
