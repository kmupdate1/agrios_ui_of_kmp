package org.b3.agrios.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.b3.agrios.data.DummyData
import org.b3.agrios.model.MoistureStatus
import org.b3.agrios.model.Zone

@Composable
fun FarmMap(
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.background(Color(0xFF8BA37A))) {
        Canvas(
            Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            // Stylized satellite-like background.
            repeat(7) { i ->
                drawRect(
                    color = if (i % 2 == 0) Color(0xFF76936C) else Color(0xFF8FA77C),
                    topLeft = Offset(0f, i * size.height / 7f),
                    size = androidx.compose.ui.geometry.Size(size.width, size.height / 7f)
                )
            }

            val polygons = listOf(
                Rect(0.10f * size.width, 0.15f * size.height, 0.28f * size.width, 0.47f * size.height),
                Rect(0.31f * size.width, 0.15f * size.height, 0.49f * size.width, 0.47f * size.height),
                Rect(0.52f * size.width, 0.15f * size.height, 0.67f * size.width, 0.51f * size.height),
                Rect(0.69f * size.width, 0.15f * size.width, 0.88f * size.width, 0.47f * size.height),
                Rect(0.10f * size.width, 0.53f * size.height, 0.45f * size.width, 0.88f * size.height),
                Rect(0.50f * size.width, 0.54f * size.height, 0.82f * size.width, 0.90f * size.height),
            )

            DummyData.zones.forEachIndexed { index, zone ->
                val r = polygons[index]
                val path = Path().apply {
                    moveTo(r.left, r.top)
                    lineTo(r.right, r.top)
                    lineTo(r.right, r.bottom)
                    lineTo(r.left, r.bottom)
                    close()
                }
                val fill = when (zone.status) {
                    MoistureStatus.OPTIMAL -> Color(0x6635C759)
                    MoistureStatus.LOW -> Color(0x66FFD33D)
                    MoistureStatus.DRY -> Color(0x66FF8A22)
                    MoistureStatus.VERY_DRY -> Color(0x66F04438)
                }
                drawPath(path, fill, style = Fill)
                drawPath(
                    path,
                    if (zone.id == selectedZone.id) Color.White else Color(0xAAFFFFFF),
                    style = Stroke(width = if (zone.id == selectedZone.id) 4f else 2f),
                )
            }
        }

        Text(
            "航空写真  •  2025/05/24 14:30",
            modifier = Modifier.align(Alignment.TopStart).padding(12.dp),
            color = Color.White,
        )

        ZoneOverlay(selectedZone, onZoneSelected)
    }
}

@Composable
private fun ZoneOverlay(
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
) {
    // Compact interaction layer; real geometry can later move to a map abstraction.
    androidx.compose.foundation.layout.Column(
        Modifier.fillMaxSize().padding(34.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceAround,
    ) {
        androidx.compose.foundation.layout.Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceAround,
        ) {
            ZoneChip(
                DummyData.zones[0],
                selectedZone,
                onZoneSelected
            )
            ZoneChip(
                DummyData.zones[1],
                selectedZone,
                onZoneSelected
            )
            ZoneChip(
                DummyData.zones[2],
                selectedZone,
                onZoneSelected
            )
            ZoneChip(
                DummyData.zones[3],
                selectedZone,
                onZoneSelected
            )
        }
        androidx.compose.foundation.layout.Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceAround,
        ) {
            ZoneChip(
                DummyData.zones[4],
                selectedZone,
                onZoneSelected
            )
            ZoneChip(
                DummyData.zones[5],
                selectedZone,
                onZoneSelected
            )
        }
    }
}

@Composable
private fun ZoneChip(
    zone: Zone,
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
) {
    Box(
        Modifier
            .background(Color.White.copy(alpha = if (zone.id == selectedZone.id) 0.95f else 0.82f))
            .clickable { onZoneSelected(zone) }
            .padding(horizontal = 10.dp, vertical = 7.dp)
    ) {
        Text("${zone.id}\n${zone.delta}")
    }
}
