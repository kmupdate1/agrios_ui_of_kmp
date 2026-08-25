package org.b3.agrios.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.b3.agrios.data.DummyData

@androidx.compose.runtime.Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    var selectedZone by remember { mutableStateOf(DummyData.zones[3]) }

    Row(modifier.background(Color(0xFFF5F7F9))) {
        Sidebar()

        Column(Modifier.fillMaxSize()) {
            TopBar()

            Row(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Column(
                    Modifier.weight(1.65f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    FarmMap(
                        selectedZone = selectedZone,
                        onZoneSelected = { selectedZone = it },
                        modifier = Modifier.weight(1.15f),
                    )
                    SoilMoistureChart(
                        zone = selectedZone,
                        values = DummyData.moistureHistory,
                        modifier = Modifier.weight(0.85f),
                    )
                }

                Column(
                    Modifier.weight(0.85f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    IrrigationRecommendation(selectedZone, Modifier.weight(0.95f))
                    ZoneList(
                        selectedZone = selectedZone,
                        onZoneSelected = { selectedZone = it },
                        modifier = Modifier.weight(1.05f),
                    )
                    AlertPanel(Modifier.weight(0.85f))
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun Sidebar() {
    val items = listOf(
        Icons.Default.Analytics to "ダッシュボード",
        Icons.Default.Map to "圃場マップ",
        Icons.Default.Tune to "エリア・ゾーン",
        Icons.Default.Sensors to "センサー",
        Icons.Default.WaterDrop to "灌水管理",
        Icons.Default.Memory to "機器制御",
        Icons.Default.Notifications to "アラート",
        Icons.Default.Assessment to "AIレポート",
        Icons.Default.ShowChart to "履歴・グラフ",
        Icons.Default.Settings to "設定",
    )

    Column(
        Modifier
            .width(205.dp)
            .fillMaxHeight()
            .background(Color(0xFF071C2C))
            .padding(12.dp),
    ) {
        Text(
            "🌿 AgrioS",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 18.dp),
        )

        items.forEachIndexed { index, (icon, label) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(
                        if (index == 0) Color(0xFF17344A) else Color.Transparent
                    )
                    .padding(10.dp, 9.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(icon, null, tint = Color.White, modifier = Modifier.width(22.dp))
                Spacer(Modifier.width(10.dp))
                Text(label, color = Color.White)
            }
        }

        Spacer(Modifier.weight(1f))

        Text("システムステータス", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("ゲートウェイ    オンライン", color = Color(0xFF9FE6AE))
        Text("センサー       24 / 26", color = Color.LightGray)
        Text("アクチュエータ  18 / 20", color = Color.LightGray)
        Text("クラウド接続    良好", color = Color(0xFF9FE6AE))
        Spacer(Modifier.height(12.dp))
    }
}
