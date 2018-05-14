package com.mohit.program.set_intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;

/**
 * Author @ Mohit Soni on 14-05-2018 04:25 PM.
 */

public class IntentSample extends Activity {

    long startMillis = 0, endMillis = 0;
    String[] dialogButton = new String[]{"Calender", "Phone", "Email"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setVisibility(View.VISIBLE);

        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dialogButton));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setCalender();
                }
                if (position == 1) {
                    setPhone();
                }
                if (position == 2) {
                    setEmail();
                }
            }
        });
    }

    /**
     * add to calender
     */
    public void setCalender() {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, 9, 14, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, 9, 14, 8, 45);
        endMillis = endTime.getTimeInMillis();

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.putExtra(CalendarContract.Events.TITLE, "Neel Birthday");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This is a sample description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Guest House");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
        startActivity(intent);
    }

    /**
     * call phone
     *
     */
    public void setPhone() {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:9876543210"));
        startActivity(call);
    }

    /**
     * call email
     */
    public void setEmail() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        sharingIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dsf@gmail.com"});
        startActivity(Intent.createChooser(sharingIntent, "Query..."));

    }
}
