package com.example.christian.wssp_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SubMenuActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mAddress;
    private TextView mPhone;
    private TextView mWebsite;
    private TextView mGeo;
    private String   mCoordinates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mName     = (TextView) findViewById(R.id.textView1);
        mUsername = (TextView) findViewById(R.id.textView2);
        mEmail    = (TextView) findViewById(R.id.textView3);
        mAddress  = (TextView) findViewById(R.id.textView4);
        mPhone    = (TextView) findViewById(R.id.textView5);
        mWebsite  = (TextView) findViewById(R.id.textView6);
        mGeo      = (TextView) findViewById(R.id.textView7);

        /* get the user data from the main activity */
        Intent intent   = getIntent();
        String name     = "name:\n"         + intent.getExtras().getString("name");
        String username = "username:\n"     + intent.getExtras().getString("username");
        String email    = "email:\n"        + intent.getExtras().getString("email");
        String address  = "address:\n"      + intent.getExtras().getString("address");
        String phone    = "phone number:\n" + intent.getExtras().getString("phone");
        String website  = "website:\n"      + intent.getExtras().getString("web");
        String geo      = intent.getExtras().getString("geo");

        mName.setText(name);
        mUsername.setText(username);
        mEmail.setText(email);
        mAddress.setText(address);
        mPhone.setText(phone);
        mWebsite.setText(website);
        mGeo.setText(geo);

        /* construct argument string for google maps api */
        mCoordinates = "geo:" + geo + "?z=0";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /* click event for opening google maps */
    public void onClick(View v) {
        Uri gmmIntentUri = Uri.parse(mCoordinates);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
