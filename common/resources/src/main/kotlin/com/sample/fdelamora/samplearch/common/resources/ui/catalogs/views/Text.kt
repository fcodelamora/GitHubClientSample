package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

@Composable
fun Information1Text(
    modifier: Modifier = Modifier,
    text: String = ""
) = Text(
    text = "ⓘ  $text",
    color = MaterialTheme.colors.secondary,
    fontSize = 10.sp,
    textAlign = TextAlign.Center,
    modifier = modifier
        .fillMaxWidth()
        .padding(20.dp)
)

@Composable
@Preview(showSystemUi = true)
private fun TextCatalogPreview() = CatalogView {
    Information1Text(text = "Information1Text")
}
