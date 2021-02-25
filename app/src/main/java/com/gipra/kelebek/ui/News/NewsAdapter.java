package com.gipra.kelebek.ui.News;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gipra.kelebek.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private Context context;
    private List<NewsList> newsList;
    private static final String TAG = "NewsAdapter";
    public  NewsAdapter(List<NewsList> newsList, Context context){
        this.newsList=newsList;
        this.context=context;
    }
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newslistitem,viewGroup,false);
        return new ViewHolder(view);
    }

    public  void onBindViewHolder(ViewHolder viewHolder,final int i){
        try{
            viewHolder.news_no.setText(String.valueOf(newsList.get(i).getCount()));
            viewHolder.news_date.setText(newsList.get(i).getDDate());
            viewHolder.news_title.setText(newsList.get(i).getCTitle());
            viewHolder.news_news.setText(newsList.get(i).getCNews());
            viewHolder.news_priority.setText(newsList.get(i).getCPriority());

        }catch (Exception e){
            Log.e(TAG,"error"+e);
        }

    }  @Override
    public int getItemCount() {

        return newsList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView news_no,news_date,news_title,news_news,news_priority;
        public ViewHolder(View view) {
            super(view);

            news_no = view.findViewById(R.id.news_no);
            news_date = view.findViewById(R.id.news_date);
            news_title = view.findViewById(R.id.news_title);
            news_news = view.findViewById(R.id.news_news);
            news_priority = view.findViewById(R.id.news_priority);

        }

    }
}
