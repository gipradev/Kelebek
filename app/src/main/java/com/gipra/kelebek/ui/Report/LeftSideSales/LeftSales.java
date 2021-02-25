package com.gipra.kelebek.ui.Report.LeftSideSales;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftSales extends Fragment {

    Button fromdate_ls,todate_ls,search_ls;
    RecyclerView recycler_ls;
    private List<SalesListView> salesListViews;
    private SalesListViewAdapter salesListViewAdapter;
    private static final String TAG = "LeftSales";
    TextView txt_ls;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView ls_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container != null) {
//            container.removeAllViews();
//        }
        final View view = inflater.inflate(R.layout.fragment_left_sales, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Left Side Sales");
        ls_search=view.findViewById(R.id.ls_search);
        ls_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //ls_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_ls=view.findViewById(R.id.txtnodata_ls);
        recycler_ls=view.findViewById(R.id.recycler_ls);
        fromdate_ls=view.findViewById(R.id.fromdate_ls);
        todate_ls=view.findViewById(R.id.todate_ls);

        search_ls=view.findViewById(R.id.search_ls);
        search_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                Leftsaleslist();
            }
        });
        try {
            Leftsaleslist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_ls=view.findViewById(R.id.fromdate_ls);

        fromdate_ls.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_ls.setText(dateString);



        todate_ls.setText(dateString);

        fromdate_ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_ls.setOnClickListener(new View.OnClickListener() {
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
                fromdate_ls.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_ls.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
    private void  Leftsaleslist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_ls.getText().toString();
        String tdate=todate_ls.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        String side="left";
        Call<ResponseSales> usercall=api.LeftSales(Integer.parseInt(u),fdate,tdate,side);
        usercall.enqueue(new Callback<ResponseSales>() {
            @Override
            public void onResponse(Call<ResponseSales> call, Response<ResponseSales> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseSales responseSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_ls.setLayoutManager(layoutManager);
                    recycler_ls.setHasFixedSize(true);
                    salesListViews=responseSales.getData();
                    salesListViewAdapter=new SalesListViewAdapter(salesListViews,getActivity());
                    recycler_ls.setAdapter(salesListViewAdapter);
                    txt_ls.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_ls.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseSales> call, Throwable t) {

            }
        });
    }
//    private void ls_Search(){
//        String search=ls_search.getQuery().toString();
//        String fdate=fromdate_ls.getText().toString();
//        String tdate=todate_ls.getText().toString();
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
//                    recycler_ls.setLayoutManager(layoutManager);
//                    recycler_ls.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
//                    recycler_ls.setAdapter(sponsorListViewAdapter);
//                    txt_ls.setVisibility(View.GONE);
//                }
//                else{
//                    txt_ls.setText("No Data Found");
//
//
//            }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSponsorSearch> call, Throwable t) {
//
//            }
//        });
//    }
    }



