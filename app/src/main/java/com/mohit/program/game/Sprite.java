package com.mohit.program.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Author @ Mohit Soni on 14-05-2018 11:11 AM.
 */

public class Sprite {
    private int DIRECTION[] = {3, 1, 0, 2};
    private static final int ROW = 4;
    private static final int COLUMN = 3;

    private int x = 0;
    private int y = 0;

    private int xSpeed;
    private GameView view;
    private Bitmap bit;
    private int ySpeed;

    private int currentFrame = 0;
    private int width;
    private int height;

    private int image_position = 0;

    public Sprite(GameView view, Bitmap bit) {
        this.view = view;
        this.bit = bit;
        this.width = bit.getWidth() / COLUMN;
        this.height = bit.getHeight() / ROW;
        Random ran = new Random();
        xSpeed = ran.nextInt(10 * 2) - 5;
        ySpeed = ran.nextInt(10 * 2) - 5;
    }

    public Sprite() {
    }

    private void upDate() {
        if (x > view.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        if (y > view.getWidth() - width - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        x = x + xSpeed;
        currentFrame = ++currentFrame % COLUMN;
    }

    public void onDraw(Canvas canvas) {
        upDate();
        int srcX = currentFrame * width;
        int srcY = getAnimation() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dis = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bit, src, dis, null);
    }

    public int getAnimation() {
        double dir = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int diraction = (int) Math.round(dir) % ROW;
        Log.i("arc", Integer.toString(diraction));
        return DIRECTION[diraction];
    }

    public boolean isCollection(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}
