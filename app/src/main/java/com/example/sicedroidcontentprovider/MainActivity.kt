package com.example.sicedroidcontentprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sicedroidcontentprovider.ui.ViewModel.ContentViewModel
import com.example.sicedroidcontentprovider.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: ContentViewModel = viewModel()
            NavGraph()

        }

    }

}