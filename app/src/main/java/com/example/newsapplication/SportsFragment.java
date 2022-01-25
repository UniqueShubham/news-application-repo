package com.example.newsapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {

    String api = "9c3a87544fd840ba990d54e3e5fa6412";
    ArrayList<NewsModel> newsList;
    NewsCardAdapter adapter;
    String country = "in";
    private RecyclerView rvSports;
    private String category="sports";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sports_fragment,null);
        rvSports = view.findViewById(R.id.rvSports);
        newsList = new ArrayList<NewsModel>();
        rvSports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsCardAdapter(newsList, getContext());
        rvSports.setAdapter(adapter);

        showNews();

        return view;
    }

    private void showNews() {
        ApiUtilities.getApiInterface().getCategoryNews(country, category,10, api).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(response.isSuccessful()==true)
                {
                    newsList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getActivity(), "Could not fetch news.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}