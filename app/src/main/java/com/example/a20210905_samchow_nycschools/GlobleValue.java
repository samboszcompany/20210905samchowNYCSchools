package com.example.a20210905_samchow_nycschools;

public class GlobleValue {
    //any globle value can put in here
    private String apiUrl = "https://data.cityofnewyork.us/resource/";

    public static final int SCHOOL_INTRO = 0;
    public static final int SCHOOL_DETAIL = 1;
    public static final int SCHOOL_TRANSPORTATION = 2;


    public String getApiUrl() {
        return apiUrl;
    }
}
