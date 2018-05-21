package com.mohit.program.grid_wallpaper;

import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.widget.GridView;

import java.util.ArrayList;

public class GridWallPaperMaster extends Activity {

    GridView grid;
    int number =0, x = 0, y = 0, width = 0, height = 0, matrix = 0, array_index =0, color_index=0;
    ArrayList<Integer> arrayList = new ArrayList<>();
    GridWallPaperAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid= (GridView)findViewById(R.id.gridview);

        Display  display = getWindowManager().getDefaultDisplay();
        Point point = new Point();

        display.getSize(point);
        x = point.x;
        y = point.y;
        number = grid.getCount();
        calculateNum(x,y);
        setAdapter();

        new Update().execute();

    }

    public class Update extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setAdapter();
                }
            });
            new Update().execute();
        }
    }

    private void calculateNum(int x, int y){
        width = x/100;
        height = y/100;
        matrix = width * height;

    }

    public void setAdapter(){
        arrayList.clear();
        for(int i=0;i<matrix;i++){
            int j = Math.abs(GridWallPaperUtil.getRemdom(10)-10);
            if(j>10){
                j = Math.abs(j - 10);
            }
            arrayList.add(GridWallPaperUtil.COLORS[j]);
        }
        adapter = new GridWallPaperAdapter(this,arrayList);
        grid.setAdapter(adapter);
    }
}
