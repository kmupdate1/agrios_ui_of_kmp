package org.b3.agrios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.b3.agrios.lifecycle.Lifecycle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.onCreate()
        application.onPrepare()

        setContent { application.Content() }
    }

    override fun onStart() {
        super.onStart()
        application.onStart()
    }

    override fun onStop() {
        application.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        application.onDestroy()
        super.onDestroy()
    }

    private val application: Lifecycle = Application
}
