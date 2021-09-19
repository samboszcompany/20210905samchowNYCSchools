package com.example.a20210905_samchow_nycschools.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.a20210905_samchow_nycschools.R;

public class MainPage extends Fragment {


    private Button btn_search_school;
    private Button btn_search_sat;
    private FragmentManager fragmentManager;

    public MainPage(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.main_page, container, false);

        fragmentManager = getFragmentManager();

        btn_search_school = view.findViewById(R.id.btn_search_school);
        btn_search_sat = view.findViewById(R.id.btn_search_sat);

        btn_search_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fm_mainpage,new SearchPage(),"searchPage")
                        .addToBackStack(null) //allow the fragment can back to this page
                        .commit(); //need .commit() to execute
            }
        });

        btn_search_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;
    }



}
