package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income;


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
public class RepurchaseIncomeFragment extends Fragment {
    Button fromdate_reincome,todate_reincome,search_reincome;
    TextView txt_reincome;
    RecyclerView recycler_reincome;
    private List<RepurchaseIncomelist> repurchaseIncomelists;
    private RepurchaseIncomeRecyclerAdapter repurchaseIncomeRecyclerAdapter;
    private static final String TAG = "RepurchaseIncome";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView reincome_search;

    public RepurchaseIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_repurchase_income, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Repurchase Income");
        reincome_search=view.findViewById(R.id.reincome_search);
        reincome_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //ReIncome_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_reincome=view.findViewById(R.id.txtnodata_reincome);
        recycler_reincome=view.findViewById(R.id.recycler_reincome);
        fromdate_reincome=view.findViewById(R.id.fromdate_reincome);
        todate_reincome=view.findViewById(R.id.todate_reincome);
        AllDetailsReIncomelist();
        search_reincome=view.findViewById(R.id.btnsearch_reincome);
        search_reincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                ReIncomelist();
            }
        });
        try {
            ReIncomelist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_reincome=view.findViewById(R.id.fromdate_reincome);

        fromdate_reincome.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_reincome.setText(dateString);



        todate_reincome.setText(dateString);

        fromdate_reincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_reincome.setOnClickListener(new View.OnClickListener() {
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
                fromdate_reincome.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_reincome.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void ReIncomelist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_reincome.getText().toString();
        String tdate=todate_reincome.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRepurchaseIncome> usercall=api.RepurchaseIncome(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseRepurchaseIncome>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncome> call, Response<ResponseRepurchaseIncome> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseRepurchaseIncome responseRepurchaseIncome=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_reincome.setLayoutManager(layoutManager);
                    recycler_reincome.setHasFixedSize(true);
                    repurchaseIncomelists=responseRepurchaseIncome.getData();
                    repurchaseIncomeRecyclerAdapter=new RepurchaseIncomeRecyclerAdapter(repurchaseIncomelists,getActivity());
                    recycler_reincome.setAdapter(repurchaseIncomeRecyclerAdapter);
                    txt_reincome.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_reincome.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseRepurchaseIncome> call, Throwable t) {

            }
        });
    }



    private void AllDetailsReIncomelist(){
        String fdate=fromdate_reincome.getText().toString();
        String tdate=todate_reincome.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRepurchaseIncome> usercall=api.AllRepurchaseIncome(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseRepurchaseIncome>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncome> call, Response<ResponseRepurchaseIncome> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseRepurchaseIncome responseRepurchaseIncome=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_reincome.setLayoutManager(layoutManager);
                    recycler_reincome.setHasFixedSize(true);
                    repurchaseIncomelists=responseRepurchaseIncome.getData();
                    repurchaseIncomeRecyclerAdapter=new RepurchaseIncomeRecyclerAdapter(repurchaseIncomelists,getActivity());
                    recycler_reincome.setAdapter(repurchaseIncomeRecyclerAdapter);
                    txt_reincome.setVisibility(View.GONE);
                }
                else{
                    txt_reincome.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseRepurchaseIncome> call, Throwable t) {

            }
        });
    }



//    private void ReIncome_Search(){
//        String search=reincome_search.getQuery().toString();
//        String fdate=fromdate_reincome.getText().toString();
//        String tdate=todate_reincome.getText().toString();
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
//                    recycler_reincome.setLayoutManager(layoutManager);
//                    recycler_reincome.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    repurchaseIncomeRecyclerAdapter=new RepurchaseIncomeRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_reincome.setAdapter(repurchaseIncomeRecyclerAdapter);
//                    txt_reincome.setVisibility(View.GONE);
//                }
//                else{
//                    txt_reincome.setText("No Data Found");
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
