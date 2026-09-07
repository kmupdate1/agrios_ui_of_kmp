package org.b3.agrios.model

enum class MoistureStatus(
    val label: String,
) {
    OPTIMAL("適正"),
    LOW("低下中"),
    DRY("乾燥"),
    VERY_DRY("非常に乾燥"),
}

enum class RecommendationLevel {
    NONE,
    HIGH,
}

data class Zone(
    val id: String,
    val status: MoistureStatus,
    val delta: Int,
    val recommendation: RecommendationLevel,
)

data class AlertItem(
    val id: String,
    val title: String,
    val message: String,
    val time: String,
    val severity: Severity,
)

enum class Severity {
    CRITICAL,
    WARNING,
    INFO,
}

data class WeatherDay(
    val date: String,
    val icon: String,
    val high: Int,
    val low: Int,
    val rainChance: Int,
)

data class WorkLog(
    val title: String,
    val time: String,
)

data class DashboardSnapshot(
    val farmName: String,
    val areaHectares: Double,
    val location: String,
    val lastSyncedAt: String,
    val zones: List<Zone>,
    val alerts: List<AlertItem>,
    val weather: List<WeatherDay>,
    val moistureHistory: List<Int>,
    val workLogs: List<WorkLog>,
)
