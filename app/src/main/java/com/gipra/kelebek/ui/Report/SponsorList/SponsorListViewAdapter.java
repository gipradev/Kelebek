package com.gipra.kelebek.ui.Report.SponsorList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class SponsorListViewAdapter extends RecyclerView.Adapter<SponsorListViewAdapter.ViewHolder> {
    private Context context;
    private List<SponsorListView> sponsorListView;
    private static final String TAG = "SponsorListViewAdapter";

    public  SponsorListViewAdapter(List<SponsorListView> sponsorListView, Context context){
        this.sponsorListView=sponsorListView;
        this.context=context;
    }


    public SponsorListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sponsor_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

//    public SponsorListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view= LayoutInflater.from(activity).inflate(R.layout.activity_sponsor_listitem,null);
//        return new SponsorListViewAdapter.ViewHolder(view);
//    }

    public  void onBindViewHolder(ViewHolder viewHolder,final int i){
    try{
        viewHolder.sp_count.setText(String.valueOf(sponsorListView.get(i).getCount()));
        viewHolder.sp_name.setText(sponsorListView.get(i).getFname());
        viewHolder.sp_username.setText(sponsorListView.get(i).getChildusername());
        viewHolder.sp_jdate.setText(sponsorListView.get(i).getDateofjoin());
        viewHolder.sp_adate.setText(sponsorListView.get(i).getDateofjoin());
        viewHolder.sp_totalbv.setText(String.valueOf(sponsorListView.get(i).getNCouponPv()));


    }catch (Exception e){
        Log.e(TAG,"error"+e);
    }

    }  @Override
    public int getItemCount() {

        return sponsorListView.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView sp_count,sp_name,sp_username,sp_jdate,sp_adate,sp_totalbv;
        public ViewHolder(View view) {
            super(view);

            sp_count=view.findViewById(R.id.sp_count);
            sp_name=view.findViewById(R.id.sp_name);
            sp_username=view.findViewById(R.id.sp_username);
            sp_jdate=view.findViewById(R.id.sp_jdate);
            sp_adate=view.findViewById(R.id.sp_adate);
            sp_totalbv=view.findViewById(R.id.sp_totalbv);
        }
    }
}








