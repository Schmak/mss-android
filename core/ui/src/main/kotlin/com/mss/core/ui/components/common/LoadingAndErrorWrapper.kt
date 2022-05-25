package com.mss.core.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.model.common.UiState
import com.mss.core.ui.model.landing.LandingUiState
import com.mss.core.ui.theme.AppTheme

@Composable
fun LoadingAndErrorWrapper(
    uiState: UiState,
    onRetry: () -> Unit = {},
    readyScreen: @Composable () -> Unit,
) {
    val errorMessageId = uiState.errorMessageId
    when {
        uiState.hasData -> readyScreen()
        uiState.isLoading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        errorMessageId != null ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(errorMessageId),
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(onClick = onRetry) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            }
    }
}

@MultiPreview
@Composable
fun PreviewLoading() {
    AppTheme {
        Surface {
            LoadingAndErrorWrapper(
                uiState = LandingUiState(isLoading = true, titleId = R.string.retry),
            ) {}
        }
    }
}

@MultiPreview
@Composable
fun PreviewError() {
    AppTheme {
        Surface {
            LoadingAndErrorWrapper(
                uiState = LandingUiState(
                    errorMessageId = R.string.try_again,
                    titleId = R.string.retry
                ),
            ) {}
        }
    }
}