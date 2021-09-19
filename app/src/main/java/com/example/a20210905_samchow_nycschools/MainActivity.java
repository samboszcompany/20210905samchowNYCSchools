package com.example.a20210905_samchow_nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a20210905_samchow_nycschools.Class.School;
import com.example.a20210905_samchow_nycschools.Fragment.MainPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv_errorMsg;
    private ListView dataDisplayListView;
    private Spinner sp_search;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private GlobleValue globleValue = new GlobleValue();
    private SchoolAdapter schoolAdapter;
    private EditText et_search;
    private Button btn_search;
    private ArrayList<School> mData = new ArrayList<School>();
    private Context mContext = this;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MainPage mainPage;
    //that should be return by api data which data allow the user can search for, need more info about the api
    final String[] searchSelectionList = {"dbn","school_name","phone_number","school_email","zip","city"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        mainPage = new MainPage();
        fragmentTransaction.replace(R.id.fm_mainpage,mainPage,"mainpage");
        fragmentTransaction.commit();


//
//        //all hardcode text should put in string file
//        dataDisplayListView = findViewById(R.id.lv_result);
//        tv_errorMsg = findViewById(R.id.tv_errorMsg);
//        et_search= findViewById(R.id.et_search);
//        btn_search= findViewById(R.id.btn_search);
//        sp_search= findViewById(R.id.sp_search);
//
//        ArrayAdapter<String> searchList = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_spinner_dropdown_item,
//                searchSelectionList);
//        sp_search.setAdapter(searchList);
//        sp_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                et_search.setHint("Search " + searchSelectionList[i]);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        et_search.clearFocus();
//
//        //If have time, i prefer create new class to store this function
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(globleValue.getApiUrl())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//        //That should be use fragment, it will much better
//        Intent intent = new Intent(this, SchoolDetailPage.class);
//
//        schoolAdapter = new SchoolAdapter(mContext);
//        dataDisplayListView.setAdapter(schoolAdapter);
//
//        getSchoolData();
//
//        dataDisplayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //that should use fragment
//                School school = (School) adapterView.getItemAtPosition(i);
//                intent.putExtra("schoolData", school);
//                startActivity(intent);
//            }
//        });
//
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //call api
//                getSchoolData();
//                et_search.clearFocus();
//            }
//        });

    }

    private void getSchoolData(){
        //this is for display call api error or empty message
        tv_errorMsg.setText("");
        Map<String, String> parameters = new HashMap<>();
        //this will update to search any value if user want to
        //it need to selection pop up or other things can allow user select the key
        if (!et_search.getText().toString().isEmpty()){
            parameters.put(sp_search.getSelectedItem().toString(),et_search.getText().toString());
        }

        //calling api
        Call<ArrayList<School>> call = jsonPlaceHolderApi.getSchoolData(parameters);
        call.enqueue(new Callback<ArrayList<School>>() {
            @Override
            public void onResponse(Call<ArrayList<School>> call, Response<ArrayList<School>> response) {
                if(!response.isSuccessful()){
                    tv_errorMsg.setText("Code: " + response.code());
                    return;
                }

                ArrayList<School> posts = response.body();

                if(posts.isEmpty()){
                    tv_errorMsg.setText("No Result");
                }

                schoolAdapter.setData(posts);
            }

            @Override
            public void onFailure(Call<ArrayList<School>> call, Throwable t) {
                tv_errorMsg.setText(t.getMessage());
            }
        });
    }



}