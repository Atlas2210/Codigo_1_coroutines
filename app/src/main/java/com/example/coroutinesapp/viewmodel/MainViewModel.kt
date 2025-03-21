package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var sequentialTime by mutableStateOf(0L)
        private set

    var concurrentTime by mutableStateOf(0L)
        private set

    var countTime1 by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    var countTime3 by mutableStateOf(0)
        private set

    var countTime4 by mutableStateOf(0)
        private set

    var countTime5 by mutableStateOf(0)
        private set

    private var job: Job? = null

    fun startCounters() {
        job = viewModelScope.launch {
            countTime1 = 0
            countTime2 = 0
            resultState = "Ejecutando Contador 1..."
            runFirstCounter()
            resultState = "Ejecutando Contador 2..."
            runSecondCounter()
            resultState = "Finalizado"
        }

    }

    fun cancelCounters() {
        job?.cancel()
        resultState = "Contadores Cancelados"
    }

    private suspend fun runFirstCounter() {
        for (i in 1..5) {
            delay(1000)
            countTime1 = i
        }
    }

    private suspend fun runSecondCounter() {
        for (i in 1..5) {
            delay(1000)
            countTime2 = i
        }
    }
}