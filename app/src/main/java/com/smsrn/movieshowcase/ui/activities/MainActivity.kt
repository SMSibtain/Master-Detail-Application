package com.smsrn.movieshowcase.ui.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.smsrn.movieshowcase.R
import com.smsrn.movieshowcase.adapter.LastAdapter
import com.smsrn.movieshowcase.databinding.ActivityMainBinding
import com.smsrn.movieshowcase.extensions.gotoActivity
import com.smsrn.movieshowcase.extensions.obtainViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.ui.BaseActivity
import com.smsrn.movieshowcase.util.Constants
import com.smsrn.movieshowcase.util.Constants.IntentKeys.MOVIE_DETAIL_OBJECT
import com.smsrn.movieshowcase.util.Constants.Sortings.ASCENDING
import com.smsrn.movieshowcase.util.Constants.Sortings.DESCENDING
import com.smsrn.movieshowcase.util.Constants.Sortings.RESET
import com.smsrn.movieshowcase.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.lang3.StringUtils

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var lastAdapter: LastAdapter<MovieDetails>
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = obtainViewModel(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this

        setAdapter()
        binding.viewModel?.getMoviesList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_select_order, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                binding.viewModel?.SEARCH_VIEW_FILTER_VALUE = newText
                if (lastAdapter.items.size > Constants.DIGIT_ZERO)
                    lastAdapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                menu.findItem(R.id.action_search).collapseActionView()
            }
        }

        menu.findItem(R.id.action_search)
            .setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean = true

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    invalidateOptionsMenu()
                    return true
                }
            })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.order_ascending -> {
                if (binding.viewModel?.SEARCH_SORTING_ORDER != ASCENDING) {
                    binding.viewModel?.SEARCH_SORTING_ORDER = ASCENDING
                    lastAdapter.items = ArrayList(lastAdapter.items.sortedBy { it.year })
                    if (binding.viewModel?.SEARCH_VIEW_FILTER_VALUE.toString().isNotEmpty()) {
                        lastAdapter.filter.filter(binding.viewModel?.SEARCH_VIEW_FILTER_VALUE)
                    }
                }
                true
            }
            R.id.order_descending -> {
                if (binding.viewModel?.SEARCH_SORTING_ORDER != DESCENDING) {
                    binding.viewModel?.SEARCH_SORTING_ORDER = DESCENDING
                    lastAdapter.items = ArrayList(lastAdapter.items.sortedByDescending { it.year })
                    if (binding.viewModel?.SEARCH_VIEW_FILTER_VALUE.toString().isNotEmpty()) {
                        lastAdapter.filter.filter(binding.viewModel?.SEARCH_VIEW_FILTER_VALUE)
                    }
                }
                true
            }
            R.id.reset -> {
                binding.viewModel?.SEARCH_SORTING_ORDER = RESET
                searchView.clearFocus();
                binding.viewModel?.setOrResetList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
