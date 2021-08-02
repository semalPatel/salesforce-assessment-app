package com.salesforce.mivi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.data.MediaEntityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class SearchContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    val contentResult: MutableLiveData<Result<MediaEntityList>> = MutableLiveData()
    var loadingState: Boolean = false

    fun searchContent(query: String) {
        viewModelScope.launch {
            loadingState = true
            when (val result = contentRepository.getContentList(query)) {
                is Result.Success -> {
                    loadingState = false
                    contentResult.postValue(result)
                }
                is Result.Failure -> {
                    loadingState = false
                    contentResult.postValue(result)
                }
            }
        }
    }
}

enum class UiState {
    LOADING, SUCCESSFUL, FAILED
}