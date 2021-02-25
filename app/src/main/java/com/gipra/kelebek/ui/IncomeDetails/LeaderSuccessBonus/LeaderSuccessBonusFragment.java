package com.gipra.kelebek.ui.IncomeDetails.LeaderSuccessBonus;


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
import android.widget.Toast;

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

public class LeaderSuccessBonusFragment extends Fragment {
    Button fromdate_lsb,todate_lsb,search_pin;
    TextView txt_lsb;
    RecyclerView recycler_lsb;
    private List<LSBlist> lsBlists;
    private LeaderSuccessRecyclerAdapter leaderSuccessRecyclerAdapter;
    private static final String TAG = "LeaderSuccessBonus";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView lsb_search;


    public LeaderSuccessBonusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_leader_success_bonus, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Leader Success Bonus");
        lsb_search=view.findViewById(R.id.lsb_search);
        lsb_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // LSB_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_lsb=view.findViewById(R.id.txtnodata_lsb);
        recycler_lsb=view.findViewById(R.id.recycler_lsb);
        fromdate_lsb=view.findViewById(R.id.fromdate_lsb);
        todate_lsb=view.findViewById(R.id.todate_lsb);

        search_pin=view.findViewById(R.id.btnsearch_lsb);
        search_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                lsblist();
            }
        });
        //lsbFirstloadlist();
        try {
            lsblist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_lsb=view.findViewById(R.id.fromdate_lsb);

        fromdate_lsb.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_lsb.setText(dateString);



        todate_lsb.setText(dateString);

        fromdate_lsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_lsb.setOnClickListener(new View.OnClickListener() {
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
                fromdate_lsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_lsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void lsblist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_lsb.getText().toString();
        String tdate=todate_lsb.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Toast.makeText(getContext(), "Loading data....", Toast.LENGTH_SHORT).show();
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLsbsSearch> usercall=api.LsbSearch(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseLsbsSearch>() {
            @Override
            public void onResponse(Call<ResponseLsbsSearch> call, Response<ResponseLsbsSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseLsbsSearch responseLsbsSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_lsb.setLayoutManager(layoutManager);
                    recycler_lsb.setHasFixedSize(true);
                    lsBlists=responseLsbsSearch.getData();
                    leaderSuccessRecyclerAdapter=new LeaderSuccessRecyclerAdapter(lsBlists,getActivity());
                    recycler_lsb.setAdapter(leaderSuccessRecyclerAdapter);
                    txt_lsb.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_lsb.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseLsbsSearch> call, Throwable t) {

            }
        });
    }


    private void lsbFirstloadlist(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLsbsSearch> usercall=api.LsbFirstSearch(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseLsbsSearch>() {
            @Override
            public void onResponse(Call<ResponseLsbsSearch> call, Response<ResponseLsbsSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseLsbsSearch responseLsbsSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_lsb.setLayoutManager(layoutManager);
                    recycler_lsb.setHasFixedSize(true);
                    lsBlists=responseLsbsSearch.getData();
                    leaderSuccessRecyclerAdapter=new LeaderSuccessRecyclerAdapter(lsBlists,getActivity());
                    recycler_lsb.setAdapter(leaderSuccessRecyclerAdapter);
                    txt_lsb.setVisibility(View.GONE);
                }
                else{

                    txt_lsb.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseLsbsSearch> call, Throwable t) {

            }
        });
    }


//    private void LSB_Search(){
//        String search=lsb_search.getQuery().toString();
//        String fdate=fromdate_lsb.getText().toString();
//        String tdate=todate_lsb.getText().toString();
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
//                    recycler_lsb.setLayoutManager(layoutManager);
//                    recycler_lsb.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    leaderSuccessRecyclerAdapter=new LeaderSuccessRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_lsb.setAdapter(leaderSuccessRecyclerAdapter);
//                    txt_lsb.setVisibility(View.GONE);
//                }
//                else{
//                    txt_lsb.setText("No Data Found");
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
