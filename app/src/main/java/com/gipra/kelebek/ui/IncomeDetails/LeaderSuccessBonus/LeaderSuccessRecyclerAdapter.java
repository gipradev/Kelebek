package com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class LeaderSuccessRecyclerAdapter extends RecyclerView.Adapter<LeaderSuccessRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<LSBlist> lsBlists;
    private static final String TAG = "LeaderSuccessRecycler";
    ViewGroup viewGroup;
    public LeaderSuccessRecyclerAdapter(List<LSBlist> lsBlists, Context context){
        this.lsBlists=lsBlists;
        this.context=context;
    }

    public LeaderSuccessRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_leader_sucess,viewGroup,false);
        return new LeaderSuccessRecyclerAdapter.ViewHolder(view);
    }


    public  void onBindViewHolder(LeaderSuccessRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.lsb_no.setText(String.valueOf(lsBlists.get(i).getCount()));
            viewHolder.lsb_date.setText(lsBlists.get(i).getToDate());
            viewHolder.lsb_grossamt.setText(lsBlists.get(i).getNGrossAmt());
            viewHolder.lsb_deduction.setText(lsBlists.get(i).getNDEDUCTIONAMT());
            viewHolder.lsb_netamt.setText(lsBlists.get(i).getNNETPAYABLE());


        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }
    }  @Override
    public int getItemCount() {

        return lsBlists.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView lsb_no,lsb_date,lsb_grossamt,lsb_deduction,lsb_netamt;
        public ViewHolder(View view) {
            super(view);
            viewGroup = view.findViewById(android.R.id.content);
            lsb_no=view.findViewById(R.id.lsb_no);
            lsb_date=view.findViewById(R.id.date_lsb);
            lsb_grossamt=view.findViewById(R.id.grossamount_lsb);
            lsb_deduction=view.findViewById(R.id.deduction_lsb);
            lsb_netamt=view.findViewById(R.id.netamount_lsb);
        }
    }
}
