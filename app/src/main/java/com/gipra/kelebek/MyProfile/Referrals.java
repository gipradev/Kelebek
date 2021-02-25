package com.gipra.kelebek.MyProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Referrals extends AppCompatActivity {
    Button referrals_fromdate,referrals_todate,referrals_btnsearch;
    SearchView referrals_search;
    RecyclerView recycler_referrals;
    TextView referrals_nodata;
    private List<SponsorListView> sponsorListView;
    private SponsorListViewAdapter sponsorListViewAdapter;
    private static final String TAG = "Referrals";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    ImageView back_referrals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);
        back_referrals=findViewById(R.id.back_referrals);
        back_referrals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyProfile.class));
                finish();
            }
        });
        referrals_fromdate=findViewById(R.id.referrals_fromdate);
        referrals_todate=findViewById(R.id.referrals_todate);
        referrals_btnsearch=findViewById(R.id.referrals_btnsearch);
        referrals_btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ref_list();
                }catch (Exception e){
                    Log.i(TAG,"error"+e);
                }

            }
        });
        ref_list();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        long date = System.currentTimeMillis();

        referrals_fromdate.requestFocus();
        String dateString = dateFormatter.format(date);
        referrals_fromdate.setText(dateString);



        referrals_todate.setText(dateString);

        referrals_fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        referrals_todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todp.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromdp = new DatePickerDialog(Referrals.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                referrals_fromdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(Referrals.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                referrals_todate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        referrals_nodata=findViewById(R.id.referrals_nodata);
        referrals_search=findViewById(R.id.referrals_search);
        referrals_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //ref_search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recycler_referrals=findViewById(R.id.recycler_referrals);
    }
    private void ref_list(){
        String fdate=referrals_fromdate.getText().toString();
        String tdate=referrals_todate.getText().toString();
        SharedPreferences shpref;
        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseSponsorSearch> usercall=api.SponsorListView(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseSponsorSearch>() {
            @Override
            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseSponsorSearch responseSponsorListView=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_referrals.setLayoutManager(layoutManager);
                    recycler_referrals.setHasFixedSize(true);
                    sponsorListView=responseSponsorListView.getData();
                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getApplicationContext());
                    recycler_referrals.setAdapter(sponsorListViewAdapter);
                    referrals_nodata.setVisibility(View.GONE);
                }
                else{
                    referrals_nodata.setText("No Data Found");
                }

            }
            @Override
            public void onFailure(Call<ResponseSponsorSearch> call, Throwable t) {

            }
        });
    }
//    private void ref_search(){
//        String search=referrals_search.getQuery().toString();
//        String fdate=referrals_fromdate.getText().toString();
//        String tdate=referrals_todate.getText().toString();
//        SharedPreferences shpref;
//        shpref=getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u=shpref.getString("ID","");
//        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseSponsorSearch>call=api.SponsorSearch(Integer.parseInt(u),fdate,tdate,search);
//        call.enqueue(new Callback<ResponseSponsorSearch>() {
//            @Override
//            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
//                Log.e(TAG, new Gson().toJson(response.body()));
//                if(response.body().getStatus().equals("1")){
//                    ResponseSponsorSearch responseSponsorSearch=response.body();
//                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_referrals.setLayoutManager(layoutManager);
//                    recycler_referrals.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    sponsorListViewAdapter=new SponsorListViewAdapter(sponsorListView,getApplicationContext());
//                    recycler_referrals.setAdapter(sponsorListViewAdapter);
//                    referrals_nodata.setVisibility(View.GONE);
//                }
//                else{
//                    referrals_nodata.setText("No Data Found");
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
