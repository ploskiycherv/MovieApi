package com.example.movieapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.movieapi.R
import com.example.movieapi.adapter.MovieItemAdapter
import com.example.movieapi.adapter.MovieItemDiffUtil
import com.example.movieapi.data.MovieModel
import com.example.movieapi.data.MovieRepo
import com.example.movieapi.data.OnItemClick
import kotlinx.android.synthetic.main.fragment_main_movie_list.*
import org.koin.android.ext.android.inject

class MainMovieListFragment : Fragment() {

    private val viewModel: MovieModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_main_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstSorting = "Popular"
        val secondSorting = "Top Rated"
        val thirdSorting = "Now Playing"

        firstTextView.text = firstSorting
        secondTextView.text = secondSorting
        thirdTextView.text = thirdSorting

        val firstMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val secondMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val thirdMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)

        firstRecyclerView.adapter = firstMovieItemAdapter
        secondRecyclerView.adapter = secondMovieItemAdapter
        thirdRecyclerView.adapter = thirdMovieItemAdapter

        with(viewModel) {
            firstMovieItemLiveData().observe(viewLifecycleOwner, Observer { data ->
                firstMovieItemAdapter.submitList(data)
            })
            secondMovieItemLiveData().observe(viewLifecycleOwner, Observer { data ->
                secondMovieItemAdapter.submitList(data)
            })
            thirdMovieItemLiveData().observe(viewLifecycleOwner, Observer { data ->
                thirdMovieItemAdapter.submitList(data)
            })
            getMovies()
        }
    }

    private var onClickListener = object : OnItemClick {
        override fun onClick(id: String?) {
            activity?.let {
                val fragment: Fragment = DescriptionMovieFragment().apply {
                    arguments = Bundle().apply { putString("ID", id) }
                }

                with(it.supportFragmentManager.beginTransaction()) {
                    replace(R.id.main_container, fragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}