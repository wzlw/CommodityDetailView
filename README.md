# CommodityDetailView
仿jd商品详情页
###引入detailLib库
在你应用的gradle文件中添加

    compile project(path: ':detaillib')
###在布局中使用

    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:wapperpadding="http://schemas.android.com/apk/res-auto"//千万别忘了添加这一行
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"
        >

        <com.zl.detaillib.view.DetailView
            android:id="@+id/dv_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            wapperpadding:padding="20dp"//两页的间隔区
            >

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <com.zl.detaillib.view.PageOneView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    wapperpadding:padding="20dp"
                    >

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:orientation="vertical">

                        <android.support.v4.view.ViewPager
                            android:id="@+id/vp_main"
                            android:layout_width="match_parent"
                            android:layout_height="300dp"
                            ></android.support.v4.view.ViewPager>

                        <TextView
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:text="hhhhhh"
                            android:textSize="100sp"/>
                    </LinearLayout>
                </com.zl.detaillib.view.PageOneView>

                <com.zl.detaillib.view.DetailWebView
                    android:id="@+id/ewv_main"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">

                </com.zl.detaillib.view.DetailWebView>

            </LinearLayout>

        </com.zl.detaillib.view.DetailView>

        <ImageView
            android:id="@+id/iv_main"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:layout_alignParentBottom="true"
            android:layout_alignParentRight="true"
            android:layout_marginBottom="30dp"
            android:layout_marginRight="10dp"
            android:background="@mipmap/ic_launcher"
            />
    </RelativeLayout>
    
###在Activity/Fragment中的使用
  
    final DetailView dv = (DetailView) findViewById(R.id.dv_main);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dv.scrollTop();//回到顶端的方法scrollTop()
            }
        });

        dv.setOnLoadUrlListener(new DetailView.OnLoadUrlListener() {
            @Override
            public String loadUrl() {
                return "http://www.baidu.com";//设置第二页加载的url
            }

            @Override
            public void initWebView(WebView view) {
                view.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                //初始化webview，例如setWebViewClient()
            }

        });
