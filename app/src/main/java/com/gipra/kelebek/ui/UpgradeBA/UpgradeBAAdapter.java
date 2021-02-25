package com.gipra.kelebek.ui.UpgradeBA;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpgradeBAAdapter extends  RecyclerView.Adapter<UpgradeBAAdapter.ViewHolder>{
    private Context context;
    private List<UpgradeBA> upgradeBA;
    private static final String TAG = "UpgradeBAAdapter";
    EditText view_upline;
    String up_id;

    public  UpgradeBAAdapter(List<UpgradeBA> upgradeBA, Context context){
        this.upgradeBA=upgradeBA;
        this.context=context;


    }
    public UpgradeBAAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upgrade_ba_list,viewGroup,false);
        return new ViewHolder(view);
    }



    public  void onBindViewHolder(ViewHolder viewHolder,final int i){
        try{
            viewHolder.upgrade_slno.setText(String.valueOf(upgradeBA.get(i).getCount()));
            viewHolder.upgrade_userid.setText(upgradeBA.get(i).getCUsername());
            viewHolder.upgrade_name.setText(upgradeBA.get(i).getCFNAME());
            viewHolder.upgrade_djoing.setText(upgradeBA.get(i).getDJOIN());
            viewHolder.upgrade_regtype.setText(upgradeBA.get(i).getCRegistrationType());


        }catch (Exception e){
            Log.e(TAG,"erro"+e);
        }

    }  @Override
    public int getItemCount() {

        return upgradeBA.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView upgrade_slno,upgrade_userid,upgrade_name,upgrade_djoing,upgrade_regtype,upgrade_ba;
        LinearLayout ll_upline;

        Button upline_submit,close_upline;
        RelativeLayout rl_upgrade;

        public ViewHolder(View view) {
            super(view);
            upgrade_slno=view.findViewById(R.id.upgrade_slno);
            upgrade_userid=view.findViewById(R.id.upgrade_userid);
            upgrade_name=view.findViewById(R.id.upgrade_name);
            upgrade_djoing=view.findViewById(R.id.upgrade_djoing);
            upgrade_regtype=view.findViewById(R.id.upgrade_regtype);
            ll_upline=view.findViewById(R.id.ll_upline);
            view_upline=view.findViewById(R.id.view_upline);
            upline_submit=view.findViewById(R.id.upline_submit);
            upline_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  Toast.makeText(context, "addessssssssss", Toast.LENGTH_SHORT).show();
                    sumbit_upline();
                }
            });
            close_upline=view.findViewById(R.id.close_upline);
            close_upline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_upline.setVisibility(View.GONE);
                }
            });
            upgrade_ba=view.findViewById(R.id.upgrade_ba);
            upgrade_ba.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_upline.setVisibility(View.VISIBLE);
                }
            });
            rl_upgrade=view.findViewById(R.id.rl_upgrade);
            rl_upgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_upline.setVisibility(View.GONE);
                }
            });
            viewupline();

        }
        private void viewupline(){
            SharedPreferences shpref;
            shpref=context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
            String u=shpref.getString("ID","");
            ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseViewUpline>call=api.ViewUpline(Integer.parseInt(u));
            call.enqueue(new Callback<ResponseViewUpline>() {
                @Override
                public void onResponse(Call<ResponseViewUpline> call, Response<ResponseViewUpline> response) {
                     up_id=response.body().getUplineid();
                    view_upline.setText(up_id);
                }

                @Override
                public void onFailure(Call<ResponseViewUpline> call, Throwable t) {

                }
            });

        }
    }
    private void sumbit_upline(){
        SharedPreferences shpref;
        shpref=context.getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String upline_id=view_upline.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUplinePost>call=api.UplinePost(Integer.parseInt(u),upline_id);
        call.enqueue(new Callback<ResponseUplinePost>() {
            @Override
            public void onResponse(Call<ResponseUplinePost> call, Response<ResponseUplinePost> response) {
                Log.d("onResponse", "" + response.body().getMessage());
              if (response.body().getStatus().equals("1")){
                  Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }
              else
              {
                  Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
              }

            }

            @Override
            public void onFailure(Call<ResponseUplinePost> call, Throwable t) {

            }
        });

    }

}


