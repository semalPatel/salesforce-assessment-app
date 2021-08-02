package com.salesforce.mivi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.databinding.FragmentSearchBinding
import com.salesforce.mivi.util.Util
import com.salesforce.mivi.viewmodel.SearchContentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

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
                            source = SearchContentAdapter.Source.SEARCH
                        )
                        adapter.addData(result.data.mediaEntities)
                        fragmentContentListBinding.contentList.adapter = adapter
                    }
                    is Result.Failure -> {
                        Snackbar.make(
                            requireContext(),
                            fragmentContentListBinding.root,
                            "Unable to complete the request",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }
}