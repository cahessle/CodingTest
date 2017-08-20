package com.example.christian.wssp_project;

/**
 * Created by christian on 8/20/2017.
 * callback function for sending user information from the
 * RecycleView fragment to the InfoScreenFragment via MainActivity
 */
public interface ClickInterface {
    public void openInfoScreen(UserInfo details);
}
