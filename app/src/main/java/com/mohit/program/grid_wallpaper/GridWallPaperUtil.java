package com.mohit.program.grid_wallpaper;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Mohit Soni on 13-Jun-16.
 */
public class GridWallPaperUtil {

    public static int getRemdom(int val){
        Random ran = new Random();
        return ran.nextInt()%val;
    }


    static int[] COLORS ={
            Color.rgb( 41, 128, 185),
            Color.rgb( 231, 76, 60),
            Color.rgb( 241, 196, 15),
            Color.rgb( 39, 174, 96),
            Color.rgb( 142, 68, 173),
            Color.rgb( 192, 57, 43),
            Color.rgb(255, 65, 0),
            Color.rgb(0, 178, 92),
            Color.rgb(112, 229, 0),
            Color.rgb(4, 133, 157),
            Color.rgb(165, 0, 139),

    };



}
