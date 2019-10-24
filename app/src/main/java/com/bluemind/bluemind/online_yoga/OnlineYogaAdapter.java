package com.bluemind.bluemind.online_yoga;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.bluemind.bluemind.R;
import com.bluemind.bluemind.chat.TextUtils;

import java.util.List;

public class OnlineYogaAdapter extends RecyclerView.Adapter<OnlineYogaAdapter.VideoViewHolder> {
    List<OnlineYoga> youtubeVideoList;

    public interface OnItemClickListener{
        void onItemClick(OnlineYoga item);
    }

    OnItemClickListener listener;

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
        holder.bind(youtubeVideoList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        WebView videoWeb;
        LinearLayout mainLayout, subLayout;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.online_yogaWebView);
            mainLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);
            subLayout = (LinearLayout) itemView.findViewById(R.id.subLayout);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new FullScreenClient(mainLayout, subLayout));
            videoWeb.setWebViewClient(new WebViewClient(){
                public void onPageReceived(WebView view, String url){
                    url = view.getTitle();
                }
            });
        }

        public void bind(final OnlineYoga item, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
