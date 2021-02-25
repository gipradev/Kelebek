package com.gipra.kelebek.ui.IncomeDetails.Repurchase_Income_details;


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
public class RepurchaseIncomeDetailFragment extends Fragment {
    Button fromdate_redetails,todate_redetails,search_redetails;
    TextView txt_redetails;
    RecyclerView recycler_redetails;
    private List<RepurchaseIncomeDetailList> repurchaseIncomeDetailLists;
    private RepurchaseDetailsRecyclerAdapter repurchaseDetailsRecyclerAdapter;
    private static final String TAG = "RepurchaseIncomeDetail";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView redetails_search;

    public RepurchaseIncomeDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_repurchase_income_details, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Repurchase Income Detail");
        redetails_search=view.findViewById(R.id.redetails_search);
        redetails_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //ReIncomeDetail_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_redetails=view.findViewById(R.id.txtnodata_redetails);
        recycler_redetails=view.findViewById(R.id.recycler_redetails);
        fromdate_redetails=view.findViewById(R.id.fromdate_redetails);
        todate_redetails=view.findViewById(R.id.todate_redetails);

        allreincomedetaillist();
        search_redetails=view.findViewById(R.id.btnsearch_redetails);
        search_redetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                reincomedetaillist();
            }
        });
        try {
            reincomedetaillist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_redetails=view.findViewById(R.id.fromdate_redetails);

        fromdate_redetails.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_redetails.setText(dateString);



        todate_redetails.setText(dateString);

        fromdate_redetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_redetails.setOnClickListener(new View.OnClickListener() {
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
                fromdate_redetails.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_redetails.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void reincomedetaillist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_redetails.getText().toString();
        String tdate=todate_redetails.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRepurchaseIncomeDetails> usercall=api.RepurchaseIncomeDetails(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseRepurchaseIncomeDetails>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncomeDetails> call, Response<ResponseRepurchaseIncomeDetails> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseRepurchaseIncomeDetails responseRepurchaseIncomeDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_redetails.setLayoutManager(layoutManager);
                    recycler_redetails.setHasFixedSize(true);
                    repurchaseIncomeDetailLists=responseRepurchaseIncomeDetails.getData();
                    repurchaseDetailsRecyclerAdapter=new RepurchaseDetailsRecyclerAdapter(repurchaseIncomeDetailLists,getActivity());
                    recycler_redetails.setAdapter(repurchaseDetailsRecyclerAdapter);
                    txt_redetails.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_redetails.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseRepurchaseIncomeDetails> call, Throwable t) {

            }
        });
    }




    private void allreincomedetaillist(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRepurchaseIncomeDetails> usercall=api.AllRepurchaseIncomeDetails(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseRepurchaseIncomeDetails>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseIncomeDetails> call, Response<ResponseRepurchaseIncomeDetails> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseRepurchaseIncomeDetails responseRepurchaseIncomeDetails=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_redetails.setLayoutManager(layoutManager);
                    recycler_redetails.setHasFixedSize(true);
                    repurchaseIncomeDetailLists=responseRepurchaseIncomeDetails.getData();
                    repurchaseDetailsRecyclerAdapter=new RepurchaseDetailsRecyclerAdapter(repurchaseIncomeDetailLists,getActivity());
                    recycler_redetails.setAdapter(repurchaseDetailsRecyclerAdapter);
                    txt_redetails.setVisibility(View.GONE);
                }
                else{
                    txt_redetails.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseRepurchaseIncomeDetails> call, Throwable t) {

            }
        });
    }



//    private void ReIncomeDetail_Search(){
//        String search=redetails_search.getQuery().toString();
//        String fdate=fromdate_redetails.getText().toString();
//        String tdate=todate_redetails.getText().toString();
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
//                    recycler_redetails.setLayoutManager(layoutManager);
//                    recycler_redetails.setHasFixedSize(true);
//                    sponsorListView=responseSponsorSearch.getData();
//                    repurchaseDetailsRecyclerAdapter=new RepurchaseDetailsRecyclerAdapter(sponsorListView,getActivity());
//                    recycler_redetails.setAdapter(repurchaseDetailsRecyclerAdapter);
//                    txt_redetails.setVisibility(View.GONE);
//                }
//                else{
//                    txt_redetails.setText("No Data Found");
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
