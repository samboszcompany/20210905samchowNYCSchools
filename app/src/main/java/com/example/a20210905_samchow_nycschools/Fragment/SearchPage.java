package com.example.a20210905_samchow_nycschools.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a20210905_samchow_nycschools.R;

public class SearchPage extends Fragment {

    final String[] searchSelectionList = {"dbn","school_name","phone_number","school_email","zip","city"};

    private FragmentManager fragmentManager;

    private TextView tv_title,tv_dbn,tv_school_name,tv_phone_num,tv_email,tv_zip,tv_city;
    private EditText et_dbn,et_school_name,et_email,et_phone_num,et_zip,et_city;
    private Button btn_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.search_page, container, false);

        fragmentManager = getFragmentManager();

        tv_title = view.findViewById(R.id.tv_title);
        tv_dbn = view.findViewById(R.id.tv_dbn);
        tv_school_name = view.findViewById(R.id.tv_school_name);
        tv_phone_num = view.findViewById(R.id.tv_phone_num);
        tv_email = view.findViewById(R.id.tv_email);
        tv_zip = view.findViewById(R.id.tv_zip);
        tv_city = view.findViewById(R.id.tv_city);
        et_dbn = view.findViewById(R.id.et_dbn);
        et_school_name = view.findViewById(R.id.et_school_name);
        et_email = view.findViewById(R.id.et_email);
        et_phone_num = view.findViewById(R.id.et_phone_num);
        et_zip = view.findViewById(R.id.et_zip);
        et_city = view.findViewById(R.id.et_city);
        btn_search = view.findViewById(R.id.btn_search);


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fm_mainpage,new ResultPage(),"searchPage")
                        .addToBackStack(null)
                        .commit();
            }
        });



        return view;
    }
}
