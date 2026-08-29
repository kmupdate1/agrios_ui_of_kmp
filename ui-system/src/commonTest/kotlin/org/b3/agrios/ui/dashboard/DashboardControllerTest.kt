package org.b3.agrios.ui.dashboard

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import org.b3.agrios.data.InMemoryDashboardRepository
import org.b3.agrios.model.AlertItem
import org.b3.agrios.model.DashboardSnapshot
import org.b3.agrios.model.MoistureStatus
import org.b3.agrios.model.RecommendationLevel
import org.b3.agrios.model.Severity
import org.b3.agrios.model.Zone

class DashboardControllerTest {
    @Test
    fun selectsOnlyExistingZone() {
        val controller = controller()

        controller.onEvent(DashboardEvent.SelectZone("B-01"))
        assertEquals("B-01", controller.state.value.selectedZoneId)

        controller.onEvent(DashboardEvent.SelectZone("UNKNOWN"))
        assertEquals("B-01", controller.state.value.selectedZoneId)
    }

    @Test
    fun runningIrrigationCannotBeReplacedByAnotherZone() {
        val controller = controller()

        controller.onEvent(DashboardEvent.ExecuteIrrigation)
        controller.onEvent(DashboardEvent.SelectZone("B-01"))
        controller.onEvent(DashboardEvent.ExecuteIrrigation)
        controller.onEvent(DashboardEvent.DeferIrrigation)

        assertEquals("A-01", controller.state.value.runningIrrigationZoneId)
        assertEquals(IrrigationStatus.RUNNING, controller.state.value.irrigationStatuses["A-01"])
        assertEquals(IrrigationStatus.DEFERRED, controller.state.value.irrigationStatuses["B-01"])
    }

    @Test
    fun acknowledgementIsIdempotentAndRejectsUnknownAlert() {
        val controller = controller()
        val alertId = controller.state.value.snapshot.alerts.first().id

        controller.onEvent(DashboardEvent.AcknowledgeAlert(alertId))
        val acknowledgedState = controller.state.value
        controller.onEvent(DashboardEvent.AcknowledgeAlert(alertId))
        controller.onEvent(DashboardEvent.AcknowledgeAlert("unknown"))

        assertEquals(setOf(alertId), controller.state.value.acknowledgedAlertIds)
        assertSame(acknowledgedState, controller.state.value)
    }

    @Test
    fun emptySnapshotHasNoSelectedZoneAndIgnoresIrrigation() {
        val controller = DashboardController(
            InMemoryDashboardRepository(snapshot(zones = emptyList())),
        )

        controller.onEvent(DashboardEvent.ExecuteIrrigation)

        assertEquals(null, controller.state.value.selectedZone)
        assertEquals(IrrigationStatus.READY, controller.state.value.selectedIrrigationStatus)
    }

    private fun controller() = DashboardController(
        InMemoryDashboardRepository(snapshot()),
    )

    private fun snapshot(
        zones: List<Zone> = listOf(
            Zone("A-01", MoistureStatus.OPTIMAL, 1_250, RecommendationLevel.NONE),
            Zone("B-01", MoistureStatus.DRY, 2_580, RecommendationLevel.HIGH),
        ),
    ) = DashboardSnapshot(
        farmName = "Test Farm",
        areaHectares = 1.0,
        location = "Test Location",
        lastSyncedAt = "2026/08/29 12:00",
        zones = zones,
        alerts = listOf(
            AlertItem("alert-1", "Alert", "Message", "12:00", Severity.WARNING),
            AlertItem("alert-2", "Alert", "Different message", "12:01", Severity.INFO),
        ),
        weather = emptyList(),
        moistureHistory = emptyList(),
        workLogs = emptyList(),
    )
}