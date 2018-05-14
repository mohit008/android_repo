package com.mohit.program.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 01:40 PM.
 */

public class DialogSample extends Activity {

    AlertDialog _alert;
    String[] dialogButton = new String[]{"Alert", "Dialog"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setVisibility(View.VISIBLE);

        // adapter
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dialogButton));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    callAlert();
                }
                if (position == 1) {
                    callDialog();
                }
            }
        });
    }

    /**
     * call alert dialog
     */
    public void callAlert() {
        final AlertDialog.Builder _builder = new AlertDialog.Builder(this);
        _builder.setIcon(R.mipmap.ic_launcher);
        _builder.setTitle("This is Dialog");
        _builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_LONG).show();
//					AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
//					builder.setTitle("Error");
//					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_LONG).show();
//						_alert = _builder.create();
//						_alert.show();
//					}
//					});
//
//					AlertDialog alert = builder.create();
//					alert.show();
            }
        });
        _builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        });
        _alert = _builder.create();
        _alert.show();

    }

    /**
     * create custom layout in dialog
     */
    public void callDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item);
        dialog.setTitle("This is custom Dialog");

        Button dialogButton = (Button) dialog.findViewById(R.id.btItem);
        dialogButton.setVisibility(View.VISIBLE);
        dialogButton.setText("Dialog Button");
        dialogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
