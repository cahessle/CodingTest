package com.example.christian.wssp_project;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by christian on 8/19/2017.
 */

public class UserPhotoCallback implements Callback<List<UserPhoto>> {
    private RecyclerAdapter mAdapter;

    public UserPhotoCallback(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onResponse(Call<List<UserPhoto>> call, Response<List<UserPhoto>> response) {
        if (response.isSuccessful()) {
            List result = response.body();

            if(result.isEmpty()) {
                Log.d("CALLBACK","Empty UserPhoto Query!");
            }
            else {
                mAdapter.AddPic((UserPhoto)result.get(0));
            }
        }
    }

    @Override
    public void onFailure(Call<List<UserPhoto>> call, Throwable t) {
        Log.d("CALLBACK","UserPhotoCallback Error " + t.getMessage());
    }
}
