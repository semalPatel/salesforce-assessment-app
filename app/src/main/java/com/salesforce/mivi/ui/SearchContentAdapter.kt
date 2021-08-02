package com.salesforce.mivi.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salesforce.mivi.Constants
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SearchContentAdapter(
    private val context: Context,
    private val source: Source
) : RecyclerView.Adapter<SearchContentViewHolder>(), OnContentClickListener {

    private val mediaEntityList = mutableListOf<SearchMediaEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentViewHolder {
        return SearchContentViewHolder(
            contentSearchResult = ContentSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onContentClickListener = this
        )
    }

    override fun onBindViewHolder(holder: SearchContentViewHolder, position: Int) {
        holder.update(mediaEntityList[position])
    }

    override fun getItemCount() = mediaEntityList.size

    override fun onRootClicked(position: Int) {
        val intent = Intent(
            context,
            DetailedActivity::class.java
        ).apply {
            putExtra(Constants.CONTENT_IMDB_ID, mediaEntityList[position].imdbId)
        }
        context.startActivity(intent)
    }

    override fun onFavoriteClicked(position: Int) {
        saveToPrefs(position)
        notifyItemChanged(position)
    }

    fun addData(data: List<SearchMediaEntity>) {
        mediaEntityList.clear()
        mediaEntityList.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    private fun saveToPrefs(position: Int) {
        val sharedPrefs = context.applicationContext.getSharedPreferences(
            Constants.FAVORITES_KEY,
            Context.MODE_PRIVATE
        )
        val id = mediaEntityList[position].imdbId
        val isFavorited = sharedPrefs.getString(id, null)
        if (isFavorited == null) {
            val jsonParser = Json { ignoreUnknownKeys = true }
            val savedEntity = jsonParser.encodeToString(mediaEntityList[position])
            with(sharedPrefs.edit()) {
                putString(id, savedEntity)
                apply()
            }
        } else {
            with(sharedPrefs.edit()) {
                remove(id)
                apply()
            }
            if (source == Source.FAVORITE) {
                mediaEntityList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    enum class Source {
        SEARCH, FAVORITE
    }
}

interface OnContentClickListener {
    fun onRootClicked(position: Int)
    fun onFavoriteClicked(position: Int)
}


