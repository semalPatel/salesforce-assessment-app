package com.salesforce.mivi.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.salesforce.mivi.Constants
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.databinding.FragmentFavoriteBinding
import com.salesforce.mivi.viewmodel.SearchContentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var fragmentContentListBinding: FragmentFavoriteBinding

    private val mediaEntityViewModel: SearchContentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentContentListBinding = FragmentFavoriteBinding.inflate(layoutInflater)
        setupList()
        handleFavoritedIds()
        return fragmentContentListBinding.root
    }

    private fun setupList() {
        val layoutManager = LinearLayoutManager(requireContext())
        fragmentContentListBinding.contentList.apply {
            this.layoutManager = layoutManager
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun handleFavoritedIds() {
        val sharedPrefs = requireContext().applicationContext.getSharedPreferences(
            Constants.FAVORITES_KEY,
            Context.MODE_PRIVATE
        )
        val jsonParser = Json { ignoreUnknownKeys = true }
        val mediaEntityList = mutableListOf<SearchMediaEntity>()
        for (entry: Map.Entry<String, Any?> in sharedPrefs.all) {
            val entity = jsonParser.decodeFromString<SearchMediaEntity>(entry.value as String)
            mediaEntityList.add(entity)
        }
        val adapter = SearchContentAdapter(
            context = this.requireContext(),
            contentResult = mediaEntityList
        )
        fragmentContentListBinding.contentList.adapter = adapter

    }
}