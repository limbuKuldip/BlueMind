package com.bluemind.bluemind.online_yoga;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

public class FullScreenClient extends WebChromeClient {
    private FrameLayout.LayoutParams matchParentLayout = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private ViewGroup content;
    private ViewGroup parent;
    View customView;

    public FullScreenClient(ViewGroup parent, ViewGroup content){
        this.parent = parent;
        this.content = content;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback){
        customView = view;
        view.setLayoutParams(matchParentLayout);
        parent.addView(view);
        content.setVisibility(View.GONE);
    }

    @Override
    public void onHideCustomView(){
        content.setVisibility(View.VISIBLE);
        parent.removeView(customView);
        customView = null;
    }
}
