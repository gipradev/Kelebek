package com.gipra.kelebek.ui.Report.My_Product;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;


import java.util.List;

class GetProductRecyclerAdapter  extends RecyclerView.Adapter<GetProductRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<GetProductListView> getProductListViews;
    private static final String TAG = "GetProductRecycler";
    ViewGroup viewGroup;
    public  GetProductRecyclerAdapter(List<GetProductListView> getProductListViews, Context context){
        this.getProductListViews=getProductListViews;
        this.context=context;


    }



    public GetProductRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_getpdtrecycler,viewGroup,false);
        return new GetProductRecyclerAdapter.ViewHolder(view);
    }

    public  void onBindViewHolder(GetProductRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.getpdt_slno.setText(String.valueOf(getProductListViews.get(i).getCount()));
            viewHolder.getpdt_product.setText(getProductListViews.get(i).getCProduct());
            viewHolder.getpdt_rate.setText(getProductListViews.get(i).getNAmount());
            viewHolder.getpdt_quantity.setText(getProductListViews.get(i).getNQuantity());
            viewHolder.getpdt_totalamount.setText(getProductListViews.get(i).getNSubtotal());
            viewHolder.getpdt_totalBV.setText(String.valueOf(getProductListViews.get(i).getNTotalBv()));



        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }

    }  @Override
    public int getItemCount() {

        return getProductListViews.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView getpdt_slno,getpdt_product,getpdt_rate,getpdt_quantity,getpdt_totalamount,getpdt_totalBV;

        public ViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);
            getpdt_slno=view.findViewById(R.id.getpdt_count);
            getpdt_product=view.findViewById(R.id.getpdt_product);
            getpdt_rate=view.findViewById(R.id.getpdt_rate);
            getpdt_quantity=view.findViewById(R.id.getpdt_quantity);
            getpdt_totalamount=view.findViewById(R.id.getpdt_totalamount);
            getpdt_totalBV=view.findViewById(R.id.getpdt_totalbv);

        }
    }
}