package com.salesforce.mivi.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.data.SearchMediaEntityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    val contentResult: MutableLiveData<Result<SearchMediaEntityList>> = MutableLiveData()
    var loadingState: ObservableBoolean = ObservableBoolean(false)

    fun searchContent(query: String) {
        viewModelScope.launch {
            loadingState.set(true)
            when (val result = contentRepository.getContentList(query)) {
                is Result.Success -> {
                    loadingState.set(false)
                    contentResult.postValue(result)
                    Log.d("Details", result.toString())
                }
                is Result.Failure -> {
                    loadingState.set(false)
                    contentResult.postValue(result)
                    Log.d("Details", result.toString())
                }
            }
        }
    }
}

enum class UiState {
    LOADING, SUCCESSFUL, FAILED
}