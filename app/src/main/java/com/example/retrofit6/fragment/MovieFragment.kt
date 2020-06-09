package com.example.retrofit6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
//import com.example.retrofit6.MovieFragmentDirections
import com.example.retrofit6.R
import com.example.retrofit6.adapter.MoviesAdapter
import com.example.retrofit6.model.Result
import com.example.retrofit6.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), MoviesAdapter.ClickListener {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        moviesAdapter = MoviesAdapter()
        moviesAdapter.setClick(this)
        recyclerMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }
        observedViewModel()             //get
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.loadResults()           //load or set
    }

    private fun observedViewModel() {

        moviesViewModel = ViewModelProvider(this)
            .get(MoviesViewModel::class.java)


        moviesViewModel.movieList.observe(viewLifecycleOwner,
            Observer { myResult ->
                moviesAdapter.updateResultList(myResult.results)
            })


    }

    override fun onClick(result: Result) {

        Toast.makeText(context, "${result.title}", Toast.LENGTH_LONG).show()
        var movieId = result.id
        var action =
            MovieFragmentDirections.actionMoviesFragmentToDetailFragment(
                movieId
            )
        findNavController().navigate(action)
    }
}
