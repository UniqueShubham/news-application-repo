package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem home, sports, health, science, entertainment, technology;
    Toolbar toolBar;
    ViewPager viewPager;
    String api = "9c3a87544fd840ba990d54e3e5fa6412";
    ImageView ivMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        tabLayout = findViewById(R.id.tabLayout);
        home = findViewById(R.id.home);
        sports = findViewById(R.id.sports);
        health = findViewById(R.id.health);
        science = findViewById(R.id.science);
        entertainment = findViewById(R.id.entertainment);
        technology = findViewById(R.id.technology);
        ivMode = findViewById(R.id.ivMode);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        viewPager = findViewById(R.id.fragContainer);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pageAdapter);

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            ivMode.setImageResource(R.drawable.light);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            ivMode.setImageResource(R.drawable.dark);
        }
        ivMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When user taps the enable/disable dark mode button
                if (isDarkModeOn==true) {

                    // if dark mode is on it will turn it off
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    editor.putBoolean("isDarkModeOn", false);
                    editor.apply();

                    ivMode.setImageResource(R.drawable.dark);
                }
                else {

                    // if dark mode is of it will turn it on
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    // it will set isDarkModeOn boolean to true
                    editor.putBoolean("isDarkModeOn", true);
                    editor.apply();

                    ivMode.setImageResource(R.drawable.light);
                }
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4 || tab.getPosition()==5)
                        pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}