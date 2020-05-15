package com.smsrn.movieshowcase.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.smsrn.movieshowcase.R
import com.smsrn.movieshowcase.adapter.LastAdapter
import com.smsrn.movieshowcase.databinding.ActivityMainBinding
import com.smsrn.movieshowcase.extensions.gotoActivity
import com.smsrn.movieshowcase.extensions.obtainViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.ui.BaseActivity
import com.smsrn.movieshowcase.util.Constants.IntentKeys.MOVIE_DETAIL_OBJECT
import com.smsrn.movieshowcase.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var lastAdapter: LastAdapter<MovieDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = obtainViewModel(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this

        setAdapter()

        binding.viewModel?.getMoviesList()
    }

    /**
     * Set Adapter For Movies Summary Collection
     */
    private fun setAdapter() {
        lastAdapter = LastAdapter(R.layout.item_movie_summary, object : LastAdapter.OnItemClickListener<MovieDetails> {
            override fun onItemClick(item: MovieDetails) {
                gotoActivity(MovieDetailsActivity::class.java, MOVIE_DETAIL_OBJECT, item)
            }
        })
        recyclerViewMoviesCollection.adapter = lastAdapter
    }
}
