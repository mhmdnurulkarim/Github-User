package com.mhmdnurulkarim.githubuser.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ){
        AsyncImage(
            model = "https://4.bp.blogspot.com/-WHY8yd-k5J8/WirwiGJiQmI/AAAAAAAAAWI/G0njZKb3_YwE9jHpN7__X3zlTOguWx7pQCLcBGAs/s1600/Gambar-Kucing-Gemuk-Lucu.jpg",
            contentDescription = null
        )
        Text(text = "Muhammad Nurul Karim")
        Text(text = "mhmdnurulkarim@gmail.com")
    }
}