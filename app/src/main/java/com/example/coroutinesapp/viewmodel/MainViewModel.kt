package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.io.IOException

class MainViewModel : ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var countTime1 by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    private var job: Job? = null

    private val sharedLock = Any()
    private var isVariableBeingUsed = false

    fun startCounters() {
        job = viewModelScope.launch {
            countTime1 = 0
            countTime2 = 0
            resultState = "Ejecutando Contador 1..."

            val job1 = launch { runFirstCounter() }
            val job2 = launch { runSecondCounter() }

            job1.join()
            job2.join()

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

            try {
                writeToSharedVariable("Contador 1: $i")
            } catch (e: IOException) {
                println("Error en el hilo 1: ${e.message}")
            }

            countTime1 = i
        }
    }

    private suspend fun runSecondCounter() {
        for (i in 1..5) {
            delay(1000)

            try {
                writeToSharedVariable("Contador 2: $i")
            } catch (e: IOException) {
                println("Error en el hilo 2: ${e.message}")
            }

            countTime2 = i
        }
    }

    private fun writeToSharedVariable(data: String) {
        synchronized(sharedLock) {
            if (isVariableBeingUsed) {
                throw IOException("Acceso simultáneo a la variable compartida.")
            } else {
                isVariableBeingUsed = true
                println(data)
                isVariableBeingUsed = false
            }
        }
    }
}