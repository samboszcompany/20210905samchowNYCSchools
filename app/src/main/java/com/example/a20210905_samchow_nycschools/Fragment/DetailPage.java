package com.example.a20210905_samchow_nycschools.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210905_samchow_nycschools.Adapter.SchoolDetailAdapter;
import com.example.a20210905_samchow_nycschools.Class.School;
import com.example.a20210905_samchow_nycschools.GlobleValue;
import com.example.a20210905_samchow_nycschools.R;

import java.util.ArrayList;
import java.util.List;

public class DetailPage extends Fragment {

    private FragmentManager fragmentManager;
    private School schoolData = new School();
    private RecyclerView rv_detail;
    private SchoolDetailAdapter schoolDetailAdapter;
    private List<Integer> adapterList = new ArrayList<>();

    public void setData(School schoolData){
        this.schoolData = schoolData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.detail_page, container, false);

        fragmentManager = getFragmentManager();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("School Detail");

        rv_detail = view.findViewById(R.id.rv_detail);
        setupAdapterList();

        schoolDetailAdapter = new SchoolDetailAdapter(adapterList,schoolData,this.getActivity().getApplicationContext());
        rv_detail.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        rv_detail.setAdapter(schoolDetailAdapter);

        return view;
    }

    private void setupAdapterList() {

        adapterList.add(GlobleValue.SCHOOL_INTRO);

        if (!schoolData.getPhone_number().isEmpty() || !schoolData.getLocation().isEmpty()) {
            adapterList.add(GlobleValue.SCHOOL_DETAIL);
        }


        if (!schoolData.getBus().isEmpty() || !schoolData.getSubway().isEmpty()) {
            adapterList.add(GlobleValue.SCHOOL_TRANSPORTATION);
        }

    }


}
