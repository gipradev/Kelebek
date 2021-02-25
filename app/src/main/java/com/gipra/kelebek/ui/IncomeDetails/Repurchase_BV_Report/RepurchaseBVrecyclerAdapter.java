package com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class RepurchaseBVrecyclerAdapter extends RecyclerView.Adapter<RepurchaseBVrecyclerAdapter.ViewHolder> {
    private Context context;
    private List<RepurchaseBvReportlist> repurchaseBvReportlists;
    private static final String TAG = "FirstPurchaseBVAdapter";

    public RepurchaseBVrecyclerAdapter(List<RepurchaseBvReportlist> repurchaseBvReportlists, Context context) {
        this.repurchaseBvReportlists = repurchaseBvReportlists;
        this.context = context;
    }

        public RepurchaseBVrecyclerAdapter.ViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_repurchase_bv, viewGroup, false);
            return new RepurchaseBVrecyclerAdapter.ViewHolder(view);
        }


        public void onBindViewHolder (RepurchaseBVrecyclerAdapter.ViewHolder viewHolder, final int i){
            try {
                viewHolder.RepurchaseBv_no.setText(String.valueOf(repurchaseBvReportlists.get(i).getCount()));
                viewHolder.RepurchaseBv_date.setText(repurchaseBvReportlists.get(i).getDActivation());
                viewHolder.RepurchaseBv_orderid.setText(String.valueOf(repurchaseBvReportlists.get(i).getOrderId()));
                viewHolder.RepurchaseBv_amount.setText(repurchaseBvReportlists.get(i).getNAmount());
                viewHolder.RepurchaseBv_BV.setText(repurchaseBvReportlists.get(i).getNPV());


            } catch (Exception e) {
                Log.e(TAG, "error" + e);
            }
        }
        @Override
        public int getItemCount () {

            return repurchaseBvReportlists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView RepurchaseBv_no, RepurchaseBv_date, RepurchaseBv_orderid, RepurchaseBv_amount, RepurchaseBv_BV;

            public ViewHolder(View view) {
                super(view);

                RepurchaseBv_no = view.findViewById(R.id.RepurchaseBv_no);
                RepurchaseBv_date = view.findViewById(R.id.RepurchaseBv_date);
                RepurchaseBv_orderid = view.findViewById(R.id.RepurchaseBv_orderid);
                RepurchaseBv_amount = view.findViewById(R.id.RepurchaseBv_amount);
                RepurchaseBv_BV = view.findViewById(R.id.RepurchaseBv_bv);
            }
        }
    }
