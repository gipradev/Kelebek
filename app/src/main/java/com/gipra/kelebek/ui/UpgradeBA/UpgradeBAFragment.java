package com.gipra.kelebek.ui.UpgradeBA;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gipra.kelebek.ApiClient;
import com.gipra.kelebek.ApiInterface;
import com.gipra.kelebek.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpgradeBAFragment extends Fragment {
    private List<UpgradeBA> upgradeBAList;
    private UpgradeBAAdapter upgradeBAAdapter;
    RecyclerView recycler_upgrade;
    private static final String TAG = "UpgradeBAFragment";
    TextView ba_nodata;
    SearchView ba_searchview;


    public UpgradeBAFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upgrade_ba, container, false);
        ba_nodata=view.findViewById(R.id.txt_ba_nodate);
        recycler_upgrade=view.findViewById(R.id.recyler_upgrade);
//        ba_searchview=view.findViewById(R.id.ba_search);
//        ba_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(),  "dhsdgsadah", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
        upgradeba_view();
        return  view;
    }
    private void upgradeba_view(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUpgradeBA> usercall=api.UpgradeBA(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseUpgradeBA>() {
            @Override
            public void onResponse(Call<ResponseUpgradeBA> call, Response<ResponseUpgradeBA> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseUpgradeBA responseUpgradeBA=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_upgrade.setLayoutManager(layoutManager);
                    recycler_upgrade.setHasFixedSize(true);
                    upgradeBAList=responseUpgradeBA.getData();
                    upgradeBAAdapter=new UpgradeBAAdapter(upgradeBAList,getActivity());
                    recycler_upgrade.setAdapter(upgradeBAAdapter);
                    ba_nodata.setVisibility(View.GONE);
                }
                else {
                    ba_nodata.setText("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<ResponseUpgradeBA> call, Throwable t) {

            }
        });
    }

}
