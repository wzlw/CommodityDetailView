package com.zl.detailDemo;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zl on 2016/11/1.
 */

public class PullWebViewClient extends WebViewClient {

    public PullWebViewClient(Context context) {

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
