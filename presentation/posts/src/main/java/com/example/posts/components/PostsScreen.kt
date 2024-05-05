package com.example.posts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.posts.model.PostItem
import com.example.posts.viewmodels.PostViewModel

@Composable
fun PostsScreen(
    viewModel: PostViewModel = hiltViewModel()
) {
    val data = viewModel.data.collectAsState(emptyList())
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        data.value?.let { posts ->
            LazyColumn {
                items(posts) { post ->
                    PostItemScreen(post)
                }
            }
        }
    }

}

@Composable
fun PostItemScreen(item: PostItem) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Text(text = item.id.toString(), fontSize = 24.sp)
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(text = item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = item.body, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}