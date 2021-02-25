package com.gipra.kelebek.ui.IncomeDetails.Repurchase_BV_Report;


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

public class RepurchaseBVReportFragment extends Fragment {

    Button fromdate_repurchaseBV, todate_repurchaseBV, search_repurchaseBV;
    TextView txt_repurchaseBV,txt_totalamount,txt_totalbv;
    RecyclerView recycler_repurchaseBV;
    private List<RepurchaseBvReportlist> repurchaseBvReportlists;
    private RepurchaseBVrecyclerAdapter repurchaseBVrecyclerAdapter;
    private static final String TAG = "RepurchaseBVR";
    DatePickerDialog fromdp, todp;
    SimpleDateFormat dateFormatter;
    SearchView rebv_search;
    LinearLayout linearLayout;

    public RepurchaseBVReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_repurchase_bv, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Repurchase BV Report");
        linearLayout=view.findViewById(R.id.totallinearlayout);
        linearLayout.setVisibility(View.GONE);
        txt_totalamount=view.findViewById(R.id.repurchasebv_totalamount);
        txt_totalbv=view.findViewById(R.id.repurchasebv_totalbv);
        rebv_search = view.findViewById(R.id.rebv_search);
        rebv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //ReBV_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_repurchaseBV = view.findViewById(R.id.txtnodata_rebv);
        recycler_repurchaseBV = view.findViewById(R.id.recycler_rebv);
        fromdate_repurchaseBV = view.findViewById(R.id.fromdate_rebv);
        todate_repurchaseBV = view.findViewById(R.id.todate_rebv);


        search_repurchaseBV = view.findViewById(R.id.btnsearch_rebv);
        search_repurchaseBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                repurchasebvlist();
            }
        });
        try {
            repurchasebvlist();
        } catch (Exception e) {
            Log.i(TAG, "error" + e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_repurchaseBV = view.findViewById(R.id.fromdate_rebv);

        fromdate_repurchaseBV.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_repurchaseBV.setText(dateString);


        todate_repurchaseBV.setText(dateString);

        fromdate_repurchaseBV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_repurchaseBV.setOnClickListener(new View.OnClickListener() {
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
                fromdate_repurchaseBV.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_repurchaseBV.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }


    private void repurchasebvlist() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate = fromdate_repurchaseBV.getText().toString();
        String tdate = todate_repurchaseBV.getText().toString();
        SharedPreferences shpref;
        shpref = getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u = shpref.getString("ID", "");
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseRepurchaseBvReport> usercall = api.RepurchaseBvReport(Integer.parseInt(u), fdate, tdate);
        usercall.enqueue(new Callback<ResponseRepurchaseBvReport>() {
            @Override
            public void onResponse(Call<ResponseRepurchaseBvReport> call, Response<ResponseRepurchaseBvReport> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                txt_totalamount.setText(String.valueOf(response.body().getTotalamount())+"/-");
                txt_totalbv.setText(String.valueOf(response.body().getTotalBv())+" BV");
                if (response.body().getStatus().equals("1")) {
                    progressDialog.dismiss();
                    ResponseRepurchaseBvReport responseRepurchaseBvReport = response.body();
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_repurchaseBV.setLayoutManager(layoutManager);
                    recycler_repurchaseBV.setHasFixedSize(true);
                    repurchaseBvReportlists = responseRepurchaseBvReport.getData();
                    repurchaseBVrecyclerAdapter = new RepurchaseBVrecyclerAdapter(repurchaseBvReportlists, getActivity());
                    recycler_repurchaseBV.setAdapter(repurchaseBVrecyclerAdapter);
                    linearLayout.setVisibility(View.VISIBLE);
                    txt_repurchaseBV.setVisibility(View.GONE);
                } else {
                    progressDialog.dismiss();
                    linearLayout.setVisibility(View.GONE);
                    txt_repurchaseBV.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseRepurchaseBvReport> call, Throwable t) {

            }
        });
    }

//    private void ReBV_Search() {
//        String search = rebv_search.getQuery().toString();
//        String fdate = fromdate_repurchaseBV.getText().toString();
//        String tdate = todate_repurchaseBV.getText().toString();
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
//                    recycler_repurchaseBV.setLayoutManager(layoutManager);
//                    recycler_repurchaseBV.setHasFixedSize(true);
//                    sponsorListView = responseSponsorSearch.getData();
//                    repurchaseBVrecyclerAdapter = new RepurchaseBVrecyclerAdapter(sponsorListView, getActivity());
//                    recycler_repurchaseBV.setAdapter(repurchaseBVrecyclerAdapter);
//                    txt_repurchaseBV.setVisibility(View.GONE);
//                } else {
//                    txt_repurchaseBV.setText("No Data Found");
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