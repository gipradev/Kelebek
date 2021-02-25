//package com.gipra.shero.ui.Genealogy;
//
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//
//import com.gipra.shero.ApiClient;
//import com.gipra.shero.ApiInterface;
//import com.gipra.shero.R;
//import com.google.gson.Gson;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class NewEntryPositiondemo extends Fragment {
//
//
//
//    public NewEntryPositiondemo() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_new_entry_position, container, false);
//           entry_level=view.findViewById(R.id.entry_level);
//    entry_under=view.findViewById(R.id.entry_under);
//    entry_side=view.findViewById(R.id.entry_side);
//    up_username=view.findViewById(R.id.up_username);
//    entry_under_layout=view.findViewById(R.id.entry_under_layout);
//    up_username_layout=view.findViewById(R.id.up_username_layout);
//    new_entry_under=view.findViewById(R.id.new_entry_under);
//    new_entry_layout=view.findViewById(R.id.new_entry_layout);
//    new_entry_main=view.findViewById(R.id.new_entry_main);
//    new_entry_text=view.findViewById(R.id.new_entry_text);
//        new_entry_text.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            new_entry_layout.setVisibility(View.VISIBLE);
//        }
//    });
//        new_entry_main.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            new_entry_layout.setVisibility(View.INVISIBLE);
//        }
//    });
//    new_entry_view();
//        return view;
//}
//    private void new_entry_view(){
//        SharedPreferences shpref;
//        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseNewEntry>call=api.NewEntry(Integer.parseInt(u));
//        call.enqueue(new Callback<ResponseNewEntry>() {
//            @Override
//            public void onResponse(Call<ResponseNewEntry> call, Response<ResponseNewEntry> response) {
//                Log.i("onResponse", new Gson().toJson(response.body()));
//                if (response.body().getStatus().equals("1")){
//                    String e_l= String.valueOf(response.body().getLevel());
//                    entry_level.setText(e_l);
//                    String e_u=response.body().getFirstName();
//                    entry_under.setText(e_u);
//                    String e_s=response.body().getGoingSide();
//                    entry_side.setText(e_s);
//                    String up_s=response.body().getUpUsername();
//                    up_username.setText(up_s);
//                    up_username_layout.setText(up_s);
//                    entry_under_layout.setText(e_u);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseNewEntry> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//}
