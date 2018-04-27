package com.mohit.program.collection_view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author @ Mohit Soni on 26-04-2018 19:07.
 */

public class ViewPagerWithPageIndicator extends Activity {
    private final String TAG = getClass().getSimpleName();

    private ViewPager viewPage;
    private Timer timer;
    private int position = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_indicator);

        viewPage = (ViewPager) findViewById(R.id.myviewpager);
        viewPage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = getLayoutInflater().inflate(R.layout.item, container, false);
                ((ImageView) view.findViewById(R.id.ivItem))
                        .setVisibility(View.VISIBLE);
                ((ImageView) view.findViewById(R.id.ivItem))
                         .setImageResource(R.mipmap.ic_launcher);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }
        });
        viewPage.setCurrentItem(position, true);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                changeIndicatorState(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        changePage();
    }

    /**
     * change background of text view according to position
     *
     * @param position
     */
    public void changeIndicatorState(int position) {
        int[] indicatorPos = {R.id.tvIndicator1, R.id.tvIndicator2, R.id.tvIndicator3, R.id.tvIndicator4};
        for (int i = 0; i < indicatorPos.length; i++) {
            TextView text = (TextView) findViewById(indicatorPos[i]);
            if (i == position) {
                text.setBackgroundResource(R.drawable.circle_white);
            } else {
                text.setBackgroundResource(R.drawable.circle_holo_blue_dark);
            }
        }
    }

    /**
     * change page periodically
     */
    protected void changePage() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        getPosition();
                        viewPage.setCurrentItem(position, true);           //change page position
                    }
                });
            }
        }, 2000, 3000);                                                  // first time,period time (you can control speed)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();                                                               // stop timer
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    /**
     * get position to change
     */
    public void getPosition() {
        if (position == 4) {
            position = 0;
        } else {
            position += 1;
        }
    }

}
