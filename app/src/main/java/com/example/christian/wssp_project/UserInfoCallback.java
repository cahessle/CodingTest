package com.example.christian.wssp_project;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by christian on 8/19/2017.
 */

public class UserInfoCallback implements Callback<List<UserInfo>> {
    private RecyclerAdapter mAdapter;

    public UserInfoCallback(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
        if (response.isSuccessful()) {
            List result = response.body();

            if(result.isEmpty()) {
                Log.d("CALLBACK","Empty UserInfo Query!");
            }
            else {
                mAdapter.AddUser((UserInfo)result.get(0));
            }
        }
    }

    @Override
    public void onFailure(Call<List<UserInfo>> call, Throwable t){
        Log.d("CALLBACK","UserInfoCallback Error " + t.getMessage());
    }


}
