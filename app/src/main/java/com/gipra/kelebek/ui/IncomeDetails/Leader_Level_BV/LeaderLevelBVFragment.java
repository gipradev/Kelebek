package com.gipra.kelebek.ui.IncomeDetails.Leader_Level_BV;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderLevelBVFragment extends Fragment {

    Button fromdate_ll,todate_ll,search_ll;
    TextView txt_ll;
    RecyclerView recycler_ll;
    private List<LeaderLevelBv> leaderLevelBvList;
    private LeaderLevelBvAdapter leaderLevelBvAdapter;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView ll_search;
    private static final String TAG = "LeaderLevelBV";

    public LeaderLevelBVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_leader_level_bv, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Leader Level BV");
        ll_search=view.findViewById(R.id.ll_search);
        ll_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //LeaderLevel_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_ll=view.findViewById(R.id.txtnodata_ll);
        recycler_ll=view.findViewById(R.id.recycler_ll);
        fromdate_ll=view.findViewById(R.id.fromdate_ll);
        todate_ll=view.findViewById(R.id.todate_ll);

        search_ll=view.findViewById(R.id.btnsearch_ll);
        search_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                leaderlevellist();
            }
        });
        try {
            leaderlevellist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_ll=view.findViewById(R.id.fromdate_ll);

        fromdate_ll.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_ll.setText(dateString);



        todate_ll.setText(dateString);

        fromdate_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todp.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromdp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromdate_ll.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_ll.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void leaderlevellist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_ll.getText().toString();
        String tdate=todate_ll.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLeaderLevelBv> usercall=api.LeaderLevelBV(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseLeaderLevelBv>() {
            @Override
            public void onResponse(Call<ResponseLeaderLevelBv> call, Response<ResponseLeaderLevelBv> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseLeaderLevelBv responseLeaderLevelBv=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_ll.setLayoutManager(layoutManager);
                    recycler_ll.setHasFixedSize(true);
                    leaderLevelBvList=responseLeaderLevelBv.getData();
                    leaderLevelBvAdapter=new LeaderLevelBvAdapter(leaderLevelBvList,getActivity());
                    recycler_ll.setAdapter(leaderLevelBvAdapter);
                    txt_ll.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_ll.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseLeaderLevelBv> call, Throwable t) {

            }
        });
    }
//    private void LeaderLevel_Search(){
//        String search=ll_search.getQuery().toString();
//        String fdate=fromdate_ll.getText().toString();
//        String tdate=todate_ll.getText().toString();
//        SharedPreferences shpref;
//        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseSponsorSearch>call=api.SponsorSearch(Integer.parseInt(u),fdate,tdate,search);
//        call.enqueue(new Callback<ResponseSponsorSearch>() {
//            @Override
//            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
//                if(response.body().getStatus().equals("1")){
//
//                    ResponseSponsorSearch responseSponsorSearch=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_ll.setLayoutManager(layoutManager);
//                    recycler_ll.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    leaderLevelBvAdapter=new LeaderLevelBvAdapter(sponsorListView,getActivity());
//                    recycler_ll.setAdapter(leaderLevelBvAdapter);
//                    txt_ll.setVisibility(View.GONE);
//                }
//                else{
//                    txt_ll.setText("No Data Found");
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSponsorSearch> call, Throwable t) {
//
//            }
//        });
//    }
}
