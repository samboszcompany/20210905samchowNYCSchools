package com.example.a20210905_samchow_nycschools.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20210905_samchow_nycschools.Class.School;
import com.example.a20210905_samchow_nycschools.GlobleValue;
import com.example.a20210905_samchow_nycschools.R;

import java.util.List;

public class SchoolDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LAYOUT_ONE = 0;

    private  static final String TAG = "SchoolDetailAdapter";
    List<Integer> adapterList;
    School schoolData;
    Context mContext;

    public SchoolDetailAdapter(List<Integer> adapterList, School schoolData,Context mContext){
        this.adapterList = adapterList;
        this.schoolData = schoolData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        Log.d(TAG, "viewType : " + viewType);

        switch (viewType) {
            case GlobleValue.SCHOOL_INTRO:
                Log.d(TAG, "GlobleValue.SCHOOL_INTRO");
                view = layoutInflater.inflate(R.layout.adapter_school_detail_intro, parent,false);
                return new SchoolDetailIntroViewHolder(view);
                
            case GlobleValue.SCHOOL_DETAIL:
                Log.d(TAG, "GlobleValue.SCHOOL_DETAIL");
                view = layoutInflater.inflate(R.layout.adapter_school_detail_contact, parent,false);
                return new SchoolDetailContactViewHolder(view);

            case GlobleValue.SCHOOL_TRANSPORTATION:
                view = layoutInflater.inflate(R.layout.adapter_school_detail_trans, parent,false);
                return new SchoolDetailTransViewHolder(view);
                
            default:
                Log.d(TAG, "return null");
                return null;

        }
        
    }

    @Override
    public int getItemViewType(int position) {
        return adapterList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (adapterList.get(position)){
            case GlobleValue.SCHOOL_INTRO:
                SchoolDetailIntroViewHolder schoolDetailIntroViewHolder = (SchoolDetailIntroViewHolder) holder;
                schoolDetailIntroViewHolder.tv_school_name.setText(schoolData.getSchool_name());
                schoolDetailIntroViewHolder.tv_dbn.setText(schoolData.getDbn());
                schoolDetailIntroViewHolder.tv_overview.setText(schoolData.getOverview_paragraph());
                break;


            case GlobleValue.SCHOOL_DETAIL:
                SchoolDetailContactViewHolder schoolDetailContactViewHolder= (SchoolDetailContactViewHolder) holder;
                String address = schoolData.getPrimary_address_line_1()
                        + "," + schoolData.getCity()
                        + " " + schoolData.getState_code()
                        + " " + schoolData.getZip();
                schoolDetailContactViewHolder.tv_address.setText(address);
                Linkify.addLinks(schoolDetailContactViewHolder.tv_address,Linkify.MAP_ADDRESSES);
                schoolDetailContactViewHolder.tv_phone_num.setText(schoolData.getPhone_number());
                Linkify.addLinks(schoolDetailContactViewHolder.tv_phone_num,Linkify.PHONE_NUMBERS);
                schoolDetailContactViewHolder.tv_fax.setText(schoolData.getFax_number());
                Linkify.addLinks(schoolDetailContactViewHolder.tv_fax,Linkify.PHONE_NUMBERS);
                schoolDetailContactViewHolder.tv_web.setText(schoolData.getWebsite());
                Linkify.addLinks(schoolDetailContactViewHolder.tv_web,Linkify.WEB_URLS);
                break;

            case GlobleValue.SCHOOL_TRANSPORTATION:
                SchoolDetailTransViewHolder schoolDetailTransViewHolder = (SchoolDetailTransViewHolder) holder;
                schoolDetailTransViewHolder.tv_bus.setText(schoolData.getBus());
                schoolDetailTransViewHolder.tv_subway.setText(schoolData.getSubway());

                break;
        }

    }

    public void showMap(Double lat, Double lng) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:" + lat + "," + lng ));
        Intent chooser = Intent.createChooser(intent,"Lauch Maps");
        mContext.startActivity(chooser);
    }


    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    class SchoolDetailIntroViewHolder extends RecyclerView.ViewHolder {

        TextView tv_school_name, tv_dbn, tv_overview;

        public SchoolDetailIntroViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_school_name = itemView.findViewById(R.id.tv_school_name);
            tv_dbn = itemView.findViewById(R.id.tv_dbn);
            tv_overview = itemView.findViewById(R.id.tv_overview);

        }
    }

    class SchoolDetailContactViewHolder extends RecyclerView.ViewHolder {

        TextView tv_address, tv_fax, tv_phone_num, tv_web;

        public SchoolDetailContactViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_address = itemView.findViewById(R.id.tv_address);
            tv_fax = itemView.findViewById(R.id.tv_fax);
            tv_phone_num = itemView.findViewById(R.id.tv_phone_num);
            tv_web = itemView.findViewById(R.id.tv_web);
        }
    }

    class SchoolDetailTransViewHolder extends RecyclerView.ViewHolder {

        TextView tv_bus, tv_subway;

        public SchoolDetailTransViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_bus = itemView.findViewById(R.id.tv_bus);
            tv_subway = itemView.findViewById(R.id.tv_subway);
        }
    }
}
