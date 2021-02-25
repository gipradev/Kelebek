package com.gipra.kelebek.ui.Genealogy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEntryPosition extends Fragment {
    LinearLayout new_entry_layout,new_entry_main,new_entry_text;
    TextView entry_level,entry_under,entry_side,up_username,entry_under_layout,up_username_layout,new_entry_under;
    LinearLayout new_rightentry_text,new_right_entry_layout;
    TextView up_right_username,new_right_entry_under,up_right_username_layout,entry_right_under_layout;
    RelativeLayout rl_right_entry,rl_left_entry;

    public NewEntryPosition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_new_entry_position, container, false);
        rl_left_entry=view.findViewById(R.id.rl_left_entry);
        rl_right_entry=view.findViewById(R.id.rl_right_entry);
        new_rightentry_text=view.findViewById(R.id.new_rightentry_text);
        new_rightentry_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_right_entry_layout.setVisibility(View.VISIBLE);
            }
        });
        new_right_entry_layout=view.findViewById(R.id.new_right_entry_layout);
        up_right_username=view.findViewById(R.id.up_right_username);
        new_right_entry_under=view.findViewById(R.id.new_right_entry_under);
        up_right_username_layout=view.findViewById(R.id.up_right_username_layout);
        entry_right_under_layout=view.findViewById(R.id.entry_right_under_layout);



        entry_level=view.findViewById(R.id.entry_level);
        entry_under=view.findViewById(R.id.entry_under);
        entry_side=view.findViewById(R.id.entry_side);
        up_username=view.findViewById(R.id.up_username);
        entry_under_layout=view.findViewById(R.id.entry_under_layout);
        up_username_layout=view.findViewById(R.id.up_username_layout);
        new_entry_under=view.findViewById(R.id.new_entry_under);
        new_entry_layout=view.findViewById(R.id.new_entry_layout);
        new_entry_main=view.findViewById(R.id.new_entry_main);
        new_entry_text=view.findViewById(R.id.new_entry_text);
        new_entry_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_entry_layout.setVisibility(View.VISIBLE);
            }
        });
        new_entry_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_entry_layout.setVisibility(View.INVISIBLE);
                new_right_entry_layout.setVisibility(View.INVISIBLE);
            }
        });
        new_entry_view();
        return view;
    }
    private void new_entry_view(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNewEntry> call=api.NewEntry(Integer.parseInt(u));
        call.enqueue(new Callback<ResponseNewEntry>() {
            @Override
            public void onResponse(Call<ResponseNewEntry> call, Response<ResponseNewEntry> response) {
                Log.i("onResponse", new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    String e_l= String.valueOf(response.body().getLevel());
                    entry_level.setText(e_l);
                    String e_u=response.body().getFirstName();
                    entry_under.setText(e_u);
                    new_entry_under.setText(e_u);
                    new_right_entry_under.setText(e_u);
                    entry_under_layout.setText(e_u);
                    entry_right_under_layout.setText(e_u);
                    String e_s=response.body().getGoingSide();
                    if (e_s.equals("Right")){
                        rl_right_entry.setVisibility(View.VISIBLE);
                        rl_left_entry.setVisibility(View.GONE);
                    }else {
                        rl_left_entry.setVisibility(View.VISIBLE);
                        rl_right_entry.setVisibility(View.GONE);

                    }

                    entry_side.setText(e_s);
                    String up_s=response.body().getUpUsername();
                    up_username.setText(up_s);
                    up_username_layout.setText(up_s);
                    up_right_username.setText(up_s);
                    up_right_username_layout.setText(up_s);

                }
            }

            @Override
            public void onFailure(Call<ResponseNewEntry> call, Throwable t) {

            }
        });

    }

}
