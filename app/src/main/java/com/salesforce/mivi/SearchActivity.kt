package com.salesforce.mivi

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.salesforce.mivi.databinding.ActivitySearchBinding
import com.salesforce.mivi.ui.SearchContentAdapter
import com.salesforce.mivi.viewmodel.SearchContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private lateinit var activitySearchBinding: ActivitySearchBinding

    private val mediaEntityViewModel: SearchContentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        setupSearchRecyclerView()
        observeContentResult()
    }

    private fun setupSearchRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        activitySearchBinding.contentList.layoutManager = layoutManager
    }

    private fun observeContentResult() {
        mediaEntityViewModel
            .contentResult
            .observe(this) { result ->
                when (result) {
                    is Result.Success -> {
                        val adapter = SearchContentAdapter(result.data.mediaEntities)
                        activitySearchBinding.contentList.adapter = adapter
                    }
                    is Result.Failure -> {

                    }
                }
            }
        mediaEntityViewModel.searchContent("Batman")
    }


    companion object {
        private val TAG = SearchActivity::class.simpleName
    }
}