package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class RepurchaseIncomeRecyclerAdapter extends RecyclerView.Adapter<RepurchaseIncomeRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<RepurchaseIncomelist> repurchaseIncomelists;
    private static final String TAG = "Repurchaseincomrecycler";

    public RepurchaseIncomeRecyclerAdapter(List<RepurchaseIncomelist> repurchaseIncomelists, Context context){
        this.repurchaseIncomelists=repurchaseIncomelists;
        this.context=context;
    }

    public RepurchaseIncomeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_repurchase_income,viewGroup,false);
        return new RepurchaseIncomeRecyclerAdapter.ViewHolder(view);
    }


    public  void onBindViewHolder(RepurchaseIncomeRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.reincome_no.setText(String.valueOf(repurchaseIncomelists.get(i).getCount()));
            viewHolder.reincome_date.setText(repurchaseIncomelists.get(i).getdDate());
            viewHolder.reincome_username.setText(repurchaseIncomelists.get(i).getCusername());
            viewHolder.reincome_bv.setText(repurchaseIncomelists.get(i).getNbv());
            viewHolder.reincome_level.setText(repurchaseIncomelists.get(i).getNlevel());
            viewHolder.reincome_percentage.setText(repurchaseIncomelists.get(i).getNprecentage());
            viewHolder.reincome_grossamt.setText(repurchaseIncomelists.get(i).getGrossamt());


        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }
    }  @Override
    public int getItemCount() {

        return repurchaseIncomelists.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView reincome_no,reincome_date,reincome_username,reincome_bv,reincome_level,reincome_percentage,reincome_grossamt;
        public ViewHolder(View view) {
            super(view);

            reincome_no=view.findViewById(R.id.reincome_no);
            reincome_date=view.findViewById(R.id.date_reincome);
            reincome_username=view.findViewById(R.id.username_reincome);
            reincome_bv=view.findViewById(R.id.bv_reincome);
            reincome_level=view.findViewById(R.id.level_reincome);
            reincome_percentage=view.findViewById(R.id.percentage_reincome);
            reincome_grossamt=view.findViewById(R.id.grossamount_reincome);
        }
    }
}
