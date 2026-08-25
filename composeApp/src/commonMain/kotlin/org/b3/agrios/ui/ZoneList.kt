package org.b3.agrios.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.b3.agrios.data.DummyData
import org.b3.agrios.model.MoistureStatus
import org.b3.agrios.model.Zone

@Composable
fun ZoneList(
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text("ゾーン一覧", modifier = Modifier.padding(bottom = 8.dp))
            DummyData.zones.forEach { zone ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            if (zone.id == selectedZone.id) Color(0xFFF4F7F8) else Color.Transparent
                        )
                        .clickable { onZoneSelected(zone) }
                        .padding(vertical = 7.dp),
                ) {
                    Text(zone.id, Modifier.weight(0.9f))
                    Text(zone.status.label, Modifier.weight(1.2f))
                    Text(zone.delta.toString(), Modifier.weight(0.8f))
                    Text(
                        if (zone.recommendation.name == "HIGH") "高" else "—",
                        color = if (zone.recommendation.name == "HIGH") Color(0xFFE23B32) else Color.Unspecified,
                    )
                }
            }
        }
    }
}
