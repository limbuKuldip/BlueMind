package com.bluemind.bluemind.online_yoga;

public class OnlineYoga {
    String videoUrl;

    public OnlineYoga(){

    }

    public OnlineYoga(String videoUrl){
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
