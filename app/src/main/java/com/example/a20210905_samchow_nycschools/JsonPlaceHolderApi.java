package com.example.a20210905_samchow_nycschools;

import com.example.a20210905_samchow_nycschools.Class.School;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    //create api function here

    @GET("s3k6-pzi2.json")
    Call<ArrayList<School>> getSchoolData(@QueryMap Map<String, String> parameters);
}
