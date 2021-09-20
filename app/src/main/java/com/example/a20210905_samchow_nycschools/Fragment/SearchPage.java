package com.example.a20210905_samchow_nycschools.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a20210905_samchow_nycschools.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchPage extends Fragment {


    private FragmentManager fragmentManager;

    private EditText et_search;
    private Button btn_search;
    private Spinner sp_search;

    final String[] searchSelectionList = {"dbn","school_name","phone_number","zip","city"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.search_page, container, false);

        fragmentManager = getFragmentManager();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Search");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_search = view.findViewById(R.id.et_search);
        sp_search = view.findViewById(R.id.sp_search);
        btn_search = view.findViewById(R.id.btn_search);

        ArrayAdapter<String> searchList = new ArrayAdapter<String>( this.getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                searchSelectionList);
        sp_search.setAdapter(searchList);
        sp_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                et_search.setHint("Search " + searchSelectionList[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> parameters = new HashMap<>();
                if (!et_search.getText().toString().isEmpty()){
                    parameters.put(sp_search.getSelectedItem().toString(),et_search.getText().toString());
                }

                ResultPage resultPage = new ResultPage(parameters);

                fragmentManager.beginTransaction()
                        .replace(R.id.fm_mainpage,resultPage,"searchPage")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
