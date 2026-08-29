package org.b3.agrios.ui.dashboard

sealed interface DashboardEvent {
    data class SelectZone(val zoneId: String) : DashboardEvent
    data class SelectNavigation(val item: DashboardNavigationItem) : DashboardEvent
    data object ToggleTheme : DashboardEvent
    data object ExecuteIrrigation : DashboardEvent
    data object DeferIrrigation : DashboardEvent
    data class AcknowledgeAlert(val alertId: String) : DashboardEvent
}

enum class DashboardNavigationItem {
    DASHBOARD,
    FARM_MAP,
    ZONES,
    SENSORS,
    IRRIGATION,
    EQUIPMENT,
    ALERTS,
    AI_REPORTS,
    HISTORY,
    SETTINGS,
}

enum class IrrigationStatus {
    READY,
    RUNNING,
    DEFERRED,
}