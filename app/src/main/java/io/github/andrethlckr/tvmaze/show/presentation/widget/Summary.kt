package io.github.andrethlckr.tvmaze.show.presentation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import io.github.andrethlckr.tvmaze.R

@Composable
fun Summary(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.summary),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT).toString(),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
        )
    }
}
