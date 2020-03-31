package com.example.movieapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.movieapi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainMovieListFragment mainMovieListFragment;
        FragmentTransaction fragmentTransaction;

        mainMovieListFragment = new MainMovieListFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_movie_list_container, mainMovieListFragment);
        fragmentTransaction.commit();

    }

}