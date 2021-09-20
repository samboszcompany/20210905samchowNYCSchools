package com.example.a20210905_samchow_nycschools.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a20210905_samchow_nycschools.ApiCallBack;
import com.example.a20210905_samchow_nycschools.ApiHandle;
import com.example.a20210905_samchow_nycschools.Class.School;
import com.example.a20210905_samchow_nycschools.R;
import com.example.a20210905_samchow_nycschools.Adapter.SchoolAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultPage extends Fragment {

    private FragmentManager fragmentManager;
    private ApiHandle apiHandle;
    private ListView lv_result;
    private TextView tv_errorMsg;
    private ArrayList<School> schoolArrayList = new ArrayList<>();
    private Map<String, String> parameters = new HashMap<>();

    public ResultPage (Map<String, String> parameters){
        this.parameters = parameters;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.result_page, container, false);

        fragmentManager = getFragmentManager();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        lv_result = view.findViewById(R.id.lv_result);
        tv_errorMsg = view.findViewById(R.id.tv_errorMsg);
        tv_errorMsg.setText("");

        SchoolAdapter schoolAdapter = new SchoolAdapter(this.getActivity().getApplicationContext());
        lv_result.setAdapter(schoolAdapter);

        apiHandle = new ApiHandle();

        apiHandle.getSchoolData(parameters, new ApiCallBack() {
            @Override
            public void success(ArrayList result) {
                ArrayList<School> posts = result;
                if(posts.isEmpty()){
                    tv_errorMsg.setText("No Result");
                }
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Total Result : " + posts.size());
                schoolAdapter.setData(posts);
            }

            @Override
            public void failure(Throwable t) {
                tv_errorMsg.setText(t.getMessage());
            }
        });

        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DetailPage detailPage = new DetailPage();
                School school = (School) adapterView.getItemAtPosition(i);
                detailPage.setData(school);

                fragmentManager.beginTransaction()
                        .replace(R.id.fm_mainpage,detailPage,"detailPage")
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }


}
