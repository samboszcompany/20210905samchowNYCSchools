package com.example.a20210905_samchow_nycschools;

import android.util.Log;

import com.example.a20210905_samchow_nycschools.Class.School;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiHandle {

    private GlobleValue globleValue = new GlobleValue();

    public Retrofit getRetrofit() {
        Log.d("debug", "globleValue.getApiUrl() =  " + globleValue.getApiUrl());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(globleValue.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public JsonPlaceHolderApi getJsonPlaceHolderApi (){
       return getRetrofit().create(JsonPlaceHolderApi.class);
    }

    public void getSchoolData(Map<String,String> paramList, final ApiCallBack apiCallBack) {
        Map<String, String> parameters = paramList;

        //calling api
        Call<ArrayList<School>> call = getJsonPlaceHolderApi().getSchoolData(parameters);
        call.enqueue(new Callback<ArrayList<School>>() {
            @Override
            public void onResponse(Call<ArrayList<School>> call, Response<ArrayList<School>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                ArrayList<School> posts = response.body();

                apiCallBack.success(posts);
            }

            @Override
            public void onFailure(Call<ArrayList<School>> call, Throwable t) {
                Log.d("debug", "return onFailure ");
                apiCallBack.failure(t);
            }
        });
    }

}
