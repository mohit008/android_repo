package com.mohit.program.collection_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author @ Mohit Soni on 26-04-2018 18:38.
 */

public class ExpandaleListViewSample extends Activity {

    List<String> groupList;
    ArrayList<HashMap<String, String>> childList;
    Map<String, ArrayList<HashMap<String, String>>> groupCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.expandable_list_sample);

        createGroup();
        createChild();

        expListView = (ExpandableListView) findViewById(R.id.ex_list);

        expListView.setAdapter(new BaseExpandableListAdapter() {

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return groupCollection.get(groupList.get(groupPosition)).get(childPosition);
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                final HashMap<String, String> child = (HashMap<String, String>) getChild(groupPosition, childPosition);
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
                }
                ((TextView) view.findViewById(android.R.id.text1)).setText(child.get("name"));
//                if (isLastChild) {
//                } else {
//                    convertView.setBackgroundColor(Color.WHITE);
//                }
                return view;
            }


            public int getChildrenCount(int groupPosition) {
                return groupCollection.get(groupList.get(groupPosition)).size();
            }

            public Object getGroup(int groupPosition) {
                return groupList.get(groupPosition);
            }

            public int getGroupCount() {
                return groupList.size();
            }

            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                String name = (String) getGroup(groupPosition);
                View view = convertView;
                if (view == null) {
                    view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
                }
                ((TextView) view.findViewById(android.R.id.text1)).setText(name);
                return view;
            }

            public boolean hasStableIds() {
                return true;
            }

            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        });

        expListView.setGroupIndicator(null);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                return false;
            }
        });
    }

    /**
     * add parent
     */
    private void createGroup() {
        groupList = new ArrayList<String>();
        groupList.add("Mortise Locks");
        groupList.add("Rim Locks");
    }

    /**
     * add child
     */
    private void createChild() {
        groupCollection = new LinkedHashMap<String, ArrayList<HashMap<String, String>>>();
        for (String laptop : groupList) {
            if (laptop.equals("Mortise Locks")) {
                for (int i = 0; i < 1; i++) {
                    if (i == 0) {
                        HashMap<String, String> child = new HashMap<String, String>();
                        child.put("name", "...YDM4109");
                        child.put("subtext", "...Biometric Mortise Lock");
                        loadChild(child, 1);
                    }
                }
            } else if (laptop.equals("Rim Locks")) {
                for (int i = 0; i <= 1; i++) {
                    if (i == 0) {
                        HashMap<String, String> child = new HashMap<String, String>();
                        child.put("name", "...YDR414");
                        child.put("subtext", "...Premium Fingerprint Rim Mounted Lock");
                        loadChild(child, 1);
                    } else if (i == 1) {
                        HashMap<String, String> child = new HashMap<String, String>();
                        child.put("name", "...YDR4110");
                        child.put("subtext", "...Biometric Rim Lock");
                        loadChild(child, 0);
                    }
                }
            }
            groupCollection.put(laptop, childList);
        }
    }

    private void loadChild(HashMap<String, String> laptopModels, int value) {
        if (value == 1)
            childList = new ArrayList<HashMap<String, String>>();
        childList.add(laptopModels);
    }


}
