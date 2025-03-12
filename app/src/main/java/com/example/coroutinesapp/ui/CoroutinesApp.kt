package com.example.coroutinesapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.coroutinesapp.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contador 1: ${viewModel.countTime1} s")
        Text("Contador 2: ${viewModel.countTime2} s")
        Text(viewModel.resultState)
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            viewModel.startCounters()
        }) {
            Text("Iniciar Contadores")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.cancelCounters()
        },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Cancelar Contadores")
        }
    }
}