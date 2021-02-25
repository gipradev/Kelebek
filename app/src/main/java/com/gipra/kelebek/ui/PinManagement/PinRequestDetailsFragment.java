package com.gipra.kelebek.ui.PinManagement;


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

public class PinRequestDetailsFragment extends Fragment {

    Button fromdate_pin,todate_pin,search_pin;
    TextView txt_pin;
    RecyclerView recycler_pin;
    private List<PinListView> pinListViews;
    private PinRecyclerAdapter pinRecyclerAdapter;
    private static final String TAG = "PinRequestFragment";
    DatePickerDialog fromdp,todp;
    SimpleDateFormat dateFormatter;
    SearchView pin_search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        if (container != null) {
//            container.removeAllViews();
//        }
        final View view = inflater.inflate(R.layout.fragment_pin_request_details, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("PIN Request Details");
        pin_search=view.findViewById(R.id.pin_search);
        pin_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Pin_Search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        txt_pin=view.findViewById(R.id.txtnodata_pin);
        recycler_pin=view.findViewById(R.id.recycler_pin);

        fromdate_pin=view.findViewById(R.id.fromdate_pin);
        todate_pin=view.findViewById(R.id.todate_pin);
        pinlist();
        search_pin=view.findViewById(R.id.btnsearch_pin);
        search_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "hbcahhdahdghha", Toast.LENGTH_SHORT).show();
                //pinlist();
                try {
                    pinlist();
                }catch (Exception e){
                    Log.i(TAG,"error"+e);
                }
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        long date = System.currentTimeMillis();
        fromdate_pin=view.findViewById(R.id.fromdate_pin);

        fromdate_pin.requestFocus();
        String dateString = dateFormatter.format(date);
        fromdate_pin.setText(dateString);
        todate_pin.setText(dateString);
        fromdate_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromdp.show();
            }
        });
        todate_pin.setOnClickListener(new View.OnClickListener() {
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
                fromdate_pin.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        todp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todate_pin.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        return view;
    }

    private void pinlist(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("loading data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String fdate=fromdate_pin.getText().toString();
        String tdate=todate_pin.getText().toString();
        Log.e(TAG,"fromdate="+fdate);
        Log.e(TAG,"todate="+tdate);
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        Log.e(TAG,"id="+u);

        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        assert u != null;
        Call<ResponsePinRequest> usercall=api.PinRequest(Integer.parseInt(u),fdate,tdate);
        usercall.enqueue(new Callback<ResponsePinRequest>() {
            @Override
            public void onResponse(Call<ResponsePinRequest> call, Response<ResponsePinRequest> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    progressDialog.dismiss();
                    txt_pin.setVisibility(View.GONE);
                    ResponsePinRequest responsePinRequest=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_pin.setLayoutManager(layoutManager);
                    recycler_pin.setHasFixedSize(true);
                    pinListViews=responsePinRequest.getData();
                    pinRecyclerAdapter=new PinRecyclerAdapter(pinListViews,getActivity());
                    recycler_pin.setAdapter(pinRecyclerAdapter);
                }
                else{
                    progressDialog.dismiss();
                    txt_pin.setText("No Data Found");
                }
            }
            @Override
            public void onFailure(Call<ResponsePinRequest> call, Throwable t) {

            }
        });
    }
    private void Pin_Search(){
        String search=pin_search.getQuery().toString();
        String fdate=fromdate_pin.getText().toString();
        String tdate=todate_pin.getText().toString();
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePinSearch>call=api.PinSearch(Integer.parseInt(u),fdate,tdate);
        call.enqueue(new Callback<ResponsePinSearch>() {
            @Override
            public void onResponse(Call<ResponsePinSearch> call, Response<ResponsePinSearch> response) {
                if(response.body().getStatus().equals("1")){

                    ResponsePinSearch responsePinSearch=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_pin.setLayoutManager(layoutManager);
                    recycler_pin.setHasFixedSize(true);
                    pinListViews=responsePinSearch.getData();
                    pinRecyclerAdapter=new PinRecyclerAdapter(pinListViews,getActivity());
                    recycler_pin.setAdapter(pinRecyclerAdapter);
                    txt_pin.setVisibility(View.GONE);
                }
                else{
                    txt_pin.setText("No Data Found");


                }
            }

            @Override
            public void onFailure(Call<ResponsePinSearch> call, Throwable t) {

            }
        });
    }
}