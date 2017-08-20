package com.example.christian.wssp_project;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ClickInterface {
    private static InfoScreenFragment mInfoScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mInfoScreen = new InfoScreenFragment();
            // create a FragmentManager
            FragmentManager fm = getFragmentManager();
            // create a FragmentTransaction to begin the transaction and replace the Fragment
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // replace the FrameLayout with new Fragment
            fragmentTransaction.replace(R.id.fragment_container, new RecycleViewFragment());
            fragmentTransaction.commit(); // save the changes
        }
    }
    public void openInfoScreen(UserInfo details) {
        mInfoScreen.SetDetailInfo(details);
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        fragmentTransaction.replace(R.id.fragment_container, mInfoScreen);
        // and add the transaction to the back stack so the user can navigate back
        fragmentTransaction.addToBackStack(null);
        // Commit the transaction
        fragmentTransaction.commit();
    }
    public void onXmlClick(View v){
        mInfoScreen.mapClickEvent();
    }
}

