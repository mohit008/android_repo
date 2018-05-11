package com.mohit.program.util;

import java.util.ArrayList;

/**
 * Author @ Mohit Soni on 11-05-2018 12:43 PM.
 */

public class Bean {

    String text;
    String id;
    ArrayList<Bean> arrayList = new ArrayList<>();

    public ArrayList<Bean> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Bean> arrayList) {
        this.arrayList = arrayList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
