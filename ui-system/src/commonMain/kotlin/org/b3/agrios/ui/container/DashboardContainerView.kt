package org.b3.agrios.ui.container

import org.b3.agrios.ui.capability.Attachable
import org.b3.agrios.ui.capability.Detachable
import org.b3.agrios.ui.capability.Movable
import org.b3.agrios.ui.capability.Resizable

interface DashboardContainerView :
    ContainerView,
    Attachable,
    Detachable,
    Resizable,
    Movable
