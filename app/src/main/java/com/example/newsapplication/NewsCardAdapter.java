package com.example.newsapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.ViewHolder> {

    ArrayList<NewsModel> newsList;
    Context context;

    public interface ItemClicked
    {
        void onItemClicked(int index);
        void startActivity(Intent intent);
    }

    public NewsCardAdapter(ArrayList<NewsModel> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCardAdapter.ViewHolder holder, int position) {

        holder.cvNews.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String url = newsList.get(holder.getAdapterPosition()).getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        holder.tvAuthor.setText("Author: " + newsList.get(position).getAuthor());
        holder.tvDesc.setText(newsList.get(position).getDescription());
        holder.tvTime.setText("Published at: " + newsList.get(position).getPublishedAt());
        holder.tvTitle.setText(newsList.get(position).getTitle());
        Glide.with(context).load(newsList.get(position).getUrlToImage()).into(holder.ivPic);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvAuthor, tvTime;
        ImageView ivPic;
        CardView cvNews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivPic = itemView.findViewById(R.id.ivPic);
            cvNews = itemView.findViewById(R.id.cvNews);
        }
    }
}
