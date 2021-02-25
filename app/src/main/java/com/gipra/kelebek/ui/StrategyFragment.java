package com.gipra.kelebek.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.gipra.kelebek.ResponseStrategy;
import com.gipra.kelebek.ResponseStrategyView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StrategyFragment extends Fragment {
    private static final String TAG = "StrategyFragment";
    TextView text_strategy;
    RadioButton radioleft,radioauto,radioright;
    ImageView image_strategy,left_strategy,auto_strategy,right_strategy;
    Button leftupdate,autoupdate,rightupdate;
    TextView txtleft,txtauto,txtright;


    public StrategyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_strategy,container,false);
        viewstrategy();
        txtleft=view.findViewById(R.id.txtleft);
        txtauto=view.findViewById(R.id.txtauto);
        txtright=view.findViewById(R.id.txtright);

        text_strategy=view.findViewById(R.id.text_strategy);
        image_strategy=view.findViewById(R.id.image_strategy);
        left_strategy=view.findViewById(R.id.left_strategy);
        auto_strategy=view.findViewById(R.id.auto_strategy);
        right_strategy=view.findViewById(R.id.right_strategy);

        radioleft=view.findViewById(R.id.radioleft);
        radioleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftupdate.setVisibility(View.VISIBLE);
                autoupdate.setVisibility(View.GONE);
                rightupdate.setVisibility(View.GONE);
//                text_strategy.setText("Left");
//                 int[] left={R.drawable.left};
//
//            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.left);
//            image_strategy.setImageBitmap(bitmap);



            }
        });
        radioauto=view.findViewById(R.id.radioauto);
        radioauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftupdate.setVisibility(View.GONE);
                autoupdate.setVisibility(View.VISIBLE);
                rightupdate.setVisibility(View.GONE);
//                text_strategy.setText("Auto");
//                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.auto);
//                image_strategy.setImageBitmap(bitmap);

            }
        });
        radioright=view.findViewById(R.id.radioright);
        radioright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftupdate.setVisibility(View.GONE);
                autoupdate.setVisibility(View.GONE);
                rightupdate.setVisibility(View.VISIBLE);
//                text_strategy.setText("Right");
//                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.right);
//                image_strategy.setImageBitmap(bitmap);

            }
        });
        leftupdate=view.findViewById(R.id.leftupdate);
        leftupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltupdate();
            }
        });
        autoupdate=view.findViewById(R.id.autoupdate);
        autoupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atupdate();
            }
        });
        rightupdate=view.findViewById(R.id.rightupdate);
        rightupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rtupdate();
            }
        });


        return  view;

    }
    private  void ltupdate(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String str=txtleft.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStrategy>usercall=api.Strategy(Integer.parseInt(u),str);
        usercall.enqueue(new Callback<ResponseStrategy>() {
            @Override
            public void onResponse(Call<ResponseStrategy> call, Response<ResponseStrategy> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){
                    String text=response.body().getUpdateStrategy();
                    text_strategy.setText(text);
                    Glide.with(getContext())
                            .load(R.drawable.left)
                            .into(image_strategy);
                }
            }

            @Override
            public void onFailure(Call<ResponseStrategy> call, Throwable t) {

            }
        });
    }
    private  void atupdate(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String str=txtauto.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStrategy>usercall=api.Strategy(Integer.parseInt(u),str);
        usercall.enqueue(new Callback<ResponseStrategy>() {
            @Override
            public void onResponse(Call<ResponseStrategy> call, Response<ResponseStrategy> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){
                    String text=response.body().getUpdateStrategy();
                    text_strategy.setText(text);
                    Glide.with(getContext())
                            .load(R.drawable.auto)
                            .into(image_strategy);
                }
            }

            @Override
            public void onFailure(Call<ResponseStrategy> call, Throwable t) {

            }
        });

    }
    private void rtupdate(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        String str=txtright.getText().toString();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStrategy>usercall=api.Strategy(Integer.parseInt(u),str);
        usercall.enqueue(new Callback<ResponseStrategy>() {
            @Override
            public void onResponse(Call<ResponseStrategy> call, Response<ResponseStrategy> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if(response.body().getStatus().equals("1")){
                    String text=response.body().getUpdateStrategy();
                    text_strategy.setText(text);
                    Glide.with(getContext())
                            .load(R.drawable.right)
                            .into(image_strategy);
                }
            }

            @Override
            public void onFailure(Call<ResponseStrategy> call, Throwable t) {

            }
        });

    }
    private void viewstrategy(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseStrategyView>usercall=api.StrategyView(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseStrategyView>() {
            @Override
            public void onResponse(Call<ResponseStrategyView> call, Response<ResponseStrategyView> response) {
                String str=response.body().getCStrategy();
                text_strategy.setText(str);
                switch (str) {
                    case "Left":
                        Glide.with(getContext())
                                .load(R.drawable.left)
                                .into(image_strategy);
                        radioleft.setChecked(true);
                        break;
                    case "Auto":
                        Glide.with(getContext())
                                .load(R.drawable.auto)
                                .into(image_strategy);
                        radioauto.setChecked(true);

                        break;
                    case "Right":
                        Glide.with(getContext())
                                .load(R.drawable.right)
                                .into(image_strategy);
                        radioright.setChecked(true);
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseStrategyView> call, Throwable t) {

            }
        });
    }

}
