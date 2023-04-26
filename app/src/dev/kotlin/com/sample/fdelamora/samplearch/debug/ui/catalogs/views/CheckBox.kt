package com.sample.fdelamora.samplearch.debug.ui.catalogs.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

@Composable
fun DebugCheckBox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Text(text = text)
    }
}

@Preview
@Composable
private fun CheckBoxCatalogPreview() {
    CatalogView {
        DebugCheckBox("DebugCheckBox", true) {}
        DebugCheckBox("DebugCheckBox", false) {}
    }
}
