package com.cortez.apirestidgs903
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import com.cortez.apirestidgs903.ui.theme.ApiRestIDGS903Theme
class MainActivity: ComponentActivity() {
    override fun  onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        enableEdgeToEdge()
        val viewModel: StudentViewModel by viewModels()
        setContent {
            ApiRestIDGS903Theme {
                StudentScreen(viewModel)
            }
        }
    }
}