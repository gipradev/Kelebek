package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class TeamSalesbvmatchRecyclerAdapter extends RecyclerView.Adapter<TeamSalesbvmatchRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<TSBvMatchingList> tsBvMatchingLists;
    private static final String TAG = "TeamSalesbvmatch";

    public TeamSalesbvmatchRecyclerAdapter(List<TSBvMatchingList> tsBvMatchingLists, Context context){
        this.tsBvMatchingLists=tsBvMatchingLists;
        this.context=context;
    }

    public TeamSalesbvmatchRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_team_sales_bv_matching,viewGroup,false);
        return new TeamSalesbvmatchRecyclerAdapter.ViewHolder(view);
    }


    public  void onBindViewHolder(TeamSalesbvmatchRecyclerAdapter.ViewHolder viewHolder, final int i){
        try{
            viewHolder.tsbvmatch_no.setText(String.valueOf(tsBvMatchingLists.get(i).getCount()));
            viewHolder.tsbvmatch_date.setText(tsBvMatchingLists.get(i).getDToDate());
            viewHolder.tsbvmatch_leftsalebv.setText(tsBvMatchingLists.get(i).getNLeftPv());
            viewHolder.tsbvmatch_rightsalebv.setText(tsBvMatchingLists.get(i).getNRightPv());
            viewHolder.tsbvmatch_matchingbv.setText(tsBvMatchingLists.get(i).getMatchingBv());
            viewHolder.tsbvmatch_leftcarrybv.setText(tsBvMatchingLists.get(i).getNLeftPv());
            viewHolder.tsbvmatch_rightcarrybv.setText(tsBvMatchingLists.get(i).getNRightCarryPv());
            viewHolder.tsbvmatch_lsb.setText(tsBvMatchingLists.get(i).getNLsb());
            viewHolder.tsbvmatch_tsb.setText(tsBvMatchingLists.get(i).getNTsb());

        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }
    }  @Override
    public int getItemCount() {

        return tsBvMatchingLists.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tsbvmatch_no,tsbvmatch_date,tsbvmatch_leftsalebv,tsbvmatch_rightsalebv,
                tsbvmatch_matchingbv,tsbvmatch_leftcarrybv,tsbvmatch_rightcarrybv,tsbvmatch_lsb,tsbvmatch_tsb;
        public ViewHolder(View view) {
            super(view);

            tsbvmatch_no=view.findViewById(R.id.tsbvmatch_no);
            tsbvmatch_date=view.findViewById(R.id.date_tsbvmatch);
            tsbvmatch_leftsalebv=view.findViewById(R.id.leftsidebv_tsbvmatch);
            tsbvmatch_rightsalebv=view.findViewById(R.id.rightsidebv_tsbvmatch);
            tsbvmatch_matchingbv=view.findViewById(R.id.matchingbv_tsbvmatch);
            tsbvmatch_leftcarrybv=view.findViewById(R.id.leftsalecarry_tsbvmatch);
            tsbvmatch_rightcarrybv=view.findViewById(R.id.rightsalecarry_tsbvmatch);
            tsbvmatch_lsb=view.findViewById(R.id.lsb_tsbvmatch);
            tsbvmatch_tsb=view.findViewById(R.id.tsb_tsbvmatch);
            
        }
    }
}
