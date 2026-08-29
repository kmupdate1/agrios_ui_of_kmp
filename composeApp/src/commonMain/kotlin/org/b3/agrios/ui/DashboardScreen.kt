package org.b3.agrios.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.b3.agrios.model.*
import org.b3.agrios.ui.dashboard.DashboardController
import org.b3.agrios.ui.dashboard.DashboardEvent
import org.b3.agrios.ui.dashboard.DashboardNavigationItem
import org.b3.agrios.ui.dashboard.IrrigationStatus

@Composable
fun DashboardScreen(
    controller: DashboardController,
    modifier: Modifier = Modifier,
) {
    val state by controller.state
    val snapshot = state.snapshot
    val palette = if (state.isDarkMode) DashboardPalette.dark else DashboardPalette.light

    Row(modifier.fillMaxSize().background(palette.background)) {
        DashboardSidebar(
            palette = palette,
            selectedItem = state.selectedNavigation,
            onItemSelected = { controller.onEvent(DashboardEvent.SelectNavigation(it)) },
        )

        Column(Modifier.fillMaxSize()) {
            DashboardHeader(
                palette = palette,
                farmName = snapshot.farmName,
                lastSyncedAt = snapshot.lastSyncedAt,
                isDarkMode = state.isDarkMode,
                onToggleTheme = { controller.onEvent(DashboardEvent.ToggleTheme) },
            )

            DashboardBody(
                palette = palette,
                snapshot = snapshot,
                selectedZone = state.selectedZone,
                irrigationStatus = state.selectedIrrigationStatus,
                runningIrrigationZoneId = state.runningIrrigationZoneId,
                acknowledgedAlertIds = state.acknowledgedAlertIds,
                onEvent = controller::onEvent,
            )
        }
    }
}

@Composable
private fun DashboardBody(
    palette: DashboardPalette,
    snapshot: DashboardSnapshot,
    selectedZone: Zone?,
    irrigationStatus: IrrigationStatus,
    runningIrrigationZoneId: String?,
    acknowledgedAlertIds: Set<String>,
    onEvent: (DashboardEvent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DashboardTitle(palette)
        if (selectedZone == null) {
            EmptyDashboardState(palette, "表示できるゾーンデータがありません")
        } else {
            Row(
                Modifier.fillMaxWidth().height(350.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                FarmOverviewCard(
                    palette = palette,
                    farmName = snapshot.farmName,
                    areaHectares = snapshot.areaHectares,
                    location = snapshot.location,
                    zones = snapshot.zones,
                    selectedZone = selectedZone,
                    onZoneSelected = { onEvent(DashboardEvent.SelectZone(it.id)) },
                    modifier = Modifier.weight(1.65f),
                )
                IrrigationCard(
                    palette = palette,
                    zone = selectedZone,
                    state = irrigationStatus,
                    runningIrrigationZoneId = runningIrrigationZoneId,
                    onExecute = { onEvent(DashboardEvent.ExecuteIrrigation) },
                    onDefer = { onEvent(DashboardEvent.DeferIrrigation) },
                    modifier = Modifier.weight(0.85f),
                )
            }

            Row(
                Modifier.fillMaxWidth().height(270.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ZoneStatusCard(
                    palette = palette,
                    zones = snapshot.zones,
                    selectedZone = selectedZone,
                    onZoneSelected = { onEvent(DashboardEvent.SelectZone(it.id)) },
                    modifier = Modifier.weight(1.1f),
                )
                AlertStatusCard(
                    palette = palette,
                    alerts = snapshot.alerts,
                    acknowledgedAlertIds = acknowledgedAlertIds,
                    onAcknowledge = { onEvent(DashboardEvent.AcknowledgeAlert(it)) },
                    modifier = Modifier.weight(0.9f),
                )
            }

            Row(
                Modifier.fillMaxWidth().height(250.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MoistureTrendCard(
                    palette = palette,
                    selectedZone = selectedZone,
                    moistureHistory = snapshot.moistureHistory,
                    modifier = Modifier.weight(1.1f),
                )
                WeatherCard(snapshot.weather, palette, modifier = Modifier.weight(0.9f))
            }

            WorkLogCard(snapshot.workLogs, palette)
        }
    }
}

@Composable
private fun EmptyDashboardState(palette: DashboardPalette, message: String) {
    Card(
        Modifier.fillMaxWidth().height(180.dp),
        colors = CardDefaults.cardColors(containerColor = palette.surface),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(message, color = palette.muted, fontSize = 13.sp)
        }
    }
}

@Composable
private fun DashboardSidebar(
    palette: DashboardPalette,
    selectedItem: DashboardNavigationItem,
    onItemSelected: (DashboardNavigationItem) -> Unit,
) {
    val items = listOf(
        DashboardNavigationItem.DASHBOARD to (Icons.Default.Analytics to "ダッシュボード"),
        DashboardNavigationItem.FARM_MAP to (Icons.Default.Map to "圃場マップ"),
        DashboardNavigationItem.ZONES to (Icons.Default.Tune to "エリア・ゾーン"),
        DashboardNavigationItem.SENSORS to (Icons.Default.Sensors to "センサー"),
        DashboardNavigationItem.IRRIGATION to (Icons.Default.WaterDrop to "灌水管理"),
        DashboardNavigationItem.EQUIPMENT to (Icons.Default.Memory to "機器制御"),
        DashboardNavigationItem.ALERTS to (Icons.Default.Notifications to "アラート"),
        DashboardNavigationItem.AI_REPORTS to (Icons.Default.Assessment to "AIレポート"),
        DashboardNavigationItem.HISTORY to (Icons.Default.ShowChart to "履歴・グラフ"),
        DashboardNavigationItem.SETTINGS to (Icons.Default.Settings to "設定"),
    )

    Column(
        Modifier
            .width(232.dp)
            .fillMaxHeight()
            .background(palette.sidebar)
            .padding(horizontal = 14.dp, vertical = 18.dp),
    ) {
        Row(
            Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier.size(34.dp).background(palette.accent, MaterialTheme.shapes.small),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Default.EnergySavingsLeaf, null, tint = Color.White)
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text("AgriOS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("SMART FARMING", color = palette.sidebarMuted, fontSize = 9.sp, letterSpacing = 1.sp)
            }
        }

        Text("OPERATIONS", color = palette.sidebarMuted, fontSize = 10.sp, letterSpacing = 1.3.sp,
            modifier = Modifier.padding(start = 12.dp, top = 28.dp, bottom = 8.dp))
        items.forEach { (item, iconAndLabel) ->
            val (icon, label) = iconAndLabel
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(if (item == selectedItem) palette.sidebarSelected else Color.Transparent,
                        MaterialTheme.shapes.small)
                    .clickable { onItemSelected(item) }
                    .padding(horizontal = 12.dp, vertical = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(icon, null, tint = if (item == selectedItem) palette.accent else palette.sidebarMuted,
                    modifier = Modifier.size(19.dp))
                Spacer(Modifier.width(10.dp))
                Text(label, color = if (item == selectedItem) Color.White else palette.sidebarText,
                    fontSize = 13.sp, fontWeight = if (item == selectedItem) FontWeight.SemiBold else FontWeight.Normal)
            }
        }

        Spacer(Modifier.weight(1f))

        Card(colors = CardDefaults.cardColors(containerColor = palette.sidebarCard),
            shape = MaterialTheme.shapes.medium) {
            Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("SYSTEM STATUS", color = palette.sidebarMuted, fontSize = 10.sp, letterSpacing = 1.sp)
                StatusLine("ゲートウェイ", "オンライン", palette.success, palette)
                StatusLine("センサー", "24 / 26", palette.sidebarText, palette)
                StatusLine("アクチュエータ", "18 / 20", palette.sidebarText, palette)
                StatusLine("クラウド接続", "良好", palette.success, palette)
            }
        }
        Text("v0.1.0 • Connected", color = palette.sidebarMuted, fontSize = 10.sp,
            modifier = Modifier.padding(start = 4.dp, top = 12.dp))
    }
}

@Composable
private fun StatusLine(label: String, value: String, valueColor: Color, palette: DashboardPalette) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = palette.sidebarText, fontSize = 10.sp)
        Text(value, color = valueColor, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun DashboardHeader(
    palette: DashboardPalette,
    farmName: String,
    lastSyncedAt: String,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().background(palette.surface).padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(farmName, color = palette.text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("最終同期 $lastSyncedAt", color = palette.muted, fontSize = 10.sp)
        }
        Metric(Icons.Default.Thermostat, "26.3°C", "気温", palette)
        Metric(Icons.Default.Opacity, "58%", "湿度", palette)
        Metric(Icons.Default.WbSunny, "652 W/m²", "日射", palette)
        Spacer(Modifier.width(14.dp))
        IconButton(onClick = onToggleTheme) {
            Icon(if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = "テーマ切替", tint = palette.muted)
        }
        Box(Modifier.size(34.dp).background(palette.accent, MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center) {
            Text("山", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun Metric(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String, label: String, palette: DashboardPalette) {
    Row(Modifier.padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = palette.accent, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(5.dp))
        Column {
            Text(value, color = palette.text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text(label, color = palette.muted, fontSize = 9.sp)
        }
    }
}

@Composable
private fun DashboardTitle(palette: DashboardPalette) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
        Column(Modifier.weight(1f)) {
            Text("圃場ダッシュボード", color = palette.text, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text("センサーとAIが支える、今日の圃場コンディション", color = palette.muted, fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp))
        }
        Row(Modifier.background(palette.success.copy(alpha = 0.12f), MaterialTheme.shapes.small)
            .padding(horizontal = 10.dp, vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(7.dp).background(palette.success, MaterialTheme.shapes.small))
            Spacer(Modifier.width(6.dp))
            Text("全システム正常", color = palette.success, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun FarmOverviewCard(
    palette: DashboardPalette,
    farmName: String,
    areaHectares: Double,
    location: String,
    zones: List<Zone>,
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("圃場マップ", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("$farmName • $areaHectares ha", color = palette.muted, fontSize = 10.sp)
                }
                Row(Modifier.border(1.dp, palette.border, MaterialTheme.shapes.small)
                    .padding(horizontal = 9.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = palette.accent, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(location, color = palette.muted, fontSize = 10.sp)
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = palette.muted, modifier = Modifier.size(15.dp))
                }
            }
            FarmMapCanvas(palette, zones, selectedZone, onZoneSelected, Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun FarmMapCanvas(
    palette: DashboardPalette,
    zones: List<Zone>,
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
    modifier: Modifier,
) {
    Box(modifier.padding(horizontal = 12.dp, vertical = 4.dp).background(Color(0xFF718B69), MaterialTheme.shapes.small)) {
        Canvas(Modifier.fillMaxSize()) {
            drawRect(Color(0xFF78916D))
            for (i in 0..8) {
                drawLine(Color(0x26738A61), Offset(0f, i * size.height / 8f),
                    Offset(size.width, i * size.height / 8f + 18f), strokeWidth = 18f)
            }
            drawLine(Color(0x998B9B82), Offset(size.width * .03f, size.height * .92f),
                Offset(size.width * .92f, size.height * .04f), strokeWidth = 7f)
            val positions = listOf(
                listOf(.08f, .12f, .27f, .45f), listOf(.30f, .12f, .49f, .45f),
                listOf(.52f, .10f, .68f, .43f), listOf(.70f, .11f, .91f, .44f),
                listOf(.08f, .53f, .45f, .88f), listOf(.49f, .52f, .84f, .89f),
            )
            zones.take(positions.size).forEachIndexed { index, zone ->
                val p = positions[index]
                val path = Path().apply {
                    moveTo(p[0] * size.width, p[1] * size.height)
                    lineTo(p[2] * size.width, p[1] * size.height + 4f)
                    lineTo(p[2] * size.width - 5f, p[3] * size.height)
                    lineTo(p[0] * size.width + 5f, p[3] * size.height - 3f)
                    close()
                }
                drawPath(path, zoneColor(zone.status).copy(alpha = .52f), style = Fill)
                drawPath(path, if (zone.id == selectedZone.id) Color.White else Color.White.copy(alpha = .65f),
                    style = Stroke(if (zone.id == selectedZone.id) 3.5f else 1.5f))
            }
        }
        Text("航空写真 • LIVE", color = Color.White, fontSize = 9.sp,
            modifier = Modifier.align(Alignment.TopStart).padding(10.dp))
        Column(Modifier.fillMaxSize().padding(horizontal = 18.dp, vertical = 26.dp),
            verticalArrangement = Arrangement.SpaceAround) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                for (zone in zones.take(4)) ZoneMapChip(zone, selectedZone, onZoneSelected)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                for (zone in zones.drop(4).take(2)) ZoneMapChip(zone, selectedZone, onZoneSelected)
            }
        }
    }
}

@Composable
private fun ZoneMapChip(zone: Zone, selectedZone: Zone, onZoneSelected: (Zone) -> Unit) {
    val selected = zone.id == selectedZone.id
    Column(Modifier.background(Color.White.copy(alpha = if (selected) .97f else .86f), MaterialTheme.shapes.small)
        .clickable { onZoneSelected(zone) }.padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(zone.id, color = Color(0xFF17252E), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Text(zone.delta.toString(), color = zoneColor(zone.status), fontSize = 9.sp)
    }
}

private fun zoneColor(status: MoistureStatus): Color = when (status) {
    MoistureStatus.OPTIMAL -> Color(0xFF42B96A)
    MoistureStatus.LOW -> Color(0xFFF0C33E)
    MoistureStatus.DRY -> Color(0xFFE98A35)
    MoistureStatus.VERY_DRY -> Color(0xFFD94B4B)
}

@Composable
private fun IrrigationCard(
    palette: DashboardPalette,
    zone: Zone,
    state: IrrigationStatus,
    runningIrrigationZoneId: String?,
    onExecute: () -> Unit,
    onDefer: () -> Unit,
    modifier: Modifier,
) {
    val isLockedByAnotherZone =
        runningIrrigationZoneId != null && runningIrrigationZoneId != zone.id

    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(32.dp).background(palette.accent.copy(alpha = .13f), MaterialTheme.shapes.small),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.WaterDrop, null, tint = palette.accent, modifier = Modifier.size(18.dp))
                }
                Spacer(Modifier.width(9.dp))
                Column(Modifier.weight(1f)) {
                    Text("AI灌水提案", color = palette.text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("AI RECOMMENDATION", color = palette.muted, fontSize = 9.sp, letterSpacing = 1.sp)
                }
                Text("LIVE", color = palette.success, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
            Divider(color = palette.border)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("${zone.id} ゾーン", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(zone.status.label, color = zoneColor(zone.status), fontSize = 11.sp)
                }
                Text(zone.delta.toString(), color = palette.text, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text(" Δ値", color = palette.muted, fontSize = 10.sp)
            }
            Text(
                when {
                    isLockedByAnotherZone -> "$runningIrrigationZoneId ゾーンの灌水を実行中です。完了後に操作できます。"
                    state == IrrigationStatus.RUNNING -> "灌水を開始しました。バルブの状態を監視しています。"
                    state == IrrigationStatus.DEFERRED -> "この提案は延期しました。次回確認は18:00です。"
                    zone.status == MoistureStatus.VERY_DRY -> "土壌水分が危険域です。24時間以内に灌水してください。"
                    zone.status == MoistureStatus.DRY -> "土壌水分が低下しています。本日中の灌水を推奨します。"
                    else -> "現在の土壌水分は適正範囲です。"
                },
                color = palette.muted, fontSize = 11.sp, lineHeight = 17.sp,
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onExecute, Modifier.weight(1f),
                    enabled = !isLockedByAnotherZone,
                    colors = ButtonDefaults.buttonColors(containerColor = palette.accent),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 9.dp)) {
                    Icon(Icons.Default.PlayArrow, null, modifier = Modifier.size(15.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(if (state == IrrigationStatus.RUNNING) "実行中" else "灌水を実行", fontSize = 11.sp)
                }
                OutlinedButton(onClick = onDefer, Modifier.weight(1f),
                    enabled = !isLockedByAnotherZone,
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 9.dp)) {
                    Icon(Icons.Default.Schedule, null, modifier = Modifier.size(14.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("延期する", fontSize = 11.sp)
                }
            }
            Text("推奨根拠：過去7日間の水分推移・天気予報・作物生育ステージ", color = palette.muted, fontSize = 9.sp)
        }
    }
}

@Composable
private fun ZoneStatusCard(
    palette: DashboardPalette,
    zones: List<Zone>,
    selectedZone: Zone,
    onZoneSelected: (Zone) -> Unit,
    modifier: Modifier,
) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("ゾーンコンディション", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("各ゾーンの土壌水分センサー", color = palette.muted, fontSize = 10.sp)
                }
                Icon(Icons.Default.GridView, null, tint = palette.muted, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth().background(palette.surfaceAlt, MaterialTheme.shapes.small)
                .padding(horizontal = 10.dp, vertical = 7.dp)) {
                TableHeader("ZONE", .8f, palette)
                TableHeader("STATUS", 1.15f, palette)
                TableHeader("Δ VALUE", .85f, palette)
                TableHeader("AI", .35f, palette)
            }
            zones.forEach { zone ->
                Row(Modifier.fillMaxWidth()
                    .background(if (zone.id == selectedZone.id) palette.accent.copy(alpha = .08f) else Color.Transparent)
                    .clickable { onZoneSelected(zone) }.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(zone.id, Modifier.weight(.8f), color = palette.text, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    Row(Modifier.weight(1.15f), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(7.dp).background(zoneColor(zone.status), MaterialTheme.shapes.small))
                        Spacer(Modifier.width(5.dp))
                        Text(zone.status.label, color = palette.muted, fontSize = 10.sp)
                    }
                    Text(zone.delta.toString(), Modifier.weight(.85f), color = palette.text, fontSize = 10.sp)
                    Text(if (zone.recommendation.name == "HIGH") "要対応" else "—", Modifier.weight(.35f),
                        color = if (zone.recommendation.name == "HIGH") palette.danger else palette.muted, fontSize = 9.sp)
                }
            }
        }
    }
}

@Composable
private fun RowScope.TableHeader(text: String, weight: Float, palette: DashboardPalette) {
    Text(text, Modifier.weight(weight), color = palette.muted, fontSize = 9.sp, letterSpacing = .8.sp)
}

@Composable
private fun AlertStatusCard(
    palette: DashboardPalette,
    alerts: List<AlertItem>,
    acknowledgedAlertIds: Set<String>,
    onAcknowledge: (String) -> Unit,
    modifier: Modifier,
) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("アラート", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text("${alerts.count { it.id !in acknowledgedAlertIds }} 件未確認", color = palette.danger, fontSize = 10.sp)
            }
            Spacer(Modifier.height(7.dp))
            alerts.forEach { alert ->
                AlertRow(palette, alert, alert.id in acknowledgedAlertIds, onAcknowledge)
            }
        }
    }
}

@Composable
private fun AlertRow(palette: DashboardPalette, alert: AlertItem, acknowledged: Boolean, onAcknowledge: (String) -> Unit) {
    val color = when (alert.severity) {
        Severity.CRITICAL -> palette.danger
        Severity.WARNING -> palette.warning
        Severity.INFO -> palette.info
    }
    Row(Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.Top) {
        Icon(if (acknowledged) Icons.Default.CheckCircle else if (alert.severity == Severity.INFO) Icons.Default.Info else Icons.Default.Warning,
            null, tint = if (acknowledged) palette.success else color, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(1f)) {
            Text(alert.title, color = if (acknowledged) palette.muted else palette.text, fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(alert.message, color = palette.muted, fontSize = 9.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(alert.time, color = palette.muted, fontSize = 9.sp)
            if (!acknowledged) {
                Text("確認", color = palette.accent, fontSize = 9.sp,
                    modifier = Modifier.clickable { onAcknowledge(alert.id) }.padding(top = 3.dp))
            }
        }
    }
}

@Composable
private fun MoistureTrendCard(
    palette: DashboardPalette,
    selectedZone: Zone,
    moistureHistory: List<Int>,
    modifier: Modifier,
) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("土壌水分の推移", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("${selectedZone.id} ゾーン • 過去24時間", color = palette.muted, fontSize = 10.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ArrowUpward, null, tint = palette.warning, modifier = Modifier.size(13.dp))
                    Text("${selectedZone.delta}", color = palette.warning, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            MoistureChart(palette, moistureHistory, Modifier.fillMaxWidth().weight(1f))
        }
    }
}

@Composable
private fun MoistureChart(palette: DashboardPalette, values: List<Int>, modifier: Modifier) {
    Canvas(modifier.padding(top = 10.dp)) {
        if (values.isEmpty()) return@Canvas

        val min = 1000f
        val max = 3000f
        val stepX = if (values.size > 1) size.width / (values.size - 1) else 0f
        val points = values.mapIndexed { index, value ->
            Offset(index * stepX, size.height - ((value - min) / (max - min)).coerceIn(0f, 1f) * size.height)
        }
        for (i in 0..3) {
            val y = i * size.height / 3f
            drawLine(palette.border, Offset(0f, y), Offset(size.width, y), strokeWidth = 1f)
        }
        val path = Path().apply {
            moveTo(points.first().x, size.height)
            points.forEach { lineTo(it.x, it.y) }
            lineTo(points.last().x, size.height)
            close()
        }
        drawPath(path, palette.accent.copy(alpha = .1f), style = Fill)
        val line = Path().apply {
            points.forEachIndexed { index, point -> if (index == 0) moveTo(point.x, point.y) else lineTo(point.x, point.y) }
        }
        drawPath(line, palette.accent, style = Stroke(2.5f))
        points.forEach { drawCircle(palette.accent, 3f, it) }
    }
}

@Composable
private fun WeatherCard(weather: List<WeatherDay>, palette: DashboardPalette, modifier: Modifier) {
    Card(modifier, colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("5日間の天気予報", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Icon(Icons.Default.Cloud, null, tint = palette.info, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(9.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                weather.forEach { day ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 4.dp)) {
                        Text(day.date.substringBefore(" "), color = palette.muted, fontSize = 9.sp)
                        Text(day.icon, fontSize = 20.sp, modifier = Modifier.padding(vertical = 7.dp))
                        Text("${day.high}°", color = palette.text, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Text("${day.low}°", color = palette.muted, fontSize = 10.sp)
                        Text("${day.rainChance}%", color = palette.info, fontSize = 9.sp, modifier = Modifier.padding(top = 5.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun WorkLogCard(workLogs: List<WorkLog>, palette: DashboardPalette) {
    Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = palette.surface),
        shape = MaterialTheme.shapes.medium, elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Schedule, null, tint = palette.accent, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text("今日の作業記録", color = palette.text, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Text(
                    workLogs.joinToString("  |  ") { "${it.title} • ${it.time}" }.ifEmpty { "本日の作業記録はありません" },
                    color = palette.muted,
                    fontSize = 10.sp,
                )
            }
            Text("すべて見る  ›", color = palette.accent, fontSize = 10.sp)
        }
    }
}

private data class DashboardPalette(
    val background: Color,
    val surface: Color,
    val surfaceAlt: Color,
    val sidebar: Color,
    val sidebarCard: Color,
    val sidebarSelected: Color,
    val sidebarText: Color,
    val sidebarMuted: Color,
    val text: Color,
    val muted: Color,
    val border: Color,
    val accent: Color,
    val success: Color,
    val warning: Color,
    val danger: Color,
    val info: Color,
) {
    companion object {
        val light = DashboardPalette(
            background = Color(0xFFF4F7F6), surface = Color.White, surfaceAlt = Color(0xFFF2F5F4),
            sidebar = Color(0xFF102A2C), sidebarCard = Color(0xFF1B393A), sidebarSelected = Color(0xFF285254),
            sidebarText = Color(0xFFD5E2DF), sidebarMuted = Color(0xFF8FAEAA), text = Color(0xFF1E2B2E),
            muted = Color(0xFF718083), border = Color(0xFFE1E8E6), accent = Color(0xFF2FAD69),
            success = Color(0xFF2D9B5C), warning = Color(0xFFE6A620), danger = Color(0xFFD95050),
            info = Color(0xFF438BD2),
        )
        val dark = DashboardPalette(
            background = Color(0xFF111918), surface = Color(0xFF1A2423), surfaceAlt = Color(0xFF22302E),
            sidebar = Color(0xFF091716), sidebarCard = Color(0xFF122322), sidebarSelected = Color(0xFF1D4542),
            sidebarText = Color(0xFFD1DEDA), sidebarMuted = Color(0xFF789792), text = Color(0xFFE7F0ED),
            muted = Color(0xFF9AAEAA), border = Color(0xFF344441), accent = Color(0xFF59C986),
            success = Color(0xFF65D391), warning = Color(0xFFF0BA4E), danger = Color(0xFFF07171),
            info = Color(0xFF76B5EF),
        )
    }
}
