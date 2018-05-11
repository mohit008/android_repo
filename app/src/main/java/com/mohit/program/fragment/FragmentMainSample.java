package com.mohit.program.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 03:18 PM.
 */

public class FragmentMainSample extends FragmentActivity {

    private ListView drawer_layout_list;
    private DrawerLayout d_layout;
    private ActionBarDrawerToggle a_toggle;

    private boolean mySlideState = true;

    private String arg[] = {"one", "two", "three"};

    // fragment to open
    private Fragment frag[] = {
            new Fragment1(),
            new Fragment2(),
            new Fragment3(),
    };

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.fragment_layout);

        d_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout_list = (ListView) findViewById(R.id.drawer_layout_list);

        // give shadow
//        d_layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        d_layout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int arg0) {

            }

            @Override
            public void onDrawerSlide(View arg0, float arg1) {

            }

            @Override
            public void onDrawerOpened(View arg0) {
                mySlideState = false;

            }

            @Override
            public void onDrawerClosed(View arg0) {
                mySlideState = true;

            }
        });

        // action bar button behavior
        /*a_toggle = new ActionBarDrawerToggle(this, d_layout, R.drawable.ic_drawer1, R.string.drawer_open, R.string.drawer_close);*/

        // pass to drawer listener
        /*d_layout.setDrawerListener(a_toggle);*/

        // enabling back button
		/*getActionBar().setDisplayHomeAsUpEnabled(true);*/

        // enabling home button
		 /*getActionBar().setHomeButtonEnabled(true);*/

        // add content to array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arg);
        drawer_layout_list.setAdapter(adapter);
        drawer_layout_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                d_layout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);

                        mySlideState = true;

                        // calling and managing fragments
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.drawer_layout_main, frag[arg2]);
                        tx.commit();
                    }
                });
                d_layout.closeDrawer(drawer_layout_list);
            }
        });

        // Default fragment when activity starts
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.drawer_layout_main, frag[0]);
        tx.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                clickEventSlide();
                mySlideState = false;
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * change drawer state
     */
    public void clickEventSlide() {
        if (mySlideState) {
            d_layout.openDrawer(Gravity.END);
        } else {
            d_layout.closeDrawer(Gravity.END);
        }
    }
}
