package com.example.a20210905_samchow_nycschools;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a20210905_samchow_nycschools.Class.School;

import java.util.List;


public class SchoolDetailPage extends AppCompatActivity {

    private TextView tv_school_name;
    private TextView tv_dbn;
    private TextView tv_display_all;
    private String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_detail_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        School schoolList = (School) getIntent().getSerializableExtra("schoolData");

        tv_school_name = findViewById(R.id.tv_school_name);
        tv_dbn = findViewById(R.id.tv_dbn);
        tv_display_all = findViewById(R.id.tv_display_all);

        tv_school_name.setText(schoolList.getSchool_name());
        tv_dbn.setText(schoolList.getDbn());

        //temp, if there are better UI, this part will change (a lot)
        description += schoolList.getOverview_paragraph() + "\n\n";

        description += "academic opportunities : " + "\n" ;

        //if "" should not show up too, i will create the other function for check isEmtry and is null
        if (schoolList.getAcademicopportunities1() != null){
            description += schoolList.getAcademicopportunities1() + "\n";
        }
        if (schoolList.getAcademicopportunities2() != null){
            description += schoolList.getAcademicopportunities2() + "\n";
        }
        if (schoolList.getAcademicopportunities3() != null){
            description += schoolList.getAcademicopportunities3() + "\n";
        }
        if (schoolList.getAcademicopportunities4() != null){
            description += schoolList.getAcademicopportunities4() + "\n";
        }
        if (schoolList.getAcademicopportunities5() != null){
            description += schoolList.getAcademicopportunities5() + "\n";
        }

        description += "\n\n";

        if (schoolList.getLanguage_classes() != null){
            description += "Language classes : " + schoolList.getLanguage_classes() + "\n\n";
        }


        description += "Contact Us : " + "\n";
        description += schoolList.getLocation() + "," + schoolList.getBuilding_code() + "\n\n"; //this can add link to google map with lag, len
        if (schoolList.getPhone_number() != null){
            description += "Phone : " + schoolList.getPhone_number() + "\n"; //this can add link to phone application
        }
        if (schoolList.getSchool_email() != null){
            description += "Email : " + schoolList.getSchool_email() + "\n"; //this can add link to email application
        }
        if (schoolList.getWebsite() != null){
            description += "Web : " + schoolList.getWebsite() + "\n\n"; //this can add link to website
        }
        

        description += "transportation : "  + "\n";
        if (schoolList.getBus() != null){
            description += "bus : " + schoolList.getBus() + "\n";
        }
        if (schoolList.getSubway() != null){
            description += "subway : " + schoolList.getSubway() + "\n";
        }

        tv_display_all.setText(description);

    }


}
