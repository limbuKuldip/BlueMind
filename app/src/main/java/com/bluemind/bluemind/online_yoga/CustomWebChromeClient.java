package com.bluemind.bluemind.online_yoga;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

public class CustomWebChromeClient extends WebChromeClient {
    private FrameLayout.LayoutParams matchParentLayout = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    private ViewGroup content;
    private ViewGroup parent;
    View customView;

    public CustomWebChromeClient(android.view.ViewGroup parent, ViewGroup content)
    {
        this.parent = parent;
        this.content = content;
    }

    public void OnShowCustomView(View view, CustomViewCallback callback)
    {
        customView = view;
        view.setLayoutParams(matchParentLayout);
        parent.addView(view);
        content.setVisibility(View.GONE);
    }

    public void OnHideCustomView()
    {
        content.setVisibility(View.VISIBLE);
        parent.removeView(customView);
        customView = null;
    }
}
