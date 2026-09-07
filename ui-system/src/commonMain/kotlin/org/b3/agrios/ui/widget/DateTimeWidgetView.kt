package org.b3.agrios.ui.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.toLocalDateTime
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.view.View
import kotlin.time.Instant

class DateTimeWidgetView(
    private val instant: Instant,
    private val timeZone: TimeZone,
    private val format: DateTimeFormat<LocalDateTime>,
) : View {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")

    @Composable
    override fun onRender() {
        Text(
            text = instant
                .toLocalDateTime(timeZone)
                .format(format),
        )
    }
}
