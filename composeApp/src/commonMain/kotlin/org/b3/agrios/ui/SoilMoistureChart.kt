package org.b3.agrios.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import org.b3.agrios.model.Zone

@Composable
fun SoilMoistureChart(
    zone: Zone,
    values: List<Int>,
    modifier: Modifier = Modifier,
) {
    Card(modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text("土壌水分の推移（${zone.id} ゾーン）")
            Canvas(
                Modifier.fillMaxWidth().height(190.dp).padding(top = 12.dp)
            ) {
                val min = 1000f
                val max = 3000f
                val stepX = size.width / (values.size - 1)
                val path = Path()

                values.forEachIndexed { index, value ->
                    val x = index * stepX
                    val y = size.height - ((value - min) / (max - min)).coerceIn(0f, 1f) * size.height
                    if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
                }

                drawLine(
                    Color(0xFFFFB020),
                    Offset(0f, size.height * 0.4f),
                    Offset(size.width, size.height * 0.4f),
                    strokeWidth = 1.5f,
                )
                drawPath(path, Color(0xFFFF7A18), style = androidx.compose.ui.graphics.drawscope.Stroke(3f))

                values.forEachIndexed { index, value ->
                    val x = index * stepX
                    val y = size.height - ((value - min) / (max - min)).coerceIn(0f, 1f) * size.height
                    drawCircle(Color(0xFFFF7A18), 4f, Offset(x, y))
                }
            }
        }
    }
}
