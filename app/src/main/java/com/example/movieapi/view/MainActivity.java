package com.example.movieapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.movieapi.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null);

        MainMovieListFragment mainMovieListFragment = new MainMovieListFragment(fragmentTransaction);

        fragmentTransaction.replace(R.id.main_container, mainMovieListFragment);

        fragmentTransaction.commit();

    }

}