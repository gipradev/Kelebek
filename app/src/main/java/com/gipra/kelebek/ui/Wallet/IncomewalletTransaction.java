package com.gipra.kelebek.ui.Wallet;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomewalletTransaction extends Fragment {
    private List<IncomeWalletList> incomeWalletList;
    private IncomeWalletAdapter incomeWalletAdapter;
    RecyclerView recycler_income_wallet;
    private static final String TAG = "IncomewalletTransaction";
    TextView txtnodata;
    SearchView income_wallet_search;

    public IncomewalletTransaction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_incomewallet_transaction, container, false);
        txtnodata=view.findViewById(R.id.txt_income_wallet_nodata);
        recycler_income_wallet=view.findViewById(R.id.recycler_income_wallet);
        income_wallet_search=view.findViewById(R.id.income_wallet_search);
        income_wallet_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(),  "dhsdgsadah", Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
       wallettrans();
        return  view;
    }
    private  void wallettrans(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api=ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseIncomeWallet>call=api.IncomeWallet(Integer.parseInt(u));
        call.enqueue(new Callback<ResponseIncomeWallet>() {
            @Override
            public void onResponse(Call<ResponseIncomeWallet> call, Response<ResponseIncomeWallet> response) {
                if (response.body().getStatus().equals("1")){
                    Toast.makeText(getContext(), "hdghjdghdf", Toast.LENGTH_SHORT).show();
                }
                else {
                    txtnodata.setText("No Data Found");
                }

            }
            @Override
            public void onFailure(Call<ResponseIncomeWallet> call, Throwable t) {


            }
        });
    }


}
