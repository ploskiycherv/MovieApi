package com.example.movieapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.movieapi.R;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    FragmentManager fragmentManager;
    MainMovieListFragment mainMovieListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.main_container);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, new MainMovieListFragment());
        fragmentTransaction.commit();


//        MainMovieListFragment mainMovieListFragment;
//        FragmentTransaction fragmentTransaction;
//
//        mainMovieListFragment = new MainMovieListFragment();
//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.main_movie_list_container, mainMovieListFragment);
//        fragmentTransaction.commit();

    }

}