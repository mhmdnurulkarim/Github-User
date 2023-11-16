package com.mhmdnurulkarim.githubuser.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OptionMenu(
    navigateToFavorite: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (modifier = modifier) {
        IconButton(onClick = navigateToFavorite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite_page"
            )
        }
        IconButton(onClick = navigateToFavorite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite_page"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OptionMenuPreview() {
    MaterialTheme {
        OptionMenu(navigateToFavorite = { /*TODO*/ }, navigateToProfile = { /*TODO*/ })
    }
}