package com.example.christian.wssp_project;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;

/**
 * Created by christian on 8/20/2017.
 */

public class RecycleViewFragment extends Fragment {
    private static RecyclerAdapter mAdapter;
    private static UserInfoCallback mInfoCallback;
    private static UserPhotoCallback mPicCallback;
    private static ApiService mRetrofitApi;
    private static final int USER_NUM = 10;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /* instantiate the adapter and retrofit callback functions */
        mAdapter = new RecyclerAdapter((ClickInterface)context);
        mInfoCallback = new UserInfoCallback(mAdapter);
        mPicCallback = new UserPhotoCallback(mAdapter);

        /* pass the interface to retrofit to set baseUrl & JSON convertor */
        mRetrofitApi = ApiService.retrofit.create(ApiService.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_main, container, false);

        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutMgr);
        rView.setAdapter(mAdapter);

        /* request the user information and photo from the server
         * register callback functions to handle the server's response */
        for(int i = 1; i <= USER_NUM; ++i) {
            try {
                Call<List<UserInfo>> callinfo = mRetrofitApi.setUserQuery(i);
                callinfo.enqueue(mInfoCallback);

                Call<List<UserPhoto>> callpic = mRetrofitApi.setPhotoQuery(i);
                callpic.enqueue(mPicCallback);

            } catch (Exception ex) {
                Log.d("RETROFIT_EXCEPTION", ex.getMessage());
            }
        }

        return view;
    }

}
