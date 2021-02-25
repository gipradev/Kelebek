package com.gipra.kelebek.ui.Report.RightSideSales;


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
import com.gipra.kelebek.ui.Report.LeftSideSales.ResponseSales;
import com.gipra.kelebek.ui.Report.LeftSideSales.SalesListView;
import com.gipra.kelebek.ui.Report.LeftSideSales.SalesListViewAdapter;
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
public class RightSales extends Fragment {

    Button fromdate_rs,todate_rs,search_rs;
    RecyclerView recycler_rs;
    private List<SalesListView> salesListViews;
    private SalesListViewAdapter salesListViewAdapter;
    private static final String TAG = "RightSales";
    TextView txt_rs;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView rs_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container != null) {
//            container.removeAllViews();
//        }
        final View view = inflater.inflate(R.layout.fragment_right_sales, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Right Side Sales");
        rs_search=view.findViewById(R.id.rs_search);
        rs_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Rs_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_rs=view.findViewById(R.id.txtnodata_rs);
        recycler_rs=view.findViewById(R.id.recycler_rs);
        fromdate_rs=view.findViewById(R.id.fromdate_rs);
        todate_rs=view.findViewById(R.id.todate_rs);

        search_rs=view.findViewById(R.id.search_rs);
        search_rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                Rightsaleslist();
            }
        });
        try {
            Rightsaleslist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_rs=view.findViewById(R.id.fromdate_rs);

        fromdate_rs.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_rs.setText(dateString);



        todate_rs.setText(dateString);

        fromdate_rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_rs.setOnClickListener(new View.OnClickListener() {
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
                fromdate_rs.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_rs.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
    private void  Rightsaleslist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_rs.getText().toString();
        String tdate=todate_rs.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        String side="right";
        Call<ResponseSales> usercall=api.RightSales(Integer.parseInt(u),fdate,tdate,side);
        usercall.enqueue(new Callback<ResponseSales>() {
            @Override
            public void onResponse(Call<ResponseSales> call, Response<ResponseSales> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseSales responseSales=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_rs.setLayoutManager(layoutManager);
                    recycler_rs.setHasFixedSize(true);
                    salesListViews=responseSales.getData();
                    salesListViewAdapter=new SalesListViewAdapter(salesListViews,getActivity());
                    recycler_rs.setAdapter(salesListViewAdapter);
                    txt_rs.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_rs.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseSales> call, Throwable t) {

            }
        });
    }
//    private void Rs_Search(){
//        String search=rs_search.getQuery().toString();
//        String fdate=fromdate_rs.getText().toString();
//        String tdate=todate_rs.getText().toString();
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
//                    recycler_rs.setLayoutManager(layoutManager);
//                    recycler_rs.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
//                    recycler_rs.setAdapter(sponsorListViewAdapter);
//                    txt_rs.setVisibility(View.GONE);
//                }
//                else{
//                    txt_rs.setText("No Data Found");
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



