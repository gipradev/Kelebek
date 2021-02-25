package com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class FirstPurchaseBVAdapter extends RecyclerView.Adapter<FirstPurchaseBVAdapter.ViewHolder> {
    private Context context;
    private List<FirstPurchaselist> firstPurchaselists;
    private static final String TAG = "FirstPurchaseBVAdapter";

    public FirstPurchaseBVAdapter(List<FirstPurchaselist> firstPurchaselists, Context context) {
        this.firstPurchaselists = firstPurchaselists;
        this.context = context;
    }

        public FirstPurchaseBVAdapter.ViewHolder onCreateViewHolder (ViewGroup viewGroup,int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_purchase_bvlist_view, viewGroup, false);
            return new FirstPurchaseBVAdapter.ViewHolder(view);
        }


        public void onBindViewHolder (FirstPurchaseBVAdapter.ViewHolder viewHolder,final int i){
            try {
                viewHolder.firstpurchase_no.setText(String.valueOf(firstPurchaselists.get(i).getCount()));
                viewHolder.firstpurchase_date.setText(firstPurchaselists.get(i).getDActivation());
                viewHolder.firstpurchase_orderid.setText(String.valueOf(firstPurchaselists.get(i).getOrderId()));
                viewHolder.firstpurchase_amount.setText(firstPurchaselists.get(i).getNAmount());
                viewHolder.firstpurchase_BV.setText(firstPurchaselists.get(i).getNBV());


            } catch (Exception e) {
                Log.e(TAG, "error" + e);
            }
        }
        @Override
        public int getItemCount () {

            return firstPurchaselists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView firstpurchase_no, firstpurchase_date, firstpurchase_orderid, firstpurchase_amount, firstpurchase_BV;

            public ViewHolder(View view) {
                super(view);

                firstpurchase_no = view.findViewById(R.id.firstpurchase_no);
                firstpurchase_date = view.findViewById(R.id.firstpurchase_date);
                firstpurchase_orderid = view.findViewById(R.id.firstpurchase_orderid);
                firstpurchase_amount = view.findViewById(R.id.firstpurchase_amount);
                firstpurchase_BV = view.findViewById(R.id.firstpurchase_bv);
            }
        }
    }
