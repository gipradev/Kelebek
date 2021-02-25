package com.gipra.kelebek.ui.IncomeDetails.First_Purchase_BV_Report;


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
import android.widget.LinearLayout;
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

public class FirstPurchaseBVFragment extends Fragment {
    Button fromdate_firstpurchase, todate_firstpurchase, search_firstpurchase;
    TextView txt_firstpurchase,t_totalamount,t_totalbv;
    RecyclerView recycler_firstpurchase;
    private List<FirstPurchaselist> firstPurchaselists;
    private FirstPurchaseBVAdapter firstPurchaseBVAdapter;
    private static final String TAG = "FirstPurchaseBV";
    DatePickerDialog fromdp, todp;
    SimpleDateFormat dateFormatter;
    SearchView firstpurchase_search;
LinearLayout linearLayout;

    public FirstPurchaseBVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_personal_first_purchase_bv, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("First Purchase BV Reports");
        linearLayout=view.findViewById(R.id.firstpurchase_totallinearlayout);
        linearLayout.setVisibility(View.GONE);



        firstpurchase_search = (SearchView) view.findViewById(R.id.firstpurchase_search);
        firstpurchase_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //FirstPurchase_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_firstpurchase = view.findViewById(R.id.txtnodata_firstpurchase);
        recycler_firstpurchase = view.findViewById(R.id.recycler_firstpurchase);
        fromdate_firstpurchase = view.findViewById(R.id.fromdate_firstpurchase);
        todate_firstpurchase = view.findViewById(R.id.todate_firstpurcahse);

        t_totalamount=view.findViewById(R.id.firstpurchase_totalamount);
        t_totalbv=view.findViewById(R.id.firstpurchase_totalbv);
        search_firstpurchase = (Button) view.findViewById(R.id.search_btnfirstpurchase);
        search_firstpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                firstpurchaselist();
            }
        });
        try {
            firstpurchaselist();
        } catch (Exception e) {
            Log.i(TAG, "error" + e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_firstpurchase = view.findViewById(R.id.fromdate_firstpurchase);

        fromdate_firstpurchase.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_firstpurchase.setText(dateString);


        todate_firstpurchase.setText(dateString);

        fromdate_firstpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_firstpurchase.setOnClickListener(new View.OnClickListener() {
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
                fromdate_firstpurchase.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_firstpurchase.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void firstpurchaselist() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        String fdate = fromdate_firstpurchase.getText().toString();
        String tdate = todate_firstpurchase.getText().toString();
        SharedPreferences shpref;
        shpref = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseFirstPurchaseSearch> usercall = api.FirstPurchase(Integer.parseInt(u), fdate, tdate);
        usercall.enqueue(new Callback<ResponseFirstPurchaseSearch>() {
            @Override
            public void onResponse(Call<ResponseFirstPurchaseSearch> call, Response<ResponseFirstPurchaseSearch> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                progressDialog.dismiss();

                t_totalamount.setText(String.valueOf(response.body().gettotalamount())+"/-");
                t_totalbv.setText(String.valueOf(response.body().gettotalBv())+" BV");

                if (response.body().getStatus().equals("1")) {
                    progressDialog.dismiss();
                    ResponseFirstPurchaseSearch responseFirstPurchaseSearch = response.body();
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_firstpurchase.setLayoutManager(layoutManager);
                    recycler_firstpurchase.setHasFixedSize(true);
                    firstPurchaselists = responseFirstPurchaseSearch.getData();
                    firstPurchaseBVAdapter = new FirstPurchaseBVAdapter(firstPurchaselists, getActivity());
                    recycler_firstpurchase.setAdapter(firstPurchaseBVAdapter);
                    linearLayout.setVisibility(View.VISIBLE);

                    txt_firstpurchase.setVisibility(View.GONE);


                } else {
                    progressDialog.dismiss();
                    linearLayout.setVisibility(View.GONE);
                    txt_firstpurchase.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseFirstPurchaseSearch> call, Throwable t) {

            }
        });
    }

//    private void FirstPurchase_Search() {
//        String search = firstpurchase_search.getQuery().toString();
//        String fdate = fromdate_firstpurchase.getText().toString();
//        String tdate = todate_firstpurchase.getText().toString();
//        SharedPreferences shpref;
//        shpref = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
//        String u = shpref.getString("ID", "");
//        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseSponsorSearch> call = api.SponsorSearch(Integer.parseInt(u), fdate, tdate, search);
//        call.enqueue(new Callback<ResponseSponsorSearch>() {
//            @Override
//            public void onResponse(Call<ResponseSponsorSearch> call, Response<ResponseSponsorSearch> response) {
//                if (response.body().getStatus().equals("1")) {
//
//                    ResponseSponsorSearch responseSponsorSearch = response.body();
//                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                    recycler_firstpurchase.setLayoutManager(layoutManager);
//                    recycler_firstpurchase.setHasFixedSize(true);
//                    sponsorListView = responseSponsorSearch.getData();
//                    firstPurchaseBVAdapter = new FirstPurchaseBVAdapter(sponsorListView, getActivity());
//                    recycler_firstpurchase.setAdapter(firstPurchaseBVAdapter);
//                    txt_firstpurchase.setVisibility(View.GONE);
//                } else {
//                    txt_firstpurchase.setText("No Data Found");
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
//
//    }
}