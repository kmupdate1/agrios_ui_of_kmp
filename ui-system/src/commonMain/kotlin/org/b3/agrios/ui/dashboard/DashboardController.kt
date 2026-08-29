package org.b3.agrios.ui.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import org.b3.agrios.data.DashboardRepository

class DashboardController(
    repository: DashboardRepository,
) {
    private val _state = mutableStateOf(
        DashboardUiState.from(repository.loadDashboard()),
    )

    val state: State<DashboardUiState>
        get() = _state

    fun onEvent(event: DashboardEvent) {
        val current = _state.value
        _state.value = when (event) {
            is DashboardEvent.SelectZone -> current.selectZone(event.zoneId)
            is DashboardEvent.SelectNavigation -> current.copy(selectedNavigation = event.item)
            DashboardEvent.ToggleTheme -> current.copy(isDarkMode = !current.isDarkMode)
            DashboardEvent.ExecuteIrrigation -> current.executeIrrigation()
            DashboardEvent.DeferIrrigation -> current.deferIrrigation()
            is DashboardEvent.AcknowledgeAlert -> current.acknowledgeAlert(event.alertId)
        }
    }

    private fun DashboardUiState.selectZone(zoneId: String): DashboardUiState =
        if (snapshot.zones.any { it.id == zoneId }) copy(selectedZoneId = zoneId) else this

    private fun DashboardUiState.executeIrrigation(): DashboardUiState {
        val zoneId = selectedZoneId ?: return this
        val runningZoneId = runningIrrigationZoneId
        return if (runningZoneId == null || runningZoneId == zoneId) {
            copy(irrigationStatuses = irrigationStatuses + (zoneId to IrrigationStatus.RUNNING))
        } else this
    }

    private fun DashboardUiState.deferIrrigation(): DashboardUiState {
        val zoneId = selectedZoneId ?: return this
        return if (irrigationStatuses[zoneId] != IrrigationStatus.RUNNING) {
            copy(irrigationStatuses = irrigationStatuses + (zoneId to IrrigationStatus.DEFERRED))
        } else this
    }

    private fun DashboardUiState.acknowledgeAlert(alertId: String): DashboardUiState =
        if (snapshot.alerts.any { it.id == alertId } && alertId !in acknowledgedAlertIds) {
            copy(acknowledgedAlertIds = acknowledgedAlertIds + alertId)
        } else this
}