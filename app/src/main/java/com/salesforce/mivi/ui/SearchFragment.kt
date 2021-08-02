package com.salesforce.mivi.ui

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.salesforce.mivi.R
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.databinding.FragmentSearchBinding
import com.salesforce.mivi.util.Util
import com.salesforce.mivi.viewmodel.SearchContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var fragmentContentListBinding: FragmentSearchBinding

    private val mediaEntityViewModel: SearchContentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentContentListBinding = FragmentSearchBinding.inflate(layoutInflater)
        setupSearch()
        observeContentResult()
        return fragmentContentListBinding.root
    }

    private fun setupSearch() {
        /*fragmentContentListBinding.searchContent.apply {
            isFocusable = true
            isClickable = true
            clearFocus()
        }*/
        fragmentContentListBinding.searchContent.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mediaEntityViewModel.searchContent(v.text.toString())
                mediaEntityViewModel.loadingState.set(true)
                Util.hideKeyboard(requireContext(), v)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        val layoutManager = LinearLayoutManager(requireContext())
        fragmentContentListBinding.contentList.apply {
            this.layoutManager = layoutManager
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchFragment.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun observeContentResult() {
        mediaEntityViewModel
            .contentResult
            .observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Success -> {
                        val adapter = SearchContentAdapter(
                            context = this.requireContext(),
                            contentResult = result.data.mediaEntities
                        )
                        fragmentContentListBinding.contentList.adapter = adapter
                    }
                    is Result.Failure -> {

                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = android.R.attr.maxWidth
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        mediaEntityViewModel.searchContent(query)
        return true
    }

    override fun onQueryTextChange(newText: String?) = false
}