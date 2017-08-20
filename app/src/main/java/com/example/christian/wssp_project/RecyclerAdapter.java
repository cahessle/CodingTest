package com.example.christian.wssp_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by christian on 8/19/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserProfileHolder> {
    /* hashmap used to pair user info and photo based on id number */
    private static Map<Integer, UserEntry> mDictionary;
    /* array used to map list view row position to id number */
    private static ArrayList<Integer> mPosition2KeyMapping;
    /* callback for triggering the info screen on list view clicks */
    private static ClickInterface mCallback;

    public static class UserProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /* variables used to store the user data for each row in the listview */
        private ImageView mUserThumbnail;
        private TextView  mUserName;
        private TextView  mUserCompany;
        private TextView  mUserCatchPhrase;
        private int mId;

        /* ViewHolder constructor */
        public UserProfileHolder(View v) {
            super(v);

            mUserThumbnail   = (ImageView) v.findViewById(R.id.imageView);
            mUserName        = (TextView)  v.findViewById(R.id.textViewName);
            mUserCompany     = (TextView)  v.findViewById(R.id.textViewCompany);
            mUserCatchPhrase = (TextView)  v.findViewById(R.id.textViewLine3);
            v.setOnClickListener(this);
        }

        /* store user info data */
        public void bindProfile(UserInfo user) {
            mId = user.getId();
            mUserName.setText(user.getName());
            mUserCompany.setText(user.getCompany().name);
            mUserCatchPhrase.setText(user.getCompany().catchPhrase);
        }

        /* call Picasso library to cache and render the thumbnail */
        public void bindPicture(UserPhoto pic){
            Picasso.with(itemView.getContext()).load(pic.getThumbnailUrl()).into(mUserThumbnail);
        }

        /* click event to display contact info screen */
        @Override
        public void onClick(View v) {
            UserEntry entry = mDictionary.get(mId);
            if(entry != null) {
                UserInfo user = entry.getProfile();
                if((user != null) && (mCallback != null)) {
                    mCallback.openInfoScreen(user);
                }
            }
        }
    }

    /* Adapter constructor */
    public RecyclerAdapter(ClickInterface listener) {
        mDictionary = new HashMap<Integer, UserEntry>();
        mPosition2KeyMapping = new ArrayList<Integer>();
        mCallback = listener;
    }

    /* store new user information in the hashmap */
    public void AddUser(UserInfo user) {
        Integer key = user.getId();
        if(mDictionary.containsKey(key)){
            mDictionary.get(key).setProfile(user);
        }
        else {
            UserEntry item = new UserEntry(user, null);
            mDictionary.put(key, item);
            mPosition2KeyMapping.add(key);
            this.notifyItemInserted(mDictionary.size());
        }
        this.notifyDataSetChanged();
    }

    /* store new photo in the hashmap */
    public void AddPic(UserPhoto pic) {
        Integer key = pic.getId();
        if(mDictionary.containsKey(key)){
            mDictionary.get(key).setPicture(pic);
        }
        else{
            UserEntry item = new UserEntry(null, pic);
            mDictionary.put(key, item);
            mPosition2KeyMapping.add(key);
            this.notifyItemInserted(mDictionary.size());
        }
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.UserProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row_view, parent, false);
        return new UserProfileHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.UserProfileHolder holder, int position) {
        UserEntry entry = mDictionary.get(mPosition2KeyMapping.get(position));
        if(entry != null) {
            UserInfo info = entry.getProfile();
            UserPhoto pic = entry.getPicture();
            if(info != null) {
                holder.bindProfile(info);
            }
            if(pic != null){
                holder.bindPicture(pic);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDictionary.size();
    }
}
