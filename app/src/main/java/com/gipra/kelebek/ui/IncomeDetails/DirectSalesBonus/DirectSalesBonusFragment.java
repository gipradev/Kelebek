package com.gipra.kelebek.ui.IncomeDetails.DirectSalesBonus;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class DirectSalesBonusFragment extends Fragment {
    Button fromdate_dsb,todate_dsb,search_dsb;
    TextView txt_dsb;
    RecyclerView recycler_dsb;
    private List<DataSalesListView> dataSalesListViews;
    private DirectSalesRecyclerAdapter directSalesRecyclerAdapter;
    private static final String TAG = "DirectSalesBonus";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView dsb_search;
    boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        final View view = inflater.inflate(R.layout.fragment_direct_sales_bonus, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Direct Sales Bonus");
        recycler_dsb=(RecyclerView) view.findViewById(R.id.recycler_dsb);

        txt_dsb=view.findViewById(R.id.txtnodata_dsb);
        fromdate_dsb=view.findViewById(R.id.fromdate_dsb);
        todate_dsb=view.findViewById(R.id.todate_dsb);

        search_dsb=view.findViewById(R.id.btnsearch_dsb);
        dsb_search=view.findViewById(R.id.dsb_search);
        dsb_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //DSB_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        initScrollListener();
        dsbfirstload();

        search_dsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                initScrollListener();
                dsblist();
            }
        });
        try {
            dsbfirstload();
        }catch (Exception e){
            Log.i(TAG,"errorssssss"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_dsb=view.findViewById(R.id.fromdate_dsb);
        fromdate_dsb.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_dsb.setText(dateString);
        todate_dsb.setText(dateString);
        fromdate_dsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_dsb.setOnClickListener(new View.OnClickListener() {
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
                fromdate_dsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_dsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        return view;
    }

    private void dsbfirstload(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e(TAG,"id==="+u);
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDirectSalesLoad> usercall=api.DirectSalesLoad(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseDirectSalesLoad>() {
            @Override
            public void onResponse(Call<ResponseDirectSalesLoad> call, Response<ResponseDirectSalesLoad> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                Log.e(TAG,"status==="+response.body().getStatus());
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseDirectSalesLoad responseDirectSalesload=response.body();
                    recycler_dsb.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    recycler_dsb.setHasFixedSize(true);
                    dataSalesListViews=responseDirectSalesload.getData();
                    directSalesRecyclerAdapter=new DirectSalesRecyclerAdapter(dataSalesListViews,getContext());
                    recycler_dsb.setAdapter(directSalesRecyclerAdapter);
                    txt_dsb.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResponseDirectSalesLoad> call, Throwable t) {

            }
        });

    }
    private void dsblist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        String fdate=fromdate_dsb.getText().toString();
        String tdate=todate_dsb.getText().toString();
        Log.e(TAG,"from "+fdate+"to "+tdate);
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e(TAG,"id "+u);
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseDirectSales> usercall1=api.DirectSales(Integer.parseInt(u),fdate,tdate);
        usercall1.enqueue(new Callback<ResponseDirectSales>() {
            @Override
            public void onResponse(Call<ResponseDirectSales> call, Response<ResponseDirectSales> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                Log.e(TAG,"status "+response.body().getStatus());
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    txt_dsb.setVisibility(View.GONE);
                    ResponseDirectSales responseDirectSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_dsb.setLayoutManager(layoutManager);
                    recycler_dsb.setHasFixedSize(true);
                    dataSalesListViews=responseDirectSales.getData();
                    directSalesRecyclerAdapter=new DirectSalesRecyclerAdapter(dataSalesListViews,getActivity());
                    recycler_dsb.setAdapter(directSalesRecyclerAdapter);

                }
                else{
                    progressDialog.dismiss();
                    txt_dsb.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseDirectSales> call, Throwable t) {

            }
        });
    }

    private void initScrollListener() {
        recycler_dsb.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == dataSalesListViews.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });


    }

    private void loadMore() {

        dataSalesListViews.add(null);
        directSalesRecyclerAdapter.notifyItemInserted(dataSalesListViews.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dataSalesListViews.remove(dataSalesListViews.size() - 1);
               int scrollPosition = dataSalesListViews.size();
                //int scrollPosition=5;
                directSalesRecyclerAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 1;

               while (currentSize - 1 < nextLimit) {
                    //dsbfirstload();
                    //dataSalesListViews.add("Item " + currentSize);
                    currentSize++;

               }

                directSalesRecyclerAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);

    }

//    private void DSB_Search(){
//        String search=dsb_search.getQuery().toString();
//        String fdate=fromdate_dsb.getText().toString();
//        String tdate=todate_dsb.getText().toString();
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
//                    recycler_dsb.setLayoutManager(layoutManager);
//                    recycler_dsb.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    directSalesRecyclerAdapter=new DirectSalesRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_dsb.setAdapter(directSalesRecyclerAdapter);
//                    txt_dsb.setVisibility(View.GONE);
//                }
//                else{
//                    txt_dsb.setText("No Data Found");
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
