package org.b3.agrios.data

import org.b3.agrios.model.DashboardSnapshot

interface DashboardRepository {
    fun loadDashboard(): DashboardSnapshot
}

class InMemoryDashboardRepository(
    private val snapshot: DashboardSnapshot,
) : DashboardRepository {
    override fun loadDashboard(): DashboardSnapshot = snapshot
}

fun demoDashboardRepository(): DashboardRepository =
    InMemoryDashboardRepository(DummyData.dashboard)