package com.example.christian.wssp_project;

/**
 * Created by christian on 8/19/2017.
 */

public class UserEntry {
    private UserInfo info;
    private UserPhoto pic;

    public UserEntry(UserInfo arg1, UserPhoto arg2) {
        this.info = arg1;
        this.pic  = arg2;
    }

    public void setProfile(UserInfo info){
        this.info = info;
    }

    public void setPicture(UserPhoto pic){
        this.pic = pic;
    }

    public UserInfo getProfile(){
        return this.info;
    }

    public UserPhoto getPicture() {
        return this.pic;
    }
}
