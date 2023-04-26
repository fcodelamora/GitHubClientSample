package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.AppDrawable
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentsColor: Color = MaterialTheme.colors.onPrimary,
    onNavigationIconClick: (() -> Unit)? = null
) {
    Column(modifier) {
        Surface(
            color = backgroundColor,
            content = {},
            modifier = modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
        )
        TopAppBar(
            backgroundColor = backgroundColor,
            title = {
                Text(
                    text = title,
                    color = contentsColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            elevation = 0.dp,
            navigationIcon = onNavigationIconClick?.let {
                {
                    IconButton(onClick = { it() }) {
                        Icon(
                            painter = painterResource(id = AppDrawable.ic_arrow_back),
                            tint = contentsColor,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun TopAppBarCatalogPreview() {
    CatalogView {
        TopAppBar(title = "TopAppBar")
        DefaultDivider()
    }
}
