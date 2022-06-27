package io.github.andrethlckr.tvmaze.show.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.show.domain.Schedule

@Composable
fun ScheduleRow(schedule: Schedule) {
    if (schedule.days.isEmpty() && schedule.time.isBlank()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                color = MaterialTheme.colors.surface.copy(alpha = 0.1f)
            )
            .padding(all = 8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.schedule),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h3,
        )

        if (schedule.days.isNotEmpty()) {
            Text(
                text = schedule.days.joinToString { it.value },
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }

        if (schedule.time.isNotBlank()) {
            Row {
                Text(
                    text = stringResource(id = R.string.airs_at),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    text = " ${schedule.time}",
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
