package com.mohit.program.grid_wallpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.mohit.program.R;

import java.util.ArrayList;

/**
 * Created by Mohit Soni on 13-Jun-16.
 */
public class GridWallPaperAdapter extends BaseAdapter {

    private Context mContext;

    ArrayList<Integer> arrayList = new ArrayList<>();

    public GridWallPaperAdapter(Context c, ArrayList<Integer> arrayList) {
        mContext = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.custom_view, parent, false);
            LinearLayout linearLayout = (LinearLayout) grid.findViewById(R.id.ll_custom);

//            ColorDrawable drawable = (ColorDrawable) linearLayout.getBackground();
//            int colorFrom = drawable.getColor();
//            int colorTo = arrayList.get(position);
//
//            int duration = 1000;
//            ObjectAnimator.ofObject(linearLayout, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
//                    .setDuration(duration)
//                    .start();
            linearLayout.setBackgroundColor(arrayList.get(position));
        } else {
            grid = (View) convertView;
        }
        return grid;
    }

}
