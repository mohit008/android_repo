package com.mohit.program.action_bar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 04:48 PM.
 */

public class SearchActionBarSample extends Activity {
    SearchView searchview;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.GINGERBREAD_MR1) {
            ActionBar action = getActionBar();
            action.setDisplayHomeAsUpEnabled(true);
        }
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        text.setText("Query : " + query);
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        text.setText(newText);
                        return false;
                    }
                });
                break;
            default:
                /* we can also use
                 *
                 * onBackPressed();  : use back pressed method.
                 * this.finish();	: onDestroy() is executed.*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		/* adding search widget */
        searchview = (SearchView) MenuItemCompat.getActionView(search);
        searchview.setQueryHint("Search");
        searchview.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));
		/* enable submit button */
        searchview.setSubmitButtonEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

}
