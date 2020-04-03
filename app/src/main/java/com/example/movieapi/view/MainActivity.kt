package com.example.movieapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction().addToBackStack(null)
        fragmentTransaction.replace(R.id.main_container, MainMovieListFragment())
        fragmentTransaction.commit()
    }
}