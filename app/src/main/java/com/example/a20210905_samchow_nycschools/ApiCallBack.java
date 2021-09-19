package com.example.a20210905_samchow_nycschools;

import java.util.ArrayList;

public interface ApiCallBack {
    void success(ArrayList result);
    void failure(Throwable t);
}