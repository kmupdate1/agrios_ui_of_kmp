package org.b3.agrios.ui.impl.sidenav.sidenav

sealed interface SideNavState {
    data object Normal : SideNavState
    data object Closed : SideNavState
}
