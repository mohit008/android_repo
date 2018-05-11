package com.mohit.program.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 03:30 PM.
 */

public class Fragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, container, false);
        Button btItem = (Button) view.findViewById(R.id.btItem);
        btItem.setText("Fragment 3");
        btItem.setVisibility(View.VISIBLE);
        return view;
    }

}
