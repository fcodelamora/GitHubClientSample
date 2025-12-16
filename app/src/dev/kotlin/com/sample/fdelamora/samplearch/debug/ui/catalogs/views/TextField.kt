package com.sample.fdelamora.samplearch.debug.ui.catalogs.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

// dev-only TextFields

@Composable
fun NumericTextField(
    value: String,
    onValueChange: (String) -> Unit
) = OutlinedTextField(
    value = value,
    onValueChange = { newValue -> onValueChange.invoke(newValue) },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
)

@Preview
@Composable
private fun TextFieldCatalogPreview() {
    CatalogView {
        NumericTextField("text") {}
    }
}
