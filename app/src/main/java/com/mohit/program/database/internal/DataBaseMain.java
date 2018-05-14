package com.mohit.program.database.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.mohit.program.R;

/**
 * Author @ Mohit Soni on 14-05-2018 12:28 PM.
 */

public class DataBaseMain extends Activity {
    private DataBaseOperation dbHelper;
    private SimpleCursorAdapter dataAdapter;

    private Button show, save, delete;
    private EditText name, surname;
    private Dialog dialog;

    private String getNamen, getSurname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_layout);
        save = (Button) this.findViewById(R.id.save);
        show = (Button) this.findViewById(R.id.show);
        delete = (Button) this.findViewById(R.id.delete);
        name = (EditText) this.findViewById(R.id.name);
        surname = (EditText) this.findViewById(R.id.surname);

        //		this.deleteDatabase("MyDB");
        //		dbPath = this.getDatabasePath("MyDB").toString();
        //		if(!checkDataBase()){
        //		}

        dbHelper = new DataBaseOperation(this);
        dbHelper.open();
        dbHelper.deleteAllDetail();
        dbHelper.close();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getNamen = name.getText().toString();
                getSurname = surname.getText().toString();

                dbHelper.insertSomeDetail(getNamen, getSurname);

                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
                Log.i("Saved", getNamen + " : " + getSurname);

                name.setText("");
                surname.setText("");
            }
        });

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                displayListView();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final AlertDialog.Builder _builder = new AlertDialog.Builder(DataBaseMain.this);
                _builder.setIcon(R.mipmap.ic_launcher);
                _builder.setTitle("Delete");
                _builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteDetail(name.getText().toString());
                        dialog.dismiss();
                        Log.i("delete", name.getText().toString());
                    }
                });
                _builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog _alert = _builder.create();
                _alert.show();
            }
        });
    }

    private void displayListView() {
        Cursor cursor = dbHelper.fetchAllDetail();
        // The desired columns to be bound
        String[] columns = new String[]{
                DataBaseOperation.NAME,
                DataBaseOperation.SURNAME
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                android.R.id.text1,
                android.R.id.text2,
        };

        // create the adapter using the cursor pointing to the desired data as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2,
                cursor,
                columns,
                to,
                0);
        ListView listView = (ListView) findViewById(R.id.list);

        // assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }

    /**
     * check id data base is available or not
     *
     * @return
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("dbPath", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
        }
        return checkDB != null ? true : false;
    }
}
