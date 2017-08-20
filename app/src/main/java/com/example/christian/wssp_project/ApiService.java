package com.example.christian.wssp_project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by christian on 8/19/2017.
 */

public interface ApiService {
    @GET("users")
    Call<List<UserInfo>> setUserQuery(@Query("id") int id);

    @GET("photos")
    Call<List<UserPhoto>> setPhotoQuery(@Query("id") int id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
