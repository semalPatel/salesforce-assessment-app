package com.salesforce.mivi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.databinding.ContentSearchResultBinding

class SearchContentAdapter(private val contentResult: List<MediaEntity>) : RecyclerView.Adapter<SearchContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContentViewHolder {
        return SearchContentViewHolder(
            ContentSearchResultBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
    }

    override fun onBindViewHolder(holder: SearchContentViewHolder, position: Int) {
        holder.update(contentResult[position])
    }

    override fun getItemCount() = contentResult.size
}

