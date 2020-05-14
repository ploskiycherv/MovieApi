package com.example.movieapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapi.R
import com.example.movieapi.presentation.list.MainMovieListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction().addToBackStack(null)

        fragmentTransaction.replace(R.id.main_container, MainMovieListFragment())

        fragmentTransaction.commit()
    }
}

// koin
// private val viewModel: MovieModel by inject()
// Жизненный цикл Application
// Усовершенствование Kotlin
// map let apply with when

//rx