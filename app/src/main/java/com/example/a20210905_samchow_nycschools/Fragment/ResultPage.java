package com.example.a20210905_samchow_nycschools.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a20210905_samchow_nycschools.ApiCallBack;
import com.example.a20210905_samchow_nycschools.ApiHandle;
import com.example.a20210905_samchow_nycschools.Class.School;
import com.example.a20210905_samchow_nycschools.R;
import com.example.a20210905_samchow_nycschools.SchoolAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultPage extends Fragment {

    private FragmentManager fragmentManager;
    private ApiHandle apiHandle;
    private ListView lv_result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.result_page, container, false);

        fragmentManager = getFragmentManager();

        lv_result = view.findViewById(R.id.lv_result);

        SchoolAdapter schoolAdapter = new SchoolAdapter(this.getActivity().getApplicationContext());
        lv_result.setAdapter(schoolAdapter);

        apiHandle = new ApiHandle();

        apiHandle.getSchoolData(new HashMap<>(), new ApiCallBack() {
            @Override
            public void success(ArrayList result) {
                ArrayList<School> posts = result;
                schoolAdapter.setData(posts);
            }

            @Override
            public void failure(Throwable t) {

            }
        });


        return view;
    }


}
