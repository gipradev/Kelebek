package com.gipra.kelebek.ui.News;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
public class NewsFragment extends Fragment {

    RecyclerView recycler_news;
    private List<NewsList> newsList;
    private NewsAdapter newsAdapter;
    private static final String TAG = "NewsFragment";
    TextView no_news;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_news, container, false);
       recycler_news=view.findViewById(R.id.recycler_news);
       no_news=view.findViewById(R.id.no_news);
       newsview();

       return view;
    }
    private  void newsview(){
        SharedPreferences shpref;
        shpref=getContext().getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
        String u=shpref.getString("ID","");
        ApiInterface api= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseNews>usercall=api.News(Integer.parseInt(u));
        usercall.enqueue(new Callback<ResponseNews>() {
            @Override
            public void onResponse(Call<ResponseNews> call, Response<ResponseNews> response) {
                Log.e(TAG, new Gson().toJson(response.body()));
                if (response.body().getStatus().equals("1")){
                    ResponseNews responseNews=response.body();
                    final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_news.setLayoutManager(layoutManager);
                    recycler_news.setHasFixedSize(true);
                    newsList=responseNews.getData();
                    newsAdapter=new NewsAdapter(newsList,getActivity());
                    recycler_news.setAdapter(newsAdapter);
                    no_news.setVisibility(View.GONE);
                }
                else{
                    no_news.setText("No Data Found");
                }

            }

            @Override
            public void onFailure(Call<ResponseNews> call, Throwable t) {

            }
        });
    }


}
