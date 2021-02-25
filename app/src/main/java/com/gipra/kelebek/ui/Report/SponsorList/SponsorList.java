package com.gipra.kelebek.ui.Report.SponsorList;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorList extends Fragment {

    Button fromdate_sp,todate_sp,search_sp;
    RecyclerView recycler_sp;
    private List<SponsorListView> sponsorListView;
    private SponsorListViewAdapter sponsorListViewAdapter;
    private static final String TAG = "SponsorList";
    TextView txt_sp;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView sp_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (container != null) {
//            container.removeAllViews();
//        }
        final View view = inflater.inflate(R.layout.fragment_sponsor_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Sponsor List");
        sp_search=view.findViewById(R.id.sp_search);
        sp_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Sp_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_sp=view.findViewById(R.id.txtnodata_sp);
        recycler_sp=view.findViewById(R.id.recycler_sp);
        fromdate_sp=view.findViewById(R.id.fromdate_sp);
        todate_sp=view.findViewById(R.id.todate_sp);

        search_sp=view.findViewById(R.id.search_sp);
        search_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
               splist();
            }
        });
        try {
            splist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_sp=view.findViewById(R.id.fromdate_sp);

        fromdate_sp.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_sp.setText(dateString);



        todate_sp.setText(dateString);

        fromdate_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_sp.setOnClickListener(new View.OnClickListener() {
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
                fromdate_sp.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_sp.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
    private void splist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_sp.getText().toString();
        String tdate=todate_sp.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSponsorSearch> usercall=api.SponsorListView(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseSponsorSearch>() {
            @Override
            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseSponsorSearch responseSponsorSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_sp.setLayoutManager(layoutManager);
                    recycler_sp.setHasFixedSize(true);
                    sponsorListView=responseSponsorSearch.getData();
                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
                    recycler_sp.setAdapter(sponsorListViewAdapter);
                    txt_sp.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_sp.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseSponsorSearch> call, Throwable t) {

            }
        });
    }
//    private void Sp_Search(){
//        String search=sp_search.getQuery().toString();
//        String fdate=fromdate_sp.getText().toString();
//        String tdate=todate_sp.getText().toString();
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
//                    recycler_sp.setLayoutManager(layoutManager);
//                    recycler_sp.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
//                    recycler_sp.setAdapter(sponsorListViewAdapter);
//                    txt_sp.setVisibility(View.GONE);
//                }
//                else{
//                    txt_sp.setText("No Data Found");
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



