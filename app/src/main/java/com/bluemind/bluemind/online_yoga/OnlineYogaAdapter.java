package com.bluemind.bluemind.online_yoga;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.bluemind.bluemind.R;

import java.util.List;

public class OnlineYogaAdapter extends RecyclerView.Adapter<OnlineYogaAdapter.VideoViewHolder> {
    List<OnlineYoga> youtubeVideoList;

    public OnlineYogaAdapter() {
    }

    public OnlineYogaAdapter(List<OnlineYoga> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_yoga_listview_items, parent, false);

        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.videoWeb.loadData(youtubeVideoList.get(position).getVideoUrl(), "text/html", "utf-8");

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;
        LinearLayout mainLayout;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.online_yogaWebView);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            });

        }
    }
}
