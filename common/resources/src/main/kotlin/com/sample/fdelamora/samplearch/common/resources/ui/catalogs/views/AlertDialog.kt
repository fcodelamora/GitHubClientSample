package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sample.fdelamora.samplearch.common.resources.ui.catalogs.CatalogView
import com.sample.fdelamora.samplearch.core.entities.ErrorViewData

// AlertDialogs

@Composable
fun ErrorDialog(
    errorViewData: ErrorViewData,
    onOkPressed: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = { errorViewData.title?.let { Text(it) } },
        text = { Text(errorViewData.errorMessage) },
        confirmButton = {
            TextButton(
                onClick = {
                    onOkPressed.invoke()
                }
            ) {
                Text("OK", color = MaterialTheme.colors.secondary)
            }
        }
    )
}

@Composable
fun SimpleAlertDialog(
    title: String?,
    message: String,
    onConfirmPressed: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = title?.let { { Text(it) } },
        text = { Text(message) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmPressed.invoke()
                }
            ) {
                Text("OK", color = MaterialTheme.colors.secondary)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                }
            ) {
                Text("CANCEL", color = MaterialTheme.colors.secondary)
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorDialogPreview() {
    CatalogView {
        ErrorDialog(ErrorViewData("title", "msg", "1234")) {}
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SimpleAlertDialogDPreview() {
    CatalogView {
        SimpleAlertDialog(title = "title", message = "msg", onConfirmPressed = {}, onDismiss = {})
    }
}
