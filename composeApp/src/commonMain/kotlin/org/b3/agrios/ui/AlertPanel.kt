package org.b3.agrios.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.b3.agrios.data.DummyData
import org.b3.agrios.model.Severity

@Composable
fun AlertPanel(modifier: Modifier = Modifier) {
    Card(modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text("アラート", modifier = Modifier.padding(bottom = 8.dp))
            DummyData.alerts.forEach { alert ->
                val color = when (alert.severity) {
                    Severity.CRITICAL -> Color(0xFFE23B32)
                    Severity.WARNING -> Color(0xFFE98A15)
                    Severity.INFO -> Color(0xFF2377D8)
                }
                Column(Modifier.padding(vertical = 7.dp)) {
                    Text(alert.title, color = color)
                    Text(alert.message)
                    Text(alert.time, color = Color.Gray)
                }
            }
        }
    }
}
