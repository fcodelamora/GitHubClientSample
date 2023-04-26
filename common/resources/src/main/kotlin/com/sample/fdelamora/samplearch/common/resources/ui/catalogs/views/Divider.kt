package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.DummyText

// Divider Catalog

@Composable
fun DefaultDivider(modifier: Modifier = Modifier) = Divider(
    color = Color.Black,
    modifier = modifier.padding(
        PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    )
)

@Preview(showSystemUi = true)
@Composable
private fun DividerCatalogPreview() {
    CatalogView {
        DummyText()
        DefaultDivider()
    }
}
