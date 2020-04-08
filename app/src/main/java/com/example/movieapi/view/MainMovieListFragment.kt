package com.example.movieapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.R
import com.example.movieapi.adapter.MovieItemAdapter
import com.example.movieapi.adapter.MovieItemDiffUtil
import com.example.movieapi.data.MovieModel
import com.example.movieapi.data.OnItemClick

class MainMovieListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main_movie_list, container, false)

        val firstRecyclerView = view.findViewById<RecyclerView>(R.id.firstRecyclerView)
        val secondRecyclerView = view.findViewById<RecyclerView>(R.id.secondRecyclerView)
        val thirdRecyclerView = view.findViewById<RecyclerView>(R.id.thirdRecyclerView)

        val firstTextView = view.findViewById<TextView>(R.id.firstTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        val thirdTextView = view.findViewById<TextView>(R.id.thirdTextView)

        val firstSorting = "popular"
        val secondSorting = "top_rated"
        val thirdSorting = "now_playing"

        firstTextView.text = firstSorting.replace("_", " ")
        secondTextView.text = secondSorting.replace("_", " ")
        thirdTextView.text = thirdSorting.replace("_", " ")

        val firstMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val secondMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val thirdMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)

        val firstLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val secondLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val thirdLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        firstRecyclerView.adapter = firstMovieItemAdapter
        secondRecyclerView.adapter = secondMovieItemAdapter
        thirdRecyclerView.adapter = thirdMovieItemAdapter

        firstRecyclerView.layoutManager = firstLinearLayoutManager
        secondRecyclerView.layoutManager = secondLinearLayoutManager
        thirdRecyclerView.layoutManager = thirdLinearLayoutManager

        val viewModel = ViewModelProvider(this).get(MovieModel::class.java)

        viewModel.firstMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> firstMovieItemAdapter.submitList(data) })
        viewModel.secondMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> secondMovieItemAdapter.submitList(data) })
        viewModel.thirdMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> thirdMovieItemAdapter.submitList(data) })

        viewModel.getMovies(firstSorting, secondSorting, thirdSorting)

        return view
    }

    private var onClickListener = object : OnItemClick {
        override fun onClick(id: String?) {
            val bundle = Bundle()
            bundle.putString("ID", id)
            val fragment: Fragment = DescriptionMovieFragment()
            fragment.arguments = bundle
            val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}