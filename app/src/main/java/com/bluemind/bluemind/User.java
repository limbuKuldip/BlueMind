package com.bluemind.bluemind;

public class User {
    String userName;
    String email;

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getUserName(){
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
