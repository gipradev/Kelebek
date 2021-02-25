package com.gipra.kelebek.ui.Wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class IncomeWalletAdapter extends RecyclerView.Adapter<IncomeWalletAdapter.ViewHolder> {
    private Context context;
    private List<IncomeWalletList> incomeWalletList;
    private static final String TAG = "IncomeWalletAdapter";

    public  IncomeWalletAdapter(List<IncomeWalletList> incomeWalletList, Context context){
        this.incomeWalletList=incomeWalletList;
        this.context=context;


    }
    public IncomeWalletAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.income_wallet_listitem,viewGroup,false);
        return new ViewHolder(view);
    }



    public  void onBindViewHolder(ViewHolder viewHolder,final int i){
//        try{
//            viewHolder.cp_count.setText(String.valueOf(cpList.get(i).getCount()));
//            viewHolder.cp_name.setText(cpList.get(i).getCFname());
//            viewHolder.cp_username.setText(cpList.get(i).getCUsername());
//            viewHolder.cp_jdate.setText(cpList.get(i).getDJOINTIME());
//            viewHolder.cp_adate.setText(cpList.get(i).getDActivated());
//            viewHolder.cp_bv.setText(String.valueOf(cpList.get(i).getBV()));
//            viewHolder.cp_pv.setText(String.valueOf(cpList.get(i).getPV()));
//
//
//        }catch (Exception e){
//            Log.e(TAG,"erro"+e);
//        }

    }  @Override
    public int getItemCount() {

        return incomeWalletList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView income_wallet_no,income_wallet_trans_no,income_wallet_date ,income_wallet_trans_type,income_wallet_from,income_wallet_to,income_wallet_amount,income_wallet_balance;
        public ViewHolder(View view) {
            super(view);
            income_wallet_no=view.findViewById(R.id.income_wallet_no);
            income_wallet_trans_no=view.findViewById(R.id.income_wallet_trans_no);
            income_wallet_date=view.findViewById(R.id.income_wallet_date);
            income_wallet_trans_type=view.findViewById(R.id.income_wallet_trans_type);
            income_wallet_from=view.findViewById(R.id.income_wallet_from);
            income_wallet_to=view.findViewById(R.id.income_wallet_to);
            income_wallet_amount=view.findViewById(R.id.income_wallet_amount);
            income_wallet_balance=view.findViewById(R.id.income_wallet_balance);
        }
    }
}
