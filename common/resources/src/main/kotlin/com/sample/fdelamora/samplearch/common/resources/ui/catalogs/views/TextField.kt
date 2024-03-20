package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.fdelamora.samplearch.common.resources.AppDrawable
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    text: String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier
        .background(color = MaterialTheme.colors.background)
        .height(IntrinsicSize.Min)
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        onValueChange = { newValue -> onValueChange(newValue) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(percent = 100),
        modifier = Modifier.wrapContentSize(),
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = placeholder, modifier = Modifier.alpha(0.8f)) },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearchClick()
            }
        )
    )
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.secondary.copy(alpha = 0.5f),
        modifier = modifier
            .clip(shape = RoundedCornerShape(percent = 100))
            .clickable { onSearchClick() }
    ) {
        Image(
            painter = painterResource(id = AppDrawable.search_web),
            contentDescription = "SEARCH",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .defaultMinSize(48.dp)
                .aspectRatio(1f)
                .height(IntrinsicSize.Min)
        )
    }
}

@Preview
@Composable
private fun TextFieldCatalogPreview() = CatalogView {
    SearchTextField()
    DefaultDivider()
    SearchTextField(placeholder = "Placeholder")
    DefaultDivider()
    SearchTextField(text = "Text")
}
