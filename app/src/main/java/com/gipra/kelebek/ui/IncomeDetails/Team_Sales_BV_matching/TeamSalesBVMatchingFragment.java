package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_BV_matching;


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


public class TeamSalesBVMatchingFragment extends Fragment {

    Button fromdate_tsbvmatch,todate_tsbvmatch,search_tsbvmatch;
    TextView txt_tsbvmatch;
    RecyclerView recycler_tsbvmatch;
    private List<TSBvMatchingList> tsBvMatchingLists;
    private TeamSalesbvmatchRecyclerAdapter teamSalesbvmatchRecyclerAdapter;
    private static final String TAG = "TeamSalesBVMatching";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView tsbvmatch_search;

    public TeamSalesBVMatchingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_team_sales_bv_matching, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Team Sales BV Matching");
        tsbvmatch_search=view.findViewById(R.id.tsbvmatch_search);
        tsbvmatch_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Pin_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_tsbvmatch=view.findViewById(R.id.txtnodata_tsbvmatch);
        recycler_tsbvmatch=view.findViewById(R.id.recycler_tsbvmatch);
        fromdate_tsbvmatch=view.findViewById(R.id.fromdate_tsbvmatch);
        todate_tsbvmatch=view.findViewById(R.id.todate_tsbvmatch);

        search_tsbvmatch=view.findViewById(R.id.btnsearch_tsbvmatch);
        search_tsbvmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                pinlist();
            }
        });
        try {
            pinlist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_tsbvmatch=view.findViewById(R.id.fromdate_tsbvmatch);

        fromdate_tsbvmatch.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_tsbvmatch.setText(dateString);



        todate_tsbvmatch.setText(dateString);

        fromdate_tsbvmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_tsbvmatch.setOnClickListener(new View.OnClickListener() {
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
                fromdate_tsbvmatch.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_tsbvmatch.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void pinlist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_tsbvmatch.getText().toString();
        String tdate=todate_tsbvmatch.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseTeamSalesBvMatching> usercall=api.TeamSalesBv(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseTeamSalesBvMatching>() {
            @Override
            public void onResponse(Call<ResponseTeamSalesBvMatching> call, Response<ResponseTeamSalesBvMatching> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseTeamSalesBvMatching responseTeamSalesBvMatching=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_tsbvmatch.setLayoutManager(layoutManager);
                    recycler_tsbvmatch.setHasFixedSize(true);
                    tsBvMatchingLists=responseTeamSalesBvMatching.getData();
                    teamSalesbvmatchRecyclerAdapter=new TeamSalesbvmatchRecyclerAdapter(tsBvMatchingLists,getActivity());
                    recycler_tsbvmatch.setAdapter(teamSalesbvmatchRecyclerAdapter);
                    txt_tsbvmatch.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_tsbvmatch.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseTeamSalesBvMatching> call, Throwable t) {

            }
        });
    }
//    private void Pin_Search(){
//        String search=tsbvmatch_search.getQuery().toString();
//        String fdate=fromdate_tsbvmatch.getText().toString();
//        String tdate=todate_tsbvmatch.getText().toString();
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
//                    recycler_tsbvmatch.setLayoutManager(layoutManager);
//                    recycler_tsbvmatch.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    teamSalesbvmatchRecyclerAdapter=new TeamSalesbvmatchRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_tsbvmatch.setAdapter(teamSalesbvmatchRecyclerAdapter);
//                    txt_tsbvmatch.setVisibility(View.GONE);
//                }
//                else{
//                    txt_tsbvmatch.setText("No Data Found");
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
