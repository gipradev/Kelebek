package com.gipra.kelebek.ui.IncomeDetails.Team_Sales_Bonus_details;

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


public class TeamSalesBonusFragment extends Fragment {

    Button fromdate_tsb,todate_tsb,search_tsb;
    TextView txt_tsb;
    RecyclerView recycler_tsb;
    private List<TSBList> tsbLists;
    private TeamSalesBonusRecyclerAdapter teamSalesBonusRecyclerAdapter;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView tsb_search;
    private static final String TAG = "TeamSalesBonus";


    public TeamSalesBonusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_team_sales_bonus, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Team Sales Bonus Details");
        tsb_search=view.findViewById(R.id.tsb_search);
        tsb_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // TSB_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_tsb=view.findViewById(R.id.txtnodata_tsb);
        recycler_tsb=view.findViewById(R.id.recycler_tsb);
        fromdate_tsb=view.findViewById(R.id.fromdate_tsb);
        todate_tsb=view.findViewById(R.id.todate_tsb);

        search_tsb=view.findViewById(R.id.btnsearch_tsb);
        search_tsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                teamsaleslist();
            }
        });
        try {
            teamsaleslist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_tsb=view.findViewById(R.id.fromdate_tsb);

        fromdate_tsb.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_tsb.setText(dateString);



        todate_tsb.setText(dateString);

        fromdate_tsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_tsb.setOnClickListener(new View.OnClickListener() {
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
                fromdate_tsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_tsb.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void teamsaleslist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_tsb.getText().toString();
        String tdate=todate_tsb.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseTeamSalesBonus> usercall=api.TeamSalesBonus(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseTeamSalesBonus>() {
            @Override
            public void onResponse(Call<ResponseTeamSalesBonus> call, Response<ResponseTeamSalesBonus> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseTeamSalesBonus responseTeamSalesBonus=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_tsb.setLayoutManager(layoutManager);
                    recycler_tsb.setHasFixedSize(true);
                    tsbLists=responseTeamSalesBonus.getData();
                    teamSalesBonusRecyclerAdapter=new TeamSalesBonusRecyclerAdapter(tsbLists,getActivity());
                    recycler_tsb.setAdapter(teamSalesBonusRecyclerAdapter);
                    txt_tsb.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_tsb.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseTeamSalesBonus> call, Throwable t) {

            }
        });
    }
//    private void TSB_Search(){
//        String search=tsb_search.getQuery().toString();
//        String fdate=fromdate_tsb.getText().toString();
//        String tdate=todate_tsb.getText().toString();
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
//                    recycler_tsb.setLayoutManager(layoutManager);
//                    recycler_tsb.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    teamSalesBonusRecyclerAdapter=new TeamSalesBonusRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_tsb.setAdapter(teamSalesBonusRecyclerAdapter);
//                    txt_tsb.setVisibility(View.GONE);
//                }
//                else{
//                    txt_tsb.setText("No Data Found");
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
