package com.salesforce.mivi.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.salesforce.mivi.R
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.databinding.ActivitySearchBinding
import com.salesforce.mivi.viewmodel.SearchContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var activitySearchBinding: ActivitySearchBinding

    private val mediaEntityViewModel: SearchContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupToolbar()
        setupSearchRecyclerView()
        observeContentResult()
    }

    private fun setupBinding() {
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        activitySearchBinding.model = mediaEntityViewModel
    }

    private fun setupSearchRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        activitySearchBinding.contentList.apply {
            this.layoutManager = layoutManager
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(activitySearchBinding.mainAppToolbar)
    }

    private fun observeContentResult() {
        mediaEntityViewModel
            .contentResult
            .observe(this) { result ->
                when (result) {
                    is Result.Success -> {
                        val adapter = SearchContentAdapter(
                            context = this,
                            contentResult = result.data.mediaEntities
                        )
                        activitySearchBinding.contentList.adapter = adapter
                    }
                    is Result.Failure -> {

                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = android.R.attr.maxWidth
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mediaEntityViewModel.searchContent(query)
        return true
    }

    override fun onQueryTextChange(newText: String?) = false

    companion object {
        private val TAG = SearchActivity::class.simpleName
    }
}