package com.gipra.kelebek.ui.Report.LeftSideMembers;


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
import com.gipra.kelebek.ui.Report.SponsorList.ResponseSponsorSearch;
import com.gipra.kelebek.ui.Report.SponsorList.SponsorListView;
import com.gipra.kelebek.ui.Report.SponsorList.SponsorListViewAdapter;
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
public class LeftMember extends Fragment {

    Button fromdate_lm,todate_lm,search_lm;
    RecyclerView recycler_lm;
    private List<SponsorListView> sponsorListView;
    private SponsorListViewAdapter sponsorListViewAdapter;
    private static final String TAG = "LeftMember";
    TextView txt_lm;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView lm_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_left_member, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Left Side Members");
        lm_search=view.findViewById(R.id.lm_search);
        lm_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Lm_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_lm=view.findViewById(R.id.txtnodata_lm);
        recycler_lm=view.findViewById(R.id.recycler_lm);
        fromdate_lm=view.findViewById(R.id.fromdate_lm);
        todate_lm=view.findViewById(R.id.todate_lm);

        search_lm=view.findViewById(R.id.search_lm);
        search_lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
               Leftmemberlist();
            }
        });
        try {
            Leftmemberlist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_lm=view.findViewById(R.id.fromdate_lm);

        fromdate_lm.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_lm.setText(dateString);



        todate_lm.setText(dateString);

        fromdate_lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_lm.setOnClickListener(new View.OnClickListener() {
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
                fromdate_lm.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_lm.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
    private void Leftmemberlist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_lm.getText().toString();
        String tdate=todate_lm.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        String side="left";
        Call<ResponseSponsorSearch> usercall=api.LeftMembers(Integer.parseInt(u),fdate,tdate,side);
        usercall.enqueue(new Callback<ResponseSponsorSearch>() {
            @Override
            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseSponsorSearch responseSponsorSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_lm.setLayoutManager(layoutManager);
                    recycler_lm.setHasFixedSize(true);
                    sponsorListView=responseSponsorSearch.getData();
                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
                    recycler_lm.setAdapter(sponsorListViewAdapter);
                    txt_lm.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_lm.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseSponsorSearch> call, Throwable t) {

            }
        });
    }
//    private void Lm_Search(){
//        String search=lm_search.getQuery().toString();
//        String fdate=fromdate_lm.getText().toString();
//        String tdate=todate_lm.getText().toString();
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
//                    recycler_lm.setLayoutManager(layoutManager);
//                    recycler_lm.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getActivity());
//                    recycler_lm.setAdapter(sponsorListViewAdapter);
//                    txt_lm.setVisibility(View.GONE);
//                }
//                else{
//                    txt_lm.setText("No Data Found");
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



