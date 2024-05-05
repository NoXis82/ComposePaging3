package com.example.posts.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.example.domain.RequestResult
import com.example.posts.model.PostItem
import com.example.posts.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: Provider<PostRepository>,
    pager: Pager<Int, PostItem>
) : ViewModel() {

    private val _data = MutableStateFlow<List<PostItem>?>(emptyList())
    val data: StateFlow<List<PostItem>?> get() = _data.asStateFlow()

    val postPagingFlow = pager.flow.cachedIn(viewModelScope)
    init {
        //getAllPosts()
    }


    private fun getAllPosts() {
        viewModelScope.launch {
            postRepository.get().getAllPosts().onStart {
                //TODO
            }.collect { result ->
                when (result) {
                    is RequestResult.Error -> {
                        Log.d(TAG, "getAllPosts: error")
                    }

                    is RequestResult.Success -> {
                        _data.value = result.data
                    }
                }
            }

        }
    }

    companion object {
        private const val TAG = "PostViewModel"
    }

}