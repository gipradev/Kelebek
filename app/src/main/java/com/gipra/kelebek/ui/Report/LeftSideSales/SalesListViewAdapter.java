package com.gipra.kelebek.ui.Report.LeftSideSales;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class SalesListViewAdapter extends RecyclerView.Adapter<SalesListViewAdapter.ViewHolder> {
    private Context context;
    private List<SalesListView> salesListViews;
    private static final String TAG = "SalesListViewAdapter";

    public SalesListViewAdapter(List<SalesListView> salesListViews, Context context){
        this.salesListViews=salesListViews;
        this.context=context;
    }


    public SalesListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sales_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

//    public SponsorListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view= LayoutInflater.from(activity).inflate(R.layout.activity_sponsor_listitem,null);
//        return new SponsorListViewAdapter.ViewHolder(view);
//    }

    public  void onBindViewHolder(ViewHolder viewHolder,final int i){

        try{
        viewHolder.sp_count.setText(String.valueOf(salesListViews.get(i).getCount()));
        viewHolder.sp_name.setText(salesListViews.get(i).getFname());
        viewHolder.sp_username.setText(salesListViews.get(i).getChildusername());
        viewHolder.sp_jdate.setText(salesListViews.get(i).getActvatedddate());
        viewHolder.sp_adate.setText(salesListViews.get(i).getActvatedddate());
        viewHolder.sp_totalbv.setText(String.valueOf(salesListViews.get(i).getNCouponPv()));


    }catch (Exception e){
        Log.e(TAG,"error"+e);
    }

    }  @Override
    public int getItemCount() {

        return salesListViews.size();
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








