package com.bluemind.bluemind.expert_Consultation;

public class ExpertsList {
    private int id;
    private String name, expertise, profilePicture;

    public ExpertsList(){

    }

    public ExpertsList(int id, String name, String expertise, String profilePicture){
        super();
        this.id = id;
        this.name = name;
        this.expertise = expertise;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
