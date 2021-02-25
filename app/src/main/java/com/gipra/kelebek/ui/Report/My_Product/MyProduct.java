package com.gipra.kelebek.ui.Report.My_Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class MyProduct extends Fragment {

    Button fromdate_mypdt,todate_mypdt,search_mypdt;
    RecyclerView recycler_mypdt;
    private List<MyProductListView> myProductListViews;
    private ProductListViewAdapter productListViewAdapter;
    private static final String TAG = "MyProduct";
    TextView txt_mypdt;
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView mypdt_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_my_product, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Product");
        mypdt_search=view.findViewById(R.id.mypdt_search);
        mypdt_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mypdt_search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_mypdt=view.findViewById(R.id.txtnodata_mypdt);
        recycler_mypdt=view.findViewById(R.id.recycler_mypdt);
        fromdate_mypdt=view.findViewById(R.id.fromdate_mypdt);
        todate_mypdt=view.findViewById(R.id.todate_mypdt);

        search_mypdt=view.findViewById(R.id.search_mypdt);
        search_mypdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                MyPdtlist();
            }
        });
        try {
            MyPdtlist();
        }catch (Exception e){
            Log.i(TAG,"error"+e);
        }

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_mypdt=view.findViewById(R.id.fromdate_mypdt);

        fromdate_mypdt.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_mypdt.setText(dateString);


        todate_mypdt.setText(dateString);

        fromdate_mypdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_mypdt.setOnClickListener(new View.OnClickListener() {
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
                fromdate_mypdt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_mypdt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }
    private void  MyPdtlist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_mypdt.getText().toString();
        String tdate=todate_mypdt.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMyProduct> usercall=api.MyProductview(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponseMyProduct>() {
            @Override
            public void onResponse(Call<ResponseMyProduct> call, Response<ResponseMyProduct> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    ResponseMyProduct responseMyProduct=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_mypdt.setLayoutManager(layoutManager);
                    recycler_mypdt.setHasFixedSize(true);
                    myProductListViews=responseMyProduct.getData();
                    productListViewAdapter=new ProductListViewAdapter(myProductListViews,getActivity(),u);
                    recycler_mypdt.setAdapter(productListViewAdapter);
                    txt_mypdt.setVisibility(View.GONE);
                }
                else{
                    progressDialog.dismiss();
                    txt_mypdt.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseMyProduct> call, Throwable t) {

            }
        });
    }

    private void mypdt_search(){
        String search=mypdt_search.getQuery().toString();
        String fdate=fromdate_mypdt.getText().toString();
        String tdate=todate_mypdt.getText().toString();
        final SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        final String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMyProduct>call=api.MyProductview(Integer.parseInt(u),fdate,tdate);
        call.enqueue(new Callback<ResponseMyProduct>() {
            @Override
            public void onResponse(Call<ResponseMyProduct> call, Response<ResponseMyProduct> response) {
                if(response.body().getStatus().equals("1")){

                    ResponseMyProduct responseSponsorSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_mypdt.setLayoutManager(layoutManager);
                    recycler_mypdt.setHasFixedSize(true);
                    myProductListViews=responseSponsorSearch.getData();
                    productListViewAdapter=new ProductListViewAdapter(myProductListViews,getActivity(),u);
                    recycler_mypdt.setAdapter(productListViewAdapter);
                    txt_mypdt.setVisibility(View.GONE);
                }
                else{
                    txt_mypdt.setText("No Data Found");


                }
            }

            @Override
            public void onFailure(Call<ResponseMyProduct> call, Throwable t) {

            }
        });
    }
}


