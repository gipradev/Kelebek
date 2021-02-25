package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class RepurchaseDetailsRecyclerAdapter extends RecyclerView.Adapter<RepurchaseDetailsRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<RepurchaseIncomeDetailList> repurchaseIncomeDetailLists;
    private static final String TAG = "RepurchaseDetails";

    public RepurchaseDetailsRecyclerAdapter(List<RepurchaseIncomeDetailList> repurchaseIncomeDetailLists, Context context){
        this.repurchaseIncomeDetailLists=repurchaseIncomeDetailLists;
        this.context=context;
    }

    public RepurchaseDetailsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_repurchase_details,viewGroup,false);
        return new RepurchaseDetailsRecyclerAdapter.ViewHolder(view);
    }


    public  void onBindViewHolder(RepurchaseDetailsRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.redetails_no.setText(String.valueOf(repurchaseIncomeDetailLists.get(i).getCount()));
            viewHolder.redetails_date.setText(repurchaseIncomeDetailLists.get(i).getTodate());
            viewHolder.redetails_dsb.setText(repurchaseIncomeDetailLists.get(i).getDsb());
            viewHolder.redetails_tsb.setText(repurchaseIncomeDetailLists.get(i).getTsb());
            viewHolder.redetails_lsb.setText(repurchaseIncomeDetailLists.get(i).getNLsb());
            viewHolder.redetails_gross.setText(repurchaseIncomeDetailLists.get(i).getGrossAmount());
            viewHolder.redetails_deduction.setText(repurchaseIncomeDetailLists.get(i).getDeductionAmount());
            viewHolder.redetails_netamount.setText(repurchaseIncomeDetailLists.get(i).getNetAmount());

        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }
    }  @Override
    public int getItemCount() {

        return repurchaseIncomeDetailLists.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView redetails_no,redetails_date,redetails_dsb,redetails_tsb,redetails_lsb,redetails_gross,redetails_deduction,redetails_netamount;
        public ViewHolder(View view) {
            super(view);

            redetails_no=view.findViewById(R.id.redetail_no);
            redetails_date=view.findViewById(R.id.date_redetails);
            redetails_dsb=view.findViewById(R.id.dsb_redetails);
            redetails_tsb=view.findViewById(R.id.tsb_redetails);
            redetails_lsb=view.findViewById(R.id.lsb_redetails);
            redetails_gross=view.findViewById(R.id.grossamount_redetails);
            redetails_deduction=view.findViewById(R.id.deduction_redetails);
            redetails_netamount=view.findViewById(R.id.netamount_redetails);

        }
    }
}
