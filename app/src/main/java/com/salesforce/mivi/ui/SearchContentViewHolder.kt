package com.salesforce.mivi.ui

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salesforce.mivi.Constants
import com.salesforce.mivi.R
import com.salesforce.mivi.data.SearchMediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentViewHolder(
    private val contentSearchResult: ContentSearchResultBinding,
    private val onContentClickListener: OnContentClickListener
) : RecyclerView.ViewHolder(contentSearchResult.root) {

    init {
        contentSearchResult.root.setOnClickListener { onContentClickListener.onRootClicked(layoutPosition) }
        contentSearchResult.favoriteBadge.setOnClickListener { onContentClickListener.onFavoriteClicked(layoutPosition) }
    }

    fun update(mediaEntity: SearchMediaEntity) {
        val newLayoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        contentSearchResult.root.layoutParams = newLayoutParams
        contentSearchResult.title.text = mediaEntity.title
        contentSearchResult.typeAndYear.text = "${mediaEntity.type} (${mediaEntity.year})"
        Glide
            .with(contentSearchResult.root.context)
            .load(mediaEntity.posterUrl)
            .centerCrop()
            .into(contentSearchResult.poster)
        toggleFavoriteBadge(mediaEntity)
    }

    private fun toggleFavoriteBadge(mediaEntity: SearchMediaEntity) {
        val context = contentSearchResult.root.context.applicationContext
        val sharedPrefs = context.getSharedPreferences(
                Constants.FAVORITES_KEY,
                Context.MODE_PRIVATE
        )
        val existingFavorites = sharedPrefs.getStringSet(Constants.FAVORITES_KEY, emptySet())
        existingFavorites?.let {
            if (it.contains(mediaEntity.imdbId)) {
                contentSearchResult.favoriteBadge.setImageResource(R.drawable.ic_star_selected)
            } else {
                contentSearchResult.favoriteBadge.setImageResource(R.drawable.ic_star_unselected)
            }
        }
    }
}