package com.mohit.program.action_bar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mohit.program.R;
import com.mohit.program.fragment.Fragment1;
import com.mohit.program.fragment.Fragment2;

/**
 * Author @ Mohit Soni on 14-05-2018 03:13 PM.
 */

public class CollapseToolBar extends AppCompatActivity {

    private static CollapsingToolbarLayout collapsingToolbarLayout = null;
    private SearchView searchview;
    private static Toolbar toolbar;
    private DrawerLayout _drawer;
    private NavigationView _nView;
    private FragmentManager _manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapse_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        _drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        _nView = (NavigationView) findViewById(R.id.nvView);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("MasterMapView");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Activity");

        dynamicToolbarColor();
        toolbarTextAppernce();

        _manager = this.getSupportFragmentManager();


        _nView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return false;
            }
        });
    }

    /**
     * change navigation item fragment
     *
     * @param menuItem
     */
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.menu_share:
                fragmentClass = Fragment1.class;
                break;
            case R.id.menu:
                fragmentClass = Fragment2.class;
                break;
            default:
                fragmentClass = Fragment1.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();

        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        _drawer.closeDrawers();
    }

    /**
     * create dynaminc collapse
     */
    private void dynamicToolbarColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // change toolbar color on collapse
                /*collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(Color.RED));*/
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(Color.WHITE));
                collapsingToolbarLayout.setContentScrimColor(Color.RED);
            }
        });
    }

    /**
     * Text appearence in toot bar
     */
    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);

        /*SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        searchview = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchview.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));*/

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show();
                //search view query
                searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        // do some when enter pressed
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //do something while typing
                        return false;
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * set title of current fragment
     *
     * @param name
     */
    public static void setTitle(String name) {
        toolbar.setTitle(name);
        collapsingToolbarLayout.setTitle(name);
    }
}
