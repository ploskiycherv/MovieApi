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

        val view = inflater.inflate(R.layout.fragment_main_movie_list, null)

        var firstRecyclerView: RecyclerView
        var secondRecyclerView: RecyclerView
        var thirdRecyclerView: RecyclerView
        var firstSorting: String
        var secondSorting: String
        var thirdSorting: String
        var firstTextView: TextView
        var secondTextView: TextView
        var thirdTextView: TextView

        firstRecyclerView = view.findViewById(R.id.firstRecyclerView)
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView)
        thirdRecyclerView = view.findViewById(R.id.thirdRecyclerView)
        firstTextView = view.findViewById(R.id.firstTextView)
        secondTextView = view.findViewById(R.id.secondTextView)
        thirdTextView = view.findViewById(R.id.thirdTextView)

        firstSorting = "popular"
        secondSorting = "top_rated"
        thirdSorting = "now_playing"

        firstTextView.setText(firstSorting!!.replace("_", " "))
        secondTextView.setText(secondSorting!!.replace("_", " "))
        thirdTextView.setText(thirdSorting!!.replace("_", " "))

        val firstMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val secondMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)
        val thirdMovieItemAdapter = MovieItemAdapter(MovieItemDiffUtil(), onClickListener)

        val firstLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val secondLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val thirdLinearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        firstRecyclerView.setAdapter(firstMovieItemAdapter)
        secondRecyclerView.setAdapter(secondMovieItemAdapter)
        thirdRecyclerView.setAdapter(thirdMovieItemAdapter)

        firstRecyclerView.setLayoutManager(firstLinearLayoutManager)
        secondRecyclerView.setLayoutManager(secondLinearLayoutManager)
        thirdRecyclerView.setLayoutManager(thirdLinearLayoutManager)

        val viewModel = ViewModelProvider(this).get(MovieModel::class.java)

        viewModel.firstMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> firstMovieItemAdapter.submitList(data) })
        viewModel.secondMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> secondMovieItemAdapter.submitList(data) })
        viewModel.thirdMovieItemLiveData().observe(viewLifecycleOwner, Observer { data -> thirdMovieItemAdapter.submitList(data) })

        viewModel.getMovies(firstSorting, secondSorting, thirdSorting)

        return view
    }

    var onClickListener = object : OnItemClick {
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