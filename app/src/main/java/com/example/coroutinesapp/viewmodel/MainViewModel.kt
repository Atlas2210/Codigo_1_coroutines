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

    var countTime1 by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    private var job1: Job? = null
    private var job2: Job? = null

    fun startCounters() {
        val N = 2 // Definimos un valor de N
        val repetitions = listOf(N, 2 * N, 3 * N, 4 * N, 5 * N)

        viewModelScope.launch {
            for (repetition in repetitions) {
                resultState = "Ejecutando $repetition veces..."

                val sequentialTime = measureTime {
                    runSequentialCounters(repetition)
                }
                println("Tiempo secuencial para $repetition repeticiones: $sequentialTime")

                val concurrentTime = measureTime {
                    runConcurrentCounters(repetition)
                }
                println("Tiempo concurrente para $repetition repeticiones: $concurrentTime")
            }

            resultState = "Finalizado"
        }
    }

    fun cancelCounters() {
        job1?.cancel()
        job2?.cancel()
        resultState = "Contadores Cancelados"
    }

    private suspend fun runSequentialCounters(repetitions: Int) {
        countTime1 = 0
        countTime2 = 0

        for (i in 1..repetitions) {
            runFirstCounter()
            runSecondCounter()
        }
    }

    private suspend fun runConcurrentCounters(repetitions: Int) {
        countTime1 = 0
        countTime2 = 0

        job1 = viewModelScope.launch {
            for (i in 1..repetitions) {
                runFirstCounter()
            }
        }

        job2 = viewModelScope.launch {
            for (i in 1..repetitions) {
                runSecondCounter()
            }
        }

        job1?.join()
        job2?.join()
    }

    private suspend fun runFirstCounter() {
        delay(1000)
        countTime1++
    }

    private suspend fun runSecondCounter() {
        delay(1000)
        countTime2++
    }

    private inline fun measureTime(action: () -> Unit): Long {
        val startTime = System.nanoTime()
        action()
        val endTime = System.nanoTime()
        return endTime - startTime
        }
}