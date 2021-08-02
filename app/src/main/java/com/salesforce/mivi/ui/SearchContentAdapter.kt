package com.salesforce.mivi.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salesforce.mivi.Constants
import com.salesforce.mivi.R
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentAdapter(
    private val context: Context,
    private val contentResult: List<SearchMediaEntity>,
) : RecyclerView.Adapter<SearchContentViewHolder>(), OnContentClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentViewHolder {
        return SearchContentViewHolder(
            contentSearchResult = ContentSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onContentClickListener = this
        )
    }

    override fun onBindViewHolder(holder: SearchContentViewHolder, position: Int) {
        holder.update(contentResult[position])
    }

    override fun getItemCount() = contentResult.size

    override fun onRootClicked(position: Int) {
        val intent = Intent(
            context,
            DetailedActivity::class.java
        ).apply {
            putExtra(Constants.CONTENT_IMDB_ID, contentResult[position].imdbId)
        }
        context.startActivity(intent)
    }

    override fun onFavoriteClicked(position: Int) {
        saveToPrefs(position)
        notifyItemChanged(position)
    }

    private fun saveToPrefs(position: Int) {
        val sharedPrefs = context.applicationContext.getSharedPreferences(
            Constants.FAVORITES_KEY,
            Context.MODE_PRIVATE
        )
        val id = contentResult[position].imdbId
        val existingFavorites = sharedPrefs.getStringSet(Constants.FAVORITES_KEY, emptySet())
        val newSet = existingFavorites?.let {
            if (it.contains(id)) {
                existingFavorites.minus(id)
            } else {
                existingFavorites.plus(id)
            }
        } ?: emptySet()
        with(sharedPrefs.edit()) {
            putStringSet(Constants.FAVORITES_KEY, newSet)
            apply()
        }
    }
}

interface OnContentClickListener {
    fun onRootClicked(position: Int)
    fun onFavoriteClicked(position: Int)
}


