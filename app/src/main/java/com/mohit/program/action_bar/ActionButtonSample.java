package com.mohit.program.action_bar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 11-05-2018 04:41 PM.
 */

public class ActionButtonSample extends Activity {
    private ShareActionProvider m_provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            ActionBar action = getActionBar();

            /*action.hide();*/
            action.show();
            action.setTitle("  Action");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu):
                Toast.makeText(getBaseContext(), "Menu Clicked", Toast.LENGTH_LONG).show();
                break;
            default:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        MenuItem menu_item = menu.findItem(R.id.menu_share);
        m_provider = (ShareActionProvider) menu_item.getActionProvider();
//        m_provider.setShareIntent(setintent());
        return super.onCreateOptionsMenu(menu);
    }

    private Intent setintent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        return intent;
    }
}
