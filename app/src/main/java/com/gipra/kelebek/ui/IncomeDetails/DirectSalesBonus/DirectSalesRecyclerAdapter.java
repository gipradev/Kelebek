package com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;



class DirectSalesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    ProgressBar progressBar;
    private Context context;
    private List<DataSalesListView> dataSalesListViews;
    private static final String TAG = "DirectSalesRecycler";
    ViewGroup viewGroup;


    public DirectSalesRecyclerAdapter(List<DataSalesListView> dataSalesListViews, Context context){
        super();
        this.dataSalesListViews=dataSalesListViews;
        this.context=context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){



        if (i == VIEW_TYPE_ITEM) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_direct_sales,viewGroup,false);
            return new ItemViewHolder(view);
        } else {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading,viewGroup,false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public  void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i){


        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, i);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, i);
        }

    }  @Override
    public int getItemCount() {
        return dataSalesListViews == null ? 0 : dataSalesListViews.size();
        //return dataSalesListViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSalesListViews.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    public  class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView dsb_no,dsb_date,dsb,dsb_grossamt,dsb_deduction;
        public ItemViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);
            dsb_no=view.findViewById(R.id.dsb_no);
            dsb_date=view.findViewById(R.id.date_dsb);
            dsb=view.findViewById(R.id.dsb_dsb);
            dsb_grossamt=view.findViewById(R.id.grossamount_dsb);
            dsb_deduction=view.findViewById(R.id.deduction_dsb);
        }
    }


    private class LoadingViewHolder extends RecyclerView.ViewHolder {


        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int i) {
        //ProgressBar would be displayed
        progressBar.getProgress();
    }

    private void populateItemRows(ItemViewHolder viewHolder, int i) {
        try{

            viewHolder.dsb_no.setText(String.valueOf(dataSalesListViews.get(i).getCount()));
            viewHolder.dsb_date.setText(dataSalesListViews.get(i).getTodate());
            viewHolder.dsb.setText(dataSalesListViews.get(i).getDsb());
            viewHolder.dsb_grossamt.setText(dataSalesListViews.get(i).getNGrossAmt());
            viewHolder.dsb_deduction.setText(dataSalesListViews.get(i).getDeductionAmount());


        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }
    }






}




