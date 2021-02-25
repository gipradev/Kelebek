package com.gipra.kelebek.ui.PinManagement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

class PinProductRecyclerAdapter extends RecyclerView.Adapter<PinProductRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<PinProductList> pinProductList;
    private static final String TAG = "PINProductRecycler";
    ViewGroup viewGroup;

    public PinProductRecyclerAdapter(List<PinProductList> pinProductList, Context context){
        this.pinProductList=pinProductList;
        this.context=context;


    }



    public PinProductRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pinpdtrecycler,viewGroup,false);
        return new PinProductRecyclerAdapter.ViewHolder(view);
    }

    public  void onBindViewHolder(PinProductRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.getpdt_slno.setText(String.valueOf(pinProductList.get(i).getCount()));
            viewHolder.getpdt_product.setText(pinProductList.get(i).getCProduct());
            viewHolder.getpdt_quantity.setText(pinProductList.get(i).getNQuantity());
            viewHolder.getpdt_bv.setText(pinProductList.get(i).getBv());
            viewHolder.getpdt_amount.setText(pinProductList.get(i).getNAmount());
            viewHolder.getpdt_total.setText(String.valueOf(pinProductList.get(i).getNSubTotal()));



        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }

    }  @Override
    public int getItemCount() {

        return pinProductList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView getpdt_slno,getpdt_product,getpdt_quantity,getpdt_bv,getpdt_amount,getpdt_total;

        public ViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);
            getpdt_slno=view.findViewById(R.id.pinpdt_count);
            getpdt_product=view.findViewById(R.id.pinpdt_product);
            getpdt_quantity=view.findViewById(R.id.pinpdt_quantity);
            getpdt_bv=view.findViewById(R.id.pinpdt_bv);
            getpdt_amount=view.findViewById(R.id.pinpdt_amt);
            getpdt_total=view.findViewById(R.id.pinpdt_total);

        }
    }
}