package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

@Composable
fun BaseScreenTop(
    title: String = "",
    onNavigateBack: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    TopAppBar(
        title = title,
        onNavigationIconClick = onNavigateBack
    )
    Surface(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)

    ) {
        Box(
            Modifier
                .padding(8.dp)
                .zIndex(1f)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun BaseScreenTopPreview() = CatalogView {
    BaseScreenTop("Title") {
        Text("Sample")
    }
}
