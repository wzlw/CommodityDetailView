package com.zl.detailDemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zl.pullimagedemo.R;
import com.zl.detaillib.view.DetailView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        DetailView dv = (DetailView) findViewById(R.id.dv_main);
        dv.setOnLoadUrlListener(new DetailView.OnLoadUrlListener() {
            @Override
            public String loadUrl() {
                return "http://www.goyoo.com";
            }

            @Override
            public void initWebView(WebView view) {
                view.setWebViewClient(new PullWebViewClient(MainActivity.this));
            }

        });


        mInflater = LayoutInflater.from(this);
        View view1 = mInflater.inflate(R.layout.viewpager_item, null);
        View view2 = mInflater.inflate(R.layout.viewpager_item, null);
        View view3 = mInflater.inflate(R.layout.viewpager_item, null);
        View view4 = mInflater.inflate(R.layout.viewpager_item, null);
        View view5 = mInflater.inflate(R.layout.viewpager_item, null);
        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        viewPager.setAdapter(new MyAdapter(views));
    }

    private class MyAdapter extends PagerAdapter{

        private final ArrayList<View> views;

        public MyAdapter(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    }
}
