package com.bluemind.bluemind;

public class UserData {
    public int user_id;
    public String submit_your_data;
    public String how_was_the_activity;

    public UserData(){

    }

    public UserData(int user_id, String submit_your_data, String how_was_the_activity){
        this.user_id = user_id;
        this.submit_your_data = submit_your_data;
        this.how_was_the_activity = how_was_the_activity;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSubmit_your_data(){
        return submit_your_data;
    }

    public void setSubmit_your_data(String submit_your_data) {
        this.submit_your_data = submit_your_data;
    }

    public String getHow_was_the_activity() {
        return how_was_the_activity;
    }

    public void setHow_was_the_activity(String how_was_the_activity) {
        this.how_was_the_activity = how_was_the_activity;
    }
}
