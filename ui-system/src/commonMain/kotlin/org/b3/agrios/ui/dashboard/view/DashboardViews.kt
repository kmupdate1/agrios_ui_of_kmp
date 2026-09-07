package org.b3.agrios.ui.dashboard.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.DashboardBody
import org.b3.agrios.ui.DashboardHeader
import org.b3.agrios.ui.DashboardSidebar
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.dashboard.DashboardController
import org.b3.agrios.ui.dashboard.DashboardEvent
import org.b3.agrios.ui.dashboard.DashboardNavigationItem
import org.b3.agrios.ui.dashboard.IrrigationStatus
import org.b3.agrios.ui.impl.console.AgriOsConsoleContentContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleRootContainerView
import org.b3.agrios.ui.view.View
import org.b3.agrios.model.DashboardSnapshot
import org.b3.agrios.model.Zone

class DashboardRootContainerView(
    private val controller: DashboardController,
    private val modifier: Modifier = Modifier,
) : ContainerView {
    override val stylesKey: StylesKey = StylesKey.ConsoleRoot

    @Composable
    override fun onRender() {
        val state by controller.state
        val eventSink = controller::onEvent

        AgriOsConsoleRootContainerView(
            sideNav = DashboardSideNavView(
                selectedItem = state.selectedNavigation,
                onEvent = eventSink,
            ),
            consoleContent = AgriOsConsoleContentContainerView(
                header = DashboardHeaderView(
                    farmName = state.snapshot.farmName,
                    lastSyncedAt = state.snapshot.lastSyncedAt,
                    isDarkMode = state.isDarkMode,
                    onToggleTheme = { eventSink(DashboardEvent.ToggleTheme) },
                ),
                content = DashboardBodyContainerView(
                    snapshot = state.snapshot,
                    selectedZone = state.selectedZone,
                    irrigationStatus = state.selectedIrrigationStatus,
                    runningIrrigationZoneId = state.runningIrrigationZoneId,
                    acknowledgedAlertIds = state.acknowledgedAlertIds,
                    onEvent = eventSink,
                ),
            ),
            modifier = modifier,
        ).onRender()
    }
}

internal class DashboardSideNavView(
    private val selectedItem: DashboardNavigationItem,
    private val onEvent: (DashboardEvent) -> Unit,
) : View {
    override val stylesKey: StylesKey = StylesKey.DashboardSideNav

    @Composable
    override fun onRender() {
        DashboardSidebar(
            palette = MaterialTheme.colorScheme,
            selectedItem = selectedItem,
            onItemSelected = { onEvent(DashboardEvent.SelectNavigation(it)) },
        )
    }
}

internal class DashboardHeaderView(
    private val farmName: String,
    private val lastSyncedAt: String,
    private val isDarkMode: Boolean,
    private val onToggleTheme: () -> Unit,
) : View {
    override val stylesKey: StylesKey = StylesKey.DashboardHeader

    @Composable
    override fun onRender() {
        DashboardHeader(
            palette = MaterialTheme.colorScheme,
            farmName = farmName,
            lastSyncedAt = lastSyncedAt,
            isDarkMode = isDarkMode,
            onToggleTheme = onToggleTheme,
        )
    }
}

internal class DashboardBodyContainerView(
    private val snapshot: DashboardSnapshot,
    private val selectedZone: Zone?,
    private val irrigationStatus: IrrigationStatus,
    private val runningIrrigationZoneId: String?,
    private val acknowledgedAlertIds: Set<String>,
    private val onEvent: (DashboardEvent) -> Unit,
) : ContainerView {
    override val stylesKey: StylesKey = StylesKey.DashboardBody

    @Composable
    override fun onRender() {
        DashboardBody(
            palette = MaterialTheme.colorScheme,
            snapshot = snapshot,
            selectedZone = selectedZone,
            irrigationStatus = irrigationStatus,
            runningIrrigationZoneId = runningIrrigationZoneId,
            acknowledgedAlertIds = acknowledgedAlertIds,
            onEvent = onEvent,
        )
    }
}