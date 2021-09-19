package com.example.a20210905_samchow_nycschools;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.example.a20210905_samchow_nycschools.Class.School;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class SchoolAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<School> mArrayList = new ArrayList<School>();
    private LayoutInflater mLayoutInflater;
    private ViewHolder holder;

    public SchoolAdapter(Context context){
        super();
        this.mContext = context;
        mLayoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<School> list){
        this.mArrayList.clear();
        this.mArrayList = list;
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        private TextView tv_dbn, tv_school_name, tv_address, tv_phone_num;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public School getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.adapter_school, null);

            holder.tv_dbn = (TextView) convertView.findViewById(R.id.tv_dbn);
            holder.tv_school_name = (TextView) convertView.findViewById(R.id.tv_school_name);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_phone_num = (TextView) convertView.findViewById(R.id.tv_phone_num);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //base on UI design, what kind of data need to show for
        School schoolData = mArrayList.get(position);
        holder.tv_dbn.setText(schoolData.getDbn());
        holder.tv_school_name.setText(schoolData.getSchool_name());
        String address = schoolData.getPrimary_address_line_1()
                + "," + schoolData.getCity()
                + " " + schoolData.getState_code()
                + " " + schoolData.getZip();
        holder.tv_address.setText(address);
        holder.tv_phone_num.setText(schoolData.getPhone_number());

        return convertView;
    }

}
