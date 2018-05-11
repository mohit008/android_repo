package com.mohit.program.tab;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 06:23 PM.
 */

@SuppressWarnings("deprecation")
public class TabHostSample extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host_layout);


        TabHost tab = getTabHost();

        TabSpec news = tab.newTabSpec("New");
        news.setIndicator("Check New Job");
        Intent intent = new Intent(this,Tab_1.class);
        news.setContent(intent);


        TabSpec old = tab.newTabSpec("Old");
        old.setIndicator("Check Old Job");
        Intent intent_old = new Intent(this,Tab_2.class);
        old.setContent(intent_old);

        tab.addTab(news);
        tab.addTab(old);
    }
}
