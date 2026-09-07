package org.b3.agrios.ui.dashboard

import org.b3.agrios.model.DashboardSnapshot
import org.b3.agrios.model.Zone

data class DashboardUiState(
    val snapshot: DashboardSnapshot,
    val selectedZoneId: String?,
    val selectedNavigation: DashboardNavigationItem = DashboardNavigationItem.DASHBOARD,
    val isDarkMode: Boolean = false,
    val irrigationStatuses: Map<String, IrrigationStatus> = emptyMap(),
    val acknowledgedAlertIds: Set<String> = emptySet(),
) {
    val selectedZone: Zone?
        get() = snapshot.zones.firstOrNull { it.id == selectedZoneId }

    val unacknowledgedAlertCount: Int
        get() = snapshot.alerts.count { it.id !in acknowledgedAlertIds }

    val selectedIrrigationStatus: IrrigationStatus
        get() = selectedZoneId
            ?.let { irrigationStatuses[it] }
            ?: IrrigationStatus.READY

    val runningIrrigationZoneId: String?
        get() = irrigationStatuses.entries
            .firstOrNull { it.value == IrrigationStatus.RUNNING }
            ?.key

    companion object {
        fun from(snapshot: DashboardSnapshot): DashboardUiState =
            DashboardUiState(
                snapshot = snapshot,
                selectedZoneId = snapshot.zones.firstOrNull()?.id,
            )
    }
}