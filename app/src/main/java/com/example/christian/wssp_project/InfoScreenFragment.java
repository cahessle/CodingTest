package com.example.christian.wssp_project;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by christian on 8/20/2017.
 */

public class InfoScreenFragment extends Fragment {
    private String mName;
    private String mUsername;
    private String mEmail;
    private String mAddress;
    private String mPhone;
    private String mWebsite;
    private String mGeo;
    private String mCoordinates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.infoscreen_fragment, container, false);

        TextView name     = (TextView) view.findViewById(R.id.nameField);
        TextView username = (TextView) view.findViewById(R.id.usernameField);
        TextView email    = (TextView) view.findViewById(R.id.emailField);
        TextView address  = (TextView) view.findViewById(R.id.addressField);
        TextView phone    = (TextView) view.findViewById(R.id.phoneField);
        TextView website  = (TextView) view.findViewById(R.id.websiteField);
        TextView geo      = (TextView) view.findViewById(R.id.geoField);

        name.setText(mName);
        username.setText(mUsername);
        email.setText(mEmail);
        address.setText(mAddress);
        phone.setText(mPhone);
        website.setText(mWebsite);
        geo.setText(mGeo);

        return view;
    }

    public void SetDetailInfo(UserInfo details){
        UserInfo.HomeAddress addr = details.getAddress();
        String addrstr = addr.street + ", " + addr.suite + "\n" + addr.city + ", " + addr.zipcode;
        String geostr  = addr.geo.lat + "," + addr.geo.lng;

        mName     = details.getName();
        mUsername = details.getUsername();
        mEmail    = details.getEmail();
        mPhone    = details.getPhone();
        mWebsite  = details.getWebsite();
        mAddress  = addrstr;
        mGeo      = geostr;

        /* construct argument string for google maps api */
        mCoordinates = "geo:" + geostr + "?z=0";
    }

    /* click event for opening google maps */
    public void mapClickEvent() {
        Uri gmmIntentUri = Uri.parse(mCoordinates);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
