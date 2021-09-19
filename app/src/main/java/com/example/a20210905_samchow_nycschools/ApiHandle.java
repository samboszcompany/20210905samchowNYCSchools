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

    public void getSchoolData(HashMap<String,String> paramList, final ApiCallBack apiCallBack) {
//        Map<String, String> parameters = paramList;
        Map<String, String> parameters = paramList;
        Log.d("debug", "calling getSchoolData");

        //calling api
        Call<ArrayList<School>> call = getJsonPlaceHolderApi().getSchoolData(parameters);
        call.enqueue(new Callback<ArrayList<School>>() {
            @Override
            public void onResponse(Call<ArrayList<School>> call, Response<ArrayList<School>> response) {
                Log.d("debug", "calling onResponse");

                if(!response.isSuccessful()){
                    Log.d("debug", "return !response.isSuccessful() ");
                    return;
                }

                ArrayList<School> posts = response.body();
                Log.d("debug", "return schoolArrayList = " + posts);

                if(posts.isEmpty()){
                }

                apiCallBack.success(posts);

                if(!response.isSuccessful()){

                }

//                Log.d("debug", "return isSuccessful ");
//                ArrayList<School> schoolArrayList = response.body();
//                Log.d("debug", "return schoolArrayList = " + response.body());
//                apiCallBack.success(schoolArrayList);
//
//                Log.d("debug", "response.isSuccessful() fail");
            }

            @Override
            public void onFailure(Call<ArrayList<School>> call, Throwable t) {
                Log.d("debug", "return onFailure ");
                apiCallBack.failure(t);
            }
        });
    }

}
