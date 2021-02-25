package com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class LeaderLevelBvAdapter extends RecyclerView.Adapter<LeaderLevelBvAdapter.ViewHolder> {
    private Context context;
    private List<LeaderLevelBv> leaderLevelBvList;
    private static final String TAG = "FirstPurchaseBVAdapter";

    public LeaderLevelBvAdapter(List<LeaderLevelBv> leaderLevelBvList, Context context) {
        this.leaderLevelBvList = leaderLevelBvList;
        this.context = context;
    }

        public LeaderLevelBvAdapter.ViewHolder onCreateViewHolder (ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_leader_level, viewGroup, false);
            return new LeaderLevelBvAdapter.ViewHolder(view);
        }


        public void onBindViewHolder (LeaderLevelBvAdapter.ViewHolder viewHolder, final int i){
            try {
                viewHolder.ll_no.setText(String.valueOf(leaderLevelBvList.get(i).getCount()));
                viewHolder.ll_date.setText(leaderLevelBvList.get(i).getDToDate());
                viewHolder.ll_fromuser.setText(leaderLevelBvList.get(i).getUsername());
                viewHolder.ll_pairbv.setText(leaderLevelBvList.get(i).getPairBv());
                viewHolder.ll_level.setText(leaderLevelBvList.get(i).getLevel());
                viewHolder.ll_percentage.setText(leaderLevelBvList.get(i).getPercentage());
                viewHolder.ll_levelbv.setText(leaderLevelBvList.get(i).getLevelBv());
            } catch (Exception e) {
                Log.e(TAG, "error" + e);
            }
        }
        @Override
        public int getItemCount () {

            return leaderLevelBvList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView ll_no, ll_date, ll_fromuser, ll_pairbv, ll_level,ll_percentage,ll_levelbv;

            public ViewHolder(View view) {
                super(view);

                ll_no = view.findViewById(R.id.ll_no);
                ll_date = view.findViewById(R.id.date_ll);
                ll_fromuser = view.findViewById(R.id.fromuser_ll);
                ll_pairbv = view.findViewById(R.id.pairbv_ll);
                ll_level = view.findViewById(R.id.level_ll);
                ll_percentage = view.findViewById(R.id.percentage_ll);
                ll_levelbv = view.findViewById(R.id.levelbv_ll);
            }
        }
    }
