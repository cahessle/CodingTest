package com.example.christian.wssp_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private UserInfoCallback mInfoCallback;
    private UserPhotoCallback mPicCallback;
    private static final int USER_NUM = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* set up the recycleView for fast scrolling */
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        /* instantiate the adapter and retrofit callback functions */
        mAdapter = new RecyclerAdapter();
        mInfoCallback = new UserInfoCallback(mAdapter);
        mPicCallback = new UserPhotoCallback(mAdapter);

        /* pass the interface to retrofit to set baseUrl & JSON convertor */
        ApiService api = ApiService.retrofit.create(ApiService.class);

        /* request the user information and photo from the server
         * register callback functions to handle the server's response */
        for(int i = 1; i <= USER_NUM; ++i) {
            try {
                Call<List<UserInfo>> callinfo = api.setUserQuery(i);
                callinfo.enqueue(mInfoCallback);

                Call<List<UserPhoto>> callpic = api.setPhotoQuery(i);
                callpic.enqueue(mPicCallback);

            } catch (Exception ex) {
                Log.d("RETROFIT_EXCEPTION", ex.getMessage());
            }
        }

        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
