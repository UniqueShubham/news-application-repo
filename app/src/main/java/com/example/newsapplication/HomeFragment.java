package com.example.newsapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    String api = "9c3a87544fd840ba990d54e3e5fa6412";
    ArrayList<NewsModel> newsList;
    NewsCardAdapter adapter;
    String country = "in";
    private RecyclerView rvHome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,null);

        rvHome = view.findViewById(R.id.rvHome);
        newsList = new ArrayList<NewsModel>();
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsCardAdapter(newsList, getContext());
        rvHome.setAdapter(adapter);

        showNews();

        return view;
    }

    private void showNews() {
        ApiUtilities.getApiInterface().getNews(country, 50, api).enqueue(new Callback<News>() {
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
