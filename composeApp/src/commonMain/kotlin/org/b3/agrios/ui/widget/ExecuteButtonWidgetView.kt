package org.b3.agrios.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

class ExecuteButtonWidgetView : View {
    @Composable
    override fun onRender() {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { println("ExecuteButtonWidgetView.onClick()") },
            content = { Text("${this@ExecuteButtonWidgetView::class.simpleName}") }
        )
    }
}
