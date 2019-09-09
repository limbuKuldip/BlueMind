package com.bluemind.bluemind.meditation;

public class MeditationPlayerList {
    private String name, coverImage;

    public MeditationPlayerList(){

    }

    public MeditationPlayerList(String name, String coverImage){
        super();
        this.name = name;
        this.coverImage = coverImage;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
